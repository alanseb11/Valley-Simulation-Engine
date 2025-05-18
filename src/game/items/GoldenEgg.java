package game.items;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.EatAction;
import game.actors.npcs.GoldenBeetle;
import game.capabilities.Status;
import game.interfaces.Eatable;
import game.monologueconditions.SurroundingCapabilityCondition;

public class GoldenEgg extends Egg implements Eatable {
    private GoldenBeetle hatched = new GoldenBeetle();

    public GoldenEgg() {
        super();
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.canHatch = false;
    }

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

    @Override
    public String eatenBy(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,20);
        actor.removeItemFromInventory(this);
        return actor + " consumes the Golden Egg and restores 20 stamina!";
    }

    @Override
    public void hatch(Location location) {
        super.hatch(location);
        location.addActor(hatched);
    }
}
