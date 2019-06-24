package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.GameModel;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gregorio Barzasi
 */
class AimComparatorTest {
    private static Map m = new Map("large");
    private static ArrayList<Player> playerArrayList = new ArrayList<>();
    private static GameModel controller = new GameModel();
    private static Weapon w = new Weapon();
    private static Square[][] squareMatrix = m.getSquareMatrix();

    @BeforeAll
    public static void initialize() {
        //player 0 OWNER
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
            p.setModel(controller);
        }
        controller.setPlayerList(playerArrayList);
        controller.setCurrentPlayer(playerArrayList.get(0));
        w.setOwner(playerArrayList.get(0));
    }

    @Test
    void getPlayersFromSourceLasHit() {
        ArrayList<String> source =new ArrayList<>();
        Set<Figure> figureSet;
        Set<String> empty =new HashSet<>();

        w.setBasicEffect(new Effect());
        w.setAddOneEffect(new Effect());
        w.setAddTwoEffect(new Effect());
        w.setAlternativeEffect(new Effect());
        w.setExtraMove(new Effect());

        Set<Figure> hitSet = new HashSet<>();
        hitSet.add(playerArrayList.get(1));
        hitSet.add(playerArrayList.get(2));

        w.getBasicEffect().setTargetHitSet(hitSet);
        w.getAddOneEffect().setTargetHitSet(hitSet);
        w.getAddTwoEffect().setTargetHitSet(hitSet);
        w.getAlternativeEffect().setTargetHitSet(hitSet);
        w.getExtraMove().setTargetHitSet(hitSet);

        AimComparator comp;

        //BASE
        source.add("basic");
        comp = new AimComparator(source);
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(0));
        assertEquals(hitSet,figureSet);

        //addOne

        source.retainAll(empty);
        source.add("addOne");
        comp = new AimComparator(source);
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(0));
        assertEquals(hitSet,figureSet);

        //addOne
        source.retainAll(empty);
        source.add("addTwo");
        comp = new AimComparator(source);
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(0));
        assertEquals(hitSet,figureSet);

        //addTwo
        source.retainAll(empty);
        source.add("alternative");
        comp = new AimComparator(source);
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(0));
        assertEquals(hitSet,figureSet);

        //extramove
        source.retainAll(empty);
        source.add("extramove");
        comp = new AimComparator(source);
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(0));
        assertEquals(hitSet,figureSet);

        //lastHit
        source.retainAll(empty);
        source.add("last");
        w.setLastHit(playerArrayList.get(3));
        comp = new AimComparator(source);
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(0));
        hitSet.retainAll(empty);
        hitSet.add(playerArrayList.get(3));
        assertEquals(hitSet,figureSet);

    }

    @Test
    void getPlayersFromSourceSpace() {
        ArrayList<String> source =new ArrayList<>();
        Set<Figure> figureSet;
        Set<Figure> hitSet = new HashSet<>();
        AimComparator comp;
        Set<String> empty =new HashSet<>();

        //Players In the same room as p2
        source.retainAll(empty);
        source.add("room");
        comp = new AimComparator(source);
        hitSet.add(playerArrayList.get(0));
        hitSet.add(playerArrayList.get(1));
        hitSet.add(playerArrayList.get(2));
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(2));
        assertEquals(hitSet,figureSet);

        //Players In my room
        source.retainAll(empty);
        source.add("myRoom");
        comp = new AimComparator(source);
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(2));
        assertEquals(hitSet,figureSet);

        //Players In the same square as p1
        source.retainAll(empty);
        hitSet.retainAll(empty);
        source.add("square");
        comp = new AimComparator(source);
        hitSet.add(playerArrayList.get(0));
        hitSet.add(playerArrayList.get(1));
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(1));
        assertEquals(hitSet,figureSet);

        //Players In my square
        source.retainAll(empty);
        source.add("mySquare");
        comp = new AimComparator(source);
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(1));
        assertEquals(hitSet,figureSet);

        //me
        source.retainAll(empty);
        source.add("me");
        hitSet.retainAll(empty);
        comp = new AimComparator(source);
        hitSet.add(playerArrayList.get(0));
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(3));
        assertEquals(hitSet,figureSet);

        playerArrayList.get(4).setOldPosition(squareMatrix[0][0]);

        //OldSquare
        source.retainAll(empty);
        source.add("oldSquare");
        hitSet.retainAll(empty);
        hitSet.add(playerArrayList.get(0));
        hitSet.add(playerArrayList.get(1));
        comp = new AimComparator(source);
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(4));
        assertEquals(hitSet,figureSet);

    }
    @Test
    void getPlayersFromSourceComb() {
        ArrayList<String> source =new ArrayList<>();
        Set<Figure> figureSet;
        Set<Figure> hitSet = new HashSet<>();
        AimComparator comp;
        Set<String> empty =new HashSet<>();

        w.setBasicEffect(new Effect());
        w.setAddOneEffect(new Effect());
        w.setAddTwoEffect(new Effect());

        hitSet.add(playerArrayList.get(1));
        hitSet.add(playerArrayList.get(2));

        w.getBasicEffect().setTargetHitSet(new HashSet<>(hitSet));

        hitSet.add(playerArrayList.get(3));

        w.getAddOneEffect().setTargetHitSet(new HashSet<>(hitSet));
        hitSet.retainAll(empty);
        hitSet.add(playerArrayList.get(4));
        w.getAddTwoEffect().setTargetHitSet(new HashSet<>(hitSet));

        source.add("base");
        source.add("addOne");
        source.add("addTwo");
        source.add("me");

        comp = new AimComparator(source);
        figureSet = comp.getPlayersFromSource(w,playerArrayList.get(0));

        hitSet.retainAll(empty);
        hitSet.add(playerArrayList.get(4));
        hitSet.add(playerArrayList.get(1));
        hitSet.add(playerArrayList.get(2));
        hitSet.add(playerArrayList.get(3));
        hitSet.add(playerArrayList.get(0));

        assertEquals(hitSet,figureSet);



    }
}