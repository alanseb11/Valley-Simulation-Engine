package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.npcs.boss.BedOfChaos;

public class BedOfChaosIntrinsicWeapon extends IntrinsicWeapon {
    private final BedOfChaos boss;

    public BedOfChaosIntrinsicWeapon(BedOfChaos boss) {
        super(25,"lashes out at", 75);
        this.boss = boss;
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
    }
}
