package it.polimi.ingsw.javaFX;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.rmi.RmiClient;
import it.polimi.ingsw.connection.socket.SClient;
import it.polimi.ingsw.virtual_model.VirtualLogin;
import it.polimi.ingsw.virtual_model.VirtualPlayer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;


/**
 *
 * @author Carlo Bellacoscia
 */

public class StartJavaFX extends Application {

    private String username;
    private String color;
    private int port = 0;
    private String ip = "";
    private String connection = "Socket";
    private ConnectionTech c;
    private VirtualPlayer p;


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        double width = primaryStage.getMaxWidth();
        double height = primaryStage.getMaxHeight();

        primaryStage.setTitle("Adrenalina");

        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(50);
        grid.setVgap(30);
        grid.setPadding(new Insets(25, 25, 0, 25));

        Scene scene = new Scene(grid,width,height);
        primaryStage.setScene(scene);

        /**
         * set background
         */
        setBackground(grid);

        /**
         * set title
         */
        Image title;
        ImageView titleV;
        title = new Image(new FileInputStream("src/main/resources/images/title.png"),1200,700,true,true);
        titleV = new ImageView(title);
        VBox ver = new VBox();
        ver.setAlignment(Pos.TOP_CENTER);
        ver.getChildren().add(titleV);

        /**
         * set bottom characters
         */
        ImageView yellowV = new ImageView(new Image(new FileInputStream("src/main/resources/images/D-struct-0R.png"), 250 ,250,true,true));
        ImageView redV = new ImageView(new Image(new FileInputStream("src/main/resources/images/violet.png"), 250 ,250,true,true));;
        ImageView blueV = new ImageView(new Image(new FileInputStream("src/main/resources/images/banshee.png"), 250 ,250,true,true));;
        ImageView greenV = new ImageView(new Image(new FileInputStream("src/main/resources/images/sprog.png"), 250 ,250,true,true));;
        ImageView grayV = new ImageView(new Image(new FileInputStream("src/main/resources/images/dozer.png"), 250 ,250,true,true));;

        HBox charList = new HBox(40);
        charList.setAlignment(Pos.BOTTOM_CENTER);
        charList.getChildren().add(yellowV);
        charList.getChildren().add(redV);
        charList.getChildren().add(blueV);
        charList.getChildren().add(greenV);
        charList.getChildren().add(grayV);


        /**
         * set buttons
         */
        Button btnRules = new Button();
        Button btnLogin = new Button("Login");
        Button btnSettings = new Button("Settings");
        Image imgLogin = new Image(new FileInputStream("src/main/resources/images/login.png"),50,50,true,true);
        btnLogin.setGraphic(new ImageView(imgLogin));
        Image imgSettings = new Image(new FileInputStream("src/main/resources/images/settings.png"),50,50,true,true);
        btnSettings.setGraphic(new ImageView(imgSettings));
        Image imgRules = new Image(new FileInputStream("src/main/resources/images/rules.jpg"),100,100,true,true);
        btnRules.setGraphic(new ImageView(imgRules));

        HBox buttonList = new HBox(20);
        buttonList.setAlignment(Pos.CENTER);
        buttonList.getChildren().add(btnRules);
        buttonList.getChildren().add(btnLogin);
        buttonList.getChildren().add(btnSettings);

        Button btnLobby = new Button("Lobby");


        VBox v = new VBox(50);
        v.setAlignment(Pos.CENTER);
        v.getChildren().add(buttonList);
        v.getChildren().add(btnLobby);


        /**
         * merge layout
         */
        VBox layout = new VBox(100);
        layout.getChildren().add(ver);
        layout.getChildren().add(v);
        layout.getChildren().add(charList);

        grid.add(layout,1,1);


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

