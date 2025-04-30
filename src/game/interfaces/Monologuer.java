package game.interfaces;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for NPC classes that can provide monologues.
 */
public interface Monologuer {
    String getMonologue(Actor listener, GameMap map);

    default String selectRandomMonologue(List<String> monologuePool) {
        return monologuePool.get(new Random().nextInt(monologuePool.size()));
    }
}
