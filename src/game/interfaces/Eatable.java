package game.interfaces;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Eatable {
    String eatenBy(Actor actor, GameMap map);
}
