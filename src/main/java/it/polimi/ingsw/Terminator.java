package it.polimi.ingsw;

import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.actions.Move;
import it.polimi.ingsw.actions.Shoot;
import it.polimi.ingsw.board.Square;

public class Terminator extends Figure{

    private Player owner;

    private Move move;
    private Shoot shot;

    public Terminator(String character, Player owner) {
        super(character);
        this.owner = owner;
    }

    public void pass(){

    }

    public void doDamage(Player p){

    }
    public void doMove(Square s){

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
