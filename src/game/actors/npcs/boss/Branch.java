package game.actors.npcs.boss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.displays.Display;

/**
 * Class representing a branch part of the Bed of Chaos boss.
 *
 * <p>
 * A Branch can recursively grow sub-parts (branches and leaves).
 * Each branch contributes damage and can contain nested sub-parts that
 * also contribute to the total damage. When growing, a Branch may
 * continue to grow additional Branches or terminate growth with a Leaf.
 * </p>
 */
public class Branch extends BossPart {
    private final List<BossPart> subParts = new ArrayList<>();
    private final Random random = new Random();


    /**
     * Constructor that initializes this branch with a damage contribution of 3.
     */
    public Branch() {
        super(3);
    }

    /**
     * Calculates the total attack damage contributed by this branch,
     * including all nested sub-parts.
     *
     * @return The total attack damage of this branch and its sub-parts.
     */
    @Override
    public int getAttackDamage() {
        int totalDamage = damage;
        for (BossPart part : subParts) {
            totalDamage += part.getAttackDamage();
        }
        return totalDamage;
    }

    /**
     * Simulates the growth of this branch by adding a new branch or leaf as a sub-part.
     * If the growth results in a leaf, the Bed of Chaos is healed by 5 points.
     *
     * @param boss The Bed of Chaos boss to which this branch belongs.
     */
    public void grow(BedOfChaos boss) {
        Display display = new Display();
        
        Branch currentBranch = this;
        while (true) {
            if (random.nextBoolean()) {
                Branch newBranch = new Branch();
                currentBranch.subParts.add(newBranch);
                display.println("Branch grows a new Branch!");
                currentBranch = newBranch;
            } else {
                currentBranch.subParts.add(new Leaf());
                boss.heal(5);
                display.println("Branch grows a new Leaf!");

                break;
            }
        }
    }
}
