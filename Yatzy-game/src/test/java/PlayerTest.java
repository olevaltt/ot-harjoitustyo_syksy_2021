
import yatzy.domain.Player;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {
    
    Player player;
    Player player2;
    
    public PlayerTest() {
        player = new Player(1);
        player2 = new Player(2);
    }
    

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {  
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
    /*
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    */
    

    // @Test
    // public void hello() {}
    
    @Test
    public void createdPlayerExists() {
        assertTrue(player!=null);
    }
    @Test
    public void createdPlayer2Exists() {
        assertTrue(player2!=null);
    }
}
