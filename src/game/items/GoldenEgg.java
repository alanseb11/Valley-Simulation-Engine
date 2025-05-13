package game.items;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.npcs.GoldenBeetle;
import game.capabilities.Status;

public class GoldenEgg extends Egg{


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
}
