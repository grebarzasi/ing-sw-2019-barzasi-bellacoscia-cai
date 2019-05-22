package it.polimi.ingsw.javaFX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;

/**
 *
 * @author Carlo Bellacoscia
 */

public class StartJavaFX extends Application {

    private String username;
    private String color;
    private int port;
    private String ip;
    private String connection;


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage origin = primaryStage;

        primaryStage.setTitle("Adrenalina");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theScene.setFill(Color.BLACK);
        primaryStage.setScene(theScene);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(50);
        grid.setVgap(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid,1045,720);
        primaryStage.setScene(scene);
        scene.setFill(Color.BLACK);

        Image back = new Image(new FileInputStream("src/main/resources/images/launcher.jpg"),1080,720,true,true);
        BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        grid.setBackground(new Background(backgroundImage));

        Button btnRules = new Button("Rules");
        Button btnLogin = new Button("Login");
        Button btnSettings = new Button("Settings");

        grid.add(btnRules,1,6);
        grid.add(btnLogin,2,6);
        grid.add(btnSettings,3,6);


        btnLogin.setOnAction(e -> {

            try {
                loginWindow lw = new loginWindow();
                lw.show();
            }catch (Exception a){
                a.printStackTrace();
            }

        });

        btnSettings.setOnAction(e -> {

            try {
                settingWindow sw = new settingWindow();
                sw.show();
            }catch (Exception a){
                a.printStackTrace();
            }

        });



        primaryStage.show();

    }

    public class loginWindow extends Stage{
        public loginWindow(){

            Scene scene = new Scene(new BorderPane(),200,100);
            this.setTitle("Login");
            this.setScene(scene);

            GridPane loginGrid = new GridPane();
            loginGrid.setAlignment(Pos.CENTER);
            loginGrid.setHgap(10);
            loginGrid.setVgap(10);
            loginGrid.setPadding(new Insets(25, 25, 25, 25));

            Scene loginScene = new Scene(loginGrid, 300, 275);
            this.setScene(loginScene);

            Text scenetitle = new Text("Benvenuto");
            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            loginGrid.add(scenetitle, 0, 1, 2, 1);

            Label lblUsername = new Label("Username:");
            final TextField txtUsername = new TextField();
            Label lblColor = new Label("Colore:");
            final TextField txtColor = new TextField();
            Button btnLogin = new Button("Login");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btnLogin);

            loginGrid.add(hbBtn, 1, 4);
            loginGrid.add(lblUsername,0,2);
            loginGrid.add(txtUsername,0,3);
            loginGrid.add(lblColor,0,4);
            loginGrid.add(txtColor,0,5);
            loginGrid.add(btnLogin,0,6);

            final Text actiontarget = new Text();
            loginGrid.add(actiontarget, 0, 8);

            btnLogin.setOnAction(e -> {

                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Accesso...");

                username = txtUsername.getText();
                color = txtColor.getText();

            });

        }
    }

    public class settingWindow extends Stage{
        public settingWindow(){

            Scene theScene = new Scene(new BorderPane(),400,300);
            this.setScene(theScene);

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(10, 10, 10, 10));

            Scene scene = new Scene(grid, 400, 300);
            this.setScene(scene);

            Label lblPort = new Label("Porta:");
            final TextField txtPort= new TextField();
            Button btnPort = new Button("Aggiorna");
            Label lblIP = new Label("IP:");
            final TextField txtIP = new TextField();
            Button btnIP = new Button("Aggiorna");
            Label lblConnection = new Label("Connessione:");

            ObservableList<String> comboItems = FXCollections.observableArrayList("Socket", "RMI");
            ComboBox connBox = new ComboBox(comboItems);



            grid.add(lblPort,1,1);
            grid.add(txtPort,1,2);
            grid.add(btnPort,2,2);
            grid.add(lblIP,1,3);
            grid.add(txtIP,1,4);
            grid.add(btnIP,2,4);
            grid.add(lblConnection,1,5);

            grid.add(connBox,2,5);

            btnPort.setOnAction(e->{

                port = Integer.parseInt(txtPort.getText());

            });

            btnIP.setOnAction(e->{

                ip = txtIP.getText();
            });

            connBox.setOnAction(e->{
                connection = connBox.getValue().toString();
            });
        }
    }
}
