package it.polimi.ingsw.view.graphical_view;

import it.polimi.ingsw.utils.FileLoader;
import it.polimi.ingsw.view.command_line_view.CliView;
import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.client.rmi.RmiClient;
import it.polimi.ingsw.connection.client.socket.SClient;
import it.polimi.ingsw.view.virtual_model.VirtualLogin;
import it.polimi.ingsw.view.virtual_model.VirtualPlayer;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;

import static it.polimi.ingsw.Color.*;
import static it.polimi.ingsw.Color.GREEN;
import static it.polimi.ingsw.Color.RED;
import static it.polimi.ingsw.view.command_line_view.CliMessages.ADRENALINA_HEAD;
import static it.polimi.ingsw.view.graphical_view.GUIFiles.*;


/**
 *
 * @author Carlo Bellacoscia
 */

public class MainView extends Application {


    private String username;
    private String color;
    private int port = 0;
    private String ip = "";
    private String connection = "Socket";
    private ConnectionTech c;
    private VirtualPlayer p;
    private VirtualLogin login;


    double widthScreen = Screen.getPrimary().getBounds().getWidth();
    double heightScreen = Screen.getPrimary().getBounds().getHeight();
    double heightBtn = heightScreen / 15;
    double heightTitle = heightBtn * 4;
    double heightCharac = heightBtn * 5;
    FileLoader fileLoader = new FileLoader();
    Button btnLobby = new Button("Lobby");

    /**
     * entry point for client side.
     */
    public static void main(String[] args){
        boolean flag=true;
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(ADRENALINA_HEAD+RESET);
        String rpl="";
        do {
            System.out.println(PURPLE_BOLD_BRIGHT+"Che interfaccia vuoi utilizzare?\n"+RESET);
            System.out.println("1- Linea di comando ("+ GREEN+"CLI"+RESET+")");
            System.out.println("2- Interfaccia grafica ("+ GREEN+"GUI"+RESET+")");
            try {
                rpl=sc.readLine();
                rpl=rpl.toLowerCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(rpl.equals("1")||rpl.equals("cli")) {
                CliView cliView = new CliView();
                cliView.start();
                flag=false;
            }else if (rpl.equals("2")||rpl.equals("gui")) {
                launch(args);
                flag=false;
            }else
                System.out.println(RED+"non disponibile, nuovamente:"+RESET);
        }while (flag);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Adrenalina");

        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(110);
        grid.setPadding(new Insets(10, 25, 0, 25));

        RowConstraints r1 = new RowConstraints(heightTitle);
        RowConstraints r2 = new RowConstraints(heightBtn);
        RowConstraints r3 = new RowConstraints(heightCharac);

        ColumnConstraints c1 = new ColumnConstraints(widthScreen - 30);

        grid.getColumnConstraints().add(c1);
        grid.getRowConstraints().addAll(r1,r2,r3);


        Scene scene = new Scene(grid,widthScreen,heightScreen);
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
        title = new Image(fileLoader.getResource(PATH_TITLE),widthScreen,heightTitle,true,true);
        titleV = new ImageView(title);
        VBox ver = new VBox();
        ver.setAlignment(Pos.TOP_CENTER);
        ver.getChildren().add(titleV);

        /**
         * set bottom characters
         */
        ImageView yellowV = new ImageView(new Image(fileLoader.getResource(PATH_YELLOW_CHARACTER), widthScreen ,heightCharac,true,true));
        ImageView redV = new ImageView(new Image(fileLoader.getResource(PATH_RED_CHARACTER), widthScreen ,heightCharac,true,true));;
        ImageView blueV = new ImageView(new Image(fileLoader.getResource(PATH_BLUE_CHARACTER), widthScreen ,heightCharac,true,true));;
        ImageView greenV = new ImageView(new Image(fileLoader.getResource(PATH_GREEN_CHARACTER), widthScreen ,heightCharac,true,true));;
        ImageView greyV = new ImageView(new Image(fileLoader.getResource(PATH_GREY_CHARACTER), widthScreen ,heightCharac,true,true));;

        HBox charList = new HBox(10);
        charList.setAlignment(Pos.BOTTOM_CENTER);
        charList.getChildren().add(yellowV);
        charList.getChildren().add(redV);
        charList.getChildren().add(blueV);
        charList.getChildren().add(greenV);
        charList.getChildren().add(greyV);


        /**
         * set buttons
         */
        Button btnRules = new Button();
        Button btnLogin = new Button("Login");
        Button btnSettings = new Button("Settings");
        Image imgLogin = new Image(fileLoader.getResource(PATH_LOGIN),widthScreen/3,heightBtn,true,true);
        btnLogin.setGraphic(new ImageView(imgLogin));
        Image imgSettings = new Image(fileLoader.getResource(PATH_SETTINGS),widthScreen/3,heightBtn,true,true);
        btnSettings.setGraphic(new ImageView(imgSettings));
        Image imgRules = new Image(fileLoader.getResource(PATH_RULES),widthScreen/3,heightBtn,true,true);
        btnRules.setGraphic(new ImageView(imgRules));

        HBox buttonList = new HBox(20);
        buttonList.setAlignment(Pos.CENTER);
        buttonList.getChildren().add(btnRules);
        buttonList.getChildren().add(btnLogin);
        buttonList.getChildren().add(btnSettings);



        VBox v = new VBox(20);
        btnLobby.setPrefSize(widthScreen/10,heightScreen);
        btnLobby.setOpacity(0);
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

        grid.add(titleV,0,0);
        grid.add(v,0,1);
        grid.add(charList,0,2);

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
            LobbyJavaFX lobby = new LobbyJavaFX(c,p,login.isReconnected());
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

                if(!ip.isEmpty()){
                    c.setIp(ip);
                }
                if(port != 0){
                    c.setPort(port);
                }
                c.run();


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

                imgY = new Image(fileLoader.getResource(PATH_YELLOW),100,100,true,true);
                buttonY.setGraphic(new ImageView(imgY));

                imgR = new Image(fileLoader.getResource(PATH_RED),100,100,true,true);
                buttonR.setGraphic(new ImageView(imgR));

                imgB = new Image(fileLoader.getResource(PATH_BLUE),100,100,true,true);
                buttonB.setGraphic(new ImageView(imgB));

                imgG = new Image(fileLoader.getResource(PATH_GREEN),100,100,true,true);
                buttonG.setGraphic(new ImageView(imgG));

                imgGr = new Image(fileLoader.getResource(PATH_GREY),100,100,true,true);
                buttonGr.setGraphic(new ImageView(imgGr));



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
            buttonGr.setOnAction(e-> color = "grey");

            btnLogin.setOnAction(e -> {

                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Accesso...");

                username = txtUsername.getText();

                login = new VirtualLogin(username,color,c);
                try {
                    p = new VirtualPlayer(username,color);
                    if(login.send()){
                        this.close();
                    }else{
                        actiontarget.setFill(Color.RED);
                        actiontarget.setText("Username o personaggio non disponibile!");

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                btnLobby.setOpacity(1);
                if(login.isReconnected())
                    btnLobby.fire();
            });

        }
    }

    /**
     * the settings window
     */
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

    /**
     * set the background to the gridpane.
     * @param grid
     */
    public  void setBackground(GridPane grid){
            Image back = new Image(fileLoader.getResource(PATH_BACK), widthScreen , heightScreen , true, true);
            BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            grid.setBackground(new Background(backgroundImage));

    }
}
