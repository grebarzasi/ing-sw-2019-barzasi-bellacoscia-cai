package it.polimi.ingsw.connection.socket;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.View;
import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;
import java.util.Set;

public class GameManagerSocket implements View {

    private ClientThreadSocket cl;

    public GameManagerSocket(ClientThreadSocket cl){
        this.cl=cl;
    }

    @Override
    public PowerUp showPowerUp(ArrayList<PowerUp> args) {
        return null;
    }

    @Override
    public Weapon showWeapon(ArrayList<Weapon> args) {
        return null;
    }

    @Override
    public Action showMoves(ArrayList<Action> args) {
        return null;
    }

    @Override
    public Square showPossibleMoves(ArrayList<Square> args) {
        return null;
    }

    @Override
    public ArrayList<Figure> showMultipleTargets(ArrayList<Figure> args) {
        return null;
    }

    @Override
    public Figure singleTargetingShowTarget(ArrayList<Figure> args) {
        return null;
    }

    @Override
    public boolean showBoolean(String message) {
        return false;
    }

    @Override
    public void displayMessage(String message) {

    }

    @Override
    public void displayLeaderboard() {

    }

    @Override
    public Effect showEffects(Set<Effect> args) {
        return null;
    }
}
