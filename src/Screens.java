import javax.swing.JFrame;
import javax.swing.JLabel; // Example of another component
import javax.swing.ImageIcon;
public class Screens {
    public static void deathScreen() {
        showScreen("death","src/deathMessage.wav","src/deathScreen.png");
    }
    public static void frankScreen() {
        showScreen("frank","src/hor.wav","src/frank.png");
    }

    private static void showScreen(String name,String sound, String image){
        JFrame frame = new JFrame(name);
        frame.setUndecorated(true);
        frame.setSize(1920, 1080);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
        SoundPlayer.playSound(sound,true);
        frame.dispose();
    }

}
