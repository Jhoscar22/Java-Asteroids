package src.game;

import java.awt.Polygon;

public interface Collider {
    Polygon getBounds();

    // Default implementation for collision detection
    default boolean collidesWith(Collider other) {
        Polygon p = getBounds();
        Polygon other_p = other.getBounds();

        for (int i = 0; i < p.npoints; i++) {
            if (other_p.contains(p.xpoints[i], p.ypoints[i])) {
                return true;
            }
        }

        for (int i = 0; i < other_p.npoints; i++) {
            if (p.contains(other_p.xpoints[i], other_p.ypoints[i])) {
                return true;
            }
        }

        return false;
    }
}