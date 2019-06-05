package it.polimi.ingsw.controller;


import com.fasterxml.jackson.databind.JsonNode;
import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.PlayerBoard;
import it.polimi.ingsw.Token;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Cell;
import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Deck;
import it.polimi.ingsw.cards.WeaponDeck;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.connection.socket.ClientThreadSocket;
import it.polimi.ingsw.virtual_model.VirtualCell;
import it.polimi.ingsw.virtual_model.VirtualModel;
import it.polimi.ingsw.virtual_model.VirtualPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


class GameStateJsonBuilderTest {

    @Test
    void create() throws IOException {
        Lobby lobby = new Lobby();
        ClientThreadSocket a =new ClientThreadSocket(lobby);
        ClientThreadSocket b =new ClientThreadSocket(lobby);
        ClientThreadSocket c =new ClientThreadSocket(lobby);

        Player p1 = a.getOwner();
        Player p2 = b.getOwner();
        Player p3 = c.getOwner();

        p1.setCharacter("red");
        p1.setUsername("test1");
        p2.setCharacter("blue");
        p2.setUsername("test2");
        p3.setCharacter("yellow");
        p3.setUsername("test3");
        lobby.addPlayer(a);
        lobby.addPlayer(b);
        lobby.addPlayer(c);


        Controller contr= new Controller(lobby);
        contr.getModel().setBoard(new Board("large"));
        Map map = contr.getModel().getBoard().getMap();
        Square[][] matrix = map.getSquareMatrix();
        p1.setPosition(matrix[0][0]);
        p2.setPosition(matrix[0][2]);
        p3.setPosition(matrix[0][3]);

        //WEAPONS
        WeaponDeck weaponDeck = contr.getModel().getBoard().getWeaponDeck();
        Weapon w1 = (Weapon)weaponDeck.fetch();
        w1.setLoaded(true);
        Weapon w2 = (Weapon)weaponDeck.fetch();
        Weapon w3 = (Weapon)weaponDeck.fetch();
        Weapon w4 = (Weapon)weaponDeck.fetch();
        Weapon w5 = (Weapon)weaponDeck.fetch();
        w5.setLoaded(true);

        ArrayList<Weapon> weaponList1= new ArrayList<>();
        ArrayList<Weapon> weaponList2= new ArrayList<>();
        ArrayList<Weapon> weaponList3= new ArrayList<>();

        weaponList1.add(w1);
        weaponList1.add(w3);
        weaponList1.add(w5);

        weaponList2.add(w4);
        weaponList2.add(w2);
        weaponList2.add(w3);

        weaponList3.add(w4);
        weaponList3.add(w5);
        weaponList3.add(w3);

        p1.setWeaponsList(weaponList1);
        p2.setWeaponsList(weaponList2);
        p3.setWeaponsList(weaponList3);

        //POWERUPS
        Deck puDeck = contr.getModel().getBoard().getPowerupDeck();

        ArrayList<PowerUp> puList1 = new ArrayList<>();
        ArrayList<PowerUp> puList2 = new ArrayList<>();
        ArrayList<PowerUp> puList3 = new ArrayList<>();

        puList1.add((PowerUp) puDeck.fetch());
        puList1.add((PowerUp) puDeck.fetch());
        puList2.add((PowerUp) puDeck.fetch());
        puList2.add((PowerUp) puDeck.fetch());
        puList3.add((PowerUp) puDeck.fetch());
        puList3.add((PowerUp) puDeck.fetch());
        puList3.add((PowerUp) puDeck.fetch());

        assertEquals(2,puList1.size());
        assertEquals(2,puList2.size());
        assertEquals(3,puList3.size());

        p1.setPowerupList(puList1);
        p2.setPowerupList(puList2);
        p3.setPowerupList(puList3);

        //BOARD
        p1.setPersonalBoard(new PlayerBoard(a.getOwner()));
        p2.setPersonalBoard(new PlayerBoard(b.getOwner()));
        p3.setPersonalBoard(new PlayerBoard(c.getOwner()));

        //damage & marks
        Token t1 = new Token(p1);
        Token t2 = new Token(p2);
        Token t3 = new Token(p3);


        p1.getPersonalBoard().addDamage(t2);
        p1.getPersonalBoard().addDamage(t2);
        p1.getPersonalBoard().addDamage(t3);
        p1.getPersonalBoard().addMark(t2);

        p2.getPersonalBoard().addDamage(t1);
        p2.getPersonalBoard().addMark(t3);

        p3.getPersonalBoard().addMark(t1);
        p3.getPersonalBoard().addMark(t2);



        JsonNode node = new GameStateJsonBuilder(contr).create();
        System.out.println(node);

        VirtualModel vmodel = new VirtualModel();
        vmodel.updateModel(node.toString());
        HashMap<String, VirtualPlayer> allP = vmodel.getAllPlayers();



        // verify if its all ok
        assertTrue(allP.containsKey(p1.getCharacter()));
        assertTrue(allP.containsKey(p2.getCharacter()));
        assertTrue(allP.containsKey(p3.getCharacter()));

        VirtualPlayer vP1 = allP.get(p1.getCharacter());
        VirtualPlayer vP2 = allP.get(p2.getCharacter());
        VirtualPlayer vP3 = allP.get(p3.getCharacter());

        assertEquals(p1.getUsername(),vP1.getUsername());
        assertEquals(p2.getUsername(),vP2.getUsername());
        assertEquals(p3.getUsername(),vP3.getUsername());

        assertEquals(p1.getCharacter(),vP1.getCharacter());
        assertEquals(p2.getCharacter(),vP2.getCharacter());
        assertEquals(p3.getCharacter(),vP3.getCharacter());

        assertEquals(0,vP1.getPoints());
        assertEquals(0,vP2.getPoints());
        assertEquals(0,vP3.getPoints());

        Cell cell = p1.getPosition().getPosition();
        assertEquals(cell.getRow(),vP1.getRow());
        assertEquals(cell.getColumn(),vP1.getColumn());

        cell = p2.getPosition().getPosition();
        assertEquals(cell.getRow(),vP2.getRow());
        assertEquals(cell.getColumn(),vP2.getColumn());

        cell = p3.getPosition().getPosition();
        assertEquals(cell.getRow(),vP3.getRow());
        assertEquals(cell.getColumn(),vP3.getColumn());

        //weapons

        assertTrue(vP1.getWeapons().containsKey(weaponList1.get(0).getName()));
        assertTrue(vP1.getWeapons().containsKey(weaponList1.get(1).getName()));
        assertTrue(vP1.getWeapons().containsKey(weaponList1.get(2).getName()));

        assertTrue(vP2.getWeapons().containsKey(weaponList2.get(0).getName()));
        assertTrue(vP2.getWeapons().containsKey(weaponList2.get(1).getName()));
        assertTrue(vP2.getWeapons().containsKey(weaponList2.get(2).getName()));

        assertTrue(vP3.getWeapons().containsKey(weaponList3.get(0).getName()));
        assertTrue(vP3.getWeapons().containsKey(weaponList3.get(1).getName()));
        assertTrue(vP3.getWeapons().containsKey(weaponList3.get(2).getName()));

        //verify load State
        assertTrue(vP1.getWeapons().get(weaponList1.get(0).getName()));
        assertFalse(vP1.getWeapons().get(weaponList1.get(1).getName()));
        assertTrue(vP1.getWeapons().get(weaponList1.get(2).getName()));

        assertFalse(vP2.getWeapons().get(weaponList2.get(0).getName()));
        assertFalse(vP2.getWeapons().get(weaponList2.get(1).getName()));
        assertFalse(vP2.getWeapons().get(weaponList2.get(2).getName()));

        assertFalse(vP3.getWeapons().get(weaponList3.get(0).getName()));
        assertTrue(vP3.getWeapons().get(weaponList3.get(1).getName()));
        assertFalse(vP3.getWeapons().get(weaponList3.get(2).getName()));

        //power up
        assertTrue(vP1.getPowerUps().contains(puList1.get(0).getName()+":"+puList1.get(0).getAmmoOnDiscard().toString()));
        assertTrue(vP1.getPowerUps().contains(puList1.get(1).getName()+":"+puList1.get(1).getAmmoOnDiscard().toString()));

        assertTrue(vP2.getPowerUps().contains(puList2.get(0).getName()+":"+puList2.get(0).getAmmoOnDiscard().toString()));
        assertTrue(vP2.getPowerUps().contains(puList2.get(1).getName()+":"+puList2.get(1).getAmmoOnDiscard().toString()));

        assertTrue(vP3.getPowerUps().contains(puList3.get(0).getName()+":"+puList3.get(0).getAmmoOnDiscard().toString()));
        assertTrue(vP3.getPowerUps().contains(puList3.get(1).getName()+":"+puList3.get(1).getAmmoOnDiscard().toString()));
        assertTrue(vP3.getPowerUps().contains(puList3.get(2).getName()+":"+puList3.get(2).getAmmoOnDiscard().toString()));

        //damage and marks
        ArrayList<String> damage = new ArrayList<>();
        ArrayList<String> marks = new ArrayList<>();

        damage.add("blue");
        damage.add("blue");
        damage.add("yellow");
        marks.add("blue");

        assertTrue(vP1.getpBoard().getDamage().containsAll(damage));
        assertTrue(vP1.getpBoard().getMarks().containsAll(marks));

        damage.clear();
        marks.clear();
        damage.add("red");
        marks.add("yellow");
        assertTrue(vP2.getpBoard().getDamage().containsAll(damage));
        assertTrue(vP2.getpBoard().getMarks().containsAll(marks));

        damage.clear();
        marks.clear();
        marks.add("red");
        marks.add("blue");
        assertTrue(vP3.getpBoard().getDamage().isEmpty());
        assertTrue(vP3.getpBoard().getMarks().containsAll(marks));

        //skulls
        assertEquals(6,vP1.getpBoard().getSkulls());
        assertEquals(6,vP2.getpBoard().getSkulls());
        assertEquals(6,vP3.getpBoard().getSkulls());

        //ammo
        assertEquals(p1.getPersonalBoard().getAmmoInventory().getRed(),vP1.getpBoard().getAmmoRed());
        assertEquals(p1.getPersonalBoard().getAmmoInventory().getBlue(),vP1.getpBoard().getAmmoBlue());
        assertEquals(p1.getPersonalBoard().getAmmoInventory().getYellow(),vP1.getpBoard().getAmmoYellow());

        assertEquals(p2.getPersonalBoard().getAmmoInventory().getRed(),vP2.getpBoard().getAmmoRed());
        assertEquals(p2.getPersonalBoard().getAmmoInventory().getBlue(),vP2.getpBoard().getAmmoBlue());
        assertEquals(p2.getPersonalBoard().getAmmoInventory().getYellow(),vP2.getpBoard().getAmmoYellow());

        assertEquals(p3.getPersonalBoard().getAmmoInventory().getRed(),vP3.getpBoard().getAmmoRed());
        assertEquals(p3.getPersonalBoard().getAmmoInventory().getBlue(),vP3.getpBoard().getAmmoBlue());
        assertEquals(p3.getPersonalBoard().getAmmoInventory().getYellow(),vP3.getpBoard().getAmmoYellow());

        //Map
        assertEquals(8,vmodel.getBoard().getSkull());
        assertEquals("large",vmodel.getBoard().getMap().getName());
        HashMap<String, VirtualCell> cells = vmodel.getBoard().getMap().getCells();
        System.out.println(cells);


    }
}