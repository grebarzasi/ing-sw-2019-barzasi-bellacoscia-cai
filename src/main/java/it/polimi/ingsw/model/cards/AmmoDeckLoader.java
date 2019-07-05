package it.polimi.ingsw.model.cards;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.utils.FileLoader;


import java.io.IOException;
import java.io.InputStream;

/**
 * Loads the Ammolot deck into a deck
 * used in the map constructor
 *
 * @author Yuting Cai
 */

public class AmmoDeckLoader {

    private AmmoDeckLoader(){

    }

    private static final int SIZE = 36;
    private static final String AMMO_PATH = ("/data_files/deck_data/ammo.json");

    public static void loadDeck(Deck deckToLoad){
        FileLoader fileLoader = new FileLoader();
        ObjectMapper mapper = new ObjectMapper();


        InputStream deckFile = fileLoader.getResource(AMMO_PATH);

        AmmoParser[] parserList = new AmmoParser[SIZE];

        //loads files into parsers

        try {

            parserList = mapper.readValue(deckFile, AmmoParser[].class);

        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int i=0;i<SIZE;i++){

            deckToLoad.getUsable().add(new AmmoLot(parserList[i].hasPowerup(), new Ammo(parserList[i].getRed(),parserList[i].getBlue(),parserList[i].getYellow())));

        }

    }

}
