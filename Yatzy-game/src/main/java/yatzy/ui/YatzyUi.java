package yatzy.ui;

import yatzy.domain.Game;
import yatzy.domain.Category;
import yatzy.domain.Dice;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.property.*;
import javafx.beans.binding.*;
import java.util.Collections;
import javafx.collections.*;
import java.util.List;
import javafx.beans.value.ChangeListener;
import java.util.stream.IntStream;


public class YatzyUi extends Application {

    final private Dice dice;
    int numberOfPlayers;
    private Game game;
    private Stage window;
    final private static int SCREEN_WIDTH = 700;
    final private static int SCREEN_HEIGHT = 700;
    final private SimpleIntegerProperty throwCount;
    final private SimpleIntegerProperty currentTurn;
    final private SimpleObjectProperty<int[]> result;
    final private SimpleIntegerProperty currentPlayer;
    final private SimpleListProperty<List<Integer>> playerScores;
    final private SimpleObjectProperty<int[]> upperTotal;
    final private SimpleObjectProperty<Boolean[]> bonus;
    final private SimpleObjectProperty<int[]> grandTotal;
    private boolean winnerFound;
    
    //0 -> button cannot be pressed
    //1 -> button can be pressed but the throw won't fit
    //2 -> button can be pressed and the throw fits
    final private SimpleListProperty<Integer> buttonState;
    
    public YatzyUi() {
        this.dice = new Dice();
        this.numberOfPlayers = -1;
        this.throwCount = new SimpleIntegerProperty(0);
        this.currentTurn = new SimpleIntegerProperty(1);
        this.currentPlayer = new SimpleIntegerProperty(1);
        this.result = new SimpleObjectProperty(new int[]{1,1,1,1,1});
        ObservableList<Integer> observableList = FXCollections.observableArrayList(new ArrayList<>(Collections.nCopies(15, 0)));
        this.buttonState = new SimpleListProperty<>(observableList);
        this.playerScores = new SimpleListProperty<>();
        this.upperTotal = new SimpleObjectProperty<>();
        this.bonus = new SimpleObjectProperty<>();
        this.grandTotal = new SimpleObjectProperty<>();
        this.winnerFound = false;
    }
    
