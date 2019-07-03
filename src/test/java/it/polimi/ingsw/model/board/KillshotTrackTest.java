package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Terminator;
import it.polimi.ingsw.model.Token;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KillshotTrackTest {

    ArrayList<Token> t = new ArrayList<>();

    KillshotTrack kills = new KillshotTrack(8);


    /**
     * Asserts that the scoring system behaves correctly in case of presence
     * of token parity by two players on the track
     */

    @Test
    void precedenceTest2() {

        KillshotTrack nurburgring = new KillshotTrack(5);

        Player sls = new Player("Mercedes", "SLS");
        Player i8 = new Player("BMW", "i8");
        Player modelS = new Player("Tesla", "ModelS");
        Player p1 = new Player("McLaren","P1");
        Terminator chiron = new Terminator("Chiron",null);

        ArrayList<Token> race1 = new ArrayList<>();
        ArrayList<Token> race2 = new ArrayList<>();
        ArrayList<Token> race3 = new ArrayList<>();
        ArrayList<Token> race4 = new ArrayList<>();
        ArrayList<Token> race5 = new ArrayList<>();
        ArrayList<Token> race6 = new ArrayList<>();
        ArrayList<Token> race7 = new ArrayList<>();
        ArrayList<Token> race8 = new ArrayList<>();
        ArrayList<Token> race9 = new ArrayList<>();

        race1.add(new Token(sls));
        race1.add(new Token(modelS));

        race2.add(new Token(modelS));
        race2.add(new Token(modelS));

        race3.add(new Token(i8));

        race4.add(new Token(sls));
        race4.add(new Token(p1));

        race5.add(new Token(p1));
        race5.add(new Token(p1));

        race6.add(new Token(chiron));
        race6.add(new Token(chiron));

        race7.add(new Token(sls));
        race7.add(new Token(sls));

        race8.add(new Token(chiron));
        race8.add(new Token(chiron));

        race9.add(new Token(p1));

        nurburgring.addKill(race1);
        nurburgring.addKill(race2);
        nurburgring.addKill(race3);
        nurburgring.addKill(race4);
        nurburgring.addKill(race5);
        nurburgring.addKill(race6);
        nurburgring.addKill(race7);
        nurburgring.addKill(race8);
        nurburgring.addKill(race9);

        nurburgring.scorePoints();

        assertEquals(8, sls.getPoints());
        assertEquals(6, p1.getPoints());
        assertEquals(4, chiron.getPoints());
        assertEquals(2, modelS.getPoints());
        assertEquals(1, i8.getPoints());

    }

    /**
     * Asserts that the scoring system behaves correctly in case of presence
     * of token parity by two players on the track, test case 2
     */

    @Test
    void precedenceTest() {

        KillshotTrack nurburgring = new KillshotTrack(5);

        Player sls = new Player("Mercedes", "SLS");
        Player i8 = new Player("BMW", "i8");
        Player modelS = new Player("Tesla", "ModelS");

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

        assertEquals(new Token(sls), nurburgring.getKillsTrack().get(0).get(0));

        nurburgring.scorePoints();

        assertEquals(8, sls.getPoints());
        assertEquals(6, modelS.getPoints());
        assertEquals(4, i8.getPoints());

    }

    /**
     * Asserts that kills are correctly added to the track
     */

    @Test
    void addKillTest() {

        int before = kills.getSkullMax();
        ArrayList<ArrayList<Token>> tokenListBefore = kills.getKillsTrack();
        int tokenBefore = tokenListBefore.size();

        kills.addKill(t);
        int after = kills.getSkullMax();
        ArrayList<ArrayList<Token>> tokenListAfter = kills.getKillsTrack();
        int tokenAfter = tokenListAfter.size();

        assertNotEquals(before, after);
        assertNotEquals(tokenBefore, tokenAfter);

    }

    /**
     * Asserts that points are scored
     */

    @Test
    void getPointsTest() {
        Player p1 = new Player();
        ArrayList<Token> t1 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Token k = new Token(p1);
            t1.add(k);
        }
        kills.addKill(t1);

        Player p2 = new Player();
        ArrayList<Token> t2 = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Token k = new Token(p2);
            t2.add(k);
        }
        kills.addKill(t2);

        Player p3 = new Player();
        ArrayList<Token> t3 = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Token k = new Token(p3);
            t3.add(k);
        }
        kills.addKill(t3);

        int before1 = p1.getPoints();
        int before2 = p2.getPoints();
        kills.scorePoints();
        int after1 = p1.getPoints();
        int after2 = p2.getPoints();


        assertNotEquals(before1, after1);
        assertNotEquals(before2, after2);
        assertEquals(8, p1.getPoints());
        assertEquals(4, p3.getPoints());
        assertEquals(6, p2.getPoints());

    }
}
