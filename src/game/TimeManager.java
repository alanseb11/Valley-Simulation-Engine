package game;

import java.util.ArrayList;
import java.util.List;

public class TimeManager {
   private List<TimeOfDay> dayCycle = new ArrayList<>();
    private int currentTimeIndex = 0;

    public TimeManager() {
        // Initialise the day cycle with different times of day
        dayCycle.add(new TimeOfDay("Morning", new Countdown(1)));
        dayCycle.add(new TimeOfDay("Afternoon", new Countdown(1)));
        dayCycle.add(new TimeOfDay("Night", new Countdown(2)));
        this.currentTimeIndex = 0;
    }

    public TimeOfDay getCurrentTime() {
        return dayCycle.get(currentTimeIndex);
    }

    public void tick() {
        TimeOfDay currentTime = getCurrentTime();
        currentTime.tick();

        // Check if the current time has passed
        if (currentTime.hasPassed()) {
            // Move to the next time of day
            currentTimeIndex = (currentTimeIndex + 1) % dayCycle.size();
            // Reset the new current time
            dayCycle.get(currentTimeIndex).reset();
        }
    }
}
