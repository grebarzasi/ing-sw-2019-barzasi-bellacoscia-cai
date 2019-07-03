package it.polimi.ingsw.model.cards;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Loads the Ammolot deck into a deck
 * used in the map constructor
 *
 * @author Yuting Cai
 */

public class AmmoDeckLoader {

    private static final int size = 36;
    private static final String AMMO_PATH = ("src/main/resources/data_files/deck_data/ammo.json");

    public static void loadDeck(Deck deckToLoad){

        ObjectMapper mapper = new ObjectMapper();


        File deckFile = new File(AMMO_PATH);

        AmmoParser[] parserList = new AmmoParser[size];

        //loads files into parsers

        try {

            parserList = mapper.readValue(deckFile, AmmoParser[].class);

        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int i=0;i<size;i++){

            deckToLoad.getUsable().add(new AmmoLot(parserList[i].hasPowerup(), new Ammo(parserList[i].getRed(),parserList[i].getBlue(),parserList[i].getYellow())));

        }

    }

}
