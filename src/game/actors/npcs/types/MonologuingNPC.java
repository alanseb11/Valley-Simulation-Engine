package game.actors.npcs.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.capabilities.Status;
import game.monologueconditions.ConditionalMonologue;

/**
 * Abstract class representing a NPC that can monologue.
 * This class extends the NPC class and provides functionality for monologuing NPCs.
 */
public abstract class MonologuingNPC extends NPC {
    protected List<ConditionalMonologue> monologuePool = new ArrayList<>();

    /**
     * Constructs a Monologuing NPC with a specified name, display character, and monologue.
     *
     * @param name the name of the NPC
     * @param displayChar the display character for the NPC
     * @param hitPoints the initial hit points of the NPC
     */
    public MonologuingNPC(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Returns a list of actions that other actors can perform on this NPC.
     *
     * @param otherActor the actor performing the action
     * @param direction the direction of the action
     * @param map the game map
     * @return a list of allowable actions
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
     * Method to be called when the monologue is triggered.
     *
     * @param listener The Actor that is listening to the monologue
     * @param map      The map containing the Actor
     * @return A string representing the monologue
     */
    public String playMonologue(Actor listener, GameMap map) {
        List<String> filteredMonologues = new ArrayList<>();

        // Filter monologues based on the listener's attributes
        for (ConditionalMonologue monologue : monologuePool) {
            if (monologue.isApplicableTo(listener, map)) {
                filteredMonologues.add(monologue.getMonologue());
            }
        }

        return selectRandomMonologue(filteredMonologues);
    }

    /**
     * Selects a random monologue from the provided list.
     *
     * @param monologuePool The list of monologues to choose from
     * @return A randomly selected monologue
     */
    private String selectRandomMonologue(List<String> monologuePool) {
        return monologuePool.get(new Random().nextInt(monologuePool.size()));
    }

}
