package game.actors.npcs;

import java.util.*;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actions.UnconsciousAction;
import game.behaviours.WanderBehaviour;
import game.capabilities.Status;
import game.interfaces.Monologuer;

/**
 * Class representing the Sellen NPC.
 */
public class Sellen extends Actor implements Monologuer {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor.
     */
    public Sellen() {
        super("Sellen", 's', 150);
        behaviours.put(1, new WanderBehaviour());
    }

    /**
     * Determines the action Sellen will perform on their turn.
     *
     * @param actions    A collection of possible actions for the Sellen
     * @param lastAction The action the Sellen took last turn. Can be used for multi-turn actions.
     * @param map        The map containing the Sellen
     * @param display    The I/O object to which messages may be written
     * @return The action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
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
     * Returns the list of actions that Sellen can perform.
     *
     * @param otherActor The Actor that is interacting with Sellen
     * @param direction  The direction in which the action is being performed
     * @param map        The map containing the Actor
     * @return An ActionList containing the actions Sellen can perform
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new ListenAction(this));
        }

        return actions;
    }

    /**
     * Returns a monologue for Sellen.
     *
     * @return A string representing Sellen's monologue
     */
    @Override
    public String getMonologue(Actor listener, GameMap map) {
        // Sellen's monologue pool
        List<String> monologuePool = new ArrayList<>();
        monologuePool.add("The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever.");
        monologuePool.add("You sense it too, don't you? The Glintstone hums, even now.");
        
        // Randomly select a monologue from the pool
        return selectRandomMonologue(monologuePool);
    }

}
