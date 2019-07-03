package it.polimi.ingsw.model.cards.power_up;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.model.cards.Ammo;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.Deck;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class PowerupDeckBuilder {


    /**
     * Builds the PowerUp deck from .json file
     * @return the created deck
     */
    public Deck PowerUpBuilder() {

        ObjectMapper mapper = new ObjectMapper();

        String PATH = "src/main/resources/data_files/deck_data/";

        File jsonFile = new File(PATH + "power_up_data.json");

        ArrayList<Card> usablePU = new ArrayList<>();

        try{

            JsonNode rootNode = mapper.readTree(jsonFile);
            Iterator<String> powerUpIterator = rootNode.fieldNames();

            while(powerUpIterator.hasNext()) {
                String puKey = powerUpIterator.next();
                String puName = rootNode.path(puKey).path("name").asText();
                ArrayList<Ammo> occList = puOcc(rootNode.path(puKey).path("occ"));
                for(Ammo a : occList) {
                    usablePU.add(new PowerUp(a, puName));
                }
            }
            return new Deck(usablePU);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    return null;
    }

    private ArrayList<Ammo> puOcc(JsonNode node){
        ArrayList<Ammo> occ = new ArrayList<>();

        for(int i = 0; i < node.path("blue").asInt(); i++){
            occ.add(new Ammo(0,1,0));
        }
        for(int i = 0; i < node.path("red").asInt(); i++){
            occ.add(new Ammo(1,0,0));
        }
        for(int i = 0; i < node.path("yellow").asInt(); i++){
            occ.add(new Ammo(0,0,1));
        }
        return occ;
    }


}
