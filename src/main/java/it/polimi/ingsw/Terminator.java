package it.polimi.ingsw;

import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;

/**
 * Terminator bot
 */

public class Terminator extends Figure{


    private static final int width = 4;
    private static final int height = 3;

    public static final int stage2 = 5;


    public Terminator(String character) {

        super(character);

    }

    /**
     * The only kind of damage infliction the terminator can perform,
     * shoots a player if visible, also adds a mark if adrenaline status is active
     */

    public void shoot(Player p){

        if(canSee(p)){
            this.inflictDamage(1,p);
            if(this.getPersonalBoard().getDamage().size() > stage2){
                this.inflictMark(1,p);
            }
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
