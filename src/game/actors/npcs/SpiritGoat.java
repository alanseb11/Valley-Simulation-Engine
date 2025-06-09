package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.time.Countdown;
import game.actions.*;
import game.actors.npcs.types.AttackableNPC;
import game.behaviours.*;
import game.capabilities.Status;
import game.interfaces.Curable;
import game.interfaces.Producible;
import game.monologueconditions.SurroundingCapabilityCondition;

/**
 * A non-player character (NPC) representing a Spirit Goat.
 * The Spirit Goat has a countdown timer and is non-hostile to enemies.
 * 
 * This class was adapted from the huntsman folder in the provided base code.
 * Original source: edu/monash/fit2099/demo/huntsman/HuntsmanSpider.java
 */
public class SpiritGoat extends AttackableNPC implements Curable, Producible {
    private Countdown rotCountdown = new Countdown(10);

    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
        this.addCapability(Status.NON_HOSTILE_TO_ENEMY);
        
        // Registering the behaviours for the Spirit Goat
        behaviours.put(0, new CountdownBehaviour(rotCountdown, new UnconsciousAction()));
    }

    /**
     * Determines the action the Spirit Goat will perform on its turn.
     *
     * @param actions    A collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    The I/O object to which messages may be written
     * @return The action to be performed, or {@link DoNothingAction} if no valid action is found.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Allow producing if the SpiritGoat can produce
        if (canProduce(map)) {
            return new ProduceAction(this);
        } else return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Determines the actions that other actors can perform on the Spirit Goat.
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
     * Cures the Spirit Goat using the specified item.
     *
     * @param item The item used for curing
     * @param user The actor performing the curing action
     * @param map  The game map where the curing action takes place
     * @return A string describing the result of the curing action
     */
    @Override
    public String beCuredBy(Item item, Actor user, GameMap map) {
        rotCountdown.resetCountdown();
        return "The " + item + " glows in " + user + "'s hand. Time rewinds for " + this + ", countdown reset to " + rotCountdown.getCountdown();
    }

    /**
     * Checks if the {@link SpiritGoat} can produce.
     *
     * @param map The map containing the actor.
     * @return A true or false value of if the actor can produce.
     */
    @Override
    public boolean canProduce(GameMap map) {
        for (Exit exit : map.locationOf(this).getExits()) {
            Location surrounding = exit.getDestination();
            // Checks if the surrounding items are BLESSED_BY_GRACE
            if (SurroundingCapabilityCondition.hasCapabilityInLocation(surrounding, Status.BLESSED_BY_GRACE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Produces a {@link SpiritGoat} at a valid surrounding location of the original {@link SpiritGoat}.
     *
     * @param map The map the actor is on.
     * @return A string describing the result of the action.
     */
    @Override
    public String produce(GameMap map) {
        for (Exit exit : map.locationOf(this).getExits()) {
            Location surrounding = exit.getDestination();
            // Checks for a valid spawn location in the SpiritGoat's surroundings
            if (surrounding.canActorEnter(this)) {
                surrounding.addActor(new SpiritGoat());
                return this + " has produced an offspring!";
            }
        }
        return this + " is stranded and has nowhere to produce an offspring!";
    }
}
