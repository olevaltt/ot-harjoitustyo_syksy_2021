package yatzy.ui;

import yatzy.domain.Player;
import yatzy.domain.Category;
import yatzy.domain.Dice;
import javafx.application.Application;
import javafx.stage.Stage;



public class YatzyUi extends Application {

    private Dice dice;
    private Player player;
    
    public YatzyUi() {
        this.player = new Player(1);
        this.dice = new Dice();
    }
    
    @Override
    public void start(Stage window) throws Exception {
        //Testcode
        this.player.addScore(Category.TWOPAIR, 22);
        int print = this.player.getScore(Category.TWOPAIR);
        System.out.println(print);
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
