package game.actors.npcs.boss;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.types.NPC;
import game.behaviours.GrowBehaviour;
import game.capabilities.Status;
import game.interfaces.DamageContributor;
import game.interfaces.Growable;
import game.weapons.BedOfChaosIntrinsicWeapon;
import game.behaviours.AttackBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class BedOfChaos extends NPC implements Growable {
    private int hp = 1000;
    private final int baseDamage = 25;
    private final double accuracy = 0.75;
    private final List<DamageContributor> parts = new ArrayList<>();
    private final Random random = new Random();

    public BedOfChaos() {
        super("Bed of Chaos", 'T', 1000);
        behaviours.clear();
        setIntrinsicWeapon(new BedOfChaosIntrinsicWeapon(this));

        Predicate<Actor> isPlayer = actor -> actor.hasCapability(Status.PLAYER);
        behaviours.put(10, new AttackBehaviour(isPlayer));

        behaviours.put(20, new GrowBehaviour(this));

    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void increaseHp(int amount) {
        hp += amount;
    }

    public List<DamageContributor> getParts() {
        return parts;
    }

    public void addPart(DamageContributor part) {
        parts.add(part);
    }

    public int getTotalAttackDamage() {
        int totalDamage = 0;
        for (DamageContributor part : parts) {
            totalDamage += part.getAttackDamage();
        }
        return totalDamage;
    }

    @Override
    public void grow() {
        if (random.nextBoolean()) {
            Branch branch = new Branch();
            System.out.println("Bed of Chaos grows a new Branch!");
            branch.grow(this);
            parts.add(branch);
        } else {
            parts.add(new Leaf());
            hp += 5;
            System.out.println("Bed of Chaos grows a new Leaf!");
        }
    }

    @Override
    public boolean canGrow(GameMap map) {
        // Get the map containing BedOfChaos
        for (Exit exit : map.locationOf(this).getExits()) {
            Actor target = exit.getDestination().getActor();
            if (target != null && target.hasCapability(Status.PLAYER)) {
                return false;  // Player nearby! Can't grow.
            }
        }
        return true;  // No player nearby.
    }

    @Override
    public String toString() {
        return String.format("Bed of Chaos [HP: %d, Parts: %d]", hp, parts.size());
    }


}
