package it.polimi.ingsw.main_board;

import it.polimi.ingsw.main_board.map.Square;

public class Player {

    private String username;
    private String character;
    private Square position;

    public Player(String username, String character, Square position) {
        this.username = username;
        this.character = character;
        this.position = position;
    }

    public Square getPosition(){
        return position;
    }


    /**
     * Verifies whether a player can see another one
     *
     * @param player    the other player
     * @return true if the current player can see the other one; false otherwise
     */
    public boolean canSee(Player player) {
        return getPosition().getRoom() == player.getPosition().getRoom() ||
                getPosition().getNorth() == player.getPosition() ||
                getPosition().getEast() == player.getPosition() ||
                getPosition().getWest() == player.getPosition() ||
                getPosition().getSouth() == player.getPosition();

    }

}


