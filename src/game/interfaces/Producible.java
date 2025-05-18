package game.interfaces;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface representing actors that can produce in the game.
 * Classes implementing this interface may check if they are currently able to produce.
 */
public interface Producible {
    /**
     * Checks if the producible is able to produce.
     *
     * @param map A map where the producible is.
     * @return A default value of false.
     */
    default boolean canProduce(GameMap map) { return false; }

    /**
     * Produces the offspring of the producible.
     *
     * @param map A map where the producible is.
     * @return A string describing the produce action.
     */
    String produce(GameMap map);
}
