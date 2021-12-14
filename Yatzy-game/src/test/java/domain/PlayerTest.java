package domain;


import yatzy.domain.Player;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



public class PlayerTest {
    
    Player player;
    Player player2;
    
    public PlayerTest() {
        player = new Player(1);
        player2 = new Player(2);
    }
 

    
    @Test
    public void createdPlayerExists() {
        assertTrue(player!=null);
    }
    @Test
    public void createdPlayer2Exists() {
        assertTrue(player2!=null);
    }

}
