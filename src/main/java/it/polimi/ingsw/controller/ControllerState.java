package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.power_up.PowerupDeckBuilder;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public interface ControllerState {

    void shoot(ArrayList<Player> target);

    void chooseWeapon(Weapon choice);

    void move(Square position);

    void pick(Square position);

    void choose(int choice);

    void discardPU(PowerUp toDiscard);

    void discardWeapon(Weapon toDiscard);

    void usePU(PowerUp toUse);

    void teleport(Square teleportHere);

    void tagback();

    void Scope(PowerUp choice);

    void Newton(PowerUp choice);

    void useNewton(Figure Target, Square moveTo);

}
