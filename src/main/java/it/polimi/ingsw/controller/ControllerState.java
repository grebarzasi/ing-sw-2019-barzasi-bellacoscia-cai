package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public interface ControllerState {

    void shoot();

    void chooseWeapon();

    void move();

    void pick();

    void choose(int choice);

    void chooseMove(String choice);

    void discardPU(PowerUp toDiscard);

    void discardWeapon(Weapon toDiscard);

    void usePU(PowerUp toUse);

    void teleport(Square teleportHere);

    void tagback();

    void Scope(PowerUp choice);

    void Newton(PowerUp choice);

    void useNewton(Figure Target, Square moveTo);

}

