package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.GameControllerServer;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.weapon.Weapon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AimRangeTest {
    private static Map m = new Map("large");
    private static ArrayList<Player> playerArrayList = new ArrayList<>();
    private static GameControllerServer controller = new GameControllerServer();
    private static Weapon w = new Weapon();
    private static Square[][] squareMatrix = m.getSquareMatrix();

    @BeforeAll
    public static void initialize() {
        //player 0
        playerArrayList.add(new Player("p 0", "prova",squareMatrix[0][0]));

        //player 1 visible same square
        playerArrayList.add(new Player("p 1", "prova",squareMatrix[0][0]));

        //player 2 visible same room
        playerArrayList.add(new Player("p 2", "prova",squareMatrix[1][0]));

        //player 3 notVisible next room
        playerArrayList.add(new Player("p 3", "prova",squareMatrix[2][0]));

        //player 4 not visible same line
        playerArrayList.add(new Player("p 4", "prova",squareMatrix[0][3]));

        for(Player p: playerArrayList){
            p.setControllerServer(controller);
        }
        controller.setPlayerList(playerArrayList);
        controller.setCurrentPlayer(playerArrayList.get(0));
        w.setOwner(playerArrayList.get(0));
    }

    @Test
    void rangeThisSquare() {
        AimRange aimingFilter= new AimRange(0,1);
        Set<Figure> figureSet;

        figureSet = aimingFilter.filter(w,w.getOwner().allFigures());

        assertTrue(figureSet.contains(playerArrayList.get(0)));
        assertTrue(figureSet.contains(playerArrayList.get(1)));
        assertFalse(figureSet.contains(playerArrayList.get(2)));
        assertFalse(figureSet.contains(playerArrayList.get(3)));
        assertFalse(figureSet.contains(playerArrayList.get(4)));
    }


    @Test
    void rangeNear() {
        AimRange aimingFilter= new AimRange(0,3);

        Set<Figure> figureSet;

        figureSet = aimingFilter.filter(w,w.getOwner().allFigures());

        assertTrue(figureSet.contains(playerArrayList.get(0)));
        assertTrue(figureSet.contains(playerArrayList.get(1)));
        assertTrue(figureSet.contains(playerArrayList.get(2)));
        assertTrue(figureSet.contains(playerArrayList.get(3)));
        assertFalse(figureSet.contains(playerArrayList.get(4)));
    }

    @Test
    void rangeMedium() {
        AimRange aimingFilter= new AimRange(1,3);

        Set<Figure> figureSet;

        figureSet = aimingFilter.filter(w,w.getOwner().allFigures());

        assertFalse(figureSet.contains(playerArrayList.get(0)));
        assertFalse(figureSet.contains(playerArrayList.get(1)));
        assertTrue(figureSet.contains(playerArrayList.get(2)));
        assertTrue(figureSet.contains(playerArrayList.get(3)));
        assertFalse(figureSet.contains(playerArrayList.get(4)));
    }
}