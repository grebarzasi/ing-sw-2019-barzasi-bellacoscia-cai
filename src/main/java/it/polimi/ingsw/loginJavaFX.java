package it.polimi.ingsw;

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

public class loginJavaFX extends Application {

    public static void main(String[] args){
        launch(args);
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
        grid.add(scenetitle, 0, 0, 2, 1);

        Label lblUsername = new Label("Username:");
        final TextField txtUsername = new TextField();
        Label lblColor = new Label("Colore:");
        final TextField txtColor = new TextField();
        Button btnLogin = new Button("Login");
        final Label lblMessage = new Label();
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnLogin);
        grid.add(hbBtn, 1, 4);
        grid.add(lblUsername,1,1);
        grid.add(txtUsername,1,2);
        grid.add(lblColor,1,3);
        grid.add(txtColor,1,4);
        grid.add(btnLogin,1,5);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btnLogin.setOnAction(e -> {

            actiontarget.setFill(Color.GREEN);
            actiontarget.setText("Accesso...");

          //  LoginBuffer user = new LoginBuffer(txtUsername.getText(),txtColor.getText());
          //  user.send();
/*
            SClient client = new SClient();
            client.setPort(1234);
            client.setUsername(txtUsername.getText());
            client.setCharacter(txtColor.getText());
            client.connect(client);
*/

        });
        primaryStage.show();

    }
}
