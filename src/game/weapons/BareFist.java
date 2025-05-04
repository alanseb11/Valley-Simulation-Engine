package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Class representing an intrinsic weapon called a bare fist.
 * This intrinsic weapon deals 25 damage points with a 50% chance
 * to hit the target.
 * @author Adrian Kristanto
 */
public class BareFist extends IntrinsicWeapon {
    public BareFist() {
        super(25, "punches", 50);
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
