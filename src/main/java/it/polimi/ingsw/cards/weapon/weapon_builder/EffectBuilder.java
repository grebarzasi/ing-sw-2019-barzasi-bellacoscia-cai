package it.polimi.ingsw.cards.weapon.weapon_builder;

import com.fasterxml.jackson.databind.JsonNode;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.MoveTarget;
import it.polimi.ingsw.cards.weapon.ShootTarget;
import it.polimi.ingsw.cards.weapon.SubEffect;
import it.polimi.ingsw.cards.weapon.weapon_builder.AimingBuilder;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * class that create a weapon effect given the jsonNode of that effect . It parse the jsonNode creating the effect chain.
 * Uses {@link AimingBuilder} to parse and create the chain of filters needed for aiming a target.
 * @author Gregorio Barzasi
 */
public class EffectBuilder {

    public static Effect buildEffect(JsonNode effectsNode){
        //initialize effect cost
        Ammo cost = buildCost(effectsNode.path("cost"));
        String name = effectsNode.path("name").asText();

        ArrayList<SubEffect> subEffList = new ArrayList<>();

        /*_BUILD THE ACTUAL EFFECT_*/

        //generate subEffect numeration Node and iterate
        JsonNode subEffNumerationNode = effectsNode.get("subEffect");
        Iterator<String> numIterator = subEffNumerationNode.fieldNames();

        while(numIterator.hasNext()) {
            String numeration = numIterator.next();

            //NOW iterate on REAL subEffect Node for each numeration
            JsonNode subEffNode = subEffNumerationNode.get(numeration);
            Iterator<String> subEffIterator = subEffNode.fieldNames();
            while(subEffIterator.hasNext()) {
                String subEff = subEffIterator.next();

                switch(subEff) {

                    //set target acq
                    case "targetAcq":
                        subEffList.add(AimingBuilder.buildTargetAcquisition(subEffNode.path("targetAcq")));
                        break;

                    //set damage and mark
                    case "shootTarget":
                        subEffList.add(buildShootTarget(subEffNode.path("shootTarget")));
                        break;

                    //set move
                    case "moveTarget":
                        subEffList.add(buildMoveTarget(subEffNode.path("moveTarget")));
                        break;
                }
            }
        }
        //return the created Effect Object
        return new Effect(name,cost,subEffList);
    }

    public static MoveTarget buildMoveTarget(JsonNode node){
        Integer maxStep = node.path("step").asInt();
        String finalPos = node.path("finalPos").asText();
        boolean directional = node.path("directional").asBoolean();
        return new MoveTarget(maxStep,finalPos,directional);
    }

    public static ShootTarget buildShootTarget(JsonNode node){
            Integer damage = node.path("damage").asInt();
            Integer mark = node.path("mark").asInt();
            return new ShootTarget(damage, mark);
    }

    public static Ammo buildCost(JsonNode node){
        return new Ammo(node.path("red").asInt(),node.path("blue").asInt(),node.path("yellow").asInt());
    }

}

