package org.rkov.krestiki.domain.aggregates;

import org.rkov.krestiki.domain.exceptions.GameFieldException;
import org.rkov.krestiki.domain.values.enums.Figure;
import org.rkov.krestiki.domain.values.objects.Coordinate;

/**
 *
 * @author yakov
 */
public final class GameField {
    public final static int MAX_BOUND_INDEX = 2;
    public final static int SIZE = MAX_BOUND_INDEX + 1;

    private Figure[][] fields;
    
    private Figure winner;

    public GameField() {
        resetGame();
    }

    public Figure[][] getFields() {
        return fields;
    }

    public Figure getField(Coordinate coordinate) {
        return this.fields[coordinate.getX()][coordinate.getY()];
    }

    public boolean isGameOwer() {
        return this.hasWinner();
    }

    public Figure getWinner() {
        return winner;
    }

    public boolean hasWinner() {
        return this.winner != null;
    }

    public void setField(Coordinate coordinate, Figure figure) throws GameFieldException {
        if (isGameOwer()) {
            throw new GameFieldException("Game is ower.");
        }

        if (this.fields[coordinate.getX()][coordinate.getY()] != null) {
            throw new GameFieldException("Field is already occupied.");
        }

        this.fields[coordinate.getX()][coordinate.getY()] = figure;

        checWinner(coordinate.getX(), coordinate.getY(), figure);
    }

    public void resetGame() {
        this.fields = new Figure[MAX_BOUND_INDEX + 1][MAX_BOUND_INDEX + 1];

        this.winner = null;
    }

    private void checWinner(int x, int y, Figure checkFigureWinner) {        
        int fieldCount = 0;

        for (int i = 0; i < SIZE; i++) {
            if (fields[x][i] == checkFigureWinner) {
                fieldCount += i;
            }
        }

        if (fieldCount == SIZE) {
            this.winner = checkFigureWinner;

            return;
        }

        fieldCount = 0;

        for (int i = 0; i < SIZE; i++) {
            if (fields[i][y] == checkFigureWinner) {
                fieldCount += i;
            }
        }

        if (fieldCount == SIZE) {
            this.winner = checkFigureWinner;

            return;
        }

        fieldCount = 0;

        for (int i = 0; i < SIZE; i++) {
            if (fields[i][i] == checkFigureWinner) {
                fieldCount += i;
            }
        }

        if (fieldCount == SIZE) {
            this.winner = checkFigureWinner;

            return;
        }

        fieldCount = 0;

        for (int i = 0; i < SIZE; i++) {
            if (fields[i][MAX_BOUND_INDEX - i] == checkFigureWinner) {
                fieldCount += i;
            }
        }

        if (fieldCount == SIZE) {
            this.winner = checkFigureWinner;
        }
    }
}
