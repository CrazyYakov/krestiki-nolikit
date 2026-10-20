package org.rkov.krestiki.presentation.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author yakov
 */
public class Choice<T> {
    private final String switchTitle;

    private final Map<Integer, String> mapLabel;
    private final Map<Integer, T> mapChoice;

    public Choice(String switchTitle) {
        this.switchTitle = switchTitle;

        mapLabel = new HashMap<>();
        mapChoice = new HashMap<>();
    }

    public Choice addChoose(int number, T choice, String label) {
        mapLabel.put(number, label);
        mapChoice.put(number, choice);

        return this;
    }

    public T getChoose(Scanner in) {
        while (true) {
            try {
                printChoice();

                String intStr = in.next();

                var numberChoice = Integer.valueOf(intStr);

                return mapChoice.get(numberChoice);
            } catch (NullPointerException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void printChoice() {
        System.out.println(switchTitle);

        for (var elem : mapLabel.entrySet()) {
            System.out.println(elem.getKey() + " - " + elem.getValue());
        }
    }
}
