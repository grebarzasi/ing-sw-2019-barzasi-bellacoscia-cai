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

        Ammo tmp = new Ammo(0,0,0);

        for(int i=0;i<size;i++){

            tmp.setRed(parserList[i].getRed());
            tmp.setBlue(parserList[i].getBlue());
            tmp.setYellow(parserList[i].getYellow());

            deckToLoad.getUsable().add(new AmmoLot(parserList[i].hasPowerup(), tmp));

        }


    }

}
