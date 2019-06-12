package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.cards.weapon.weapon_builder.WeaponBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gregorio Barzasi
 */
class WeaponBuilderTest {

    @Test
    void buildWeaponCompleteTest(){
      Weapon wp = WeaponBuilder.buildWeapon("completeTest2");
      assertEquals("Test",wp.getName());
      assertNotNull(wp.getBasicEffect());
      assertNotNull(wp.getAddOneEffect());
      assertNotNull(wp.getAddTwoEffect());
      assertNotNull(wp.getAlternativeEffect());
      assertNotNull(wp.getExtraMove());

      assertNotEquals(wp.getBasicEffect().getEffectList(),wp.getAddOneEffect().getEffectList());
      assertNotEquals(wp.getBasicEffect().getEffectList(),wp.getAddTwoEffect().getEffectList());
      assertNotEquals(wp.getBasicEffect().getEffectList(),wp.getAlternativeEffect().getEffectList());

      assertNotEquals(wp.getAddOneEffect().getEffectList(),wp.getAddTwoEffect().getEffectList());
      assertNotEquals(wp.getAddOneEffect().getEffectList(),wp.getAlternativeEffect().getEffectList());

      assertNotEquals(wp.getAddTwoEffect().getEffectList(),wp.getAlternativeEffect().getEffectList());

      assertTrue(wp.isLoaded());
    }
}