package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;

/**
 * Represents a countdown effect that decreases over time.
 * 
 * The countdown value decreases with each turn, and when it reaches zero, the actor succumbs to the Crimson Rot.
 */
public class Countdown {
    private int countdown;
    private final int initialCountdown;
    private Action action = new DoNothingAction();

    /**
     * Constructs a {@code CountdownDecay} with the specified initial countdown value.
     * 
     * @param initial_countdown The initial countdown value
     */
    public Countdown(int initial_countdown, Action action) {
        this.countdown = initial_countdown;
        this.initialCountdown = initial_countdown;
        this.action = action;
    }

    /**
     * Constructs a {@code CountdownDecay} with the specified initial countdown value.
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
     * Gets the current action.
     *
     * @return The current action
     */
    public Action getAction() {
        return action;
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
     * 
     * This method decrements the countdown and prints a message indicating the actor's remaining turns
     * before succumbing to the Crimson Rot.
     * 
     * @param actor The actor to whom the countdown effect is applied
     */
    public void applyTo(Actor actor) {
        Display display = new Display();
        display.println(actor + " has " + countdown + (countdown == 1 ? " turn" : " turns") + " left before " + action.menuDescription(actor));
        decrement();
    }

    /**
     * Applies the countdown effect to the specified item.
     *
     * This method decrements the countdown and prints a message.
     *
     * @param item The item to which the countdown effect is applied
     */
    public void applyToItem(Item item, String effect) {
        Display display = new Display();
        display.println(item + " has " + countdown + (countdown == 1 ? " turn" : " turns") + " left before " + effect);
        decrement();
    }

}
