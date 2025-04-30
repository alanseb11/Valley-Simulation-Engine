package game.behaviours;

import java.util.function.Predicate;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

/**
 * A {@link Behaviour} that allows an {@link Actor} to attack another {@link Actor} if certain conditions are met.
 */
public class AttackBehaviour implements Behaviour {
    Predicate<Actor> condition;
    
    /**
     * Constructor.
     *
     * @param condition a predicate that determines if the target actor is valid for attack
     */
    public AttackBehaviour(Predicate<Actor> condition) {
        this.condition = condition;
    }

    /**
     * Returns an {@link Action} that allows the actor to attack another actor if the conditions are met.
     *
     * @param actor The actor performing the action
     * @param map   The map containing the actor
     * @return An {@link AttackAction} that allows the actor to attack another actor, or null if no valid target is found
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Actor target = exit.getDestination().getActor();
            if (isValidTarget(target)) {
                return new AttackAction(target, exit.getName());
            }
        }
        return null;
    }
    
    private boolean isValidTarget(Actor target) {
        return target != null && condition.test(target);
    }

}
