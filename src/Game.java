import javax.crypto.spec.DESedeKeySpec;
import java.util.Scanner;
import java.util.Set;

public class Game {
    public void runGame(){
        int[] specialStats = giveStats();
        Player player = new Player(specialStats);
    }
    private int[] giveStats() {
        Scanner scan = new Scanner(System.in);
        int defaultStat = Player.getDefaultStat();
        int maxStat = Player.getMaxStat();
        int minStat = Player.getMinStat();
        int distributePoints = Player.getDistributePoints();
        System.out.println("Welcome to fallout player");
        System.out.println("You will create a character to go out into the wasteland");
        System.out.println("You have been given " + distributePoints + " points to give to your S.P.E.C.I.A.L. They all start off at " + defaultStat);
        System.out.println("You can lower the other stats to gain more points to distribute");
        System.out.println("Keep in mind that they max out at " + maxStat);

        String[] special = {"Strength", "Perception", "Endurance", "Charisma", "Intelligence", "Agility", "Luck"};
        int[] specialNums = new int [special.length];
        System.out.println("Remember, only type from "+minStat+"-"+maxStat);
        for (int x = 0; x < special.length;x++){
            System.out.print("Please type the points you would like to set for " + special[x] + ": ");
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
        scan.close();
        return specialNums;
    }
}
