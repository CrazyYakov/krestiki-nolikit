package org.rkov.krestiki.domain.values.objects;

/**
 *
 * @author yakov
 */
public class Coordinate {
    private final static int MAX_BOUND_INDEX = 2;

    public final byte x;
    public final byte y;

    public Coordinate(int x, int y) {
        if (x < 0 || x > MAX_BOUND_INDEX || y < 0 || y > MAX_BOUND_INDEX) {
            throw new IndexOutOfBoundsException("Coordinates out of bounds.");
        }

        this.x = (byte) x;
        this.y = (byte) y;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }
}
