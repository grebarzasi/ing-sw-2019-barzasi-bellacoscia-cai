package it.polimi.ingsw.CLI;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.socket.SClient;
import it.polimi.ingsw.connection.rmi.RmiClient;

import static it.polimi.ingsw.CLI.CLiBoardStuff.ALL_CHARACTERS;
import static it.polimi.ingsw.CLI.CliMessages.*;
import static java.lang.Thread.sleep;

import it.polimi.ingsw.virtual_model.ViewClient;
import it.polimi.ingsw.virtual_model.VirtualLogin;
import it.polimi.ingsw.virtual_model.VirtualPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * class that initializes the connection with server and let you proceed with login
 *
 * @author Gregorio Barzasi
 */
public class CliView {

    private ConnectionTech c;
    private BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    private VirtualPlayer p;
    private CliLobby lobby;
    private CliGame view;
    private VirtualLogin l;

    private String ip;
    private int port;


    public static void main(String args[]) {
        CliView cliView = new CliView();
        cliView.start();
    }


    public void start(){
        System.out.println(HEAD_MSG);
        try {
            chooseConnection();
            login();
            lobby = new CliLobby(c,sc,p);
            if(l.isReconnected())
                lobby.gameStart();
            else
                lobby.startLobby();
        }catch(IOException e){
            System.err.println(CONNECTION_ERR);
        }
    }


    /**
     * Choose connection type
     */


    public void chooseConnection() throws IOException {
        do {
            System.out.println("\n" + SELECT_CONN);
            String temp = "";
            boolean flag = true;
            do {
                temp = sc.readLine();
                temp = temp.toLowerCase();
                if (temp.equals("r") || temp.equals("rmi") || temp.equals("1")) {
                    c = new RmiClient();
                    c.setRmi(true);
                    System.out.println(RMI);
                    flag = false;
                } else if (temp.equals("s") || temp.equals("socket") || temp.equals("2") || temp.isEmpty()) {
                    c = new SClient();
                    c.setRmi(false);
                    System.out.println(SOCKET);
                    flag = false;
                } else
                    System.out.println(SELECT_CONN_ERR);
            } while (flag);

            ip = acquireIp();
            if (!ip.isEmpty())
                c.setIp(ip);
            else
                System.out.println(DEFAULT + " " + c.getIp() + "\n");


            port = acquirePort();
            if (port != 0) {
                c.setPort(port);
            } else {
                System.out.println(DEFAULT + " " + c.getPort() + "\n");
            }

            System.out.println(LINE_SEP);
            c.run();
            System.out.println(LINE_SEP);
        }while(!c.connected());
    }

    /**
     * parse login info
     */

    public void login()throws IOException{
        System.out.println("\n"+PRE_LOGIN);
        while(true){
            String username = acquireUsername();
            if(username.isEmpty()) {
                System.out.println("\n" + LOGIN_ERR);
                continue;
            }
            String character = acquireCharacter();
            l = new VirtualLogin(username, character, c);
            System.out.println(WAITING);
            if(l.send()){
                System.out.println(LOGIN_SUCC);
                p = new VirtualPlayer(username,character);
                break;
            }
            System.out.println("\n"+LOGIN_ERR);
        }
    }


    public void setConnection(ConnectionTech c){
        this.c=c;
    }
    public ConnectionTech getConnection(){
       return this.c;
    }
    /**
     * acquire port for connection
     */
    public int acquirePort()throws IOException {
        int port;
        System.out.println("\n"+PORT_SELECT);

        do {
            String s = sc.readLine();
            if(s.isEmpty()){
                return 0;
            }
            try {
                port = Integer.parseInt(s);
            }catch(NumberFormatException e){
                port=0;
            }
            if (port <= 1023 || port > 49151) {
                System.out.println(PORT_ERR);
            }
        } while (port <= 1023 || port > 49151);
        return port;
    }
    /**
     * acquire ip for connection checking validity of the input
     */
    public String acquireIp()throws IOException{
        System.out.println("\n"+IP_SELECT);
        boolean flag =true;
        String ip;
        InetAddress test;
        do{
            ip=sc.readLine();
            try {
            if(ip.isEmpty())
                test=Inet4Address.getLocalHost();
            else
                test = InetAddress.getByName(ip);

            }catch(java.net.UnknownHostException e){
                System.out.println("\n"+IP_SELECT_ERR);
                continue;
            }
                flag=false;
//
//            if(test.isReachable(2000))
//                flag=false;
//            else
//              System.out.println("\n"+IP_SELECT_ERR);
        }while (flag);
        return ip;
    }

    /**
     * parse username
     */
    private String acquireUsername()throws IOException{
            System.out.println(USERNAME);
            return sc.readLine();
        }


    /**
     * parse character
     */
    private String acquireCharacter()throws IOException{
            String character;

            do {
                System.out.println(CHARACTER);
                character = sc.readLine();
                if (!ALL_CHARACTERS.contains(character)) {
                    System.out.println(CHARACTER_ERR);
                }
            } while (!ALL_CHARACTERS.contains(character));
    return character;
    }
}
