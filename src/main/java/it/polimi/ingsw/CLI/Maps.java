package it.polimi.ingsw.CLI;


import it.polimi.ingsw.virtual_model.VirtualCell;
import it.polimi.ingsw.virtual_model.VirtualModel;
import it.polimi.ingsw.virtual_model.VirtualPlayer;

import java.io.*;
import java.util.ArrayList;

import static it.polimi.ingsw.CLI.Color.*;


public class Maps {

    public static final String RED_P = PURPLE_BACKGROUND_BRIGHT+"Ȣ"+RESET;
    public static final String GREEN_P = GREEN_BACKGROUND_BRIGHT+"G"+RESET;
    public static final String BLUE_P =CYAN_BACKGROUND_BRIGHT+"B"+RESET;
    public static final String GRAY_P = WHITE_BACKGROUND_BRIGHT+"W"+RESET;
    public static final String YELLOW_P = YELLOW_BACKGROUND_BRIGHT+"Ÿ"+RESET;

    public static final String ARMORY = " ۩ ";
    public static final String AMMO = "ѻ";

    public static final String YELLOW_AMMO = YELLOW+AMMO+RESET;
    public static final String BLUE_AMMO = BLUE+AMMO+RESET;
    public static final String RED_AMMO = RED+AMMO+RESET;
    public static final String PU_AMMO = BLACK+"Ѻ"+RESET;

    public static final String RED_T = PURPLE_BOLD_BRIGHT+"o"+RESET;
    public static final String GREEN_T = GREEN_BOLD_BRIGHT+"o"+RESET;
    public static final String BLUE_T =CYAN_BOLD_BRIGHT+"o"+RESET;
    public static final String GRAY_T = WHITE_BOLD_BRIGHT+"o"+RESET;
    public static final String YELLOW_T = YELLOW_BOLD_BRIGHT+"o"+RESET;

    private String lastColor="";
    private String lastBackground="";

    private static final int CELLS_NUM =12;
    private static final int ROW_NUM =3;
    private static final int COL_NUM =4;
    private static final int PLAYERS_NUM =5;

    private static final int USERNAME_SPACE =10;
    private static final int MARKS_SPACE =9;
    private static final int DAMAGE_SPACE =12;
    private int cellsPlnum=12;
    private int cellsCPnum=12;
    private int boardPrinted=0;

    private VirtualModel model;

    public Maps(VirtualModel model){
        this.model=model;
    }

    public void loadFile(String name)throws IOException {
        File f=new File("src/main/resources/cli_files/" + name +".txt");
        BufferedReader in = new BufferedReader(new FileReader(f));
        String line;
        String[] splitLine;
        while((line=in.readLine())!=null){
            splitLine=line.split(";");
            for(String s : splitLine){
                plotString(s);
            }
        }
    }

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
                System.out.println();
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
                boardPrinted++;
                break;
            case "":
                break;
            default:
            System.out.print(s);
        }

    }

    public int getRowNum(int room){
        return(int) Math.ceil((double)room/COL_NUM)-1;
    }

    public int getColNum(int room){
        return(room-((getRowNum(room))*4))-1;
    }

    public void printPlayers(int num){
        int room=CELLS_NUM-num+1;
        int row =getRowNum(room);
        int col =getColNum(room);
        int i= 0;
        for(VirtualPlayer p: model.getAllPlayers()){
            if(p.getRow()==row && p.getColumn()==col)
                printPown(p.getCharacter());
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

    public void printCarePackage(int num){
        int room=CELLS_NUM-num+1;
        int row =getRowNum(room);
        int col =getColNum(room);
        String key=row+":"+col;
        VirtualCell cell = model.getBoard().getMap().getCells().get(key);
        if(cell.isArmory())
            System.out.print(ARMORY);
        else
            colorizeCP(cell.getContent());

        System.out.print(lastColor);
        System.out.print(lastBackground);
    }


    public void printBoard1(int num){
        ArrayList<VirtualPlayer> temp = new ArrayList<>(model.getAllPlayers());
        temp.remove(model.getOwner());
        if(num > (model.getAllPlayers().size()-1))
            return;
        createBoard1(temp.get(num));
    }

    public void createBoard1(VirtualPlayer p) {
        int i = 0;
        System.out.print(RESET);

        //UsernameSpace
        System.out.print(BLACK_UNDERLINED+ "| " + p.getUsername() + " ");
        for (i=p.getUsername().length(); i < USERNAME_SPACE; i++) {
            System.out.print(" ");
        }
        System.out.print(RESET);

        printPown(p.getCharacter());

        //Print marks
        System.out.print(BLACK_UNDERLINED+"  (");
        i=0;
        for (String d : p.getpBoard().getMarks()) {
            printToken(d);
            i++;
        }
        for (; i < MARKS_SPACE; i++) {
            System.out.print(" ");
        }
        System.out.print(BLACK_UNDERLINED+")  |"+RESET);
    }

    public void printBoard2(int num){
        ArrayList<VirtualPlayer> temp = new ArrayList<>(model.getAllPlayers());
        temp.remove(model.getOwner());
        if(num > (model.getAllPlayers().size()-1))
            return;
        createBoard2(temp.get(num));
    }

    public void createBoard2(VirtualPlayer p) {
        int i = 0;
        System.out.print(RESET);

        //UsernameSpace
        System.out.print(BLACK+"< ");
        for (String d : p.getpBoard().getDamage()) {
            printToken(d);
            i++;
        }
        for (; i < DAMAGE_SPACE; i++) {
            System.out.print(" ");
        }
        System.out.print(BLACK+"> "+RESET);

    }

    public void printBoard3(int num){
        ArrayList<VirtualPlayer> temp = new ArrayList<>(model.getAllPlayers());
        temp.remove(model.getOwner());
        if(num > (model.getAllPlayers().size()-1))
            return;
        createBoard3(temp.get(num));
    }

    public void createBoard3(VirtualPlayer p) {
        System.out.print(RED_UNDERLINED+""+p.getpBoard().getSkulls()+RESET);
    }



    public void colorizeCP(String pu){
        char[] array =pu.toCharArray();
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


    public void printPown(String character){
        String pown;
        switch (character){
            case "red":
                pown=RED_P;
                break;
            case "blue":
                pown=BLUE_P;
                break;
            case "yellow":
                pown=YELLOW_P;
                break;
            case "green":
                pown=GREEN_P;
                break;
            case "gray":
                pown=GRAY_P;
                break;
            default:
                pown="";
        }
        System.out.print(pown);
    }

    public void printToken(String character){
        String pown;
        switch (character){
            case "red":
                pown=RED_T;
                break;
            case "blue":
                pown=BLUE_T;
                break;
            case "yellow":
                pown=YELLOW_T;
                break;
            case "green":
                pown=GREEN_T;
                break;
            case "gray":
                pown=GRAY_T;
                break;
            default:
                pown="";
        }
        System.out.print(pown);
    }

    public static void main(String args[]){



    }
}
