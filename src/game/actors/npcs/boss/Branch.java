package game.actors.npcs.boss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.displays.Display;

public class Branch extends BossPart {
    private final List<BossPart> subParts = new ArrayList<>();
    private final Random random = new Random();

    public Branch() {
        super(3);
    }

    @Override
    public int getAttackDamage() {
        int totalDamage = damage;
        for (BossPart part : subParts) {
            totalDamage += part.getAttackDamage();
        }
        return totalDamage;
    }

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
