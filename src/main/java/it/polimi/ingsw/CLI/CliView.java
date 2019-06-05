package it.polimi.ingsw.CLI;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.socket.SClient;
import it.polimi.ingsw.connection.rmi.RmiClient;
import static it.polimi.ingsw.CLI.CliMessages.*;
import it.polimi.ingsw.virtual_model.VirtualLogin;
import it.polimi.ingsw.virtual_model.VirtualPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CliView {

    private ConnectionTech c;
    private BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    private VirtualPlayer p;
    private CliLobby lobby;

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
            lobby.startLobby();
        }catch(Exception e){
            System.err.println(CONNECTION_ERR);
            e.printStackTrace();
        }
    }


    /**
     * command connection type
     */


    public void chooseConnection() throws IOException {
        System.out.println("\n"+SELECT_CONN);
        String temp="";
        temp = sc.readLine();
        if(temp.equals("R")||temp.equals("RMI")||temp.equals("1")||temp.equals("r")){
            c= new RmiClient();
            c.setRmi(true);
            System.out.println("\n"+RMI);
        }else if(temp.equals("S")||temp.equals("Socket")||temp.equals("2")||temp.equals("s")||temp.isEmpty()){
            c= new SClient();
            c.setRmi(false);
            System.out.println("\n"+SOCKET);

        }
        port=acquirePort();
        ip=acquireIp();
        if(port!=0)
            c.setPort(port);
        else
            System.out.println(DEFAULT+" "+ c.getPort());

        if(!ip.isEmpty())
            c.setIp(ip);
        else
            System.out.println(DEFAULT+" "+c.getIp());

        c.initConnection();
    }

    /**
     * parse login info
     */

    public void login()throws IOException{
        System.out.println("\n"+PRE_LOGIN);
        VirtualLogin l;
        while(true){
            String username = acquireUsername();
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

    public int acquirePort()throws IOException {
        int port;
        System.out.println(PORT_SELECT);

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

    public String acquireIp()throws IOException{
        System.out.println(IP_SELECT);
        return sc.readLine();
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
                if (!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray")) {
                    System.out.println(CHARACTER_ERR);
                }
            } while (!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray"));
    return character;
    }
}
