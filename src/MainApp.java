import javax.swing.*;
import java.awt.*;

public class MainApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MainFrame("InstaBot");
                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setLayout(new BorderLayout());
                frame.setResizable(false);
            }
        });
    }
}