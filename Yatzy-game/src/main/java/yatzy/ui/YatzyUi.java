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
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ToggleButton;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.property.*;
import javafx.beans.binding.*;
import java.util.Collections;
import javafx.collections.*;


public class YatzyUi extends Application {

    final private Dice dice;
    int nOf_players;
    private Game game;
    private Stage window;
    private static final int SCREEN_WIDTH = 700;
    private static final int SCREEN_HEIGHT = 700;
    final private SimpleIntegerProperty throwCount;
    final private SimpleIntegerProperty currentTurn;
    final private SimpleObjectProperty<int[]> result;
    final private SimpleIntegerProperty currentPlayer;
    
    //0 -> button cannot be pressed
    //1 -> button can be pressed but the throw won't fit
    //2 -> button can be pressed and the throw fits
    private SimpleListProperty<Integer> buttonState;
    
    public YatzyUi() {
        this.dice = new Dice();
        this.nOf_players = -1;
        this.throwCount = new SimpleIntegerProperty(0);
        this.currentTurn = new SimpleIntegerProperty(0);
        this.currentPlayer = new SimpleIntegerProperty(1);
        this.result = new SimpleObjectProperty(new int[]{1,1,1,1,1});
        this.buttonState = new SimpleListProperty<>();
        ObservableList<Integer> observableList = FXCollections.observableArrayList(new ArrayList<Integer>(Collections.nCopies(15, 0)));
        this.buttonState = new SimpleListProperty<>(observableList);
        
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
        int j = 0;
        for (Category value: Category.values()) {
            if(i == 7) {
                i = 9;
            }
            
            Button button = new Button("lukitse pisteet");
            final int index = j;
            BooleanBinding isLocked = Bindings.createBooleanBinding(() -> buttonState.get(index) == 0, buttonState);
            button.textProperty().bind(
                new When(isLocked).then("lukittu").otherwise("lukitse pisteet")
            );
            button.disableProperty().bind(isLocked);
            scoreboard.add(button, 0, i);
            scoreboard.add(new Label(value.label), 1, i);
            i++;
            j++;
            
        }
        scoreboard.add(new Label("Yht."), 1, 7);
        scoreboard.add(new Label("Bonus"), 1, 8);
        scoreboard.add(new Label("Yht."), 1, 18);
        
        return scoreboard;
    }
    
       
    
    private VBox drawDice() {
        VBox main = new VBox();
        Label turnInfo = new Label();
        StringBinding infoText = Bindings.createStringBinding(
                () -> "Kierros " + String.valueOf(currentTurn.get()) + ", Pelaajan " + String.valueOf(currentPlayer.get()) + " vuoro, Heitetty " + String.valueOf(throwCount.get()) + " kertaa",
                currentPlayer, currentTurn, throwCount
        );
        
        turnInfo.textProperty().bind(infoText);
        main.getChildren().add(turnInfo);

        
        GridPane grid = new GridPane();
                
        for (int i = 0; i < 5; i++) {
            Label label = new Label();
            final int index = i;
            StringBinding text = Bindings.createStringBinding(() -> String.valueOf(result.get()[index]), result);
            label.textProperty().bind(text);
            grid.add(label, i, 0);
        }

        ToggleButton[] buttons = new ToggleButton[5];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new ToggleButton("Valitse");
        }

        for (int i = 0; i < buttons.length; i++) {
            grid.add(buttons[i], i, 1);
        }
        
        main.getChildren().add(grid);
        
        Button roll = new Button("Heitä");
        
        //Allows the player throw the dice only when 1 or more dice has been selected
        roll.disableProperty().bind(
                buttons[0].selectedProperty()
                .or(buttons[1].selectedProperty())
                .or(buttons[2].selectedProperty())
                .or(buttons[3].selectedProperty())
                .or(buttons[4].selectedProperty())
                .not()
                .or(throwCount.greaterThanOrEqualTo(3))
        );
        
        roll.setOnAction((event) -> {  
            int[] newResult = throwDice(buttons);
            result.set(Arrays.copyOf(newResult, 5));
            for (ToggleButton button : buttons) {
                button.setSelected(false);
            }
        });
        
        main.getChildren().add(roll);
        
        return main;    
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
        this.game.increaseThrowCounter();
        this.throwCount.set(this.game.getThrowCount());
        return this.dice.throwDice(diceIndicesArray);
   
    }
    
    
    public static void main(String[] args) {
        launch(YatzyUi.class);
    }
    
}
