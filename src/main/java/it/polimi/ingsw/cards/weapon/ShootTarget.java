package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Figure;

import java.util.Set;

/**
 * Inflict damage and marks
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


    public Set<Figure> applyEffect(Weapon w, Set<Figure> p){
        if(p==null)
            return null;
        if(damageNum != 0) {
            for (Figure target : p) {
                w.getOwner().inflictDamage(damageNum, target);
                w.setLastHit(target);
            }
        }
        if(markNum != 0) {
            for (Figure target : p) {
                w.getOwner().inflictMark(damageNum, target);
                w.setLastHit(target);
            }
        }
        return p;
    }

}