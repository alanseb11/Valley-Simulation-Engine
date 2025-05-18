package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.capabilities.Status;
import game.interfaces.Eatable;

public abstract class Egg extends Item implements Eatable {
    protected boolean canHatch = true;
    /***
     * Constructor.
     */
    public Egg() {
        super("Egg", '0', true);
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        if (owner.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new EatAction(this));
        }
        return actions;
    }

    public void hatch(Location location) {
        location.removeItem(this);
    }
    
}
