package it.polimi.ingsw.CLI;


import java.io.*;

import static it.polimi.ingsw.CLI.Color.*;

public class Maps {

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
                break;
            case"#B":
                System.out.print(BLUE);
                break;
            case"#G":
                System.out.print(GREEN);
                break;
            case"#Y":
                System.out.print(YELLOW);
                break;
            case"#W":
                System.out.print(WHITE);
                break;
            case"#P":
                System.out.print(PURPLE);
                break;

                //Background
            case"#r":
                System.out.print(RED_B);
                break;
            case"#b":
                System.out.print(BLUE_B);
                break;
            case"#g":
                System.out.print(GREEN_B);
                break;
            case"#y":
                System.out.print(YELLOW_B);
                break;
            case"#w":
                System.out.print(WHITE_B);
                break;
            case"#p":
                System.out.print(PURPLE_B);
                break;

                //RESET
            case"#z":
                System.out.print(RESET);
                break;

                //New Line
            case"#?":
                System.out.println();
                break;
                //Powerup or Armory
            case "#%":
                break;
                //Players
            case "#@":
                System.out.println("P");
                break;

            default:
                System.out.print(s);
        }

    }

    public static void main(String args[]){
        try {
            new Maps().loadFile("cli_large");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
