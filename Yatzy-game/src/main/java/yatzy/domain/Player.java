package yatzy.domain;

import java.util.HashMap;

public class Player {
    
    public int playerID;
    public HashMap<Category, Integer> playerScore;
    
    public Player(int playerID) {
        this.playerScore = new HashMap<>();
        this.playerID = playerID;
    }
    
    
    public int getPlayerNumber() {
        return this.playerID;
    }
    
    
    public void addScore(Category category, int result) {
        this.playerScore.put(category, result);
    }
    
    public HashMap<Category, Integer> getScore() {
        return this.playerScore; 
    }
    
}
