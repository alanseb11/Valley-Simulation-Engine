package game.items.food;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Eatable;

public class Lumenfruit extends Item implements Eatable {
    /***
     * Constructor.
     */
    public Lumenfruit() {
        super("Lumenfruit", 'I', true);
    }

    @Override
    public String eatenBy(Actor actor, GameMap map) {
        return "";
    }
}
