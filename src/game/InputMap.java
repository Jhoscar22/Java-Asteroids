package src.game;

import java.awt.event.*;

public class InputMap {
    private Player player;

    public InputMap() {
        this.player = null;
    }

    public void bindPlayer(Player player) {
        this.player = player;
    }

    public void handleKeyPressed(KeyEvent e) {
        int distance = 5;
        int angle = 5;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: // move left
                player.move(-distance, 0);
                break;
            case KeyEvent.VK_W: // move up
                player.move(0, -distance);
                break;
            case KeyEvent.VK_D: // move right
                player.move(distance, 0);
                break;
            case KeyEvent.VK_S: // move down
                player.move(0, distance);
                break;
            case KeyEvent.VK_Q: // rotate counter-clockwise
                player.rotate(angle * Math.PI / 180);
                break;
            case KeyEvent.VK_E: // rotate clockwise
                player.rotate(-angle * Math.PI / 180);
                break;
        }
    }
}
