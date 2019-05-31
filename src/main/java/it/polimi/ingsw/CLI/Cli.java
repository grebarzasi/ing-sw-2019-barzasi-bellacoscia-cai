package it.polimi.ingsw.CLI;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.View;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Cli implements View {

    private BufferedReader sc;

    @Override
    public PowerUp showPowerUp(ArrayList<PowerUp> args) {

        for(PowerUp p: args){

            System.out.println(args.indexOf(p)+" :");
            System.out.println(p.getName());
            System.out.println(" " +powerUptoColor(p));

        }

        //TODO take input and return it;
        return null;

    }

    private String powerUptoColor(PowerUp toMatch){

        String colour = "nothing";

        if(toMatch.getAmmoOnDiscard().getRed() == 1){
            colour = "red";
        }else if(toMatch.getAmmoOnDiscard().getBlue() == 1){
            colour = "blue";
        }else if(toMatch.getAmmoOnDiscard().getYellow() == 1){
            colour = "yellow";
        }

        return colour;

    }

    @Override
    public Weapon showWeapon(ArrayList<Weapon> args) {
        return null;
    }

    @Override
    public String showMoves(Set<String> args) {
        return null;
    }

    @Override
    public Square showPossibleMoves(ArrayList<Square> args) {
        return null;
    }

    @Override
    public Player showTarget(Figure arg) {
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
    public boolean showBoolean() {
        return false;
    }

    @Override
    public void displayMessage(String string) {

    }

    @Override
    public void displayLeaderboard() {

    }
}
