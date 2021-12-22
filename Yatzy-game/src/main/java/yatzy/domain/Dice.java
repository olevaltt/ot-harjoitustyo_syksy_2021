package yatzy.domain;

import java.lang.Math;
import java.util.Arrays;

//This class represents all five dice that are used during the game
public class Dice {
    
    final static int NOF_DICE = 5;
    int[] result = new int[NOF_DICE];
    
    public Dice() {
        Arrays.fill(this.result, 1);
    }

    
    public int[] throwAllDice() {
        return throwDice(new int[]{0, 1, 2, 3, 4});
    }
    
    public int[] throwDice(int[] throwableDice) {
        for (int diceIndex : throwableDice) {
            result[diceIndex] = (int)  (Math.random() * 6) + 1;
        }
        
        return result;
    }

    public int[] getResult() {
        return this.result;
    }
    

    
    
    
    
}
