package it.polimi.ingsw.controller;


import com.fasterxml.jackson.databind.JsonNode;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.Token;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.map.Cell;
import it.polimi.ingsw.model.board.map.Map;
import it.polimi.ingsw.model.board.map.Square;
import it.polimi.ingsw.model.cards.Ammo;
import it.polimi.ingsw.model.cards.Deck;
import it.polimi.ingsw.model.cards.WeaponDeck;
import it.polimi.ingsw.model.cards.power_up.PowerUp;
import it.polimi.ingsw.model.cards.weapon.Weapon;
import it.polimi.ingsw.controller.client_handler.ClientHandler;
import it.polimi.ingsw.controller.client_handler.SocketClientHandler;
import it.polimi.ingsw.view.virtual_model.UpdateParser;
import it.polimi.ingsw.view.virtual_model.VirtualModel;
import it.polimi.ingsw.view.virtual_model.VirtualPlayer;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.connection.ConnMessage.INNER_SEP;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;


class UpdateBuilderTest {

    @Test
    void create() throws IOException {
        Lobby lobby = new Lobby();
        ClientHandler a =new SocketClientHandler(lobby);
        ClientHandler b =new SocketClientHandler(lobby);
        ClientHandler c =new SocketClientHandler(lobby);
        ClientHandler d =new SocketClientHandler(lobby);

        Player p1 = a.getOwner();
        Player p2 = b.getOwner();
        Player p3 = c.getOwner();
        Player p4 = d.getOwner();

        p1.setCharacter("red");
        p1.setUsername("Iron Man");

        p2.setCharacter("blue");
        p2.setUsername("Thanos");

        p3.setCharacter("yellow");
        p3.setUsername("Thor");

        p4.setCharacter("green");
        p4.setUsername("Hulk");

        lobby.addPlayer(a);
        lobby.addPlayer(b);
        lobby.addPlayer(c);
        lobby.addPlayer(d);


        Controller contr= new Controller(lobby);
        contr.getModel().setBoard(new Board("large"));
        Map map = contr.getModel().getBoard().getMap();
        Square[][] matrix = map.getSquareMatrix();
        p1.setPosition(matrix[1][2]);
        p2.setPosition(matrix[1][2]);
        p3.setPosition(matrix[0][3]);
        p4.setPosition(matrix[2][3]);
        contr.getModel().setCurrentPlayer(p3);
        //WEAPONS
        WeaponDeck weaponDeck = contr.getModel().getBoard().getWeaponDeck();
        Weapon w1 = (Weapon)weaponDeck.fetch();
        w1.setLoaded(true);
        Weapon w2 = (Weapon)weaponDeck.fetch();
        w2.setLoaded(true);
        Weapon w3 = (Weapon)weaponDeck.fetch();
        w3.setLoaded(true);

        ArrayList<Weapon> weaponList1= new ArrayList<>();
        ArrayList<Weapon> weaponList2= new ArrayList<>();
        ArrayList<Weapon> weaponList3= new ArrayList<>();
        ArrayList<Weapon> weaponList4= new ArrayList<>();

        weaponList1.add((Weapon) weaponDeck.fetch());
        weaponList1.add(w1);
        weaponList1.add(w3);

        weaponList2.add(w2);
        weaponList2.add((Weapon) weaponDeck.fetch());
        weaponList2.add((Weapon) weaponDeck.fetch());

        weaponList3.add((Weapon) weaponDeck.fetch());
        weaponList3.add((Weapon) weaponDeck.fetch());

        weaponList4.add((Weapon) weaponDeck.fetch());

        p1.setWeaponsList(weaponList1);
        p2.setWeaponsList(weaponList2);
        p3.setWeaponsList(weaponList3);
        p4.setWeaponsList(weaponList4);

        //POWERUPS
        Deck puDeck = contr.getModel().getBoard().getPowerupDeck();

        ArrayList<PowerUp> puList1 = new ArrayList<>();
        ArrayList<PowerUp> puList2 = new ArrayList<>();
        ArrayList<PowerUp> puList3 = new ArrayList<>();
        ArrayList<PowerUp> puList4 = new ArrayList<>();

        puList1.add((PowerUp) puDeck.fetch());
        puList1.add((PowerUp) puDeck.fetch());
        puList2.add((PowerUp) puDeck.fetch());
        puList2.add((PowerUp) puDeck.fetch());
        puList3.add((PowerUp) puDeck.fetch());
        puList3.add((PowerUp) puDeck.fetch());
        puList3.add((PowerUp) puDeck.fetch());

      //  puList4.add((PowerUp) puDeck.fetch());

        assertEquals(2,puList1.size());
        assertEquals(2,puList2.size());
        assertEquals(3,puList3.size());

        p1.setPowerupList(puList1);
        p2.setPowerupList(puList2);
        p3.setPowerupList(puList3);
        p4.setPowerupList(puList4);

        //BOARD
        p1.setPersonalBoard(new PlayerBoard(a.getOwner()));
        p2.setPersonalBoard(new PlayerBoard(b.getOwner()));
        p3.setPersonalBoard(new PlayerBoard(c.getOwner()));
        p4.setPersonalBoard(new PlayerBoard(d.getOwner()));

        //damage & marks
        Token t1 = new Token(p1);
        Token t2 = new Token(p2);
        Token t3 = new Token(p3);
        Token t4 = new Token(p4);


        p1.getPersonalBoard().addDamage(t2);
        p1.getPersonalBoard().addDamage(t2);
        p1.getPersonalBoard().addDamage(t3);
        p1.getPersonalBoard().addDamage(t2);
        p1.getPersonalBoard().addDamage(t2);
        p1.getPersonalBoard().addDamage(t3);
        p1.getPersonalBoard().addDamage(t2);
        p1.getPersonalBoard().addDamage(t2);
        p1.getPersonalBoard().addDamage(t3);
        p1.getPersonalBoard().addDamage(t2);
        p1.getPersonalBoard().addDamage(t2);
        p1.getPersonalBoard().addDamage(t3);
        p1.getPersonalBoard().addMark(t2);

        p2.getPersonalBoard().addDamage(t1);
        p2.getPersonalBoard().addMark(t3);

        p3.getPersonalBoard().addMark(t1);
        p3.getPersonalBoard().addMark(t2);

        //ammo
        p1.getPersonalBoard().setAmmoInventory(new Ammo(0,2,3));
        p2.getPersonalBoard().setAmmoInventory(new Ammo(1,0,0));
        p3.getPersonalBoard().setAmmoInventory(new Ammo(1,2,3));
        p4.getPersonalBoard().setAmmoInventory(new Ammo(3,3,3));

        //
        int[]test={6,4,2,1,1};
        p3.getPersonalBoard().setPointVec(test);
        int[]test2={4,2,1,1};
        p4.getPersonalBoard().setPointVec(test2);



        JsonNode node = new UpdateBuilder(contr).create();
        VirtualModel vmodel = new VirtualModel(new VirtualPlayer("Iron Man","red"));
        UpdateParser parser=new UpdateParser(vmodel);
        parser.updateModel(node.toString());
        VirtualPlayer vP1 = vmodel.findPlayer(p1.getCharacter());
        VirtualPlayer vP2 = vmodel.findPlayer(p2.getCharacter());
        VirtualPlayer vP3 = vmodel.findPlayer(p3.getCharacter());
        VirtualPlayer vP4 = vmodel.findPlayer(p4.getCharacter());




        // verify if its all ok
        assertNotNull(vmodel.findPlayer(p1.getCharacter()));
        assertNotNull(vmodel.findPlayer(p2.getCharacter()));
        assertNotNull(vmodel.findPlayer(p3.getCharacter()));



        assertEquals(p1.getUsername(),vP1.getUsername());
        assertEquals(p2.getUsername(),vP2.getUsername());
        assertEquals(p3.getUsername(),vP3.getUsername());
//        assertEquals(vmodel.getTurn(),vP2);

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

        assertEquals(vP1.getWeapons().get(0),weaponList1.get(0).getName()+INNER_SEP+weaponList1.get(0).isLoaded());
        assertEquals(vP1.getWeapons().get(1),weaponList1.get(1).getName()+INNER_SEP+weaponList1.get(1).isLoaded());
        assertEquals(vP1.getWeapons().get(2),weaponList1.get(2).getName()+INNER_SEP+weaponList1.get(2).isLoaded());

        assertEquals(vP2.getWeapons().get(0),weaponList2.get(0).getName()+INNER_SEP+weaponList2.get(0).isLoaded());
        assertEquals(vP2.getWeapons().get(1),weaponList2.get(1).getName()+INNER_SEP+weaponList2.get(1).isLoaded());
        assertEquals(vP2.getWeapons().get(2),weaponList2.get(2).getName()+INNER_SEP+weaponList2.get(2).isLoaded());


        assertEquals(vP3.getWeapons().get(0),weaponList3.get(0).getName()+INNER_SEP+weaponList3.get(0).isLoaded());
        assertEquals(vP3.getWeapons().get(1),weaponList3.get(1).getName()+INNER_SEP+weaponList3.get(1).isLoaded());


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
        assertEquals(0,vP1.getpBoard().getSkulls());
        assertEquals(0,vP2.getpBoard().getSkulls());
        assertEquals(0,vP3.getpBoard().getSkulls());

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
    }
}