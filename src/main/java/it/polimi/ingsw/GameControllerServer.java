package it.polimi.ingsw;

import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.annotation.Target;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * The type Game controller server.
 */
public class GameControllerServer implements Controller {

    private final static int maxPlayers = 5;
    private final static int width = 4;
    private final static int height = 3;

    private ArrayList<Player> playerList;

    private Player currentPlayer;

    private Terminator bot;

    private Time timeTurn;

    public Board currentBoard;

    /**
     * Parameters for the connection.
     */
    private ServerSocket server = null;
    private Socket client = null;

    int port;
    private BufferedReader input;
    private PrintWriter output;
    String username;
    String character;


    public GameControllerServer() {
    }

    public GameControllerServer(ArrayList<Player> playerList, Player currentPlayer, Time timeTurn, Board currentBoard) {
        this.playerList = playerList;
        this.currentPlayer = currentPlayer;
        this.timeTurn = timeTurn;
        this.currentBoard = currentBoard;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public Figure getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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

    public ArrayList<Player> getPlayers() {
        return this.playerList;
    }


    /**
     * Socket connection.
     *
     * @author Carlo Bellacoscia
     */
    public Socket connection(){

        port = getPort();

        try {

            System.out.println("Inizializzo Server");
            server = new ServerSocket(port);

            System.out.println("Server in ascolto sulla porta " + port);
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

    public void logInfo(){

        /**
         * open buffered reader for reading data from client
         */
        try {
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

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\nConnessione non riuscita");
        }
    }


    /**
     *ask player
     * @author Gregorio Barzasi
     */
    public Square askPosition(){

        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int column = sc.nextInt();

        Square s = currentBoard.getMap().getSquareMatrix()[row][column];

        return s;
    }
    public Ammo askAmmo(){
        Figure p = getCurrentPlayer();
        Scanner sc = new Scanner(System.in);
        int red = sc.nextInt();
        int blue = sc.nextInt();
        int yellow = sc.nextInt();

        Ammo a = new Ammo(red, blue, yellow);
        p.getPersonalBoard().removeAmmo(a);

        return a;
    }
    public Figure askOneTarget(){

        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        for (Player p :playerList) {
            if(p.getUsername().equals(name)){
                return p;
            }
        }
        return null;
    }
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

    public void newTurn() {
        int i;


        for(i = 0; i<this.playerList.size() ; i++){
            if(currentPlayer == this.playerList.get(i)){
                if(i == this.playerList.size()-1) {
                    currentPlayer = this.playerList.get(i + 1);
                }else{
                    currentPlayer = this.playerList.get(0);
                }
            }
        }

    }

    public void endTurn() {

        for(Figure figure : this.playerList){

            if(figure.getPersonalBoard().getDamage().size() >= 11){
                figure.setPosition(null);
            }

        }

        this.getCurrentBoard().refillSquares();

    }


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        GameControllerServer s = new GameControllerServer();

        System.out.println("Inserisci la porta");
        s.setPort(sc.nextInt());
        s.connection();
    }

}