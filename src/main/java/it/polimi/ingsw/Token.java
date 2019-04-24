package it.polimi.ingsw;

import java.util.*;

public class Token {

    private Player owner;

    public Token(Player owner) {
        this.owner = owner;
    }

    public Token() {
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }



}