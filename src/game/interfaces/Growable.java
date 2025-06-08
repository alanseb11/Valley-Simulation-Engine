package game.interfaces;

import edu.monash.fit2099.engine.positions.GameMap;

public interface Growable {
    void grow();
    boolean canGrow(GameMap map);
}
