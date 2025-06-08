package game.items.plants;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
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
public abstract class Plantable extends Item {
    protected Ground crop;
    protected int staminaCost;

    /**
     * Constructor.
     *
     * @param name         the name of the item
     * @param displayChar  the character to display for the item
     * @param portable     whether the item can be carried
     * @param crop         the ground that the item will turn into when planted
     */
    public Plantable(String name, char displayChar, boolean portable, Ground crop, int staminaCost) {
        super(name, displayChar, portable);
        this.crop = crop;
        this.staminaCost = staminaCost;
    }

    /**
     * Returns a list of actions that can be performed with the seed.
     *
     * @param owner the actor who owns the seed
     * @param map   the game map where the item is located
     * @return a list of actions that can be performed with the item
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        Ground here = map.locationOf(owner).getGround();

        if (here.hasCapability(Status.FERTILE) && !here.hasCapability(Status.PLANTED)) {
            actions.add(new PlantAction(this));
        }
        return actions;
    }

    /**
     * Checks if the item can be planted by the specified actor.
     * @param actor
     * @return true if the actor has enough stamina to plant the seed, false otherwise
     */
    public boolean isPlantableBy(Actor actor) {
        return actor.getAttribute(BaseActorAttributes.STAMINA) >= getStaminaCost(actor);
    }

    /**
     * Returns the planted form of the item.
     *
     * @return the planted form
     */
    public Ground getPlantedForm() {
        return crop;
    }

    /**
     * Returns the stamina cost required to plant the item.
     *
     * @param actor the actor performing the planting action
     * @return the stamina cost
     */
    public int getStaminaCost(Actor actor) {
        if (actor.hasCapability(Status.BUFFED)) {
            return Math.round(staminaCost * 0.75f); // 25% less stamina cost if buffed
        }
        // Return the original stamina cost if not buffed
        return staminaCost;
    }

    /**
     * Plants the item in the game world.
     * 
     * @param actor the actor performing the planting action
     * @param map   the game map where the item is being planted
     */
    public void plant(Actor actor, GameMap map) {
        // Remove the seed from the actor's inventory
        actor.removeItemFromInventory(this);

        // Sprout where actor is
        Location actorLocation = map.locationOf(actor);
        bloom(actorLocation);

        // Deduct stamina from the actor
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, getStaminaCost(actor));
    }

    /**
     * Plants the item at the specified location.
     *
     * @param here the location where the item is planted
     */
    protected void bloom(Location here) {
        here.setGround(crop);
    }
}
