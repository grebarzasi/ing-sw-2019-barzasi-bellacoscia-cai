package it.polimi.ingsw.javaFX;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.virtual_model.VirtualLobby;
import it.polimi.ingsw.virtual_model.VirtualPlayer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/*

CONTORNO ROSSO!!

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.RED);
        borderGlow.setHeight(50);
        borderGlow.setWidth(50);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        imgOther1.setEffect(borderGlow);

CONTORNO ROSSO!!



NASCONDERE CELLE!!

        btn00.setOpacity(0.5);
        btn01.setOpacity(0.5);
        btn02.setOpacity(0.5);

NASCONDERE CELLE!!

*/

public class GameJavaFX extends Application {

    private int map = 3;
    private Font font = new Font(20);
    private int skullMax = 8;

    private VirtualLobby lobby;

    private boolean turn = false;
    private boolean move = false;
    private boolean pick = false;
    private boolean shoot = false;

    public void setLobby(VirtualLobby lobby) {
        this.lobby = lobby;
    }

    private VirtualPlayer player = new VirtualPlayer("carlo", "yellow");

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ADRENALINA");

        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(0, 0, 0, 0));

        Scene scene = new Scene(grid,2190,1920);
        primaryStage.setScene(scene);


        /**
         * set background.
         */
        try {
            Image back = new Image(new FileInputStream("src/main/resources/images/game_background.jpg"), 2190, 1920, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            grid.setBackground(new Background(backgroundImage));
        }catch (IOException e){
            e.printStackTrace();
        }

        /**
         * set title.
         */
        ImageView imgTitle = new ImageView(new Image(new FileInputStream("src/main/resources/images/title.png"),720,480,true,true));

        /**
         * set the board.
         */
        GridPane gridBoard = new GridPane();
        gridBoard.setAlignment(Pos.CENTER);
        gridBoard.setHgap(0);
        gridBoard.setVgap(0);
        gridBoard.setPadding(new Insets(0, 0, 0, 0));

        Image imgBoard = null;
        switch (map){
            case 1:{
                imgBoard = new Image(new FileInputStream("src/main/resources/images/1.png"),720,480,true,true);
                break;
            }
            case 2:{
                imgBoard = new Image(new FileInputStream("src/main/resources/images/2.png"),720,480,true,true);
                break;
            }
            case 3:{
                imgBoard = new Image(new FileInputStream("src/main/resources/images/3.png"),720,480,true,true);
                break;
            }
            case 4:{
                imgBoard = new Image(new FileInputStream("src/main/resources/images/4.png"),720,480,true,true);
                break;
            }
        }
        BackgroundImage backgroundMap = new BackgroundImage(imgBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background back = new Background(backgroundMap);
        gridBoard.setBackground(back);

        Button btn00 = new Button();
        Button btn01 = new Button();
        Button btn02 = new Button();
        Button btn10 = new Button();
        Button btn11 = new Button();
        Button btn12 = new Button();
        Button btn20 = new Button();
        Button btn21 = new Button();
        Button btn22 = new Button();
        Button btn30= new Button();
        Button btn31 = new Button();
        Button btn32= new Button();
        gridBoard.add(btn00,0,0);
        gridBoard.add(btn01,0,1);
        gridBoard.add(btn02,0,2);
        gridBoard.add(btn10,1,0);
        gridBoard.add(btn11,1,1);
        gridBoard.add(btn12,1,2);
        gridBoard.add(btn20,2,0);
        gridBoard.add(btn21,2,1);
        gridBoard.add(btn22,2,2);
        gridBoard.add(btn30,3,0);
        gridBoard.add(btn31,3,1);
        gridBoard.add(btn32,3,2);
        btn00.setPrefSize(170,170);
        btn01.setPrefSize(170,170);
        btn02.setPrefSize(170,170);
        btn10.setPrefSize(170,170);
        btn11.setPrefSize(170,170);
        btn12.setPrefSize(170,170);
        btn20.setPrefSize(170,170);
        btn21.setPrefSize(170,170);
        btn22.setPrefSize(170,170);
        btn30.setPrefSize(170,170);
        btn31.setPrefSize(170,170);
        btn32.setPrefSize(170,170);
        transparent(btn00);
        transparent(btn01);
        transparent(btn02);
        transparent(btn10);
        transparent(btn11);
        transparent(btn12);
        transparent(btn20);
        transparent(btn21);
        transparent(btn22);
        transparent(btn30);
        transparent(btn31);
        transparent(btn32);


        /**
         * set personal space.
         */
        Image imgPBoard = null;


        double widthPB = 350;
        double heightPB = 100;
        double dimPW = 150;
        double dimPPU = 100;


        Image imgPWe = new Image(new FileInputStream("src/main/resources/images/weapon.png"),dimPW,dimPW,true,true);
        Image imgPPU = new Image(new FileInputStream("src/main/resources/images/powerup.png"),dimPPU,dimPPU,true,true);

        Button btnPWe1 = new Button();
        Button btnPWe2 = new Button();
        Button btnPWe3 = new Button();

        Button btnPPu1 = new Button();
        Button btnPPu2 = new Button();
        Button btnPPu3 = new Button();

        BackgroundImage backgroundPWe = new BackgroundImage(imgPWe, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backPWe = new Background(backgroundPWe);
        BackgroundImage backgroundPPu = new BackgroundImage(imgPPU, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backPPu = new Background(backgroundPPu);

        btnPWe1.setBackground(backPWe);
        btnPWe2.setBackground(backPWe);
        btnPWe3.setBackground(backPWe);
        btnPWe1.setPrefSize(100,150);
        btnPWe2.setPrefSize(100,150);
        btnPWe3.setPrefSize(100,150);

        btnPPu1.setBackground(backPPu);
        btnPPu2.setBackground(backPPu);
        btnPPu3.setBackground(backPPu);
        btnPPu1.setPrefSize(85,100);
        btnPPu2.setPrefSize(85,100);
        btnPPu3.setPrefSize(85,100);

        switch (player.getCharacter()){
            case "yellow":{
                imgPBoard = new Image(new FileInputStream("src/main/resources/images/yellow_board.png"),widthPB-20,heightPB-20,true,true);
                break;
            }
            case "red":{
                imgPBoard = new Image(new FileInputStream("src/main/resources/images/red_board.png"),widthPB-20,heightPB-20,true,true);
                break;
            }
            case "blue":{
                imgPBoard = new Image(new FileInputStream("src/main/resources/images/blue_board.png"),widthPB-20,heightPB-20,true,true);
                break;
            }
            case "green":{
                imgPBoard = new Image(new FileInputStream("src/main/resources/images/green_board.png"),widthPB-20,heightPB-20,true,true);
                break;
            }
            case "gray":{
                imgPBoard = new Image(new FileInputStream("src/main/resources/images/gray_board.png"),widthPB-20,heightPB-20,true,true);
                break;
            }
        }

        BackgroundImage backgroundPB = new BackgroundImage(imgPBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backPB = new Background(backgroundPB);
        Button btnPB = new Button();
        btnPB.setPrefSize(widthPB,heightPB);
        btnPB.setBackground(backPB);

        HBox hPWe = new HBox(20);
        hPWe.getChildren().add(btnPWe1);
        hPWe.getChildren().add(btnPWe2);
        hPWe.getChildren().add(btnPWe3);

        HBox hPPU = new HBox(45);
        hPPU.getChildren().add(btnPPu1);
        hPPU.getChildren().add(btnPPu2);
        hPPU.getChildren().add(btnPPu3);

        VBox vAmmo = addImgAmmo(30,10);



        /**
         * set Killshot track.
         */
        Image imgTrack = new Image(new FileInputStream("src/main/resources/images/killshotrack.png"),300,100,true,true);
        BackgroundImage backgroundSkull = new BackgroundImage(imgTrack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backSkull = new Background(backgroundSkull);
        Button btnTrack = new Button();
        btnTrack.setPrefSize(300,100);
        btnTrack.setBackground(backSkull);


        /**
         * merge personal space
         */
        VBox vPers = new VBox(30);
        vPers.getChildren().add(btnTrack);
        vPers.getChildren().add(btnPB);
        vPers.getChildren().add(hPWe);
        vPers.getChildren().add(hPPU);
        vPers.getChildren().add(vAmmo);

        /**
         * set other players board
         */
        Image imgDeck = new Image(new FileInputStream("src/main/resources/images/powerup.png"),dimPW,dimPW,true,true);
        BackgroundImage backgroundDeck = new BackgroundImage(imgDeck, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backDeck = new Background(backgroundDeck);
        Button btnDeck = new Button();
        btnDeck.setBackground(backDeck);
        btnDeck.setPrefSize(dimPW,dimPW);
        VBox vOther = setOtherBoard(player);
        vOther.getChildren().add(btnDeck);


        /**
         * set game buttons
         */
        TextField msg = new TextField();
        Image msgBack = new Image(new FileInputStream("src/main/resources/images/img854.png"),300,100,true,true);
        BackgroundImage backgroundMsg = new BackgroundImage(msgBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backMsg = new Background(backgroundMsg);
        msg.setBackground(backMsg);
        msg.setAlignment(Pos.CENTER);
        msg.setText("Benvenuto Giocatore!");
        msg.setFont(font);
        msg.setEditable(false);

        Button btnMove = new Button("Muovi");
        Button btnPick = new Button("Raccogli");
        Button btnShoot = new Button("Spara");
        HBox hBtn = new HBox(30);
        hBtn.setAlignment(Pos.CENTER);
        hBtn.getChildren().add(btnMove);
        hBtn.getChildren().add(btnPick);
        hBtn.getChildren().add(btnShoot);


        /**
         * merge layout.
         */
        VBox v = new VBox(25);
        v.getChildren().add(imgTitle);
        v.getChildren().add(gridBoard);
        v.getChildren().add(msg);
        v.getChildren().add(hBtn);

        HBox layout = new HBox(20);
        layout.getChildren().add(vPers);
        layout.getChildren().add(v);
        layout.getChildren().add(vOther);

        grid.add(layout,1,1);

        primaryStage.show();

        btn00.setOnAction(e->{
            System.out.println("ok");
        });
    }


    public void transparent(Button btn){
        btn.setBorder(null);
        btn.setOpacity(0);
    }

    public void addImgWe(HBox h){

        double dimOPU = 50;

        Image imgWe = null;

        try {
             imgWe = new Image(new FileInputStream("src/main/resources/images/weapon.png"),dimOPU,dimOPU,true,true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BackgroundImage backgroundOWe = new BackgroundImage(imgWe, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backWe = new Background(backgroundOWe);

        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        btn1.setBackground(backWe);
        btn2.setBackground(backWe);
        btn3.setBackground(backWe);
        btn1.setPrefSize(50,50);
        btn2.setPrefSize(50,50);
        btn3.setPrefSize(50,50);

        h.getChildren().add(btn1);
        h.getChildren().add(btn2);
        h.getChildren().add(btn3);
    }

    public VBox addImgAmmo(double dim, int spacing){

        double dimOA = dim;

        ImageView imgAR = null;
        ImageView imgAB = null;
        ImageView imgAY = null;

        try {
            imgAR = new ImageView(new Image(new FileInputStream("src/main/resources/images/red_ammo.png"),dimOA,dimOA,true,true));
            imgAB = new ImageView(new Image(new FileInputStream("src/main/resources/images/blue_ammo.png"),dimOA,dimOA,true,true));
            imgAY = new ImageView(new Image(new FileInputStream("src/main/resources/images/yellow_ammo.png"),dimOA,dimOA,true,true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        VBox v = new VBox(spacing);
        v.getChildren().add(imgAR);
        v.getChildren().add(imgAB);
        v.getChildren().add(imgAY);

        return v;
    }

    public Image setBoard(String color, double width, double height){

        double widthOB = width;
        double heightOB = height;

        Image img = null;

        try {
            switch (color) {
                case "yellow": {
                    img = new Image(new FileInputStream("src/main/resources/images/yellow_board.png"), widthOB, heightOB, true, true);
                    break;
                }
                case "red": {
                    img = new Image(new FileInputStream("src/main/resources/images/red_board.png"), widthOB, heightOB, true, true);
                    break;
                }
                case "blue": {
                    img = new Image(new FileInputStream("src/main/resources/images/blue_board.png"), widthOB, heightOB, true, true);
                    break;
                }
                case "green": {
                    img = new Image(new FileInputStream("src/main/resources/images/green_board.png"), widthOB, heightOB, true, true);
                    break;
                }
                case "gray": {
                    img = new Image(new FileInputStream("src/main/resources/images/gray_board.png"), widthOB, heightOB, true, true);
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return img;
    }

    public VBox setOtherBoard(VirtualPlayer player){

        Image imgOther1;
        Image imgOther2;
        Image imgOther3;
        Image imgOther4;
        Image imgOther5;

        double widthOB = 200;
        double heightOB = 100;
        double resize = 50;

        VBox v = new VBox(15);

        HBox h1 = new HBox(5);
        HBox h2 = new HBox(5);
        HBox h3 = new HBox(5);
        HBox h4 = new HBox(5);
        HBox h5 = new HBox(5);

        Button btnOther1 = new Button();
        Button btnOther2 = new Button();
        Button btnOther3 = new Button();
        Button btnOther4 = new Button();
        Button btnOther5 = new Button();


        ArrayList<VirtualPlayer> players = new ArrayList<>();
        players.add(new VirtualPlayer("gre","blue"));
        players.add(new VirtualPlayer("theo","red"));

        for (VirtualPlayer p : players ) {
            if(!p.equals(player)){
                switch (p.getCharacter()) {
                    case "yellow": {
                        imgOther1 = setBoard(p.getCharacter(), widthOB, heightOB);
                        setButtonBack(btnOther1,imgOther1);
                        btnOther1.setPrefSize(widthOB,heightOB-resize);
                        h1.getChildren().add(btnOther1);
                        addImgWe(h1);
                        v.getChildren().add(h1);
                        v.getChildren().add(addImgAmmo(15,5));
                        break;
                    }
                    case "red": {
                        imgOther2 = setBoard(p.getCharacter(), widthOB, heightOB);
                        setButtonBack(btnOther2,imgOther2);
                        btnOther2.setPrefSize(widthOB,heightOB-resize);
                        h2.getChildren().add(btnOther2);
                        addImgWe(h2);
                        v.getChildren().add(h2);
                        v.getChildren().add(addImgAmmo(15,5));
                        break;
                    }
                    case "blue": {
                        imgOther3 = setBoard(p.getCharacter(), widthOB, heightOB);
                        setButtonBack(btnOther3,imgOther3);
                        btnOther3.setPrefSize(widthOB,heightOB-resize);
                        h3.getChildren().add(btnOther3);
                        addImgWe(h3);
                        v.getChildren().add(h3);
                        v.getChildren().add(addImgAmmo(15,5));
                        break;
                    }
                    case "green": {
                        imgOther4 = setBoard(p.getCharacter(), widthOB, heightOB);
                        setButtonBack(btnOther4,imgOther4);
                        btnOther4.setPrefSize(widthOB,heightOB-resize);
                        h4.getChildren().add(btnOther4);
                        addImgWe(h4);
                        v.getChildren().add(h4);
                        v.getChildren().add(addImgAmmo(15,5));
                        break;
                    }
                    case "gray": {
                        imgOther5 = setBoard(p.getCharacter(), widthOB, heightOB);
                        setButtonBack(btnOther5,imgOther5);
                        btnOther5.setPrefSize(widthOB,heightOB-resize);
                        h5.getChildren().add(btnOther5);
                        addImgWe(h5);
                        v.getChildren().add(h5);
                        v.getChildren().add(addImgAmmo(15,5));
                        break;
                    }
                }
            }
        }
        return v;
    }

    public void setButtonBack(Button btn,Image img ){
        BackgroundImage background = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background back = new Background(background);
        btn.setBackground(back);
    }
}
