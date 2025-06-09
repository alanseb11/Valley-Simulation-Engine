package game.grounds.plants;

import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.interfaces.Daybound;
import game.time.*;

/**
 * A class representing the LumentreeSprout crop.
 */
public class LumentreeSprout extends Sprout implements Daybound {
    private final Countdown timeUntilGrown = new Countdown(10);
    private final TimeManager timeManager = new TimeManager();

    /**
     * Constructor for LumentreeSprout.
     */
    public LumentreeSprout() {
        super('l', "LumentreeSprout");

        timeManager.add(new Morning());
        timeManager.add(new Afternoon());
        timeManager.add(new Night());
    }

    /**
     * This method is called once per turn.
     * The LumentreeSprout slowly grows each tick where it is day until it is fully grown.
     *
     * @param location The location of the ground on which the LumentreeSprout lies.
     */
    @Override
    public void tick(Location location) {
        // Applies the tick cycle of the time manager
        this.getTimeManager().tick();
        // Only grows when it's not nighttime
        if (!this.getTimeManager().getCurrentTime().hasCapability(Status.NIGHT)) {
            // Checks if the countdown for growth is expired
            if (timeUntilGrown.isExpired()) {
                grow(location);
            } else {
                timeUntilGrown.applyTo(this, "growing into a Lumentree");
            }
        }
    }

    /**
     * A method grow that executes when the sprout is fully grown.
     * The LumentreeSprout becomes a Lumentree once fully grown.
     *
     * @param location The location of the Sprout
     */
    @Override
    public void grow(Location location) {
        location.setGround(new Lumentree());
    }

    /**
     * Returns the {@link TimeManager} associated with this entity.
     *
     * @return the TimeManager controlling the day/night cycle for this entity
     */
    @Override
    public TimeManager getTimeManager() {
        return timeManager;
    }
}
