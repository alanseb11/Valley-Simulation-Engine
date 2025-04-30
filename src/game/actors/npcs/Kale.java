package game.actors.npcs;

import java.util.*;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actions.UnconsciousAction;
import game.behaviours.WanderBehaviour;
import game.capabilities.Status;
import game.interfaces.Monologuer;

/**
 * Class representing the Kale NPC.
 */
public class Kale extends Actor implements Monologuer {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor.
     */
    public Kale() {
        super("Kale", 'k', 200);
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
     * Returns the list of actions that other actors can perform on Sellen.
     *
     * @param otherActor The Actor that is interacting with Kale
     * @param direction  The direction in which the action is being performed
     * @param map        The map containing the Actor
     * @return An ActionList containing the actions that can be performed on Kale
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
     * Returns the monologue of Kale.
     *
     * @param listener The Actor that is listening to the monologue
     * @param map      The map containing the Actor
     * @return The monologue of Kale
     */
    @Override
    public String getMonologue(Actor listener, GameMap map) {
        // Kale's default monologue pool
        List<String> monologuePool = new ArrayList<>();
        monologuePool.add("A merchant's life is a lonely one. But the roads... they whisper secrets to those who listen.");

        // Kale's conditional monologues
        if (listener.getBalance() < 500) {
            monologuePool.add("Ah, hard times, I see. Keep your head low and your blade sharp.");
        }

        if (listener.getItemInventory().isEmpty()) {
            monologuePool.add("Not a scrap to your name? Even a farmer should carry a trinket or two.");
        }

        for (Exit exit: map.locationOf(listener).getExits()) {
            if (exit.getDestination().getGround().hasCapability(Status.CURSED)) {
                monologuePool.add("Rest by the flame when you can, friend. These lands will wear you thin.");
                break;
            }
        }

        // Select a random monologue from the pool
        return selectRandomMonologue(monologuePool);
    }

}
