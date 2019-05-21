package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LobbyTest {
/*
    @Test
    void allPurposeTest() {

        Player maxwell = new Player("Maxwell","Physicist");
        Player natta = new Player("Natta","Chemist");
        Player tesla = new Player("Tesla","Engineer");
        Player nash = new Player("Nash","Mathematician");
        Player voltaire  = new Player("Voltaire","Philosopher");
        Player fermi = new Player("Fermi","Nuclear Physicist");

        Lobby test = new Lobby(maxwell);

        assertEquals(1, test.getJoinedPlayers().size());

        test.addPlayer(natta);
        test.addPlayer(natta);

        test.readyPlayer(natta);

        assertFalse(test.allReady());
        assertFalse(test.canStart());

        test.readyPlayer(maxwell);
        assertTrue(test.allReady());
        assertFalse(test.canStart());

        test.addPlayer(tesla);
        test.addPlayer(nash);
        test.addPlayer(voltaire);

        assertEquals(5,test.getJoinedPlayers().size());

        test.addPlayer(fermi);

        assertEquals(5,test.getJoinedPlayers().size());

        assertFalse(test.allReady());
        assertFalse(test.canStart());

        test.readyPlayer(tesla);
        test.readyPlayer(nash);
        test.readyPlayer(voltaire);

        assertTrue(test.allReady());
        assertTrue(test.canStart());

        test.disconnectPlayer(voltaire);
        test.addPlayer(fermi);

        assertFalse(test.allReady());
        assertFalse(test.canStart());

        test.readyPlayer(fermi);

        assertTrue(test.allReady());
        assertTrue(test.canStart());


    }*/
}