package yatzy.ui;

import yatzy.domain.Game;
import yatzy.domain.Category;
import yatzy.domain.Dice;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.util.Scanner;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ToggleButton;
import java.util.ArrayList;




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
    }
    
    
    
    //So far its possible to initialize scoreboard and reroll dice.
    
    //Todo:
    //Get number of players from user and initialize based on that. DONE
    //Draw UI DONE
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
        
        gameLayout.setLeft(drawScoreboard(this.nOf_players));
        Scene gameScene = new Scene(gameLayout);
        
        gameLayout.setRight(drawDice());
        
        this.window.setScene(gameScene);
        this.window.show();
        
    }
    
    
    //Add Panes inside GridPanes instead of Labels.
    
    private GridPane drawScoreboard(int numberOfPlayers) {
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
        
        return scoreboard;
    }
    
       
    
    private GridPane drawDice() {
        GridPane grid = new GridPane();
        
        
        ToggleButton[] buttons = new ToggleButton[5];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new ToggleButton("Select");
        }
      
        
        
        for (int i = 0; i < buttons.length; i++) {
            grid.add(buttons[i], i, 1);
        }
        

        
        HBox dice = new HBox();
        
        Label die1 = new Label(String.valueOf(this.dice.getResult()[0]));
        Label die2 = new Label(String.valueOf(this.dice.getResult()[1]));
        Label die3 = new Label(String.valueOf(this.dice.getResult()[2]));
        Label die4 = new Label(String.valueOf(this.dice.getResult()[3]));
        Label die5 = new Label(String.valueOf(this.dice.getResult()[4]));

        dice.getChildren().addAll(die1, die2, die3, die4, die5);
        
        grid.add(dice, 2, 0);
        
        Button reroll = new Button("reroll");
        grid.add(reroll,2 , 2);
        reroll.setOnAction((event) -> {            
            refreshDice(dice, die1, die2, die3, die4, die5, buttons);
            for (ToggleButton button : buttons) {
                button.setSelected(false);
            }
        });

        return grid;    
    }
    
    private void refreshDice(HBox dice, Label die1, Label die2, Label die3, Label die4, Label die5, ToggleButton[] buttons){
        

        int[] result = throwDice(buttons);
        
        dice.getChildren().clear();
        
        die1 = new Label(String.valueOf(result[0]));
        die2 = new Label(String.valueOf(result[1]));
        die3 = new Label(String.valueOf(result[2]));
        die4 = new Label(String.valueOf(result[3]));
        die5 = new Label(String.valueOf(result[4]));

        dice.getChildren().addAll(die1, die2, die3, die4, die5);


 
    }
    
    private int[] throwDice(ToggleButton[] buttons) {
        ArrayList<Integer> diceIndices = new ArrayList<>();
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isSelected()) {
                diceIndices.add(i);
            }
        }

        int[] diceIndicesArray = new int[diceIndices.size()];
        for (int i = 0; i < diceIndicesArray.length; i++) {
            diceIndicesArray[i] = diceIndices.get(i);
        }

        return this.dice.throwDice(diceIndicesArray);
   
    }
    
    
    public static void main(String[] args) {
        launch(YatzyUi.class);
    }
    
}
