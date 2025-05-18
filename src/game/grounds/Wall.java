package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class representing a wall that cannot be entered by any actor
 * @author Riordan D. Alfredo
 */
public class Wall extends Ground {
    /**
     * Constructor.
     */
    public Wall() {
        super('#', "Wall");
    }

    /**
     * Actor can't enter a wall.
     *
     * @param actor the Actor to check
     * @return False for unable to enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
