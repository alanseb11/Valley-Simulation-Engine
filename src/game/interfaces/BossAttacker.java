package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
public interface BossAttacker {
    String performAttack(Actor target, GameMap map);
}
