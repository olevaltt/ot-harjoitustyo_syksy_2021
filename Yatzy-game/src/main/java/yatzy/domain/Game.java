package yatzy.domain;

import java.util.LinkedList;
import java.util.Queue;
import javafx.beans.property.ListProperty;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;
import javafx.collections.FXCollections;


public class Game {
    
    Queue<Player> players = new LinkedList<>();
    int totalTurns;
    int turnCounter = 1;
    boolean winnerFound = false;
    int throwCount = 0;
    final int bonusAmount = 50;

    public Game(int playerCount) {
        this.totalTurns = 15;
        for (int i = 1; i <= playerCount; i++) {
            Player player = new Player(i);
            players.add(player);
        }
    }
    
    public Player getPlayerById(int id) {
        return players.stream().filter(player -> player.getPlayerId() == id).findAny().orElse(null);
    }

    public void addPlayerScore(Player player, Category category, int result) {
        player.addScore(category, result);
    }

    public Player getCurrentPlayer() {
        return players.peek();
    }
    
    public void changeTurn() {
        if (getCurrentPlayer().getPlayerId() == this.players.size()) {
            this.turnCounter++;
        }
        
        Player player = players.poll();
        players.add(player);

        this.throwCount = 0;
        if (turnCounter == totalTurns + 1) {
            winnerFound = true;
        }
    }
    
    public int getBonusAmount() {
        return this.bonusAmount;
    }
    
    public boolean getWinnerFound() {
        return winnerFound;
    }
    
    public void increaseThrowCounter() {
        this.throwCount++;
    }
    
    public int getThrowCount() {
        return this.throwCount;
    }
    
    public int getCurrentTurn() {
        return this.turnCounter;
    }
    
    public boolean throwAllowed() {
        return this.throwCount < 3;
    }
    
    public void updatePlayerScores(ListProperty<List<Integer>> scores) {
        scores.set(
            FXCollections.observableList(IntStream.range(1, players.size() + 1).mapToObj((id) -> getPlayerById(id).getScore()).map((scoreMap) -> {
                return Arrays.asList(Category.values()).stream().map((category) -> scoreMap.get(category)).collect(Collectors.toList());
            }).collect(Collectors.toList()))
        );
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
    }
    
    public boolean checkIfScoreIsPlaceable(Category category, int[] result) {
        int[] thrownDice = this.checkThrownDice(result);

        switch (category) {
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
                
            default:
                return false;    
                       
        }
    }
    
    
    public boolean checkCategoryOnes(int[] thrownDice) {
        return thrownDice[0] > 0;
    }
    
    public boolean checkCategoryTwos(int[] thrownDice) {
        return thrownDice[1] > 0;
    }
    
    public boolean checkCategoryThrees(int[] thrownDice) {
        return thrownDice[2] > 0;
    }
    
    public boolean checkCategoryFours(int[] thrownDice) {
        return thrownDice[3] > 0;
    }
    
    public boolean checkCategoryFives(int[] thrownDice) {
        return thrownDice[4] > 0;
    }
    
    public boolean checkCategorySixes(int[] thrownDice) {
        return thrownDice[5] > 0;
    }
    
