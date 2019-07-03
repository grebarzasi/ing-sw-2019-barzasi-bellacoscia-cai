package it.polimi.ingsw;

import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;

/**
 * Terminator bot
 */

public class Terminator extends Figure{

    /**
     * The amount of damage a bot has to receive before changing adrenaline stage
     */
    public static final int BOT_ADRENALINE_THRESHOLD = 5;

    /**
     * the amount of damage inflicted by the bot
     */
    private  int damage = 1;

    public Terminator(String character,GameModel model) {

        super(character,model);

    }

    public Terminator(String character,GameModel model,boolean testMode) {

        super(character,model);
        if(testMode)
            damage=12;


    }

    /**
     * The only kind of damage infliction the terminator can perform,
     * shoots a player if visible, also adds a mark if adrenaline status is active
     */

    public void shoot(Player p){

        this.inflictDamage(damage,p);
        if(this.getPersonalBoard().getDamage().size() > BOT_ADRENALINE_THRESHOLD){

            this.inflictMark(1,p);

        }
    }


    /**
     * Since the bot can only make one move the breadth first search can be simplified to a single condition check
     * @param range the range within the player can move
     * @return the list of adjacent squares
     */
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
