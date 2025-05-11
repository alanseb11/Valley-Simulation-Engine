package game.actors.npcs;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.EatBeetleAction;
import game.actions.FollowBehaviour;
import game.capabilities.Status;
import game.items.GoldenEgg;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

public class GoldenBeetle extends Actor{
    private int turns = 0;
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
        this.behaviours.put(10, new WanderBehaviour());

    }



}