        btnLobby.setOnAction(e->{

            LobbyJavaFX lobby = new LobbyJavaFX(c,p);
            try {
                lobby.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        });



        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    public class loginWindow extends Stage{

        public loginWindow(){

            if(connection.equals("RMI")){
                c = new RmiClient();
                c.setRmi(true);
            }else if(connection.equals("Socket")){
                c = new SClient();
                c.setRmi(false);
            }

            try {
                if(!ip.isEmpty()){
                    c.setIp(ip);
                }
                if(port != 0){
                    c.setPort(port);
                }
                c.initConnection();
            } catch (RemoteException e) {
                e.printStackTrace();
            }


            Scene scene = new Scene(new BorderPane(),700,400);
            this.setTitle("Login");
            this.setScene(scene);
            scene.setFill(Color.BLACK);


            GridPane loginGrid = new GridPane();
            loginGrid.setAlignment(Pos.CENTER);
            loginGrid.setHgap(10);
            loginGrid.setVgap(10);
            loginGrid.setPadding(new Insets(25, 25, 25, 25));

            Scene loginScene = new Scene(loginGrid, 700, 400);
            this.setScene(loginScene);

            setBackground(loginGrid);

            Text scenetitle = new Text("Benvenuto");
            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            loginGrid.add(scenetitle, 0, 1, 2, 1);

            Label lblUsername = new Label("Username:");
            final TextField txtUsername = new TextField();
            Label lblColor = new Label("Scegli un colore:");

            Image imgY;
            Button buttonY = new Button();
            Image imgR;
            Button buttonR = new Button();
            Image imgB;
            Button buttonB = new Button();
            Image imgG;
            Button buttonG = new Button();
            Image imgGr;
            Button buttonGr = new Button();
            try {
                imgY = new Image(new FileInputStream("src/main/resources/images/yellow.png"),100,100,true,true);
                buttonY.setGraphic(new ImageView(imgY));

                imgR = new Image(new FileInputStream("src/main/resources/images/red.png"),100,100,true,true);
                buttonR.setGraphic(new ImageView(imgR));

                imgB = new Image(new FileInputStream("src/main/resources/images/blue.png"),100,100,true,true);
                buttonB.setGraphic(new ImageView(imgB));

                imgG = new Image(new FileInputStream("src/main/resources/images/green.png"),100,100,true,true);
                buttonG.setGraphic(new ImageView(imgG));

                imgGr = new Image(new FileInputStream("src/main/resources/images/gray.png"),100,100,true,true);
                buttonGr.setGraphic(new ImageView(imgGr));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            HBox colorList = new HBox(10);
            colorList.getChildren().add(buttonY);
            colorList.getChildren().add(buttonB);
            colorList.getChildren().add(buttonR);
            colorList.getChildren().add(buttonG);
            colorList.getChildren().add(buttonGr);


            Button btnLogin = new Button("Login");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btnLogin);

            loginGrid.add(hbBtn, 1, 4);
            loginGrid.add(lblUsername,0,2);
            loginGrid.add(txtUsername,0,3);
            loginGrid.add(lblColor,0,4);
            loginGrid.add(colorList,0,5);
            loginGrid.add(btnLogin,0,6);

            final Text actiontarget = new Text();
            loginGrid.add(actiontarget, 0, 8);

            buttonY.setOnAction(e-> color = "yellow");
            buttonR.setOnAction(e-> color = "red");
            buttonB.setOnAction(e-> color = "blue");
            buttonG.setOnAction(e-> color = "green");
            buttonGr.setOnAction(e-> color = "gray");

            btnLogin.setOnAction(e -> {

                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Accesso...");

                username = txtUsername.getText();

                VirtualLogin login = new VirtualLogin(username,color,c);
                try {
                    p = new VirtualPlayer(username,color,false);
                    if(login.send()){
                        this.close();
                    }else{
                        actiontarget.setFill(Color.RED);
                        actiontarget.setText("Username o personaggio non disponibile!");

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            });

        }
    }

    public class settingWindow extends Stage{
        public settingWindow(){

            Scene theScene = new Scene(new BorderPane(),400,300);
            this.setScene(theScene);

            GridPane settingsGrid = new GridPane();
            settingsGrid.setAlignment(Pos.CENTER);
            settingsGrid.setHgap(10);
            settingsGrid.setVgap(10);
            settingsGrid.setPadding(new Insets(10, 10, 10, 10));

            Scene scene = new Scene(settingsGrid, 400, 300);
            this.setScene(scene);

            setBackground(settingsGrid);

            Label lblPort = new Label("Porta:");
            final TextField txtPort= new TextField();
            Button btnPort = new Button("Aggiorna");
            Label lblIP = new Label("IP:");
            final TextField txtIP = new TextField();
            Button btnIP = new Button("Aggiorna");
            Label lblConnection = new Label("Connessione:");

            ObservableList<String> comboItems = FXCollections.observableArrayList("Socket", "RMI");
            ComboBox connBox = new ComboBox(comboItems);



            settingsGrid.add(lblPort,1,1);
            settingsGrid.add(txtPort,1,2);
            settingsGrid.add(btnPort,2,2);
            settingsGrid.add(lblIP,1,3);
            settingsGrid.add(txtIP,1,4);
            settingsGrid.add(btnIP,2,4);
            settingsGrid.add(lblConnection,1,5);

            settingsGrid.add(connBox,2,5);

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

    public  void setBackground(GridPane grid){
        try {
            Image back = new Image(new FileInputStream("src/main/resources/images/background.jpg"), 2000, 1200, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            grid.setBackground(new Background(backgroundImage));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
