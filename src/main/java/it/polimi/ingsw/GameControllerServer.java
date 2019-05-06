package it.polimi.ingsw;

import it.polimi.ingsw.board.map.Board;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Set;

/**
 * The type Game controller server.
 */
public class GameControllerServer implements Controller {

    private ArrayList<Figure> figureList;

    private Figure currentFigure;

    private Time timeTurn;

    public Board currentBoard;

    /**
     * Parameters for the connection.
     */
    private ServerSocket server = null;
    private Socket client = null;
    int door = 1984;
    private BufferedReader input;
    private PrintWriter output;
    String username;
    String character;


    public GameControllerServer() {
    }

    public GameControllerServer(ArrayList<Figure> figureList, Figure currentFigure, Time timeTurn, Board currentBoard) {
        this.figureList = figureList;
        this.currentFigure = currentFigure;
        this.timeTurn = timeTurn;
        this.currentBoard = currentBoard;
    }

    public ArrayList<Figure> getFigureList() {
        return figureList;
    }

    public void setFigureList(ArrayList<Figure> figureList) {
        this.figureList = figureList;
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    public Time getTimeTurn() {
        return timeTurn;
    }

    public void setTimeTurn(Time timeTurn) {
        this.timeTurn = timeTurn;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public ArrayList<Figure> getPlayers() {
        return this.figureList;
    }


    /**
     * Socket connection.
     *
     * @author Carlo Bellacoscia
     */
    public Socket connection(){

        try {

            System.out.println("Inizializzo Server");
            server = new ServerSocket(door);

            System.out.println("Server in ascolto sulla porta " + door);
            client = server.accept();

            System.out.println("Connessione stabilita\n");

            try {

                logInfo();
                Player p = new Player(username, character);

            } catch (Exception e) {
                e.printStackTrace();
            }

            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    public void logInfo() throws Exception{

        /**
         * open buffered reader for reading data from client
         */
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));

        /**
         * read username and character
         */
        username = input.readLine();
        System.out.println("username: " + username);

        character = input.readLine();
        System.out.println("character: " + character + "\n");

        /**
         * open printwriter for writing data to client
         */
        output = new PrintWriter(client.getOutputStream(), true);


        System.out.println("Benvenuto, " + username + "!\n");

        output.flush();
        output.close();

    }


    /**
     *ask player
     * @autor Gregorio Barzasi
     */
    public Square askPosition(){
        return null;
    }
    public Ammo askAmmo(){return null;}
    public Set<Figure> askTarget(int num){
        return null;
    }
    public Weapon askWeapon(){
        return null;
    }
    public void askUseEffect(Set<Effect> eff){}

    public void setActions() {}
    public void getStatus(Figure p) {}
    public void kill(Figure p) {}
    public void finalFrenzy() {}
    public void finalScore(Figure p) {}
    public void update() {}
    public void newTurn() {}
    public void endTurn() {}


    public static void main(String[] args){

        GameControllerServer s = new GameControllerServer();
        s.connection();
    }

}