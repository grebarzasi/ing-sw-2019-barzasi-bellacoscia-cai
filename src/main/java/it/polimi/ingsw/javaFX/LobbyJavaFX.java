package it.polimi.ingsw.javaFX;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.virtual_model.VirtualLobby;
import it.polimi.ingsw.virtual_model.VirtualPlayer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Carlo Bellacoscia
 */
public class LobbyJavaFX extends Application {

    private boolean terminator = false;
    private boolean frenzy = false;
    private int map;
    private int skull;
    private ConnectionTech conn;
    private VirtualPlayer owner;

    private VirtualLobby lobby;
    private ArrayList<VirtualPlayer> joinedPlayers = new ArrayList<>();

    private Stage primaryStage;

    public LobbyJavaFX(ConnectionTech conn, VirtualPlayer owner) {
        this.conn = conn;
        this.owner = owner;
        this.lobby = new VirtualLobby(conn, owner);
    }

    public VirtualPlayer getOwner() {
        return owner;
    }

    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;


            primaryStage.setTitle("Lobby");

            Group root = new Group();
            Scene theScene = new Scene(root);
            primaryStage.setScene(theScene);

            GridPane lobbyGrid = new GridPane();
            lobbyGrid.setAlignment(Pos.CENTER);
            lobbyGrid.setHgap(40);
            lobbyGrid.setVgap(20);
            lobbyGrid.setPadding(new Insets(25, 25, 0, 25));

            Scene scene = new Scene(lobbyGrid, 900, 900);
            primaryStage.setScene(scene);

            Font font = new Font("TimesRoman", 20);
            Font titleFont = new Font("TimesRoman", 36);

            Text title = new Text("Lobby");
            title.setFont(titleFont);

