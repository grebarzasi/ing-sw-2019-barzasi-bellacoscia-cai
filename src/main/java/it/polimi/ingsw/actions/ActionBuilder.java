package it.polimi.ingsw.actions;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.GameModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * this class is used by {@link GameModel} to initialize {@link Action} according to game status
 * @author Gregorio Barzasi
 */
public class ActionBuilder {
    public Set<Action> buildActionFor(Figure p, boolean isFrenzy, boolean isEndTurn){

        //create the actionSet
        Set<Action> actionSet = new HashSet<>();


        /*FRENZY ACTION*/
        if(isFrenzy){
            return null;

        }

        /*NORMAL ACTION*/

        //reload is the only action allowed on end turn
        if(isEndTurn){
            ArrayList<SubAction> reloadArrayList = new ArrayList<>();
            reloadArrayList.add(new Reload());
            Action reload = new Action("Scegli le armi da ricaricare",reloadArrayList);
            actionSet.add(reload);
            return actionSet;
        }
        //else create action for that turn
        return createNormalActions(p,actionSet);

    }


    private Set<Action> createNormalActions(Figure p, Set<Action> actionSet){

        //initialize arrayList for actions
        ArrayList<SubAction> shootArrayList = new ArrayList<>();
        String shootDescription="Scegli un arma e spara";
        ArrayList<SubAction> pickArrayList = new ArrayList<>();
        String pickDescription="Raccogli";
        ArrayList<SubAction> moveArrayList = new ArrayList<>();

        //check damage num
        int damageNum = p.getPersonalBoard().getDamage().size();

        //if "adrenalinic" add special moves
        if(damageNum>5){
            shootArrayList.add(new Move(1));
            shootDescription="Muovi 1 posizione poi scegli un arma e spara ";
        }
        if (damageNum>2 ){
            pickArrayList.add(new Move(2));
            pickDescription="Muovi 2 posizioni poi raccogli";
        }

        //now add normal actions
        shootArrayList.add(new Shoot());
        pickArrayList.add(new Pick());
        moveArrayList.add(new Move(3));

        //adds all to actionSet ad return
        actionSet.add(new Action("Muovi 3 posizioni",moveArrayList));
        actionSet.add(new Action(pickDescription,pickArrayList));
        actionSet.add(new Action(shootDescription,shootArrayList));

        return actionSet;

    }
}