    @Override
    public void start(Stage window) throws Exception {
        this.window = window;
        setWelcomeScene();
    }
    
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
            this.numberOfPlayers = 1;
            setGameScene();
        });
        
        twoPlayer.setOnAction((event) -> {
            this.numberOfPlayers = 2;
            setGameScene();
        });
        
        threePlayer.setOnAction((event) -> {
            this.numberOfPlayers = 3;
            setGameScene();
        });
        
        fourPlayer.setOnAction((event) -> {
            this.numberOfPlayers = 4;
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
        this.game = new Game(this.numberOfPlayers);
        
        this.game.updatePlayerScores(playerScores);
        
        int[] newUpperTotal = new int[this.numberOfPlayers];
        Arrays.fill(newUpperTotal, 0);
        this.upperTotal.set(newUpperTotal);
        this.upperTotal.bind(Bindings.createObjectBinding(() -> {
            return playerScores.get().stream().mapToInt((scoreList) -> {
                int sum = 0;
                for (int i = 0; i < 6; i++) {
                    if (scoreList.get(i) != null) {
                        sum = sum + scoreList.get(i);
                    }
                }
                return sum;
            }).toArray();
        }, playerScores));
        
        Boolean[] newBonus = new Boolean[this.numberOfPlayers];
        Arrays.fill(newBonus, false);
        this.bonus.set(newBonus);
        this.bonus.bind(Bindings.createObjectBinding(() -> {
            Boolean[] ar = new Boolean[this.numberOfPlayers];
            for (int i = 0; i < this.numberOfPlayers; i++) {
                ar[i] = upperTotal.get()[i] >= 63;
            }
            return ar;
        }, upperTotal));
        
        int[] newGrandTotal = new int[this.numberOfPlayers];
        Arrays.fill(newGrandTotal, 0);
        this.grandTotal.set(newGrandTotal);
        this.grandTotal.bind(Bindings.createObjectBinding(() -> {
            return IntStream.range(0, this.numberOfPlayers).map((index) -> {
                List<Integer> scoreList = playerScores.get(index);
                int sum = 0;
                sum = sum + upperTotal.get()[index];
                if (bonus.get()[index]) {
                    sum = sum + this.game.getBonusAmount();
                }
                for (int i = 6; i < 15; i++) {
                    if (scoreList.get(i) != null) {
                        sum = sum + scoreList.get(i);
                    }
                }
                return sum;
            }).toArray();
        }, playerScores, upperTotal, bonus));
        
        BorderPane gameLayout = new BorderPane();
        gameLayout.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        gameLayout.setLeft(drawScoreboard(this.numberOfPlayers));
        Scene gameScene = new Scene(gameLayout);
        gameLayout.setRight(drawDice());
        
        this.window.setScene(gameScene);
        this.window.show();
    }
    
    
    private GridPane drawScoreboard(int numberOfPlayers) {
        GridPane scoreboard = new GridPane();
        scoreboard.setGridLinesVisible(true);
        
        for (int playerId = 0; playerId < numberOfPlayers; playerId++) {
            int column = playerId + 2;
            final int playerIdFinal = playerId;
            scoreboard.add(new Label("P" + (playerId + 1)), column, 0);
            Label upperTotalLabel = new Label();
            upperTotalLabel.textProperty().bind(Bindings.createStringBinding(() -> {
                return String.valueOf(upperTotal.get()[playerIdFinal]);
            }, upperTotal));
            scoreboard.add(upperTotalLabel, column, 7);
            
            Label bonusLabel = new Label();
            bonusLabel.textProperty().bind(Bindings.createStringBinding(() -> {
                return (bonus.get()[playerIdFinal]) ? String.valueOf(this.game.getBonusAmount()) : "";
            }, bonus));
            scoreboard.add(bonusLabel, column, 8);
            
            Label grandTotalLabel = new Label();
            grandTotalLabel.textProperty().bind(Bindings.createStringBinding(() -> {
                return String.valueOf(grandTotal.get()[playerIdFinal]);
            }, grandTotal));
            scoreboard.add(grandTotalLabel, column, 18);
        }
        
        int rowIndex = 1;
        int index = 0;
        for (Category value: Category.values()) {
            if(rowIndex == 7) {
                rowIndex = 9;
            }
            
            Button button = new Button();
            final int indexFinal = index;
            BooleanBinding isLocked = Bindings.createBooleanBinding(() -> buttonState.get(indexFinal) == 0, buttonState);
            button.textProperty().bind(
                Bindings.createStringBinding(() -> {
                    switch(buttonState.get(indexFinal)) {
                        case 0:
                            return "lukittu";
                        case 1:
                            return "viivaa yli";
                        case 2:
                            return "aseta tulos";
                        default: 
                            return "virhe"; 
                    }
                }, buttonState)
            );
            button.disableProperty().bind(isLocked);
            scoreboard.add(button, 0, rowIndex);
            scoreboard.add(new Label(value.label), 1, rowIndex);
            button.setOnAction((event) -> {
                this.game.addScore(indexFinal, result.getValue());
                this.throwCount.set(this.game.getThrowCount());
                this.currentTurn.set(this.game.getCurrentTurn());
                this.currentPlayer.set(this.game.getCurrentPlayer().getPlayerId());
                ObservableList<Integer> observableList = FXCollections.observableArrayList(new ArrayList<Integer>(Collections.nCopies(15, 0)));
                this.buttonState.set(observableList);
                this.game.updatePlayerScores(playerScores);
                this.winnerFound = this.game.getWinnerFound();
                if (this.winnerFound == true) {
                    setWinnerScene();
                }
            });
            
            for (int playerId = 0; playerId < this.numberOfPlayers; playerId++) {
                Label label = new Label();
                final int playerIdFinal = playerId;
                label.textProperty().bind(Bindings.createStringBinding(() -> {
                    Integer score = playerScores.get(playerIdFinal).get(indexFinal);
                    return (score == null) ? "" : score.toString();
                }, playerScores));
                
                scoreboard.add(label, playerId + 2, rowIndex);
            }
            
            rowIndex++;
            index++;
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
                () -> String.format("Kierros %d, Pelaajan %d vuoro, Heitetty %d kertaa.", currentTurn.get(), currentPlayer.get(), throwCount.get()),
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
            ToggleButton button = new ToggleButton("Valitse");
            button.disableProperty().bind(throwCount.isEqualTo(0));
            ChangeListener throwCountListener = (observable, oldValue, newValue) -> {
                if ((int) newValue == 0) {
                    button.selectedProperty().set(true);
                }
            };
            button.selectedProperty().set(true);
            throwCount.addListener(throwCountListener);
            buttons[i] = button;
            grid.add(button, i, 1);
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
            this.game.updateButtonState(this.buttonState, newResult);
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
    
    public void setWinnerScene() {
        BorderPane winnerLayout = new BorderPane();
        winnerLayout.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        
        GridPane finalScores = new GridPane();
        int winner = 0;
        int winnerScore = 0;
        for (int i = 0; i < this.numberOfPlayers; i++) {
            Label player = new Label(this.game.getPlayerById(i + 1).toString());
            finalScores.add(player, 0, i);
            if (grandTotal.get()[i] > winnerScore) {
                winner = i + 1;
                winnerScore = grandTotal.get()[i];
            }
            Label finalScore = new Label(" Pisteet: " + Integer.toString(grandTotal.get()[i]));
            finalScores.add(finalScore, 1, i);
        }
        Label winnerLabel = new Label("Voittaja on: " + this.game.getPlayerById(winner));
        finalScores.add(winnerLabel, 0, this.numberOfPlayers);
        
        winnerLayout.setCenter(finalScores);
        Scene winnerScene = new Scene(winnerLayout);
       
        this.window.setScene(winnerScene);
        this.window.show();
    }
    
    public static void main(String[] args) {
        launch(YatzyUi.class);
    }
}
