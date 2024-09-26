package src.game;

import java.awt.*;

public class Asteroid extends Sprite {
    private final int RADIUS;

    public Asteroid(int x, int y, double angle) {
        super(x, y, angle);
        this.RADIUS = 50;
    }

    @Override
    public void render(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Polygon p = getBounds();
        g.setColor(Color.blue);
        g.fillPolygon(p);

    }

    @Override
    public Polygon getBounds() {
        Polygon asteroidShape = new Polygon();
        for (int i = 0; i < 5; i++) {
            asteroidShape.addPoint(
                x_pos + (int) (Math.cos(- angle + i * 2 * Math.PI / 5) * RADIUS),
                y_pos + (int) (Math.sin(- angle + i * 2 * Math.PI / 5) * RADIUS)
            );
        }
        return asteroidShape;
    }
    
}
