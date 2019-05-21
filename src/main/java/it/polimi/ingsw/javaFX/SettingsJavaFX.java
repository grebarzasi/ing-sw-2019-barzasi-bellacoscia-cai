package it.polimi.ingsw.javaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SettingsJavaFX extends Application {
    String text = "Socket";

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Login");

        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        Button btnBack = new Button("Indietro");
        Label lblPort = new Label("Porta:");
        final TextField txtPort= new TextField();
        Button btnPort = new Button("Aggiorna");
        Label lblIP = new Label("IP:");
        final TextField txtIP = new TextField();
        Button btnIP = new Button("Aggiorna");
        Label lblConnection = new Label("Connessione:");
        Label lblConnected = new Label(text);
        Button btnSocket = new Button("Socket");
        Button btnRMI = new Button("RMI");

        grid.add(btnBack,0,0);
        grid.add(lblPort,1,1);
        grid.add(txtPort,1,2);
        grid.add(btnPort,2,2);
        grid.add(lblIP,1,3);
        grid.add(txtIP,1,4);
        grid.add(btnIP,2,4);
        grid.add(lblConnection,1,5);
        grid.add(lblConnected,2,5);
        grid.add(btnSocket,1,6);
        grid.add(btnRMI,2,6);

        btnBack.setOnAction(e->{
            StartJavaFX start = new StartJavaFX();
            try {
                start.start(primaryStage);
            }catch (Exception a){
                a.printStackTrace();
            }
        });

        btnPort.setOnAction(e->{

            int port;
            port = Integer.parseInt(txtPort.getText());

        });

        btnIP.setOnAction(e->{

            String ip;
            ip = txtIP.getText();
        });

        btnSocket.setOnAction(e->{
            text = "Socket";
        });

        btnRMI.setOnAction(e->{
            text = "RMI";
        });

        primaryStage.show();


    }
}
