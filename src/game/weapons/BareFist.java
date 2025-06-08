package game.weapons;

import java.util.Random;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Class representing an intrinsic weapon called a bare fist.
 * This intrinsic weapon deals 25 damage points with a 50% chance
 * to hit the target.
 * @author Adrian Kristanto
 */
public class BareFist extends IntrinsicWeapon {
    private float damageMultiplier;
    
    public BareFist(float damageMultiplier) {
        super(25, "punches", 50);
        this.damageMultiplier = damageMultiplier;
    }

    /**
     * Sample implementation of the attack method of the intrinsic weapon.
     * If the hit rate is not met, the attacker misses the target.
     * Otherwise, the target is hit, hurting the target by the intrinsic weapon's damage
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return the description once the attack is done
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        int totalDamage = Math.round(damage * damageMultiplier);
        target.hurt(totalDamage);

        return String.format("%s %s %s for %d damage", attacker, verb, target, totalDamage);
    }

    /**
     * Returns a string representation of the BareFist.
     *
     * @return A string representing the BareFist
     */
    @Override
    public String toString() {
        return "Bare Fist";
    }
}
