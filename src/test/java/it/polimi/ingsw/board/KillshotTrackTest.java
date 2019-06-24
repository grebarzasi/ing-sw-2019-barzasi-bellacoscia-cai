package it.polimi.ingsw.board;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.Token;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KillshotTrackTest {

    ArrayList<Token> t = new ArrayList<>();

    KillshotTrack kills = new KillshotTrack(8);


    @Test
    void rigorousTest(){

        KillshotTrack nurburgring = new KillshotTrack(5);

        Player sls = new Player("Mercedes","SLS");
        Player i8 = new Player("BMW","i8");
        Player modelS = new Player("Tesla","ModelS");

        ArrayList<Token> race1 = new ArrayList<>();
        ArrayList<Token> race2 = new ArrayList<>();
        ArrayList<Token> race3 = new ArrayList<>();
        ArrayList<Token> race4 = new ArrayList<>();
        ArrayList<Token> race5 = new ArrayList<>();

        race1.add(new Token(sls));
        race1.add(new Token(sls));

        race2.add(new Token(modelS));
        race2.add(new Token(modelS));

        race3.add(new Token(i8));
        race3.add(new Token(i8));

        race4.add(new Token(sls));
        race4.add(new Token(sls));

        race5.add(new Token(sls));
        race5.add(new Token(sls));



        nurburgring.addKill(race1);
        nurburgring.addKill(race2);
        nurburgring.addKill(race3);
        nurburgring.addKill(race4);
        nurburgring.addKill(race5);

        nurburgring.getPoints();

        assertEquals(8,sls.getPoints());
        assertEquals(6,modelS.getPoints());
        assertEquals(4,i8.getPoints());

    }

    @Test
    void addKillTest() {
        int before = kills.getSkullMax();
        ArrayList<ArrayList <Token>> tokenListBefore = kills.getKillsTrack();
        int tokenBefore = tokenListBefore.size();

        kills.addKill(t);
        int after = kills.getSkullMax();
        ArrayList<ArrayList <Token>> tokenListAfter = kills.getKillsTrack();
        int tokenAfter = tokenListAfter.size();

        assertNotEquals(before,after);
        assertNotEquals(tokenBefore,tokenAfter);

    }

    @Test
    void getPointsTest() {
        Player p1 =  new Player();
        ArrayList <Token> t1 = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            Token k = new Token(p1);
            t1.add(k);
        }
        kills.addKill(t1);

        Player p2 =  new Player();
        ArrayList <Token> t2 = new ArrayList<>();
        for (int i = 0; i < 1; i++){
            Token k = new Token(p2);
            t2.add(k);
        }
        kills.addKill(t2);

        Player p3 =  new Player();
        ArrayList <Token> t3 = new ArrayList<>();
        for (int i = 0; i < 1; i++){
            Token k = new Token(p3);
            t3.add(k);
        }
        kills.addKill(t3);

        int before1 = p1.getPoints();
        int before2 = p2.getPoints();
        kills.getPoints();
        int after1 = p1.getPoints();
        int after2 = p2.getPoints();


        assertNotEquals(before1,after1);
        assertNotEquals(before2,after2);
        assertEquals(8,p1.getPoints());
        assertEquals(4,p3.getPoints());
        assertEquals(6,p2.getPoints());

    }

    @Test
    void killerListCreatorTest() {
        Player p =  new Player();
        ArrayList <Token> t = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            Token k = new Token(p);
            t.add(k);
        }
        kills.addKill(t);

        ArrayList<Figure> f = kills.killerListCreator();

        assertFalse(f.isEmpty());
        assertNotNull(p);

    }

    @Test
    void countOccTest() {
        Player p =  new Player();
        ArrayList <Token> t = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            Token k = new Token(p);
            t.add(k);
        }
        kills.addKill(t);

        Integer occ = kills.countOcc(p);
        Integer zero = 0;

        assertNotEquals(zero, occ);
    }

    @Test
    void getIndexMinTest() {

        Player p1 =  new Player();
        Player p2 =  new Player();
        Token k1 = new Token(p1);
        Token k2 = new Token(p2);
        ArrayList <Token> t1 = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            t1.add(k1);
            kills.addKill(t1);

        }

        ArrayList <Token> t2 = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            t2.add(k2);
            kills.addKill(t2);

        }

        Integer occ1 = kills.countOcc(p1);
        Integer occ2 = kills.countOcc(p2);
        ArrayList<Integer> occ = new ArrayList<>();
        occ.add(occ1);
        occ.add(occ2);
        int res = 1;
        int minIndex = kills.getIndexMin(occ);

        assertEquals(res,minIndex);
    }
}