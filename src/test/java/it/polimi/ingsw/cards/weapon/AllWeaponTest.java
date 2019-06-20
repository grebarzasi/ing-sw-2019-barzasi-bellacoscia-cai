package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Figure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


import it.polimi.ingsw.Player;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static it.polimi.ingsw.cards.AmmoDeckLoader.loadDeck;
import static org.junit.jupiter.api.Assertions.*;
public class AllWeaponTest {

    @Test
    public void prova() {
        /*
        Set<Effect> effects;
        effects = shootingWith.getUsableEff();

        Effect choice = this.controller.getView().showEffects(effects);
        if (choice == null) {
            this.controller.goBack();
            this.controller.choosingMove.executeState();
        }

        do {
            if (shootingWith.getDirectionTemp() != null) {
                dir = shootingWith.getDirectionTemp();
                String rpl = controller.getView().chooseDirection(new ArrayList<>(dir.getTargetTemp()));
                if (rpl == null) {
                    shootingWith.resetWeapon();
                    this.controller.goBack();
                    this.controller.choosingMove.executeState();
                }
                dir.setDirectionTemp(rpl);
            } else if (shootingWith.getAskTemp() != null) {
                ask = shootingWith.getAskTemp();
                ArrayList<Figure> rpl = controller.getView().showTargetAdvanced(ask.getTargetTemp(), ask.getNumMax(), ask.isFromDiffSquare(), ask.getMsg());
                if (rpl == null) {
                    shootingWith.resetWeapon();
                    this.controller.goBack();
                    this.controller.choosingMove.executeState();
                }
                ask.setTargetTemp(new HashSet<>(rpl));
            }
            ok = choice.executeEffect();
        } while (!ok);
        shootingWith.resetWeapon();
    */}
}
