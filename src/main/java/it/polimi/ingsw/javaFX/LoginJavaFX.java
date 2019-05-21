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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginJavaFX extends Application {

    String username;
    String color;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

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
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Benvenuto");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 1, 2, 1);

        Button btnBack = new Button("Indietro");
        Label lblUsername = new Label("Username:");
        final TextField txtUsername = new TextField();
        Label lblColor = new Label("Colore:");
        final TextField txtColor = new TextField();
        Button btnLogin = new Button("Login");
        final Label lblMessage = new Label();
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnLogin);

        grid.add(btnBack,0,0);
        grid.add(hbBtn, 1, 4);
        grid.add(lblUsername,0,2);
        grid.add(txtUsername,0,3);
        grid.add(lblColor,0,4);
        grid.add(txtColor,0,5);
        grid.add(btnLogin,0,6);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 7);

        btnBack.setOnAction(e->{
            StartJavaFX start = new StartJavaFX();
            try {
                start.start(primaryStage);
            }catch (Exception a){
                a.printStackTrace();
            }
        });

        btnLogin.setOnAction(e -> {

            actiontarget.setFill(Color.GREEN);
            actiontarget.setText("Accesso...");

            username = txtUsername.getText();
            color = txtColor.getText();
        });
        primaryStage.show();

    }
}
