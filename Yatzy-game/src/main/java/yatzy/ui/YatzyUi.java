package yatzy.ui;

import yatzy.domain.Game;
//import yatzy.domain.Player;
import yatzy.domain.Category;
import yatzy.domain.Dice;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.util.Scanner;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;



public class YatzyUi extends Application {

    private Dice dice;
    //private Player player;
    int nOf_players;
    Scanner input;
    private Game game;
    private Stage window;
    private static final int SCREEN_WIDTH = 700;
    private static final int SCREEN_HEIGHT = 700;
    
    public YatzyUi() {
        this.dice = new Dice();
        this.nOf_players = -1;
        this.input = new Scanner(System.in);
        
        
        
    }
    
    @Override
    public void start(Stage window) throws Exception {
        this.window = window;
        setWelcomeScene();
        
        
        /*
        BorderPane welcomeLayout = new BorderPane();
        
        welcomeLayout.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        
        HBox buttons = new HBox();
        buttons.setSpacing(5);
        
        Button onePlayer = new Button("1");
        Button twoPlayer = new Button("2");
        Button threePlayer = new Button("3");
        Button fourPlayer = new Button("4");
        buttons.getChildren().addAll(onePlayer, twoPlayer, threePlayer, fourPlayer);

        
        VBox textAndButtons = new VBox();
        textAndButtons.setSpacing(5);
        
        textAndButtons.getChildren().add(new Label("Kuinka monta pelaajaa?"));
        textAndButtons.getChildren().add(buttons);
        
        
        welcomeLayout.setCenter(textAndButtons);
        */
        
        //this.nOf_players = askNumberOfPlayers();
        
        
        
        /*
        BorderPane gameLayout = new BorderPane();
        gameLayout.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        */
        
        //gameLayout.setLeft(createScoreboard(this.nOf_players));
        
        //Scene gameScene = new Scene(setGameLayout());
        
        
        
        /*
        onePlayer.setOnAction((event) -> {
            this.nOf_players = 1;
            window.setScene(gameScene);
        });
        
        twoPlayer.setOnAction((event) -> {
            this.nOf_players = 2;
            window.setScene(gameScene);
        });
        
        threePlayer.setOnAction((event) -> {
            this.nOf_players = 3;
            window.setScene(gameScene);
        });
        
        fourPlayer.setOnAction((event) -> {
            this.nOf_players = 4;
            window.setScene(gameScene);
        });
        */
 
        //window.setScene(welcomeScene);
        //window.show();
        
       
        //gameLayout.setLeft(createScoreboard(this.nOf_players));
        
        
        //Testcode
        //this.player.addScore(Category.TWOPAIR, 22);
        //int print = this.player.getScore(Category.TWOPAIR);
        //System.out.println(print);
       
        
       
    }
    
    
    //Todo:
    //Get number of players from user and initialize based on that.
    //Draw UI
    //Add turn conter
    //Add turn queue
    
    private void setWelcomeScene() {
        BorderPane welcomeLayout = new BorderPane();
        welcomeLayout.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        
        HBox buttons = new HBox();
        buttons.setSpacing(5);
        
        Button onePlayer = new Button("1");
        Button twoPlayer = new Button("2");
        Button threePlayer = new Button("3");
        Button fourPlayer = new Button("4");
        buttons.getChildren().addAll(onePlayer, twoPlayer, threePlayer, fourPlayer);
        
        onePlayer.setOnAction((event) -> {
            this.nOf_players = 1;
            setGameScene();
        });
        
        twoPlayer.setOnAction((event) -> {
            this.nOf_players = 2;
            setGameScene();
        });
        

        threePlayer.setOnAction((event) -> {
            this.nOf_players = 3;
            setGameScene();
        });
        
        fourPlayer.setOnAction((event) -> {
            this.nOf_players = 4;
            setGameScene();
        });
        
        VBox textAndButtons = new VBox();
        textAndButtons.setSpacing(5);
        
        textAndButtons.getChildren().add(new Label("Kuinka monta pelaajaa?"));
        textAndButtons.getChildren().add(buttons);
        
        welcomeLayout.setCenter(textAndButtons);
        
        Scene welcomeScene = new Scene(welcomeLayout);
       
        this.window.setScene(welcomeScene);
        this.window.show();
    }
    
    private void setGameScene() {
        this.game = new Game(this.nOf_players);
        BorderPane gameLayout = new BorderPane();
        gameLayout.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        
        gameLayout.setLeft(createScoreboard(this.nOf_players));
        Scene gameScene = new Scene(gameLayout);
        
        this.window.setScene(gameScene);
        this.window.show();
        
    }
    
    private int askNumberOfPlayers() {
        System.out.println("Anna pelaajien määrä");
        return Integer.parseInt(input.nextLine());
    }
    
    private GridPane createScoreboard(int numberOfPlayers) {
        GridPane scoreboard = new GridPane();
        scoreboard.setGridLinesVisible(true);
        
        for (int x = 1; x < 1 + numberOfPlayers; x++) {
            scoreboard.add(new Label("P" + x), 1 + x, 0);
        }
        
        
        int i = 1;
        for (Category value: Category.values()) {
            if(i == 7) {
                i = 9;
            }
            scoreboard.add(new Button("lukitse pisteet"), 0, i);
            scoreboard.add(new Label(value.label), 1, i);
            i++;
            
        }
        scoreboard.add(new Label("Yht."), 1, 7);
        scoreboard.add(new Label("Bonus"), 1, 8);
        scoreboard.add(new Label("Yht."), 1, 18);
        
       
        /*
        for (int x = 1; x < 1 + numberOfPlayers; x++) {
            for (int y = 1; y < 19; y++) {
                scoreboard.add(new Label("42"), x , y);
            }
        }
        */
        
        
        return scoreboard;
    }
    
    
    public static void main(String[] args) {
        launch(YatzyUi.class);
    }
    
}
