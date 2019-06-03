package it.polimi.ingsw;

public class Token {

    private Figure owner;

    public Token(Figure owner) {
        this.owner = owner;
    }

    public Figure getOwner() {
        return owner;
    }

    public void setOwner(Figure owner) {
        this.owner = owner;
    }

    public String toString(){
        return owner.getCharacter();
    }

}