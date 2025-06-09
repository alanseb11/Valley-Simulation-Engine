package game.time;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * TimeManager class to manage the time of day in the game.
 * It cycles through different times of day and handles the transition between them.
 */
public class TimeManager {
    private List<TimeOfDay> dayCycle = new ArrayList<>();
    private int currentTimeIndex = 0;

    /**
     * Constructor for TimeManager.
     * Initialises the day cycle with different times of day.
     */
    public TimeManager() {
        // Initialise the day cycle with different times of day
        dayCycle.add(new Morning());
        dayCycle.add(new Afternoon());
        dayCycle.add(new Night());
        this.currentTimeIndex = 0;
    }

    /**
     * Gets the current time of day.
     *
     * @return the current TimeOfDay object
     */
    public TimeOfDay getCurrentTime() {
        return dayCycle.get(currentTimeIndex);
    }

    /**
     * Manages the ticking of time in the game.
     * 
     * @param actor     the actor to apply the effect to
     */
    public void tick(Actor actor) {
        TimeOfDay currentTime = getCurrentTime();
    
        if (currentTime.hasPassed()) {
            currentTime.reset();
    
            // Move to the next time of day
            currentTimeIndex = (currentTimeIndex + 1) % dayCycle.size();
            TimeOfDay nextTime = dayCycle.get(currentTimeIndex);
            
            nextTime.applyEffect(actor);
            nextTime.tick();
        } else {
            currentTime.applyEffect(actor);
            currentTime.tick();
        }
        
    }
}
