package game.actors.npcs.boss;

import edu.monash.fit2099.engine.actors.Actor;
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
            branch.grow();
            parts.add(branch);
        } else {
            parts.add(new Leaf());
            hp += 5;
        }
    }

    @Override
    public boolean canGrow() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("Bed of Chaos [HP: %d, Accuracy: %.2f]", hp, parts.size());
    }


}
