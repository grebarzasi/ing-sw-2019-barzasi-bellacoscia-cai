package it.polimi.ingsw;

import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;

/**
 * Terminator bot
 */

public class Terminator extends Figure{


    private static final int width = 4;
    private static final int height = 3;
    private static final int range = 3;


    public Terminator(String character, Player owner) {
        super(character);
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

    @Override
    public ArrayList<Square> canGo() {


        int row;
        int column;

        ArrayList<Square> destinations = new ArrayList<>();

        for (row = 0; row < height; row++) {
            for (column = 0; column < width; column++) {
                if (this.getPosition().isAdjacent((this.getModel().getBoard().getMap().getSquareMatrix()[row][column]))) {

                    destinations.add(this.getModel().getBoard().getMap().getSquareMatrix()[row][column]);

                }
            }
        }

        return destinations;

    }

}
