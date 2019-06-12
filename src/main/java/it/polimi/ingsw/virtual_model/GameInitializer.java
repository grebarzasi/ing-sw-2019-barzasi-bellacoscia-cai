package it.polimi.ingsw.virtual_model;


import com.fasterxml.jackson.databind.JsonNode;
import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.Token;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.Deck;
import it.polimi.ingsw.cards.WeaponDeck;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.connection.socket.SClientHandler;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.UpdateBuilder;
import it.polimi.ingsw.javaFX.GameJavaFX;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;




public class GameInitializer extends Application {

    private static final String MAP_NAME="small";
    private static final int SKULLMAX=6;
    private VirtualModel vmodel;

    public VirtualModel getVmodel() {
        return vmodel;
    }

    public void setVmodel(VirtualModel vmodel) {
        this.vmodel = vmodel;
    }

    public static void main(String[] args){
        launch(args);
    }

    public void initAll() throws IOException {
        Lobby lobby = new Lobby();
        SClientHandler a =new SClientHandler(lobby);
        SClientHandler b =new SClientHandler(lobby);
        SClientHandler c =new SClientHandler(lobby);
        SClientHandler d =new SClientHandler(lobby);

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
        contr.getModel().setBoard(new Board(MAP_NAME));
        Map map = contr.getModel().getBoard().getMap();
        Square[][] matrix = map.getSquareMatrix();
        p1.setPosition(matrix[1][2]);
        p2.setPosition(matrix[1][2]);
        p3.setPosition(matrix[0][1]);
        p4.setPosition(matrix[2][3]);
        contr.getModel().setCurrentPlayer(p3);
        contr.getModel().getBoard().getTrack().setSkullMax(SKULLMAX);


        //WEAPONS
        WeaponDeck weaponDeck = contr.getModel().getBoard().getWeaponDeck();
        Weapon w1 = (Weapon)weaponDeck.fetch();
        w1.setLoaded(false);
        Weapon w2 = (Weapon)weaponDeck.fetch();
        w2.setLoaded(false);
        Weapon w3 = (Weapon)weaponDeck.fetch();
        w3.setLoaded(false);

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


        p1.setPowerupList(puList1);
        p2.setPowerupList(puList2);
        p3.setPowerupList(puList3);
        p4.setPowerupList(puList4);

        //BOARD
//        p1.setPersonalBoard(new PlayerBoard(a.getOwner()));
//        p2.setPersonalBoard(new PlayerBoard(b.getOwner()));
//        p3.setPersonalBoard(new PlayerBoard(c.getOwner()));
//        p4.setPersonalBoard(new PlayerBoard(d.getOwner()));

        //damage & marks
        Token t1 = new Token(p1);
        Token t2 = new Token(p2);
        Token t3 = new Token(p3);
        Token t4 = new Token(p4);

        ArrayList<Token> tokenArrayList=new ArrayList<>();
        tokenArrayList.add(t1);
        tokenArrayList.add(t1);
        ArrayList<Token> tokenArrayList2=new ArrayList<>();
        tokenArrayList2.add(t2);
//
        contr.getModel().getBoard().getTrack().addKill(tokenArrayList);
        contr.getModel().getBoard().getTrack().addKill(tokenArrayList2);
        contr.getModel().getBoard().getTrack().addKill(tokenArrayList2);
        contr.getModel().getBoard().getTrack().addKill(tokenArrayList2);
//        contr.getModel().getBoard().getTrack().addKill(tokenArrayList);
//        contr.getModel().getBoard().getTrack().addKill(tokenArrayList);
        contr.getModel().getBoard().getTrack().addKill(tokenArrayList);
        contr.getModel().getBoard().getTrack().addKill(tokenArrayList2);
        contr.getModel().getBoard().getTrack().addKill(tokenArrayList);
        contr.getModel().getBoard().getTrack().addKill(tokenArrayList2);
//        contr.getModel().getBoard().getTrack().addKill(tokenArrayList2);

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
        vmodel = new VirtualModel(new VirtualPlayer("Iron Man","red"));
        UpdateParser parser=new UpdateParser(vmodel);
        System.out.println(node.toString());
        parser.updateModel(node.toString());
        VirtualPlayer vP1 = vmodel.findPlayer(p1.getCharacter());
        VirtualPlayer vP2 = vmodel.findPlayer(p2.getCharacter());
        VirtualPlayer vP3 = vmodel.findPlayer(p3.getCharacter());
        VirtualPlayer vP4 = vmodel.findPlayer(p4.getCharacter());


        HashMap<String, VirtualCell> cells = vmodel.getBoard().getMap().getCells();
       }

        public void start(Stage primaryStage) throws IOException {
                initAll();
                GameJavaFX game = new GameJavaFX(vmodel);
                try {
                    game.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }