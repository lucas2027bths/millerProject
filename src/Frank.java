public class Frank {
    private int health;
    private final int MAX_HEALTH = 999;

    private int stimpaks;
    private static final int STIMPAK_HEAL = 42;
    private static final int STIMPAK_AP = 2;
    private final int MELEE_DAMAGE;
    private static final int MELEE_AP = 3;

    private final int MINIGUN_DAMAGE;
    private static final int MINIGUN_AP = 4;
    private static final int MINIGUN_RELOAD_AP = 2;

    private int minigunClip;
    private static final int MINIGUN_MAX_CLIP = 100;

    private final int DEFAULT_ACTION_POINTS;
    private int bullets;

    private static final int LUCK_RATE = 2;

    private Player player;

    public Frank(Player player) {
        this.player = player;
        this.health = MAX_HEALTH;
        this.stimpaks = 10;
        this.MELEE_DAMAGE = 22;
        this.MINIGUN_DAMAGE = 18;
        this.minigunClip = MINIGUN_MAX_CLIP;
        this.DEFAULT_ACTION_POINTS = 10;
        this.bullets = 1000;
    }


    public void turn() {
        int actionPoints = DEFAULT_ACTION_POINTS;

        while (actionPoints > 1 && health > 0) {

            if (health < MAX_HEALTH * 0.5 && stimpaks > 0) {
                System.out.println("Frank used a stimpak!");
                stimpaks--;
                health = Math.min(MAX_HEALTH, health + STIMPAK_HEAL);
                System.out.println("Frank now has " + health + " HP!");
                actionPoints -= STIMPAK_AP;
                continue;
            }

            if (actionPoints >= MINIGUN_AP && bullets > 0) {
                if (minigunClip > 0) {
                    shootMinigun();
                    actionPoints -= MINIGUN_AP;
                } else {
                    reloadMinigun();
                    actionPoints -= MINIGUN_RELOAD_AP;
                }
                continue;
            }

            if (actionPoints >= MELEE_AP) {
                meleeAttack();
                actionPoints -= MELEE_AP;
                continue;
            }

            break;
        }
    }


    private void shootMinigun() {
        System.out.println("Frank fired his minigun!");
        SoundPlayer.playSound("src/minigunShoot.wav", true);

        int shots = Math.min(25, minigunClip);
        minigunClip -= shots;
        bullets -= shots;

        if (random(0, 5) != 1) {
            int damage = random(MINIGUN_DAMAGE, MINIGUN_DAMAGE * LUCK_RATE);
            player.takeDamage(damage);
        } else {
            System.out.println("Frank missed!");
        }
    }

    private void reloadMinigun() {
        System.out.println("Frank reloads his minigun!");
        SoundPlayer.playSound("src/Minigun_Reload.wav", true);
        minigunClip = MINIGUN_MAX_CLIP;
    }

    private void meleeAttack() {
        System.out.println("Frank swung at the player!");
        if (random(0, 5) != 1) {
            int damage = random(MELEE_DAMAGE, MELEE_DAMAGE * LUCK_RATE);
            player.takeDamage(damage);
        } else {
            System.out.println("Frank missed!");
        }
    }
    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        System.out.println("Frank was hit for " + damage + " damage!");
        health = Math.max(0, health - damage);
    }

    public int getBullets() {
        return bullets;
    }

    private int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
