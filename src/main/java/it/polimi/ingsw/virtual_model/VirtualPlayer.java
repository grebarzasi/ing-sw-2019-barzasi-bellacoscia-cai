package it.polimi.ingsw.virtual_model;

/**
 * This class represent a virtual version of server-side player
 * @author Gregorio Barzasi
 */
public class VirtualPlayer {
    private String username;
    private String character;
    private boolean printed;

    public VirtualPlayer(String username,String character){
        this.character=character;
        this.username=username;
        this.printed=false;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String charachter) {
        this.character = charachter;
    }

    public boolean isPrinted() {
        return printed;
    }

    public void setPrinted(boolean printed) {
        this.printed = printed;
    }
}
