package org.rkov.krestiki.presentation.view;

import java.io.InputStream;
import java.util.Scanner;

import org.rkov.krestiki.domain.entities.Player;
import org.rkov.krestiki.domain.values.enums.Figure;
import org.rkov.krestiki.presentation.services.Choice;

/**
 *
 * @author yakov
 */
public class MenuView {
    private Scanner scanner;

    public MenuView(InputStream stream) {
        this.scanner = new Scanner(stream);
    }

    public void showMenu() {
        boolean run = true;

        while (run) {
            Choice<Integer> choice = new Choice("Крестики нолики:");
            choice.addChoose(1, 1, "Играть");
            choice.addChoose(2, 2, "Выйти");

            switch (choice.getChoose(scanner)) {
                case 1 -> {
                    Player player1 = new Player(readText("Введите имя для крестиков: "), Figure.TIK);

                    Player player2 = new Player(readText("Введите имя для ноликов: "), Figure.TAK);

                    GameView gameView = new GameView(player1, player2);
                    gameView.setScanner(scanner);

                    gameView.printGame();
                }

                case 2 -> {
                    System.out.println("Выход из игры.");

                    run = false;
                }
                default ->
                    System.out.println("Было выбрано не то число");
            }
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
