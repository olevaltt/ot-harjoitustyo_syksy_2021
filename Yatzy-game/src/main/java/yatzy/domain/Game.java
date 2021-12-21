package yatzy.domain;

import java.util.LinkedList;
import java.util.Queue;
import javafx.beans.property.ListProperty;
import java.util.HashMap;
import java.util.ArrayList;


public class Game {
    
    Queue<Player> players = new LinkedList<>();
    int totalTurns;
    int turnCounter = 1;
    boolean gameOver = false;
    int throwCount = 0;
    
    
    public Game(int playerCount) {
        this.totalTurns = playerCount * 15;
        for (int i = 0; i < playerCount - 1; i++) {
            Player player = new Player(i + 1);
            players.add(player);
        }
    }
    
    /*
    public int getPlayerScore(Player player, Category category) {
        return player.getScore(category);
    }
    */
    
    public void addPlayerScore(Player player, Category category, int result) {
        player.addScore(category, result);
    }
    
    
    private Player getPlayerById(int id) {
        return players.stream().filter(player -> player.playerID == id).findAny().orElse(null);
    }
    
    public Player getCurrentPlayer() {
        return players.peek();
    }
    
    public void changeTurn() {
        Player player = players.poll();
        players.add(player);

        this.turnCounter++;
        this.throwCount = 0;
        if (turnCounter == totalTurns) {
            gameOver = true;
            System.out.println("Game Over");
            //Game over --> calculate points and announce winner
        }
    }
    
    public void increaseThrowCounter() {
        this.throwCount++;
    }
    
    public int getThrowCount() {
        return this.throwCount;
    }
    
    public boolean throwAllowed() {
        if (this.throwCount >= 3) {
            return false;
        } else {
            return true;
        }
    }
    
    public void updateButtonState(ListProperty buttonState, int[] result) {
        
        Category[] categories = Category.values();
        HashMap<Category, Integer> scoreMap = getCurrentPlayer().getScore();

        for (int i = 0; i < 15; i++) {
            
            Integer score = scoreMap.get(categories[i]);

            
            if (score != null) {
                buttonState.set(i, 0);
            } else if (checkIfScoreIsPlaceable(categories[i], result)) {
                buttonState.set(i, 2);
            } else {
                buttonState.set(i, 1);
            }
        }
        /*
        System.out.println(buttonState.get(0));
        System.out.println(buttonState.get(1));
        System.out.println(buttonState.get(2));
*/
    }
    
    public boolean checkIfScoreIsPlaceable(Category category, int[] result) {
        int[] thrownDice = this.checkThrownDice(result);

        switch(category) {
            case ONES:
                return checkCategoryOnes(thrownDice);
                
            case TWOS:
                return checkCategoryTwos(thrownDice);
                
            case THREES:
                return checkCategoryThrees(thrownDice);
                
            case FOURS:
                return checkCategoryFours(thrownDice);
                    
            case FIVES:
                return checkCategoryFives(thrownDice);
                
            case SIXES:
                return checkCategorySixes(thrownDice);
                
            case ONEPAIR:
                return checkCategoryOnePair(thrownDice);
                
            case TWOPAIR:
                return checkCategoryTwoPair(thrownDice);
                
            case THREEOFAKIND:
                return checkCategoryThreeOfAKind(thrownDice);
                
            case FOUROFAKIND:
                return checkCategoryFourOfAKind(thrownDice);
                
            case SMALLSTRAIGHT:
                return checkCategorySmallStraight(thrownDice);
                
            case LARGESTRAIGHT:
                return checkCategoryLargeStraight(thrownDice);
                
            case FULLHOUSE:
                return checkCategoryFullHouse(thrownDice);
                
            case CHANCE:
                return checkCategoryChance(thrownDice);
                
            case YATZY:
                return checkCategoryYatzy(thrownDice);
                
            default: return false;    
                       
        }
    }
    
    
    public boolean checkCategoryOnes(int[] thrownDice) {
        if (thrownDice[0] > 0) {
            return true;
        } else {
            return false;
        }
         
    }
    
