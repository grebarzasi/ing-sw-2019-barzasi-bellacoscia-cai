package it.polimi.ingsw.cards.weapon;

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

    public void applyEffect(){

    }

}