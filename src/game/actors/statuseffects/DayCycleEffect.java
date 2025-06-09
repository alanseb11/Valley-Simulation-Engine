package game.actors.statuseffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Daybound;

/**
 * A status effect that applies the day/night cycle's effect to an actor.
 * <p>
 * This effect uses the {@link Daybound} interface to access the time manager and
 * apply time-based effects to the actor each tick.
 */
public class DayCycleEffect extends StatusEffect {
    private final Daybound daybound;
    
    /**
     * Constructs a DayCycleEffect for a given Daybound entity.
     *
     * @param Daybound the entity that is bound to the day/night cycle and provides access to the time manager
     */
    public DayCycleEffect(Daybound daybound) {
        super("Day cycle's effect");
        this.daybound = daybound;
    }

    /**
     * Applies the day/night cycle effect to the actor each tick.
     *
     * @param location the location of the actor
     * @param actor    the actor affected by this status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        daybound.getTimeManager().tick(actor);
    }

}
