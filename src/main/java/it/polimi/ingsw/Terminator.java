package it.polimi.ingsw;

import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;

/**
 * Terminator bot
 */

public class Terminator extends Figure{

    //damage required for adrenaline stage 2
    public static final int BOT_ADRENALINE_THRESHOLD = 5;
    public static final int BOT_DAMAGE = 11;

    public Terminator(String character,GameModel model) {

        super(character,model);

    }

    /**
     * The only kind of damage infliction the terminator can perform,
     * shoots a player if visible, also adds a mark if adrenaline status is active
     */

    public void shoot(Player p){

        this.inflictDamage(BOT_DAMAGE,p);
        if(this.getPersonalBoard().getDamage().size() > BOT_ADRENALINE_THRESHOLD){

            this.inflictMark(1,p);

        }
    }


    @Override
    public ArrayList<Square> canGo(int range) {


        int row;
        int column;

        ArrayList<Square> destinations = new ArrayList<>();

        for (row = 0; row < Map.HEIGHT; row++) {
            for (column = 0; column < Map.WIDTH; column++) {
                if (this.getPosition().isAdjacent((this.getModel().getBoard().getMap().getSquareMatrix()[row][column]))) {

                    destinations.add(this.getModel().getBoard().getMap().getSquareMatrix()[row][column]);

                }
            }
        }

        return destinations;

    }

}
