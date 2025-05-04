package game.monologueconditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Threshold;
import game.interfaces.MonologueCondition;

/**
 * A condition that checks the wallet balance of an actor.
 * This condition can be used to trigger monologues based on the actor's wallet status.
 */
public class WalletCondition implements MonologueCondition {
    private int walletThreshold;
    private Threshold comparison;

    /**
     * Constructor.
     *
     * @param threshold   The threshold value for the wallet condition
     * @param comparison  The comparison type (ABOVE or BELOW)
     */
    public WalletCondition(int threshold, Threshold comparison) {
        this.walletThreshold = threshold;
        this.comparison = comparison;
    }

    /**
     * Checks if the condition is met for the given actor.
     *
     * @param listener The actor to check the condition against
     * @param map      The game map
     * @return true if the condition is met, false otherwise
     */
    @Override
    public boolean test(Actor listener, GameMap map) {
        return comparison.compare(listener.getBalance(), walletThreshold);
    }

}
