package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Countdown;
import game.actors.npcs.OmenSheep;

public class OmenSheepEgg extends Egg {
    private final Countdown timeUntilHatch = new Countdown(3);
    private final OmenSheep hatched = new OmenSheep();

    /**
     * Constructor.
     */
    public OmenSheepEgg() {
        super();
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        timeUntilHatch.resetCountdown();
    }

    public void tick(Location currentLocation) {
        if (timeUntilHatch.isExpired() && !currentLocation.containsAnActor()) {
            hatch(currentLocation);
        }

        timeUntilHatch.applyToItem(this, "hatching");
    }

    @Override
    public String eatenBy(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,10);
        actor.removeItemFromInventory(this);
        return actor + " consumes the Egg and restores 10 health!";
    }

    @Override
    public void hatch(Location location) {
        super.hatch(location);
        location.addActor(hatched);
    }
}
