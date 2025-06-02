package game.actors.npcs.types;

import java.util.HashMap;
import java.util.Map;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.UnconsciousAction;
import game.behaviours.BehaviourType;
import game.behaviours.PrioritisedBehaviourType;
import game.behaviours.WanderBehaviour;

/**
 * Abstract class representing a NPC in the game.
 * This class extends the Actor class and provides basic functionality for NPCs.
 */
public abstract class NPC extends Actor {
    /**
     * A map of behaviours that the NPC can perform.
     * The key is an integer representing the priority of the behaviour.
     * The value is the Behaviour itself.
     */
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * The strategy used to select behaviors.
     */
    protected BehaviourType behaviourType;

    /**
     * Constructor for NPC.
     *
     * @param name         The name of the NPC.
     * @param displayChar  The character to represent the NPC on the map.
     * @param hitPoints    The initial hit points of the NPC.
     */
    public NPC(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        
        // Default to prioritized behavior strategy
        this.behaviourType = new PrioritisedBehaviourType();
        
        // Registering the behaviours for the NPC
        behaviours.put(99, new WanderBehaviour());
    }

    /**
     * Sets the behavior selection strategy for this NPC.
     *
     * @param strategy The strategy to use for selecting behaviors
     */
    public void setBehaviour(BehaviourType strategy) {
        this.behaviourType = strategy;
    }

    /**
     * Determines the action the NPC will perform on its turn.
     *
     * @param actions    A collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        The map containing the Actor
     * @param display    The I/O object to which messages may be written
     * @return The action to be performed, or {@link DoNothingAction} if no valid action is found.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // If the NPC is not conscious, it cannot perform any actions
        if (!this.isConscious()) {
            return new UnconsciousAction();
        }

        // Use the current strategy to select an action
        Action action = behaviourType.selectAction(behaviours, this, map);
        if (action != null) {
            return action;
        }

        return new DoNothingAction();
    }
}
