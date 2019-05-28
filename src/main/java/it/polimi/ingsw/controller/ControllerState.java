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

    void choose();

    void chooseMove();

    void discardPU();

    void discardWeapon();

    void usePU();

    void teleport(Square teleportHere);

    void tagback();

    void Scope(PowerUp choice);

    void Newton(PowerUp choice);

    void useNewton(Figure Target, Square moveTo);

}

