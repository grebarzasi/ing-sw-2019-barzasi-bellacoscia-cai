package it.polimi.ingsw.connection;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.View;
import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.connection.rmi.RmiClientHandler;
import it.polimi.ingsw.connection.socket.SocketClientHandler;
import it.polimi.ingsw.virtual_model.ViewClient;
import javafx.application.Platform;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static it.polimi.ingsw.connection.ConnMessage.*;
import static it.polimi.ingsw.connection.ServerMessage.*;

public class ServerCommManager  extends Thread implements View {

    private SocketClientHandler socketClient;
    private Player owner;
    private RmiClientHandler rmiHandler;
    private ViewClient rmiClient;
    private boolean rmi;
    private boolean inUse =false;
    private String rplTh;
    private String updateBuffer;


    public ServerCommManager(SocketClientHandler socketClient){
        this.socketClient = socketClient;
        this.rmi=false;
        this.owner=socketClient.getOwner();
    }
    public ServerCommManager(RmiClientHandler cl){
        this.rmiHandler=cl;
        this.rmiClient=rmiHandler.getViewClient();
        this.rmi=true;
        this.owner=rmiHandler.getOwner();
    }

    public String askAndWait(String question,String args) throws IOException{

            socketClient.getOut().println(question);
            while (!socketClient.getIn().readLine().equals(AKN));
            socketClient.getOut().println(args);
            String rpl;
            do
                rpl= socketClient.getIn().readLine();
            while(rpl.isEmpty());

            if(rpl.equals(NOTHING)) {
                return null;
            }
            return rpl;

    }
    /**
     * Convert args into Strings, sends it to client and wait for a reply.
     * then convert the string into a PU pointer and returns it
     * @param args the Powerups to show
     * @return a pu element
     */
    public PowerUp showPowerUp(ArrayList<PowerUp> args) {
            String s = "";
            String rpl = "";
            String[] temp;
            for (PowerUp p : args) {
                s = s + p.getName() + INNER_SEP + p.getAmmoOnDiscard().toString() + INFO_SEP;
            }
            final String sTh=s;

            /*-------------------------------------------*/
            Thread t = new Thread(() -> {
                try{
                    setInUse(true);
                    if (rmi)
                        setRplTh(rmiClient.showPowerUp(parseString(sTh)));
                    else
                        setRplTh(askAndWait(SHOW_PU,sTh));

                    setInUse(false);
                }catch(IOException e){
                    handleDisconnection();
                    setRplTh(null);
                }
            });
            t.start();

            /*-------------------------------------------*/
            if(!handleInactivity(t)){
                return null;
            }

            rpl=rplTh;
            if(rpl==null)
                return null;
            temp = rpl.split(INNER_SEP);
            for (PowerUp p : args) {
                if (p.getName().equals(temp[0]) && p.getAmmoOnDiscard().toString().equals(temp[1]))
                    return p;
            }
            return null;
    }

    public Weapon showWeapon(ArrayList<Weapon> args){
            String s="";
            String rpl="";
            String[] temp;
            for(Weapon p : args){
                s=s+p.getName()+INNER_SEP+p.getChamber().toString()+INNER_SEP+p.getBasicEffect().getCost().toString().replaceFirst(p.getChamber().toString(),"")+INFO_SEP;
            }
            try{
            setInUse(true);
            if (rmi) {
                    rpl=rmiClient.showWeapon(parseString(s));
            }
            else
            rpl=askAndWait(SHOW_WEAPONS,s);
            setInUse(false);
            }catch(IOException e){
                handleDisconnection();
                return null;
            }
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

        try{
        setInUse(true);
        if (rmi) {
                rpl=rmiClient.showActions(parseString(s));
        }
        else
        rpl=askAndWait(SHOW_ACTIONS,s);
        setInUse(false);
        }catch(IOException e){
            handleDisconnection();
            return null;
        }

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

        try {
            setInUse(true);
            if (rmi) {
                rpl = rmiClient.showPossibleMoves(parseString(s));
            } else
                rpl = askAndWait(SHOW_MOVES, s);
            setInUse(false);
        }catch(IOException e){
            handleDisconnection();
            return null;
        }
        if(rpl==null)
            return null;
        for(Square p : args){
            if((p.getPosition().getRow()+INNER_SEP+p.getPosition().getColumn()).equals(rpl))
                return p;
        }
        return null;
    }


    public Boolean showBoolean(String message){
        String rpl="";
        try{
        setInUse(true);
        if (rmi) {
                rpl=Boolean.toString(rmiClient.showBoolean(message));
        }
        else
            rpl=askAndWait(SHOW_BOOLEAN,message);
        setInUse(false);
        }catch(IOException e){
            handleDisconnection();
            return null;
        }
        if(rpl==null)
            return null;
        if(rpl.equals("true"))
            return true;
        else if( rpl.equals("false"))
            return false;
        return false;
    }

