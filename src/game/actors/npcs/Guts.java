package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
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
        this.setIntrinsicWeapon(new BareFist());
        this.addCapability(Ability.ATTACK);

        // Register behaviours
        behaviours.put(0, new AttackBehaviour(target -> target.getAttribute(BaseActorAttributes.HEALTH) > 50));

        // Initialise monologue pool
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "RAAAAGH!"));
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "I'LL CRUSH YOU ALL!"));
        monologuePool
                .add(new ConditionalMonologue(new HealthCondition(50, Threshold.BELOW), "WEAK! TOO WEAK TO FIGHT ME!"));
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Tick the time manager to update the time of day
        timeManager.tick(this, map);
        
        // If the NPC is not conscious, it cannot perform any actions
        if (!this.isConscious()) {
            return new UnconsciousAction();
        }

        // Check if Guts is on Blight or surrounded by peaceful creatures
        if (this.hasCapability(Status.AGGRESSIVE)) {
            // Check if Guts is on Blight
            if (map.locationOf(this).getGround().hasCapability(Status.CURSED)) {
                this.damageMultiplier = 1.30f;
            } else {
                // Check if Guts is near peaceful creatures
                boolean nearPeaceful = map.locationOf(this).getExits().stream()
                        .map(Exit::getDestination)
                        .anyMatch(loc -> SurroundingCapabilityCondition.hasCapabilityInLocation(loc,
                                Status.NON_HOSTILE_TO_ENEMY));
                this.damageMultiplier = nearPeaceful ? 1.30f : 1.15f;
            }
        } else {
            this.damageMultiplier = 1.0f;
        }
        
        // Perform the behaviours
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) return action;
        }
        
        return new DoNothingAction();
    }

}
