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
 *
 * <p>Golden Beetles:
 * <ul>
 *     <li>Have 25 hit points</li>
 *     <li>Lay a {@link GoldenEgg} every 5 turns using a {@link Countdown} system</li>
 *     <li>Wander the map by default</li>
 *     <li>Follow nearby actors with {@link Status#FOLLOWABLE}</li>
 *     <li>Can be consumed by the Player to restore health and receive currency</li>
 *     <li>Can be attacked like other NPCs</li>
 * </ul>
 * </p>
 *
 * This class implements {@link Eatable} and {@link Producible} to enable custom logic for consumption and egg production.
 */
public class GoldenBeetle extends Actor implements Eatable, Producible {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private final Countdown timeUntilLay = new Countdown(5, new ProduceAction(this));

    /**
     * Constructs a Golden Beetle with 25 HP.
     *
     * Adds a {@link CountdownBehaviour} to trigger egg-laying every 5 turns,
     * and a default {@link WanderBehaviour} for movement.
     */
    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
        behaviours.put(0, new CountdownBehaviour(timeUntilLay));
        behaviours.put(2, new WanderBehaviour());
    }

    /**
     * Executes one turn of the Golden Beetle's behaviour.
     *
     * <p>Each turn:
     * <ul>
     *     <li>Scans adjacent tiles for {@link Status#FOLLOWABLE} actors and follows them</li>
     *     <li>Executes the first applicable behaviour from the priority map</li>
     * </ul>
     * </p>
     *
     * @param actions    The list of possible actions this turn
     * @param lastAction The last action taken
     * @param map        The current game map
     * @param display    Display for text output
     * @return An action to perform this turn, or null if none available
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
     * Called when this beetle is consumed by another actor.
     *
     * <p>Effects on the consumer:
     * <ul>
     *     <li>Restores 15 HP</li>
     *     <li>Grants 1000 runes if the actor has {@link Status#PLAYER}</li>
     * </ul>
     * The beetle is removed from the map after consumption.
     * </p>
     *
     * @param actor The consuming actor
     * @param map   The current game map
     * @return Description of the consumption event
     */
    @Override
    public String eatenBy(Actor actor, GameMap map) {
        actor.heal(15);
        actor.addBalance(1000);
        map.removeActor(this);
        return actor + " eats the " + this + " and gains 15 health + 1000 runes!";
    }

    /**
     * Returns the actions available to other actors when interacting with this beetle.
     *
     * <p>Currently allows:
     * <ul>
     *     <li>{@link EatAction} if the actor is hostile</li>
     *     <li>{@link AttackAction} if the actor is hostile</li>
     * </ul>
     * </p>
     *
     * @param otherActor The interacting actor
     * @param direction  Direction of the beetle relative to the actor
     * @param map        The game map
     * @return A list of allowable actions
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
     * Produces a {@link GoldenEgg} and places it on the current map location.
     *
     * @param actor The beetle (this)
     * @param map   The current map
     * @return A message indicating the egg has been laid
     */
    @Override
    public String produce(Actor actor, GameMap map) {
        map.locationOf(this).addItem(new GoldenEgg());
        return this + " has laid a Golden Egg!";
    }
}