    public void displayMessage(String message){
        try {
            setInUse(true);
            if (rmi) {
                rmiClient.displayMessage(message);
            } else
                askAndWait(SHOW_MESSAGE, message);
            setInUse(false);
        }catch(IOException e){
            handleDisconnection();
        }
    }

    public void displayLeaderboard(){
    }
    public String chooseDirection(ArrayList<Figure> args){
        String s="";
        String rpl="";
        for(Figure f: args)
            s=s+f.getCharacter()+INFO_SEP;

        try{
        setInUse(true);
        if (rmi) {
             rpl=rmiClient.chooseDirection(parseString(s));
        }
        else
             rpl= askAndWait(CHOOSE_DIRECTION,s).toUpperCase();
        setInUse(false);
        }catch(IOException e){
            handleDisconnection();
            return null;
        }
        if(rpl.equals(NOTHING))
            return null;
        return rpl;
    }

    public Effect showEffects(Set<Effect> args){
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
        try{
        setInUse(true);
        if (rmi) {
                rpl=rmiClient.showEffects(parseString(s));
        }
        else
             rpl=askAndWait(SHOW_EFFECTS,s);
        setInUse(false);
        }catch(IOException e){
            handleDisconnection();
            return null;
        }
        if(rpl==null)
            return null;
        temp=rpl.split(INNER_SEP);
        for(Effect p : args){
            if(p.getName().equals(temp[0]))
                return p;
        }
        return null;
    }

    public ArrayList<Figure> showTargetAdvanced(Set<Figure> args,int maxNum,boolean fromDiffSquare, String msg){
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
        setInUse(true);
        if (rmi) {
                rpl=rmiClient.showTargetAdvanced(parseString(s));
        }
        else{
                socketClient.getOut().println(SHOW_TARGET_ADV);
                while (!socketClient.getIn().readLine().equals(AKN)) ;
                socketClient.getOut().println(s);
                do {
                    rpl = socketClient.getIn().readLine();
                }
            while (rpl.isEmpty());
        }
        setInUse(false);
        }catch(IOException e){
            handleDisconnection();
            return null;
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
        updateBuffer = s;
        if(owner.isInactive()) {
            return true;
        }
        setInUse(true);
        try{
            if (rmi) {
                rmiClient.updateModel(s);
            }
            else {
                askAndWait(UPDATE, s);
            }
            setInUse(false);
        }catch(IOException e){
            handleDisconnection();
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


    public void handleDisconnection(){
        owner.setDisconnected(true);
        System.err.println(owner.getUsername()+CLIENT_UNREACHABLE);
    }

    public boolean handleInactivity(Thread t){
        int i=0;
        System.out.print("\nInactivity countdown: ");
        for(;i<INACTIVITY_TIMEOUT;i++){
            if(!t.isAlive())
                return true;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(INACTIVITY_TIMEOUT-i+"s ");
        }
        System.out.println("\n"+owner.getCharacter()+" is inactive!");
        t.interrupt();
        setInUse(false);
        setInactive(true);
        return false;
    }

    @Override
    public void run(){
        try {

            while (true) {
                if (isInactive()&&!isDisconnected()) {
                    if (!isInUse())
                        sendsUpdate(updateBuffer);
                        setInactive(!showBoolean(RETURN_IN_GAME));
                        sendsUpdate(updateBuffer);
                } else {
                        if (!isInUse()) {
                            if (!isRmi()) {
                                synchronized (socketClient) {
                                }
                                socketClient.getOut().println(PING);
                                while (!socketClient.getIn().readLine().equals(PONG)) ;
                            } else
                                rmiClient.isConnected();
                        }
                    }
                sleep(2000);
            }


        }catch(IOException e){
            handleDisconnection();
            this.interrupt();
        }catch(InterruptedException b){
            b.printStackTrace();
        }
    }

    public SocketClientHandler getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(SocketClientHandler socketClient) {
        this.socketClient = socketClient;
    }

    public synchronized boolean isRmi() {
        return rmi;
    }

    public void setRmi(boolean rmi) {
        this.rmi = rmi;
    }

    public synchronized boolean isInUse() {
        return inUse;
    }

    public synchronized void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public ViewClient getRmiClient() {
        return rmiClient;
    }

    public void setRmiClient(ViewClient rmiClient) {
        this.rmiClient = rmiClient;
    }

    public boolean isDisconnected() {
        return owner.isDisconnected();
    }

    public void setDisconnected(boolean disconnected) {
        owner.setDisconnected(disconnected);
    }

    public boolean isInactive() {
        return owner.isInactive();
    }

    public void setInactive(boolean inactive) {
        owner.setInactive(inactive);
    }





    public void setRplTh(String rplTh) {
        this.rplTh = rplTh;
    }
}
