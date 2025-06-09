package game.grounds.plants;

import edu.monash.fit2099.engine.positions.Location;
import game.time.Countdown;
import game.grounds.Soil;
import game.items.food.Hydrofruit;

/**
 * A class representing the HydrofruitSprout crop.
 */
public class HydrofruitSprout extends Sprout {
    private final Countdown timeUntilGrown = new Countdown(7);
    private int watered = 0;

    /**
     * Constructor for HydrofruitSprout.
     */
    public HydrofruitSprout() {
        super('h', "HydrofruitSprout");
    }

    /**
     * This method is called once per turn.
     * The HydrofruitSprout slowly grows each tick until it is fully grown.
     *
     * @param location The location of the ground on which the HydrofruitSprout lies.
     */
    @Override
    public void tick(Location location) {
        // Checks if the countdown for growth is expired
        if (timeUntilGrown.isExpired()) {
            grow(location);
        } else {
            timeUntilGrown.applyTo(this, "growing into a Hydrofruit");
        }
    }

    /**
     * A method grow that executes when the sprout is fully grown.
     * The HydrofruitSprout produces 1 Hydrofruit regularly, and 2 if watered 3 or more times.
     *
     * @param location The location of the Sprout
     */
    @Override
    public void grow(Location location) {
        // Sets ground back to soil once it's fully grown
        location.setGround(new Soil());

        // A new Hydrofruit is added at the location once the sprout is grown
        location.addItem(new Hydrofruit());
        // If watered 3 or more times, then it produces twice the amount
        if (watered >= 3) {
            location.addItem(new Hydrofruit());
        }
    }

    /**
     * The water method for when the player waters the sprout.
     * Each water increases the count of 'watered' by 1.
     *
     * @return A string of the action.
     */
    @Override
    public String water() {
        watered ++;
        return super.water();
    }
}
