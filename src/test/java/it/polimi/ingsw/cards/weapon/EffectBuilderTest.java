package it.polimi.ingsw.cards.weapon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.weapon.aiming.AimRange;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**Test Efefct creation in different scenario
 * @author Gregorio Barzasi
 */
public class EffectBuilderTest {

    @Test
    public void buildEffect() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{" +
                "\"cost\":{\"red\": 1}," +
                "\"subEffects\":{" +
                    "\"1\":{" +
                        "\"targetAcq\":{\"isVisible\":{\"visible\":true},\"askPlayer\":{\"num\":1}}," +
                        "\"shootTarget\": {\"damage\": 1,\"mark\": 1}," +
                        "\"moveTarget\": {\"maxStep\": 1}" +
                    "}," +

                    "\"2\":{" +
                        "\"targetAcq\":{\"isVisible\":{\"visible\":true},\"askPlayer\":{\"num\":5}}," +
                        "\"shootTarget\": {\"damage\": 5}," +
                        "\"moveTarget\": {\"maxStep\": 2}" +
                        "}}" +
                    "}";

        JsonNode newNode = mapper.readTree(newString);
        Effect effect = EffectBuilder.buildEffect(newNode);
        assertEquals(1,effect.getCost().getRed());
        assertTrue(effect.getEffectList().get(0) instanceof TargetAcquisition );
        assertTrue(effect.getEffectList().get(1) instanceof ShootTarget );
        assertTrue(effect.getEffectList().get(2) instanceof MoveTarget );
        assertTrue(effect.getEffectList().get(3) instanceof TargetAcquisition );
        assertTrue(effect.getEffectList().get(4) instanceof ShootTarget );
        assertTrue(effect.getEffectList().get(5) instanceof MoveTarget );
    }



    /**BUILD MOVE TARGET
     * Method that build the MoveTarget Effect.
     * Parse max steps.
     *
     */
    @Test
    public void buildMoveTarget() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"maxStep\":8}";
        JsonNode newNode = mapper.readTree(newString);
        MoveTarget target = EffectBuilder.buildMoveTarget(newNode);
        assertEquals(8,(int)target.getMaxSteps());
    }


    /**BUILD SHOOT TARGET
     * Method that build the ShootTarget Effect.
     * Parse damage num and mark num from json node.
     * if one is missing corresponding field =0.
     *
     */

    @Test
    public void buildShootTargetComplete() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"damage\":3,\"mark\":5}";
        JsonNode newNode = mapper.readTree(newString);
        ShootTarget target = EffectBuilder.buildShootTarget(newNode);
        assertEquals(3,(int)target.getDamageNum());
        assertEquals(5,(int)target.getMarkNum());
    }

    @Test
    public void buildShootTargetSingle() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //min
        String newStringDam = "{\"damage\":3}";
        JsonNode newNodeDam = mapper.readTree(newStringDam);
        ShootTarget targetDam = EffectBuilder.buildShootTarget(newNodeDam);
        assertEquals(3,(int)targetDam.getDamageNum());
        assertEquals(0,(int)targetDam.getMarkNum());
        //max
        String newStringMark = "{\"mark\":5}";
        JsonNode newNodeMark = mapper.readTree(newStringMark);
        ShootTarget targetMark = EffectBuilder.buildShootTarget(newNodeMark);
        assertEquals(5,(int)targetMark.getMarkNum());
        assertEquals(0,(int)targetMark.getDamageNum());
    }

    /**BUILD AMMO COST
     * Method that build the Cost of every effect.
     * Parse int value for every type of ammo.
     * if one is missing corresponding field =0.
     *
     */
    @Test
    public void buildCostComplete() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"red\":3,\"blue\":1,\"yellow\":5}";
        JsonNode newNode = mapper.readTree(newString);
        Ammo ammo = EffectBuilder.buildCost(newNode);
        assertEquals(3,(int)ammo.getRed());
        assertEquals(1,(int)ammo.getBlue());
        assertEquals(5,(int)ammo.getYellow());
    }
    @Test
    public void buildCostRed() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"red\":3}";
        JsonNode newNode = mapper.readTree(newString);
        Ammo ammo = EffectBuilder.buildCost(newNode);
        assertEquals(3,(int)ammo.getRed());
        assertEquals(0,(int)ammo.getBlue());
        assertEquals(0,(int)ammo.getYellow());
    }
    @Test
    public void buildCostCouple() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"blue\":3,\"red\":6}";
        JsonNode newNode = mapper.readTree(newString);
        Ammo ammo = EffectBuilder.buildCost(newNode);
        assertEquals(6,(int)ammo.getRed());
        assertEquals(3,(int)ammo.getBlue());
        assertEquals(0,(int)ammo.getYellow());
    }
}