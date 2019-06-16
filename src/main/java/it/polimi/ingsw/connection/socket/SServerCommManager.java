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
import java.util.HashMap;
import java.util.Set;

import static it.polimi.ingsw.connection.ConnMessage.*;

public class SServerCommManager implements View {

    private SocketClientHandler cl;

    public SServerCommManager(SocketClientHandler cl){
        this.cl=cl;
    }

    public String askAndWait(String question,String args) {
        try{
            cl.getOut().println(question);
            while (!cl.getIn().readLine().equals(AKN));
            cl.getOut().println(args);
            String rpl;
            do
                rpl=cl.getIn().readLine();
            while(rpl.isEmpty());

            if(rpl.equals(NOTHING))
                return null;
            return rpl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Convert args into Strings, sends it to client and wait for a reply.
     * then convert the string into a PU pointer and returns it
     * @param args the Powerups to show
     * @return a pu element
     * @throws IOException
     */
    public PowerUp showPowerUp(ArrayList<PowerUp> args) {
            String s = "";
            String rpl = "";
            String[] temp;
            for (PowerUp p : args) {
                s = s + p.getName() + INNER_SEP + p.getAmmoOnDiscard().toString() + INFO_SEP;
            }
            rpl = askAndWait(SHOW_PU,s);
            if(rpl==null)
                return null;
            temp = rpl.split(INNER_SEP);
            for (PowerUp p : args) {
                if (p.getName().equals(temp[0]) && p.getAmmoOnDiscard().toString().equals(temp[1]))
                    return p;
            }
            return null;
    }

    public Weapon showWeapon(ArrayList<Weapon> args) {
            String s="";
            String rpl="";
            String[] temp;
            for(Weapon p : args){
                s=s+p.getName()+INNER_SEP+p.getChamber().toString()+INNER_SEP+p.getBasicEffect().getCost().toString().replaceFirst(p.getChamber().toString(),"")+INFO_SEP;
            }
            rpl=askAndWait(SHOW_WEAPONS,s);
            if(rpl==null)
                return null;
            temp=rpl.split(INNER_SEP);
            for(Weapon p : args){
                if(p.getName().equals(temp[0]))
                    return p;
            }
        return null;
    }

    public Action showActions(ArrayList<Action> args) {
        String s="";
        String rpl="";
        String[] temp;
        for(Action a : args){
            s=s+a.getDescription()+INNER_SEP+a.getRange()+INFO_SEP;
        }
        rpl=askAndWait(SHOW_ACTIONS,s);
        if(rpl==null)
            return null;
        temp=rpl.split(INNER_SEP);
        for(Action p : args){
            if(p.getDescription().equals(temp[0]))
                return p;
        }
        return null;
    }

    public Square showPossibleMoves(ArrayList<Square> args, Boolean show) {
        String s=show.toString()+INFO_SEP;
        String rpl="";
        for(Square p : args){
            s=s+p.getPosition().getRow()+INNER_SEP+p.getPosition().getColumn()+INFO_SEP;
        }
        rpl=askAndWait(SHOW_MOVES,s);
        if(rpl==null)
            return null;
        for(Square p : args){
            if((p.getPosition().getRow()+INNER_SEP+p.getPosition().getColumn()).equals(rpl))
                return p;
        }
        return null;
    }



//    public ArrayList<Figure> showTargets(ArrayList<Figure> args) {
//        String s="";
//        String rpl="";
//        String[] temp;
//        HashMap<String,Figure> players = new HashMap<>();
//        for(Figure a : args){
//            s=s+a.getCharacter()+INFO_SEP;
//            players.put(a.getCharacter(),a);
//        }
//        rpl=askAndWait(SHOW_TARGETS,s);
//        if(rpl==null)
//            return null;
//        ArrayList<Figure> target = new ArrayList<>();
//        temp=rpl.split(INFO_SEP);
//        for(String a : temp){
//            if(!players.containsKey(a))
//                return null;
//            target.add(players.get(a));
//        }
//        return target;
//    }
//
//    public Figure singleTargetingShowTarget(ArrayList<Figure> args) {
//        String s="";
//        String rpl="";
//        String[] temp;
//        HashMap<String,Figure> players = new HashMap<>();
//        for(Figure a : args){
//            s=s+a.getCharacter()+INNER_SEP+" "+INFO_SEP;
//            players.put(a.getCharacter(),a);
//        }
//        rpl=askAndWait(SHOW_SINGLE_TARGET,s);
//        if(rpl==null)
//            return null;
//        if(!players.containsKey(rpl))
//            return null;
//         return players.get(rpl);
//    }

    public Boolean showBoolean(String message) {
        String rpl=askAndWait(SHOW_BOOLEAN,message);
        if(rpl==null)
            return null;
        if(rpl.equals("true"))
            return true;
        else if( rpl.equals("false"))
            return false;
        return false;
    }

    public void displayMessage(String message) {
        askAndWait(SHOW_MESSAGE,message);
    }

    public void displayLeaderboard() {
    }
    public String chooseDirection(ArrayList<Figure> args) {
        String s="";
        String rpl;
        for(Figure f: args)
            s=s+f.getCharacter()+INFO_SEP;
        rpl= askAndWait(CHOOSE_DIRECTION,s).toUpperCase();
        if(rpl.equals(NOTHING))
            return null;
        return rpl;
    }

    public Effect showEffects(Set<Effect> args) {
        String s="";
        String rpl="";
        String[] temp;
        String cost;
        for(Effect e : args){
            cost=e.getCost().toString();
            if(cost.isEmpty())
                cost=NOTHING;
            s=s+e.getName()+INNER_SEP+cost+INFO_SEP;
//            s=s+"effetto"+i+INNER_SEP+e.getCost().toString()+INFO_SEP;

        }
        rpl=askAndWait(SHOW_EFFECTS,s);
        if(rpl==null)
            return null;
        temp=rpl.split(INNER_SEP);
        for(Effect p : args){
            if(p.getName().equals(temp[0]))
                return p;
        }
        return null;
    }

    public ArrayList<Figure> showTargetAdvanced(Set<Figure> args,int maxNum,boolean fromDiffSquare, String msg) {
        String s="";
        String rpl="";
        String[] temp;
        HashMap<String,Figure> players = new HashMap<>();
        s=maxNum+INNER_SEP+fromDiffSquare+INNER_SEP+msg.replaceAll(INNER_SEP,"").replaceAll(INFO_SEP,"")+INFO_SEP;
        for(Figure a : args){
            s=s+a.getCharacter()+INFO_SEP;
            players.put(a.getCharacter(),a);
        }

        try{
            cl.getOut().println(SHOW_TARGET_ADV);
            while (!cl.getIn().readLine().equals(AKN));
            cl.getOut().println(s);
            do
                rpl=cl.getIn().readLine();
            while(rpl.isEmpty());

            if(rpl.equals(NOTHING))
                return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Figure> target = new ArrayList<>();
        temp=rpl.split(INFO_SEP);
        if(temp.length>maxNum)
            return null;
        for(String a : temp){
            if(!players.containsKey(a))
                return null;
            target.add(players.get(a));
        }
        return target;
    }

    public boolean sendsUpdate(String s){
       if(askAndWait(UPDATE,s)!=null)
           return true;
       return false;
    }
}
