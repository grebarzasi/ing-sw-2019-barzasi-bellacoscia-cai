package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureTest {

    @Test
    void inflictDamage() {

        Player rhaegar = new Player("Rhaegal","Prince");
        Player robert = new Player("Robert","Rebel");

        robert.inflictDamage(1,rhaegar);

        assertEquals(1,rhaegar.getPersonalBoard().getDamage().size());

        rhaegar.inflictDamage(2,robert);
        robert.inflictDamage(20,rhaegar);

        assertEquals(12,rhaegar.getPersonalBoard().getDamage().size());
        assertEquals(2,robert.getPersonalBoard().getDamage().size());

        int i;
        for(i=0;i<12;i++){

            assertEquals(rhaegar.getPersonalBoard().getDamage().get(i).getOwner(),robert);

        }


    }

    @Test
    void inflictMark() {
    }

    @Test
    void multipleDeaths() {

        Player albert = new Player ("Albert","albert");
        Player bill = new Player("Bill","bill");
        Player charlie = new Player("Charlie","charlie");
        Player don = new Player("Don","don");
        Player ed = new Player("Ed", "ed");

        Token a = new Token(albert);
        Token b = new Token(bill);
        Token c = new Token(charlie);
        Token d = new Token(don);
        Token e = new Token(ed);

        albert.getPersonalBoard().addDamage(b);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(d);
        albert.getPersonalBoard().addDamage(d);

        albert.die();

        assertEquals(0,albert.getPersonalBoard().getDamage().size());
        assertEquals(0,albert.getPoints());

        assertEquals(8,charlie.getPoints());
        assertEquals(6,ed.getPoints());
        assertEquals(4,don.getPoints());
        assertEquals(3,bill.getPoints());

        albert.getPersonalBoard().addDamage(b);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(d);
        albert.getPersonalBoard().addDamage(d);

        albert.die();

        assertEquals(0,albert.getPersonalBoard().getDamage().size());
        assertEquals(0,albert.getPoints());

        assertEquals(14,charlie.getPoints());
        assertEquals(10,ed.getPoints());
        assertEquals(6,don.getPoints());
        assertEquals(5,bill.getPoints());

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(d);
        albert.getPersonalBoard().addDamage(e);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);

        albert.die();

        assertEquals(0,albert.getPersonalBoard().getDamage().size());
        assertEquals(0,albert.getPoints());

        assertEquals(19,charlie.getPoints());
        assertEquals(8,don.getPoints());
        assertEquals(11,ed.getPoints());
        assertEquals(5,bill.getPoints());

    }
}