            /**
             * set background
             */
            Image back = new Image(new FileInputStream("src/main/resources/images/background.jpg"), 2000, 1200, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            lobbyGrid.setBackground(new Background(backgroundImage));

            /**
             * skulls choise
             */
            javafx.scene.control.Label lblSkulls = new javafx.scene.control.Label("Numero di teschi:");
            lblSkulls.setFont(font);
            ObservableList<String> skuItems = FXCollections.observableArrayList("5", "6", "7", "8");
            ComboBox skuBox = new ComboBox(skuItems);
            HBox skuH = new HBox(20);
            skuH.getChildren().add(lblSkulls);
            skuH.getChildren().add(skuBox);

            /**
             * terminator choise
             */
            javafx.scene.control.Label lblTerminator = new javafx.scene.control.Label("Aggiungere Terminator?");
            lblTerminator.setFont(font);
            ObservableList<String> terItems = FXCollections.observableArrayList("SI", "NO");
            ComboBox terBox = new ComboBox(terItems);
            HBox terH = new HBox(20);
            terH.getChildren().add(lblTerminator);
            terH.getChildren().add(terBox);

            /**
             * frenzy choise
             */
            javafx.scene.control.Label lblFrenzy = new javafx.scene.control.Label("Aggiungere Frenesia Finale?");
            lblFrenzy.setFont(font);
            ObservableList<String> freItems = FXCollections.observableArrayList("SI", "NO");
            ComboBox freBox = new ComboBox(freItems);
            HBox freH = new HBox(20);
            freH.getChildren().add(lblFrenzy);
            freH.getChildren().add(freBox);

            /**
             * map Buttons
             */
            Label lblMap = new Label("Scegli una mappa:");
            lblMap.setFont(font);
            Button map1 = new Button();
            javafx.scene.image.Image img1 = new Image(new FileInputStream("src/main/resources/images/1.png"), 150, 150, true, true);
            map1.setGraphic(new ImageView(img1));
            Button map2 = new Button();
            javafx.scene.image.Image img2 = new Image(new FileInputStream("src/main/resources/images/2.png"), 150, 150, true, true);
            map2.setGraphic(new ImageView(img2));
            Button map3 = new Button();
            javafx.scene.image.Image img3 = new Image(new FileInputStream("src/main/resources/images/3.png"), 150, 150, true, true);
            map3.setGraphic(new ImageView(img3));
            Button map4 = new Button();
            javafx.scene.image.Image img4 = new Image(new FileInputStream("src/main/resources/images/4.png"), 150, 150, true, true);
            map4.setGraphic(new ImageView(img4));
            HBox mapBox = new HBox(50);
            mapBox.setAlignment(Pos.CENTER);
            mapBox.getChildren().add(map1);
            mapBox.getChildren().add(map2);
            mapBox.getChildren().add(map3);
            mapBox.getChildren().add(map4);

            /**
             * player connected
             */

            joinedPlayers = lobby.getNewPlayersList();

/*

            Label lblPlayer = new Label("Player connessi:");
            lblPlayer.setFont(font);
            HBox playerBox = new HBox();
            HBox usernameBox = new HBox();
            for (VirtualPlayer p : joinedPlayers) {
                switch (p.getCharacter()) {
                    case "yellow": {
                        ImageView yellowV = new ImageView(new Image(new FileInputStream("src/main/resources/images/D-struct-0R.png"), 150, 150, true, true));
                        playerBox.getChildren().add(yellowV);
                        break;
                    }
                    case "red": {
                        ImageView redV = new ImageView(new Image(new FileInputStream("src/main/resources/images/violet.png"), 150, 150, true, true));
                        playerBox.getChildren().add(redV);
                        break;
                    }
                    case "blue": {
                        ImageView blueV = new ImageView(new Image(new FileInputStream("src/main/resources/images/banshee.png"), 150, 150, true, true));
                        playerBox.getChildren().add(blueV);
                        break;
                    }
                    case "green": {
                        ImageView greenV = new ImageView(new Image(new FileInputStream("src/main/resources/images/sprog.png"), 150, 150, true, true));
                        playerBox.getChildren().add(greenV);
                        break;
                    }
                    case "gray": {
                        ImageView grayV = new ImageView(new Image(new FileInputStream("src/main/resources/images/dozer.png"), 150, 150, true, true));
                        playerBox.getChildren().add(grayV);
                        break;
                    }
                }
                TextField txtUsername = new TextField(p.getUsername());
                txtUsername.setPrefSize(150, 30);
                txtUsername.setFont(font);
                usernameBox.getChildren().add(txtUsername);
            }

*/

            Button btnStart = new Button("PRONTO");
            btnStart.setAlignment(Pos.CENTER);
            btnStart.setFont(font);

            Button btnBack = new Button("Indietro");

            /**
             * merge layout
             */

            lobbyGrid.add(btnBack, 0, 0);
            lobbyGrid.add(title, 1, 1);
            lobbyGrid.add(skuH, 1, 2);
            lobbyGrid.add(terH, 1, 3);
            lobbyGrid.add(freH, 1, 4);
            lobbyGrid.add(lblMap, 1, 5);
            lobbyGrid.add(mapBox, 1, 6);
            /*

            lobbyGrid.add(lblPlayer, 1, 7);
            lobbyGrid.add(playerBox, 1, 8);
            lobbyGrid.add(usernameBox, 1, 9);

             */
            lobbyGrid.add(btnStart, 1, 10);

            ImageView load = null;
            try {
                load = new ImageView(new Image(new FileInputStream("src/main/resources/images/loading.png"), 70, 70, true, true));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(3000), load);
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(360);
            rotateTransition.setCycleCount(Timeline.INDEFINITE);
            rotateTransition.setInterpolator(Interpolator.LINEAR);
            ImageView finalLoad = load;
            lobbyGrid.add(finalLoad, 2, 10);


            /**
             * buttons action
             */

            skuBox.setOnAction(e -> {
                skull = Integer.parseInt(skuBox.getValue().toString());
            });

            btnBack.setOnAction(e -> {
                StartJavaFX start = new StartJavaFX();
                try {
                    start.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            terBox.setOnAction(e -> {
                if (terBox.getValue().toString().equals("SI")) {
                    terminator = true;
                } else {
                    terminator = false;
                }
            });

            freBox.setOnAction(e -> {
                if (freBox.getValue().toString().equals("SI")) {
                    frenzy = true;
                } else {
                    frenzy = false;
                }
            });

            map1.setOnAction(e -> map = 1);
            map2.setOnAction(e -> map = 2);
            map3.setOnAction(e -> map = 3);
            map4.setOnAction(e -> map = 4);

            btnStart.setOnAction(e -> {

                lobby.setKillPref(skull);
                lobby.setTerminatorPref(terminator);
                lobby.setFinalFrenzyPref(frenzy);
                lobby.setMapPref(map);


                try {
                    lobby.sendPref();
                    btnStart.setVisible(false);
                    rotateTransition.play();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                while(!lobby.isGameStarted()){
                    try {
                        lobby.waitUpdate();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                try {
                    GameJavaFX game = new GameJavaFX();
                    game.setLobby(lobby);
                    game.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            });

            primaryStage.setMaximized(true);
            primaryStage.show();

    }
}
