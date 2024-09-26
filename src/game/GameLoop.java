package src.game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import src.graphics.Scene;

public class GameLoop {
    private Scene scene;
    private boolean isRunning;
    private final int FPS = 120;
    private final double frameTime = 1_000_000_000.0 / FPS;  // Time per frame in nanoseconds

    private ExecutorService executor;  // Executor service for future parallelization

    public GameLoop(Scene scene) {
        this.scene = scene;
        this.isRunning = true;
        // Create a thread pool for parallelizing tasks (e.g., collision detection)
        // Using the number of available processors on the machine
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        this.executor = Executors.newFixedThreadPool(availableProcessors);
    }

    public void start() {
        long lastTime = System.nanoTime();
        double accumulator = 0;  // To accumulate time for updates when frames take longer than expected
        
        while (isRunning) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;  // Time passed in seconds
            lastTime = currentTime;

            // Update accumulator with the delta time
            accumulator += deltaTime;

            // If more than the time for one frame has passed, update the game state
            while (accumulator >= frameTime / 1_000_000_000.0) {
                update(frameTime / 1_000_000_000.0);  // Pass the delta time for smooth movement
                accumulator -= frameTime / 1_000_000_000.0;  // Remove the processed frame time from the accumulator
            }

            // Render the scene (no need to wait for exact frame timing)
            scene.repaint();

            // Cap the frame rate by sleeping if the frame finished early
            sleepUntilNextFrame(lastTime);
        }

        // Shutdown the executor when game loop stops (for collision parallelization)
        executor.shutdown();
    }

    /**
     * Updates the game state, including object movements and collision detection.
     * @param deltaTime the time passed since the last update in seconds
     */
    private void update(double deltaTime) {
        // Update all game objects using deltaTime (e.g., move sprites)
        // scene.update(deltaTime);

        // TODO: In the future, you can parallelize collision detection here by submitting tasks to the executor.
        // For example, divide the asteroids into chunks and handle them in parallel using executor.submit(...)
    }

    /**
     * Sleeps for the remaining frame time if the frame was processed faster than the target frame time.
     * @param frameStartTime the time the frame started in nanoseconds
     */
    private void sleepUntilNextFrame(long frameStartTime) {
        long elapsedTime = System.nanoTime() - frameStartTime;
        long sleepTime = (long) frameTime - elapsedTime;

        // Only sleep if the frame finished early (i.e., within the target frame time)
        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime / 1_000_000);  // Convert nanoseconds to milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}