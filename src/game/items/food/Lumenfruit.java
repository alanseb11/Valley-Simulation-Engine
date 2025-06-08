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

public class Lumenfruit extends Item implements Eatable, Sellable {
    /***
     * Constructor.
     */
    public Lumenfruit() {
        super("Lumenfruit", 'I', true);
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
        // If a surrounding actor is a merchant, then offer to sell the Lumenfruit
        for (Exit exit: map.locationOf(owner).getExits()) {
            Location surroundings = exit.getDestination();
            if (surroundings.containsAnActor() && surroundings.getActor().hasCapability(Status.MERCHANT)) {
                actions.add(new SellAction(this, surroundings.getActor()));
            }
        }
        return actions;
    }

    @Override
    public String eatenBy(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,30);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 10);
        actor.removeItemFromInventory(this);
        return actor + " consumes the " + this + " and restores 30 health and 10 stamina!";
    }

    @Override
    public String sell(Actor actor, Actor merchant) {
        if (merchant.getBalance() >= 50) {
            actor.addBalance(50);
            merchant.deductBalance(50);
            return actor + " has sold the " + this + " to the " + merchant + " for 50 runes.";
        } else {
            return merchant + " does not have enough runes to buy the " + this;
        }
    }
}
