import java.util.Scanner;

public class Player {
    private int Name;
    private final static int defaultStat = 5;
    private final static int maxStat = 10;
    private final static int minStat = 1;
    private final static int distributePoints = 7;


    private int stimpaks = 12;
    private final int stimpakHeal = 10;
    private final int stimpakAP = 2;

    private final int gaussRifleAP = 4;
    private final int gaussRifleReloadAp = 2;
    private final int gaussClipMax = 25;
    private int microFusionCells = 400;
    private int gaussClip = gaussClipMax;
    private int gaussRifleMinDamage = 40;
    private int gaussRifleBulletSpend = 10;

    private final int powerFistAP = 3;
    private final int powerFistMinDamage = 15;
    private Frank enemy;
    //S.P.E.C.I.A.L traits
    private int intelligence;
    private int strength;
    private int endurance;
    private int charisma;
    private int agility ;
    private int luck ;
    private int perception;
    private int health;
    private int maxHealth;
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
        maxHealth = defaultHealth * endurance;
        health = maxHealth;
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
    public int getHealth(){
        return health;
    }
    public void takeDamage(int damage){
        System.out.println("You took " + damage + " damage!");
        health -= damage;
    }
    public void setEnemey(Frank enemy){
        this.enemy = enemy;
    }
    public void turn(){
        Scanner scanner = Game.scan;
        actionPoints = agility;
        while (actionPoints > 1 && health > 0) {
            System.out.println("You have " + actionPoints + " action points.");
            System.out.println("1. Use Gauss Rifle (" + gaussClip + "/" + gaussClipMax + ") (" + gaussRifleAP + " AP)");
            System.out.println("2. Use Power Fist (" + powerFistAP + " AP)");
            System.out.println("3. Use Stimpak (" + stimpaks + " left.) ( " + stimpakAP + " AP) (" + health + " health)");
            System.out.println("4. Skip turn");
            System.out.println("5. Inspect Frank (No AP cost)");
            int choice = scanner.nextInt();
            if (choice == 1 && actionPoints >= 4 && microFusionCells > 0) {
                if (gaussClip > 0) {
                    if (random(0, 5 / luck) == 0) {
                        enemy.takeDamage(random(gaussRifleMinDamage, gaussRifleMinDamage * perception));
                    } else {
                        System.out.println("You missed!");
                    }
                    gaussClip -= gaussRifleBulletSpend;
                    actionPoints -= gaussRifleAP;
                } else {
                    System.out.println("reloading");
                    gaussClip = gaussClipMax;
                    microFusionCells -= gaussClipMax;
                }
            } else if (choice == 1) {
                System.out.println("You either don't have enough Action points or you ran out of bullets");
            }
            if (choice == 2 && actionPoints >= 3) {
                if (random(0, 5 / luck) == 0) {
                    enemy.takeDamage(random(powerFistMinDamage, powerFistMinDamage * strength));
                } else {
                    System.out.println("You missed!");
                }
                actionPoints -= powerFistAP;
            }else if (choice == 2){
                System.out.println("You don't have enough action points!");
            }
            if (choice == 3 && stimpaks > 0) {
                health = Math.min(maxHealth,(health+(stimpakHeal * intelligence)));
                System.out.println("You now have " + health);
                actionPoints -= stimpakAP;
            }
            if (choice == 4) {
                break;
            }
            if (choice == 5){
                System.out.println("Frank has " + enemy.getHealth() + " health and he has " + enemy.getBullets() + " bullets remaining");
            }
        }
    }

    private int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
