package it.polimi.ingsw.view.virtual_model;
/**
 *smalles piece of a map.if is armory content contains ammo info, else in contains care package info
 *
 */
public class VirtualCell {
    private String content="";
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
