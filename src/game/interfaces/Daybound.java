package game.interfaces;

import game.time.TimeManager;

/**
 * An interface for entities that are affected by the day/night cycle.
 * <p>
 * Implementing classes must provide access to a {@link TimeManager}
 * to allow time-based effects to be applied.
 */
public interface Daybound {
    /**
     * Returns the {@link TimeManager} associated with this entity.
     *
     * @return the TimeManager controlling the day/night cycle for this entity
     */
    TimeManager getTimeManager();
}
