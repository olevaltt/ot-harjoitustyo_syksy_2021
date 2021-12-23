package domain;

import yatzy.domain.Category;
import yatzy.domain.Game;
import yatzy.domain.Player;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.HashMap;


public class GameTest {
    
    Game testGame;
    
    public GameTest() {
    }
    
    @Before
    public void setUp() {
        testGame = new Game(4);
    }

    @Test
    public void getCurrentPlayerWorks() {
        assertEquals(testGame.getCurrentPlayer().getPlayerId(), 1);
    }
    
    @Test
    public void changeTurnWorks() {
        testGame.changeTurn();
        testGame.changeTurn();
        testGame.changeTurn();
        testGame.changeTurn();
        testGame.changeTurn();
        assertEquals(testGame.getCurrentPlayer().getPlayerId(), 2);
    }
    
    @Test
    public void thrownDiceWorks() {
        int[] result = {1, 1, 2, 5, 3};
        int[] thrownDice = {2, 1, 1, 0, 1, 0};
        assertArrayEquals(testGame.checkThrownDice(result), thrownDice);
    }
    
    @Test
    public void getBonusAmountWorks() {
        assertEquals(testGame.getBonusAmount(), 50);
    }
    
    @Test
    public void getWinnerFoundWorks() {
        assertFalse(testGame.getWinnerFound());
    }
    
    @Test
    public void getThrowCountWorks1() {
        assertEquals(testGame.getThrowCount(), 0);
    }
    
    @Test
    public void getThrowCountWorks2() {
        testGame.increaseThrowCounter();
        assertEquals(testGame.getThrowCount(), 1);
    }
    
    @Test
    public void getCurrentTurnWorks() {
        assertEquals(testGame.getCurrentTurn(), 1);
    }
    
    @Test
    public void checkCategoryOnesWorks() {
        int[] thrownDice = {2, 1, 1, 0, 1, 0};
        assertTrue(testGame.checkCategoryOnes(thrownDice));
    }
    
    @Test
    public void checkCategoryTwosWorks() {
        int[] thrownDice = {1, 3, 0, 0, 1, 0};
        assertTrue(testGame.checkCategoryTwos(thrownDice));
    }
    
    @Test
    public void checkCategoryThreesWorks() {
        int[] thrownDice = {1, 1, 1, 0, 0, 2};
        assertTrue(testGame.checkCategoryThrees(thrownDice));
    }
    
    @Test
    public void checkCategoryFoursWorks() {
        int[] thrownDice = {0, 1, 1, 1, 0, 2};
        assertTrue(testGame.checkCategoryFours(thrownDice));
    }
    
    @Test
    public void checkCategoryFivesWorks() {
        int[] thrownDice = {0, 1, 1, 0, 3, 0};
        assertTrue(testGame.checkCategoryFives(thrownDice));
    }
    
    @Test
    public void checkCategorySixesWorks() {
        int[] thrownDice = {1, 1, 1, 0, 0, 2};
        assertTrue(testGame.checkCategorySixes(thrownDice));
    }
    
    @Test
    public void checkCategoryOnePairWorks() {
        int[] thrownDice = {1, 1, 1, 0, 0, 2};
        assertTrue(testGame.checkCategoryOnePair(thrownDice));
    }
    
    @Test
    public void checkCategoryTwoPairWorks() {
        int[] thrownDice = {0, 0, 1, 0, 2, 2};
        assertTrue(testGame.checkCategoryTwoPair(thrownDice));
    }
    
    @Test
    public void checkCategoryThreeOfAKindWorks() {
        int[] thrownDice = {1, 0, 1, 0, 0, 3};
        assertTrue(testGame.checkCategoryThreeOfAKind(thrownDice));
    }
    @Test
    public void checkCategoryFourOfAKindWorks() {
        int[] thrownDice = {0, 0, 1, 0, 0, 4};
        assertTrue(testGame.checkCategoryFourOfAKind(thrownDice));
    }
    
    @Test
    public void checkCategorySmallStraightWorks() {
        int[] thrownDice = {1, 1, 1, 1, 1, 0};
        assertTrue(testGame.checkCategorySmallStraight(thrownDice));
    }
    
