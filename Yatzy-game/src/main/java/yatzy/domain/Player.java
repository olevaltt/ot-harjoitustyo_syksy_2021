package yatzy.domain;

import java.util.HashMap;

public class Player {
    
    public int playerNumber;
    public HashMap<Category, Integer> playerScore;
    
    public Player(int playerNumber) {
        this.playerScore = new HashMap<>();
        this.playerNumber = playerNumber;
    }
    
    
    public int getPlayerNumber() {
        return this.playerNumber;
    }
    
    
    public void addScore(Category category, int result) {
        this.playerScore.put(category, result);
    }
    
    public int getScore(Category category) {
        return this.playerScore.get(category); 
    }
    
}
