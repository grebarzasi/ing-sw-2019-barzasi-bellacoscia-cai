package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Gregorio Barzasi
 */
public class ShootTarget implements SubEffect {

    private int damageNum;
    private int markNum;

    public ShootTarget(int damage, int mark) {
        this.damageNum=damage;
        this.markNum=mark;
    }

    public int getDamageNum() {
        return damageNum;
    }

    public int getMarkNum() {
        return markNum;
    }

    public Set<Player> applyEffect(Weapon w, Set<Player> p){

        //implement
        return p;
    }

}