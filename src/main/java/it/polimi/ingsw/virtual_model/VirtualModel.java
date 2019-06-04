package it.polimi.ingsw.virtual_model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.connection.ConnectionTech;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class VirtualModel {
    private HashMap<String,VirtualPlayer> allPlayers;
    private VirtualBoard board;
    private ConnectionTech conn;

    public VirtualModel(ConnectionTech conn){
        this.conn=conn;
        this.allPlayers = new HashMap<>();
        this.board=new VirtualBoard();
    }

    public VirtualModel(){
        this.allPlayers = new HashMap<>();
        this.board=new VirtualBoard();
    }

    public void updateModel(String s){
        ObjectMapper mapper = new ObjectMapper();
        // path of weapons data
        try {
            //open json file and start parsing
            JsonNode rootNode = mapper.readTree(s);
            parsePlayers(rootNode.path("players"));
            board.parseBoard(rootNode.path("main_board"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parsePlayers(JsonNode node){
               Iterator<String> characterIterator = node.fieldNames();
               while (characterIterator.hasNext()){
                   String character = characterIterator.next();
                   //if is a new player, add
                   if(!allPlayers.containsKey(character))
                        allPlayers.put(character,new VirtualPlayer(node.path("username").asText(),character));
                   VirtualPlayer player = allPlayers.get(character);

                   //parse player points
                   player.setPoints(node.path("points").asInt());

                   //parse weapons info ( name, loaded)
                    player.setWeapons(parseWeapon(node.path("weapons")));

                   //parse powerUp info
                   player.setPowerUps(parsePowerUp(node.path("powerups")));

                   //parse player board
                   parsePlayerBoard(node.path("board"),player.getpBoard());

               }
    }

    public HashMap<String,Boolean> parseWeapon(JsonNode node){
        Iterator<String> weaponsIterator = node.fieldNames();
        HashMap<String,Boolean> weaponsOwned = new HashMap<>();
        while (weaponsIterator.hasNext()){
            String weaponName = weaponsIterator.next();

            //add weapon
           weaponsOwned.put(weaponName,node.path(weaponName).asBoolean());
        }
        return weaponsOwned;
    }

    public HashMap<String,String> parsePowerUp(JsonNode node){
        Iterator<String> puIterator = node.fieldNames();
        HashMap<String,String> puOwned = new HashMap<>();
        while (puIterator.hasNext()){
            String puName = puIterator.next();
            puOwned.put(puName,node.path(puName).asText());
        }
        return puOwned;
    }

    public void parsePlayerBoard(JsonNode node,VirtualPlayerBoard board){
        //parse damage and marks, then set to the player
        ArrayList<String> damage = new ArrayList<>();
        ArrayList<String> marks = new ArrayList<>();

        Iterator<JsonNode> damageIter = node.path("damage").elements();
        Iterator<JsonNode> marksIter = node.path("marks").elements();
        while (damageIter.hasNext())
            damage.add(damageIter.next().toString().replace("\"", ""));

        while (marksIter.hasNext())
            marks.add(marksIter.next().toString().replace("\"", ""));
        board.setDamage(damage);
        board.setMarks(marks);

        //set skull
        board.setSkulls(node.path("skulls").asInt());

        //set ammo
        JsonNode ammoNode =node.path("ammo");
        board.setAmmoRed(ammoNode.path("red").asInt());
        board.setAmmoBlue(ammoNode.path("blue").asInt());
        board.setAmmoYellow(ammoNode.path("yellow").asInt());
    }

    public static void main(String args[]){

    }
}
