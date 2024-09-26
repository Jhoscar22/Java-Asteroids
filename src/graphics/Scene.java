package src.graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

import src.game.Asteroid;
import src.game.InputMap;
import src.game.Player;
import src.game.Sprite;


public class Scene extends JPanel implements KeyListener {
    private int WIDTH = 500;
    private int HEIGHT = 500;
    private Player player;
    private ArrayList<Sprite> sprites;
    private InputMap inputMap;

    public Scene() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocusInWindow();

        sprites = new ArrayList<Sprite>();
        player = new Player(250, 250, 0);
        sprites.add(player);
        sprites.add(new Asteroid(250, 250, 0));
        inputMap = new InputMap();
        inputMap.bindPlayer(player);
        // System.out.println("Do sprites collide? " + sprites.get(0).collidesWith(sprites.get(1)));
        // System.out.println("Do sprites collide? " + sprites.get(1).collidesWith(sprites.get(0)));

        this.addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        // Enable antialiasing for smoother shapes
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw all sprites
        for (Sprite sprite: sprites) {
            sprite.render(g2D);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("You pressed: " + e.getKeyChar() + " " + e.getKeyCode());
        inputMap.handleKeyPressed(e);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
