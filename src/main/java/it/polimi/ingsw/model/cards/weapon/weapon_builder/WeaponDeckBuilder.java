package it.polimi.ingsw.model.cards.weapon.weapon_builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.WeaponDeck;
import it.polimi.ingsw.utils.FileLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * return weapon deck
 * @author Gregorio Barzasi
 */

public class WeaponDeckBuilder {

    public static WeaponDeck buildDeck(){
        FileLoader fileLoader = new FileLoader();
        ObjectMapper mapper = new ObjectMapper();

        // path of weapons list
        final String path = "/data_files/weapons_data/weapons_list.json";

        InputStream jsonFile = fileLoader.getResource(path);
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
