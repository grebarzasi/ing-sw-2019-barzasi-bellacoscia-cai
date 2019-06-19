package it.polimi.ingsw.javaFX;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import it.polimi.ingsw.Figure;
import it.polimi.ingsw.virtual_model.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.chrono.HijrahDate;
import java.util.ArrayList;
import java.util.Set;

import static it.polimi.ingsw.connection.ConnMessage.*;
import static it.polimi.ingsw.javaFX.GUIFiles.*;
import static it.polimi.ingsw.CLI.CliMessages.*;
import static it.polimi.ingsw.javaFX.GUIFiles.CHOOSE_DIRECTION;
import static java.lang.Thread.sleep;

/*

CONTORNO ROSSO!!

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(CliColor.RED);
        borderGlow.setHeight(50);
        borderGlow.setWidth(50);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        imgOther1.setEffect(borderGlow);

CONTORNO ROSSO!!



POSIZIONE: STRING -> INDICE DI BTNCELL

        String pos;
        int temp1 = Integer.parseInt(pos.split(":")[0]);
        int temp2 = Integer.parseInt(pos.split(":")[1]);
        int i = temp1*4 + temp2;

POSIZIONE: STRING -> INDICE DI BTNCELL



NASCONDERE CELLE!!

        btn00.setOpacity(0.5);
        btn01.setOpacity(0.5);
        btn02.setOpacity(0.5);

NASCONDERE CELLE!!



RISALIRE ALLA CELLA!!

        Double d = 3.2;
        Double u;
        int c;
        u = (d - d.intValue())*10;
        c = d.intValue();

        System.out.println("row: " + u.intValue() + " column: " + c);

RISALIRE ALLA CELLA!!


*/

/**
 * Game GUI.
 *
 * @author Carlo Bellacoscia
 */
public class GameJavaFX extends Application implements ViewClient {

    private Stage primaryStage;

    private boolean start;
    private boolean move;
    private boolean pick;
    private boolean shoot;
    private boolean reset;


    private VirtualModel model;

    private VirtualGame game;

    private UpdateParser parser;

    private Font font;
    private Image emptyCell;
    private Image backBtn;

    private String action;

    private ArrayList<ArrayList<Button>> btnCell;
    private TextField msg;
    private TextField pointsField;
    private GridPane gridSkull;
    private String decision;

    private Button btnMove;
    private Button btnPick;
    private Button btnShoot;
    private Button btnEnd;
    private Button btnCancel;
    private Button btnDeck;
    private Button btnTerminator;
    private Button btnPowerUp;
    private Button btnEmpty;
    private Background btnEmptyBG;




    private GridPane gridPBoard;

    private Button btnPwe1;
    private Button btnPwe2;
    private Button btnPwe3;
    private ArrayList<Button> we;
    private Button btnPpu1;
    private Button btnPpu2;
    private Button btnPpu3;
    private ArrayList<Button> pu;

    private GridPane gridPAmmo;

    private GridPane gridOtherBoard1;
    private GridPane gridOtherBoard2;
    private GridPane gridOtherBoard3;
    private GridPane gridOtherBoard4;
    private GridPane gridOtherBoard5;
    private ArrayList<GridPane> gridOtherBoards;

    private ArrayList<Button> weOther1;
    private ArrayList<Button> weOther2;
    private ArrayList<Button> weOther3;
    private ArrayList<Button> weOther4;
    private ArrayList<Button> weOther5;
    private ArrayList<ArrayList<Button>> otherWe;

    private GridPane gridOtherAmmo1;
    private GridPane gridOtherAmmo2;
    private GridPane gridOtherAmmo3;
    private GridPane gridOtherAmmo4;
    private GridPane gridOtherAmmo5;
    private ArrayList<GridPane> gridOtherAmmo;

