package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Producible {
    default boolean canProduce(GameMap map) { return false; }

    String produce(Actor actor, GameMap map);
}
