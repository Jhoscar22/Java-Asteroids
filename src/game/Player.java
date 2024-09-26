package src.game;

import java.awt.*;

public class Player extends Sprite {
    private final int SIDE_LENGTH;

    public Player(int x, int y, double angle) {
        super(x, y, angle);
        this.SIDE_LENGTH = 200;
    }

    private int getHalfHeight() {
        return (int) (SIDE_LENGTH * Math.sqrt(3) / 4);
    }

    @Override
    public void render(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Polygon p = getBounds();
        g.setColor(Color.magenta);
        g.fillPolygon(p);
    }

    @Override
    public Polygon getBounds() {
        Polygon playerShape = new Polygon();
        int half_height = getHalfHeight();

        playerShape.addPoint(
            x_pos + (int) (Math.cos(- angle + Math.PI / 6) * half_height),
            y_pos + (int) (Math.sin(- angle + Math.PI / 6) * half_height)
        );
        playerShape.addPoint(
            x_pos + (int) (Math.cos(- angle + 5 * Math.PI / 6) * half_height),
            y_pos + (int) (Math.sin(- angle + 5 * Math.PI / 6) * half_height)
        );
        playerShape.addPoint(
            x_pos + (int) (Math.cos(- angle + 9 * Math.PI / 6) * half_height),
            y_pos + (int) (Math.sin(- angle + 9 * Math.PI / 6) * half_height)
        );
        return playerShape;
    }


    public void move(int dx, int dy) {
        int half_height = getHalfHeight();
        x_pos = Math.max(half_height, Math.min(x_pos + dx, 500 - half_height));
        y_pos = Math.max(half_height, Math.min(y_pos + dy, 500 - half_height));
    }

    public void rotate(double angle) {
        this.angle += angle;
    }
    
}
