package game.actors.npcs;

import java.util.HashMap;
import java.util.Map;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.capabilities.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.interfaces.Eatable;
import game.items.GoldenEgg;


public class GoldenBeetle extends Actor implements Eatable {

    private int health = 25;
    private int turnsSinceLastEgg = 0;
    private Actor followTarget = null;
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();

    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
        behaviours.put(2, new WanderBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        turnsSinceLastEgg++;

        if (turnsSinceLastEgg >=5) {
            map.locationOf(this).addItem(new GoldenEgg());
            turnsSinceLastEgg = 0;
            display.println(this + " has laid a Golden Egg");
            return null;
        }

        if (followTarget == null) {
            Location here = map.locationOf(this);
            for (Exit exit : here.getExits()) {
                Actor potentialTarget = exit.getDestination().getActor();
                if (potentialTarget != null && potentialTarget.hasCapability(Status.FOLLOWABLE)) {
                    followTarget = potentialTarget;
                    behaviours.put(1, new FollowBehaviour(followTarget));
                    break;
                }
            }
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }

        return null;
    }


    @Override
    public String eatenBy(Actor actor, GameMap map) {
        actor.heal(15);
        if (actor.hasCapability(Status.PLAYER)) {
            actor.addBalance(1000);
        }
        map.removeActor(this);
        return actor + " eats the " + this + " and gains 15 health + 1000 runes!";
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.PLAYER)) {
            actions.add(new EatAction(this));
        }
        return actions;
    }




}
