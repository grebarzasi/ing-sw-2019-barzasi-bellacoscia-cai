package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.GameModel;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.weapon.Weapon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AimDirectionTest {
    private static Map m = new Map("large");
    private static ArrayList<Player> playerArrayList = new ArrayList<>();
    private static GameModel controller = new GameModel();
    private static Weapon w = new Weapon();
    private static Square[][] squareMatrix = m.getSquareMatrix();

    @BeforeAll
    public static void initialize() {
        //player 0
        playerArrayList.add(new Player("p 0", "prova1",squareMatrix[1][3]));

        //player 1 visible same square
        playerArrayList.add(new Player("p 1", "prova2",squareMatrix[0][0]));

        //player 2 visible same room
        playerArrayList.add(new Player("p 2", "prova3",squareMatrix[1][0]));

        //player 3 notVisible next room
        playerArrayList.add(new Player("p 3", "prova4",squareMatrix[1][1]));

        //player 4 not visible same line
        playerArrayList.add(new Player("p 4", "prova5",squareMatrix[1][2]));

        //player not visible other room
        playerArrayList.add(new Player("p 5", "prova6",squareMatrix[2][3]));


        for(Player p: playerArrayList){
            p.setModel(controller);
        }
        controller.setPlayerList(playerArrayList);
        controller.setCurrentPlayer(playerArrayList.get(0));
        w.setOwner(playerArrayList.get(0));
    }

    @Test
    void wallBlock() {
        AimDirection dir = new AimDirection(false);
        Set<Figure> figureSet;
        Set<Figure> result=new HashSet<>();
        result.add(playerArrayList.get(4));
        result.add(playerArrayList.get(0));
        figureSet = dir.filter(w,w.getOwner().allFigures());
        w.getDirectionTemp().setDirectionTemp("o");
        figureSet = dir.filter(w,w.getOwner().allFigures());
        assertEquals(result,figureSet);
    }
}