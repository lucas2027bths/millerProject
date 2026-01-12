import java.util.Scanner;

public class Player {
    private int Name;
    private int defaultStat = 5;
    //S.P.E.C.I.A.L traits
    private int Intelligence = defaultStat;
    private int Strength = defaultStat;
    private int Endurance = defaultStat;
    private int Charisma = defaultStat;
    private int Agility = defaultStat;
    private int Luck = defaultStat;
    private int Perception = defaultStat;

    public Player(){

    }

    private void giveStats() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to fallout player");
        System.out.println("You will create a character to go out into the wasteland");
        System.out.println("You have been given 5 points to give to your S.P.E.C.I.A.L. They all start off at 5");
        System.out.println("Keep in mind that they max out at 10");
    }
}
