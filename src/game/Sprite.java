package src.game;

import src.graphics.Renderable;

public abstract class Sprite implements Renderable, Collider {
    protected int x_pos, y_pos; // Position
    protected double angle; // Rendering angle

    protected int speed; // Movement speed
    protected double direction; // Angle of the movement axis

    public Sprite(int x, int y, double angle) {
        this.x_pos = x;
        this.y_pos = y;
        this.angle = angle;
        this.speed = 0;
        this.direction = 0;
    }

}
