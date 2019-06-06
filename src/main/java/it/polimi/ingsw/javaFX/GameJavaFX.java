package it.polimi.ingsw.javaFX;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.power_up.Teleporter;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.virtual_model.VirtualLobby;
import it.polimi.ingsw.virtual_model.VirtualPlayer;
import it.polimi.ingsw.virtual_model.VirtualPlayerBoard;
import javafx.application.Application;
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
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/*

CONTORNO ROSSO!!

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.RED);
        borderGlow.setHeight(50);
        borderGlow.setWidth(50);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        imgOther1.setEffect(borderGlow);

CONTORNO ROSSO!!



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
public class GameJavaFX extends Application {

    /**
     * All Path.
     */
    private static final String PATH_BACK_GAME = "src/main/resources/images/background/game_background.jpg";
    private static final String PATH_BACK = "src/main/resources/images/background/background.jpg";
    private static final String PATH_BACK_WEAPON = "src/main/resources/images/background/weapon.png";
    private static final String PATH_BACK_POWERUP = "src/main/resources/images/background/powerup.png";
    private static final String PATH_BACK_MSG = "src/main/resources/images/background/img854.png";
    private static final String PATH_BACK_POINTS = "src/main/resources/images/background/img5006.jpg";

    private static final String PATH_SMALL_MAP = "src/main/resources/images/map/2.png";
    private static final String PATH_MEDIUM1_MAP = "src/main/resources/images/map/1.png";
    private static final String PATH_MEDIUM2_MAP = "src/main/resources/images/map/4.png";
    private static final String PATH_LARGE_MAP = "src/main/resources/images/map/3.png";

    private static final String PATH_YELLOW_BOARD = "src/main/resources/images/board/yellow_board.png";
    private static final String PATH_RED_BOARD = "src/main/resources/images/board/red_board.png";
    private static final String PATH_BLUE_BOARD = "src/main/resources/images/board/blue_board.png";
    private static final String PATH_GREEN_BOARD = "src/main/resources/images/board/green_board.png";
    private static final String PATH_GREY_BOARD = "src/main/resources/images/board/gray_board.png";

    private static final String PATH_GENERAL_COLOR = "src/main/resources/images/character/";
    private static final String PATH_YELLOW = "src/main/resources/images/character/yellow.png";
    private static final String PATH_RED = "src/main/resources/images/character/red.png";
    private static final String PATH_BLUE = "src/main/resources/images/character/blue.png";
    private static final String PATH_GREEN = "src/main/resources/images/character/green.png";
    private static final String PATH_GREY = "src/main/resources/images/character/gray.png";
    private static final String PATH_YELLOW_CHARACTER = "src/main/resources/images/character/D-struct-0R.png";
    private static final String PATH_RED_CHARACTER = "src/main/resources/images/character/violet.png";
    private static final String PATH_BLUE_CHARACTER = "src/main/resources/images/character/banshee.png";
    private static final String PATH_GREEN_CHARACTER = "src/main/resources/images/character/sprog.png";
    private static final String PATH_GREY_CHARACTER = "src/main/resources/images/character/dozer.png";

    private static final String PATH_RED_AMMO = "src/main/resources/images/ammo/red_ammo.png";
    private static final String PATH_BLUE_AMMO = "src/main/resources/images/ammo/blue_ammo.png";
    private static final String PATH_YELLOW_AMMO = "src/main/resources/images/ammo/yellow_ammo.png";

    private static final String PATH_YELLOW_DAMAGE = "src/main/resources/images/damage/yellow.png";
    private static final String PATH_RED_DAMAGE = "src/main/resources/images/damage/red.png";
    private static final String PATH_BLUE_DAMAGE = "src/main/resources/images/damage/blue.png";
    private static final String PATH_GREEN_DAMAGE = "src/main/resources/images/damage/green.png";
    private static final String PATH_GREY_DAMAGE = "src/main/resources/images/damage/grey.png";
    private static final String PATH_YELLOW_DAMAGE_DOUBLE = "src/main/resources/images/damage/yellow_double.png";
    private static final String PATH_RED_DAMAGE_DOUBLE = "src/main/resources/images/damage/red_double.png";
    private static final String PATH_BLUE_DAMAGE_DOUBLE = "src/main/resources/images/damage/blue_double.png";
    private static final String PATH_GREEN_DAMAGE_DOUBLE = "src/main/resources/images/damage/green_double.png";
    private static final String PATH_GREY_DAMAGE_DOUBLE = "src/main/resources/images/damage/grey_double.png";

    private static final String PATH_WEAPON = "src/main/resources/images/weapon/";
    private static final String PATH_POWER_UP = "src/main/resources/images/power-up/";

    private static final String PATH_TITLE = "src/main/resources/images/title.png";
    private static final String PATH_TRACK = "src/main/resources/images/killshotrack.png";
    private static final String PATH_SKULL = "src/main/resources/images/skull.png";
    private static final String PATH_LOADING = "src/main/resources/images/loading.png";
    private static final String PATH_RULES = "src/main/resources/images/rules.jpg";
    private static final String PATH_LOGIN = "src/main/resources/images/login.png";
    private static final String PATH_SETTINGS = "src/main/resources/images/settings.png";

    private int map = 3;
    private javafx.scene.text.Font font = new Font(20);
    private int skullMax = 8;

    private VirtualLobby lobby;

    private boolean turn = false;
    private boolean move = false;
    private boolean pick = false;
    private boolean shoot = false;

    private VirtualPlayer player;
    private ArrayList<VirtualPlayer> players;
    private int points;

    public void setLobby(VirtualLobby lobby) {
        this.lobby = lobby;

        /*

        player = lobby.getOwner();
        players = lobby.getNewPlayersList();
        points = player.getPoints();
        skullMax = lobby.getKillPref();

         */

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        ///*

        points = 0;
        player = new VirtualPlayer("Mariangiangiangela", "yellow");
        players = new ArrayList<>();
        players.add(player);
        players.add(new VirtualPlayer("AlbertoAngela","blue"));
        players.add(new VirtualPlayer("Mussolini","red"));
        players.add(new VirtualPlayer("MioPadre","green"));
        players.add(new VirtualPlayer("Bignè","grey"));
        //System.out.println(players.get(4).getCharacter());

        ArrayList<String> damage = new ArrayList<>();
        damage.add("red");
        damage.add("red");
        damage.add("yellow");
        damage.add("blue");
        damage.add("green");
        damage.add("grey");
        damage.add("blue");
        damage.add("blue");
        damage.add("green");
        damage.add("green");
        damage.add("green");
        damage.add("green");

        ArrayList<String> marks = new ArrayList<>();
        marks.add("red");
        marks.add("red");
        marks.add("yellow");

        player.getpBoard().setMarks(marks);
        player.getpBoard().setDamage(damage);
        player.getpBoard().setSkulls(7);

        // */

        primaryStage.setTitle("ADRENALINA");

        double widthScreen = Screen.getPrimary().getBounds().getWidth();
        double heightScreen = Screen.getPrimary().getBounds().getHeight();

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

        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> grid.setPrefWidth((double)newSceneWidth));
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> grid.setPrefHeight((double)newSceneHeight));

        /**
         * set Background.
         */
        try {
            Image back = new Image(new FileInputStream(PATH_BACK_GAME), 2190, 1920, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            grid.setBackground(new Background(backgroundImage));
        }catch (IOException e){
            e.printStackTrace();
        }

        /**
         * set space in grid.
         */
        double widthLateral = widthScreen/4;
        double widthCenter = widthScreen/2;
        ColumnConstraints c1 = new ColumnConstraints(widthLateral);
        ColumnConstraints c2 = new ColumnConstraints(widthCenter);
        ColumnConstraints c3 = new ColumnConstraints(widthLateral);
        c1.setPercentWidth(25);
        c2.setPercentWidth(50);
        c3.setPercentWidth(25);


        double heightLateral = heightScreen/6;
        double heightCenter = heightScreen/2;
        RowConstraints r1 = new RowConstraints(heightLateral);
        RowConstraints r2 = new RowConstraints(heightCenter);
        RowConstraints r3 = new RowConstraints(heightLateral);
        r1.setPercentHeight(25);
        r2.setPercentHeight(50);
        r3.setPercentHeight(25);


        grid.getColumnConstraints().addAll(c1,c2,c3);
        grid.getRowConstraints().addAll(r1,r2,r3);

        /**
         * set title.
         */
        ImageView imgTitle = new ImageView(new Image(new FileInputStream(PATH_TITLE),widthCenter,heightLateral,true,true));
        grid.add(imgTitle,1,0);

        /**
         * set points.
         */
        TextField pointsField = new TextField();
        Image pointsBack = new Image(new FileInputStream(PATH_BACK_POINTS),100,100,true,true);
        BackgroundImage backgroundPoints = new BackgroundImage(pointsBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backPoints = new Background(backgroundPoints);

        pointsField.setBackground(backPoints);
        pointsField.setPrefSize(widthLateral/3,heightLateral/3);
        pointsField.setText("Punti: " + points);
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

        grid.add(pointsField,2,0);

        /**
         * set Killshot track.
         */
        GridPane gridSkull = new GridPane();
        gridSkull.setPadding(new Insets(100, 0, 80, 0));

        Image imgTrack = new Image(new FileInputStream(PATH_TRACK),widthLateral,heightLateral,true,true);
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

        fillSkulls(gridSkull,skullMax, widthSkull-5,imgTrack.getHeight()-25);

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
                imgBoard = new Image(new FileInputStream(PATH_MEDIUM1_MAP),widthCenter,heightCenter,true,true);
                break;
            }
            case 2:{
                imgBoard = new Image(new FileInputStream(PATH_SMALL_MAP),widthCenter,heightCenter,true,true);
                break;
            }
            case 3:{
                imgBoard = new Image(new FileInputStream(PATH_LARGE_MAP),widthCenter,heightCenter,true,true);
                break;
            }
            case 4:{
                imgBoard = new Image(new FileInputStream(PATH_MEDIUM2_MAP),widthCenter,heightCenter,true,true);
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


        ArrayList<ArrayList<Button>> btnCell = new ArrayList<>();
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

        double widthPers = widthLateral;
        double heightPBoard = heightCenter/3;
        double heightPCards = heightPBoard *2;

        ColumnConstraints pc1 = new ColumnConstraints(widthPers);

        RowConstraints pr1=new RowConstraints(heightBoard);
        RowConstraints pr2=new RowConstraints(heightPCards);

        gridPers.getColumnConstraints().add(pc1);
        gridPers.getRowConstraints().addAll(pr1,pr2);

        Image imgPBoard = null;

        GridPane gridPBoard = new GridPane();

        switch (player.getCharacter()){
            case "yellow":{
                imgPBoard = new Image(new FileInputStream(PATH_YELLOW_BOARD),widthPers,heightPBoard,true,true);
                break;
            }
            case "red":{
                imgPBoard = new Image(new FileInputStream(PATH_RED_BOARD),widthPers,heightPBoard,true,true);
                break;
            }
            case "blue":{
                imgPBoard = new Image(new FileInputStream(PATH_BLUE_BOARD),widthPers,heightPBoard,true,true);
                break;
            }
            case "green":{
                imgPBoard = new Image(new FileInputStream(PATH_GREEN_BOARD),widthPers,heightPBoard,true,true);
                break;
            }
            case "grey":{
                imgPBoard = new Image(new FileInputStream(PATH_GREY_BOARD),widthPers,heightPBoard,true,true);
                break;
            }
        }

        BackgroundImage backgroundPB = new BackgroundImage(imgPBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backPB = new Background(backgroundPB);

        gridPBoard.setBackground(backPB);

        setCellBoard(gridPBoard,widthPers,heightPBoard-50);
        fillBoard(gridPBoard,player,widthPers,heightPBoard/4-6,heightPBoard/4,heightPBoard/3-6);

        gridPers.add(gridPBoard,0,0);

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


        Image imgWe = null;
        Image imgPPU = null;

        try {
            imgWe = new Image(new FileInputStream(PATH_BACK_WEAPON),widthCard,heightCard,true,true);
            imgPPU = new Image(new FileInputStream(PATH_BACK_POWERUP),widthCard-20,heightCard-20,true,true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BackgroundImage backgroundOWe = new BackgroundImage(imgWe, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backWe = new Background(backgroundOWe);
        BackgroundImage backgroundPPu = new BackgroundImage(imgPPU, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backPPu = new Background(backgroundPPu);
        Button btnPwe1 = new Button();
        Button btnPwe2 = new Button();
        Button btnPwe3 = new Button();
        Button btnPpu1 = new Button();
        Button btnPpu2 = new Button();
        Button btnPpu3 = new Button();

        btnPwe1.setPrefSize(widthCard,heightCard);
        btnPwe2.setPrefSize(widthCard,heightCard);
        btnPwe3.setPrefSize(widthCard,heightCard);
        btnPpu1.setPrefSize(widthCard,heightCard);
        btnPpu2.setPrefSize(widthCard,heightCard);
        btnPpu3.setPrefSize(widthCard,heightCard);

        btnPwe1.setBackground(backWe);
        btnPwe2.setBackground(backWe);
        btnPwe3.setBackground(backWe);
        btnPpu1.setBackground(backPPu);
        btnPpu2.setBackground(backPPu);
        btnPpu3.setBackground(backPPu);

        gridCards.add(btnPwe1,0,0);
        gridCards.add(btnPwe2,1,0);
        gridCards.add(btnPwe3,2,0);
        gridCards.add(btnPpu1,0,1);
        gridCards.add(btnPpu2,1,1);
        gridCards.add(btnPpu3,2,1);

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

        fillAmmo(gridPAmmo,player.getpBoard(),widthAmmo,heightAmmo);
        grid.add(gridPAmmo,0,2);



        /**
         * set other boards
         */
        GridPane gridOther = new GridPane();
        gridOther.setAlignment(Pos.CENTER);

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

        grid.add(gridOther,2,1);

        GridPane gridOtherSpace1 = setOtherspace(widthOther,heightOther);
        GridPane gridOtherSpace2 = setOtherspace(widthOther,heightOther);
        GridPane gridOtherSpace3 = setOtherspace(widthOther,heightOther);
        GridPane gridOtherSpace4 = setOtherspace(widthOther,heightOther);
        GridPane gridOtherSpace5 = setOtherspace(widthOther,heightOther);


        gridOther.add(gridOtherSpace1,0,0);
        gridOther.add(gridOtherSpace2,0,1);
        gridOther.add(gridOtherSpace3,0,2);
        gridOther.add(gridOtherSpace4,0,3);
        gridOther.add(gridOtherSpace5,0,4);

        double widthOtherWeapon = widthCard/3;
        double heightOtherWeapon = heightCenter/5;

        GridPane gridOtherWe = new GridPane();

        ColumnConstraints owc1 = new ColumnConstraints(widthOtherWeapon);
        ColumnConstraints owc2 = new ColumnConstraints(widthOtherWeapon);
        ColumnConstraints owc3 = new ColumnConstraints(widthOtherWeapon);

        RowConstraints owr1 = new RowConstraints(heightOtherWeapon);
        RowConstraints owr2 = new RowConstraints(heightOtherWeapon);
        RowConstraints owr3 = new RowConstraints(heightOtherWeapon);
        RowConstraints owr4 = new RowConstraints(heightOtherWeapon);
        RowConstraints owr5 = new RowConstraints(heightOtherWeapon);

        gridOtherWe.getColumnConstraints().addAll(owc1,owc2,owc3);
        gridOtherWe.getRowConstraints().addAll(owr1,owr2,owr3,owr4,owr5);


        gridOther.add(gridOtherWe,1,0,1,5);

        double heightOtherAmmo = heightOther/5;
        double heightOtherBoard = heightOtherAmmo * 4;

        GridPane gridOtherAmmo1 = new GridPane();
        GridPane gridOtherAmmo2 = new GridPane();
        GridPane gridOtherAmmo3 = new GridPane();
        GridPane gridOtherAmmo4 = new GridPane();
        GridPane gridOtherAmmo5 = new GridPane();
        setOtherAmmo(gridOtherAmmo1,widthOther,heightOtherAmmo);
        setOtherAmmo(gridOtherAmmo2,widthOther,heightOtherAmmo);
        setOtherAmmo(gridOtherAmmo3,widthOther,heightOtherAmmo);
        setOtherAmmo(gridOtherAmmo4,widthOther,heightOtherAmmo);
        setOtherAmmo(gridOtherAmmo5,widthOther,heightOtherAmmo);

        GridPane gridOtherBoard1 = new GridPane();
        GridPane gridOtherBoard2 = new GridPane();
        GridPane gridOtherBoard3 = new GridPane();
        GridPane gridOtherBoard4 = new GridPane();
        GridPane gridOtherBoard5 = new GridPane();


        Image imgOther1;
        Image imgOther2;
        Image imgOther3;
        Image imgOther4;
        Image imgOther5;

        ArrayList<Button> weOther1 = new ArrayList<>();
        ArrayList<Button> weOther2 = new ArrayList<>();
        ArrayList<Button> weOther3 = new ArrayList<>();
        ArrayList<Button> weOther4 = new ArrayList<>();
        ArrayList<Button> weOther5 = new ArrayList<>();


        for (VirtualPlayer p : players ) {
            if(!p.getCharacter().equals(player.getCharacter())){
                switch (p.getCharacter()) {
                    case "yellow": {
                        imgOther1 = setBoard(p.getCharacter(), widthOther, heightOtherBoard);
                        setGridBack(gridOtherBoard1,imgOther1);
                        fillOtherAmmo(gridOtherAmmo1,p.getpBoard(),heightOtherAmmo,heightOtherAmmo);
                        weOther1 = setOtherWeapon(gridOtherWe,0,widthOtherWeapon,heightOtherWeapon);
                        break;
                    }
                    case "red": {
                        imgOther2 = setBoard(p.getCharacter(), widthOther, heightOtherBoard);
                        setGridBack(gridOtherBoard2,imgOther2);
                        fillOtherAmmo(gridOtherAmmo2,p.getpBoard(),heightOtherAmmo,heightOtherAmmo);
                        weOther2 = setOtherWeapon(gridOtherWe,1,widthOtherWeapon,heightOtherWeapon);
                        break;
                    }
                    case "blue": {
                        imgOther3 = setBoard(p.getCharacter(), widthOther, heightOtherBoard);
                        setGridBack(gridOtherBoard3,imgOther3);
                        fillOtherAmmo(gridOtherAmmo3,p.getpBoard(),heightOtherAmmo,heightOtherAmmo);
                        weOther3 = setOtherWeapon(gridOtherWe,2,widthOtherWeapon,heightOtherWeapon);
                        break;
                    }
                    case "green": {
                        imgOther4 = setBoard(p.getCharacter(), widthOther, heightOtherBoard);
                        setGridBack(gridOtherBoard4,imgOther4);
                        fillOtherAmmo(gridOtherAmmo4,p.getpBoard(),heightOtherAmmo,heightOtherAmmo);
                        weOther4 = setOtherWeapon(gridOtherWe,3,widthOtherWeapon,heightOtherWeapon);
                        break;
                    }
                    case "grey": {
                        imgOther5 = setBoard(p.getCharacter(), widthOther, heightOtherBoard);
                        setGridBack(gridOtherBoard5,imgOther5);
                        fillOtherAmmo(gridOtherAmmo5,p.getpBoard(),heightOtherAmmo,heightOtherAmmo);
                        weOther5 = setOtherWeapon(gridOtherWe,4,widthOtherWeapon,heightOtherWeapon);
                        break;
                    }
                }
            }
        }

        ArrayList<ArrayList<Button>> weOther = new ArrayList<>();
        weOther.add(weOther1);
        weOther.add(weOther2);
        weOther.add(weOther3);
        weOther.add(weOther4);
        weOther.add(weOther5);


        gridOtherSpace1.add(gridOtherBoard1,0,0);
        gridOtherSpace1.add(gridOtherAmmo1,0,1);
        gridOtherSpace2.add(gridOtherBoard2,0,0);
        gridOtherSpace2.add(gridOtherAmmo2,0,1);
        gridOtherSpace3.add(gridOtherBoard3,0,0);
        gridOtherSpace3.add(gridOtherAmmo3,0,1);
        gridOtherSpace4.add(gridOtherBoard4,0,0);
        gridOtherSpace4.add(gridOtherAmmo4,0,1);
        gridOtherSpace5.add(gridOtherBoard5,0,0);
        gridOtherSpace5.add(gridOtherAmmo5,0,1);

        setCellBoard(gridOtherBoard1,widthOther,heightOtherBoard);
        setCellBoard(gridOtherBoard2,widthOther,heightOtherBoard);
        setCellBoard(gridOtherBoard3,widthOther,heightOtherBoard);
        setCellBoard(gridOtherBoard4,widthOther,heightOtherBoard);
        setCellBoard(gridOtherBoard5,widthOther,heightOtherBoard);


        /**
         * set deck
         */
        Image imgDeck = new Image(new FileInputStream(PATH_BACK_POWERUP),widthCard,widthCard,true,true);
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
        Image msgBack = new Image(new FileInputStream(PATH_BACK_MSG),300,100,true,true);
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

        /**
         * set buttons' action.
         */

        for (ArrayList<Button> btnArr: btnCell){
            for (Button btn : btnArr) {
                btn.setOnAction(e->{System.out.println("ok");});
            }

        }

        btnPwe1.setOnAction(e->{
            if(shoot){

            }else {
                infoWindow iw = new infoWindow(false);
                iw.show();
            }
        });
        btnPwe2.setOnAction(e->{
            if(shoot){

            }else {
                infoWindow iw = new infoWindow(false);
                iw.show();
            }
        });
        btnPwe3.setOnAction(e->{
            if(shoot){

            }else {
                infoWindow iw = new infoWindow(false);
                iw.show();
            }
        });
        btnPpu1.setOnAction(e->{
            infoWindow iw = new infoWindow(true);
            iw.show();
        });
        btnPpu2.setOnAction(e->{
            infoWindow iw = new infoWindow(true);
            iw.show();
        });
        btnPpu3.setOnAction(e->{
            infoWindow iw = new infoWindow(true);
            iw.show();
        });

        for (ArrayList<Button> btnArr : weOther){
            for (Button btn : btnArr) {
                btn.setOnAction(e->{
                    infoWindow iw = new infoWindow(false);
                    iw.show();
                });
            }

        }

        btnMove.setOnAction(e->{
            move = true;
            System.out.println("ok");
        });
        btnPick.setOnAction(e->{
            pick = true;
            System.out.println("ok");
        });
        btnShoot.setOnAction(e->{
            shoot = true;
            System.out.println("ok");
        });

        btnDeck.setOnAction(e->{System.out.println("ok");});

        // ** HARDCODED TEST **
        setPlayerOnCell(btnCell.get(0),player.getCharacter());
        setPlayerOnCell(btnCell.get(0),players.get(1).getCharacter());
        setPlayerOnCell(btnCell.get(0),players.get(2).getCharacter());
        setPlayerOnCell(btnCell.get(0),players.get(3).getCharacter());
        setPlayerOnCell(btnCell.get(0),players.get(4).getCharacter());

        hideCell(btnCell.get(1));
        hideCell(btnCell.get(2));
        hideCell(btnCell.get(4));
        hideCell(btnCell.get(5));


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

    public GridPane setOtherspace(double widthBoard, double heightBoard){

        GridPane grid = new GridPane();

        double heightAmmo = heightBoard/3;
        double heightOther = heightAmmo * 2;

        ColumnConstraints c1 = new ColumnConstraints(widthBoard);

        RowConstraints r1 = new RowConstraints(heightOther);
        RowConstraints r2 = new RowConstraints(heightAmmo);

        grid.getColumnConstraints().add(c1);
        grid.getRowConstraints().addAll(r1,r2);


        return grid;
    }

    public Image setBoard(String color, double width, double height){

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
        }catch (IOException e){
            e.printStackTrace();
        }

        return img;
    }


    public void setGridBack(GridPane grid, Image img ){
        BackgroundImage background = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background back = new Background(background);
        grid.setBackground(back);
    }

    public void setCellBoard(GridPane grid, double widthOtherBoard, double heightOtherBoard){

        grid.setAlignment(Pos.CENTER_LEFT);


        double width = widthOtherBoard/19;

        ColumnConstraints c1 = new ColumnConstraints(width);
        ColumnConstraints c2 = new ColumnConstraints(width);
        ColumnConstraints c3 = new ColumnConstraints(width);
        ColumnConstraints c4 = new ColumnConstraints(width);
        ColumnConstraints c5 = new ColumnConstraints(width);
        ColumnConstraints c6 = new ColumnConstraints(width);
        ColumnConstraints c7 = new ColumnConstraints(width);
        ColumnConstraints c8 = new ColumnConstraints(width);
        ColumnConstraints c9= new ColumnConstraints(width);
        ColumnConstraints c10 = new ColumnConstraints(width);
        ColumnConstraints c11 = new ColumnConstraints(width);
        ColumnConstraints c12 = new ColumnConstraints(width);
        ColumnConstraints c13 = new ColumnConstraints(width);
        ColumnConstraints c14 = new ColumnConstraints(width);


        RowConstraints r1 = new RowConstraints(heightOtherBoard/3-6);
        RowConstraints r2 = new RowConstraints(heightOtherBoard/3);
        RowConstraints r3 = new RowConstraints(heightOtherBoard/3-6);


        grid.getColumnConstraints().addAll(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14);
        grid.getRowConstraints().addAll(r1,r2,r3);


    }


    public void fillAmmo(GridPane grid, VirtualPlayerBoard board, double w, double h){
        int red = board.getAmmoRed();
        int blue = board.getAmmoBlue();
        int yellow = board.getAmmoYellow();

        while(red > 0){
            ImageView imgAR = null;

            try {
                imgAR = new ImageView(new Image(new FileInputStream(PATH_RED_AMMO),w,h,true,true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAR,3-red,0);
            red--;
        }
        while(blue > 0){
            ImageView imgAB = null;

            try {
                imgAB = new ImageView(new Image(new FileInputStream(PATH_BLUE_AMMO),w,h,true,true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAB,3-blue,1);
            blue--;
        }
        while(yellow > 0){
            ImageView imgAY = null;

            try {
                imgAY = new ImageView(new Image(new FileInputStream(PATH_YELLOW_AMMO),w,h,true,true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAY,3-yellow,2);
            yellow--;
        }
    }

    public void setOtherAmmo(GridPane grid, double widthOther, double heightOther){

        double width = widthOther/9;

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

        grid.getColumnConstraints().addAll(c1,c2,c3,c4,c5,c6,c7,c8,c9);
        grid.getRowConstraints().add(r1);

    }

    private void fillOtherAmmo(GridPane grid, VirtualPlayerBoard board, double w, double h) {

        int red = board.getAmmoRed();
        int blue = board.getAmmoBlue();
        int yellow = board.getAmmoYellow();

        while(red > 0){
            ImageView imgAR = null;

            try {
                imgAR = new ImageView(new Image(new FileInputStream(PATH_RED_AMMO),w,h,true,true));
                            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAR,3-red,0);
            red--;
        }
        while(blue > 0){
            ImageView imgAB = null;

            try {
                imgAB = new ImageView(new Image(new FileInputStream(PATH_BLUE_AMMO),w,h,true,true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAB,6-blue,0);
            blue--;
        }
        while(yellow > 0){
            ImageView imgAY = null;

            try {
                imgAY = new ImageView(new Image(new FileInputStream(PATH_YELLOW_AMMO),w,h,true,true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            grid.add(imgAY,9-yellow,0);
            yellow--;
        }
    }

    public ArrayList<Button> setOtherWeapon (GridPane grid, int row, double w, double h){

        ArrayList<Button> res = new ArrayList<>();

        Image imgDeck = null;
        try {
            imgDeck = new Image(new FileInputStream(PATH_BACK_WEAPON),w,h,true,true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BackgroundImage backgroundDeck = new BackgroundImage(imgDeck, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backDeck = new Background(backgroundDeck);

        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        btn1.setPrefSize(w,h);
        btn2.setPrefSize(w,h);
        btn3.setPrefSize(w,h);
        btn1.setBackground(backDeck);
        btn2.setBackground(backDeck);
        btn3.setBackground(backDeck);
        grid.add(btn1,0,row);
        grid.add(btn2,1,row);
        grid.add(btn3,2,row);

        res.add(btn1);
        res.add(btn2);
        res.add(btn3);

        return res;
    }

    public void setPlayerOnCell(ArrayList<Button> btn, String color){

        final int R = 4;
        final int B = 3;
        final int Y = 0;
        final int G = 2;
        final int GR = 1;

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

        switch(color){
            case("red"):{
                setButtonBack(btn.get(R),img);
                btn.get(R).setOpacity(1);
                btn.get(R).setEffect(borderGlow);
                break;
            }
            case("blue"):{
                setButtonBack(btn.get(B),img);
                btn.get(B).setOpacity(1);
                btn.get(B).setEffect(borderGlow);
                break;
            }case("yellow"):{
                setButtonBack(btn.get(Y),img);
                btn.get(Y).setOpacity(1);
                btn.get(Y).setEffect(borderGlow);
                break;
            }case("green"):{
                setButtonBack(btn.get(G),img);
                btn.get(G).setOpacity(1);
                btn.get(G).setEffect(borderGlow);
                break;
            }case("grey"):{
                setButtonBack(btn.get(GR),img);
                btn.get(GR).setOpacity(1);
                btn.get(GR).setEffect(borderGlow);
                break;
            }
        }


    }

    public void setButtonBack(Button btn,Image img ){
        BackgroundImage background = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background back = new Background(background);
        btn.setBackground(back);
    }

    public void fillSkulls(GridPane grid, int skullMax, double w, double h) {

        int i = skullMax - 1;

        while(i >= 0){

            ImageView skull = null;

            try {
                skull = new ImageView(new Image(new FileInputStream(PATH_SKULL), w, h, true, true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            grid.add(skull,i,0);
            i--;
        }
    }

    public void fillSkulls(GridPane grid, int skullMax, double w, double h, String color, boolean two) {

    }

    public void fillBoard(GridPane grid, VirtualPlayer p, double width, double hmarks, double hdamage, double hskulls){

        int i = 10;
        int j = 2;
        int k = 2;
        int no = 3;

        if(!p.getpBoard().getMarks().equals(null))
        for (String color : p.getpBoard().getMarks()) {
            switch (color){
                case "yellow":{
                    ImageView img = null;

                    try {
                        img = new ImageView(new Image(new FileInputStream(PATH_YELLOW_DAMAGE), width, hmarks, true, true));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    grid.add(img,i,0);
                    break;
                }
                case "red":{
                    ImageView img = null;

                    try {
                        img = new ImageView(new Image(new FileInputStream(PATH_RED_DAMAGE), width, hmarks, true, true));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    grid.add(img,i,0);
                    break;
                }case "blue":{
                    ImageView img = null;

                    try {
                        img = new ImageView(new Image(new FileInputStream(PATH_BLUE_DAMAGE), width, hmarks, true, true));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    grid.add(img,i,0);
                    break;
                }case "green":{
                    ImageView img = null;

                    try {
                        img = new ImageView(new Image(new FileInputStream(PATH_GREEN_DAMAGE), width, hmarks, true, true));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    grid.add(img,i,0);
                    break;
                }case "grey":{
                    ImageView img = null;

                    try {
                        img = new ImageView(new Image(new FileInputStream(PATH_GREY_DAMAGE), width, hmarks, true, true));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    grid.add(img,i,0);
                    break;
                }
            }
            i++;
        }

        if(!p.getpBoard().getDamage().equals(null))
        for (String color : p.getpBoard().getDamage()) {
            switch(color){
                case "yellow":{
                    ImageView img = null;

                    try {
                        img = new ImageView(new Image(new FileInputStream(PATH_YELLOW_DAMAGE), width, hmarks, true, true));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    grid.add(img,j,1);
                    break;
                }
                case "red":{
                    ImageView img = null;

                    try {
                        img = new ImageView(new Image(new FileInputStream(PATH_RED_DAMAGE), width, hmarks, true, true));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    grid.add(img,j,1);
                    break;
                }
                case "blue":{
                    ImageView img = null;

                    try {
                        img = new ImageView(new Image(new FileInputStream(PATH_BLUE_DAMAGE), width, hmarks, true, true));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    grid.add(img,j,1);
                    break;
                }
                case "green":{
                    ImageView img = null;

                    try {
                        img = new ImageView(new Image(new FileInputStream(PATH_GREEN_DAMAGE), width, hmarks, true, true));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    grid.add(img,j,1);
                    break;
                }
                case "grey":{
                    ImageView img = null;

                    try {
                        img = new ImageView(new Image(new FileInputStream(PATH_GREY_DAMAGE), width, hmarks, true, true));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    grid.add(img,j,1);
                    break;
                }
            }
            j++;
        }
        while(k <= p.getpBoard().getSkulls() + 2){

            if(k == no){
                k++;
                continue;
            }

            ImageView skull = null;

            try {
                skull = new ImageView(new Image(new FileInputStream(PATH_SKULL), width-25, hskulls-25, true, true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            grid.add(skull,k,2);
            k++;
        }
    }

    public void hideCell(ArrayList<Button> btn){
        for (Button b : btn) {
            b.setOpacity(0.5);
        }
    }

    public class infoWindow extends Stage {

        private boolean pu;

        public infoWindow(boolean pu) {

            this.pu = pu;

            //hardcoded test.
            Ammo a = new Ammo(1, 0, 0);
            Weapon w = new Weapon("ZX-2", a);
            Teleporter t = new Teleporter(a, "teleporter");


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

            if (!pu) {
                final String PATH_WE = "src/main/resources/data_files/gui_data/weapon.json";
                File jsonFileWe = new File(PATH_WE);
                try {

                    JsonNode rootNodeWe = null;
                    try {
                        rootNodeWe = mapper.readTree(jsonFileWe);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    JsonNode chamberNodeWe = rootNodeWe.path(w.getName());

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
                    imgWe2 = new ImageView(new Image(new FileInputStream(PATH_WEAPON + textWe), widthScreen / 2 + 20, heightScreen + 20, true, true));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                grid.add(imgWe, 0, 0);
                grid.add(imgWe2, 1, 0);

            } else if(pu){


                final String PATH_PU = "src/main/resources/data_files/gui_data/power_up_data.json";
                File jsonFilePU = new File(PATH_PU);

                try {

                    JsonNode rootNodePu = null;
                    try {
                        rootNodePu = mapper.readTree(jsonFilePU);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    JsonNode chamberNodePu = rootNodePu.path(t.getName());

                    Ammo r = new Ammo(1, 0, 0);
                    Ammo b = new Ammo(0, 1, 0);
                    Ammo y = new Ammo(0, 0, 1);


                    if(t.getAmmoOnDiscard().getRed() == r.getRed()){
                        powerup = chamberNodePu.path("color").path("red").asText();
                    } else
                        if (t.getAmmoOnDiscard().getBlue() == b.getBlue()){
                            powerup = chamberNodePu.path("color").path("blue").asText();
                        }else
                            if (t.getAmmoOnDiscard().getYellow() == y.getYellow()){
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


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
