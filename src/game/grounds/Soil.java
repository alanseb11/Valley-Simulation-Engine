package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.capabilities.Status;

/**
 * A class representing the soil in the valley
 * @author Adrian Kristanto
 */
public class Soil extends Ground {
    public Soil() {
        super('.', "Soil");
        this.addCapability(Status.FERTILE);
    }

}
