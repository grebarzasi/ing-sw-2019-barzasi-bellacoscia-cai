package it.polimi.ingsw;


import it.polimi.ingsw.connection.MainServer;
import it.polimi.ingsw.connection.socket.SClientHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;

import static it.polimi.ingsw.connection.ConnMessage.COUNTDOWN;


/**
 * The game lobby, contains up to 5 players and their ready status
 */

public class Lobby extends Thread {

    private boolean started;
    private boolean hasTimerStarted;
    private Timer timer;

    private int mapPref;
    private int killPref;
    private boolean terminatorPref;
    private boolean finalFrenzyPref;

    private MainServer god;


    private final int maxPlayer = 5;

    //List of players who have joined the lobby
    private ArrayList<SClientHandler> joinedPlayers;


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
    public synchronized boolean addPlayer(SClientHandler p) {
        if (joinedPlayers.size() < maxPlayer && usernameCheck(p) && characterCheck(p)) {
            joinedPlayers.add(p);
            return true;
        }

        return false;
    }

    /**
     * Restore session on connection start
     */

    public synchronized boolean restorePlayer(SClientHandler p) {
        if (!usernameCheck(p)) {
            for(SClientHandler toCheck: this.joinedPlayers){
                if(toCheck.getOwner().getUsername().equals(p.getOwner().getUsername())){
                   if(toCheck.isExpired())
                       return false;
                   //HERE
                    return true;
                }
            }

        }
        return false;
    }


    /**
     * Updates all client when other player is added and starts countdown when the 3rd player join
     */
    public synchronized void updateClients() {

        int i = 0;
        for (SClientHandler c : joinedPlayers) {
            if (c.isReady()) {
                i++;
                c.updateLobby();
            }
        }
        if (i == 3 && !hasStarted()) {
            hasTimerStarted=true;
            System.out.println("Countdown started");
            timer.schedule(new TimerGameStart(this), (COUNTDOWN * 1000));
        }
    }

    /**
     * check username already added. if not return true
     */

    public boolean usernameCheck(SClientHandler p){
        if(p.getOwner().getUsername().isEmpty())
            return false;
        for(SClientHandler toCheck: joinedPlayers){
            if(toCheck.getOwner().getUsername().equals(p.getOwner().getUsername())){
                return false;
            }
        }
        return true;
    }
    /**
     * check character already added. if not return true
     */

    public boolean characterCheck(SClientHandler p){
        for(SClientHandler toCheck: joinedPlayers){
            if(toCheck.getOwner().getCharacter().equals(p.getOwner().getCharacter())){
                return false;
            }
        }
        return true;
    }
    /**
     * remove player on disconnection
     */

    public void disconnectPlayer(SClientHandler p){
        joinedPlayers.remove(p);
        updateClients();
        System.out.print(p.getOwner().getUsername() + " has cowardly left the battle before it began\n");
    }



    @Override
    public synchronized String toString() {
        String s="";
        for(SClientHandler c: joinedPlayers){
            s= s + c.toString()+";";
        }
        return s;
    }

    //make decision on the game setup;
    public void prefDecision(){
        ArrayList<Integer> killPrefList=new ArrayList<>();
        ArrayList<Integer> mapPrefList=new ArrayList<>();
        ArrayList<Boolean> terminatorList=new ArrayList<>();
        ArrayList<Boolean> finalFrenzyList=new ArrayList<>();

        for(SClientHandler c: joinedPlayers){
            killPrefList.add(c.getKillPref());
            mapPrefList.add(c.getMapPref());
            terminatorList.add(c.isTerminatorPref());
            finalFrenzyList.add(c.isFinalFrenzyPref());
        }

        this.killPref=modeOf(killPrefList);
        this.mapPref=(modeOf(mapPrefList));
        this.terminatorPref=(modeOfBool(terminatorList));
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


    public boolean timerHasStarted() {
        return hasTimerStarted;
    }

    public void setHasTimerStarted(boolean hasTimerStarted) {
        this.hasTimerStarted = hasTimerStarted;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getMapPref() {
        return mapPref;
    }

    public void setMapPref(int mapPref) {
        this.mapPref = mapPref;
    }

    public int getKillPref() {
        return killPref;
    }

    public void setKillPref(int killPref) {
        this.killPref = killPref;
    }

    public boolean hasTerminatorPref() {
        return terminatorPref;
    }

    public void setTerminatorPref(boolean terminatorPref) {
        this.terminatorPref = terminatorPref;
    }

    public boolean hasFinalFrenzy() {
        return finalFrenzyPref;
    }

    public void setFinalFrenzyPref(boolean finalFrenzyPref) {
        this.finalFrenzyPref = finalFrenzyPref;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }


    public synchronized ArrayList<SClientHandler> getJoinedPlayers() {
        return joinedPlayers;
    }

    public void setJoinedPlayers(ArrayList<SClientHandler> joinedPlayers) {
        this.joinedPlayers = joinedPlayers;
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
        god.startGame();
    }
}








