package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CureAction;
import game.capabilities.Status;
import game.interfaces.Curable;

/**
 * A class representing a blight covering the ground of the valley.
 * @author Adrian Kristanto
 */
public class Blight extends Ground implements Curable {
    
    /**
     * Constructor.
     */
    public Blight() {
        super('x', "Blight");
        this.addCapability(Status.CURSED);
    }

    /**
     * Returns a list of allowable actions for the actor on this ground.
     * 
     * @param actor     The actor performing the action
     * @param location  The location of the actor
     * @param direction The direction of the ground from the actor
     * @return A list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        
        // Check if the actor is on the same location as the blight
        if (location.getActor() == actor) {
            // Allow curing if the other actor has a curative item
            for (Item item : actor.getItemInventory()) {
                if (item.hasCapability(Status.CURATIVE)) {
                    actions.add(new CureAction(item, this));
                }
            }
        }
        
        return actions;
    }
    
    /**
     * This method is called when the Blight is cured by an item.
     * It transforms the blight into fertile soil and drains stamina from the user.
     *
     * @param item The item used to cure the blight
     * @param user The actor using the item
     * @param map  The map containing the actor
     * @return A message indicating the result of the curing action
     */
    @Override
    public String beCuredBy(Item item, Actor user, GameMap map) {
        // Check if the actor has sufficient stamina
        if (user.getAttribute(BaseActorAttributes.STAMINA) < getStaminaCost(user)) {
            return user + " tries to use the " + item + " but not enough strength flows to purify the " + this;
        }

        // Transform the ground into Soil
        map.locationOf(user).setGround(new Soil());

        // Drain stamina from the user
        user.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, getStaminaCost(user));

        // Return a success message
        return user + " channels restorative energy through the " + item + ", purifying the " + this + " back to fertile soil";
    }

    @Override
    public int getStaminaCost(Actor actor) {
        if (actor.hasCapability(Status.BUFFED)) {
            return Math.round(50 * 0.75f); // 25% less stamina cost if buffed
        }
        return 50; // Default stamina cost for curing the blight
    }

}
