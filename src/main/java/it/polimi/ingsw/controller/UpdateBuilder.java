package it.polimi.ingsw.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.Token;
import it.polimi.ingsw.board.Armory;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.NonSpawnSquare;
import it.polimi.ingsw.board.map.SpawnSquare;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.AmmoLot;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

import static it.polimi.ingsw.CLI.CliMessages.TERMINATOR_NAME;
import static it.polimi.ingsw.connection.ConnMessage.*;

public class UpdateBuilder {
    private Controller controller;

    private ObjectMapper mapper = new ObjectMapper();

    public UpdateBuilder(Controller conn){
        this.controller=conn;
    }


    public JsonNode create(){
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.set("players",allPlayersNode());
        rootNode.set("main_board",mainBoardNode());
        rootNode.put("turn",controller.getCurrentPlayer().getCharacter());
        //rootNode.set("bot",mainBoardNode());
        return rootNode;
    }

    private ObjectNode allPlayersNode(){
        ArrayList<Player> players = controller.getModel().getPlayerList();
        ObjectNode playersNode = mapper.createObjectNode();

        for(Player p : players){
            playersNode.set(p.getCharacter(),playerNode(p));
        }
        if(controller.getModel().getBot()!=null)
            playersNode.set(controller.getModel().getBot().getCharacter(),botNode(controller.getModel().getBot()));

        return playersNode;
    }

    private ObjectNode botNode(Figure p){
        ObjectNode playerNode = mapper.createObjectNode();
        //username,points and position ( "row:column")
        playerNode.put("username",TERMINATOR_NAME);
        playerNode.put("points",p.getPoints());

        if(p.getPosition()!=null)
            playerNode.put("pos",p.getPosition().getPosition().getRow()+":"+p.getPosition().getPosition().getColumn());
        else
            playerNode.put("pos",NOTHING+INNER_SEP+NOTHING);

        //add Weapon
        ObjectNode weaponNode = mapper.createObjectNode();
        weaponNode.put("I'M",true);
        weaponNode.put("THE",true);
        weaponNode.put("TERMINATOR",true);
        playerNode.set("weapons",weaponNode);

//        //add pu
            ObjectNode puNode = mapper.createObjectNode();
//        Integer i=0;
//        for(PowerUp pu :p.getPowerupList()) {
//            puNode.put(i.toString(),pu.getName()+INNER_SEP+pu.getAmmoOnDiscard().toString());
//            i++;
//        }

        playerNode.set("powerups",puNode);

        //add board section
        playerNode.set("board",boardNode(p));

        return playerNode;
    }


    private ObjectNode playerNode(Player p){
        ObjectNode playerNode = mapper.createObjectNode();
        //username,points and position ( "row:column")
        playerNode.put("username",p.getUsername());
        playerNode.put("points",p.getPoints());

        if(p.getPosition()!=null)
             playerNode.put("pos",p.getPosition().getPosition().getRow()+":"+p.getPosition().getPosition().getColumn());
        else
            playerNode.put("pos",NOTHING+INNER_SEP+NOTHING);

            //add Weapon
        ObjectNode weaponNode = mapper.createObjectNode();
        for(Weapon w:p.getWeaponsList())
            weaponNode.put(w.getName(),w.isLoaded());
        playerNode.set("weapons",weaponNode);

        //add pu
        ObjectNode puNode = mapper.createObjectNode();
        int i=0;
        for(PowerUp pu :p.getPowerupList()) {
            puNode.put(Integer.toString(i),pu.getName()+INNER_SEP+pu.getAmmoOnDiscard().toString());
            i++;
        }

        playerNode.set("powerups",puNode);

        //add board section
        playerNode.set("board",boardNode(p));

        return playerNode;
    }

    private ObjectNode boardNode(Figure p){

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

        private ObjectNode mainBoardNode(){
         ObjectNode mainBoardNode = mapper.createObjectNode();
         Board board=controller.getModel().getBoard();
         Map map = board.getMap();
         //skull
         mainBoardNode.put("skull",board.getTrack().getSkullMax());

         //map
         mainBoardNode.put("map",map.getName());

         //killshotTruck
         mainBoardNode.put("killshot_truck",killshottruck());
         //cells
         ObjectNode cellsNode = mapper.createObjectNode();
         cellsNode.set("cells_pu",createCellNode(map,false));
         cellsNode.set("cells_armory",createCellNode(map,true));
         mainBoardNode.set("cells",cellsNode);

         return mainBoardNode;
        }

    private String killshottruck(){
        Board board=controller.getModel().getBoard();
        ArrayList<ArrayList<Token>> killshot = board.getTrack().getKillsTrack();
        StringBuilder s= new StringBuilder();
        for(ArrayList<Token> aT: killshot) {
            for (Token t : aT)
                s.append(t.getOwner().getCharacter()).append(INNER_SEP);
            s.append(INFO_SEP);
        }
        return s.toString();
    }

        private ObjectNode createCellNode(Map map, boolean isArmory){
        ObjectNode node = mapper.createObjectNode();
        Square[][] sq = map.getSquareMatrix();
        for(int r=0;r<3;r++)
            for(int c=0;c<4;c++){
                Square s = sq[r][c];
                String coord = r+":"+c;
                if (s.isSpawn() && isArmory){
                     Armory armory=((SpawnSquare)s).getArmory();
                     node.put(coord,armory.toString());
                }
                if (!s.isSpawn() && !isArmory){
                    AmmoLot ammoLot =((NonSpawnSquare)s).getDrop();
                    if(ammoLot!=null)
                        node.put(coord,ammoLot.toString());
                    else
                        node.put(coord,"empty");
                }
            }
                return node;
    }

    }
