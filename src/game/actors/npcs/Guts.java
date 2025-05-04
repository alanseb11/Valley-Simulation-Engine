package game.actors.npcs;

import java.util.*;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ConditionalMonologue;
import game.actions.ListenAction;
import game.actions.UnconsciousAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Status;
import game.capabilities.Threshold;
import game.interfaces.Monologuer;
import game.monologueconditions.DefaultCondition;
import game.monologueconditions.HealthCondition;
import game.weapons.BareFist;

/**
 * Class representing the Guts NPC.
 */
public class Guts extends Actor implements Monologuer {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private List<ConditionalMonologue> monologuePool = new ArrayList<>();

    /**
     * Constructor.
     */
    public Guts() {
        super("Guts", 'g', 500);
        this.setIntrinsicWeapon(new BareFist());

        // Register behaviours
        behaviours.put(0, new AttackBehaviour(target -> target.getAttribute(BaseActorAttributes.HEALTH) > 50));
        behaviours.put(1, new WanderBehaviour());

        // Initialise monologue pool
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "RAAAAGH!"));
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "I'LL CRUSH YOU ALL!"));
        monologuePool.add(new ConditionalMonologue(new HealthCondition(50, Threshold.BELOW), "WEAK! TOO WEAK TO FIGHT ME!"));
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
     * Returns the list of actions that other actors can perform on Guts.
     *
     * @param otherActor The Actor that is interacting with Guts
     * @param direction  The direction in which the action is being performed
     * @param map        The map containing the Actor
     * @return An ActionList containing the actions that can be performed on Guts
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
     * Returns the monologue pool of Guts.
     *
     * @return A list of ConditionalMonologue objects representing Guts' monologues
     */
    @Override
    public List<ConditionalMonologue> getMonologuePool() {
        return monologuePool;
    }

}
