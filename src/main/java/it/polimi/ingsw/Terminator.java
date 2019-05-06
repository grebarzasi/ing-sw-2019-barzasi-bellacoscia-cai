package it.polimi.ingsw;

import it.polimi.ingsw.actions.Move;
import it.polimi.ingsw.actions.Shoot;
import it.polimi.ingsw.board.map.Square;

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

    public void shoot(Player p){

        if(canSee(p)){
            p.getPersonalBoard().addDamage(new Token(this));
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
