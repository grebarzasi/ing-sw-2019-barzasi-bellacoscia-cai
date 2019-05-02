package it.polimi.ingsw.cards.power_up;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.Card;
import it.polimi.ingsw.cards.Deck;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The type Power up builder.
 *
 * @author Carlo Bellacoscia
 */
public class DeckPowerUpBuilder extends Card{

    @Override
    public void fetch() {

    }

    /**
     * Power up builder.
     *
     * @return the deck
     */
    public Deck PowerUpBuilder() {

        ObjectMapper mapper = new ObjectMapper();

        String path = "src/main/resources/data_files/deck_data/";

        File jsonFile = new File(path + "power_up_data.json");

        ArrayList<Card> usablePU = new ArrayList<>();

        try{

            JsonNode rootNode = mapper.readTree(jsonFile);
            Iterator<JsonNode> powerUpIterator = rootNode.elements();
            while(powerUpIterator.hasNext()) {
                String puKey = powerUpIterator.next().asText();

                String puName = rootNode.path("name").textValue();
                ArrayList<Ammo> occList = puOcc(rootNode.path(puKey).path("occ"));
                for(Ammo a : occList) {
                    switch (puKey) {
                        case "teleporter":
                            usablePU.add(new Teleporter(a, puName));
                            break;
                        case "newton":
                            usablePU.add(new Newton(a, puName));
                            break;
                        case "tagback_granade":
                            usablePU.add(new TagbackGrenade(a, puName));
                            break;
                        case "targeting_scope":
                            usablePU.add(new TargetingScope(a, puName));
                            break;
                    }
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

    /**
     * Pu occ array list.
     *
     * @param node the node
     * @return the array list
     */
    public ArrayList<Ammo> puOcc(JsonNode node){
        ArrayList<Ammo> occ = new ArrayList<>();

        for(int i = 0; i <= node.path("blue").asInt(); i++){
            occ.add(new Ammo(0,1,0));
        }
        for(int i = 0; i <= node.path("red").asInt(); i++){
            occ.add(new Ammo(1,0,0));
        }
        for(int i = 0; i <= node.path("yellow").asInt(); i++){
            occ.add(new Ammo(0,0,1));
        }
        return occ;
    }


}
