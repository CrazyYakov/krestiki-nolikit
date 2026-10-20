package org.rkov.krestiki.domain.entities;

import org.rkov.krestiki.domain.values.enums.Figure;

/**
 *
 * @author yakov
 */
public class Player {
    public final String name;
    public final Figure figure;

    public Player(String name, Figure figure) {
        this.name = name;
        this.figure = figure;
    }
}
