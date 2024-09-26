package src.graphics;

import javax.swing.*;

public class Window extends JFrame {
    Scene panel;

    public Window() {
        this.setTitle("Asteroids");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        panel = new Scene();
        this.add(panel);

        this.pack();
        this.setVisible(true);
    }
}