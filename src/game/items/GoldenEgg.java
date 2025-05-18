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

public class GoldenEgg extends Egg implements Eatable {


    public GoldenEgg() {
        super();
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        if (!currentLocation.getItems().contains(this)) {
            return;
        }

        if(nearCursedTile(currentLocation)) {
            currentLocation.removeItem(this);
            currentLocation.addActor(new GoldenBeetle());
        }
    }

    public boolean nearCursedTile(Location location) {
        for (Exit exit : location.getExits()) {
            if (exit.getDestination().getGround().hasCapability(Status.CURSED)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String eatenBy(Actor actor, GameMap map){
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,20);
        actor.removeItemFromInventory(this);
        return actor + " consumes the Golden Egg and restores 20 stamina!";
    }
}
