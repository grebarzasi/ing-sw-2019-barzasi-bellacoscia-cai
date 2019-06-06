package it.polimi.ingsw.controller;

public class FrenzySpecialAction implements ControllerState {


    private int range;
    private Controller controller;

    @Override
    public void command() {



    }

    public FrenzySpecialAction(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
