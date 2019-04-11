package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.Card;

import java.util.*;

public abstract class Weapon implements Card {

    private String name;
    private Ammo chamber;

    private boolean loaded;

    //List of subEffect and corresponding Target selected in the execution of the effect
    //Effects works like a chain of "sub effects".
    private Effect basicEffect;
    private Effect addOneEffect;
    private Effect addTwoEffect;
    private Effect alternativeEffect;

    //additional move that can be done at any point if necessary
    private Effect extraMove;

    //saves the last Target hit
    private Player lastHit;


    public Weapon() {
    }

    public abstract void basic();

    public abstract void alternative();

    public abstract void additionalOne();

    public abstract void additionalTwo();

    public void buildWeapon() {
    }

    public void fetch() {

    }
}