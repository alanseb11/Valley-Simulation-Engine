package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.UnconsciousAction;
import game.actors.npcs.types.MonologuingNPC;
import game.behaviours.AttackBehaviour;
import game.capabilities.*;
import game.monologueconditions.ConditionalMonologue;
import game.monologueconditions.DefaultCondition;
import game.monologueconditions.HealthCondition;
import game.monologueconditions.SurroundingCapabilityCondition;
import game.weapons.BareFist;

/**
 * Class representing the Guts NPC.
 */
public class Guts extends MonologuingNPC {

    /**
     * Constructor.
     */
    public Guts() {
        super("Guts", 'g', 500);
        this.setIntrinsicWeapon(new BareFist(damageMultiplier));
        this.addCapability(Ability.ATTACK);

        // Register behaviours
        behaviours.put(0, new AttackBehaviour(target -> target.getAttribute(BaseActorAttributes.HEALTH) > 50));

        // Initialise monologue pool
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "RAAAAGH!"));
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "I'LL CRUSH YOU ALL!"));
        monologuePool
                .add(new ConditionalMonologue(new HealthCondition(50, Threshold.BELOW), "WEAK! TOO WEAK TO FIGHT ME!"));
    }

    /**
     * Determines the action that Guts will take during their turn.
     * 
     * @param actions    the list of actions available to Guts
     * @param lastAction the last action taken by Guts
     * @param map        the game map
     * @param display    the display to show messages
     * @return the {@link Action} that Guts will take this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // If the NPC is not conscious, it cannot perform any actions
        if (!this.isConscious()) {
            return new UnconsciousAction();
        }

        if (this.hasCapability(Status.AGGRESSIVE)) {
            // Check if Guts is on or surrounded by Blight
            if (map.locationOf(this).getGround().hasCapability(Status.CURSED)) {
                damageMultiplier = 1.30f;
            } else {
                boolean nearCursed = map.locationOf(this).getExits().stream()
                        .map(Exit::getDestination)
                        .anyMatch(loc -> SurroundingCapabilityCondition.hasCapabilityInLocation(loc,
                                Status.CURSED));
                this.damageMultiplier = nearCursed ? 1.30f : 1.15f;
            }
        } else {
            damageMultiplier = 1.0f;
        }
        this.setIntrinsicWeapon(new BareFist(damageMultiplier));

        // Perform the behaviours
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
    
        // Only allow if the actor is a Farmer and aggressive
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && otherActor.hasCapability(Status.AGGRESSIVE)) {
            actions.add(new AttackAction(this, direction));
        }
    
        return actions;
    }

}
