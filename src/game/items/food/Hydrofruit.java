package game.items.food;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.actions.SellAction;
import game.capabilities.Status;
import game.interfaces.Eatable;
import game.interfaces.Sellable;
import game.items.plants.HydrofruitSeed;

/**
 * A class representing the Hydrofruit item.
 * The Hydrofruit is both edible and sellable.
 */
public class Hydrofruit extends Item implements Eatable, Sellable {
    /***
     * Constructor for the Hydrofruit.
     */
    public Hydrofruit() {
        super("Hydrofruit", 'H', true);
    }

    /**
     * A list of allowable actions that the owner can perform on the fruit.
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
        // If a surrounding actor is a merchant, then offer to sell the Hydrofruit
        for (Exit exit: map.locationOf(owner).getExits()) {
            Location surroundings = exit.getDestination();
            if (surroundings.containsAnActor() && surroundings.getActor().hasCapability(Status.MERCHANT)) {
                actions.add(new SellAction(this, surroundings.getActor()));
            }
        }
        return actions;
    }

    /**
     * Defines what happens when an actor consumes the Hydrofruit.
     * Consuming the fruit restores 20 stamina and removes it from the inventory.
     *
     * @param actor The actor consuming the fruit.
     * @param map   The game map in which the action is occurring.
     * @return A message indicating the result of the action.
     */
    @Override
    public String eatenBy(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,20);
        actor.removeItemFromInventory(this);
        // A Hydrofruit seed can be extracted from the remains of an eaten Hydrofruit
        actor.addItemToInventory(new HydrofruitSeed());
        return actor + " consumes the " + this + " and obtains a Hydrofruit seed and restores 20 stamina!";
    }

    /**
     A method that's called when the Hydrofruit is sold.
     * The fruit costs 100 runes and can only be bought by merchants with enough balance.
     *
     * @param actor The actor selling the item
     * @param merchant The merchant buying the item
     * @return A string representing the transaction
     */
    @Override
    public String sell(Actor actor, Actor merchant) {
        // The Hydrofruit costs 100 runes
        if (merchant.getBalance() >= 100) {
            actor.addBalance(100);
            merchant.deductBalance(100);
            // Removes the sold item from player's inventory
            actor.removeItemFromInventory(this);
            // Selling the Hydrofruit heals the actor by 5
            actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 5);
            return actor + " has sold the " + this + " to the " + merchant + " for 100 runes.";
        } else {
            return merchant + " does not have enough runes to buy the " + this;
        }
    }
}
