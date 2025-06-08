package game.actors.npcs.boss;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.types.AttackableNPC;
import game.behaviours.GrowBehaviour;
import game.capabilities.Status;
import game.interfaces.Growable;
import game.weapons.BedOfChaosIntrinsicWeapon;
import game.behaviours.AttackBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class BedOfChaos extends AttackableNPC implements Growable {
    private final int baseDamage = 25;
    private List<BossPart> parts = new ArrayList<>();
    private final Random random = new Random();

    public BedOfChaos() {
        super("Bed of Chaos", 'T', 1000);
        behaviours.clear();
        setIntrinsicWeapon(new BedOfChaosIntrinsicWeapon(this));

        Predicate<Actor> isPlayer = actor -> actor.hasCapability(Status.HOSTILE_TO_ENEMY);
        behaviours.put(10, new AttackBehaviour(isPlayer));
        behaviours.put(20, new GrowBehaviour(this));

    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getTotalAttackDamage() {
        int totalDamage = 0;
        for (BossPart part : parts) {
            totalDamage += part.getAttackDamage();
        }
        return totalDamage;
    }

    @Override
    public String grow() {
        if (random.nextBoolean()) {
            Branch branch = new Branch();
            parts.add(branch);
            branch.grow(this);
            return this + " grew a new Branch!";
        } else {
            parts.add(new Leaf());
            this.heal(5);
            return this + " grew a new Leaf!";
        }
    }

    @Override
    public boolean canGrow(GameMap map) {
        // Get the map containing BedOfChaos
        for (Exit exit : map.locationOf(this).getExits()) {
            Actor target = exit.getDestination().getActor();
            if (target != null && target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("Bed of Chaos [HP: %d, Parts: %d, Damage: %d]", this.getAttribute(BaseActorAttributes.HEALTH) , parts.size(), getBaseDamage() + getTotalAttackDamage());
    }


}
