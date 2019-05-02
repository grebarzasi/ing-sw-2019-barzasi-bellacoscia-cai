package it.polimi.ingsw.cards.weapon.weapon_builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.cards.Card;
import it.polimi.ingsw.cards.Deck;
import it.polimi.ingsw.cards.WeaponDeck;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * return weapon deck
 * @author Gregorio Barzasi
 */

public class WeaponDeckBuilder {

    public static WeaponDeck buildDeck(){

        ObjectMapper mapper = new ObjectMapper();

        // path of weapons list
        final String path = "src/main/resources/data_files/weapons_data/weapons_list.json";

        File jsonFile = new File(path);
        try {
            ArrayList<Card> usableWList = new ArrayList<>();

            //open json file and start parsing
            JsonNode rootNode = mapper.readTree(jsonFile);
            Iterator<String> wNameIterator = rootNode.fieldNames();

            //create one or more instance of a weapon according to json
            while(wNameIterator.hasNext()){
                String wName = wNameIterator.next();
                for(int i = 0;rootNode.path(wName).asInt()>i ; i++)
                    usableWList.add(WeaponBuilder.buildWeapon(wName));
            }


            return new WeaponDeck(usableWList);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }
}
