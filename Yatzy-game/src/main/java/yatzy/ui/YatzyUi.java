package yatzy.ui;

import yatzy.domain.Game;
import yatzy.domain.Player;
import yatzy.domain.Category;
import yatzy.domain.Dice;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import java.util.Scanner;



public class YatzyUi extends Application {

    private Dice dice;
    //private Player player;
    int nOf_players;
    Scanner input;
    private Game game;
    
    public YatzyUi() {
        this.dice = new Dice();
        this.nOf_players = -1;
        this.input = new Scanner(System.in);
        this.game = new Game();
        
    }
    
    @Override
    public void start(Stage window) throws Exception {
        
        BorderPane layout = new BorderPane();
        
        System.out.println("Anna pelaajien määrä");
        this.nOf_players = Integer.parseInt(input.nextLine());
        
        this.game.createPlayers(nOf_players);
        
        
        
        
        
        
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
    
    
    
    
    public static void main(String[] args) {
        launch(YatzyUi.class);
    }
    
}
