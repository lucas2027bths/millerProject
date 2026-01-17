import java.util.Scanner;

public class Player {

    //Constants
    private static final int DEFAULT_STAT = 5;
    private static final int MAX_STAT = 10;
    private static final int MIN_STAT = 1;
    private static final int DISTRIBUTE_POINTS = 7;
    private static final int DEFAULT_AGILITYPOINTS = 5;
    //Health and other htings
    private static final int DEFAULT_HEALTH = 140;
    private int health;
    private int maxHealth;
    private int actionPoints;

   //stimpak
    private int stimpaks = 12;
    private static final int STIMPAK_HEAL = 10;
    private static final int STIMPAK_AP = 2;
    //gun
    private static final int GAUSS_AP = 4;
    private static final int GAUSS_RELOAD_AP = 2;
    private static final int GAUSS_CLIP_MAX = 40;
    private static final int GAUSS_MIN_DAMAGE = 25;
    private static final int GAUSS_BULLET_COST = 10;

    private int microFusionCells = 400;
    private int gaussClip = GAUSS_CLIP_MAX;

    //melee
    private static final int POWER_FIST_AP = 3;
    private static final int POWER_FIST_MIN_DAMAGE = 15;

    //special
    private int strength;
    private int perception;
    private int endurance;
    private int charisma;
    private int intelligence;
    private int agility;
    private int luck;

    private Frank enemy;


    public Player(int[] special) {
        strength     = special[0];
        perception   = special[1];
        endurance    = special[2];
        charisma     = special[3];
        intelligence = special[4];
        agility      = special[5];
        luck         = special[6];

        maxHealth = DEFAULT_HEALTH + (20 * endurance);
        health = maxHealth;
    }

    public static int getDefaultStat() { return DEFAULT_STAT; }
    public static int getMaxStat() { return MAX_STAT; }
    public static int getMinStat() { return MIN_STAT; }
    public static int getDistributePoints() { return DISTRIBUTE_POINTS; }
    public int getHealth() { return health; }


    public void takeDamage(int damage) {
        System.out.println("You took " + damage + " damage!");
        health -= damage;
    }

    public void setEnemy(Frank enemy) {
        this.enemy = enemy;
    }

    public void turn() {
        Scanner scanner = Game.scan;
        actionPoints = DEFAULT_AGILITYPOINTS + (agility * 2);

        while (actionPoints > 1 && health > 0) {
            SoundPlayer.playSound("src/startTurn.wav", false);

            System.out.println("\nAP: " + actionPoints);
            System.out.println("1. Gauss Rifle (" + gaussClip + "/" + GAUSS_CLIP_MAX + ") (" + GAUSS_AP + " AP)");
            System.out.println("2. Power Fist (" + POWER_FIST_AP + " AP)");
            System.out.println("3. Stimpak (" + stimpaks + ") (" + STIMPAK_AP + " AP) HP: " + health);
            System.out.println("4. Skip turn");
            System.out.println("5. Inspect Frank");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1 -> useGaussRifle();
                case 2 -> usePowerFist();
                case 3 -> useStimpak();
                case 4 -> { return; }
                case 5 -> inspectEnemy();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void useGaussRifle() {
        if (actionPoints < GAUSS_AP || microFusionCells <= 0) {
            SoundPlayer.playSound("src/denied.wav", false);
            System.out.println("Not enough AP or ammo.");
            return;
        }

        if (gaussClip <= 0) {
            SoundPlayer.playSound("src/reload.wav", true);
            System.out.println("Reloading...");
            gaussClip = GAUSS_CLIP_MAX;
            microFusionCells -= GAUSS_CLIP_MAX;
            actionPoints -= GAUSS_RELOAD_AP;
            return;
        }

        SoundPlayer.playSound("src/gaussShoot.wav", true);
        int hitChance = 50 + (luck * 5) + (perception * 3);
        if (random(1, 100) <= hitChance) {
            enemy.takeDamage(random(GAUSS_MIN_DAMAGE, GAUSS_MIN_DAMAGE + random(0, perception * 5)));
        }else{
            System.out.println("You missed!");
        }
        gaussClip -= GAUSS_BULLET_COST;
        actionPoints -= GAUSS_AP;
    }

    private void usePowerFist() {
        if (actionPoints < POWER_FIST_AP) {
            SoundPlayer.playSound("src/denied.wav", false);
            System.out.println("Not enough AP!");
            return;
        }
        int hitChance = 50 + (luck * 5) + (perception * 3);
        hitChance = Math.min(hitChance, 90);

        if (random(1, 100) <= hitChance) {
            enemy.takeDamage(random(POWER_FIST_MIN_DAMAGE, POWER_FIST_MIN_DAMAGE * strength));
        }else{
            System.out.println("You missed!");
        }
        actionPoints -= POWER_FIST_AP;
    }

    private void useStimpak() {
        if (stimpaks <= 0 || actionPoints < STIMPAK_AP) {
            SoundPlayer.playSound("src/denied.wav", false);
            return;
        }

        health = Math.min(maxHealth, health + (STIMPAK_HEAL + (intelligence * 5)));
        stimpaks--;
        actionPoints -= STIMPAK_AP;

        System.out.println("Health restored to " + health);
    }

    private void inspectEnemy() {
        SoundPlayer.playSound("src/inspect.wav", false);
        System.out.println("Frank HP: " + enemy.getHealth() +
                " | Bullets: " + enemy.getBullets());
    }


    private int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
