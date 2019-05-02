package it.polimi.ingsw;

import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;


import java.sql.Time;
import java.util.ArrayList;
import java.util.Set;

public class GameControllerServer implements Controller {

    private ArrayList<Figure> figureList;

    private Figure currentFigure;

    private Time timeTurn;

    public Board currentBoard;





    public GameControllerServer(ArrayList<Figure> figureList, Figure currentFigure, Time timeTurn, Board currentBoard) {
        this.figureList = figureList;
        this.currentFigure = currentFigure;
        this.timeTurn = timeTurn;
        this.currentBoard = currentBoard;
    }


    public void setActions() {
    }

    public void getStatus(Figure p) {
    }

    public void kill(Figure p) {
    }

    public void finalFrenzy() {
    }

    public void finalScore(Figure p) {
    }

    public void update() {
    }

    public void newTurn() {
    }

    public void endTurn() {
    }


    /**
    *ask player
     * @autor Gregorio Barzasi
     */
    public Square askPosition(){
        return null;
    }
    public Ammo askAmmo(){return null;}
    public Set<Figure> askTarget(int num){
        return null;
    }
    public Weapon askWeapon(){
        return null;
    }
    public void askUseEffect(Set<Effect> eff){}




    public ArrayList<Figure> getFigureList() {
        return figureList;
    }

    public void setFigureList(ArrayList<Figure> figureList) {
        this.figureList = figureList;
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    public Time getTimeTurn() {
        return timeTurn;
    }

    public void setTimeTurn(Time timeTurn) {
        this.timeTurn = timeTurn;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }



    public ArrayList<Figure> getPlayers() {
        return this.figureList;
    }
}