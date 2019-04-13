package it.polimi.ingsw.cards.weapon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.cards.weapon.aiming.AimAskPlayer;
import it.polimi.ingsw.cards.weapon.aiming.AimDifferent;
import it.polimi.ingsw.cards.weapon.aiming.AimEqual;
import it.polimi.ingsw.cards.weapon.aiming.AimVisible;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * @author Gregorio Barzasi
 */
public class AimingBuilderTest {

    @Test
    public void BuildTargetAcquisition() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"isVisible\":{\"visible\":\"true\"},\"different\":[\"ciao\",\"prova\"],\"direction\":null}";
        JsonNode newNode = mapper.readTree(newString);
        System.out.println(newNode);
        TargetAcquisition target = AimingBuilder.buildTargetAcquisition(newNode);
        assertTrue(true);

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

    @Test(expected = NullPointerException.class)
    public void buildIsVisibleBadJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"ciao\":true}";
        JsonNode newNode = mapper.readTree(newString);
        AimVisible target = AimingBuilder.buildIsVisible(newNode);
        assertNull(target);
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
}