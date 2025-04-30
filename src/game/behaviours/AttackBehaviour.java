package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

public class AttackBehaviour implements Behaviour {

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public AttackBehaviour() {}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Actor target = getTargetActor(exit);
            if (isValidTarget(target)) {
                return new AttackAction(target, exit.getName());
            }
        }
        return null;
    }
    
    private Actor getTargetActor(Exit exit) {
        return exit.getDestination().containsAnActor() ? exit.getDestination().getActor() : null;
    }
    
    private boolean isValidTarget(Actor target) {
        return target != null && target.getAttribute(BaseActorAttributes.HEALTH) > 50;
    }

}
