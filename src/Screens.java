import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Screens {

    public static void deathScreen() {
        showScreen("death", "src/deathMessage.wav", "src/deathScreen.png");
    }

    public static void frankScreen() {
        showScreen("frank", "src/hor.wav", "src/frank.png");
    }

    private static void showScreen(String name, String sound, String imagePath) {
        final BufferedImage img;
        try {
            img = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        JFrame frame = new JFrame(name);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel() {
            @Override //overrides jpanels default drawing from what I saw online
            protected void paintComponent(Graphics g) { //I learned protected lets this class, and sub class / class in same package use this so thats cool
                super.paintComponent(g); //not completley sure what super means yet but apparently something like it gets the parent class and runs jpanels regular paintcomponet before I put the image
                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
                //I think I'm just adding on a image onto jpanels regular "paint paintComponent"
            }
        };
        frame.add(panel);
        //graphic stuff that just lets me full screen
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);
        SoundPlayer.playSound(sound, true);
        frame.dispose();
    }
}
