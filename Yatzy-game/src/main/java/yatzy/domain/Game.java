package yatzy.domain;

import java.util.LinkedList;
import java.util.Queue;



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
    
    
    public int getPlayerScore(Player player, Category category) {
        return player.getScore(category);
    }
    
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
    
    public boolean throwAllowed() {
        if (this.throwCount >= 3) {
            return false;
        } else {
            return true;
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
    
    public void setCategoryOnes(int[] thrownDice) { 
        addPlayerScore(getCurrentPlayer(), Category.ONES, thrownDice[0] * 1);
    }
    
    public void setCategoryTwos(int[] thrownDice) { 
        addPlayerScore(getCurrentPlayer(), Category.TWOS, thrownDice[1] * 1);
    }
    
    public void setCategoryThrees(int[] thrownDice) { 
        addPlayerScore(getCurrentPlayer(), Category.THREES, thrownDice[2] * 1);
    }
    
    public void setCategoryFours(int[] thrownDice) {
        addPlayerScore(getCurrentPlayer(), Category.FOURS, thrownDice[3] * 1);
    }
    
    public void setCategoryFives(int[] thrownDice) {
        addPlayerScore(getCurrentPlayer(), Category.FIVES, thrownDice[4] * 1);
    }
    
    public void setCategorySixes(int[] thrownDice) {
        addPlayerScore(getCurrentPlayer(), Category.SIXES, thrownDice[5] * 1);
    }
    
    public void setCategoryOnePair(int[] thrownDice) {
        //This can possibly be made in a better way.
        //For now this solution works as it accounts the case where player has 2 legitimate results that fit this gategory.
        //In that case this solution chooses the biggest score to be added.
        if (thrownDice[0] >= 2) {
            addPlayerScore(getCurrentPlayer(), Category.ONEPAIR, 2);
        }
        if (thrownDice[1] >= 2) {
            addPlayerScore(getCurrentPlayer(), Category.ONEPAIR, 4);
        }
        if (thrownDice[2] >= 2) {
            addPlayerScore(getCurrentPlayer(), Category.ONEPAIR, 6);
        }
        if (thrownDice[3] >= 2) {
            addPlayerScore(getCurrentPlayer(), Category.ONEPAIR, 8);
        }
        if (thrownDice[4] >= 2) {
            addPlayerScore(getCurrentPlayer(), Category.ONEPAIR, 10);
        }
        if (thrownDice[5] >= 2) {
            addPlayerScore(getCurrentPlayer(), Category.ONEPAIR, 12);
        }
        
        // How to add 0 points so that this solution works?
    }
    
    public void setCategoryTwoPair(int[] thrownDice) {
        
    }
    
    public void setCategoryThreeOfAKind(int[] thrownDice) {
        if (thrownDice[0] >= 3) {
            addPlayerScore(getCurrentPlayer(), Category.THREEOFAKIND, 3);
        } else if (thrownDice[1] >= 3) {
            addPlayerScore(getCurrentPlayer(), Category.THREEOFAKIND, 6);
        } else if (thrownDice[2] >= 3) {
            addPlayerScore(getCurrentPlayer(), Category.THREEOFAKIND, 9);
        } else if (thrownDice[3] >= 3) {
            addPlayerScore(getCurrentPlayer(), Category.THREEOFAKIND, 12);
        } else if (thrownDice[4] >= 3) {
            addPlayerScore(getCurrentPlayer(), Category.THREEOFAKIND, 15);
        } else if (thrownDice[5] >= 3) {
            addPlayerScore(getCurrentPlayer(), Category.THREEOFAKIND, 18);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.THREEOFAKIND, 0);
        }
    }
    
    public void setCategoryFourOfAKind(int[] thrownDice) {
        if (thrownDice[0] >= 4) {
            addPlayerScore(getCurrentPlayer(), Category.FOUROFAKIND, 4);
        } else if (thrownDice[1] >= 4) {
            addPlayerScore(getCurrentPlayer(), Category.FOUROFAKIND, 8);
        } else if (thrownDice[2] >= 4) {
            addPlayerScore(getCurrentPlayer(), Category.FOUROFAKIND, 12);
        } else if (thrownDice[3] >= 4) {
            addPlayerScore(getCurrentPlayer(), Category.FOUROFAKIND, 16);
        } else if (thrownDice[4] >= 4) {
            addPlayerScore(getCurrentPlayer(), Category.FOUROFAKIND, 20);
        } else if (thrownDice[5] >= 4) {
            addPlayerScore(getCurrentPlayer(), Category.FOUROFAKIND, 24);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.FOUROFAKIND, 0);
        }
    }
    
    public void setCategorySmallStraight(int[] thrownDice) {
        if (thrownDice[0] == 1 && thrownDice[1] == 1 && thrownDice[2] == 1 && thrownDice[3] == 1 && thrownDice[4] == 1) {
            addPlayerScore(getCurrentPlayer(), Category.SMALLSTRAIGHT, 15);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.SMALLSTRAIGHT, 0);
        }
    }
    
    public void setCategoryLargeStraight(int[] thrownDice) {
        if (thrownDice[1] == 1 && thrownDice[2] == 1 && thrownDice[3] == 1 && thrownDice[4] == 1 && thrownDice[5] == 1) {
            addPlayerScore(getCurrentPlayer(), Category.LARGESTRAIGHT, 20);
        } else {
            addPlayerScore(getCurrentPlayer(), Category.LARGESTRAIGHT, 0);
        }
    }
    
    public void setCategoryFullHouse(int[] thrownDice) {
        
    }
    
    public void setCategoryChance(int[] thrownDice) {
        int result = (thrownDice[0] * 1 + thrownDice[1] * 2 + thrownDice[2] * 3 + thrownDice[3] * 4 + thrownDice[4] * 5 + thrownDice[5] * 6);
        addPlayerScore(getCurrentPlayer(), Category.CHANCE, result);
    }
    
    public void setCategoryYatzy(int[] thrownDice) {
        for (int i = 0; i < thrownDice.length; i++) {
            if (thrownDice[i] == 5) {
                addPlayerScore(getCurrentPlayer(), Category.YATZY, (thrownDice[i] * 5) + 50);
            } else {
                addPlayerScore(getCurrentPlayer(), Category.YATZY, 0);
            }
        }
        
    }
    
    
}
