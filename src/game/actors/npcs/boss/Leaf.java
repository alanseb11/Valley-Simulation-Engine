package game.actors.npcs.boss;

public class Leaf extends BossPart {
    private final int damage = 1;

    @Override
    public int getAttackDamage() {
        return damage;
    }
}
