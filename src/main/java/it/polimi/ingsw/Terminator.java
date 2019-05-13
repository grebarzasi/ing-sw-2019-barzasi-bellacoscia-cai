package it.polimi.ingsw;

import it.polimi.ingsw.actions.Move;
import it.polimi.ingsw.actions.Shoot;
import it.polimi.ingsw.board.map.Square;

/**
 * Terminator bot
 */

public class Terminator extends Figure{

    //reference to the player controlling the terminator
    private Player owner;

    //the movement it can perform in the turn
    private Move move;
    //the shot it can perform is a target is visible
    private Shoot shot;

    public Terminator(String character, Player owner) {
        super(character);
        this.owner = owner;
    }

    public void pass(){

    }

    /**
     * The only kind of damage infliction the terminator can perform,
     * shoots a player if visible, also adds a mark if adrenaline status is active
     */

    public void shoot(Player p){

        if(canSee(p)){
            this.inflictDamage(1,p);
            if(this.getPersonalBoard().getDamage().size() >= 6){
                this.inflictMark(1,p);
            }
        }

    }


    public void doMove(Square s){

        if(s.isAdjacent(this.getPosition())){
            this.setPosition(s);
        }
        else{
            System.out.println("Terminator can only be moved by one square");
        }

    }


    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Shoot getShoot() {
        return shot;
    }

    public void setShoot(Shoot shoot) {
        this.shot = shoot;
    }
}
