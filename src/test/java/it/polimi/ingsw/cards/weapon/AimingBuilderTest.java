package it.polimi.ingsw.cards.weapon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.cards.weapon.aiming.*;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.cards.weapon.weapon_builder.AimingBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Gregorio Barzasi
 */
public class AimingBuilderTest {

    @Test
    public void BuildTargetAcquisition() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"isVisible\":{\"visible\":true},\"different\":[\"ciao\",\"prova\"]," +
                "\"equal\":[\"ciao\",\"prova\"],\"direction\":null,\"askPlayer\":{\"num\":3}" +
                ",\"range\":{\"min\":3}}";
        JsonNode newNode = mapper.readTree(newString);
        TargetAcquisition target = AimingBuilder.buildTargetAcquisition(newNode);
        assertTrue(target instanceof TargetAcquisition);
        assertTrue(target.getAimRoutine().get(0) instanceof AimVisible);
        assertTrue(target.getAimRoutine().get(1) instanceof AimDifferent);
        assertTrue(target.getAimRoutine().get(2) instanceof AimEqual);
        assertTrue(target.getAimRoutine().get(3) instanceof AimDirection);
        assertTrue(target.getAimRoutine().get(4) instanceof AimAskPlayer);
        assertTrue(target.getAimRoutine().get(5) instanceof AimRange);

    }

    /**IS VISIBLE
     * Method that build the AimVisible filter. Throws NullPointerException in case some required field is missing or
     * bad named; tests also the case with all field well written and the case with only mandatory field;
     */
    @Test
    public void buildIsVisibleAllCorrect() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"visible\":true,\"origin\":\"last\"}";
        JsonNode newNode = mapper.readTree(newString);
        AimVisible target = AimingBuilder.buildIsVisible(newNode);
        assertTrue(target.isVisible());
        assertEquals("last",target.getOrigin());
    }

    @Test
    public void buildIsVisibleOnlyVisible() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"visible\":true}";
        JsonNode newNode = mapper.readTree(newString);
        AimVisible target = AimingBuilder.buildIsVisible(newNode);
        assertTrue(target.isVisible());
        assertNull(target.getOrigin());
    }

    @Test
    public void buildIsVisibleBadJson() throws IOException {
        assertThrows(NullPointerException.class,
                ()-> {
                    ObjectMapper mapper = new ObjectMapper();
                    String newString = "{\"ciao\":true}";
                    JsonNode newNode = mapper.readTree(newString);
                    AimVisible target = AimingBuilder.buildIsVisible(newNode);
                    assertNull(target);
                });
    }

    /**BUILD DIFFERENT AND EQUAL
     * Method that build the AimDifferrent And Equal filter.
     * parse an array of string into an ArrayList.
     */
    @Test
    public void buildDifferentAndEqual() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "[\"casa\",\"chiesa\"]";
        JsonNode newNode = mapper.readTree(newString);
        AimDifferent targetDiff = AimingBuilder.buildDifferent(newNode);
        AimEqual targetEqual = AimingBuilder.buildEqual(newNode);
        assertEquals("casa",targetDiff.getSourceList().get(0));
        assertEquals("chiesa",targetDiff.getSourceList().get(1));
        assertEquals("casa",targetEqual.getSourceList().get(0));
        assertEquals("chiesa",targetEqual.getSourceList().get(1));
    }


    /**BUILD ASK PLAYER
     * Method that build the Ask Player filter.
     *
     */

    @Test
    public void buildAskPlayerIncomplete() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"num\":3}";
        JsonNode newNode = mapper.readTree(newString);
        AimAskPlayer target = AimingBuilder.buildAskPlayer(newNode);
        assertFalse(target.isFromDiffSquare());
        assertEquals(3,(int)target.getNumMax());
    }


    @Test
    public void buildAskPlayer() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"num\":3,\"diffSquare\":\"true\"}";
        JsonNode newNode = mapper.readTree(newString);
        AimAskPlayer target = AimingBuilder.buildAskPlayer(newNode);
        assertTrue(target.isFromDiffSquare());
        assertEquals(3,(int)target.getNumMax());
    }


    /**BUILD RANGE
     * Method that build the Range filter.
     *
     */
    @Test
    public void buildRangeComplete() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"min\":3,\"max\":\"5\"}";
        JsonNode newNode = mapper.readTree(newString);
        AimRange target = AimingBuilder.buildRange(newNode);
        assertEquals(3,(int)target.getMinDistance());
        assertEquals(5,(int)target.getMaxDistance());
    }

    @Test
    public void buildRangeSingle() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //min
        String newStringMin = "{\"min\":3}";
        JsonNode newNodeMin = mapper.readTree(newStringMin);
        AimRange targetMin = AimingBuilder.buildRange(newNodeMin);
        assertEquals(3,(int)targetMin.getMinDistance());
        assertEquals(0,(int)targetMin.getMaxDistance());
        //max
        String newStringMax = "{\"max\":5}";
        JsonNode newNodeMax = mapper.readTree(newStringMax);
        AimRange targetMax = AimingBuilder.buildRange(newNodeMax);
        assertEquals(5,(int)targetMax.getMaxDistance());
        assertEquals(0,(int)targetMax.getMinDistance());
    }
}