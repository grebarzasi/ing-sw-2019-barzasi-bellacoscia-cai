package it.polimi.ingsw.view.graphical_view;

import it.polimi.ingsw.utils.FileLoader;
import it.polimi.ingsw.view.virtual_model.VirtualModel;
import it.polimi.ingsw.view.virtual_model.VirtualPlayer;
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
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


import static it.polimi.ingsw.view.graphical_view.GUIFiles.*;

/**
 * @author Carlo Bellacoscia
 */
public class LeaderboardJavaFX extends Application {

    private double widthScreen;
    private double heightScreen;
    private double heightTitle;
    private double heightFirst;
    private double heightSecond;
    private double heightThird;
    private double heightOther;
    private FileLoader fileLoader = new FileLoader();
    private VirtualModel model;
    private ArrayList<String> players;

    /**
     * Create a leaderboard.
     *
     * @param players colors of players in the leaderboard.
     * @param model the model.
     */
    public LeaderboardJavaFX(ArrayList<String> players, VirtualModel model) {
        this.model = model;
        this.players = players;
    }

    /**
     *  Set the leaderboard's layout
     *
     * @param primaryStage the stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        widthScreen = Screen.getPrimary().getBounds().getWidth();
        heightScreen = Screen.getPrimary().getBounds().getHeight();
        heightOther = heightScreen/20;
        heightTitle = heightOther * 4;
        heightSecond = heightOther * 3;
        heightFirst = heightOther * 4;
        heightThird = heightOther * 2;

        Image back1 = new Image(fileLoader.getResource(PATH_BACK_MSG),widthScreen,heightThird,true,true);
        BackgroundImage backImg = new BackgroundImage(back1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backImg);

        TextField txt1 = new TextField();
        txt1.setEditable(false);
        txt1.setPrefSize(widthScreen/4,heightFirst/3);
        txt1.setFont(new Font(24));
        txt1.setBackground(background);

        TextField txt2 = new TextField();
        txt2.setEditable(false);
        txt2.setPrefSize(widthScreen/4,heightSecond/3);
        txt2.setFont(new Font(20));
        txt2.setBackground(background);

        TextField txt3 = new TextField();
        txt3.setEditable(false);
        txt3.setPrefSize(widthScreen/4,heightThird/3);
        txt3.setFont(new Font(18));
        txt3.setBackground(background);

        TextField txt4 = new TextField();
        txt4.setEditable(false);
        txt4.setPrefSize(widthScreen/4,heightOther /2);
        txt4.setFont(new Font(14));
        txt4.setBackground(background);

        TextField txt5 = new TextField();
        txt5.setEditable(false);
        txt5.setPrefSize(widthScreen/4,heightOther/2);
        txt5.setFont(new Font(14));
        txt5.setBackground(background);


        ImageView img1 = new ImageView(new Image(fileLoader.getResource("/images/leaderboard/first.png"),widthScreen,heightFirst,true,true));
        ImageView img2 = new ImageView(new Image(fileLoader.getResource("/images/leaderboard/second.png"),widthScreen,heightSecond,true,true));
        ImageView img3 = new ImageView(new Image(fileLoader.getResource("/images/leaderboard/third.png"),widthScreen,heightThird,true,true));
        ImageView img4 = new ImageView(new Image(fileLoader.getResource("/images/leaderboard/fourth.png"),widthScreen,heightOther,true,true));
        ImageView img5 = new ImageView(new Image(fileLoader.getResource("/images/leaderboard/fifth.png"),widthScreen,heightOther,true,true));

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

        HashMap<Integer, VirtualPlayer> all = new HashMap<>();
        for (VirtualPlayer p : model.getAllPlayers()) {
            all.put(players.indexOf(p.getCharacter()), p);
        }


        //set Title
        ImageView imgTitle = new ImageView(new Image(fileLoader.getResource(PATH_TITLE), widthScreen, heightTitle, true, true));
        grid.add(imgTitle, 0, 0);

        //set Background

            Image back = new Image(fileLoader.getResource(PATH_BACK_GAME), widthScreen + 300 , heightScreen + 300, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            grid.setBackground(new Background(backgroundImage));


        //set leaderboard
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

        switch (players.size()){
            case(5):{
                txt5.setText(all.get(4).getUsername() + " : " + all.get(4).getPoints() + " punti");
                txt5.setStyle("-fx-text-fill:" + all.get(4).getCharacter() );
                txt5.setFont(Font.font("Verdana", FontWeight.BOLD,14));
            }
            case(4):{
                txt4.setText(all.get(3).getUsername() + " : " + all.get(3).getPoints() + " punti");
                txt4.setStyle("-fx-text-fill:" + all.get(3).getCharacter() );
                txt4.setFont(Font.font("Verdana", FontWeight.BOLD,14));

            }
            case(3):{
                txt3.setText(all.get(2).getUsername() + " : " + all.get(2).getPoints() + " punti");
                txt3.setStyle("-fx-text-fill:" + all.get(2).getCharacter() );
                txt3.setFont(Font.font("Verdana", FontWeight.BOLD,18));

            }
            case(2):{
                txt2.setText(all.get(1).getUsername() + " : " + all.get(1).getPoints() + " punti");
                txt2.setStyle("-fx-text-fill:" + all.get(1).getCharacter() );
                txt2.setFont(Font.font("Verdana", FontWeight.BOLD,20));

            }
            case(1):{
                txt1.setText(all.get(0).getUsername() + " : " + all.get(0).getPoints() + " punti");
                txt1.setStyle("-fx-text-fill:" + all.get(0).getCharacter() );
                txt1.setFont(Font.font("Verdana", FontWeight.BOLD,24));

            }
            default:{

            }
        }

        grid.add(h1,0,1);
        grid.add(h2,0,2);
        grid.add(h3,0,3);
        grid.add(h4,0,4);
        grid.add(h5,0,5);


        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
