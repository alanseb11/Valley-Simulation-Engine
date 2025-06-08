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

    public void grow() {
        if (random.nextBoolean()) {
            Branch newBranch = new Branch();
            newBranch.grow();
            subParts.add(newBranch);
        } else {
            subParts.add(new Leaf());
        }
    }
}
