package game.actors.npcs.types;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Status;
import game.actions.AttackAction;

/**
 * Abstract class representing an NPC that can be attacked.
 * This class extends the NPC class and provides functionality for attackable NPCs.
 */
public abstract class AttackableNPC extends NPC {
    /**
     * Constructor for AttackableNPC.
     */
    public AttackableNPC(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
    
    /**
     * Returns a list of actions that other actors can perform on this NPC.
     *
     * @param otherActor The actor to check for actions against
     * @param direction  The direction the actor is facing
     * @param map       The map the actor is on
     * @return A list of actions that can be performed
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        
        // Allow attacking if the other actor is hostile to enemies
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }

}
