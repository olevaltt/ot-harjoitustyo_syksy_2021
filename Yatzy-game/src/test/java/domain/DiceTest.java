
package domain;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import yatzy.domain.Dice;



public class DiceTest {
    
    Dice testDice;
    
    public DiceTest() {
    }
    
@Before
public void setUp() {
    testDice = new Dice();
    
}
    
@Test
public void throwDiceDoesNotReturnNull() {
    int[] list = new int[]{0,2,4};
    assertNotEquals(testDice.throwDice(list), null);
}

@Test
public void getResultDoesNotReturnNull() {
    assertNotEquals(testDice.getResult(), null);
}

}
