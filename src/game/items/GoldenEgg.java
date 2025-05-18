package game.items;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Actor;
import game.actors.npcs.GoldenBeetle;
import game.capabilities.Status;
import game.interfaces.Eatable;
import game.monologueconditions.SurroundingCapabilityCondition;

/**
 * Represents a Golden Egg item in the game, laid by the {@link GoldenBeetle}.
 * <p>
 * This egg can either hatch into a new {@link GoldenBeetle} when placed near cursed ground,
 * or be consumed by the player to restore stamina. If the egg is picked up by the player,
 * it becomes unable to hatch.
 * </p>
 */
public class GoldenEgg extends Egg implements Eatable {

    /**
     * The Golden Beetle instance that this egg will hatch into.
     */
    private GoldenBeetle hatched = new GoldenBeetle();

    /**
     * Constructor for GoldenEgg.
     * Inherits name, display character, and portability from {@link Egg}.
     */
    public GoldenEgg() {
        super();
    }

    /**
     * Called every turn when the egg is in an actor's inventory.
     * Sets {@code canHatch} to false to prevent hatching from inventory.
     *
     * @param currentLocation The location of the actor carrying this item.
     * @param actor           The actor carrying the egg.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.canHatch = false;
    }

    /**
     * Called every turn when the egg is on the ground.
     * If the egg is near cursed ground and not already on an actor,
     * it will hatch into a {@link GoldenBeetle}.
     *
     * @param currentLocation The location of the ground the egg lies on.
     */
    @Override
    public void tick(Location currentLocation) {
        if (canHatch && !currentLocation.containsAnActor()) {
            for (Exit _ : currentLocation.getExits()) {
                if (SurroundingCapabilityCondition.hasCapabilityInLocation(currentLocation, Status.CURSED)) {
                    this.hatch(currentLocation);
                    return;
                }
            }
        }
    }

    /**
     * Defines what happens when an actor consumes the Golden Egg.
     * Consuming the egg restores 20 stamina and removes it from the inventory.
     *
     * @param actor The actor consuming the egg.
     * @param map   The game map in which the action is occurring.
     * @return A message indicating the result of the action.
     */
    @Override
    public String eatenBy(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,20);
        actor.removeItemFromInventory(this);
        return actor + " consumes the Golden Egg and restores 20 stamina!";
    }

    /**
     * Hatches the Golden Egg into a {@link GoldenBeetle} and places it at the specified location.
     *
     * @param location The map location where the egg hatches.
     */
    @Override
    public void hatch(Location location) {
        super.hatch(location);
        location.addActor(hatched);
    }
}
