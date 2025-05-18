package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Eatable;

public class Egg extends Item implements Eatable {
    /***
     * Constructor.
     */
    public Egg() {
        super("Egg", '0', true);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {

    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        return getEatAction(owner);
    }

    @Override
    public String eatenBy(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,10);
        actor.removeItemFromInventory(this);
        return actor + " consumes the Egg and restores 10 stamina!";
    }
}
