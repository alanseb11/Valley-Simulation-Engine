package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utility;
import game.items.Egg;

public class HatchAction extends Action {
    private Egg egg;

    public HatchAction(Egg egg) { this.egg = egg; }

    @Override
    public String execute(Actor hatched, GameMap map) {
        return "";
    }

    @Override
    public String menuDescription(Actor hatched) {
        String article = Utility.startsWithVowel(hatched.toString()) ? "an " : "a ";
        return egg + " hatches into " + article + hatched;
    }
}
