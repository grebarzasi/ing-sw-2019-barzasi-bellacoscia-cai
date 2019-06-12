package it.polimi.ingsw.virtual_model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static it.polimi.ingsw.connection.ConnMessage.*;

/**
 * @author Gregorio Barzasi
 */
public class UpdateParser {

    public VirtualModel model;

    public UpdateParser(VirtualModel model){
        this.model=model;
    }


    public void updateModel(String s){
        System.out.println(s);
        ObjectMapper mapper = new ObjectMapper();
        // path of weapons data
        try {
            //open json file and start parsing
            JsonNode rootNode = mapper.readTree(s);
            parsePlayers(rootNode.path("players"));
            parseBoard(rootNode.path("main_board"));
            model.setTurn(model.findPlayer(rootNode.path("turn").asText()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        model.setUpdated(true);
    }

    public void parsePlayers(JsonNode node){
        Iterator<String> characterIterator = node.fieldNames();
        model.getAllPlayers().clear();
        while (characterIterator.hasNext()){
            String character = characterIterator.next();
            //if is a new player, add
            model.getAllPlayers().add(new VirtualPlayer(node.path(character).path("username").asText(),character));
            VirtualPlayer player = model.findPlayer(character);

            //parse player points
            player.setPoints(node.path(character).path("points").asInt());

            //parse player position

            String[] posArray = node.path(character).get("pos").asText().split(INNER_SEP);
            if(posArray[0].equals(NOTHING)) {
                posArray[0] = "-1";
                posArray[1] = "-1";
            }
            player.setRow(Integer.parseInt(posArray[0]));
            player.setColumn(Integer.parseInt(posArray[1]));

            //parse weapons info ( name, loaded)
            player.setWeapons(parseWeapon(node.path(character).path("weapons")));

            //parse powerUp info
            player.setPowerUps(parsePowerUp(node.path(character).path("powerups")));

            //parse player board
            parsePlayerBoard(node.path(character).path("board"),player.getpBoard());
        }
        model.setOwner(model.findPlayer(model.getOwner().getCharacter()));
    }

    public  ArrayList<String> parseWeapon(JsonNode node){
        Iterator<String> weaponsIterator = node.fieldNames();
        ArrayList<String> weaponsOwned = new  ArrayList<>();
        while (weaponsIterator.hasNext()){
            String weaponName = weaponsIterator.next();

            //add weapon
            weaponsOwned.add(weaponName+INNER_SEP+node.path(weaponName).asBoolean());
        }
        return weaponsOwned;
    }

    public ArrayList<String> parsePowerUp(JsonNode node){
        Iterator<String> puIterator = node.fieldNames();
        ArrayList<String> puOwned =new ArrayList<>();
        while (puIterator.hasNext()){
            String puID = puIterator.next();
            puOwned.add(node.path(puID).asText());
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

    public void parseBoard(JsonNode node){
        model.getBoard().setSkull(node.path("skull").asInt());

        //parse killshotTrack
        ArrayList<String> kills = new ArrayList<>();
        String all=node.path("killshot_truck").asText();
        String[] outer = all.split(INFO_SEP);

        for(String s : outer)
            kills.add(s);
        model.getBoard().setKillshotTrack(kills);

        //parse cells
        model.getBoard().getMap().setName(node.path("map").asText());
        parseMap(node.path("cells"));

    }


    public void parseCell(JsonNode node,boolean armory) {

        Iterator<String> cellsIterator = node.fieldNames();
        while (cellsIterator.hasNext()) {
            String cell = cellsIterator.next();
            if(model.getBoard().getMap().getCells().containsKey(cell))
                model.getBoard().getMap().getCells().get(cell).setContent(node.path(cell).asText());
            else
                model.getBoard().getMap().getCells().put(cell,new VirtualCell(node.path(cell).asText(),armory));
        }
    }

    public void parseMap(JsonNode node) {

        //parse pU cells
        parseCell(node.path("cells_pu"),false);
        //parse armory cells
        parseCell(node.path("cells_armory"),true);

    }

}
