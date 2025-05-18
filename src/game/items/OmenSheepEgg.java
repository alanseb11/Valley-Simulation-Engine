package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Countdown;
import game.actors.npcs.OmenSheep;

/**
 * Represents an Omen Sheep Egg item in the game, laid by the {@link OmenSheep}.
 * This egg can either hatch into a new {@link OmenSheep} after 3 turns on the ground,
 * or be consumed by the player to restore health.
 * The egg is unable to hatch while in the player's inventory.
 */
public class OmenSheepEgg extends Egg {
    private final Countdown timeUntilHatch = new Countdown(3);
    private final OmenSheep hatched = new OmenSheep();

    /**
     * Constructor.
     */
    public OmenSheepEgg() {
        super();
    }

    /**
     * Called every tick when the egg is in the actor's inventory.
     * The timeUntilHatch is reset when in the actor's inventory.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        timeUntilHatch.resetCountdown();
    }

    /**
     * Called every tick when the egg is on the ground.
     * It will hatch if the timeUntilHatch is expired and there is no other actor
     * currently at its location.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (timeUntilHatch.isExpired() && !currentLocation.containsAnActor()) {
            hatch(currentLocation);
        } else if (!timeUntilHatch.isExpired()) {
            timeUntilHatch.applyToItem(this, "hatching");
        }
    }

    /**
     * Defines what happens when the actor consumes the OmenSheepEgg.
     * The actor's health is increased by 10 after consuming the egg.
     *
     * @param actor the actor that is consuming the object
     * @param map   the current game map where the action is taking place
     * @return A string describing the result of the actor consuming the egg.
     */
    @Override
    public String eatenBy(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,10);
        actor.removeItemFromInventory(this);
        return actor + " consumes the Egg and restores 10 health!";
    }

    /**
     * Hatches the egg into an {@link OmenSheep} at the location of the egg.
     *
     * @param location The location where the egg hatches.
     */
    @Override
    public void hatch(Location location) {
        super.hatch(location);
        location.addActor(hatched);
        new Display().println(this + " has hatched into an " + hatched);
    }
}
