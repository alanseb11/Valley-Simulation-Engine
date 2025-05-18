package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Producible {
    boolean canProduce(Actor actor, GameMap map);

    String produce(Actor actor, GameMap map);
}
