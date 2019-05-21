package it.polimi.ingsw;


import java.util.ArrayList;
import java.util.HashMap;


/**
 * The game lobby, contains up to 5 players and their ready status
 */

public class Lobby {

    private boolean gameStarted=false;

    public boolean isGameStarted() {
        return gameStarted;
    }

    private final int maxPlayer = 5;

    //List of players who have joined the lobby
    private ArrayList<Player> joinedPlayers;
    //Maps each player to their status, ready or not
    private HashMap<Player,Boolean> readyStatus = new HashMap<>();

    private String map;

    public Lobby() {
        this.joinedPlayers = new ArrayList<>();
        this.readyStatus = new HashMap<>();
    }

    public Lobby(Player p){
        this.joinedPlayers = new ArrayList<>();
        this.joinedPlayers.add(p);
        this.readyStatus.put(p,false);

    }

    public void addPlayer(Player p){

        if (this.joinedPlayers.size() < maxPlayer && usernamecheck(p) && charactercheck(p)) {
            this.joinedPlayers.add(p);
            this.readyStatus.put(p, false);
            System.out.println(p.getUsername() + " has joined the battle as " + p.getCharacter());
        } else {
            System.out.println(p.getUsername() + " has been rejected");
        }
    }

    public boolean usernamecheck(Player p){
        for(Player toCheck: this.joinedPlayers){
            if(toCheck.getUsername().equals(p.getUsername())){
                return false;
            }
        }
        return true;
    }

    public boolean charactercheck(Player p){
        for(Player toCheck: this.joinedPlayers){
            if(toCheck.getCharacter().equals(p.getCharacter())){
                return false;
            }
        }
        return true;
    }

    public void disconnectPlayer(Player p){
        this.joinedPlayers.remove(p);
        this.readyStatus.remove(p);
        System.out.print(p.getUsername() + " has cowardly left the battle before it began\n");
    }

    /**
     * Checks if all players have set their status to ready
     * @return True if all players are ready, false otherwise
     */
    public boolean allReady(){

        for (Player p : joinedPlayers){
            if(!readyStatus.get(p)){
                return false;
            }
        }
        return true;
    }

    public boolean canStart(){
        if(joinedPlayers.size()>=3 && allReady()){
            return true;
        }
        return false;
    }

    public void readyPlayer(Player p){
        this.readyStatus.replace(p,true);

    }

    public void unreadyPlayer(Player p){
        this.readyStatus.replace(p,false);
    }

    public ArrayList<Player> getJoinedPlayers() {
        return joinedPlayers;
    }

    public void setJoinedPlayers(ArrayList<Player> joinedPlayers) {
        this.joinedPlayers = joinedPlayers;
    }

    public HashMap<Player, Boolean> getReadyStatus() {
        return readyStatus;
    }

    public void setReadyStatus(HashMap<Player, Boolean> readyStatus) {
        this.readyStatus = readyStatus;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}








