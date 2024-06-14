package it.unibo.controller;

import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedHashSet;
import java.util.Set;

import it.unibo.api.BrickWall;
import it.unibo.model.Brick;

public class BrickWallImpl implements BrickWall {

    private Set<Brick> wall;
    private int width;
    private int height;
    private int sideOffset;

    public BrickWallImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void generateLayout() {
        this.resetLayout();

        int gcd = gcd(this.width, this.height);

        // Calcola la dimensione base del mattoncino
        int brickWidth = gcd;
        int brickHeight = (int) (brickWidth / Brick.ASPECT_RATIO);

        // Verifica che il mattoncino base riempia l'area
        if (this.height % brickHeight != 0) {
            brickHeight = gcd;
            brickWidth = (int) (brickHeight * Brick.ASPECT_RATIO);
        }

        int numBricksRow = this.width / brickWidth;
        int numBricksColumn = this.height / brickHeight;

        this.sideOffset = (int) (this.width - (brickWidth * numBricksRow)) / 2;

        for (int i = 0; i < numBricksColumn; i++) {
            wall.add(
                    BrickFactory.createImmortalBrick(
                            new Point(0, i * brickHeight),
                            new Dimension(brickWidth, brickHeight)));
            for (int j = 0; j < numBricksRow; j++) {
                wall.add(
                        BrickFactory.createRandomBrick(
                                new Point((j * brickWidth) + this.sideOffset, i * brickHeight),
                                new Dimension(brickWidth, brickHeight)));
            }
            wall.add(
                    BrickFactory.createImmortalBrick(
                            new Point(this.width - this.sideOffset, i * brickHeight),
                            new Dimension(brickWidth, brickHeight)));
        }

        this.toString();
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    @Override
    public void resetLayout() {
        this.wall = new LinkedHashSet<Brick>();
    }

    @Override
    public void shiftLayout() {
        throw new UnsupportedOperationException("Unimplemented method 'shiftLayout'");
    }

    @Override
    public void removeDeathBricks() {
        throw new UnsupportedOperationException("Unimplemented method 'removeDeathBricks'");
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public Set<Brick> getWall() {
        return wall;
    }

    @Override
    public String toString() {
        return wall.toString();
    }
}
