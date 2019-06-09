package it.polimi.ingsw.connection.socket;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.View;
import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static it.polimi.ingsw.connection.ConnMessage.*;

public class GameManagerSocket implements View {

    private ClientThreadSocket cl;

    public GameManagerSocket(ClientThreadSocket cl){
        this.cl=cl;
    }

    /**
     * Convert args into Strings, sends it to client and wait for a reply.
     * then convert the string into a PU pointer and returns it
     * @param args the Powerups to show
     * @return a pu element
     * @throws IOException
     */
    public PowerUp showPowerUp(ArrayList<PowerUp> args) {
        String s="";
        String rpl="";
        String[] temp;
        for(PowerUp p : args){
            s=s+p.getName()+INNER_SEP+p.getAmmoOnDiscard().toString()+INFO_SEP;
        }
        cl.getOut().println(SHOW_PU);
        cl.getOut().println(s);

        try {
            rpl=cl.getIn().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(rpl.equals(NOTHING))
            return null;
        temp=rpl.split(INNER_SEP);
        for(PowerUp p : args){
            if(p.getName().equals(temp[0])&&p.getAmmoOnDiscard().toString().equals(temp[1]))
                return p;
        }
        return null;
    }

    public Weapon showWeapon(ArrayList<Weapon> args) {
        String s="";
        String rpl="";
        String[] temp;
        for(Weapon p : args){
            s=s+p.getName()+INNER_SEP+p.isLoaded()+INFO_SEP;
        }
        cl.getOut().println(SHOW_WEAPONS);
        cl.getOut().println(s);

        try {
            rpl=cl.getIn().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(rpl.equals(NOTHING))
            return null;
        temp=rpl.split(INNER_SEP);
        for(Weapon p : args){
            if(p.getName().equals(temp[0]))
                return p;
        }
        return null;
    }

    public Action showMoves(ArrayList<Action> args) {
        return null;
    }

    public Square showPossibleMoves(ArrayList<Square> args) {
        return null;
    }

    public ArrayList<Figure> showMultipleTargets(ArrayList<Figure> args) {
        return null;
    }

    public Figure singleTargetingShowTarget(ArrayList<Figure> args) {
        return null;
    }

    public boolean showBoolean(String message) {
        return false;
    }

    public void displayMessage(String message) {

    }

    public void displayLeaderboard() {

    }

    public Effect showEffects(Set<Effect> args) {
        return null;
    }
}
