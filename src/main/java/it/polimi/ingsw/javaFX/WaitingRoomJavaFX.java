package it.polimi.ingsw.javaFX;

import it.polimi.ingsw.virtual_model.VirtualLobby;
import it.polimi.ingsw.virtual_model.VirtualPlayer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class WaitingRoomJavaFX extends Application {

    VirtualLobby lobby;

    private Font font = new Font("TimesRoman", 20);


    public WaitingRoomJavaFX(VirtualLobby lobby){
        this.lobby = lobby;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        while(!lobby.isGameStarted()) {
            primaryStage.setTitle("Sala d'attesa");

            Group root = new Group();
            Scene theScene = new Scene(root);
            primaryStage.setScene(theScene);

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(25, 25, 0, 25));

            Scene scene = new Scene(grid, 2100, 1080);
            primaryStage.setScene(scene);

            Image back = new Image(new FileInputStream("src/main/resources/images/background.jpg"), 2000, 1200, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            grid.setBackground(new Background(backgroundImage));

            Label lblPlayer = new Label("Player connessi:");
            lblPlayer.setFont(font);
            HBox playerBox = new HBox();
            HBox usernameBox = new HBox();
            for (VirtualPlayer p : lobby.getNewPlayersList()) {
                try {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TextField txtUsername = new TextField(p.getUsername());
                txtUsername.setPrefSize(150, 30);
                txtUsername.setFont(font);
                txtUsername.setEditable(false);
                usernameBox.getChildren().add(txtUsername);

                VBox v = new VBox();
                v.setAlignment(Pos.CENTER);
                v.getChildren().add(lblPlayer);
                v.getChildren().add(playerBox);
                v.getChildren().add(usernameBox);

                grid.add(v,1,1);
            }

            primaryStage.setMaximized(true);
            primaryStage.show();


            lobby.waitUpdate();


        }

        GameJavaFX game = new GameJavaFX();
        game.setLobby(lobby);
        game.start(primaryStage);
    }
}

