package it.polimi.ingsw.model.cards.weapon;

import it.polimi.ingsw.model.Figure;

import java.util.Set;

/**
 * Inflict damage and marks
 * @author Gregorio Barzasi
 */
public class ShootTarget implements SubEffect {
    /**
     * the damage num that has to be inflicted to targets
     */
    private int damageNum;
    /**
     * the mark num that has to be inflicted to targets
     */
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

    /**
     * inflicts damage and/or marks to the target set
     * @param p the set of target that have to be hit
     */
    public Set<Figure> applyEffect(Weapon w, Set<Figure> p){
        if(p==null)
            return null;
        if(damageNum != 0) {
            for (Figure target : p) {
                w.getOwner().inflictDamage(damageNum, target);
                w.setLastHit(target);
                w.getDamaged().add(target);
            }
        }
        if(markNum != 0) {
            for (Figure target : p) {
                w.getOwner().inflictMark(markNum, target);
                w.setLastHit(target);
            }
        }
        return p;
    }

    public void resetSubEffect() {
    }
}