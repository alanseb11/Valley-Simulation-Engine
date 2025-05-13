package game.actors.npcs;

import java.util.HashMap;
import java.util.Map;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import game.capabilities.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.GoldenEgg;


public class GoldenBeetle extends Actor{

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
            map.locationOf(this).addItem(new GoldenEgg);
            turnsSinceLastEgg = 0;
            display.println(this + " has layed a Golden Egg");
            return null;
        }
    }




}
