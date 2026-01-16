public class Frank {
    private int health;
    private final int maxHealth = 999;

    private int stimpaks;
    private final int stimpakHeal = 42;
    private final int stimpakAP = 2;

    private final int luckRate = 2;

    private final int meleeDamage;
    private final int meleeAp = 3;

    private final int minigunDamage;
    private final int minigunAp = 4;
    private final int minigunReloadAp = 2;

    private int minigunClip = 100;
    private final int minigunMaxClip = 100;

    private final int defaultActionPoints;
    private int bullets;

    private Player player;

    public Frank(Player player) {
        health = maxHealth;
        stimpaks = 10;
        meleeDamage = 30;
        defaultActionPoints = 10;
        minigunDamage = 38;
        bullets = 1000;
        this.player = player;
    }

    public int turn() {
        int actionPoints = defaultActionPoints;
        int totalDamage = 0;

        while (actionPoints > 1) {


            if (health < maxHealth * 0.5 && stimpaks > 0 && actionPoints >= stimpakAP) {
                stimpaks--;
                health += stimpakHeal;
                if (health > maxHealth) health = maxHealth;
                actionPoints -= stimpakAP;
                continue;
            }

            if (actionPoints >= minigunAp && bullets > 0) {
                if (minigunClip > 0) {
                    System.out.println("Frank shot his minigun");
                    minigunClip -= 25;
                    bullets -= 25;

                    if (random(0, 5) != 1) {
                        int dmg = random(minigunDamage, minigunDamage * luckRate);
                        player.takeDamage(dmg);
                        totalDamage += dmg;
                    } else {
                        System.out.println("Frank missed");
                    }

                    actionPoints -= minigunAp;
                    continue;
                } else {
                    System.out.println("Frank reloads");
                    minigunClip = minigunMaxClip;
                    actionPoints -= minigunReloadAp;
                    continue;
                }
            }

            if (actionPoints >= meleeAp) {
                System.out.println("Frank swung");
                if (random(0, 5) != 1) {
                    int dmg = random(meleeDamage, meleeDamage * luckRate);
                    player.takeDamage(dmg);
                    totalDamage += dmg;
                } else {
                    System.out.println("Frank missed");
                }
                actionPoints -= meleeAp;
                continue;
            }

            break;
        }

        return totalDamage;
    }

    private int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
