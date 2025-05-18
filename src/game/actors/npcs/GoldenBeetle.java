package game.actors.npcs;

import java.util.HashMap;
import java.util.Map;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.demo.huntsman.AttackAction;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.Countdown;
import game.actions.EatAction;
import game.actions.ProduceAction;
import game.capabilities.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.behaviours.CountdownBehaviour;
import game.interfaces.Eatable;
import game.interfaces.Producible;
import game.items.GoldenEgg;

/**
 * Represents a Golden Beetle NPC in the game world.
 * <p>
 * Golden Beetles lay a {@link GoldenEgg} every five turns, can wander the map, and follow nearby
 * followable actors such as the Player. They are also edible by the Player, granting healing and runes.
 * </p>
 */
public class GoldenBeetle extends Actor implements Eatable, Producible {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private final Countdown timeUntilLay = new Countdown(5, new ProduceAction(this));

    /**
     * Constructs a new Golden Beetle with 25 HP and assigns its initial behaviours.
     */
    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
        behaviours.put(0, new CountdownBehaviour(timeUntilLay));
        behaviours.put(2, new WanderBehaviour());
    }

    /**
     * Defines the Golden Beetle's turn-based behavior.
     * <ul>
     *     <li>Searches for a nearby followable actor and initiates follow behaviour.</li>
     *     <li>Executes the highest-priority available behaviour action.</li>
     * </ul>
     *
     * @param actions    Available actions for this turn.
     * @param lastAction Last action performed by this actor.
     * @param map        The current game map.
     * @param display    Display to show any output.
     * @return The action to perform.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location here = map.locationOf(this);
        for (Exit exit : here.getExits()) {
            Actor potentialTarget = exit.getDestination().getActor();
            if (potentialTarget != null && potentialTarget.hasCapability(Status.FOLLOWABLE)) {
                behaviours.put(1, new FollowBehaviour(potentialTarget));
                break;
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

    /**
     * Defines the behavior when the Golden Beetle is consumed by another actor.
     * <ul>
     *     <li>Restores 15 HP to the consumer.</li>
     *     <li>Adds 1000 runes to the consumer if they are the Player.</li>
     *     <li>Removes the beetle from the game map.</li>
     * </ul>
     *
     * @param actor The actor consuming the beetle.
     * @param map   The current game map.
     * @return A description of the result of the eating action.
     */
    @Override
    public String eatenBy(Actor actor, GameMap map) {
        actor.heal(15);
        actor.addBalance(1000);
        map.removeActor(this);
        return actor + " eats the " + this + " and gains 15 health + 1000 runes!";
    }

    /**
     * Determines what actions other actors can perform on the Golden Beetle.
     * Currently allows the Player to eat it.
     *
     * @param otherActor The actor interacting with this beetle.
     * @param direction  The direction of the beetle relative to the actor.
     * @param map        The current game map.
     * @return A list of allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new EatAction(this));
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Spawns a Golden Egg on the beetle's location.
     */
    @Override
    public String produce(Actor actor, GameMap map) {
        map.locationOf(this).addItem(new GoldenEgg());
        return this + " has laid a Golden Egg!";
    }
}