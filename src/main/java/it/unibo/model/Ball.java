package it.unibo.model;

import it.unibo.api.Direction;
import it.unibo.api.GameEntityImpl;
import it.unibo.api.GameInfo;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

/**
 * The Ball class represents a ball entity in the game.
 * It extends the GameEntityImpl class and defines the behavior
 * and properties of the ball, including its movement and collision handling.
 */
public final class Ball extends GameEntityImpl {
    private Direction dir;
    private Random rand = new Random();
    final Dimension BALL_DIMENSION = new Dimension(5,5);

    /**
     * Constructs a new Ball object at the default starting position
     * with a default direction.
     */
    public Ball() {
        super(new Point(GameInfo.GAME_WIDTH / 2, GameInfo.GAME_HEIGHT - 50),
        new Dimension(5, 5), 1);
        this.dir = new Direction(-1, -1);
    }

    /**
     * Constructs a new Ball object by duplicating the original Ball object.
     * The direction of the new Ball is inverted to prevent overlap.
     *
     * @param orig the original Ball object to duplicate
     */
    public Ball(final Ball orig) {
        super(orig.getPosition(), orig.getSize(), 1);
        this.dir = new Direction(-orig.getDirection().getHorizontalVelocity(),
                orig.getDirection().getVerticalVelocity());
    }

    /**
     * Gets the current direction of the Ball.
     *
     * @return the current direction
     */
    private Direction getDirection() {
        return dir;
    }

    /**
     * Updates the position and state of the Ball.
     * The Ball's direction is reversed if it goes out of bounds,
     * and its health is decreased if it falls out of the game area.
     */
    public void update() {
        boolean acceptable = true;
        Point candidate = new Point(position.x + dir.getHorizontalVelocity() * GameInfo.BALL_SPEED,
                position.y + dir.getVerticalVelocity() * GameInfo.BALL_SPEED);
        // Reverse direction if out of horizontal bounds
        if (candidate.getX() <= 0 || candidate.getX() >= GameInfo.GAME_WIDTH) {
            dir = new Direction(-dir.getHorizontalVelocity(), dir.getVerticalVelocity());
            acceptable = false;
        }
        // Reverse direction if touching the top
        if (candidate.getY() <= 0) {
            dir = new Direction(dir.getHorizontalVelocity(), -dir.getVerticalVelocity());
            acceptable = false;
        }
        // Decrease health if the ball falls out of the game area
        if (candidate.getY() > GameInfo.GAME_HEIGHT) {
            super.setHealth(super.getHealth() - 1);
        }
        if (acceptable) {
            position = candidate;
        }
    }

    /**
     * Checks if the Ball is still alive (health > 0).
     *
     * @return true if the Ball is alive, false otherwise
     */
    public boolean isAlive() {
        return super.isAlive();
    }

    /**
     * Handles the collision event by changing the Ball's direction.
     */
    @Override
    public void onCollision() {
        dir = new Direction(rand.nextInt(3) - 1, -dir.getVerticalVelocity());
    }

    /**
     * Gets the current position of the Ball.
     *
     * @return the current position
     */
    @Override
    public Point getPosition() {
        return this.position;
    }

    /**
     * Gets the current size of the Ball.
     *
     * @return the current size
     */
    @Override
    public Dimension getSize() {
        return size;
    }

    /**
     * Gets the current health of the Ball.
     *
     * @return the current health
     */
    @Override
    public int getHealth() {
        return super.getHealth();
    }

    /**
     * Sets the health of the Ball.
     *
     * @param health the new health value
     */
    @Override
    public void setHealth(final int health) {
        super.setHealth(health);
    }
}
