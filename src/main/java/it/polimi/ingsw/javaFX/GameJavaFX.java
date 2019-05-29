package it.polimi.ingsw.javaFX;

import it.polimi.ingsw.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameJavaFX extends Application {

    private int map = 1;
    private Player player = new Player("carlo", "red");

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
                imgBoard = /*new ImageView(*/new Image(new FileInputStream("src/main/resources/images/1.png"),720,480,true,true);
                break;
            }
            case 2:{
                imgBoard = /*new ImageView(*/new Image(new FileInputStream("src/main/resources/images/2.png"),720,480,true,true);
                break;
            }
            case 3:{
                imgBoard = /*new ImageView(*/new Image(new FileInputStream("src/main/resources/images/3.png"),720,480,true,true);
                break;
            }
            case 4:{
                imgBoard = /*new ImageView(*/new Image(new FileInputStream("src/main/resources/images/4.png"),720,480,true,true);
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
        ImageView imgPBoard = null;
        ImageView imgOther1 = null;
        ImageView imgOther2 = null;
        ImageView imgOther3 = null;
        ImageView imgOther4 = null;


        double widthPB = 350;
        double heightPB = 150;
        double dimPW = 150;
        double dimPPU = 100;

        double widthOB = 200;
        double heightOB = 100;

        ImageView imgPWe1 = new ImageView(new Image(new FileInputStream("src/main/resources/images/weapon.png"),dimPW,dimPW,true,true));
        ImageView imgPWe2 = new ImageView(new Image(new FileInputStream("src/main/resources/images/weapon.png"),dimPW,dimPW,true,true));
        ImageView imgPWe3 = new ImageView(new Image(new FileInputStream("src/main/resources/images/weapon.png"),dimPW,dimPW,true,true));

        ImageView imgPPU1 = new ImageView(new Image(new FileInputStream("src/main/resources/images/powerup.png"),dimPPU,dimPPU,true,true));
        ImageView imgPPU2 = new ImageView(new Image(new FileInputStream("src/main/resources/images/powerup.png"),dimPPU,dimPPU,true,true));
        ImageView imgPPU3 = new ImageView(new Image(new FileInputStream("src/main/resources/images/powerup.png"),dimPPU,dimPPU,true,true));

        switch (player.getCharacter()){
            case "yellow":{
                imgPBoard = new ImageView(new Image(new FileInputStream("src/main/resources/images/yellow_board.png"),widthPB,heightPB,true,true));
                imgOther1 = new ImageView(new Image(new FileInputStream("src/main/resources/images/red_board.png"),widthOB,heightOB,true,true));
                imgOther2 = new ImageView(new Image(new FileInputStream("src/main/resources/images/blue_board.png"),widthOB,heightOB,true,true));
                imgOther3 = new ImageView(new Image(new FileInputStream("src/main/resources/images/green_board.png"),widthOB,heightOB,true,true));
                imgOther4 = new ImageView(new Image(new FileInputStream("src/main/resources/images/gray_board.png"),widthOB,heightOB,true,true));

                break;
            }
            case "red":{
                imgPBoard = new ImageView(new Image(new FileInputStream("src/main/resources/images/red_board.png"),widthPB,heightPB,true,true));
                imgOther1 = new ImageView(new Image(new FileInputStream("src/main/resources/images/yellow_board.png"),widthOB,heightOB,true,true));
                imgOther2 = new ImageView(new Image(new FileInputStream("src/main/resources/images/blue_board.png"),widthOB,heightOB,true,true));
                imgOther3 = new ImageView(new Image(new FileInputStream("src/main/resources/images/green_board.png"),widthOB,heightOB,true,true));
                imgOther4 = new ImageView(new Image(new FileInputStream("src/main/resources/images/gray_board.png"),widthOB,heightOB,true,true));

                break;
            }
            case "blue":{
                imgPBoard = new ImageView(new Image(new FileInputStream("src/main/resources/images/blue_board.png"),widthPB,heightPB,true,true));
                imgOther1 = new ImageView(new Image(new FileInputStream("src/main/resources/images/yellow_board.png"),widthOB,heightOB,true,true));
                imgOther2 = new ImageView(new Image(new FileInputStream("src/main/resources/images/red_board.png"),widthOB,heightOB,true,true));
                imgOther3 = new ImageView(new Image(new FileInputStream("src/main/resources/images/green_board.png"),widthOB,heightOB,true,true));
                imgOther4 = new ImageView(new Image(new FileInputStream("src/main/resources/images/gray_board.png"),widthOB,heightOB,true,true));
                break;
            }
            case "green":{
                imgPBoard = new ImageView(new Image(new FileInputStream("src/main/resources/images/green_board.png"),widthPB,heightPB,true,true));
                imgOther1 = new ImageView(new Image(new FileInputStream("src/main/resources/images/yellow_board.png"),widthOB,heightOB,true,true));
                imgOther2 = new ImageView(new Image(new FileInputStream("src/main/resources/images/red_board.png"),widthOB,heightOB,true,true));
                imgOther3 = new ImageView(new Image(new FileInputStream("src/main/resources/images/blue_board.png"),widthOB,heightOB,true,true));
                imgOther4 = new ImageView(new Image(new FileInputStream("src/main/resources/images/gray_board.png"),widthOB,heightOB,true,true));
                break;
            }
            case "gray":{
                imgPBoard = new ImageView(new Image(new FileInputStream("src/main/resources/images/gray_board.png"),widthPB,heightPB,true,true));
                imgOther1 = new ImageView(new Image(new FileInputStream("src/main/resources/images/yellow_board.png"),widthOB,heightOB,true,true));
                imgOther2 = new ImageView(new Image(new FileInputStream("src/main/resources/images/red_board.png"),widthOB,heightOB,true,true));
                imgOther3 = new ImageView(new Image(new FileInputStream("src/main/resources/images/blue_board.png"),widthOB,heightOB,true,true));
                imgOther4 = new ImageView(new Image(new FileInputStream("src/main/resources/images/green_board.png"),widthOB,heightOB,true,true));
                break;
            }
        }

        double dimSkull = 40;
        ImageView imgSkull = new ImageView(new Image(new FileInputStream("src/main/resources/images/skull.png"),dimSkull,dimSkull,true,true));


        HBox hPWe = new HBox(20);
        hPWe.getChildren().add(imgPWe1);
        hPWe.getChildren().add(imgPWe2);
        hPWe.getChildren().add(imgPWe3);

        HBox hPPU = new HBox(45);
        hPPU.getChildren().add(imgPPU1);
        hPPU.getChildren().add(imgPPU2);
        hPPU.getChildren().add(imgPPU3);

        VBox vAmmo = addImgAmmo(30,10);
        VBox vPers = new VBox(30);
        vPers.getChildren().add(imgSkull);
        vPers.getChildren().add(imgPBoard);
        vPers.getChildren().add(hPWe);
        vPers.getChildren().add(hPPU);
        vPers.getChildren().add(vAmmo);


        HBox hWe1 = new HBox(10);
        hWe1.getChildren().add(imgOther1);
        addImgWe(hWe1);

        HBox hWe2 = new HBox(10);
        hWe2.getChildren().add(imgOther2);
        addImgWe(hWe2);

        HBox hWe3 = new HBox(10);
        hWe3.getChildren().add(imgOther3);
        addImgWe(hWe3);

        HBox hWe4 = new HBox(10);
        hWe4.getChildren().add(imgOther4);
        addImgWe(hWe4);

        ImageView imgDeck = new ImageView(new Image(new FileInputStream("src/main/resources/images/powerup.png"),dimPW,dimPW,true,true));

        VBox vOther = new VBox(20);
        vOther.getChildren().add(hWe1);
        VBox v1 = addImgAmmo(15, 5);
        vOther.getChildren().add(v1);
        vOther.getChildren().add(hWe2);
        VBox v2 = addImgAmmo(15,5);
        vOther.getChildren().add(v2);
        vOther.getChildren().add(hWe3);
        VBox v3 = addImgAmmo(15,5);
        vOther.getChildren().add(v3);
        vOther.getChildren().add(hWe4);
        VBox v4 = addImgAmmo(15,5);
        vOther.getChildren().add(v4);
        vOther.getChildren().add(imgDeck);

        Button btnMove = new Button("Muovi");
        Button btnPick = new Button("Raccogli");
        Button btnShoot = new Button("Spara");
        HBox hBtn = new HBox(30);
        hBtn.setAlignment(Pos.CENTER);
        hBtn.getChildren().add(btnMove);
        hBtn.getChildren().add(btnPick);
        hBtn.getChildren().add(btnShoot);

        TextField msg = new TextField();
        Image msgBack = new Image(new FileInputStream("src/main/resources/images/img854.png"),300,100,true,true);
        BackgroundImage backgroundMsg = new BackgroundImage(msgBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backMsg = new Background(backgroundMsg);
        msg.setBackground(backMsg);
        msg.setAlignment(Pos.CENTER);
        Font msgFont = new Font(20);
        msg.setText("Benvenuto Giocatore!");
        msg.setFont(msgFont);

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

        ImageView imgWe1 = null;
        ImageView imgWe2 = null;
        ImageView imgWe3 = null;

        try {
             imgWe1 = new ImageView(new Image(new FileInputStream("src/main/resources/images/weapon.png"),dimOPU,dimOPU,true,true));
             imgWe2 = new ImageView(new Image(new FileInputStream("src/main/resources/images/weapon.png"),dimOPU,dimOPU,true,true));
             imgWe3 = new ImageView(new Image(new FileInputStream("src/main/resources/images/weapon.png"),dimOPU,dimOPU,true,true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        h.getChildren().add(imgWe1);
        h.getChildren().add(imgWe2);
        h.getChildren().add(imgWe3);
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
}