    public boolean checkCategoryTwos(int[] thrownDice) {
        if (thrownDice[1] > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkCategoryThrees(int[] thrownDice) {
        if (thrownDice[2] > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkCategoryFours(int[] thrownDice) {
        if (thrownDice[3] > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkCategoryFives(int[] thrownDice) {
        if (thrownDice[4] > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkCategorySixes(int[] thrownDice) {
        if (thrownDice[5] > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkCategoryOnePair(int[] thrownDice) {
        ArrayList<Integer> eligibleValues = new ArrayList<>();
        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] >= 2) {
                eligibleValues.add(i);
            }
        }
        
        if (eligibleValues.size() > 0) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean checkCategoryTwoPair(int[] thrownDice) {
        ArrayList<Integer> howManyPairs = new ArrayList<>();

        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] >= 2) {
                howManyPairs.add(i);
            }
        }
        
        if (howManyPairs.size() == 2) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean checkCategoryThreeOfAKind(int[] thrownDice) {
        
        ArrayList<Integer> eligibleValues = new ArrayList<>();
        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] > 2) {
                eligibleValues.add(i);
            }
        }
        
        if (eligibleValues.size() > 0) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean checkCategoryFourOfAKind(int[] thrownDice) {
        ArrayList<Integer> eligibleValues = new ArrayList<>();
        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] > 3) {
                return true;
            }
        }
        return false;

    }
    
    public boolean checkCategorySmallStraight(int[] thrownDice) {
        ArrayList<Integer> eligibleValues = new ArrayList<>();
        for (int i = 0; i < thrownDice.length - 1; i++) {
            if (thrownDice[i] == 1) {
                eligibleValues.add(i);
            }
        }
        
        if (eligibleValues.size() == 5) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkCategoryLargeStraight(int[] thrownDice) {
        ArrayList<Integer> eligibleValues = new ArrayList<>();
        for (int i = 1; i < thrownDice.length; i++) {
            if (thrownDice[i] == 1) {
                eligibleValues.add(i);
            }
        }
        
        if (eligibleValues.size() == 5) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkCategoryFullHouse(int[] thrownDice) {
        ArrayList<Integer> howManyPairs = new ArrayList<>();
        ArrayList<Integer> howManyThreeOfAKinds = new ArrayList<>();

        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] == 2) {
                howManyPairs.add(i);
            }
        }
        
        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] == 3) {
                howManyThreeOfAKinds.add(i);
            }
        }
        
        if (howManyPairs.size() == 1 && howManyThreeOfAKinds.size() == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkCategoryChance(int[] thrownDice) {
        return true;
    }
    
    public boolean checkCategoryYatzy(int[] thrownDice) {
        ArrayList<Integer> eligibleValues = new ArrayList<>();
        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] == 5) {
                eligibleValues.add(i);
            }
        }
        
        if (eligibleValues.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public int[] checkThrownDice(int[] result) {
        int[] thrownDice = new int[6];
        for (int i = 0; i < thrownDice.length; i++) {
            thrownDice[i] = 0;
        } 
        int Ones = 0;
        int Twos = 0;
        int Threes = 0;
        int Fours = 0;
        int Fives = 0;
        int Sixes = 0;
        
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 1) {
                Ones++;
            } else if (result[i] == 2) {
                Twos++;
            } else if (result[i] == 3) {
                Threes++;
            } else if (result[i] == 4) {
                Fours++;
            } else if (result[i] == 5) {
                Fives++;
            } else {
                Sixes++;
            }
        }

        thrownDice[0] = Ones;
        thrownDice[1] = Twos;
        thrownDice[2] = Threes;
        thrownDice[3] = Fours;
        thrownDice[4] = Fives;
        thrownDice[5] = Sixes;
        return thrownDice;
    }
    
    public void addScore(int categoryNumber, ListProperty buttonstate, int[] result) {
        int[] thrownDice = checkThrownDice(result);
        Category[] categories = Category.values();
        
        switch(categories[categoryNumber]) {
            case ONES:
               addCategoryOnesScore(thrownDice);
                
            case TWOS:
                addCategoryTwosScore(thrownDice);
                
            case THREES:
                addCategoryThreesScore(thrownDice);
                
            case FOURS:
                addCategoryFoursScore(thrownDice);
                    
            case FIVES:
                addCategoryFivesScore(thrownDice);
                
            case SIXES:
                addCategorySixesScore(thrownDice);
                
            case ONEPAIR:
                addCategoryOnePairScore(thrownDice);
                
            case TWOPAIR:
                addCategoryTwoPairScore(thrownDice);
                
            case THREEOFAKIND:
                addCategoryThreeOfAKindScore(thrownDice);
                
            case FOUROFAKIND:
                addCategoryFourOfAKindScore(thrownDice);
                
            case SMALLSTRAIGHT:
                addCategorySmallStraightScore(thrownDice);
                
            case LARGESTRAIGHT:
                addCategoryLargeStraightScore(thrownDice);
                
            case FULLHOUSE:
                addCategoryFullHouseScore(thrownDice);
                
            case CHANCE:
                addCategoryChanceScore(thrownDice);
                
            case YATZY:
                addCategoryYatzyScore(thrownDice);
                       
        }

    }
    
    public void addCategoryOnesScore(int[] thrownDice) { 
        addPlayerScore(getCurrentPlayer(), Category.ONES, thrownDice[0] * 1);
        
    }
    
    public void addCategoryTwosScore(int[] thrownDice) { 
        addPlayerScore(getCurrentPlayer(), Category.TWOS, thrownDice[1] * 2);
        
    }
    
    public void addCategoryThreesScore(int[] thrownDice) { 
        addPlayerScore(getCurrentPlayer(), Category.THREES, thrownDice[2] * 3);
        
    }
    
    public void addCategoryFoursScore(int[] thrownDice) {
        addPlayerScore(getCurrentPlayer(), Category.FOURS, thrownDice[3] * 4);
        
    }
    
    public void addCategoryFivesScore(int[] thrownDice) {
        addPlayerScore(getCurrentPlayer(), Category.FIVES, thrownDice[4] * 5);
        
    }
    
    public void addCategorySixesScore(int[] thrownDice) {
        addPlayerScore(getCurrentPlayer(), Category.SIXES, thrownDice[5] * 6);
        
    }
    
    public void addCategoryOnePairScore(int[] thrownDice) {
        int correctDice = 0;
        if (checkCategoryOnePair(thrownDice)) {
            for (int i = 0; i < thrownDice.length; i++) {
                if (thrownDice[i] >= 2) {
                    correctDice = i + 1;
                }
            }
            addPlayerScore(getCurrentPlayer(), Category.ONEPAIR, 2*(correctDice));
        } else {
            addPlayerScore(getCurrentPlayer(), Category.ONEPAIR, 0);
        }
    }
    
    public void addCategoryTwoPairScore(int[] thrownDice) {
        int sum = 0;
        if (checkCategoryTwoPair(thrownDice)) {
            for (int i = 0; i < thrownDice.length; i++) {
                if (thrownDice[i] >= 2) {
                    sum = sum + 2 * (i + 1);
                }
            }
            addPlayerScore(getCurrentPlayer(), Category.TWOPAIR, sum);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.TWOPAIR, 0);
        }
    }
    
    public void addCategoryThreeOfAKindScore(int[] thrownDice) {
       int correctDice = 0;
        if (checkCategoryThreeOfAKind(thrownDice)) {
            for (int i = 0; i < thrownDice.length; i++) {
                if (thrownDice[i] >= 3) {
                    correctDice = i + 1;
                }
            }
            addPlayerScore(getCurrentPlayer(), Category.THREEOFAKIND, 3 * correctDice);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.THREEOFAKIND, 0);
        }
        
    }
    
    public void addCategoryFourOfAKindScore(int[] thrownDice) {
        int correctDice = 0;
        if (checkCategoryFourOfAKind(thrownDice)) {
            for (int i = 0; i < thrownDice.length; i++) {
                if (thrownDice[i] >= 4) {
                    correctDice = i + 1;
                }
            }
            addPlayerScore(getCurrentPlayer(), Category.FOUROFAKIND, 4 * correctDice);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.FOUROFAKIND, 0);
        }
        
       
    }
    
    public void addCategorySmallStraightScore(int[] thrownDice) {
        if (checkCategorySmallStraight(thrownDice)) {
            addPlayerScore(getCurrentPlayer(), Category.SMALLSTRAIGHT, 15);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.SMALLSTRAIGHT, 0);
        }
    }
    
    public void addCategoryLargeStraightScore(int[] thrownDice) {
        if (checkCategoryLargeStraight(thrownDice)) {
            addPlayerScore(getCurrentPlayer(), Category.LARGESTRAIGHT, 20);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.LARGESTRAIGHT, 0);
        }
    }
    
    public void addCategoryFullHouseScore(int[] thrownDice) {
        if (checkCategoryFullHouse(thrownDice)) {
            ArrayList<Integer> pairIndices = new ArrayList<>();
            for (int i = 0; i < thrownDice.length; i++) {
                if (thrownDice[i] >= 4) {
                    pairIndices.add(i);
                }
            }
            if (pairIndices.get(0) > pairIndices.get(1)) {
                int score = pairIndices.get(0) * 3 + pairIndices.get(1) * 2;
                addPlayerScore(getCurrentPlayer(), Category.FULLHOUSE, score);
            } else {
                int score = pairIndices.get(1) * 3 + pairIndices.get(0) * 2;
                addPlayerScore(getCurrentPlayer(), Category.FULLHOUSE, score);
            }
        } else {
            addPlayerScore(getCurrentPlayer(), Category.FULLHOUSE, 0);
        }
    }
    
    public void addCategoryChanceScore(int[] thrownDice) {
        int sum = 0;
        ArrayList<Integer> eligibleValues = new ArrayList<>();
        for (int i = 0; i < thrownDice.length; i++) {
            sum = sum + (thrownDice[i] * i + 1);
        }
        addPlayerScore(getCurrentPlayer(), Category.CHANCE, sum);
            
    }
    
    public void addCategoryYatzyScore(int[] thrownDice) {
        int correctDice = 0;
        if (checkCategoryYatzy(thrownDice)) {
            for (int i = 0; i < thrownDice.length; i++) {
                if (thrownDice[i] == 5) {
                    correctDice = i + 1;
                }
            }
            addPlayerScore(getCurrentPlayer(), Category.FOUROFAKIND, 5 * correctDice + 50);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.FOUROFAKIND, 0);
        }
        
    }
    
    
}
