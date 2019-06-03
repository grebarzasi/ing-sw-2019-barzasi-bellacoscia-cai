package it.polimi.ingsw.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.Token;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class GameStateJsonBuilder {
    private Controller controller;
    private ObjectMapper mapper = new ObjectMapper();

    public JsonNode create(){
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.set("players",allPlayersNode());
        rootNode.set("main_board",mainBoardNode());


        return null;
    }

    public ObjectNode allPlayersNode(){
        ArrayList<Player> players = controller.getModel().getPlayerList();
        ObjectNode playersNode = mapper.createObjectNode();

        for(Player p : players){
            playersNode.set(p.getCharacter(),playerNode(p));
        }


        return null;
    }

    public ObjectNode playerNode(Player p){
        ObjectNode playerNode = mapper.createObjectNode();
        //username,points and position ( "row:column")
        playerNode.put("username",p.getUsername());
        playerNode.put("points",p.getPoints());
        playerNode.put("pos",p.getPosition().getPosition().getRow()+":"+p.getPosition().getPosition().getColumn());

        //add Weapon
        ObjectNode weaponNode = mapper.createObjectNode();
        for(Weapon w:p.getWeaponsList())
            weaponNode.put(w.getName(),w.isLoaded());
        playerNode.set("weapons",weaponNode);

        //add pu
        ObjectNode puNode = mapper.createObjectNode();
        for(PowerUp pu :p.getPowerupList()) {
            String ammo;
            if(pu.getAmmoOnDiscard().getRed()==1)
                ammo="red";
            else if(pu.getAmmoOnDiscard().getBlue()==1)
                ammo="blue";
            else
                ammo="yellow";
            puNode.put(pu.getName(),ammo);
        }

        playerNode.set("powerups",puNode);

        //add board section
        playerNode.set("board",boardNode(p));

        return playerNode;
    }

    public ObjectNode boardNode(Player p){

            ObjectNode boardNode = mapper.createObjectNode();

            //damage and marks array
            ArrayNode damage = mapper.createArrayNode();
            for(Token t:p.getPersonalBoard().getDamage()){
                damage.add(t.toString());
            }
            ArrayNode marks = mapper.createArrayNode();
            for(Token t:p.getPersonalBoard().getMark()){
                marks.add(t.toString());
            }
            boardNode.putPOJO("damage", damage);
            boardNode.putPOJO("marks", marks);

            //add skulls
            boardNode.put("skulls", p.getPersonalBoard().getPointVec().length);

            //create ammoNode
            ObjectNode ammoNode = mapper.createObjectNode();
            Ammo a = p.getPersonalBoard().getAmmoInventory();
            ammoNode.put("red", a.getRed());
            ammoNode.put("blue", a.getBlue());
            ammoNode.put("yellow",a.getYellow());
            boardNode.set("ammo",ammoNode);

            return boardNode;

        }
        public ObjectNode mainBoardNode(){
         ObjectNode mainBoardNode = mapper.createObjectNode();
         mainBoardNode.put("skull",true);
        return mainBoardNode;
        }

    }
