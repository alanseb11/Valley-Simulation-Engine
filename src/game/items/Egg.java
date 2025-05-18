package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.capabilities.Status;
import game.interfaces.Eatable;

/**
 * Represents an abstract class {@link Egg} which is produced.
 * It is an {@link Item} which is able to hatch into an {@link Actor}.
 * It may also be consumed when in the player's inventory.
 */
public abstract class Egg extends Item implements Eatable {
    /**
     * A value canHatch which has a default value of true.
     */
    protected boolean canHatch = true;

    /***
     * Constructor.
     */
    public Egg() {
        super("Egg", '0', true);
    }

    /**
     * A list of allowable actions that the owner can perform on the egg.
     *
     * @param owner the actor that owns the item
     * @param map the map where the actor is performing the action on
     * @return An action list of the allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        if (owner.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new EatAction(this));
        }
        return actions;
    }

    /**
     * Hatches the egg.
     *
     * @param location The location where the egg hatches.
     */
    public void hatch(Location location) {
        location.removeItem(this);
    }
    
}
