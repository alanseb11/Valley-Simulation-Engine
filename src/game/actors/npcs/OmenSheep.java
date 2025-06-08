package game.actors.npcs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Countdown;
import game.actions.*;
import game.actors.npcs.types.AttackableNPC;
import game.behaviours.*;
import game.capabilities.Status;
import game.grounds.Inheritree;
import game.interfaces.Curable;
import game.interfaces.Producible;
import game.items.food.eggs.OmenSheepEgg;

/**
 * A non-player character (NPC) representing an Omen Sheep.
 * The Omen Sheep has a countdown timer and is non-hostile to enemies.
 * 
 * This class was adapted from the huntsman folder in the provided base code.
 * Original source: edu/monash/fit2099/demo/huntsman/HuntsmanSpider.java
 */
public class OmenSheep extends AttackableNPC implements Curable, Producible {
    /**
     * Constructor.
     * 
     * Initialises the Omen Sheep with a name, display character, and hit points.
     */
    public OmenSheep() {
        super("Omen Sheep", 'm', 75);
        this.addCapability(Status.NON_HOSTILE_TO_ENEMY);

        // Registering the behaviours for the Omen Sheep
        behaviours.put(0, new CountdownBehaviour(new Countdown(15), new UnconsciousAction()));
        behaviours.put(1, new CountdownBehaviour(new Countdown(7), new ProduceAction(this)));
    }

    /**
     * Determines the actions that other actors can perform on the Spirit Goat.
     * 
     * This method was adapted from the huntsman folder in the provided base code.
     * Original source: edu/monash/fit2099/demo/huntsman/HuntsmanSpider.java
     *
     * @param otherActor The Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        The current GameMap
     * @return A list of allowable actions, including {@link AttackAction} if applicable.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        // Allow curing if the other actor has a curative item
        for (Item item : otherActor.getItemInventory()) {
            if (item.hasCapability(Status.CURATIVE)) {
                actions.add(new CureAction(item, this));
            }
        }

        return actions;
    }
    
    /**
     * This method is called when the Omen Sheep is cured by an item.
     * This method defines the behaviour of the Omen Sheep when it is cured, allowing it to interact with the game world and create Inheritrees.
     * 
     * @param item The item used to cure the Omen Sheep
     * @param user The Actor who used the item
     * @param map  The current GameMap
     * @return A message indicating the result of the curing action
     */
    @Override
    public String beCuredBy(Item item, Actor user, GameMap map) {
        for (Exit exit : map.locationOf(this).getExits()) {
            Location tile = exit.getDestination();
			tile.setGround(new Inheritree());
            tile.getGround().addCapability(Status.PLANTED);
        }
        return user + " invokes the power of the " + item + " and Inheritrees emerge in a ring around " + this;
    }

    /**
     * Produces an {@link OmenSheepEgg} at the location of the {@link OmenSheep}.
     *
     * @param map The map the actor is on.
     * @return A string describing the result of the action.
     */
    @Override
    public String produce(GameMap map) {
        // Produces an egg at the GameMap position of the OmenSheep
        map.locationOf(this).addItem(new OmenSheepEgg());
        return this + " has produced an egg!";
    }
}
