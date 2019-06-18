package it.polimi.ingsw.javaFX;

import it.polimi.ingsw.virtual_model.VirtualPlayer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


import static it.polimi.ingsw.javaFX.GUIFiles.*;

public class LeaderboardJavaFX extends Application {

    private double widthScreen;
    private double heightScreen;
    private double heightTitle;
    private double heightFirst;
    private double heightSecond;
    private double heightThird;
    private double heightOther;


    @Override
    public void start(Stage primaryStage) throws Exception {

        widthScreen = Screen.getPrimary().getBounds().getWidth();
        heightScreen = Screen.getPrimary().getBounds().getHeight();
        heightOther = heightScreen/20;
        heightTitle = heightOther * 4;
        heightSecond = heightOther * 3;
        heightFirst = heightOther * 4;
        heightThird = heightOther * 2;

        TextField txt1 = new TextField();
        txt1.setEditable(false);
        txt1.setPrefSize(widthScreen/4,heightFirst/3);
        txt1.setFont(new Font(24));
        TextField txt2 = new TextField();
        txt2.setEditable(false);
        txt2.setPrefSize(widthScreen/4,heightSecond/3);
        txt2.setFont(new Font(20));
        TextField txt3 = new TextField();
        txt3.setEditable(false);
        txt3.setPrefSize(widthScreen/4,heightThird/3);
        txt3.setFont(new Font(18));
        TextField txt4 = new TextField();
        txt4.setEditable(false);
        txt4.setPrefSize(widthScreen/4,heightOther);
        txt4.setFont(new Font(14));
        TextField txt5 = new TextField();
        txt5.setEditable(false);
        txt5.setPrefSize(widthScreen/4,heightOther);
        txt5.setFont(new Font(14));

        ImageView img1 = new ImageView(new Image(new FileInputStream("src/main/resources/images/leaderboard/first.png"),widthScreen,heightFirst,true,true));
        ImageView img2 = new ImageView(new Image(new FileInputStream("src/main/resources/images/leaderboard/second.png"),widthScreen,heightSecond,true,true));
        ImageView img3 = new ImageView(new Image(new FileInputStream("src/main/resources/images/leaderboard/third.png"),widthScreen,heightThird,true,true));
        ImageView img4 = new ImageView(new Image(new FileInputStream("src/main/resources/images/leaderboard/fourth.png"),widthScreen,heightOther,true,true));
        ImageView img5 = new ImageView(new Image(new FileInputStream("src/main/resources/images/leaderboard/fifth.png"),widthScreen,heightOther,true,true));


        // HARDCODED TEST
        VirtualPlayer p1 = new VirtualPlayer("Mimmo","yellow");
        p1.setPoints(40);
        VirtualPlayer p2 = new VirtualPlayer("Woz","blue");
        p2.setPoints(39);
        VirtualPlayer p3 = new VirtualPlayer("Gandalf","green");
        p3.setPoints(38);
        VirtualPlayer p4 = new VirtualPlayer("Piton","red");
        p4.setPoints(37);
        VirtualPlayer p5 = new VirtualPlayer("AcquaMan","grey");
        p5.setPoints(36);

        ArrayList<VirtualPlayer> allPlayers = new ArrayList<>();
        allPlayers.add(p1);
        allPlayers.add(p2);
        allPlayers.add(p3);
        allPlayers.add(p4);
        allPlayers.add(p5);



        primaryStage.setTitle("Classifica");

        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(0, 0, 0, 0));

        Scene scene = new Scene(grid, widthScreen, heightScreen);
        primaryStage.setScene(scene);

        RowConstraints r1 = new RowConstraints(heightTitle);
        RowConstraints r2 = new RowConstraints(heightFirst);
        RowConstraints r3 = new RowConstraints(heightSecond);
        RowConstraints r4 = new RowConstraints(heightThird);
        RowConstraints r5 = new RowConstraints(heightOther);
        RowConstraints r6 = new RowConstraints(heightOther);

        ColumnConstraints c = new ColumnConstraints(widthScreen);

        grid.getRowConstraints().addAll(r1,r2,r3,r4,r5,r6);
        grid.getColumnConstraints().add(c);

        /**
         * set Title
         */
        ImageView imgTitle = new ImageView(new Image(new FileInputStream(PATH_TITLE), widthScreen, heightTitle, true, true));
        grid.add(imgTitle, 0, 0);

        /**
         * set Background
         */
        try {
            Image back = new Image(new FileInputStream(PATH_BACK_GAME), widthScreen , heightScreen -50, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            grid.setBackground(new Background(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * set leaderboard
         */
        HBox h1 = new HBox(20);
        HBox h2 = new HBox(20);
        HBox h3 = new HBox(20);
        HBox h4 = new HBox(20);
        HBox h5 = new HBox(20);
        h1.getChildren().add(img1);
        h1.getChildren().add(txt1);
        h2.getChildren().add(img2);
        h2.getChildren().add(txt2);
        h3.getChildren().add(img3);
        h3.getChildren().add(txt3);
        h4.getChildren().add(img4);
        h4.getChildren().add(txt4);
        h5.getChildren().add(img5);
        h5.getChildren().add(txt5);
        h1.setAlignment(Pos.CENTER);
        h2.setAlignment(Pos.CENTER);
        h3.setAlignment(Pos.CENTER);
        h4.setAlignment(Pos.CENTER);
        h5.setAlignment(Pos.CENTER);

        switch (allPlayers.size()){
            case(5):{
                txt5.setText(allPlayers.get(4).getUsername());
            }
            case(4):{
                txt4.setText(allPlayers.get(3).getUsername());
            }
            case(3):{
                txt3.setText(allPlayers.get(2).getUsername());
            }
            case(2):{
                txt2.setText(allPlayers.get(1).getUsername());
            }
            case(1):{
                txt1.setText(allPlayers.get(0).getUsername());
            }
        }

        grid.add(h1,0,1);
        grid.add(h2,0,2);
        grid.add(h3,0,3);
        grid.add(h4,0,4);
        grid.add(h5,0,5);


        primaryStage.show();
    }
}
