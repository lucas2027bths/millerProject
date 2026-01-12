import java.util.Scanner;

public class Player {
    private int Name;
    //S.P.E.C.I.A.L traits
    private int Intelligence = 5;
    private int Strength = 5;
    private int Endurance = 5;
    private int Charisma = 5;
    private int Agility = 5;
    private int Luck = 5;
    private int Perception = 5;

    public Player(){

    }

    private void giveStats() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to fallout player");
        System.out.println("You will create a character to go out into the wasteland");
        System.out.println("You have been given 5 points to give to your S.P.E.C.I.A.L. They all start off at 5");
        System.out.println("Keep in mind that they max out at 10");
        System.out.println("Please insert the amount of points you will give to Intelligence");

    }
}
