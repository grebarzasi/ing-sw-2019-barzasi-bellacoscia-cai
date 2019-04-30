package it.polimi.ingsw.cards;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AmmoDeckLoader {

    private static final int size = 36;

    public static void loadDeck(Deck deckToLoad){

        ObjectMapper mapper = new ObjectMapper();

        final String PATH = ("data_files/deck_data/ammo.json");

        File deckFile = new File(PATH);

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
