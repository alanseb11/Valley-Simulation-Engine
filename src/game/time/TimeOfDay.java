package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Abstract class representing a time of day in the game.
 * Each time of day has a name and a duration, and can exert effects on the game map.
 */
public abstract class TimeOfDay {
    private String name;
    private Countdown duration;
    
/**
     * Constructor for TimeOfDay.
     *
     * @param name the name of the time of day
     * @param duration the duration of the time of day as a Countdown object
     */
    public TimeOfDay(String name, Countdown duration) {
        this.name = name;
        this.duration = duration;
    }

    /**
     * Gets the name of the time of day.
     *
     * @return the name of the time of day
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Checks if the time of day has passed.
     * A time of day is considered passed if its duration has expired.
     * 
     * @return true if the time of day has passed, false otherwise
     */
    public boolean hasPassed() {
        return duration.isExpired();
    }

    /**
     * Ticks down the duration of the time of day.
     */
    public void tick() {
        duration.decrement();
    }

    /**
     * Resets the duration of the time of day.
     * This method sets the countdown back to its initial value.
     */
    public void reset() {
        duration.resetCountdown();
    }

    /**
     * Applies the effects of the time of day to the specified actor and game map.
     * This method should be implemented by subclasses to define specific effects.
     *
     * @param actor the actor to apply the effect to
     * @param map the game map where the effect is applied
     */
    public abstract void applyEffect(Actor actor, GameMap map);

}
