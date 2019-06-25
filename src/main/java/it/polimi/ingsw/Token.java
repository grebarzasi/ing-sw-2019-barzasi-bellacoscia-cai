package it.polimi.ingsw;

/**
 * A player's token, can become a mark, damage, a kill or an overkill on the killshot track
 */

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

    @Override
    public boolean equals(Object o){
        if(o==this)
            return true;
        if (!(o instanceof Token)) {
            return false;
        }
        Token c=(Token)o;
        if(c.getOwner().getCharacter().equals(owner.getCharacter()))
            return true;
        return false;
    }


}