package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Countdown;
import game.actions.*;
import game.behaviours.*;
import game.capabilities.Status;
import game.interfaces.Curable;
import game.interfaces.Producible;

import java.util.HashMap;
import java.util.Map;

/**
 * A non-player character (NPC) representing a Spirit Goat.
 * The Spirit Goat has a countdown timer and is non-hostile to enemies.
 * 
 * This class was adapted from the huntsman folder in the provided base code.
 * Original source: edu/monash/fit2099/demo/huntsman/HuntsmanSpider.java
 */
public class SpiritGoat extends Actor implements Curable, Producible {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private Countdown countdown = new Countdown(10, new UnconsciousAction());

    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
        this.addCapability(Status.NON_HOSTILE_TO_ENEMY);
        
        // Registering the behaviours for the Spirit Goat
        behaviours.put(0, new CountdownBehaviour(countdown));
        behaviours.put(1, new WanderBehaviour());
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
        // If the Spirit Goat is not conscious, it cannot perform any actions
        if (!this.isConscious()) {
            return new UnconsciousAction();
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) return action;
        }
        
        // If no action is found, return a DoNothingAction
        return new DoNothingAction();
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
        ActionList actions = new ActionList();
        // Allow attacking if the other actor is hostile to enemies
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }

        // Allow curing if the other actor has a curative item
        for (Item item : otherActor.getItemInventory()) {
            if (item.hasCapability(Status.CURATIVE)) {
                actions.add(new CureAction(item, this));
            }
        }

        // Allow producing if the SpiritGoat can produce
        if (canProduce(otherActor, map)) {
            actions.add(new ProduceAction(this));
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
        countdown.resetCountdown();
        return "The " + item + " glows in " + user + "'s hand. Time rewinds for " + this + ", countdown reset to " + countdown.getCountdown();
    }

    @Override
    public boolean canProduce(Actor otherActor, GameMap map) {
        for (Exit exit : map.locationOf(this).getExits()) {
            Location surrounding = exit.getDestination();

            // Checks if the surrounding ground or actors are BLESSED_BY_GRACE
            if (surrounding.getGround().hasCapability(Status.BLESSED_BY_GRACE)
            || otherActor.hasCapability(Status.BLESSED_BY_GRACE)) {
                return true;
            }

            // Checks if the surrounding actors have an item BLESSED_BY_GRACE
            for (Item item: otherActor.getItemInventory()) {
                if (item.hasCapability(Status.BLESSED_BY_GRACE)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String produce(Actor actor, GameMap map) {
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
