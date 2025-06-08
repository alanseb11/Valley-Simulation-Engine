package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.npcs.boss.BedOfChaos;

import java.util.Random;

public class BedOfChaosIntrinsicWeapon extends IntrinsicWeapon {
    private final BedOfChaos boss;

    public BedOfChaosIntrinsicWeapon(BedOfChaos boss) {
        super(25,"lashes out at", 75);
        this.boss = boss;
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        int totalDamage = boss.getBaseDamage() + boss.getTotalAttackDamage();
        Random rand = new Random();

        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(totalDamage);

        return String.format("%s %s %s for %d damage", attacker, verb, target, totalDamage);
    }
}
