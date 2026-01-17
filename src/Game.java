import java.util.Scanner;
public class Game {
    public static Scanner scan = new Scanner(System.in);
    public void runGame() {
        int[] specialStats = giveStats();
        Player player = new Player(specialStats);
        System.out.println();
        Screens.frankScreen();
        Frank frank = new Frank(player);
        player.setEnemy(frank);
        while (frank.getHealth() > 0 && player.getHealth() > 0){
            player.turn();
            frank.turn();
        }
        if (player.getHealth() > 0){
            System.out.println("You won and Frank Horrigan was killed.");
        }else{
            System.out.println("You lost.");
            Screens.deathScreen();
        }
    }
    private int[] giveStats() {
        int defaultStat = Player.getDefaultStat();
        int maxStat = Player.getMaxStat();
        int minStat = Player.getMinStat();
        int distributePoints = Player.getDistributePoints();
        System.out.println("Welcome to fallout player");
        System.out.println("You will create a character to fight frank horrigan");
        System.out.println("You have been given " + distributePoints + " points to give to your S.P.E.C.I.A.L. They all start off at " + defaultStat);
        System.out.println("You can lower the other stats to gain more points to distribute");
        System.out.println("Keep in mind that they max out at " + maxStat);

        String[] special = {"Strength", "Perception", "Endurance", "Charisma", "Intelligence", "Agility", "Luck"};
        int[] specialNums = new int [special.length];
        System.out.println("Remember, only type from "+minStat+"-"+maxStat);
        for (int x = 0; x < special.length;x++){
            System.out.print("Please type the points you would like to set for " + special[x] + ": ");
            SoundPlayer.playSound("src/startTurn.wav",false);
            int setValue = scan.nextInt();

            while (setValue > maxStat || setValue < minStat){
                System.out.println("Remember, only type from 1-"+maxStat);
                setValue = scan.nextInt();
            }

            if (setValue < defaultStat){
                distributePoints += (defaultStat - setValue);
            }
            if (setValue > defaultStat){
                distributePoints -= (setValue - defaultStat);
                if (distributePoints < 0){
                    setValue += distributePoints;
                    distributePoints = 0;
                }
            }
            specialNums[x] = setValue;
            System.out.println("Your " + special[x] + " is now " + setValue);
            System.out.println("Your distribution points are: " +distributePoints);
        }
        return specialNums;
    }
}
