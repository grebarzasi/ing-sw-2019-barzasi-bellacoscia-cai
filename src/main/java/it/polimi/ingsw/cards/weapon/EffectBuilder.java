package it.polimi.ingsw.cards.weapon;

import com.fasterxml.jackson.databind.JsonNode;
import it.polimi.ingsw.cards.Ammo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * class that create a weapon effect given the jsonNode of that effect . It parse the jsonNode creating the effect chain.
 * Uses {@link AimingBuilder} to parse and create the chain of filters needed for aiming a target.
 * @author Gregorio Barzasi
 */
public class EffectBuilder {

    //create subEffect array
    private static  ArrayList<SubEffect> subEffList = new ArrayList<>();


    public static Effect buildEffect(JsonNode effectsNode){
        //initialize effect cost
        JsonNode costNode = effectsNode.get("cost");
        Ammo cost = new Ammo(costNode.get("red").asInt(),costNode.get("blue").asInt(),costNode.get("yellow").asInt());

        //generate subEffect
        JsonNode subEffNumerationNode = effectsNode.get("subEffects");

        //iterate on sub effect Numeration Node
        Iterator<String> numIterator = subEffNumerationNode.fieldNames();
        while(numIterator.hasNext()) {

            //iterate on subEffect Node for each numeration
            JsonNode subEffNode = subEffNumerationNode.get(numIterator.next());
            Iterator<String> atomicIterator = subEffNode.fieldNames();

            while(atomicIterator.hasNext()) {
                switch(atomicIterator.next()) {

                    //set target acq
                    case "targetAcq":
                        subEffList.add(AimingBuilder.buildTargetAcquisition(subEffNode.get("targetAcq")));
                        break;

                    //set damage and mark
                    case "shootTarget":
                        subEffList.add(buildShootTarget(subEffNode.get("shootTarget")));
                        break;

                    //set move
                    case "moveTarget":
                        subEffList.add(buildMoveTarget(subEffNode.get("moveTarget")));
                        break;
                }
            }
        }
        //return the created Effect Object
        return new Effect(cost,subEffList);
    }

    public static MoveTarget buildMoveTarget(JsonNode moveNode){
        return null;
    }

    public static ShootTarget buildShootTarget(JsonNode shootNode){
        return null;
    }

}

