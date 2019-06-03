package it.polimi.ingsw.controller;


import com.fasterxml.jackson.databind.JsonNode;
import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.PlayerBoard;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Deck;
import it.polimi.ingsw.cards.WeaponDeck;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.connection.socket.ClientThreadSocket;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


class GameStateJsonBuilderTest {

    @Test
    void create() throws IOException {
        Lobby lobby = new Lobby();
        ClientThreadSocket a =new ClientThreadSocket(lobby);
        ClientThreadSocket b =new ClientThreadSocket(lobby);
        ClientThreadSocket c =new ClientThreadSocket(lobby);

        a.getOwner().setCharacter("red");
        a.getOwner().setUsername("test1");
        b.getOwner().setCharacter("blue");
        b.getOwner().setUsername("test2");
        c.getOwner().setCharacter("yellow");
        c.getOwner().setUsername("test2");
        lobby.addPlayer(a);
        lobby.addPlayer(b);
        lobby.addPlayer(c);


        Controller contr= new Controller(lobby);
        contr.getModel().setBoard(new Board("large"));
        Map map = contr.getModel().getBoard().getMap();
        Square[][] matrix = map.getSquareMatrix();
        a.getOwner().setPosition(matrix[0][0]);
        b.getOwner().setPosition(matrix[0][2]);
        c.getOwner().setPosition(matrix[0][3]);

        WeaponDeck weaponDeck = contr.getModel().getBoard().getWeaponDeck();
        Weapon w1 = (Weapon)weaponDeck.fetch();
        Weapon w2 = (Weapon)weaponDeck.fetch();
        Weapon w3 = (Weapon)weaponDeck.fetch();
        Weapon w4 = (Weapon)weaponDeck.fetch();
        Weapon w5 = (Weapon)weaponDeck.fetch();

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

        a.getOwner().setWeaponsList(weaponList1);
        b.getOwner().setWeaponsList(weaponList2);
        c.getOwner().setWeaponsList(weaponList3);

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

        a.getOwner().setPowerupList(puList1);
        b.getOwner().setPowerupList(puList2);
        c.getOwner().setPowerupList(puList3);

        a.getOwner().setPersonalBoard(new PlayerBoard(a.getOwner()));
        b.getOwner().setPersonalBoard(new PlayerBoard(b.getOwner()));
        c.getOwner().setPersonalBoard(new PlayerBoard(c.getOwner()));

        Board board=contr.getModel().getBoard();
        board.getTrack().getKillsTrack();
        JsonNode node = new GameStateJsonBuilder(contr).create();
        System.out.println(node);
    }
}