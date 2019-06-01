package it.polimi.ingsw.CLI;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.virtual_model.VirtualLobby;
import it.polimi.ingsw.virtual_model.VirtualPlayer;

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
        }while(ynAsk("\nYou want to edit something? (Y/N)","\nOk,check again:","\nSending preferences"));

        lobby.sendPref();
        clearScreen();
        waitingRoom();
    }

    public int askMap()throws IOException{
        String temp;
        int num=0;
        do {System.out.println("\n1-...\n2-...\n3-...\n4-...");
            System.out.println("Select your map:");
            temp = sc.readLine();
            try{
                num = Integer.parseInt(temp);
            }
            catch (NumberFormatException e)
            {num=0;
            }
            if(num<1 || num>4){
                System.err.println("\nNot available,  again:");
            }
        }while(num<1 || num>4);
        lobby.setKillPref(num);
        System.out.println("\nPerfect! you selected map" + num +" !");
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
            if (temp.equals("Y")||temp.equals("y")) {
                System.out.println(yes);
                return true;
            } else if (temp.equals("N")||temp.equals("n")) {
                System.out.println(no);
                return false;
            }else{
                System.err.println("\nNot valid, again:");
            }
        }while(true);

    }

    /**
     * method used for general game settings
     */

    public void gameSetup() throws IOException {
        lobby= new VirtualLobby(c,p);
        System.out.println(
                "\n\n----------------------------------------\n" +
                "*** LOBBY! Setup your game preferences! ***"+
                "\n----------------------------------------");

        String temp;
        //TERMINATOR
        lobby.setTerminatorPref(ynAsk("\nTerminator bot? (Y/N)","\nTerminator hired, you're brave!","\nTerminator rejected"));

        //FINAL FRENZY
        lobby.setFinalFrenzyPref(ynAsk("\nFinal Frenzy? (Y/N)","\nFinal frenzy set!","\nNo final frenzy, ok!"));

        //MAX KILL
        int num=0;
        do {
            System.out.println("\nSkull on Kill Track? (3-8)");
            temp = sc.readLine();
            try{
            num = Integer.parseInt(temp);
            }
            catch (NumberFormatException e)
            {num=0;
            }
            if(num<3 || num>8){
                System.err.println("\nOut of range,  again:");
            }
        }while(num<3 || num>8);
        lobby.setKillPref(num);
        System.out.println("\nPerfect! you selected " + num +" skulls on kill track!");

        //MAP
        lobby.setMapPref(askMap());
        System.out.println("------------------------------\n");

    }

    public synchronized void waitingRoom()throws IOException {
        clearScreen();
        System.out.println(
                "\n\n----------------------------------------\n" +
                        "*** Waiting room..." +
                        "\n----------------------------------------");

        while (!lobby.isGameStarted()){
            if(lobby.hasGameTimerStarted())
                new CliCountDown(30).start();
            lobby.waitUpdate();
            for(VirtualPlayer p : lobby.getNewPlayersList() ){
                if (!p.isPrinted()) {
                    System.out.println(p.getUsername() + " using " + p.getCharacter() + ": is READY!");
                    p.setPrinted(true);
                }
            }

        }

    }




    public static void clearScreen() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
