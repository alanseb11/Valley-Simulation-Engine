package game.interfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PlantAction;
import game.capabilities.Status;

/**
 * An interface representing items that can be planted in the game.
 * 
 * Classes implementing this interface must define the behaviour for planting
 * the item and the resulting form after planting.
 */
public interface Plantable {
    /**
     * Checks if the item can be planted by the specified actor.
     * @param actor
     * @return true if the actor has enough stamina to plant the seed, false otherwise
     */
    default boolean isPlantableBy(Actor actor) {
        return actor.getAttribute(BaseActorAttributes.STAMINA) >= getStaminaCost();
    }

    /**
     * Returns a list of actions that can be performed with the seed.
     *
     * @param owner the actor who owns the seed
     * @param map   the game map where the item is located
     * @param seed  the seed to be planted
     * @return a list of actions that can be performed with the item
     */
    default ActionList getPlantAction(Actor owner, GameMap map, Plantable seed) {
        ActionList actions = new ActionList();
        Location location = map.locationOf(owner);
        Ground ground = location.getGround();

        if (ground.hasCapability(Status.FERTILE) && !ground.hasCapability(Status.PLANTED)) {
            actions.add(new PlantAction(seed));
        }
        return actions;
    }

    /**
     * Returns the stamina cost required to plant the item.
     *
     * @return the stamina cost
     */
    int getStaminaCost();

    /**
     * Returns the form of the item after it has been planted.
     *
     * @return the planted form of the item
     */
    Ground getPlantedForm();

    /**
     * Plants the item in the game world.
     * 
     * @param actor the actor performing the planting action
     * @param map   the game map where the item is being planted
     */
    void plant(Actor actor, GameMap map);

    /**
     * Plants the item at the specified location.
     *
     * @param tile the location where the item is planted
     */
    void bloom(Location tile);
}
