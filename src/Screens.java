import javax.swing.JFrame;
import javax.swing.JLabel; // Example of another component
import javax.swing.ImageIcon;

public class Screens {
    public static void deathScreen() {
        JFrame frame = new JFrame("Death");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel death = new JLabel(new ImageIcon("src/deathScreen.png"));
        frame.add(death);
        frame.setVisible(true);
    }
}
