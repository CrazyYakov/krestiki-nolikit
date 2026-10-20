package org.rkov.krestiki.domain.aggregates;

import java.util.HashMap;
import java.util.Map;

import org.rkov.krestiki.domain.entities.Player;
import org.rkov.krestiki.domain.exceptions.GameProcessException;

/**
 *
 * @author yakov
 */
public final class GameProcess {
    private final Map<Player, Integer> countWins;

    public GameProcess(Player player1, Player player2) {
        this.countWins = new HashMap<>();
        this.countWins.put(player1, 0);
        this.countWins.put(player2, 0);
    }

    public void addWin(Player player) throws GameProcessException {
        checkPlayerExists(player);

        this.countWins.put(player, this.countWins.get(player) + 1);
    }

    public Integer getWins(Player player) throws GameProcessException {
        checkPlayerExists(player);

        return this.countWins.get(player);
    }

    private void checkPlayerExists(Player player) throws GameProcessException {
        if (! this.countWins.containsKey(player)) {
            throw new GameProcessException("Player not found in the game process.");
        }
    }
}