    public boolean checkCategoryOnePair(int[] thrownDice) {
        ArrayList<Integer> eligibleValues = new ArrayList<>();
        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] >= 2) {
                eligibleValues.add(i);
            }
        }
        return eligibleValues.size() > 0;      
    }
    
    public boolean checkCategoryTwoPair(int[] thrownDice) {
        ArrayList<Integer> howManyPairs = new ArrayList<>();

        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] >= 2) {
                howManyPairs.add(i);
            }
        }
        return howManyPairs.size() == 2;
    }
    
    public boolean checkCategoryThreeOfAKind(int[] thrownDice) {
        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] > 2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkCategoryFourOfAKind(int[] thrownDice) {
        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] > 3) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkCategorySmallStraight(int[] thrownDice) {
        int eligibleValues = 0;
        for (int i = 0; i < 5; i++) {
            if (thrownDice[i] == 1) {
                eligibleValues++;
            }
        }
        return eligibleValues == 5;
    }
    
    public boolean checkCategoryLargeStraight(int[] thrownDice) {
        int eligibleValues = 0;
        for (int i = 1; i < 6; i++) {
            if (thrownDice[i] == 1) {
                eligibleValues++;
            }
        }
        return eligibleValues == 5;
    }
    
    public boolean checkCategoryFullHouse(int[] thrownDice) {
        int howManyPairs = 0;
        int howManyThreeOfAKinds = 0;

        for (int amount : thrownDice) {
            if (amount == 2) {
                howManyPairs++;
            } else if (amount == 3) {
                howManyThreeOfAKinds++;
            }
        }
        
        return howManyPairs == 1 && howManyThreeOfAKinds == 1;
    }
    
    public boolean checkCategoryChance(int[] thrownDice) {
        return true;
    }
    
    public boolean checkCategoryYatzy(int[] thrownDice) {
        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] == 5) {
                return true;
            }
        }
        return false;
    }
    
    public int[] checkThrownDice(int[] result) {
        int[] thrownDice = {0, 0, 0, 0, 0, 0};
        for (int value : result) {
            thrownDice[value - 1] = thrownDice[value - 1] + 1;
        }
        return thrownDice;
    }
    
    public void addScore(int categoryNumber, int[] result) {
        int[] thrownDice = checkThrownDice(result);
        Category[] categories = Category.values();
        
        switch (categories[categoryNumber]) {
            case ONES:
                addCategoryOnesScore(thrownDice);
                break;
                
            case TWOS:
                addCategoryTwosScore(thrownDice);
                break;
                
            case THREES:
                addCategoryThreesScore(thrownDice);
                break;
                
            case FOURS:
                addCategoryFoursScore(thrownDice);
                break;
                    
            case FIVES:
                addCategoryFivesScore(thrownDice);
                break;
                
            case SIXES:
                addCategorySixesScore(thrownDice);
                break;
                
            case ONEPAIR:
                addCategoryOnePairScore(thrownDice);
                break;
                
            case TWOPAIR:
                addCategoryTwoPairScore(thrownDice);
                break;
                
            case THREEOFAKIND:
                addCategoryThreeOfAKindScore(thrownDice);
                break;
                
            case FOUROFAKIND:
                addCategoryFourOfAKindScore(thrownDice);
                break;
                
            case SMALLSTRAIGHT:
                addCategorySmallStraightScore(thrownDice);
                break;
                
            case LARGESTRAIGHT:
                addCategoryLargeStraightScore(thrownDice);
                break;
                
            case FULLHOUSE:
                addCategoryFullHouseScore(thrownDice);
                break;
                
            case CHANCE:
                addCategoryChanceScore(thrownDice);
                break;
                
            case YATZY:
                addCategoryYatzyScore(thrownDice);
                break;         
        }
        changeTurn();
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
            addPlayerScore(getCurrentPlayer(), Category.ONEPAIR, 2 * (correctDice));
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
            int sum = 0;
            for (int i = 0; i < thrownDice.length; i++) {
                if (thrownDice[i] == 2) {
                    sum += 2 * (i + 1);
                } else if (thrownDice[i] == 3) {
                    sum += 3 * (i + 1);
                }
            }
            addPlayerScore(getCurrentPlayer(), Category.FULLHOUSE, sum);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.FULLHOUSE, 0);
        }
    }
    
    public void addCategoryChanceScore(int[] thrownDice) {
        int sum = 0;
        for (int i = 0; i < thrownDice.length; i++) {
            sum = sum + (thrownDice[i] * (i + 1));
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
            addPlayerScore(getCurrentPlayer(), Category.YATZY, 5 * correctDice + 50);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.YATZY, 0);
        } 
    }
}
