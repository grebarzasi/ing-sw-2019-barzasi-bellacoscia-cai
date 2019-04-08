package it.polimi.ingsw.cards;

import java.util.*;

public abstract class Weapon extends Deck implements Card {

    private Ammo reloadCost;

    private Ammo chamber;

    private List<Effect> basicEffect;

    private List<Effect> additionalOneEffect;

    private List<Effect> additionalTwoEffect;

    private List<Effect> alternativeEffect;

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