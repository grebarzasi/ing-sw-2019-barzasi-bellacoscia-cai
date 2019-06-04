package it.polimi.ingsw.virtual_model;

public class VirtualCell {
    private String content;
    private boolean armory;

    public VirtualCell(String content, boolean armory) {
        this.content = content;
        this.armory = armory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isArmory() {
        return armory;
    }

    public void setArmory(boolean armory) {
        this.armory = armory;
    }

    public String toString(){
        return content;
    }
}
