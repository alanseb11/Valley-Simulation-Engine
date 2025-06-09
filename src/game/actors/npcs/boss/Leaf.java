package game.actors.npcs.boss;

/**
 * Class representing a leaf part of the Bed of Chaos boss.
 *
 * <p>
 * A Leaf is a simple boss part that contributes a small amount of damage
 * (1 point) to the total attack damage. Unlike branches, leaves cannot grow
 * additional parts. When grown, leaves also heal the Bed of Chaos.
 * </p>
 */
public class Leaf extends BossPart {
    /**
     * Constructor that initializes this leaf with a damage contribution of 1.
     */
    public Leaf() {

        super(1);
    }
}
