package it.polimi.ingsw;


import it.polimi.ingsw.connection.ClientHandler;
import it.polimi.ingsw.connection.MainServer;
import it.polimi.ingsw.controller.Controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;

import static it.polimi.ingsw.connection.ConnMessage.COUNTDOWN;


/**
 * The game lobby, contains up to 5 players and their ready status
 */

public class Lobby extends Thread {

    /**
     * The controller of the game
     */
    private Controller controller;

    /**
     * True if the game has started, false otherwise
     */
    private boolean started;
    private boolean hasTimerStarted;
    private Timer timer;

    private static final int MIN_PLAYERS=3;

    private int mapPref;
    private int killPref;
    private boolean terminatorPref;
    private boolean finalFrenzyPref;

    private boolean testMode;


    private MainServer god;

    private final int maxPlayer = 5;

    //List of players who have joined the lobby
    private ArrayList<ClientHandler> joinedPlayers;


    public Lobby(MainServer god) {
        this.joinedPlayers = new ArrayList<>();
        this.timer= new Timer();
        this.started=false;
        this.hasTimerStarted=false;
        this.god=god;
    }

    public Lobby() {
        this.joinedPlayers = new ArrayList<>();
        this.timer= new Timer();
        this.started=false;
        this.hasTimerStarted=false;
    }
    /**
     * if is legal, a player is added to joined players
     */
    public synchronized boolean addPlayer(ClientHandler p) {
        if(!hasStarted()) {
            if (joinedPlayers.size() < maxPlayer && usernameCheck(p) && characterCheck(p)) {
                joinedPlayers.add(p);
                return true;
            }
            return false;
        }else{
            return reconnectPlayer(p);
        }
    }

    /**
     * Restore session on connection start
     */



    /**
     * Updates all client when other player is added and starts countdown when the 3rd player join
     */
    public synchronized void updateClients() {

        int i = 0;
        for (ClientHandler c : joinedPlayers) {
            if (c.isReady()) {
                i++;
                c.updateLobby();
            }
        }
        if (i == MIN_PLAYERS && !hasStarted()) {
            hasTimerStarted=true;
            System.out.println("Countdown started");
            timer=new Timer();
            timer.schedule(new TimerGameStart(this), (COUNTDOWN * 1000));
        }
    }

    /**
     * check username already added. if not return true
     */

    public boolean usernameCheck(ClientHandler p){
        if(p.getOwner().getUsername().equals("null"))
            return false;
        if(p.getOwner().getUsername().isEmpty())
            return false;
        for(ClientHandler toCheck: joinedPlayers){
            if(toCheck.getOwner().getUsername().equals(p.getOwner().getUsername())){
                return false;
            }
        }
        return true;
    }
    /**
     * check character already added. if not return true
     */

    public boolean characterCheck(ClientHandler p){
        for(ClientHandler toCheck: joinedPlayers){
            if(toCheck.getOwner().getCharacter().equals(p.getOwner().getCharacter())){
                return false;
            }
        }
        return true;
    }
    /**
     * remove player on disconnection
     */

    public void disconnectPlayer(ClientHandler p){
        joinedPlayers.remove(p);
        updateClients();
        System.out.print(p.getOwner().getUsername() + " has cowardly left the battle before it began\n");
        if(joinedPlayers.size()<MIN_PLAYERS) {
            timer.cancel();
            updateClients();
        }
    }

    public boolean reconnectPlayer(ClientHandler p){
        for(Player x: controller.getModel().getPlayerList()){
            if(x.getCharacter().equals(p.getOwner().getCharacter())&&x.getUsername().equals(p.getOwner().getUsername())&&x.isDisconnected()){
                x.getView().reconnectPlayer(p);
                return true;
            }
        }
        return false;
    }



    @Override
    public synchronized String toString() {
        String s="";
        for(ClientHandler c: joinedPlayers){
            s= s + c.toString()+";";
        }
        return s;
    }

    //make decision on the game setup

    public void prefDecision(){
        ArrayList<Integer> killPrefList=new ArrayList<>();
        ArrayList<Integer> mapPrefList=new ArrayList<>();
        ArrayList<Boolean> terminatorList=new ArrayList<>();
        ArrayList<Boolean> finalFrenzyList=new ArrayList<>();

        for(ClientHandler c: joinedPlayers){
            killPrefList.add(c.getKillPref());
            mapPrefList.add(c.getMapPref());
            terminatorList.add(c.isTerminatorPref());
            finalFrenzyList.add(c.isFinalFrenzyPref());
        }


        modeOf(killPrefList);
        if(killPref<5||killPref>8)
            killPref=8;
        if(testMode)
            killPref=2;
        this.mapPref=(modeOf(mapPrefList));

        if(joinedPlayers.size()<5)
            this.terminatorPref=(modeOfBool(terminatorList)||testMode);
        else terminatorPref=false;

        this.finalFrenzyPref=(modeOfBool(finalFrenzyList));
    }


    public Boolean modeOfBool(ArrayList<Boolean> list){
        int def=0;
        for(Boolean s : list){
            if(s)
                def=+1;
            else
                def=-1;
        }
        if(def>0)
            return true;
        if(def<=0)
            return false;
        return false;
    }

    public Integer modeOf(ArrayList<Integer> list){
        HashMap<Integer,Integer> occ = new HashMap<>();
        for(Integer s : list){
            if(occ.containsKey(s))
                occ.replace(s,occ.get(s)+1);
            else
                occ.put(s,1);
        }
        Integer pref=0;
        int max=0;
        Set<Integer> all = occ.keySet();
        for(Integer s: all){
            if(max<occ.get(s)){
                pref = s;
                max=occ.get(s);
            }
        }
        return pref;
    }

    public int getMapPref() {
        return mapPref;
    }

    public int getKillPref() {
        return killPref;
    }

    public boolean hasTerminatorPref() {
        return terminatorPref;
    }

    public boolean hasFinalFrenzy() {
        return finalFrenzyPref;
    }

    public synchronized ArrayList<ClientHandler> getJoinedPlayers() {
        return joinedPlayers;
    }

    public synchronized boolean hasStarted() {
        return started;
    }

    public synchronized boolean hasTimerStarted() {
        return hasTimerStarted;
    }

    public synchronized void setStarted(boolean hasStarted) {
        this.started = hasStarted;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public void run() {
        while(!hasStarted()){
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        prefDecision();
        System.out.println(killPref);
        System.out.println(finalFrenzyPref);
        System.out.println(mapPref);
        System.out.println(terminatorPref);
        try {
            god.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








