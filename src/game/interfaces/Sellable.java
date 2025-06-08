package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

public interface Sellable {
    String sell(Actor actor, Actor merchant);
}
