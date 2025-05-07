package game.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.monologueconditions.ConditionalMonologue;

/**
 * Interface for NPC classes that can provide monologues.
 */
public interface Monologuer {
    List<ConditionalMonologue> getMonologuePool();

    /**
     * Method to be called when the monologue is triggered.
     *
     * @param listener The Actor that is listening to the monologue
     * @param map      The map containing the Actor
     * @return A string representing the monologue
     */
    default String playMonologue(Actor listener, GameMap map) {
        List<String> filteredMonologues = new ArrayList<>();

        // Filter monologues based on the listener's attributes
        for (ConditionalMonologue condition : getMonologuePool()) {
            if (condition.isApplicableTo(listener, map)) {
                filteredMonologues.add(condition.getMonologue());
            }
        }

        return selectRandomMonologue(filteredMonologues);
    }

    /**
     * Selects a random monologue from the provided list.
     *
     * @param monologuePool The list of monologues to choose from
     * @return A randomly selected monologue
     */
    default String selectRandomMonologue(List<String> monologuePool) {
        return monologuePool.get(new Random().nextInt(monologuePool.size()));
    }
    
}
