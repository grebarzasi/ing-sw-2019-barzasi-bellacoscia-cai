package it.polimi.ingsw.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

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

                squareMatrix[row][column] = new Square(new Cell(row,column),parserList[i].getRoom(),parserList[i].getIsRespawn(),null,null,null,null) ;
                squareMatrix[row][column].setRoom(parserList[i].getRoom());

                i++;
            }
        }

        i=0;

        /*
         * loads confinement data into each square
         * translates the boolean format field from file into adjacent square
         */

        for( row = 0; row < 3; row++){
            for( column = 0; column < 4; column++){


                if(row != 0 && confinementList[i].getNorth()) {
                    squareMatrix[row][column].setNorth(squareMatrix[row - 1][column]);
                }

                if(column != 3 && confinementList[i].getEast()) {
                    squareMatrix[row][column].setEast(squareMatrix[row][column+1]);
                }

                if(row != 2 && confinementList[i].getSouth()) {
                    squareMatrix[row][column].setSouth(squareMatrix[row+1][column]);
                }

                if(column != 0 && confinementList[i].getWest()) {
                    squareMatrix[row][column].setWest(squareMatrix[row][column-1]);
                }

                i++;
            }
        }

        /*for(column = 0, row = 0 ; column < 3; column ++){
                squareMatrix[row][column].setNorth(null);
        }
         */
    }

}
    
    
