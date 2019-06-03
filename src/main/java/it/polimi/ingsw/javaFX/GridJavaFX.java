package it.polimi.ingsw.javaFX;

import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.virtual_model.VirtualLobby;
import it.polimi.ingsw.virtual_model.VirtualPlayer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Game GUI.
 *
 * @author Carlo Bellacoscia
 */
public class GridJavaFX extends Application {

    private int map = 3;
    private javafx.scene.text.Font font = new Font(20);
    private int skullMax = 8;

    private VirtualLobby lobby;

    private boolean turn = false;
    private boolean move = false;
    private boolean pick = false;
    private boolean shoot = false;

    public void setLobby(VirtualLobby lobby) {
        this.lobby = lobby;
    }

    private VirtualPlayer player = new VirtualPlayer("carlo", "yellow");


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ADRENALINA");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        double widthScreen = screenSize.getWidth();
        double heightScreen = screenSize.getHeight();

        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(0, 0, 0, 0));

        Scene scene = new Scene(grid,widthScreen,heightScreen);
        primaryStage.setScene(scene);

        /**
         * set space in grid.
         */
        double widthLateral = widthScreen/4;
        double widthCenter = widthScreen/2;
        ColumnConstraints c1 = new ColumnConstraints(widthLateral);
        ColumnConstraints c2 = new ColumnConstraints(widthCenter);
        ColumnConstraints c3 = new ColumnConstraints(widthLateral);


        double heightLateral = heightScreen/6;
        double heightCenter = heightScreen/2;
        RowConstraints r1 = new RowConstraints(heightLateral);
        RowConstraints r2 = new RowConstraints(heightCenter);
        RowConstraints r3 = new RowConstraints(heightLateral);


        grid.getColumnConstraints().addAll(c1,c2,c3);
        grid.getRowConstraints().addAll(r1,r2,r3);

        grid.setGridLinesVisible(true);

        /**
         * set title.
         */
        ImageView imgTitle = new ImageView(new Image(new FileInputStream("src/main/resources/images/title.png"),widthCenter,heightLateral,true,true));
        grid.add(imgTitle,1,0);

        /**
         * set Killshot track.
         */
        GridPane gridSkull = new GridPane();
        gridSkull.setPadding(new Insets(100, 0, 80, 0));

        Image imgTrack = new Image(new FileInputStream("src/main/resources/images/killshotrack.png"),widthLateral,heightLateral,true,true);
        BackgroundImage backgroundSkull = new BackgroundImage(imgTrack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backSkull = new Background(backgroundSkull);

        gridSkull.setBackground(backSkull);


        double widthSkull = imgTrack.getWidth()/9;
        ColumnConstraints kc1 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc2 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc3 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc4 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc5 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc6 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc7 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc8 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc9 = new ColumnConstraints(widthSkull);

        RowConstraints kr1 =new RowConstraints(imgTrack.getHeight()-25);

        gridSkull.getColumnConstraints().addAll(kc1,kc2,kc3,kc4,kc5,kc6,kc7,kc8,kc9);
        gridSkull.getRowConstraints().add(kr1);

        grid.add(gridSkull,0,0);

        /**
         * set map.
         */
        GridPane gridBoard = new GridPane();
        gridBoard.setAlignment(Pos.CENTER);
        gridBoard.setPadding(new Insets(0, 60, 0, 60));


        Image imgBoard = null;
        switch (map){
            case 1:{
                imgBoard = new Image(new FileInputStream("src/main/resources/images/1.png"),widthCenter,heightCenter,true,true);
                break;
            }
            case 2:{
                imgBoard = new Image(new FileInputStream("src/main/resources/images/2.png"),widthCenter,heightCenter,true,true);
                break;
            }
            case 3:{
                imgBoard = new Image(new FileInputStream("src/main/resources/images/3.png"),widthCenter,heightCenter,true,true);
                break;
            }
            case 4:{
                imgBoard = new Image(new FileInputStream("src/main/resources/images/4.png"),widthCenter,heightCenter,true,true);
                break;
            }
        }

        BackgroundImage backgroundMap = new BackgroundImage(imgBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background back = new Background(backgroundMap);
        gridBoard.setBackground(back);


        double widthBoard = (widthCenter-120)/4;
        double heightBoard = heightCenter/3;
        ColumnConstraints bc1 = new ColumnConstraints(widthBoard);
        ColumnConstraints bc2 = new ColumnConstraints(widthBoard);
        ColumnConstraints bc3 = new ColumnConstraints(widthBoard);
        ColumnConstraints bc4 = new ColumnConstraints(widthBoard);

        RowConstraints br1=new RowConstraints(heightBoard);
        RowConstraints br2=new RowConstraints(heightBoard);
        RowConstraints br3=new RowConstraints(heightBoard);

        gridBoard.getColumnConstraints().addAll(bc1,bc2,bc3,bc4);
        gridBoard.getRowConstraints().addAll(br1,br2,br3);


        grid.add(gridBoard,1,1);

        /**
         * set a grid in cells.
         */
        GridPane gridCell1 = new GridPane();
        GridPane gridCell2 = new GridPane();
        GridPane gridCell3 = new GridPane();
        GridPane gridCell4 = new GridPane();
        GridPane gridCell5 = new GridPane();
        GridPane gridCell6 = new GridPane();
        GridPane gridCell7 = new GridPane();
        GridPane gridCell8 = new GridPane();
        GridPane gridCell9 = new GridPane();
        GridPane gridCell10 = new GridPane();
        GridPane gridCell11 = new GridPane();
        GridPane gridCell12 = new GridPane();
        ArrayList<Button> btnCell1 = setGridCell(gridCell1,widthBoard,heightBoard);
        ArrayList<Button> btnCell2 = setGridCell(gridCell2,widthBoard,heightBoard);
        ArrayList<Button> btnCell3 = setGridCell(gridCell3,widthBoard,heightBoard);
        ArrayList<Button> btnCell4 = setGridCell(gridCell4,widthBoard,heightBoard);
        ArrayList<Button> btnCell5 = setGridCell(gridCell5,widthBoard,heightBoard);
        ArrayList<Button> btnCell6 = setGridCell(gridCell6,widthBoard,heightBoard);
        ArrayList<Button> btnCell7 = setGridCell(gridCell7,widthBoard,heightBoard);
        ArrayList<Button> btnCell8 = setGridCell(gridCell8,widthBoard,heightBoard);
        ArrayList<Button> btnCell9 = setGridCell(gridCell9,widthBoard,heightBoard);
        ArrayList<Button> btnCell10 = setGridCell(gridCell10,widthBoard,heightBoard);
        ArrayList<Button> btnCell11 = setGridCell(gridCell11,widthBoard,heightBoard);
        ArrayList<Button> btnCell12 = setGridCell(gridCell12,widthBoard,heightBoard);
        gridBoard.add(gridCell1,0,0);
        gridBoard.add(gridCell2,0,1);
        gridBoard.add(gridCell3,0,2);
        gridBoard.add(gridCell4,1,0);
        gridBoard.add(gridCell5,1,1);
        gridBoard.add(gridCell6,1,2);
        gridBoard.add(gridCell7,2,0);
        gridBoard.add(gridCell8,2,1);
        gridBoard.add(gridCell9,2,2);
        gridBoard.add(gridCell10,3,0);
        gridBoard.add(gridCell11,3,1);
        gridBoard.add(gridCell12,3,2);

        /**
         * set personal space.
         */
        GridPane gridPers = new GridPane();

        double widthPers = widthLateral;
        double heightPBoard = heightCenter/3;
        double heightPCards = heightPBoard *2;

        ColumnConstraints pc1 = new ColumnConstraints(widthPers);

        RowConstraints pr1=new RowConstraints(heightBoard);
        RowConstraints pr2=new RowConstraints(heightPCards);

        gridPers.getColumnConstraints().add(pc1);
        gridPers.getRowConstraints().addAll(pr1,pr2);

        gridPers.setGridLinesVisible(true);


        GridPane gridCards = new GridPane();

        double widthCard = widthPers/3;
        double heightCard = heightPCards/2;
        ColumnConstraints cc1 = new ColumnConstraints(widthCard);
        ColumnConstraints cc2 = new ColumnConstraints(widthCard);
        ColumnConstraints cc3 = new ColumnConstraints(widthCard);

        RowConstraints cr1=new RowConstraints(heightCard);
        RowConstraints cr2=new RowConstraints(heightCard);

        gridCards.getColumnConstraints().addAll(cc1,cc2,cc3);
        gridCards.getRowConstraints().addAll(cr1,cr2);

        gridCards.setGridLinesVisible(true);

        gridPers.add(gridCards,0,1);

        grid.add(gridPers,0,1);

        /**
         * set personal ammo
         */
        GridPane gridPAmmo = new GridPane();
        gridPAmmo.setAlignment(Pos.CENTER);

        double widthAmmo = 50;
        double heightAmmo = 20;
        ColumnConstraints ac1 = new ColumnConstraints(widthAmmo);
        ColumnConstraints ac2 = new ColumnConstraints(widthAmmo);
        ColumnConstraints ac3 = new ColumnConstraints(widthAmmo);

        RowConstraints ar1 = new RowConstraints(heightAmmo);
        RowConstraints ar2 = new RowConstraints(heightAmmo);
        RowConstraints ar3 = new RowConstraints(heightAmmo);

        gridPAmmo.getColumnConstraints().addAll(ac1,ac2,ac3);
        gridPAmmo.getRowConstraints().addAll(ar1,ar2,ar3);

        gridPAmmo.setGridLinesVisible(true);

        grid.add(gridPAmmo,0,2);



        /**
         * set other boards
         */
        GridPane gridOther = new GridPane();

        double widthOCard = widthLateral/3;
        double widthOther = widthOCard * 2;
        double heightOther = heightCenter/5;
        ColumnConstraints oc1 = new ColumnConstraints(widthOther);
        ColumnConstraints oc2 = new ColumnConstraints(widthOCard);

        RowConstraints or1 = new RowConstraints(heightOther);
        RowConstraints or2 = new RowConstraints(heightOther);
        RowConstraints or3 = new RowConstraints(heightOther);
        RowConstraints or4 = new RowConstraints(heightOther);
        RowConstraints or5 = new RowConstraints(heightOther);

        gridOther.getColumnConstraints().addAll(oc1,oc2);
        gridOther.getRowConstraints().addAll(or1,or2,or3,or4,or5);

        gridOther.setGridLinesVisible(true);

        grid.add(gridOther,2,1);
        /**
         * set deck
         */
        Image imgDeck = new Image(new FileInputStream("src/main/resources/images/powerup.png"),widthCard,widthCard,true,true);
        BackgroundImage backgroundDeck = new BackgroundImage(imgDeck, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backDeck = new Background(backgroundDeck);
        Button btnDeck = new Button();
        btnDeck.setBackground(backDeck);
        btnDeck.setPrefSize(widthCard,widthCard);
        grid.add(btnDeck,2,2);

        /**
         * set buttons
         */
        TextField msg = new TextField();
        Image msgBack = new Image(new FileInputStream("src/main/resources/images/img854.png"),300,100,true,true);
        BackgroundImage backgroundMsg = new BackgroundImage(msgBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backMsg = new Background(backgroundMsg);
        msg.setBackground(backMsg);
        msg.setAlignment(Pos.CENTER);
        msg.setText("Benvenuto Giocatore!");
        msg.setFont(font);
        msg.setEditable(false);

        Button btnMove = new Button("Muovi");
        Button btnPick = new Button("Raccogli");
        Button btnShoot = new Button("Spara");
        HBox hBtn = new HBox(30);
        hBtn.setAlignment(Pos.CENTER);
        hBtn.getChildren().add(btnMove);
        hBtn.getChildren().add(btnPick);
        hBtn.getChildren().add(btnShoot);

        VBox vmsg = new VBox(25);
        vmsg.setAlignment(Pos.CENTER);
        vmsg.getChildren().add(msg);
        vmsg.getChildren().add(hBtn);

        grid.add(vmsg,1,2);

        primaryStage.show();
    }

    public ArrayList<Button> setGridCell (GridPane grid, double width, double height){

        ArrayList<Button> res = new ArrayList<>();

        double w = width/3;
        double h = height/2;
        ColumnConstraints c1 = new ColumnConstraints(w);
        ColumnConstraints c2 = new ColumnConstraints(w);
        ColumnConstraints c3 = new ColumnConstraints(w);

        RowConstraints r1 = new RowConstraints(h);
        RowConstraints r2 = new RowConstraints(h);

        grid.getColumnConstraints().addAll(c1,c2,c3);
        grid.getRowConstraints().addAll(r1,r2);

        Button b1 = new Button();
        Button b2 = new Button();
        Button b3 = new Button();
        Button b4 = new Button();
        Button b5 = new Button();
        Button b6 = new Button();
        b1.setPrefSize(w,h);
        b2.setPrefSize(w,h);
        b3.setPrefSize(w,h);
        b4.setPrefSize(w,h);
        b5.setPrefSize(w,h);
        b6.setPrefSize(w,h);
        b1.setOpacity(0);
        b2.setOpacity(0);
        b3.setOpacity(0);
        b4.setOpacity(0);
        b5.setOpacity(0);
        b6.setOpacity(0);
        grid.add(b1,0,0);
        grid.add(b2,0,1);
        grid.add(b3,1,0);
        grid.add(b4,1,1);
        grid.add(b5,2,0);
        grid.add(b6,2,1);
        res.add(b1);
        res.add(b2);
        res.add(b3);
        res.add(b4);
        res.add(b5);
        res.add(b6);

        return res;
    }
}
