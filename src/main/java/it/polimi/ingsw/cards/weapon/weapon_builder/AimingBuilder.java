package it.polimi.ingsw.cards.weapon.weapon_builder;

import com.fasterxml.jackson.databind.JsonNode;
import it.polimi.ingsw.cards.weapon.aiming.AimingFilter;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
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

                case "visibleSquare":
                    aimRoutine.add(buildVisibleSquare(aimFilterNode));
                    break;

                case "different":
                    aimRoutine.add(buildDifferent(aimFilterNode));
                    break;

                case "equal":
                    aimRoutine.add(buildEqual(aimFilterNode));
                    break;

                case "substitute":
                    aimRoutine.add(buildSubstitute(aimFilterNode));
                    break;

                case "range":
                    aimRoutine.add(buildRange(aimFilterNode));
                    break;

                case "direction":
                    aimRoutine.add(new AimDirection(aimFilterNode.path("wallBang").asBoolean()));
                    break;

                case "askPlayer":
                    aimRoutine.add(buildAskPlayer(aimFilterNode));
                    break;
            }
        }
        return new TargetAcquisition(aimRoutine);
    }

    /**
     * Method that build the AimVisible filter. Throws NullPointerException in case some required field is missing or
     * bad named;     *
     *
     * @param node containing well built json
     * @return {@link AimVisible} Object
     */

    public static AimSquare buildVisibleSquare(JsonNode node) {
        try {
            boolean mine = node.get("mine").asBoolean();
            String msg = node.path("msg").asText();
            int min = node.path("rangeMin").asInt();
            int max = node.path("rangeMax").asInt();
            return new AimSquare(mine,msg,min,max);
        } catch (NullPointerException e) {
            throw new NullPointerException("Bad json");
        }
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
        while (element.hasNext()) {
//            source.add(element.next().toString().replace("\"", ""));
            source.add(element.next().textValue());
        }
        return new AimEqual(source);
    }
    /**
     * Method that build the AimSubstitute filter.
     *
     * @param node containing well built json
     * @return {@link AimSubstitute} Object
     */

    public static AimSubstitute buildSubstitute(JsonNode node) {
        ArrayList<String> source = new ArrayList<>();
        Iterator<JsonNode> element = node.elements();
        while (element.hasNext())
            source.add(element.next().toString().replace("\"", ""));
        return new AimSubstitute(source);
    }

    /**
     * Method that build the AimAskPlayer filter.
     *
     * @param node containing well built json
     * @return {@link AimAskPlayer} Object
     */

    public static AimAskPlayer buildAskPlayer(JsonNode node) {
            Integer numMax = node.path("num").asInt();
            boolean fromDiffSquare = node.path("diffSquare").asBoolean();
            boolean selectSquare = node.path("selectSquare").asBoolean();
            boolean selectRoom = node.path("selectRoom").asBoolean();
            String msg= node.path("msg").asText();
            return new AimAskPlayer(msg,numMax, fromDiffSquare);
    }

    /**
     * Method that build the AimEqual filter.
     *
     * @param node containing well built json
     * @return {@link AimEqual} Object
     */

    public static AimRange buildRange(JsonNode node) {
        try {
            Integer minRange = node.path("min").asInt();
            Integer maxRange = node.path("max").asInt();
            return new AimRange(minRange, maxRange);
        } catch (NullPointerException e) {
            throw new NullPointerException("Bad json in BuildRange");
        }
    }
}
