package it.polimi.ingsw.CLI;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.virtual_model.VirtualLobby;
import it.polimi.ingsw.virtual_model.VirtualModel;
import it.polimi.ingsw.virtual_model.VirtualPlayer;
import static it.polimi.ingsw.CLI.CliMessages.*;
import static it.polimi.ingsw.connection.ConnMessage.COUNTDOWN;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Gregorio Barzasi
 */
public class CliLobby extends Thread{
    private VirtualLobby lobby;
    private ConnectionTech c;
    private BufferedReader sc;
    private VirtualPlayer p;

    public CliLobby(ConnectionTech c, BufferedReader sc, VirtualPlayer p){
        this.c=c;
        this.sc=sc;
        this.p=p;
    }

    public void startLobby()throws IOException{
        do {
            gameSetup();
            clearScreen();
        }while(ynAsk(PREFERENCE_Q,PREFERENCE_Y,PREFERENCE_N));

        lobby.sendPref();
        clearScreen();
        waitingRoom();
    }

    public int askMap()throws IOException{
        String temp;
        int num=0;
        do {System.out.println(MAP_OPT);
            System.out.println(MAP_Q);
            temp = sc.readLine();
            if(temp.isEmpty())
                temp="1";
            try{
                num = Integer.parseInt(temp);
            }
            catch (NumberFormatException e)
            {num=0;
            }
            if(num<1 || num>4){
                System.err.println(MAP_N);
            }
        }while(num<1 || num>4);
        lobby.setKillPref(num);
        System.out.println(MAP_Y+num);
        return num;
    }

    /**
     * method for general question with y/n answer
     */
    public boolean ynAsk(String question, String yes, String no)throws IOException{
        String temp;
        do {
            System.out.println(question);
            temp = sc.readLine();
            if (temp.equals("Y")||temp.equals("y")||temp.equals("1")) {
                System.out.println(yes);
                return true;
            } else if (temp.equals("N")||temp.equals("n")||temp.equals("2")||temp.isEmpty()) {
                System.out.println(no);
                return false;
            }else{
                System.err.println(GENERIC_N);
            }
        }while(true);

    }

    /**
     * method used for general game settings
     */

    public void gameSetup() throws IOException {
        lobby= new VirtualLobby(c,p);
        System.out.println(LOBBY_HEAD);

        String temp;
        //TERMINATOR
        lobby.setTerminatorPref(ynAsk(TERMINATOR_Q,TERMINATOR_Y,TERMINATOR_N));

        //FINAL FRENZY
        lobby.setFinalFrenzyPref(ynAsk(FRENZY_Q,FRENZY_Y,FRENZY_N));

        //MAX KILL
        int num=0;
        do {
            System.out.println(SKULL_Q);
            temp = sc.readLine();
            if(temp.isEmpty())
                temp="8";
            try{
            num = Integer.parseInt(temp);
            }
            catch (NumberFormatException e)
            {num=0;
            }
            if(num<5 || num>8){
                System.err.println(SKULL_N);
            }
        }while(num<5 || num>8);
        lobby.setKillPref(num);
        System.out.println(SKULL_Y+num);

        //MAP
        lobby.setMapPref(askMap());
        System.out.println(LINE_SEP);

    }

    public synchronized void waitingRoom()throws IOException {
        clearScreen();
        System.out.println(WAITINGROOM_HEAD);

        while (!lobby.isGameStarted()){
            if(lobby.hasGameTimerStarted()) {
                new CliCountDown(COUNTDOWN).start();
                lobby.setGameTimerStarted(false);
            }
            lobby.waitUpdate();
            for(VirtualPlayer p : lobby.getNewPlayersList() ){
                if (!p.isPrinted()) {
                    System.out.println(p.getUsername() + " using " + p.getCharacter() + ": is READY!");
                    p.setPrinted(true);
                }
            }

        }
        gameStart();

    }

    public void gameStart(){
        new CliGame(c,p).gameStart();
    }


}
