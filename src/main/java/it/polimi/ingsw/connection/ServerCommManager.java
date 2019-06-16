package it.polimi.ingsw.connection;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.View;
import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.connection.socket.SocketClientHandler;
import it.polimi.ingsw.virtual_model.ViewClient;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static it.polimi.ingsw.connection.ConnMessage.*;

public class ServerCommManager implements View {

    private SocketClientHandler socketClient;
    private boolean rmi;
    private ViewClient rmiClient;

    public ServerCommManager(SocketClientHandler socketClient){
        this.socketClient = socketClient;
        this.rmi=false;
    }
    public ServerCommManager(ViewClient cl){
        this.rmiClient=cl;
        this.rmi=true;
    }

    public String askAndWait(String question,String args) {
        try{
            socketClient.getOut().println(question);
            while (!socketClient.getIn().readLine().equals(AKN));
            socketClient.getOut().println(args);
            String rpl;
            do
                rpl= socketClient.getIn().readLine();
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
            if (rmi) {
                try {
                    rpl=rmiClient.showPowerUp(parseString(s));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            else
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
            if (rmi) {
                try {
                    rpl=rmiClient.showWeapon(parseString(s));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            else
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
        if (rmi) {
            try {
                rpl=rmiClient.showActions(parseString(s));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else
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
        if (rmi) {
            try {
                rpl=rmiClient.showPossibleMoves(parseString(s));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else
        rpl=askAndWait(SHOW_MOVES,s);
        if(rpl==null)
            return null;
        for(Square p : args){
            if((p.getPosition().getRow()+INNER_SEP+p.getPosition().getColumn()).equals(rpl))
                return p;
        }
        return null;
    }


    public Boolean showBoolean(String message) {
        String rpl="";
        if (rmi) {
            try {
                rpl=Boolean.toString(rmiClient.showBoolean(message));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else
            rpl=askAndWait(SHOW_BOOLEAN,message);
        if(rpl==null)
            return null;
        if(rpl.equals("true"))
            return true;
        else if( rpl.equals("false"))
            return false;
        return false;
    }

    public void displayMessage(String message) {
        if (rmi) {
            try {
                rmiClient.displayMessage(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else
            askAndWait(SHOW_MESSAGE,message);
    }

    public void displayLeaderboard() {
    }
    public String chooseDirection(ArrayList<Figure> args) {
        String s="";
        String rpl="";
        for(Figure f: args)
            s=s+f.getCharacter()+INFO_SEP;
        if (rmi) {
            try {
                rpl=rmiClient.chooseDirection(parseString(s));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else
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
        }
        if (rmi) {
            try {
                rpl=rmiClient.showEffects(parseString(s));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else
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
        if (rmi) {
            try {
                rpl=rmiClient.showTargetAdvanced(parseString(s));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                socketClient.getOut().println(SHOW_TARGET_ADV);
                while (!socketClient.getIn().readLine().equals(AKN)) ;
                socketClient.getOut().println(s);
                do {
                    rpl = socketClient.getIn().readLine();
                }
            while (rpl.isEmpty());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(rpl.equals(NOTHING))
                return null;

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
        if (rmi) {
            try {
                rmiClient.updateModel(s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else {
            askAndWait(UPDATE, s);
        }
       return true;
    }

    public ArrayList<String> parseString(String args){
        ArrayList<String> sList = new ArrayList<>();
        if(args.equals(NOTHING)) {
            return sList;
        }
        String[] s=args.split(INFO_SEP);
        for(String x : s){
            sList.add(x);
        }
        return sList;
    }
}
