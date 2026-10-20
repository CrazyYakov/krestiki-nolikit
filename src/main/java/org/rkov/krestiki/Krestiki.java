package org.rkov.krestiki;

import org.rkov.krestiki.presentation.view.MenuView;

/**
 *
 * @author yakov
 */
public class Krestiki {

    public static void main(String[] args) {
        MenuView menu = new MenuView(System.in);

        menu.showMenu();
    }
}
