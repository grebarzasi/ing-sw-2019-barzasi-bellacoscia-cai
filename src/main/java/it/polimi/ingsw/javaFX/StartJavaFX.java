package it.polimi.ingsw.javaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class StartJavaFX extends Application {


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Adrenalina");

        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        Scene scene = new Scene(grid,1080,720);
        primaryStage.setScene(scene);


        Image intro = new Image(new FileInputStream("src/main/resources/images/launcher.jpg"),1080,720,true,true);
        BackgroundImage backgroundImage = new BackgroundImage(intro, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        grid.setBackground(new Background(backgroundImage));

        Button btnRules = new Button("Rules");
        Button btnLogin = new Button("Login");
        Button btnSettings = new Button("Settings");

        grid.add(btnRules,1,1);
        grid.add(btnLogin,2,1);
        grid.add(btnSettings,3,1);


        btnLogin.setOnAction(e -> {

            LoginJavaFX login = new LoginJavaFX();
            try {
                login.start(primaryStage);
            }catch (Exception a){
                a.printStackTrace();
            }

        });

        btnSettings.setOnAction(e -> {

            SettingsJavaFX settings = new SettingsJavaFX();
            try {
                settings.start(primaryStage);
            }catch (Exception a){
                a.printStackTrace();
            }

        });



        primaryStage.show();

    }
}
