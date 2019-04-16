package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.cards.weapon.weapon_builder.WeaponBuilder;
import org.junit.jupiter.api.Test;

class EffectTest {

    @Test
    void executeEffect() {
        Weapon wp = WeaponBuilder.buildWeapon("completeTest2");
        wp.getBasicEffect().executeEffect();
    }
}