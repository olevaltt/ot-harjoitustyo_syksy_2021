
package yatzy.domain;


public enum Category {
    ONES("1"),
    TWOS("2"),
    THREES("3"),
    FOURS("4"),
    FIVES("5"),
    SIXES("6"),
    ONEPAIR("1P"),
    TWOPAIR("2P"),
    THREEOFAKIND("3L"),
    FOUROFAKIND("4L"),
    SMALLSTRAIGHT("PS"),
    LARGESTRAIGHT("SS"),
    FULLHOUSE("M"),
    CHANCE("S"),
    YATZY("Y");
    
    public final String label;
    
    private Category(String label) {
        this.label = label;
    }
    
    
    
}
