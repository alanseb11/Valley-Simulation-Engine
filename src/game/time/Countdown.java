package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;

/**
 * Represents a countdown that decreases over time.
 */
public class Countdown {
    private int countdown;
    private final int initialCountdown;

    /**
     * Constructs a {@code Countdown} with the specified initial countdown value,
     * 
     * @param initial_countdown The initial countdown value
     */
    public Countdown(int initial_countdown) {
        this.countdown = initial_countdown;
        this.initialCountdown = initial_countdown;
    }

    /**
     * Resets the countdown to the initial countdown value.
     */
    public void resetCountdown() {
        this.countdown = initialCountdown;
    }

    /**
     * Gets the current countdown value.
     * 
     * @return The current countdown value
     */
    public int getCountdown() {
        return countdown;
    }

    /**
     * Decrements the countdown value by 1.
     */
    public void decrement() {
        countdown--;
    }

    /**
     * Determines whether the countdown has reached expired.
     * 
     * @return {@code true} if the countdown is less than or equal to zero, {@code false} otherwise
     */
    public boolean isExpired() {
        return countdown <= 0;
    }

    /**
     * Applies the countdown effect to the specified actor.
     * This method decrements the countdown and prints a message indicating the actor's remaining turns
     * before an action occurs.
     * 
     * @param actor The actor to whom the countdown is applied
     * @param action The action to be performed when the countdown expires
     */
    public void applyTo(Actor actor, String action) {
        new Display().println(actor + " has " + countdown + (countdown == 1 ? " turn" : " turns") + " left before " + action);
        decrement();
    }

    /**
     * Applies the countdown effect to the specified item.
     * This method decrements the countdown and prints a message indicating the remaining turns.
     *
     * @param item The item to which the countdown is applied
     * @param action The action to be performed when the countdown expires
     */
    public void applyTo(Item item, String action) {
        new Display().println(item + " has " + countdown + (countdown == 1 ? " turn" : " turns") + " left before " + action);
        decrement();
    }

}
