package game.actors.npcs.boss;

import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.GrowBehaviour;
import game.capabilities.Status;
import game.interfaces.Growable;
import game.weapons.BedOfChaosIntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.actions.AttackAction;

import java.util.*;
import java.util.function.Predicate;

/**
 * Represents the Bed of Chaos boss NPC in the game.
 *
 * <p>
 * This boss is stationary, grows branches and leaves, and can attack the player.
 * Each branch and leaf contributes to its total attack damage.
 * It implements the {@link Growable} interface to manage its growth behavior.
 * </p>
 */
public class BedOfChaos extends Actor implements Growable {
    private final int baseDamage = 25;
    private List<BossPart> parts = new ArrayList<>();
    private final Random random = new Random();
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor to create a new Bed of Chaos instance with base health and behaviors.
     */
    public BedOfChaos() {
        super("Bed of Chaos", 'T', 1000);

        setIntrinsicWeapon(new BedOfChaosIntrinsicWeapon(this));

        Predicate<Actor> isPlayer = actor -> actor.hasCapability(Status.HOSTILE_TO_ENEMY);
        behaviours.put(10, new AttackBehaviour(isPlayer));
        behaviours.put(20, new GrowBehaviour(this));

    }

    /**
     * Gets the base damage of the Bed of Chaos.
     *
     * @return base damage value
     */
    public int getBaseDamage() {
        return baseDamage;
    }

    /**
     * Calculates the total damage of the Bed of Chaos by adding
     * the damage of all grown parts (branches and leaves).
     *
     * @return total attack damage
     */
    public int getTotalAttackDamage() {
        int totalDamage = 0;
        for (BossPart part : parts) {
            totalDamage += part.getAttackDamage();
        }
        return totalDamage;
    }

    /**
     * Handles the growth behavior of the Bed of Chaos.
     * Randomly grows a new branch (which may recursively grow sub-parts) or a leaf.
     *
     * @return description of the growth event
     */
    @Override
    public String grow() {
        if (random.nextBoolean()) {
            Branch branch = new Branch();
            parts.add(branch);
            branch.grow(this);
            return this + " grew a new Branch!";
        } else {
            parts.add(new Leaf());
            this.heal(5);
            return this + " grew a new Leaf!";
        }
    }

    /**
     * Determines whether the Bed of Chaos can grow.
     * Growth is not allowed if a player is adjacent.
     *
     * @param map the game map containing this boss
     * @return true if it can grow, false otherwise
     */
    @Override
    public boolean canGrow(GameMap map) {
        // Get the map containing BedOfChaos
        for (Exit exit : map.locationOf(this).getExits()) {
            Actor target = exit.getDestination().getActor();
            if (target != null && target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a string representation of the Bed of Chaos, including
     * its current health, number of parts, and total damage.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return String.format("Bed of Chaos [HP: %d, Parts: %d, Damage: %d]", this.getAttribute(BaseActorAttributes.HEALTH) , parts.size(), getBaseDamage() + getTotalAttackDamage());
    }

    /**
     * Returns the list of actions other actors can perform on the Bed of Chaos.
     * Allows the player (HOSTILE_TO_ENEMY) to attack it.
     *
     * @param otherActor the actor interacting with the boss
     * @param direction  the direction of the boss relative to the other actor
     * @param map        the current game map
     * @return an ActionList of possible actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Determines and executes the action the Bed of Chaos performs each turn.
     * Prioritizes behaviors based on their defined priority.
     *
     * @param actions    the list of possible actions
     * @param lastAction the action taken in the previous turn
     * @param map        the game map
     * @param display    the display object for rendering messages
     * @return the action to be executed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (edu.monash.fit2099.engine.actors.Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }
}
