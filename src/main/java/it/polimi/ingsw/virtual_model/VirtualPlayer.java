package it.polimi.ingsw.virtual_model;

/**
 * @author Gregorio Barzasi
 */
public class VirtualPlayer {
    private String username;
    private String charachter;
    private boolean ready;

    public VirtualPlayer(String username,String charachter){
        this.charachter=charachter;
        this.username=username;
    }
}
