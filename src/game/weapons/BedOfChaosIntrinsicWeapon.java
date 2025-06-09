package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.npcs.boss.BedOfChaos;

import java.util.Random;

/**
 * Represents the intrinsic weapon used by the {@link BedOfChaos} boss.
 *
 * <p>
 * This weapon combines the base damage of the Bed of Chaos with the accumulated
 * damage from its grown parts (e.g., branches and leaves).
 * It has a 75% chance to hit.
 * </p>
 *
 * <p>
 * If the attack misses, a message is returned indicating the miss.
 * If the attack hits, the target takes the calculated damage.
 * </p>
 *
 * @author YourName
 */

public class BedOfChaosIntrinsicWeapon extends IntrinsicWeapon {
    private final BedOfChaos boss;

    /**
     * Constructor for the BedOfChaosIntrinsicWeapon.
     *
     * @param boss the instance of Bed of Chaos using this weapon
     */
    public BedOfChaosIntrinsicWeapon(BedOfChaos boss) {
        super(25,"lashes out at", 75);
        this.boss = boss;
    }

    /**
     * Performs an attack using this intrinsic weapon.
     *
     * <p>
     * Calculates the total damage by summing the base damage and the damage from all
     * boss parts (branches and leaves).
     * If the attack misses based on hit rate, a miss message is returned.
     * </p>
     *
     * @param attacker the actor performing the attack (Bed of Chaos)
     * @param target   the actor being attacked
     * @param map      the map where the attack takes place
     * @return a description of the attack result
     */
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
