package it.polimi.ingsw.cards.weapon;

import com.fasterxml.jackson.databind.JsonNode;
import it.polimi.ingsw.cards.weapon.aiming.*;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * class that create a aiming filter given the jsonNode of that effect target . It parse the jsonNode creating the filter chain.
 *
 * @author Gregorio Barzasi
 */
public class AimingBuilder {
    public static TargetAcquisition buildTargetAcquisition(JsonNode targetNode) {

        //create aiming filter array
        ArrayList<AimingFilter> aimRoutine = new ArrayList<>();

        //Iterate on aiming filters in json and create the chain of filters
        Iterator<String> aimFilter = targetNode.fieldNames();
        while (aimFilter.hasNext()) {
            //add the right filter
            String field = aimFilter.next();
            JsonNode aimFilterNode = targetNode.path(field);
            switch (field) {

                case "isVisible":
                    aimRoutine.add(buildIsVisible(aimFilterNode));
                    break;

                case "different":
                    aimRoutine.add(buildDifferent(aimFilterNode));
                    break;

                case "equal":
                    aimRoutine.add(buildEqual(aimFilterNode));
                    break;

                case "range":
                    aimRoutine.add(new AimRange(aimFilterNode.path("min").asInt(), aimFilterNode.path("max").asInt()));
                    break;

                case "direction":
                    aimRoutine.add(new AimDirection());
                    break;

                case "askPlayer":
                    aimRoutine.add(buildAskPlayer(aimFilterNode));
                    break;
            }
        }

        return null;
    }

    /**
     * Method that build the AimVisible filter. Throws NullPointerException in case some required field is missing or
     * bad named;     *
     *
     * @param node containing well built json
     * @return {@link AimVisible} Object
     */

    public static AimVisible buildIsVisible(JsonNode node) {
        try {
            boolean visible = node.get("visible").asBoolean();
            String origin = node.path("origin").asText();
            if (origin.isEmpty())
                return new AimVisible(visible);
            return new AimVisible(visible, origin);
        } catch (NullPointerException e) {
            throw new NullPointerException("Bad json");
        }
    }

    /**
     * Method that build the AimDifferent filter.
     *
     * @param node containing well built json
     * @return {@link AimDifferent} Object
     */

    public static AimDifferent buildDifferent(JsonNode node) {
        ArrayList<String> source = new ArrayList<>();
        Iterator<JsonNode> element = node.elements();
        while (element.hasNext())
            source.add(element.next().toString().replace("\"", ""));
        return new AimDifferent(source);
    }

    /**
     * Method that build the AimEqual filter.
     *
     * @param node containing well built json
     * @return {@link AimEqual} Object
     */

    public static AimEqual buildEqual(JsonNode node) {
        ArrayList<String> source = new ArrayList<>();
        Iterator<JsonNode> element = node.elements();
        while (element.hasNext())
            source.add(element.next().toString().replace("\"", ""));
        return new AimEqual(source);
    }

    /**
     * Method that build the AimEqual filter.
     *
     * @param node containing well built json
     * @return {@link AimEqual} Object
     */

    public static AimAskPlayer buildAskPlayer(JsonNode node) {
        try {
            Integer numMax = node.get("num").asInt();
            boolean fromDiffSquare = node.path("diffSquare").asBoolean();
            return new AimAskPlayer(numMax, fromDiffSquare);
        } catch (NullPointerException e) {
            throw new NullPointerException("Bad json");
        }
    }
}
