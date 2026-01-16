import java.util.Scanner;

public class Player {
    private int Name;
    private final static int defaultStat = 5;
    private final static int maxStat = 10;
    private final static int minStat = 1;
    private final static int distributePoints = 7;
    //S.P.E.C.I.A.L traits
    private int intelligence;
    private int strength;
    private int endurance;
    private int charisma;
    private int agility ;
    private int luck ;
    private int perception;
    private int health;
    private int actionPoints;
    private final int defaultHealth = 40;
    public Player(int[]special){
        strength = special[0];
        perception = special[1];
        endurance = special[2];
        charisma = special[3];
        intelligence = special[4];
        agility = special[5];
        luck = special[6];
        health = defaultHealth * endurance;
    }
    public static int getDefaultStat(){
        return defaultStat;
    }
    public static int getMaxStat(){
        return maxStat;
    }
    public static int getMinStat(){
        return minStat;
    }
    public static int getDistributePoints(){
        return distributePoints;
    }

    public void takeDamage(int damage){
        System.out.println("You took " + damage + "!");
        health -= damage;
    }
}
