package it.polimi.ingsw.cards.weapon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gregorio Barzasi
 */
class WeaponBuilderTest {

    @Test
    void buildWeaponCompleteTest() throws IOException {
      Weapon wp = WeaponBuilder.buildWeapon("completeTest");
      assertEquals("Test",wp.getName());
      assertTrue(wp.getBasicEffect() instanceof Effect);
      assertTrue(wp.getAddOneEffect() instanceof Effect);
      assertTrue(wp.getAddTwoEffect() instanceof Effect);
      assertTrue(wp.getAlternativeEffect() instanceof Effect);

    }
}