package it.polimi.ingsw;

import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public interface View {

    public PowerUp showPowerUp(ArrayList<PowerUp> args);

    public Weapon showWeapon(ArrayList<Weapon> args);

    public String showMoves(String[] args);

    public Square showPossibleMoves(ArrayList<Square> args);

    public Player showTarget(Figure arg);

    public Player showMultipleTargets(ArrayList<Figure> args);


}
