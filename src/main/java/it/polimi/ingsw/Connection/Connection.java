package it.polimi.ingsw.Connection;

import java.util.Scanner;

public class Connection {

    protected int port;
    protected String username;
    protected String character;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void acquirePort() {
        if(port == 0) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Insert port:");
            do {
                port = sc.nextInt();
                if (port <= 1023 || port > 49151) {
                    System.out.println("Not available port, insert another port:");
                }
            } while (port <= 1023 || port > 49151);
        }
    }

    public void acquireUsername(){
        if(username == null) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Insert username");
            username = sc.nextLine();
        }

    }

    public void acquireCharacter(){
        if(character == null) {
            Scanner sc = new Scanner(System.in);

            do {
                System.out.println("Insert color");
                character = sc.nextLine();
                if (!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray")) {
                    System.out.println("Not available color, insert another color:");
                }
            } while (!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray"));
        }
    }
}
