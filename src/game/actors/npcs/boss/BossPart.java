package game.actors.npcs.boss;

/**
 * Abstract class representing a part of the Bed of Chaos boss entity.
 *
 * <p>
 * Each BossPart contributes a certain amount of damage to the total attack damage of the boss.
 * Concrete subclasses, such as branches and leaves, define how much damage they add and
 * can add additional functionality (e.g., recursive growth).
 * </p>
 */
public abstract class BossPart {
    protected final int damage;

    /**
     * Constructor to initialize the damage contribution of this boss part.
     *
     * @param damage The damage points that this part contributes.
     */
    public BossPart(int damage) {
        this.damage = damage;
    }

    /**
     * Gets the attack damage contributed by this boss part.
     *
     * @return The damage points contributed by this part.
     */
    public int getAttackDamage() {
        return damage;
    }
}
