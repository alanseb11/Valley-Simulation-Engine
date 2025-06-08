package game.actors.npcs.boss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Branch extends BossPart {
    private final int damage = 3;
    private final List<BossPart> subParts = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public int getAttackDamage() {
        int totalDamage = damage;
        for (BossPart part : subParts) {
            totalDamage += part.getAttackDamage();
        }
        return totalDamage;
    }

    public void grow(BedOfChaos boss) {
        Branch currentBranch = this;
        while (true) {
            if (random.nextBoolean()) {
                Branch newBranch = new Branch();
                currentBranch.subParts.add(newBranch);
                System.out.println("Branch grows a new Branch!");
                currentBranch = newBranch;  // Keep growing from the new branch
            } else {
                currentBranch.subParts.add(new Leaf());
                boss.increaseHp(5);
                System.out.println("Branch grows a new Leaf!");

                break;
            }
        }
    }
}
