package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Represents an action where an {@link Actor} attacks another {@link Actor}.
 * 
 * This action handles the logic for performing an attack, including determining the weapon used,
 * applying damage to the target, and handling the target's state (e.g., unconsciousness) after the attack.
 * 
 * This class is a modified version of the original `AttackAction` class from the FIT2099 demo.
 * 
 * Original Author: @author Adrian Kristanto.
 * Original source: src/edu/monash/fit2099/demo/huntsman/AttackAction.java
 * 
 * Modified by: @author Naomi Chuang
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target the actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the attack action.
     * 
     * This method determines the weapon to use (if not already specified), applies damage to the target,
     * and handles the target's state if they become unconscious after the attack.
     * 
     * @param actor The actor performing the attack
     * @param map   The game map where the action takes place
     * @return A string describing the result of the attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        String result = weapon.attack(actor, target, map);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }

        return result;
    }

    /**
     * Returns a description of the attack action for the menu.
     * 
     * @param actor The actor performing the attack
     * @return A string describing the attack action for the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : actor.getIntrinsicWeapon());
    }
}

