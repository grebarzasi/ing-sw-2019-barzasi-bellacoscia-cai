package it.polimi.ingsw.cards;

import java.util.*;

public abstract class Action {

    private int maxMove;

    private boolean frenzy;

    public Action() {
    }

    public static abstract void doAction();

}