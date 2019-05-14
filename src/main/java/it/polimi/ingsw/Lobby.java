package it.polimi.ingsw;


import java.util.ArrayList;

public class Lobby {

    ArrayList<Player> joinedPlayers;
    ArrayList<Boolean> readyStatus;

    public Lobby() {
        this.joinedPlayers = new ArrayList<>();
        this.readyStatus = new ArrayList<>();
    }

    public Lobby(Player p){
        this.joinedPlayers = new ArrayList<>();
        this.readyStatus = new ArrayList<>();
        this.addPlayer(p);

    }

    public void addPlayer(Player p){
        this.joinedPlayers.add(p);
        this.readyStatus.add(false);
        System.out.println(p.getUsername() + " has joined the battle as " + p.getCharacter());
    }

    public void disconnectPlayer(Player p){
        this.joinedPlayers.remove(p);
        this.readyStatus.remove(this.joinedPlayers.indexOf(p));
        System.out.print(p.getUsername() + "has cowardly left the battle before it began");
    }

    public void readyPlayer(Player p){
        this.readyStatus.set(this.joinedPlayers.indexOf(p),true);
    }

    public void unreadyPlayer(Player p){
        this.readyStatus.set(this.joinedPlayers.indexOf(p),false);
    }

}