    @Test
    public void checkCategoryLargeStraightWorks() {
        int[] thrownDice = {0, 1, 1, 1, 1, 1};
        assertTrue(testGame.checkCategoryLargeStraight(thrownDice));
    }
    
    @Test
    public void checkCategoryFullHouseWorks() {
        int[] thrownDice = {0, 2, 0, 3, 0, 0};
        assertTrue(testGame.checkCategoryFullHouse(thrownDice));
    }
    
    @Test
    public void checkCategoryChanceWorks() {
        int[] thrownDice = {0, 2, 0, 3, 0, 0};
        assertTrue(testGame.checkCategoryChance(thrownDice));
    }
    
    @Test
    public void checkCategoryYatzyWorks() {
        int[] thrownDice = {0, 0, 0, 0, 0, 5};
        assertTrue(testGame.checkCategoryYatzy(thrownDice));
    }
    
    @Test
    public void checkIfScoreIsPlaceableOnesReturnsTrue() {
        int[] result = {1, 1, 2, 3, 6};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.ONES, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableOnesReturnsFalse() {
        int[] result = {6, 6, 6, 5, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.ONES, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableTwosReturnsTrue() {
        int[] result = {1, 1, 2, 3, 6};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.TWOS, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableTwosReturnsFalse() {
        int[] result = {6, 6, 6, 5, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.TWOS, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableThreesReturnsTrue() {
        int[] result = {1, 1, 2, 3, 6};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.THREES, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableThreesReturnsFalse() {
        int[] result = {6, 6, 6, 5, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.THREES, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableFoursReturnsTrue() {
        int[] result = {1, 4, 2, 3, 6};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.FOURS, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableFoursReturnsFalse() {
        int[] result = {6, 6, 6, 5, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.FOURS, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableFivesReturnsTrue() {
        int[] result = {1, 5, 2, 3, 6};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.FIVES, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableFivesReturnsFalse() {
        int[] result = {6, 6, 6, 2, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.FIVES, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableSixesReturnsTrue() {
        int[] result = {1, 5, 2, 3, 6};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.SIXES, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableSixesReturnsFalse() {
        int[] result = {4, 4, 5, 2, 1};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.SIXES, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableOnePairReturnsTrue() {
        int[] result = {5, 5, 2, 6, 6};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.ONEPAIR, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableOnePairReturnsFalse() {
        int[] result = {1, 4, 5, 2, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.ONEPAIR, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableTwoPairReturnsTrue() {
        int[] result = {5, 5, 2, 6, 6};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.TWOPAIR, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableTwoPairReturnsFalse() {
        int[] result = {1, 4, 5, 2, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.TWOPAIR, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableThreeOfAKindReturnsTrue() {
        int[] result = {5, 4, 6, 6, 6};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.THREEOFAKIND, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableThreeOfAKindReturnsFalse() {
        int[] result = {1, 4, 5, 2, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.THREEOFAKIND, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableFourOfAKindReturnsTrue() {
        int[] result = {5, 6, 6, 6, 6};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.FOUROFAKIND, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableFourOfAKindReturnsFalse() {
        int[] result = {1, 4, 5, 2, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.FOUROFAKIND, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableSmallStraightReturnsTrue() {
        int[] result = {1, 2, 3, 4, 5};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.SMALLSTRAIGHT, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableSmallStraightReturnsFalse() {
        int[] result = {1, 4, 5, 2, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.SMALLSTRAIGHT, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableLargeStraightReturnsTrue() {
        int[] result = {6, 2, 3, 4, 5};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.LARGESTRAIGHT, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableLargeStraightReturnsFalse() {
        int[] result = {1, 4, 5, 2, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.LARGESTRAIGHT, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableFullHouseReturnsTrue() {
        int[] result = {5, 4, 4, 4, 5};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.FULLHOUSE, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableFullHouseReturnsFalse() {
        int[] result = {1, 4, 5, 2, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.FULLHOUSE, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableChanceReturnsTrue() {
        int[] result = {5, 4, 4, 4, 5};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.FULLHOUSE, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableYatzyReturnsTrue() {
        int[] result = {6, 6, 6, 6, 6};
        assertTrue(testGame.checkIfScoreIsPlaceable(Category.YATZY, result));
    }
    
    @Test
    public void checkIfScoreIsPlaceableYatzyReturnsFalse() {
        int[] result = {6, 6, 6, 5, 6};
        assertFalse(testGame.checkIfScoreIsPlaceable(Category.YATZY, result));
    }
    
    @Test
    public void addScoreOnesWorks() {
        int categoryNumber = 0;
        int[] result = {1, 1, 3, 5, 1};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.ONES);
        
        assertNotNull(score);
        assertEquals((int) score, 3);
    }
    
    @Test
    public void addScoreTwosWorks() {
        int categoryNumber = 1;
        int[] result = {1, 2, 3, 2, 1};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.TWOS);
        
        assertNotNull(score);
        assertEquals((int) score, 4);
    }
    
    @Test
    public void addScoreThreesWorks() {
        int categoryNumber = 2;
        int[] result = {3, 2, 3, 3, 3};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.THREES);
        
        assertNotNull(score);
        assertEquals((int) score, 12);
    }
    
    @Test
    public void addScoreFoursWorks() {
        int categoryNumber = 3;
        int[] result = {3, 4, 4, 3, 4};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.FOURS);
        
        assertNotNull(score);
        assertEquals((int) score, 12);
    }
    
    @Test
    public void addScoreFivesWorks() {
        int categoryNumber = 4;
        int[] result = {3, 4, 3, 3, 4};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.FIVES);
        
        assertNotNull(score);
        assertEquals((int) score, 0);
    }
    
    @Test
    public void addScoreSixesWorks() {
        int categoryNumber = 5;
        int[] result = {6, 4, 6, 3, 6};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.SIXES);
        
        assertNotNull(score);
        assertEquals((int) score, 18);
    }
    
    @Test
    public void addScoreOnePairWorks() {
        int categoryNumber = 6;
        int[] result = {6, 4, 6, 3, 6};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.ONEPAIR);
        
        assertNotNull(score);
        assertEquals((int) score, 12);
    }
    
    @Test
    public void addScoreTwoPairWorks() {
        int categoryNumber = 7;
        int[] result = {6, 4, 6, 3, 4};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.TWOPAIR);
        
        assertNotNull(score);
        assertEquals((int) score, 20);
    }
    
    @Test
    public void addScoreThreeOfAKindWorks() {
        int categoryNumber = 8;
        int[] result = {6, 4, 6, 6, 4};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.THREEOFAKIND);
        
        assertNotNull(score);
        assertEquals((int) score, 18);
    }
    
    @Test
    public void addScoreFourOfAKindWorks() {
        int categoryNumber = 9;
        int[] result = {6, 6, 6, 6, 4};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.FOUROFAKIND);
        
        assertNotNull(score);
        assertEquals((int) score, 24);
    }
    
    @Test
    public void addScoreSmallStraightWorks() {
        int categoryNumber = 10;
        int[] result = {1, 2, 3, 4, 5};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.SMALLSTRAIGHT);
        
        assertNotNull(score);
        assertEquals((int) score, 15);
    }
    
    @Test
    public void addScoreLargeStraightWorks() {
        int categoryNumber = 11;
        int[] result = {6, 2, 3, 4, 5};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.LARGESTRAIGHT);
        
        assertNotNull(score);
        assertEquals((int) score, 20);
    }
    
    @Test
    public void addScoreFullHouseWorks() {
        int categoryNumber = 12;
        int[] result = {6, 6, 5, 6, 5};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.FULLHOUSE);
        
        assertNotNull(score);
        assertEquals((int) score, 28);
    }
    
    @Test
    public void addScoreChanceWorks() {
        int categoryNumber = 13;
        int[] result = {1, 1, 1, 1, 1};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.CHANCE);
        
        assertNotNull(score);
        assertEquals((int) score, 5);
    }
    
    @Test
    public void addScoreYatzyWorks() {
        int categoryNumber = 14;
        int[] result = {3, 3, 3, 3, 3};
        testGame.addScore(categoryNumber, result);
        Player player = testGame.getPlayerById(1);
        HashMap<Category, Integer> map = player.getScore();
        Integer score = map.get(Category.YATZY);
        
        assertNotNull(score);
        assertEquals((int) score, 65);
    }
    
}
