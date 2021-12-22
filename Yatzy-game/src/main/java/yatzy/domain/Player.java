package yatzy.domain;

import java.util.HashMap;

public class Player {
    
    final private int playerID;
    private HashMap<Category, Integer> playerScore;
    
    public Player(int playerID) {
        this.playerScore = new HashMap<>();
        this.playerID = playerID;
    }
    
    
    public int getPlayerId() {
        return this.playerID;
    }
    
    
    public void addScore(Category category, int result) {
        this.playerScore.put(category, result);
    }
    
    public HashMap<Category, Integer> getScore() {
        return this.playerScore; 
    }
    
}
