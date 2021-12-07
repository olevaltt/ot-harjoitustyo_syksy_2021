package yatzy.domain;

import java.lang.Math;
import java.util.Arrays;

//This class represents all five dice that are used during the game
public class Dice {
    
    final static int NOF_DICE = 5;
    int[] result = new int[NOF_DICE];
    
    public Dice() {
        Arrays.fill(this.result, -1);
    }
    
    public int[] throwDice() {

        for (int i = 0; i < NOF_DICE; i++) {
            if (result[i] == -1) {
                result[i] = (int)  (Math.random() * 6) + 1;
            }
        }
        return result;
    }
    
    public void chooseThrowableDice(int[] throwableDice) {
        for (int index : throwableDice) {
            result[index] = -1;
        }
    }
    
    public int[] getResult() {
        return this.result;
    }
    
    public void clearDice() {
        Arrays.fill(this.result, -1);
    }
    
    
    
    
}