    private double widthScreen;
    private double widthLateral;
    private double widthCenter;
    private double widthSkull;
    private double widthBoard;
    private double widthPers;
    private double widthCard;
    private double widthOCard;
    private double widthOther;
    private double widthOtherWeapon;
    private double heightScreen;
    private double heightLateral;
    private double heightCenter;
    private double heightBoard;
    private double heightPBoard;
    private double heightPCards;
    private double heightCard;
    private double heightOther;
    private double heightOtherWeapon;
    private double heightOtherAmmo;
    private double heightOtherBoard;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public GameJavaFX(VirtualModel model) {

        this.model = model;

        start = false;
        move = false;
        pick = false;
        shoot = false;
        reset = false;

        parser = new UpdateParser(model);

        game = new VirtualGame();
        font = new Font(20);
        try {
            emptyCell = new Image(new FileInputStream(PATH_EMPTY_DAMAGE),widthCenter,heightCenter,true,true);
            backBtn = new Image(new FileInputStream(PATH_BACK_POINTS), 100, 100, true, true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        action = "";
        decision = "";

        btnCell = new ArrayList<>();
        msg = new TextField();
        pointsField = new TextField();
        gridSkull = new GridPane();
        btnMove = new Button("Muovi");
        btnPick = new Button("Raccogli");
        btnShoot = new Button("Spara");
        btnEnd = new Button("Termina il turno");
        btnCancel = new Button("Annulla");
        btnTerminator = new Button("Terminator");
        btnPowerUp = new Button("Power-up");
        btnDeck = new Button();
        gridPBoard = new GridPane();

        btnPwe1 = new Button();
        btnPwe2 = new Button();
        btnPwe3 = new Button();
        btnPpu1 = new Button();
        btnPpu2 = new Button();
        btnPpu3 = new Button();
        pu = new ArrayList<>();
        we = new ArrayList<>();
        gridPAmmo = new GridPane();
        gridOtherBoard1 = new GridPane();
        gridOtherBoard2 = new GridPane();
        gridOtherBoard3 = new GridPane();
        gridOtherBoard4 = new GridPane();
        gridOtherBoard5 = new GridPane();
        gridOtherBoards = new ArrayList<>();
        weOther1 = new ArrayList<>();
        weOther2 = new ArrayList<>();
        weOther3 = new ArrayList<>();
        weOther4 = new ArrayList<>();
        weOther5 = new ArrayList<>();
        otherWe = new ArrayList<>();
        gridOtherAmmo1 = new GridPane();
        gridOtherAmmo2 = new GridPane();
        gridOtherAmmo3 = new GridPane();
        gridOtherAmmo4 = new GridPane();
        gridOtherAmmo5 = new GridPane();
        gridOtherAmmo = new ArrayList<>();

        widthScreen = Screen.getPrimary().getBounds().getWidth();
        widthLateral = widthScreen / 4;
        widthCenter = widthScreen / 2;
        widthSkull = widthLateral / 9;
        widthBoard = (widthCenter - 120) / 4;
        widthPers = widthLateral;
        widthCard = widthPers / 3;
        widthOCard = widthLateral / 3;
        widthOther = widthOCard * 2;
        widthOtherWeapon = widthCard / 3;
        heightScreen = Screen.getPrimary().getBounds().getHeight();
        heightLateral = heightScreen / 6;
        heightCenter = heightScreen / 2;
        heightBoard = heightCenter / 3;
        heightPBoard = heightCenter / 3;
        heightPCards = heightPBoard * 2;
        heightCard = heightPCards / 2;
        heightOther = heightCenter / 5;
        heightOtherWeapon = heightCenter / 5;
        heightOtherAmmo = heightOther / 5;
        heightOtherBoard = heightOtherAmmo * 4;

        Image img = null;
        try {
            img = new Image(new FileInputStream(PATH_EMPTY_CELL), 100, 100, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BackgroundImage background = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        btnEmptyBG = new Background(background);

    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        this.primaryStage = primaryStage;

        primaryStage.setTitle("ADRENALINA");

        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(10, 20, 100, 10));

        Scene scene = new Scene(grid, widthScreen, heightScreen);
        primaryStage.setScene(scene);

        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> grid.setPrefWidth((double) newSceneWidth));
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> grid.setPrefHeight((double) newSceneHeight));

        /**
         * set Background.
         */
        try {
            Image back = new Image(new FileInputStream(PATH_BACK_GAME), widthScreen , heightScreen , true, true);
            BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            grid.setBackground(new Background(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * set space in grid.
         */
        ColumnConstraints c1 = new ColumnConstraints(widthLateral);
        ColumnConstraints c2 = new ColumnConstraints(widthCenter);
        ColumnConstraints c3 = new ColumnConstraints(widthLateral);
        c1.setPercentWidth(25);
        c2.setPercentWidth(50);
        c3.setPercentWidth(25);


        RowConstraints r1 = new RowConstraints(heightLateral);
        RowConstraints r2 = new RowConstraints(heightCenter);
        RowConstraints r3 = new RowConstraints(heightLateral);
        r1.setPercentHeight(25);
        r2.setPercentHeight(50);
        r3.setPercentHeight(25);


        grid.getColumnConstraints().addAll(c1, c2, c3);
        grid.getRowConstraints().addAll(r1, r2, r3);

        /**
         * set title.
         */
        ImageView imgTitle = new ImageView(new Image(new FileInputStream(PATH_TITLE), widthCenter, heightLateral, true, true));
        grid.add(imgTitle, 1, 0);

        /**
         * set points.
         */
        /*
        Image pointsBack = new Image(new FileInputStream(PATH_BACK_POINTS), 100, 100, true, true);
        BackgroundImage backgroundPoints = new BackgroundImage(pointsBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backPoints = new Background(backgroundPoints);

        pointsField.setBackground(backPoints);
        pointsField.setPrefSize(widthLateral / 3, heightLateral / 3);
        pointsField.setText("Punti: " + model.getOwner().getPoints());
        pointsField.setAlignment(Pos.CENTER);
        pointsField.setEditable(false);

        DropShadow pointsBorder = new DropShadow();
        pointsBorder.setColor(Color.WHITE);
        pointsBorder.setHeight(50);
        pointsBorder.setWidth(50);
        pointsBorder.setOffsetX(0f);
        pointsBorder.setOffsetY(0f);
        pointsField.setEffect(pointsBorder);

        pointsField.setStyle("-fx-text-fill: grey; -fx-font-size: 20px;");

        grid.add(pointsField, 2, 0);

         */

        /**
         * set Killshot track.
         */
        gridSkull.setPadding(new Insets(100, 0, 80, 0));

        Image imgTrack = new Image(new FileInputStream(PATH_TRACK), widthLateral, heightLateral, true, true);
        BackgroundImage backgroundSkull = new BackgroundImage(imgTrack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backSkull = new Background(backgroundSkull);

        gridSkull.setBackground(backSkull);

        ColumnConstraints kc1 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc2 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc3 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc4 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc5 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc6 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc7 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc8 = new ColumnConstraints(widthSkull);
        ColumnConstraints kc9 = new ColumnConstraints(widthSkull);

        RowConstraints kr1 = new RowConstraints(imgTrack.getHeight() - 25);

        gridSkull.getColumnConstraints().addAll(kc1, kc2, kc3, kc4, kc5, kc6, kc7, kc8, kc9);
        gridSkull.getRowConstraints().add(kr1);


        grid.add(gridSkull, 0, 0);

        /**
         * set map.
         */
        GridPane gridBoard = new GridPane();
        gridBoard.setAlignment(Pos.CENTER);
        gridBoard.setPadding(new Insets(0, 60, 0, 60));


        Image imgBoard = null;
        switch (model.getBoard().getMap().getName()) {
            case ("medium1"): {
                imgBoard = new Image(new FileInputStream(PATH_MEDIUM1_MAP), widthCenter, heightCenter, true, true);
                break;
            }
            case ("small"): {
                imgBoard = new Image(new FileInputStream(PATH_SMALL_MAP), widthCenter, heightCenter, true, true);
                break;
            }
            case ("large"): {
                imgBoard = new Image(new FileInputStream(PATH_LARGE_MAP), widthCenter, heightCenter, true, true);
                break;
            }
            case ("medium2"): {
                imgBoard = new Image(new FileInputStream(PATH_MEDIUM2_MAP), widthCenter, heightCenter, true, true);
                break;
            }
        }

        BackgroundImage backgroundMap = new BackgroundImage(imgBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background back = new Background(backgroundMap);
        gridBoard.setBackground(back);


        ColumnConstraints bc1 = new ColumnConstraints(widthBoard);
        ColumnConstraints bc2 = new ColumnConstraints(widthBoard);
        ColumnConstraints bc3 = new ColumnConstraints(widthBoard);
        ColumnConstraints bc4 = new ColumnConstraints(widthBoard);

        RowConstraints br1 = new RowConstraints(heightBoard);
        RowConstraints br2 = new RowConstraints(heightBoard);
        RowConstraints br3 = new RowConstraints(heightBoard);

        gridBoard.getColumnConstraints().addAll(bc1, bc2, bc3, bc4);
        gridBoard.getRowConstraints().addAll(br1, br2, br3);

        grid.add(gridBoard, 1, 1);

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
        ArrayList<Button> btnCell1 = setGridCell(gridCell1, widthBoard, heightBoard);
        ArrayList<Button> btnCell2 = setGridCell(gridCell2, widthBoard, heightBoard);
        ArrayList<Button> btnCell3 = setGridCell(gridCell3, widthBoard, heightBoard);
        ArrayList<Button> btnCell4 = setGridCell(gridCell4, widthBoard, heightBoard);
        ArrayList<Button> btnCell5 = setGridCell(gridCell5, widthBoard, heightBoard);
        ArrayList<Button> btnCell6 = setGridCell(gridCell6, widthBoard, heightBoard);
        ArrayList<Button> btnCell7 = setGridCell(gridCell7, widthBoard, heightBoard);
        ArrayList<Button> btnCell8 = setGridCell(gridCell8, widthBoard, heightBoard);
        ArrayList<Button> btnCell9 = setGridCell(gridCell9, widthBoard, heightBoard);
        ArrayList<Button> btnCell10 = setGridCell(gridCell10, widthBoard, heightBoard);
        ArrayList<Button> btnCell11 = setGridCell(gridCell11, widthBoard, heightBoard);
        ArrayList<Button> btnCell12 = setGridCell(gridCell12, widthBoard, heightBoard);
        gridBoard.add(gridCell1, 0, 0);
        gridBoard.add(gridCell2, 1, 0);
        gridBoard.add(gridCell3, 2, 0);
        gridBoard.add(gridCell4, 3, 0);
        gridBoard.add(gridCell5, 0, 1);
        gridBoard.add(gridCell6, 1, 1);
        gridBoard.add(gridCell7, 2, 1);
        gridBoard.add(gridCell8, 3, 1);
        gridBoard.add(gridCell9, 0, 2);
        gridBoard.add(gridCell10, 1, 2);
        gridBoard.add(gridCell11, 2, 2);
        gridBoard.add(gridCell12, 3, 2);


        btnCell.add(btnCell1);
        btnCell.add(btnCell2);
        btnCell.add(btnCell3);
        btnCell.add(btnCell4);
        btnCell.add(btnCell5);
        btnCell.add(btnCell6);
        btnCell.add(btnCell7);
        btnCell.add(btnCell8);
        btnCell.add(btnCell9);
        btnCell.add(btnCell10);
        btnCell.add(btnCell11);
        btnCell.add(btnCell12);

        /**
         * set personal space.
         */
        GridPane gridPers = new GridPane();

        ColumnConstraints pc1 = new ColumnConstraints(widthPers);

        RowConstraints pr1 = new RowConstraints(heightBoard);
        RowConstraints pr2 = new RowConstraints(heightPCards);

        gridPers.getColumnConstraints().add(pc1);
        gridPers.getRowConstraints().addAll(pr1, pr2);

        Image imgPBoard = null;

        switch (model.getOwner().getCharacter()) {
            case "yellow": {
                imgPBoard = new Image(new FileInputStream(PATH_YELLOW_BOARD), widthPers, heightPBoard, true, true);
                break;
            }
            case "red": {
                imgPBoard = new Image(new FileInputStream(PATH_RED_BOARD), widthPers, heightPBoard, true, true);
                break;
            }
            case "blue": {
                imgPBoard = new Image(new FileInputStream(PATH_BLUE_BOARD), widthPers, heightPBoard, true, true);
                break;
            }
            case "green": {
                imgPBoard = new Image(new FileInputStream(PATH_GREEN_BOARD), widthPers, heightPBoard, true, true);
                break;
            }
            case "grey": {
                imgPBoard = new Image(new FileInputStream(PATH_GREY_BOARD), widthPers, heightPBoard, true, true);
                break;
            }
        }

        BackgroundImage backgroundPB = new BackgroundImage(imgPBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backPB = new Background(backgroundPB);

        gridPBoard.setBackground(backPB);

        setCellBoard(gridPBoard, widthPers, heightPBoard - 50);

        gridPers.add(gridPBoard, 0, 0);

        GridPane gridCards = new GridPane();


        ColumnConstraints cc1 = new ColumnConstraints(widthCard);
        ColumnConstraints cc2 = new ColumnConstraints(widthCard);
        ColumnConstraints cc3 = new ColumnConstraints(widthCard);

        RowConstraints cr1 = new RowConstraints(heightCard);
        RowConstraints cr2 = new RowConstraints(heightCard);

        gridCards.getColumnConstraints().addAll(cc1, cc2, cc3);
        gridCards.getRowConstraints().addAll(cr1, cr2);


        Image imgWe = null;
        Image imgPPU = null;

        btnPwe1.setPrefSize(widthCard, heightCard);
        btnPwe2.setPrefSize(widthCard, heightCard);
        btnPwe3.setPrefSize(widthCard, heightCard);
        btnPpu1.setPrefSize(widthCard, heightCard);
        btnPpu2.setPrefSize(widthCard, heightCard);
        btnPpu3.setPrefSize(widthCard, heightCard);

        we.add(btnPwe1);
        we.add(btnPwe2);
        we.add(btnPwe3);

        hideBtn(btnPpu1, 0);
        hideBtn(btnPpu2, 0);
        hideBtn(btnPpu3, 0);

        pu.add(btnPpu1);
        pu.add(btnPpu2);
        pu.add(btnPpu3);

        gridCards.add(btnPwe1, 0, 0);
        gridCards.add(btnPwe2, 1, 0);
        gridCards.add(btnPwe3, 2, 0);
        gridCards.add(btnPpu1, 0, 1);
        gridCards.add(btnPpu2, 1, 1);
        gridCards.add(btnPpu3, 2, 1);

        gridPers.add(gridCards, 0, 1);

        grid.add(gridPers, 0, 1);

        /**
         * set personal ammo
         */
        gridPAmmo.setAlignment(Pos.CENTER);

        double widthAmmo = 50;
        double heightAmmo = 20;
        ColumnConstraints ac1 = new ColumnConstraints(widthAmmo);
        ColumnConstraints ac2 = new ColumnConstraints(widthAmmo);
        ColumnConstraints ac3 = new ColumnConstraints(widthAmmo);

        RowConstraints ar1 = new RowConstraints(heightAmmo);
        RowConstraints ar2 = new RowConstraints(heightAmmo);
        RowConstraints ar3 = new RowConstraints(heightAmmo);

        gridPAmmo.getColumnConstraints().addAll(ac1, ac2, ac3);
        gridPAmmo.getRowConstraints().addAll(ar1, ar2, ar3);

        grid.add(gridPAmmo, 0, 2);


        /**
         * set other boards
         */
        gridOtherBoards.add(gridOtherBoard1);
        gridOtherBoards.add(gridOtherBoard2);
        gridOtherBoards.add(gridOtherBoard3);
        gridOtherBoards.add(gridOtherBoard4);
        gridOtherBoards.add(gridOtherBoard5);

        gridOtherAmmo.add(gridOtherAmmo1);
        gridOtherAmmo.add(gridOtherAmmo2);
        gridOtherAmmo.add(gridOtherAmmo3);
        gridOtherAmmo.add(gridOtherAmmo4);
        gridOtherAmmo.add(gridOtherAmmo5);

        GridPane gridOther = new GridPane();
        gridOther.setAlignment(Pos.CENTER);

        ColumnConstraints oc1 = new ColumnConstraints(widthOther);
        ColumnConstraints oc2 = new ColumnConstraints(widthOCard);

        RowConstraints or1 = new RowConstraints(heightOther);
        RowConstraints or2 = new RowConstraints(heightOther);
        RowConstraints or3 = new RowConstraints(heightOther);
        RowConstraints or4 = new RowConstraints(heightOther);
        RowConstraints or5 = new RowConstraints(heightOther);

        gridOther.getColumnConstraints().addAll(oc1, oc2);
        gridOther.getRowConstraints().addAll(or1, or2, or3, or4, or5);

        grid.add(gridOther, 2, 1);

        GridPane gridOtherSpace1 = setOtherspace(widthOther, heightOther);
        GridPane gridOtherSpace2 = setOtherspace(widthOther, heightOther);
        GridPane gridOtherSpace3 = setOtherspace(widthOther, heightOther);
        GridPane gridOtherSpace4 = setOtherspace(widthOther, heightOther);
        GridPane gridOtherSpace5 = setOtherspace(widthOther, heightOther);


        gridOther.add(gridOtherSpace1, 0, 0);
        gridOther.add(gridOtherSpace2, 0, 1);
        gridOther.add(gridOtherSpace3, 0, 2);
        gridOther.add(gridOtherSpace4, 0, 3);
        gridOther.add(gridOtherSpace5, 0, 4);

        GridPane gridOtherWe = new GridPane();

        ColumnConstraints owc1 = new ColumnConstraints(widthOtherWeapon);
        ColumnConstraints owc2 = new ColumnConstraints(widthOtherWeapon);
        ColumnConstraints owc3 = new ColumnConstraints(widthOtherWeapon);

        RowConstraints owr1 = new RowConstraints(heightOtherWeapon);
        RowConstraints owr2 = new RowConstraints(heightOtherWeapon);
        RowConstraints owr3 = new RowConstraints(heightOtherWeapon);
        RowConstraints owr4 = new RowConstraints(heightOtherWeapon);
        RowConstraints owr5 = new RowConstraints(heightOtherWeapon);

        gridOtherWe.getColumnConstraints().addAll(owc1, owc2, owc3);
        gridOtherWe.getRowConstraints().addAll(owr1, owr2, owr3, owr4, owr5);


        gridOther.add(gridOtherWe, 1, 0, 1, 5);

        setOtherAmmo(gridOtherAmmo1, widthOther, heightOtherAmmo);
        setOtherAmmo(gridOtherAmmo2, widthOther, heightOtherAmmo);
        setOtherAmmo(gridOtherAmmo3, widthOther, heightOtherAmmo);
        setOtherAmmo(gridOtherAmmo4, widthOther, heightOtherAmmo);
        setOtherAmmo(gridOtherAmmo5, widthOther, heightOtherAmmo);


        Image imgOther1;
        Image imgOther2;
        Image imgOther3;
        Image imgOther4;
        Image imgOther5;

        setCellBoard(gridOtherBoard1, widthOther, heightOtherBoard);
        setCellBoard(gridOtherBoard2, widthOther, heightOtherBoard);
        setCellBoard(gridOtherBoard3, widthOther, heightOtherBoard);
        setCellBoard(gridOtherBoard4, widthOther, heightOtherBoard);
        setCellBoard(gridOtherBoard5, widthOther, heightOtherBoard);
        /*______________________________________________________*/


        for(int i=0; i<4; i++){
            weOther1 = setOtherWeapon(gridOtherWe, i, widthOtherWeapon, heightOtherWeapon);
            otherWe.add(weOther1);
        }
        ArrayList<VirtualPlayer> allP = new ArrayList<>(model.getAllPlayers());
        allP.remove(model.getOwner());
        int i=0;
        for(VirtualPlayer p : allP) {
            imgOther1 = setBoard(p.getCharacter(), widthOther, heightOtherBoard);
            setGridBack(gridOtherBoards.get(i), imgOther1);
            i++;
        }

        /*______________________________________________________*/
//
//        int i = 0;
//        for (VirtualPlayer p : model.getAllPlayers()) {
//            if (!p.getCharacter().equals(model.getOwner().getCharacter())) {
//                switch (p.getCharacter()) {
//                    case "yellow": {
//                        imgOther1 = setBoard(p.getCharacter(), widthOther, heightOtherBoard);
//                        setGridBack(gridOtherBoards.get(i), imgOther1);
//                        weOther1 = setOtherWeapon(gridOtherWe, i, widthOtherWeapon, heightOtherWeapon);
//                        break;
//                    }
//                    case "red": {
//                        imgOther2 = setBoard(p.getCharacter(), widthOther, heightOtherBoard);
//                        setGridBack(gridOtherBoards.get(i), imgOther2);
//                        weOther2 = setOtherWeapon(gridOtherWe, i, widthOtherWeapon, heightOtherWeapon);
//                        break;
//                    }
//                    case "blue": {
//                        imgOther3 = setBoard(p.getCharacter(), widthOther, heightOtherBoard);
//                        setGridBack(gridOtherBoards.get(i), imgOther3);
//                        weOther3 = setOtherWeapon(gridOtherWe, i, widthOtherWeapon, heightOtherWeapon);
//                        break;
//                    }
//                    case "green": {
//                        imgOther4 = setBoard(p.getCharacter(), widthOther, heightOtherBoard);
//                        setGridBack(gridOtherBoards.get(i), imgOther4);
//                        weOther4 = setOtherWeapon(gridOtherWe, i, widthOtherWeapon, heightOtherWeapon);
//                        break;
//                    }
//                    case "grey": {
//                        imgOther5 = setBoard(p.getCharacter(), widthOther, heightOtherBoard);
//                        setGridBack(gridOtherBoards.get(i), imgOther5);
//                        weOther5 = setOtherWeapon(gridOtherWe, i, widthOtherWeapon, heightOtherWeapon);
//                        break;
//                    }
//                }
//                i++;
//            }
//        }

        otherWe.add(weOther1);
        otherWe.add(weOther2);
        otherWe.add(weOther3);
        otherWe.add(weOther4);
        otherWe.add(weOther5);


        ArrayList<GridPane> gridOtherSpace = new ArrayList<>();
        gridOtherSpace.add(gridOtherSpace1);
        gridOtherSpace.add(gridOtherSpace2);
        gridOtherSpace.add(gridOtherSpace3);
        gridOtherSpace.add(gridOtherSpace4);
        gridOtherSpace.add(gridOtherSpace5);


        for (int j = 0; j < 4; j++) {
            gridOtherSpace.get(j).add(gridOtherBoards.get(j), 0, 0);
            gridOtherSpace.get(j).add(gridOtherAmmo.get(j), 0, 1);
        }

        /*______________________________________________________*/

        /*
        ** SET DECK **

        Image imgDeck = new Image(new FileInputStream(PATH_BACK_POWERUP), widthCard, widthCard, true, true);
        BackgroundImage backgroundDeck = new BackgroundImage(imgDeck, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backDeck = new Background(backgroundDeck);
        btnDeck.setBackground(backDeck);
        btnDeck.setPrefSize(widthCard, widthCard);
        grid.add(btnDeck, 2, 2);

        */

        /**
         * set buttons
         */
        Image msgBack = new Image(new FileInputStream(PATH_BACK_MSG), 300, 100, true, true);
        BackgroundImage backgroundMsg = new BackgroundImage(msgBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backMsg = new Background(backgroundMsg);
        msg.setBackground(backMsg);
        msg.setAlignment(Pos.CENTER);
        msg.setFont(font);
        msg.setEditable(false);

        HBox hBtn = new HBox(30);
        hBtn.setAlignment(Pos.CENTER);
        hBtn.getChildren().add(btnTerminator);
        hBtn.getChildren().add(btnPowerUp);
        hBtn.getChildren().add(btnMove);
        hBtn.getChildren().add(btnPick);
        hBtn.getChildren().add(btnShoot);
        hBtn.getChildren().add(btnEnd);
        hBtn.getChildren().add(btnCancel);
        setButtonBack(btnMove,backBtn);
        setButtonBack(btnPick,backBtn);
        setButtonBack(btnPowerUp,backBtn);
        setButtonBack(btnTerminator,backBtn);
        setButtonBack(btnCancel,backBtn);
        setButtonBack(btnEnd,backBtn);
        setButtonBack(btnShoot,backBtn);
        btnMove.setTextFill(Color.WHITE);
        btnPick.setTextFill(Color.WHITE);
        btnPowerUp.setTextFill(Color.WHITE);
        btnShoot.setTextFill(Color.WHITE);
        btnTerminator.setTextFill(Color.WHITE);
        btnCancel.setTextFill(Color.WHITE);
        btnEnd.setTextFill(Color.WHITE);


        VBox vmsg = new VBox(25);
        vmsg.setAlignment(Pos.CENTER);
        vmsg.getChildren().add(msg);
        vmsg.getChildren().add(hBtn);

        grid.add(vmsg, 1, 2);

        /**
         * set buttons' action.
         */

        update();


        /*
        btnCancel.setOnAction(e->{
            update();
            hideBtn(btnCancel,0);
        });

        btnMove.setOnAction(e->{
            if (model.getTurn().getCharacter().equals(model.getOwner().getCharacter()) && btnShoot.getOpacity() == 1) {

                action = "move";
                msg.setText(CHOOSE_SQUARE);
                hideBtn(btnCancel,1);
                for (ArrayList<Button> btnArr : btnCell) {
                    chooseSquare(btnArr, btnCell.indexOf(btnArr),false);
                }


                for (String s : game.getHideSquare()) {
                    hideCell(btnCell.get(getCoordinate(s)), 0.5);
                }
            }else
                msg.setText("Aspetta il tuo turno...");

        });
        btnPick.setOnAction(e->{
            if(model.getTurn().getCharacter().equals(model.getOwner().getCharacter()) && btnShoot.getOpacity() == 1) {

                action = "pick";
                msg.setText(CHOOSE_SQUARE);
                hideBtn(btnCancel,1);
                for (ArrayList<Button> btnArr : btnCell) {
                    chooseSquare(btnArr, btnCell.indexOf(btnArr),true);
                }

                for (String s : game.getHideSquare()) {
                    hideCell(btnCell.get(getCoordinate(s)), 0.5);
                }

            }else
                msg.setText("Aspetta il tuo turno...");
        });

        btnShoot.setOnAction(e->{
            if(model.getTurn().getCharacter().equals(model.getOwner().getCharacter()) && btnShoot.getOpacity() == 1){

                action = "shoot";
                msg.setText(CHOOSE_PLAYER);
                hideBtn(btnCancel,1);
                for (ArrayList<Button> btnArr: btnCell) {
                    choosePlayer(btnArr, btnCell.indexOf(btnArr));
                    for (Button btn : we) {
                        btn.setOnAction(es->{
                            game.setWeapon(model.getOwner().getWeapons().get(we.indexOf(btn)));
                        });
                    }
                }
            }else
                msg.setText("Aspetta il tuo turno...");
        });

        btnPowerUp.setOnAction(e->{
            int j = 0;
            for (Button btn : pu) {
                if(j < model.getOwner().getPowerUps().size()) {
                    String power = model.getOwner().getPowerUps().get(j);
                    btn.setOnAction(ep -> {
                        game.setPowerup(power);
                    });
                    j++;
                }
            }
        });


        for (Button btn : we) {
            btn.setOnAction(e->{
                int j = we.indexOf(btn);
                infoWindow iw = new infoWindow(model.getOwner(),j,false);
                iw.show();
            });
        }

        for (Button btn : pu) {
            btn.setOnAction(e->{
                int j = pu.indexOf(btn);
                infoWindow iw = new infoWindow(model.getOwner(),j,true);
                iw.show();
            });
        }

        btnDeck.setOnAction(e->{System.out.println("ok");});

        for (VirtualPlayer player : model.getAllPlayers()) {
            setPlayerOnCell(btnCell.get(player.getRow()*4+player.getColumn()),player.getCharacter());
        }

        for (ArrayList<Button> btnArr : btnCell) {
            btnArr.get(5).setOnAction(e->{
                if(btnCell.indexOf(btnArr) == 2 || btnCell.indexOf(btnArr) == 4 || btnCell.indexOf(btnArr) == 11){
                    chooseWeapon cw = new chooseWeapon();
                    cw.show();
                }
            });
        }

        */
        msg.setText(WELCOME);

        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public ArrayList<Button> setGridCell(GridPane grid, double width, double height) {

        ArrayList<Button> res = new ArrayList<>();

        double w = width / 3;
        double h = height / 2;
        ColumnConstraints c1 = new ColumnConstraints(w);
        ColumnConstraints c2 = new ColumnConstraints(w);
        ColumnConstraints c3 = new ColumnConstraints(w);

        RowConstraints r1 = new RowConstraints(h);
        RowConstraints r2 = new RowConstraints(h);

        grid.getColumnConstraints().addAll(c1, c2, c3);
        grid.getRowConstraints().addAll(r1, r2);

        Button b1 = new Button();
        Button b2 = new Button();
        Button b3 = new Button();
        Button b4 = new Button();
        Button b5 = new Button();
        Button b6 = new Button();

        btnEmpty= new Button();

        b1.setPrefSize(w, h);
        b2.setPrefSize(w, h);
        b3.setPrefSize(w, h);
        b4.setPrefSize(w, h);
        b5.setPrefSize(w, h);
        b6.setPrefSize(w, h);

        btnEmpty.setPrefSize(w, h);

        hideBtn(b1, 0);
        hideBtn(b2, 0);
        hideBtn(b3, 0);
        hideBtn(b4, 0);
        hideBtn(b5, 0);
        hideBtn(b6, 0);

        hideBtn(btnEmpty, 0);

        grid.add(b1, 0, 0);
        grid.add(b2, 0, 1);
        grid.add(b3, 1, 0);
        grid.add(b4, 1, 1);
        grid.add(b5, 2, 0);
        grid.add(b6, 2, 1);
        res.add(b1);
        res.add(b2);
        res.add(b3);
        res.add(b4);
        res.add(b5);
        res.add(b6);

        return res;
    }

    public GridPane setOtherspace(double widthBoard, double heightBoard) {

        GridPane grid = new GridPane();

        double heightAmmo = heightBoard / 3;
        double heightOther = heightAmmo * 2;

        ColumnConstraints c1 = new ColumnConstraints(widthBoard);

        RowConstraints r1 = new RowConstraints(heightOther);
        RowConstraints r2 = new RowConstraints(heightAmmo);

        grid.getColumnConstraints().add(c1);
        grid.getRowConstraints().addAll(r1, r2);


        return grid;
    }

    public Image setBoard(String color, double width, double height) {

        double widthOB = width;
        double heightOB = height;

        Image img = null;

        try {
            switch (color) {
                case "yellow": {
                    img = new Image(new FileInputStream(PATH_YELLOW_BOARD), widthOB, heightOB, true, true);
                    break;
                }
                case "red": {
                    img = new Image(new FileInputStream(PATH_RED_BOARD), widthOB, heightOB, true, true);
                    break;
                }
                case "blue": {
                    img = new Image(new FileInputStream(PATH_BLUE_BOARD), widthOB, heightOB, true, true);
                    break;
                }
                case "green": {
                    img = new Image(new FileInputStream(PATH_GREEN_BOARD), widthOB, heightOB, true, true);
                    break;
                }
                case "grey": {
                    img = new Image(new FileInputStream(PATH_GREY_BOARD), widthOB, heightOB, true, true);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }


    public void setGridBack(GridPane grid, Image img) {
        BackgroundImage background = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background back = new Background(background);
        grid.setBackground(back);
    }

    public void setCellBoard(GridPane grid, double widthOtherBoard, double heightOtherBoard) {

        grid.setAlignment(Pos.CENTER_LEFT);


        double width = widthOtherBoard / 19;

        ColumnConstraints c1 = new ColumnConstraints(width);
        ColumnConstraints c2 = new ColumnConstraints(width);
        ColumnConstraints c3 = new ColumnConstraints(width);
        ColumnConstraints c4 = new ColumnConstraints(width);
        ColumnConstraints c5 = new ColumnConstraints(width);
        ColumnConstraints c6 = new ColumnConstraints(width);
        ColumnConstraints c7 = new ColumnConstraints(width);
        ColumnConstraints c8 = new ColumnConstraints(width);
        ColumnConstraints c9 = new ColumnConstraints(width);
        ColumnConstraints c10 = new ColumnConstraints(width);
        ColumnConstraints c11 = new ColumnConstraints(width);
        ColumnConstraints c12 = new ColumnConstraints(width);
        ColumnConstraints c13 = new ColumnConstraints(width);
        ColumnConstraints c14 = new ColumnConstraints(width);


        RowConstraints r1 = new RowConstraints(heightOtherBoard / 3 - 6);
        RowConstraints r2 = new RowConstraints(heightOtherBoard / 3);
        RowConstraints r3 = new RowConstraints(heightOtherBoard / 3 - 6);


        grid.getColumnConstraints().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14);
        grid.getRowConstraints().addAll(r1, r2, r3);


    }


    public void fillAmmo(GridPane grid, VirtualPlayerBoard board, double w, double h) {
        int red = board.getAmmoRed();
        int blue = board.getAmmoBlue();
        int yellow = board.getAmmoYellow();

        while (red > 0) {
            ImageView imgAR = null;

            try {
                imgAR = new ImageView(new Image(new FileInputStream(PATH_RED_AMMO), w, h, true, true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAR, 3 - red, 0);
            red--;
        }
        while (blue > 0) {
            ImageView imgAB = null;

            try {
                imgAB = new ImageView(new Image(new FileInputStream(PATH_BLUE_AMMO), w, h, true, true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAB, 3 - blue, 1);
            blue--;
        }
        while (yellow > 0) {
            ImageView imgAY = null;

            try {
                imgAY = new ImageView(new Image(new FileInputStream(PATH_YELLOW_AMMO), w, h, true, true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAY, 3 - yellow, 2);
            yellow--;
        }
    }

    public void setOtherAmmo(GridPane grid, double widthOther, double heightOther) {

        double width = widthOther / 9;

        ColumnConstraints c1 = new ColumnConstraints(width);
        ColumnConstraints c2 = new ColumnConstraints(width);
        ColumnConstraints c3 = new ColumnConstraints(width);
        ColumnConstraints c4 = new ColumnConstraints(width);
        ColumnConstraints c5 = new ColumnConstraints(width);
        ColumnConstraints c6 = new ColumnConstraints(width);
        ColumnConstraints c7 = new ColumnConstraints(width);
        ColumnConstraints c8 = new ColumnConstraints(width);
        ColumnConstraints c9 = new ColumnConstraints(width);

        RowConstraints r1 = new RowConstraints(heightOther);

        grid.getColumnConstraints().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9);
        grid.getRowConstraints().add(r1);

    }

    private void fillOtherAmmo(GridPane grid, VirtualPlayerBoard board, double w, double h) {

        int red = board.getAmmoRed();
        int blue = board.getAmmoBlue();
        int yellow = board.getAmmoYellow();

        while (red > 0) {
            ImageView imgAR = null;

            try {
                imgAR = new ImageView(new Image(new FileInputStream(PATH_RED_AMMO), w, h, true, true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAR, 3 - red, 0);
            red--;
        }
        while (blue > 0) {
            ImageView imgAB = null;

            try {
                imgAB = new ImageView(new Image(new FileInputStream(PATH_BLUE_AMMO), w, h, true, true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAB, 6 - blue, 0);
            blue--;
        }
        while (yellow > 0) {
            ImageView imgAY = null;

            try {
                imgAY = new ImageView(new Image(new FileInputStream(PATH_YELLOW_AMMO), w, h, true, true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAY, 9 - yellow, 0);
            yellow--;
        }
    }

    public ArrayList<Button> setOtherWeapon(GridPane grid, int row, double w, double h) {

        ArrayList<Button> res = new ArrayList<>();

        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        btn1.setPrefSize(w, h);
        btn2.setPrefSize(w, h);
        btn3.setPrefSize(w, h);
        hideBtn(btn1, 0);
        hideBtn(btn2, 0);
        hideBtn(btn3, 0);
        grid.add(btn1, 0, row);
        grid.add(btn2, 1, row);
        grid.add(btn3, 2, row);

        res.add(btn1);
        res.add(btn2);
        res.add(btn3);

        return res;
    }

    public void setPlayerOnCell(ArrayList<Button> btn, String color) {

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.WHITE);
        borderGlow.setHeight(30);
        borderGlow.setWidth(30);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);

        String image = color + ".png";

        Image img = null;
        try {
            img = new Image(new FileInputStream(PATH_GENERAL_COLOR + image), 50, 50, true, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        switch (color) {
            case ("red"): {
                setButtonBack(btn.get(R), img);
                hideBtn(btn.get(R), 1);
                btn.get(R).setEffect(borderGlow);
                break;
            }
            case ("blue"): {
                setButtonBack(btn.get(B), img);
                hideBtn(btn.get(B), 1);
                btn.get(B).setEffect(borderGlow);
                break;
            }
            case ("yellow"): {
                setButtonBack(btn.get(Y), img);
                hideBtn(btn.get(Y), 1);
                btn.get(Y).setEffect(borderGlow);
                break;
            }
            case ("green"): {
                setButtonBack(btn.get(G), img);
                hideBtn(btn.get(G), 1);
                btn.get(G).setEffect(borderGlow);
                break;
            }
            case ("grey"): {
                setButtonBack(btn.get(GR), img);
                hideBtn(btn.get(GR), 1);
                btn.get(GR).setEffect(borderGlow);
                break;
            }
        }


    }

    public void setButtonBack(Button btn, Image img) {
        BackgroundImage background = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background back = new Background(background);
        btn.setBackground(back);
        hideBtn(btn, 1);
    }

    public void fillSkulls(GridPane grid, int skullMax, double w, double h) {

        int i = skullMax  - 1 ;

        while (i >= 0) {

            ImageView skull = null;

            try {
                skull = new ImageView(new Image(new FileInputStream(PATH_SKULL), w, h, true, true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            grid.add(skull, 7-i, 0);
            i--;
        }
    }

    public void fillSkulls(GridPane grid, int skullMax, double w, double h, String color, boolean two) {



    }

    public void fillBoard(GridPane grid, VirtualPlayer p, double width, double hmarks, double hdamage, double hskulls) {

        int i = 10;
        int j = 2;
        int k = 2;
        int no = 3;

        if (!p.getpBoard().getMarks().equals(null)) {
            for (String color : p.getpBoard().getMarks()) {
                switch (color) {
                    case "yellow": {
                        ImageView img = null;

                        try {
                            img = new ImageView(new Image(new FileInputStream(PATH_YELLOW_DAMAGE), width, hmarks, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        grid.add(img, i, 0);
                        break;
                    }
                    case "red": {
                        ImageView img = null;

                        try {
                            img = new ImageView(new Image(new FileInputStream(PATH_RED_DAMAGE), width, hmarks, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        grid.add(img, i, 0);
                        break;
                    }
                    case "blue": {
                        ImageView img = null;

                        try {
                            img = new ImageView(new Image(new FileInputStream(PATH_BLUE_DAMAGE), width, hmarks, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        grid.add(img, i, 0);
                        break;
                    }
                    case "green": {
                        ImageView img = null;

                        try {
                            img = new ImageView(new Image(new FileInputStream(PATH_GREEN_DAMAGE), width, hmarks, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        grid.add(img, i, 0);
                        break;
                    }
                    case "grey": {
                        ImageView img = null;

                        try {
                            img = new ImageView(new Image(new FileInputStream(PATH_GREY_DAMAGE), width, hmarks, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        grid.add(img, i, 0);
                        break;
                    }
                }
                i++;
            }
        }else{
            for (int empty = i; empty < 3; empty++ ){
                ImageView img = null;

                try {
                    img = new ImageView(new Image(new FileInputStream(PATH_EMPTY_DAMAGE), width, hmarks, true, true));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                grid.add(img, empty, 0);
            }
        }

        if (!p.getpBoard().getDamage().equals(null)) {
            for (String color : p.getpBoard().getDamage()) {
                switch (color) {
                    case "yellow": {
                        ImageView img = null;

                        try {
                            img = new ImageView(new Image(new FileInputStream(PATH_YELLOW_DAMAGE), width, hmarks, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        grid.add(img, j, 1);
                        break;
                    }
                    case "red": {
                        ImageView img = null;

                        try {
                            img = new ImageView(new Image(new FileInputStream(PATH_RED_DAMAGE), width, hmarks, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        grid.add(img, j, 1);
                        break;
                    }
                    case "blue": {
                        ImageView img = null;

                        try {
                            img = new ImageView(new Image(new FileInputStream(PATH_BLUE_DAMAGE), width, hmarks, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        grid.add(img, j, 1);
                        break;
                    }
                    case "green": {
                        ImageView img = null;

                        try {
                            img = new ImageView(new Image(new FileInputStream(PATH_GREEN_DAMAGE), width, hmarks, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        grid.add(img, j, 1);
                        break;
                    }
                    case "grey": {
                        ImageView img = null;

                        try {
                            img = new ImageView(new Image(new FileInputStream(PATH_GREY_DAMAGE), width, hmarks, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        grid.add(img, j, 1);
                        break;
                    }
                }
                j++;
            }
        } else {
            for (int empty = j; empty < 12; empty++ ){
                ImageView img = null;

                try {
                    img = new ImageView(new Image(new FileInputStream(PATH_EMPTY_DAMAGE), width, hmarks, true, true));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                grid.add(img, empty, 0);
            }
        }
        while (k <= 6 - p.getpBoard().getSkulls() + 1) {

            if (k <= no) {
                k++;
                continue;
            }

            ImageView skull = null;

            try {
                skull = new ImageView(new Image(new FileInputStream(PATH_SKULL), (width - 25) / 6, hskulls - 25, true, true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            grid.add(skull, k, 2);
            k++;
        }


    }

    public void fillWeapon(ArrayList<Button> btnArr, VirtualPlayer p, double width, double height) {
        String[] wpState;
        int i = 0;

        for (String name : p.getWeapons()) {
            wpState = name.split(INNER_SEP);
            if (!btnArr.isEmpty() && i < btnArr.size()) {
                if (wpState[1].equals("true")) {
                    Image img = null;
                    try {
                        img = new Image(new FileInputStream(PATH_WEAPON + wpState[0].toLowerCase().replace(" ", "_").replace("-", "_") + ".png"), width, height, true, true);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    setButtonBack(btnArr.get(i), img);
                    hideBtn(btnArr.get(i), 1);
                    i++;
                } else {
                    Image img = null;
                    try {
                        img = new Image(new FileInputStream(PATH_BACK_WEAPON), width, height, true, true);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    setButtonBack(btnArr.get(i), img);
                    hideBtn(btnArr.get(i), 1);
                    i++;
                }
            }
        }
    }

    public void fillPowerUp(ArrayList<Button> btnArr, VirtualPlayer p) {

        int i = 0;

        for (String info : p.getPowerUps()) {

            String name = info.split(":")[0].toLowerCase().replace(" ", "_");
            String color = info.split(":")[1].toLowerCase().replace(" ", "_");

            switch (color) {
                case ("y"): {
                    color = "yellow";
                    break;
                }
                case ("r"): {
                    color = "red";
                    break;
                }
                case ("b"): {
                    color = "blue";
                    break;
                }

            }

            File jsonFilePU = new File(PATH_PU);

            try {

                Image imgPu = null;
                try {
                    imgPu = new Image(new FileInputStream(PATH_POWER_UP + name + "_" + color + ".png"), widthCard - 20, heightCard - 20, true, true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                setButtonBack(btnArr.get(i), imgPu);
                i++;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void fillAmmoTiles() {

        Runnable run = () -> {


            double w = btnCell.get(0).get(0).getPrefWidth();
            double h = btnCell.get(0).get(0).getPrefHeight();

            DropShadow borderGlow = new DropShadow();
            borderGlow.setHeight(20);
            borderGlow.setWidth(20);
            borderGlow.setOffsetX(0f);
            borderGlow.setOffsetY(0f);

            for (int i = 0; i < 12; i++) {


                if (i == 2 || i == 4 || i == 11) {
                    try {
                        Image img = new Image(new FileInputStream(PATH_BACK_WEAPON), w, h, true, true);
                        BackgroundImage background = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                        Background back = new Background(background);
                        btnCell.get(i).get(5).setBackground(back);
                        hideBtn(btnCell.get(i).get(5), 1);
                        btnCell.get(i).get(5).setAlignment(Pos.CENTER_LEFT);
                        borderGlow.setColor(Color.LIGHTGREEN);
                        btnCell.get(i).get(5).setEffect(borderGlow);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                int x = (int) Math.ceil((double) (i + 1) / 4) - 1;
                int y = (i + 1) - (x * 4) - 1;
                String key = x + ":" + y;


                try {
                    Image img = new Image(new FileInputStream(PATH_AMMO + model.getBoard().getMap().getCells().get(key).getContent() + ".png"), w, h, true, true);
                    BackgroundImage background = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                    Background back = new Background(background);
                    btnCell.get(i).get(5).setBackground(back);
                    hideBtn(btnCell.get(i).get(5), 1);
                    borderGlow.setColor(Color.LIGHTCYAN);
                    btnCell.get(i).get(5).setEffect(borderGlow);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };

        Platform.runLater(run);
    }

    public int getCoordinate(String s) {


        int temp1 = Integer.parseInt(s.split(":")[0]);
        int temp2 = Integer.parseInt(s.split(":")[1]);

        return temp1 * 4 + temp2;
    }


    public void hideCell(ArrayList<Button> btn, double o) {

        for (Button b : btn) {
            if(btn.indexOf(b) == 5){
                continue;
            }
            if(o==0.5) {
                b.setBackground(btnEmptyBG);
                b.setEffect(null);
                b.setOpacity(0.7);
            } else
                b.setOpacity(o);
        }
        drawPlayers();
    }


    public void drawPlayers() {
        ArrayList<VirtualPlayer> temp = new ArrayList<>(model.getAllPlayers());
        for (VirtualPlayer player : temp) {
            if (player.getRow() != -1) {
                setPlayerOnCell(btnCell.get((player.getRow() * 4) - 1 + player.getColumn() + 1), player.getCharacter());
            }
        }
    }

    public void hideBtn(Button btn, double o) {
        if (o == 0) {
            //setButtonBack(btn,emptyCell);
        }
        btn.setOpacity(o);

    }

    public void chooseSquare(ArrayList<Button> btnArr, int btn, boolean pick) {

        for (Button b : btnArr) {
            Runnable run = () -> b.setOnAction(e -> {
                if (b.getOpacity() == 0 || btnArr.indexOf(b) != 5) {
                    int x = (int) Math.ceil((double) (btn + 1) / 4) - 1;
                    int y = (btn + 1) - (x * 4) - 1;
                    game.setTargetSquare(x + ":" + y);

                    model.getOwner().setRow(x);
                    model.getOwner().setColumn(y);

                    String key = x + ":" + y;

                    if(x+y == 2 || x+y == 4 || x+y == 11 ){
                        //chooseWeapon cw = new chooseWeapon(model.getBoard().getMap().getCells().get(key).getContent());
                        //cw.show();
                    }


                    hideBtn(btnCancel, 0);

                    update();
                } else
                    msg.setText(ERR_SQUARE);

                if (pick) {
                    hideBtn(btnArr.get(5), 0);
                }
            });

            Platform.runLater(run);

        }


    }


    public void choosePlayer(ArrayList<Button> btnArr, int btn) {

        for (Button b : btnArr) {
            b.setOnAction(e -> {
                if (b.getOpacity() == 1) {

                    switch (btnArr.indexOf(b)) {
                        case (0): {
                            game.setTargetPlayer("yellow");

                            break;
                        }
                        case (1): {
                            game.setTargetPlayer("grey");

                            break;
                        }
                        case (2): {
                            game.setTargetPlayer("green");

                            break;
                        }
                        case (3): {
                            game.setTargetPlayer("blue");

                            break;
                        }
                        case (4): {
                            game.setTargetPlayer("red");

                            break;
                        }
                        case (5): {
                            msg.setText(ERR_PLAYER);
                            break;
                        }
                    }
                    update();

                    hideBtn(btnCancel, 0);

                } else {
                    msg.setText(ERR_PLAYER);
                }
            });
        }

    }

    private void setWeapon(VirtualPlayer p, ArrayList<Button> btnArr) {
        for (Button btn : btnArr) {
            btn.setOnAction(e -> {
                int i = btnArr.indexOf(btn);
                infoWindow iw = new infoWindow(p, i, false);
                iw.show();
            });
        }
    }

    public boolean verifyDiffSquare(ArrayList<String> args,String test){
        VirtualPlayer p = model.findPlayer(test);
        VirtualPlayer v;
        for(String s: args) {
            v = model.findPlayer(s);
            if (p.getRow() == v.getRow() && p.getColumn() == v.getColumn()) {
                return false;
            }
        }
        return true;
    }

    public void update() {

        if(start) {
            msg.setText(CLEAR);

            DropShadow borderGlow = new DropShadow();
            borderGlow.setColor(Color.RED);
            borderGlow.setHeight(50);
            borderGlow.setWidth(50);
            borderGlow.setOffsetX(0f);
            borderGlow.setOffsetY(0f);

            if(reset) {
                for (ArrayList<Button> btnArr : btnCell) {
                    for (Button btn : btnArr) {
                    }
                }

                reset = false;
            }


            if (model.getTurn().getCharacter().equals(model.getOwner().getCharacter())) {
                gridPBoard.setEffect(borderGlow);
            }else {
                gridPBoard.setEffect(null);
                for (GridPane grid : gridOtherBoards) {
                    grid.setEffect(null);
                }

                ArrayList<Button> btns = new ArrayList<>();
                String turn = model.getTurn().getCharacter();
                for (VirtualPlayer p: model.getAllPlayers()) {
                    if(turn.equals(p.getCharacter())){
                        gridOtherBoards.get(model.getAllPlayers().indexOf(p)).setEffect(null);
                    }
                }

            }

            for (ArrayList<Button> btnArr : btnCell) {
                hideCell(btnArr, 0);
                for (Button b : btnArr) {
                    b.setOnAction(e -> {
                    });
                }
            }

            hideBtn(btnCancel, 0);
            hideBtn(btnEnd, 0);
            hideBtn(btnTerminator, 0);
            hideBtn(btnMove, 0);
            hideBtn(btnPick, 0);
            hideBtn(btnShoot, 0);
            hideBtn(btnPowerUp, 0);

            btnMove.setOnAction(e -> {
                if (model.getTurn().getCharacter().equals(model.getOwner().getCharacter()) && btnShoot.getOpacity() == 1) {

                    action = "move";
                    msg.setText(CHOOSE_SQUARE);
                    hideBtn(btnCancel, 1);
                    for (ArrayList<Button> btnArr : btnCell) {
                        chooseSquare(btnArr, btnCell.indexOf(btnArr), false);
                    }


                    for (String s : game.getHideSquare()) {
                        hideCell(btnCell.get(getCoordinate(s)), 0.5);
                    }
                } else
                    msg.setText(WAIT);

            });
            btnPick.setOnAction(e -> {
                if (model.getTurn().getCharacter().equals(model.getOwner().getCharacter()) && btnShoot.getOpacity() == 1) {

                    action = "pick";
                    msg.setText(CHOOSE_SQUARE);
                    hideBtn(btnCancel, 1);
                    for (ArrayList<Button> btnArr : btnCell) {
                        chooseSquare(btnArr, btnCell.indexOf(btnArr), true);
                    }

                    for (String s : game.getHideSquare()) {
                        hideCell(btnCell.get(getCoordinate(s)), 0.5);
                    }

                } else
                    msg.setText(WAIT);
            });

            btnShoot.setOnAction(e -> {
                if (model.getTurn().getCharacter().equals(model.getOwner().getCharacter()) && btnShoot.getOpacity() == 1) {

                    action = "shoot";
                    msg.setText(CHOOSE_PLAYER);
                    hideBtn(btnCancel, 1);
                    for (ArrayList<Button> btnArr : btnCell) {
                        choosePlayer(btnArr, btnCell.indexOf(btnArr));
                        for (Button btn : we) {
                            btn.setOnAction(es -> {
                                game.setWeapon(model.getOwner().getWeapons().get(we.indexOf(btn)));
                            });
                        }
                    }
                } else
                    msg.setText(WAIT);
            });

            btnPowerUp.setOnAction(e -> {
                int j = 0;
                for (Button btn : pu) {
                    if (j < model.getOwner().getPowerUps().size()) {
                        String power = model.getOwner().getPowerUps().get(j);
                        btn.setOnAction(ep -> {
                            game.setPowerup(power);
                        });
                        j++;
                    }
                }
            });


            for (Button btn : we) {
                btn.setOnAction(e -> {
                    int j = we.indexOf(btn);
                    infoWindow iw = new infoWindow(model.getOwner(), j, false);
                    iw.show();
                });
            }

            for (Button btn : pu) {
                btn.setOnAction(e -> {
                    int j = pu.indexOf(btn);
                    infoWindow iw = new infoWindow(model.getOwner(), j, true);
                    iw.show();
                });
            }

            /*

            btnDeck.setOnAction(e -> {
                System.out.println("ok");
            });

             */


            for (VirtualPlayer player : model.getAllPlayers()) {
                if (player.getRow() != -1) {
                    setPlayerOnCell(btnCell.get(player.getRow() * 4 + player.getColumn()), player.getCharacter());
                }
            }

            ArrayList<String> infoWe = new ArrayList<>();
            for (ArrayList<Button> btnArr : btnCell) {
                btnArr.get(5).setOnAction(e -> {
                    if (btnCell.indexOf(btnArr) == 2 || btnCell.indexOf(btnArr) == 4 || btnCell.indexOf(btnArr) == 11) {

                        for (String pos : model.getBoard().getMap().getCells().keySet()) {

                            if(getCoordinate(pos) == btnCell.indexOf(btnArr)){
                                switch(model.getBoard().getMap().getCells().get(pos).getContent().split(INFO_SEP).length){
                                    case(3):{
                                        infoWe.add(model.getBoard().getMap().getCells().get(pos).getContent().split(INFO_SEP)[2]);

                                    }
                                    case(2):{
                                        infoWe.add(model.getBoard().getMap().getCells().get(pos).getContent().split(INFO_SEP)[1]);

                                    }
                                    case(1):{
                                        infoWe.add(model.getBoard().getMap().getCells().get(pos).getContent().split(INFO_SEP)[0]);

                                    }
                                }
                                chooseWeapon cw = new chooseWeapon(infoWe);
                                cw.show();

                            }
                        }
                    }
                });
            }


            fillSkulls(gridSkull, model.getBoard().getSkull(), widthSkull - 5, heightLateral / 3);

            drawPlayers();
            fillAmmoTiles();


            fillBoard(gridPBoard, model.getOwner(), widthBoard, heightPBoard / 4 - 6, heightPBoard / 4, heightPBoard / 3 - 6);
            fillAmmo(gridPAmmo, model.getOwner().getpBoard(), widthLateral / 7, heightLateral / 7);


            for (Button btn : we) {
                hideBtn(btn, 0);
            }
            for (Button btn : pu) {
                hideBtn(btn, 0);
            }
            fillWeapon(we, model.getOwner(), widthCard, heightCard);
            fillPowerUp(pu, model.getOwner());


            int i = 0;
            for (VirtualPlayer p : model.getAllPlayers()) {

                //Owner already have his own board
                if (p.equals(model.getOwner()))
                    continue;


                fillBoard(gridOtherBoards.get(i), p, widthBoard, heightPBoard / 5 - 10, heightPBoard / 4 - 10, heightPBoard / 5 - 10);

                //all player but not the terminator
                if(!p.getUsername().equals(TERMINATOR_NAME)) {
                    fillOtherAmmo(gridOtherAmmo.get(i), p.getpBoard(), widthLateral / 7, heightLateral / 7);
                    //set enemy wp
                    fillWeapon(otherWe.get(i), p, widthOCard / 3.7, heightOtherWeapon);
                    //set infobutton
                    setWeapon(p, otherWe.get(i));
                }
                i++;

            }
        }
    }

    @Override
    public String showWeapon(ArrayList<String> args) {

        Runnable run = () -> {

            btnCancel.setOnAction(e->{
                update();
                game.setWeapon(NOTHING);
            });

            if(args.size() < 4) {

                DropShadow borderGlow = new DropShadow();
                borderGlow.setColor(Color.WHITE);
                borderGlow.setHeight(50);
                borderGlow.setWidth(50);
                borderGlow.setOffsetX(0f);
                borderGlow.setOffsetY(0f);

                if(shoot) {
                    for (Button btn : we) {
                        btn.setEffect(borderGlow);
                        btn.setOnAction(e -> {
                            game.setWeapon(args.get(we.indexOf(btn)));
                        });
                    }
                    shoot = false;
                }

                if(model.getOwner().getRow()*4 + model.getOwner().getColumn() == 2
                        || model.getOwner().getRow()*4 + model.getOwner().getColumn() == 4
                        || model.getOwner().getRow()*4 + model.getOwner().getColumn() == 11
                        || pick && !shoot
                ){
                    chooseWeapon cw = new chooseWeapon(args);
                    cw.show();
                    pick = false;
                }else{
                    for (ArrayList<Button> btnArr : btnCell) {
                        btnArr.get(5).setOnAction(e -> {
                            if (btnCell.indexOf(btnArr) == 2 || btnCell.indexOf(btnArr) == 4 || btnCell.indexOf(btnArr) == 11) {
                                chooseWeapon cw = new chooseWeapon(args);
                                cw.show();
                            }
                        });
                    }
                }
            }else{
                discardCards dc = new discardCards(args,false);
                dc.show();
            }
        };

        Platform.runLater(run);

        while (game.getWeapon().equals("")) ;
        String res;
        res = game.getWeapon();

        game.setWeapon("");

        for (Button btn : we) {
            btn.setEffect(null);
        }

        return res;
    }

    @Override
    public String showPowerUp(ArrayList<String> args) {
        Runnable run = () -> {

            msg.setText(CHOOSE_POWERUP);

            btnCancel.setOnAction(e->{
                update();
                game.setPowerup(NOTHING);
            });

            if(args.size() != model.getOwner().getPowerUps().size()) {
                discardCards dc = new discardCards(args,true);
                dc.show();

            }else{
                DropShadow borderGlow = new DropShadow();
                borderGlow.setColor(Color.WHITE);
                borderGlow.setHeight(50);
                borderGlow.setWidth(50);
                borderGlow.setOffsetX(0f);
                borderGlow.setOffsetY(0f);

                for (Button btn : pu) {
                    btn.setEffect(borderGlow);
                    btn.setOnAction(e->{
                        game.setPowerup(args.get(pu.indexOf(btn)));
                    });
                }
            }
        };

        Platform.runLater(run);

        while (game.getPowerup().equals("")){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String res;
        res = game.getPowerup();

        game.setPowerup("");

        for (Button btn : pu) {
            btn.setEffect(null);
        }

        return res;
    }

    @Override
    public String showActions(ArrayList<String> args) {

        Runnable run = () -> {

            msg.setText(CHOOSE_ACTION);

            for (String act : args) {

                switch (act.split(INNER_SEP)[0].toLowerCase()) {
                    case ("move"): { //move
                        hideBtn(btnMove,1);
                        btnMove.setOnAction(e->{
                            move = true;
                            game.setAction(act);
                            for (ArrayList<Button> btnArr : btnCell) {
                                chooseSquare(btnArr,btnCell.indexOf(btnArr),false);
                            }
                            update();
                        });
                        break;
                    }
                    case ("pick"): { //pick
                        hideBtn(btnPick, 1);
                        btnPick.setOnAction(e->{
                            pick = true;

                            game.setAction(act);
                            for (ArrayList<Button> btnArr : btnCell) {
                                chooseSquare(btnArr,btnCell.indexOf(btnArr),true);
                            }
                            update();

                        });
                        break;
                    }
                    case ("shoot"): { //shoot
                        hideBtn(btnCancel,1);
                        btnCancel.setOnAction(e->{
                            update();
                            game.setAction(NOTHING);
                        });
                        btnShoot.setText("Spara");
                        hideBtn(btnShoot, 1);
                        btnShoot.setOnAction(e->{
                            shoot = true;

                            game.setAction(act);

                            update();

                        });
                        break;
                    }
                    case ("reload"): { //reload
                        hideBtn(btnShoot, 1);
                        btnShoot.setText("Ricarica");
                        btnShoot.setOnAction(e->{
                            game.setAction(act);
                            update();

                        });
                        break;
                    }
                    case ("use bot"): { //terminator
                        hideBtn(btnTerminator, 1);
                        btnTerminator.setOnAction(e->{
                            game.setAction(act);
                            update();

                        });
                        break;
                    }
                    case ("powerup"): { //power-up
                        hideBtn(btnPowerUp, 1);
                        btnPowerUp.setOnAction(e->{
                            game.setAction(act);
                            update();

                        });
                        break;
                    }
                    case("end turn"):{ //end turn
                        hideBtn(btnEnd,1);
                        btnEnd.setOnAction(e->{
                            game.setAction(act);
                            update();

                        });
                    }
                }
            }
        };


        Platform.runLater(run);

        while (game.getAction().equals("")){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String res = game.getAction();
        game.setAction("");

        return res;
    }

    @Override
    public String showPossibleMoves(ArrayList<String> args) {
        Runnable run = () -> {

            msg.setText(CHOOSE_SQUARE);

            hideBtn(btnCancel,1);

            btnCancel.setOnAction(e->{
                update();
                game.setTargetSquare(NOTHING);
            });

            for (ArrayList<Button> btnArr : btnCell) {
                hideCell(btnArr, 0.5);
                for (Button btn : btnArr) {
                    btn.setOnAction(e -> {
                        msg.setText(ERR_SQUARE);
                    });
                }
            }
            args.remove(0);
            for (String s : args) {
                for(Button btn:btnCell.get(getCoordinate(s))){
                    btn.setOnAction(e->{
                        game.setTargetSquare(s);
                        setButtonBack(btnCell.get(model.getOwner().getRow()*4 + model.getOwner().getColumn()).get(Y),emptyCell);
                        hideBtn(btnCell.get(model.getOwner().getRow()*4 + model.getOwner().getColumn()).get(Y),0.5);
                    });
                }
                hideCell(btnCell.get(getCoordinate(s)), 0);

            }


        };

        Platform.runLater(run);

        while (game.getTargetSquare().equals("")) ;
        String res = game.getTargetSquare();
        game.setTargetSquare("");
        reset = true;

        return res;
    }

    @Override
    public String chooseDirection(ArrayList<String> args) {

        Runnable run = () -> {

            msg.setText(CHOOSE_DIRECTION);

            hideBtn(btnCancel,1);
            btnCancel.setOnAction(e->{
                update();
                game.setDirection(NOTHING);
            });

            int i = model.getOwner().getRow();
            int j = model.getOwner().getColumn();


            for (; i < 2; i++) {
                for (Button btn : btnCell.get((i * 4) + j)) {
                    btn.setOnAction(e -> {
                        game.setDirection("east");
                    });
                }
            }

            i = model.getOwner().getRow();
            j = model.getOwner().getColumn();


            for (; i > 0; i--) {
                for (Button btn : btnCell.get((i * 4) + j)) {
                    btn.setOnAction(e -> {
                        game.setDirection("west");
                    });
                }
            }

            i = model.getOwner().getRow();
            j = model.getOwner().getColumn();


            for (; j < 3; j++) {
                for (Button btn : btnCell.get((i * 4) + j)) {
                    btn.setOnAction(e -> {
                        game.setDirection("north");
                    });
                }
            }

            i = model.getOwner().getRow();
            j = model.getOwner().getColumn();


            for (; j > 0; j--) {
                for (Button btn : btnCell.get((i * 4) + j)) {
                    btn.setOnAction(e -> {
                        game.setDirection("south");
                    });
                }
            }
        };

        Platform.runLater(run);

        while (game.getDirection().equals("")) ;
        String res = game.getDirection();
        game.setDirection("");

        return res;
    }

    @Override
    public String showEffects(ArrayList<String> args) {


        Runnable run = () -> {

            msg.setText(CHOOSE_EFFECT);

            hideBtn(btnCancel,1);
            btnCancel.setOnAction(e->{
                update();
                game.setEffect(NOTHING);
            });

            switch (args.size()) {
                case (4): {
                    hideBtn(btnPowerUp,1);
                    btnPowerUp.setText(args.get(3).split(INNER_SEP)[0]);
                    btnPowerUp.setOnAction(e -> {
                        game.setEffect(args.get(3).split(INNER_SEP)[0]);
                    });
                }
                case (3): {
                    hideBtn(btnPick,1);
                    btnPick.setText(args.get(2).split(INNER_SEP)[0]);
                    btnPick.setOnAction(e -> {
                        game.setEffect(args.get(2).split(INNER_SEP)[0]);
                    });

                }
                case (2): {
                    hideBtn(btnMove,1);
                    btnMove.setText(args.get(1).split(INNER_SEP)[0]);
                    btnMove.setOnAction(e -> {
                        game.setEffect(args.get(1).split(INNER_SEP)[0]);
                    });
                }
                case (1): {
                    hideBtn(btnShoot,1);
                    btnShoot.setText(args.get(0).split(INNER_SEP)[0]);
                    btnShoot.setOnAction(e -> {
                        game.setEffect(args.get(0).split(INNER_SEP)[0]);
                    });
                }

            }
        };

        Platform.runLater(run);

        while (game.getEffect().equals("")) ;
        String res = game.getEffect();
        game.setEffect("");
        Runnable up = () -> update();
        Platform.runLater(up);

        return res;
    }

    @Override
    public String showTargets(ArrayList<String> args) {return null;}

            /*

        Runnable run = () -> {

            for (ArrayList<Button> btnArr : btnCell) {
                hideCell(btnArr, 0.5);
            }

            for (String color : args) {
                for (VirtualPlayer p : model.getAllPlayers()) {
                    if (p.getCharacter().equals(color)) {
                        switch (p.getCharacter()) {
                            case ("yellow"): {
                                hideBtn(btnCell.get(p.getRow() * 4 + p.getColumn()).get(Y), 1);
                                btnCell.get(p.getRow() * 4 + p.getColumn()).get(Y).setOnAction(e -> {
                                    game.setTargetPlayer(p.getCharacter());
                                });
                                break;
                            }
                            case ("red"): {
                                hideBtn(btnCell.get(p.getRow() * 4 + p.getColumn()).get(R), 1);
                                btnCell.get(p.getRow() * 4 + p.getColumn()).get(R).setOnAction(e -> {
                                    game.setTargetPlayer(p.getCharacter());
                                });
                                break;
                            }
                            case ("blue"): {
                                hideBtn(btnCell.get(p.getRow() * 4 + p.getColumn()).get(B), 1);
                                btnCell.get(p.getRow() * 4 + p.getColumn()).get(B).setOnAction(e -> {
                                    game.setTargetPlayer(p.getCharacter());
                                });
                                break;
                            }
                            case ("green"): {
                                hideBtn(btnCell.get(p.getRow() * 4 + p.getColumn()).get(G), 1);
                                btnCell.get(p.getRow() * 4 + p.getColumn()).get(G).setOnAction(e -> {
                                    game.setTargetPlayer(p.getCharacter());
                                });
                                break;
                            }
                            case ("grey"): {
                                hideBtn(btnCell.get(p.getRow() * 4 + p.getColumn()).get(GR), 1);
                                btnCell.get(p.getRow() * 4 + p.getColumn()).get(GR).setOnAction(e -> {
                                    game.setTargetPlayer(p.getCharacter());
                                });
                                break;
                            }
                        }
                    }
                }

            }
        };

        Platform.runLater(run);

        while (game.getTargetPlayer().equals("")) ;
        String res = game.getTargetPlayer();
        game.setTargetPlayer("");

        return res;
    }

    */

    @Override
    public String showTargetAdvanced(ArrayList<String> args) {

        Runnable run = () -> {

            msg.setText(args.get(0).split(INNER_SEP)[2]);

            hideBtn(btnCancel,1);
            btnCancel.setOnAction(e->{
                update();
                game.setTargetPlayer(NOTHING);
            });

            for (ArrayList<Button> btnArr : btnCell) {
                hideCell(btnArr, 0.5);
            }

            Boolean diff = Boolean.parseBoolean(args.get(0).split(INNER_SEP)[1]);
            args.remove(0);
            for (String color : args) {
                for (VirtualPlayer p : model.getAllPlayers()) {
                    if (p.getCharacter().equals(color) && (!diff || verifyDiffSquare(game.getTargetPlayers(),p.getCharacter()) )) {
                        switch (p.getCharacter()) {
                            case ("yellow"): {
                                hideBtn(btnCell.get(p.getRow() * 4 + p.getColumn()).get(Y), 1);
                                btnCell.get(p.getRow() * 4 + p.getColumn()).get(Y).setOnAction(e -> {
                                    game.getTargetPlayers().add(p.getCharacter());
                                });
                                break;
                            }
                            case ("red"): {
                                hideBtn(btnCell.get(p.getRow() * 4 + p.getColumn()).get(R), 1);
                                btnCell.get(p.getRow() * 4 + p.getColumn()).get(R).setOnAction(e -> {
                                    game.getTargetPlayers().add(p.getCharacter());
                                });
                                break;
                            }
                            case ("blue"): {
                                hideBtn(btnCell.get(p.getRow() * 4 + p.getColumn()).get(B), 1);
                                btnCell.get(p.getRow() * 4 + p.getColumn()).get(B).setOnAction(e -> {
                                    game.getTargetPlayers().add(p.getCharacter());
                                });
                                break;
                            }
                            case ("green"): {
                                hideBtn(btnCell.get(p.getRow() * 4 + p.getColumn()).get(G), 1);
                                btnCell.get(p.getRow() * 4 + p.getColumn()).get(G).setOnAction(e -> {
                                    game.getTargetPlayers().add(p.getCharacter());
                                });
                                break;
                            }
                            case ("grey"): {
                                hideBtn(btnCell.get(p.getRow() * 4 + p.getColumn()).get(GR), 1);
                                btnCell.get(p.getRow() * 4 + p.getColumn()).get(GR).setOnAction(e -> {
                                    System.out.println(p.getCharacter());
                                    game.getTargetPlayers().add(p.getCharacter());
                                });
                                break;
                            }
                        }
                    }
                }

            }
        };

        int max = Integer.parseInt(args.get(0).split(INNER_SEP)[0]);
        Platform.runLater(run);
        int k = game.getTargetPlayers().size();
        while(game.getTargetPlayers().size() != max);

        String res = "";
        for(String targ : game.getTargetPlayers()){
            res = res + targ + INFO_SEP;
        }
        game.setTargetPlayers(new ArrayList<>());
        return res;
    }

    @Override
    public boolean showBoolean(String message) {
        Runnable run = () -> {
            decisionWindow dw = new decisionWindow(message);
            dw.show();
        };

        Platform.runLater(run);

        while (decision.equals("")) ;
        String res = decision;
        decision = "";

        if (res.equals("SI")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void displayMessage(String message) {
        msg.setText(message);
    }

    @Override
    public void displayLeaderboard() {
        LeaderboardJavaFX leaderboard = new LeaderboardJavaFX();
        try {
            leaderboard.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String singleTargetingShowTarget(ArrayList<String> args) {
        return null;
    }

    @Override
    public void updateModel(String message) {

        parser.updateModel(message);
        Runnable runnable=()->{update();};
        if(model.isUpdated()){
            Platform.runLater(runnable);
        }
    }


    public class decisionWindow extends Stage {

        public decisionWindow(String message) {

            double widthScreen = Screen.getPrimary().getBounds().getWidth() / 4;
            double heightScreen = Screen.getPrimary().getBounds().getHeight() / 4;

            Group root = new Group();
            Scene theScene = new Scene(root);
            this.setScene(theScene);

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(30);
            grid.setVgap(20);
            grid.setPadding(new Insets(0, 0, 0, 0));

            try {
                Image back = new Image(new FileInputStream(PATH_BACK_GAME), 2190, 1920, true, true);
                BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                grid.setBackground(new Background(backgroundImage));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(grid, widthScreen, heightScreen);
            this.setScene(scene);

            scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> grid.setPrefWidth((double) newSceneWidth));
            scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> grid.setPrefHeight((double) newSceneHeight));

            ColumnConstraints c1 = new ColumnConstraints(widthScreen);

            RowConstraints r1 = new RowConstraints(heightScreen);
            RowConstraints r2 = new RowConstraints(heightScreen);

            grid.getColumnConstraints().add(c1);
            grid.getRowConstraints().addAll(r1, r2);

            Text msg = new Text(message);

            Button btnY = new Button("SI");
            Button btnN = new Button("NO");

            btnY.setOnAction(e -> {
                decision = "SI";
            });
            btnN.setOnAction(e -> {
                decision = "NO";
            });

            VBox v = new VBox(20);
            v.getChildren().add(btnY);
            v.getChildren().add(btnN);

            grid.add(msg, 0, 0);
            grid.add(v, 0, 1);

        }
    }

    public class infoWindow extends Stage {

        private boolean pu;

        public infoWindow(VirtualPlayer p, int i, boolean pu) {

            this.pu = pu;

            String weapon = null;
            String textWe = null;
            String powerup = null;
            String textPu = null;

            double widthScreen = Screen.getPrimary().getBounds().getWidth() / 3;
            double heightScreen = Screen.getPrimary().getBounds().getHeight() / 3;

            Group root = new Group();
            Scene theScene = new Scene(root);
            this.setScene(theScene);

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(0);
            grid.setVgap(0);
            grid.setPadding(new Insets(0, 0, 0, 0));

            try {
                Image back = new Image(new FileInputStream(PATH_BACK_GAME), 2190, 1920, true, true);
                BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                grid.setBackground(new Background(backgroundImage));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(grid, widthScreen + 50, heightScreen + 50);
            this.setScene(scene);

            scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> grid.setPrefWidth((double) newSceneWidth));
            scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> grid.setPrefHeight((double) newSceneHeight));

            ColumnConstraints c1 = new ColumnConstraints(widthScreen / 2);
            ColumnConstraints c2 = new ColumnConstraints(widthScreen / 2);

            RowConstraints r1 = new RowConstraints(heightScreen);

            grid.getColumnConstraints().addAll(c1, c2);
            grid.getRowConstraints().add(r1);

            ObjectMapper mapper = new ObjectMapper();


            if (!pu && i<p.getWeapons().size()) {


                File jsonFileWe = new File(PATH_WE);
                try {

                    JsonNode rootNodeWe = null;
                    try {
                        rootNodeWe = mapper.readTree(jsonFileWe);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    JsonNode chamberNodeWe = rootNodeWe.path(p.getWeapons().get(i).split(INNER_SEP)[0].toLowerCase());

                    weapon = chamberNodeWe.path("path").asText();
                    textWe = chamberNodeWe.path("info").asText();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                ImageView imgWe = null;
                try {
                    imgWe = new ImageView(new Image(new FileInputStream(PATH_WEAPON + weapon), widthScreen / 2 - 50, heightScreen - 50, true, true));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                ImageView imgWe2 = null;
                try {
                    imgWe2 = new ImageView(new Image(new FileInputStream(PATH_INFO + textWe), widthScreen / 2 + 20, heightScreen + 20, true, true));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                grid.add(imgWe, 0, 0);
                grid.add(imgWe2, 1, 0);


            } else if (pu && i<p.getPowerUps().size()) {


                File jsonFilePU = new File(PATH_PU);

                try {

                    JsonNode rootNodePu = null;
                    try {
                        rootNodePu = mapper.readTree(jsonFilePU);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (i < p.getPowerUps().size()) {
                        JsonNode chamberNodePu = rootNodePu.path(p.getPowerUps().get(i).split(INNER_SEP)[0].toLowerCase());

                        if (p.getPowerUps().get(i).split(INNER_SEP)[1].equals("R")) {
                            powerup = chamberNodePu.path("color").path("red").asText();
                        } else if (p.getPowerUps().get(i).split(":")[1].equals("B")) {
                            powerup = chamberNodePu.path("color").path("blue").asText();
                        } else if (p.getPowerUps().get(i).split(":")[1].equals("Y")) {
                            powerup = chamberNodePu.path("color").path("yellow").asText();
                        }
                        textPu = chamberNodePu.path("info").asText();

                        ImageView imgPu = null;
                        try {
                            imgPu = new ImageView(new Image(new FileInputStream(PATH_POWER_UP + powerup), widthScreen / 2 - 50, heightScreen - 50, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        ImageView imgPu2 = null;
                        try {
                            imgPu2 = new ImageView(new Image(new FileInputStream(PATH_POWER_UP + textPu), widthScreen / 2 + 20, heightScreen + 20, true, true));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        grid.add(imgPu, 0, 0);
                        grid.add(imgPu2, 1, 0);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public class chooseWeapon extends Stage {
        public chooseWeapon(ArrayList<String> args) {

            this.setTitle("SCEGLI UN'ARMA");

            double widthScreen = Screen.getPrimary().getBounds().getWidth() / 3;
            double heightScreen = Screen.getPrimary().getBounds().getHeight() / 3;

            Group root = new Group();
            Scene theScene = new Scene(root);
            this.setScene(theScene);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(0, 0, 0, 0));

            try {
                Image back = new Image(new FileInputStream(PATH_BACK_GAME), 2190, 1920, true, true);
                BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                grid.setBackground(new Background(backgroundImage));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(grid, widthScreen + 50, heightScreen + 50);
            this.setScene(scene);

            scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> grid.setPrefWidth((double) newSceneWidth));
            scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> grid.setPrefHeight((double) newSceneHeight));

            ColumnConstraints c1 = new ColumnConstraints(widthScreen / 3);
            ColumnConstraints c2 = new ColumnConstraints(widthScreen / 3);
            ColumnConstraints c3 = new ColumnConstraints(widthScreen / 3);

            RowConstraints r = new RowConstraints(heightScreen);

            grid.getColumnConstraints().addAll(c1, c2, c3);
            grid.getRowConstraints().add(r);

            Button we1 = new Button();
            Button we2 = new Button();
            Button we3 = new Button();
            we1.setPrefSize(widthScreen,heightScreen);
            we2.setPrefSize(widthScreen,heightScreen);
            we3.setPrefSize(widthScreen,heightScreen);
            hideBtn(we1,0);
            hideBtn(we2,0);
            hideBtn(we3,0);
            ArrayList<Button> btnArr = new ArrayList<>();
            btnArr.add(we1);
            btnArr.add(we2);
            btnArr.add(we3);

            ObjectMapper mapper = new ObjectMapper();
            String weapon = null;

            int numWe = 0;
            for (String s : args) {
                if(numWe > args.size()){
                    break;
                }
                File jsonFileWe = new File(PATH_WE);
                try {

                    JsonNode rootNodeWe = null;
                    try {
                        rootNodeWe = mapper.readTree(jsonFileWe);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    JsonNode chamberNodeWe = rootNodeWe.path(s.split(INNER_SEP)[0].toLowerCase());

                    weapon = chamberNodeWe.path("path").asText();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                Image imgWe = null;
                try {
                    imgWe = new Image(new FileInputStream(PATH_WEAPON + weapon), widthScreen, heightScreen, true, true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                setButtonBack(btnArr.get(numWe),imgWe);
                hideBtn(btnArr.get(numWe),1);
                btnArr.get(numWe).setOnAction(e->{
                    game.setWeapon(s);
                    this.close();
                });

            numWe++;
            }
            switch (args.size()){
                case(3):{
                    grid.add(btnArr.get(2),1,0);
                    args.remove(1);

                }
                case(2):{
                    grid.add(btnArr.get(1),0,0);
                    args.remove(0);

                }
                case(1):{
                    grid.add(btnArr.get(0),2,0);
                    args.remove(0);

                }
            }


        }
    }


    public class discardCards extends Stage {
        public discardCards(ArrayList<String> cards, boolean pu) {


            double widthScreen = Screen.getPrimary().getBounds().getWidth() / 3;
            double heightScreen = Screen.getPrimary().getBounds().getHeight() / 3;

            Group root = new Group();
            Scene theScene = new Scene(root);
            this.setScene(theScene);

            GridPane gridDisc = new GridPane();
            gridDisc.setAlignment(Pos.CENTER);
            gridDisc.setHgap(20);
            gridDisc.setVgap(0);
            gridDisc.setPadding(new Insets(50, 50, 50, 50));

            try {
                Image back = new Image(new FileInputStream(PATH_BACK_GAME), widthScreen * 3, heightScreen * 3, true, true);
                BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                gridDisc.setBackground(new Background(backgroundImage));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(gridDisc, widthScreen + 200, heightScreen + 100);
            this.setScene(scene);

            scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> gridDisc.setPrefWidth((double) newSceneWidth));
            scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> gridDisc.setPrefHeight((double) newSceneHeight));

            ColumnConstraints c1 = new ColumnConstraints(widthScreen / 4);
            ColumnConstraints c2 = new ColumnConstraints(widthScreen / 4);
            ColumnConstraints c3 = new ColumnConstraints(widthScreen / 4);
            ColumnConstraints c4 = new ColumnConstraints(widthScreen / 4);


            RowConstraints r = new RowConstraints(heightScreen);

            gridDisc.getColumnConstraints().addAll(c1, c2, c3, c4);
            gridDisc.getRowConstraints().add(r);

            Button btn1 = new Button();
            Button btn2 = new Button();
            Button btn3 = new Button();
            Button btn4 = new Button();
            btn1.setPrefSize(widthScreen / 4, heightScreen);
            btn2.setPrefSize(widthScreen / 4, heightScreen);
            btn3.setPrefSize(widthScreen / 4, heightScreen);
            btn4.setPrefSize(widthScreen / 4, heightScreen);
            hideBtn(btn1, 0);
            hideBtn(btn2, 0);
            hideBtn(btn3, 0);
            hideBtn(btn4, 0);
            ArrayList<Button> btnArr = new ArrayList<>();
            btnArr.add(btn1);
            btnArr.add(btn2);
            btnArr.add(btn3);
            btnArr.add(btn4);

            ObjectMapper mapper = new ObjectMapper();


            if (!pu) {

                this.setTitle("Scegli un'arma da scartare");

                File jsonFileWe = new File(PATH_WE);
                try {


                    JsonNode rootNodeWe = null;
                    try {
                        rootNodeWe = mapper.readTree(jsonFileWe);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    for (String name : cards) {

                        JsonNode chamberNodeWe = rootNodeWe.path(name.split(INNER_SEP)[0].toLowerCase());

                        String weapon = chamberNodeWe.path("path").asText();

                        Image imgWe = null;
                        try {
                            imgWe = new Image(new FileInputStream(PATH_WEAPON + weapon), widthScreen / 2 - 50, heightScreen - 50, true, true);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        setButtonBack(btnArr.get(cards.indexOf(name)),imgWe);
                        btnArr.get(cards.indexOf(name)).setOnAction(e->{
                            game.setWeapon(name.split(INNER_SEP)[0]);
                            this.close();
                        });
                        gridDisc.add(btnArr.get(cards.indexOf(name)),cards.indexOf(name),0);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else
                if(pu){

                    this.setTitle("Scegli un Power-up da scartare");

                    File jsonFilePU = new File(PATH_PU);

                    try {

                        JsonNode rootNodePu = null;
                        try {
                            rootNodePu = mapper.readTree(jsonFilePU);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int i = 0;
                        for (String n : cards) {
                            String name = n.toLowerCase();
                            String[] temp;
                            temp=name.split(INNER_SEP);
                            JsonNode chamberNodePu = rootNodePu.path(temp[0]);
                            String powerup = null;
                            if (temp[1].equals("r")) {
                                powerup = chamberNodePu.path("color").path("red").asText();
                            } else if (temp[1].equals("b")) {
                                powerup = chamberNodePu.path("color").path("blue").asText();
                            } else if (temp[1].equals("y")) {
                                powerup = chamberNodePu.path("color").path("yellow").asText();
                            }

                            Image imgPu = null;
                            try {
                                imgPu = new Image(new FileInputStream(PATH_POWER_UP + powerup), widthScreen / 2 - 50, heightScreen - 50, true, true);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            final int key = i;
                            setButtonBack(btnArr.get(i),imgPu);
                            Runnable run = () -> btnArr.get(key).setOnAction(e->{
                                game.setPowerup(n);
                                this.close();
                            });

                            Platform.runLater(run);

                            gridDisc.add(btnArr.get(i),i,0);
                            i++;
                        }

                        } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    public boolean isConnected(){
        return true;
    }
}