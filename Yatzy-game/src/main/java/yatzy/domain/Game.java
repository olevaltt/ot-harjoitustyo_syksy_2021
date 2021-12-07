package yatzy.domain;

import java.util.LinkedList;
import java.util.Queue;

public class Game {
    
    Queue<Player> players = new LinkedList<>();
    
    public Game(int playerCount) {
        for (int i = 0; i < playerCount - 1; i++) {
            Player player = new Player(i + 1);
            players.add(player);
        }
    }
    
    
    public int getPlayerScore(Player player, Category category) {
        return player.getScore(category);
    }
    
    
    private Player getPlayerByID(int id) {
        return players.stream().filter(player -> player.playerID == id).findAny().orElse(null);
    }
    
    public Player getCurrentPlayer() {
        return players.peek();
    }
    
    public void changeTurn() {
        Player player = players.poll();
        players.add(player);
    }
}
