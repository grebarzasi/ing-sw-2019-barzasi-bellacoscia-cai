package it.polimi.ingsw.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;

/**
 * loads the map into squareMatrix from .json file
 * map data has been divided into a map file and a confinement file containing
 * information about the confinements of the {@link Square}s. (north, east, south and west)
 */


public class MapLoader {



    public static void loadMap(String selection, Square[][] squareMatrix) {

        ObjectMapper mapper = new ObjectMapper();

        String PATH = ("data_files/map_data/");

        File squareFile = new File(PATH + selection + ".json");
        File confinementFile = new File (PATH + selection + "_confinements.json");


        int row;
        int column;
        int i = 0;

        SquareParser[] parserList = new SquareParser[12];
        SquareConfinementParser[] confinementList = new SquareConfinementParser[12];

        Square tmpSquare = new Square(null,null,null,false,null,null,null,null);


        //loads files into parsers

        try {

            parserList = mapper.readValue(squareFile, SquareParser[].class);
            confinementList = mapper.readValue(confinementFile, SquareConfinementParser[].class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //loads square data for each cell

        for( row = 0; row < 3; row++){
            for( column = 0; column < 4; column++){

                squareMatrix[row][column] = new Square(null,new Cell(row,column),parserList[i].getRoom(),parserList[i].getIsRespawn(),tmpSquare,tmpSquare,tmpSquare,tmpSquare) ;
                squareMatrix[row][column].setRoom(parserList[i].getRoom());

                i++;
            }
        }

        i=0;

        /**
         * loads confinement data into each square
         * translates the boolean format field from file into adjacent square
         */

        for( row = 0; row < 3; row++){
            for( column = 0; column < 4; column++){


                if(row != 0 && confinementList[i].getNorth() == true) {
                    squareMatrix[row][column].setNorth(squareMatrix[row-1][column]);
                }
                if(column != 3 && confinementList[i].getEast() == true) {
                    squareMatrix[row][column].setNorth(squareMatrix[row][column+1]);
                }
                if(row != 2 && confinementList[i].getSouth() == true) {
                    squareMatrix[row][column].setNorth(squareMatrix[row+1][column]);
                }
                if(column != 0 && confinementList[i].getWest() == true) {
                    squareMatrix[row][column].setNorth(squareMatrix[row][column-1]);
                }

                i++;
            }
        }

    }
    
    
}
