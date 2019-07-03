package it.polimi.ingsw.CLI;


import it.polimi.ingsw.virtual_model.GameInitializer;
import it.polimi.ingsw.virtual_model.VirtualCell;
import it.polimi.ingsw.virtual_model.VirtualModel;
import it.polimi.ingsw.virtual_model.VirtualPlayer;
import static it.polimi.ingsw.CLI.CLiBoardStuff.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static it.polimi.ingsw.CLI.CliColor.*;
import static it.polimi.ingsw.CLI.CliMessages.TERMINATOR_NAME;
import static it.polimi.ingsw.connection.ConnMessage.INFO_SEP;
import static it.polimi.ingsw.connection.ConnMessage.INNER_SEP;

/**
 * Build the cli board line by line. reads the maps from file and parse special tokens to know when and where place
 * game info like players board and killshot truck.
 */

public class CliBoard {
    private File f;
    private String all="";
    private String lastColor="";
    private String lastBackground="";
    private int cellsPlnum=12;
    private int cellsCPnum=12;
    private int boardPrinted=0;

    private ArrayList<String> armory=new ArrayList<>();

    private ArrayList<String> weaponTemp;
    private VirtualModel model;

    public CliBoard(VirtualModel model){
        this.model=model;
    }

    /**
     * draw the board
     */
    public void draw(){
        armory.clear();
        if(all.isEmpty())
            loadMap();
        cellsPlnum=12;
        cellsCPnum=12;
        boardPrinted=0;
        BufferedReader in = new BufferedReader(new StringReader(all));
        String line;
        String[] splitLine;
        try {
            while ((line = in.readLine()) != null) {
                splitLine = line.split(";");
                for (String s : splitLine) {
                    plotString(s);
                }
            }
        }catch(IOException e){
            System.err.print("something went wrong drawing the board");
        }
    }
    public void loadMap(){
        try {
            all=new String(Files.readAllBytes(Paths.get("src/main/resources/cli_files/cli_" + model.getBoard().getMap().getName() +".txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * reads and replace special tag in map file
     */
    public void plotString(String s){

        switch(s){
            //Text
            case"#R":
                System.out.print(RED);
                lastColor=RED;
                break;
            case"#B":
                System.out.print(BLUE);
                lastColor=BLUE;
                break;
            case"#G":
                System.out.print(GREEN);
                lastColor=GREEN;
                break;
            case"#Y":
                System.out.print(YELLOW);
                lastColor=YELLOW;
                break;
            case"#W":
                System.out.print(WHITE);
                lastColor=WHITE;
                break;
            case"#P":
                System.out.print(PURPLE);
                lastColor=PURPLE;
                break;

                //Background
            case"#r":
                System.out.print(RED_B);
                lastBackground=RED_B;
                break;
            case"#b":
                System.out.print(BLUE_B);
                lastBackground=BLUE_B;
                break;
            case"#g":
                System.out.print(GREEN_B);
                lastBackground=GREEN_B;
                break;
            case"#y":
                System.out.print(YELLOW_B);
                lastBackground=YELLOW_B;
                break;
            case"#w":
                System.out.print(WHITE_B);
                lastBackground=WHITE_B;
                break;
            case"#p":
                System.out.print(PURPLE_B);
                lastBackground=PURPLE_B;
                break;

                //RESET
            case"#z":
                System.out.print(RESET);
                lastBackground=RESET;
                lastColor=RESET;
                break;

                //New Line
            case"#?":
                System.out.println(RESET);
                break;
                //Powerup or Armory
            case "%%%":
                printCarePackage(cellsCPnum);
                cellsCPnum--;
                break;
                //Players
            case "@@@@@":
                printPlayers(cellsPlnum);
                cellsPlnum--;
                break;
            case"#+":
                printBoard1(boardPrinted);
                break;
            case"#&":
                printBoard2(boardPrinted);
                break;
            case"#$":
                printBoard3(boardPrinted);
                break;
            case"#£":
                printBoard4(boardPrinted);
                boardPrinted++;
                break;
            case"#PLAYERB":
                createBoard1(model.getOwner());
                System.out.println();
                createBoard2(model.getOwner());
                System.out.println();
                createBoard3(model.getOwner());
                System.out.println();
                break;
            case"#KILLSHOT_T":
                createKillshotTrack();
                break;
            case"#ARMORY":
                createAmory();
                break;
            case "":
                break;
            default:
            System.out.print(s);
        }

    }
    /**
     * @return row and column coordinates given the room number
     */

    public int getRowNum(int room){
        return(int) Math.ceil((double)room/COL_NUM)-1;
    }

    public int getColNum(int room){
        return(room-((getRowNum(room))*4))-1;
    }

    /**
     * print player pawn in the right spot on map
     */
    public void printPlayers(int num){
        int room=CELLS_NUM-num+1;
        int row =getRowNum(room);
        int col =getColNum(room);
        int i= 0;
        for(VirtualPlayer p: model.getAllPlayers()){
            if(p.getRow()==row && p.getColumn()==col)
                printPawn(p.getCharacter());
            else
                System.out.print(" ");
            i++;
        }
        for(;i<5;i++){
            System.out.print(" ");
        }
        System.out.print(lastColor);
        System.out.print(lastBackground);


    }

    /**
     * print care package token in the right spot on map
     */
    public void printCarePackage(int num){
        int room=CELLS_NUM-num+1;
        int row =getRowNum(room);
        int col =getColNum(room);
        String key=row+":"+col;
        VirtualCell cell = model.getBoard().getMap().getCells().get(key);
        if(cell.isArmory()) {
            armory.add(cell.getContent());
            System.out.print(ARMORY+armory.size());
        }else
            colorizeCP(cell.getContent());

        System.out.print(lastColor);
        System.out.print(lastBackground);
    }

    /*PLAYER BOARD ZONE*/
    /**
     * print armory with weapons cost info
     */
    public void createAmory(){
        int i = 1;
        int h = 0;
        String[] temp;
        String[] temp2;
        for (String s: armory) {
            temp2=s.split(INFO_SEP);
            System.out.print(BLACK + i +"- " );
            for(String k:temp2) {
                h=0;
                temp = k.split(INNER_SEP);
                System.out.print(temp[0] + "[");
                colorizeCP(temp[1]);
                System.out.print("]");
                h=h+temp[0].length()+temp[1].length()+2;
                if(temp.length>2) {
                    colorizeCP(temp[2]);
                    h=h+temp[2].length();
                }
                System.out.print(BLACK);
                for(;h<ARMORY_SPACE;h++)
                    System.out.print(" ");
            }
            System.out.println();
            i++;
        }
    }

    /**
     * print killshottrack on top of the board with tokens or skull.
     */
    public void createKillshotTrack(){
        ArrayList<String> kill=model.getBoard().getKillshotTrack();
        int skullMax= model.getBoard().getSkull();

        String[] temp;
        int i=0;
        int h=0;
        int k=0;
        int totalSkull=skullMax;
        for (; totalSkull < 8; totalSkull++) {
            System.out.print("[  ]");
            i=i+4;
            k++;
        }
        for(String s : kill){
            if(kill.size()==1&&kill.get(0).isEmpty())
                continue;
            k++;
            if(k<9) {
                System.out.print("[");
                i++;
            }
            temp=s.split(INNER_SEP);
            for(String j:temp){
                printToken(j);
                i++;
                h++;
            }
            for (; h < 2; h++) {
                System.out.print(" ");
                i++;
            }
            h=0;
            if(k<9) {
                System.out.print("]");
                i++;
            }
        }
        for (; k < 8; k++) {
            System.out.print("[" +SKULL_T+ " ]");
            i=i+4;
        }
        for (; i < KILLSHOT_SPACE; i++) {
            System.out.print(" ");
        }
    }

    /**
     * print player board info in the right spot on map
     * Line #1 ( username and marks token )
     */
    public void printBoard1(int num){
        ArrayList<VirtualPlayer> temp = new ArrayList<>(model.getAllPlayers());
        temp.remove(model.getOwner());
        if(num >=temp.size())
            return;
        createBoard1(temp.get(num));
    }

    public void createBoard1(VirtualPlayer p) {
        int i = 0;
        //System.out.print(RESET+BOARD_LEFT);

        //UsernameSpace
        //System.out.print(WHITE+"╲");
        System.out.print(RESET+""+BLACK_BOLD+"");
        if (p.getCharacter().equals(model.getTurn().getCharacter())) {
            System.out.print(BLACK_B);
            System.out.print(WHITE_BOLD_BRIGHT);
        }else if (p.isInactive()) {
            System.out.print(YELLOW_B);
            System.out.print(WHITE_BOLD_BRIGHT);
        }else {
            System.out.print(WHITE_BACKGROUND_BRIGHT);
            if(p.getUsername().equals(TERMINATOR_NAME)) {
                System.out.print(RED_BOLD_BRIGHT);
            }
        }
        System.out.print(" " + p.getUsername() + "");


        for (i=p.getUsername().length(); i < USERNAME_SPACE; i++) {
            System.out.print(" ");
        }
        System.out.print(RESET);

        printPawn(p.getCharacter());
        //Print marks
        //System.out.print(WHITE+"╱"+"╲"+WHITE_UNDERLINED+" ╭");
        System.out.print(WHITE_UNDERLINED+"   ["+BLACK_BOLD+(model.getAllPlayers().indexOf(p)+1)+""+WHITE_UNDERLINED+"] ╭"+WHITE);

        for (String d : p.getpBoard().getMarks()) {
            printToken(d);
            i++;

        }
        for (; i < MARKS_SPACE+USERNAME_SPACE; i++) {
            System.out.print("─");
        }
        System.out.print(WHITE+"╫"+RESET);

        //print weapons#1
        weaponTemp = p.getWeapons();
        //Weapon
        printWeapon(0,p);

        //OWNER PU
        printPU(0,p);
    }
    /**
     * print player board info in the right spot on map
     * Line #2 ( Damage token )
     */
    public void printBoard2(int num){
        ArrayList<VirtualPlayer> temp = new ArrayList<>(model.getAllPlayers());
        temp.remove(model.getOwner());
        if(num >=temp.size())
            return;
        createBoard2(temp.get(num));
    }

    public void createBoard2(VirtualPlayer p) {
        int i = 0;
        System.out.print(RESET+"├");
        //UsernameSpace
        System.out.print(WHITE+"╾");
        for (String d : p.getpBoard().getDamage()) {
            if(i==2||i==5||i==10){
                System.out.print(WHITE_UNDERLINED+"╮╭"+RESET);
            }
            i++;
            printToken(d);
        }
        for (; i < DAMAGE_SPACE; i++) {
            if((i==2||i==5||i==10)){
                System.out.print(WHITE_UNDERLINED+"╮╭"+RESET);
            }
            System.out.print("─");
        }
        System.out.print(WHITE+"┴"+RESET);

        //print ammo
        if(!p.getUsername().equals(TERMINATOR_NAME)) {
            System.out.print("(");
            System.out.print(p.getpBoard().getAmmoBlue());
            colorizeCP("B");
            System.out.print(" " + p.getpBoard().getAmmoYellow());
            colorizeCP("Y");
            System.out.print(" " + p.getpBoard().getAmmoRed());
            colorizeCP("R");
            System.out.print(")╫");
        }else
            System.out.print("(-- -- --)╫");

        //Weapon
        printWeapon(1,p);

        //Owner PU
        printPU(1,p);
    }
    /**
     * print player board info in the right spot on map
     * Line #3 ( Point track and skull token )
     */
    public void printBoard3(int num){
        ArrayList<VirtualPlayer> temp = new ArrayList<>(model.getAllPlayers());
        temp.remove(model.getOwner());
        if(num >=temp.size())
            return;
        createBoard3(temp.get(num));
    }

    public void createBoard3(VirtualPlayer p) {
        int i = 0;
        int[] track=DEATH_TRACK;
        System.out.print(RESET+"├");
        System.out.print(RESET);

        if(p.getpBoard().isFlipped())
            track=DEATH_TRACK_FRENZY;
        //skulls
        for(; i<(p.getpBoard().getSkulls()); i++){
            System.out.print("["+SKULL_T+"]");
        }
        for(; i< track.length; i++){
            System.out.print("["+CYAN+track[i]+RESET+"]");
        }
        System.out.print("┼"+RESET);

        //powerup
        System.out.print("─");
        int r=0;
        for(String s: p.getPowerUps()){
            System.out.print("<");
            colorizeCP("P");
            System.out.print(">");
            r++;
        }
        for(;r<3;r++)
            System.out.print("   ");

        System.out.print("╫");

        //Weapon
        printWeapon(2,p);
        //Owner PU
        printPU(2,p);
    }
    /**
     * print player board info in the right spot on map
     * Line #4 ( Board separator)
     */
    public void printBoard4(int num){
        ArrayList<VirtualPlayer> temp = new ArrayList<>(model.getAllPlayers());
        temp.remove(model.getOwner());
        if(num == temp.size()-1){
            System.out.print(RESET+BOARD_BOTTOM+RESET);
            return;
        }
        if(num >temp.size()-1)
            return;
        System.out.print(RESET+BOARD_SEP+RESET);
    }


    public void printWeapon(int k,VirtualPlayer p){
        int i=0;
        String s="";
        String[] temp={"",""};
        if(k<weaponTemp.size()) {
            s=weaponTemp.get(k);
            temp=s.split(INNER_SEP);
            if (model.getOwner().equals(p)&&temp[1].equals("false")) {
                temp[0] = RED_B + temp[0];
                i=i-RED_B.length();
            }else if (temp[1].equals("false"))
                temp[0]=temp[0].replaceAll(".",WEAPON_CENSORED);
            i=i+temp[0].length();
        }
        for(;i<WEAPON_SPACE;i++)
            System.out.print(" ");
        System.out.print(temp[0]);
        System.out.print(WHITE+"}┤"+RESET);
    }

    public void printPU(int k,VirtualPlayer p){
        if(!model.getOwner().equals(p))
            return;
        String pu[];
        if(model.getOwner().getPowerUps().size()>k){
            pu= model.getOwner().getPowerUps().get(k).split(":");
            System.out.print("├──< "+pu[0]+" [");
            colorizeCP(pu[1]);
            System.out.print("]>");
        }
    }
    /**
     * convert Care package info into token
     */
    public void colorizeCP(String pu){
        char[] array =pu.toCharArray();
        if(pu.equals("empty")) {
            System.out.print("   ");
            return;
        }
        for(char c:array){
            switch(Character.toString(c)){
                case "R":
                    pu=RED_AMMO;
                    break;
                case "B":
                    pu=BLUE_AMMO;
                    break;
                case "Y":
                    pu=YELLOW_AMMO;
                    break;
                case "P":
                    pu=PU_AMMO;
                    break;
            }
            System.out.print(pu);

        }
    }

    /**
     * convert Character info into pawn
     */
    public void printPawn(String character){
        String pawn;
        switch (character){
            case "red":
                pawn=RED_P;
                break;
            case "blue":
                pawn=BLUE_P;
                break;
            case "yellow":
                pawn=YELLOW_P;
                break;
            case "green":
                pawn=GREEN_P;
                break;
            case "grey":
                pawn=GREY_P;
                break;
            default:
                pawn="";
        }
        System.out.print(pawn);
    }
    /**
     * convert Character info into token
     */
    public void printToken(String character){
        String pawn="";
        switch (character){
            case "red":
                pawn=RED_T;
                break;
            case "blue":
                pawn=BLUE_T;
                break;
            case "yellow":
                pawn=YELLOW_T;
                break;
            case "green":
                pawn=GREEN_T;
                break;
            case "grey":
                pawn=GREY_T;
                break;
            case "":
                pawn=" ";
                break;
        }
        System.out.print(pawn);
    }

    public VirtualModel getModel() {
        return model;
    }

    public void setModel(VirtualModel model) {
        this.model = model;
    }

    public static void main(String[] args){
        GameInitializer game = new GameInitializer();
        try {
            game.initAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CliBoard cli= new  CliBoard(game.getVmodel());
        cli.draw();

    }
}
