package org.rkov.krestiki.presentation.view;

import java.util.Scanner;

import org.rkov.krestiki.domain.aggregates.GameField;
import org.rkov.krestiki.domain.aggregates.GameProcess;
import org.rkov.krestiki.domain.entities.Player;
import org.rkov.krestiki.domain.exceptions.GameFieldException;
import org.rkov.krestiki.domain.exceptions.GameProcessException;
import org.rkov.krestiki.domain.values.enums.Figure;
import org.rkov.krestiki.domain.values.objects.Coordinate;
import org.rkov.krestiki.presentation.services.Choice;

/**
 *
 * @author yakov
 */
public class GameView {
    private GameProcess gameProcess;
    private Player player1;
    private Player player2;

    private Scanner scanner;

    public GameView(Player player1, Player player2) {
        this.gameProcess = new GameProcess(player1, player2);
        this.player1 = player1;
        this.player2 = player2;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printGame() {
        while (true) {
            try {
                System.out.println(player1.name + " " + gameProcess.getWins(player1));
                System.out.println(player2.name + " " + gameProcess.getWins(player2));

                this.playGame(new Player[]{player1, player2}, gameProcess);
            } catch (GameProcessException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            Choice<Integer> choice = new Choice<>("Выйти?")
                    .addChoose(1, 1, "Да")
                    .addChoose(2, 2, "Нет");

            if (choice.getChoose(scanner) == 1) {
                return;
            }
        }
    }

    private void playGame(Player[] players, GameProcess gameProcess) {
        GameField field = new GameField();


        while (!field.isGameOwer()) {
            for (var player : players) {
                System.out.println("Ход игрока: " + player.name + " (" + player.figure.label() + ")");

                try {
                    field.setField(writeCoordinates(), player.figure);                    
                    
                    printField(field);

                    if (field.isGameOwer()) {
                        break;
                    }
                } catch (GameFieldException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
        }

        if (field.hasWinner()) {    
            for (var player : players) {
                if (player.figure == field.getWinner()) {
                    System.out.println("Победил игрок: " + player.name + " (" + player.figure.label() + ")");

                    try {
                        gameProcess.addWin(player);
                    } catch (GameProcessException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                }
            }
        } else {
            System.out.println("Ничья!");
        }
    }

    private Coordinate writeCoordinates() {
        while (true) {
            try {
                String coordinateStr = readText("Введите координаты (x,y) через запятую: ");

                String[] parts = coordinateStr.split(",");
                return new Coordinate(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Неверный формат координат: " + e.getMessage());
            }
        }
    }
    
    public void printField(GameField field) {
        for (int i = 0; i < GameField.SIZE; i++) {
            for (int j = 0; j < GameField.SIZE; j++) {

                Figure figure = field.getField(new Coordinate(i, j));

                String label = figure == null ? " " : figure.shortLabel();

                System.out.print(label + " ");
            }
            
            System.out.println();
        }
    }

    private String readText(String text) {
        String result = new String();

        while (result.isBlank()) {
            System.out.println(text);

            result = scanner.next();
        }

        return result;
    }
}
