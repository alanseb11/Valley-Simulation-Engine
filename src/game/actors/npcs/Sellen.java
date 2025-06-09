package game.actors.npcs;

import java.util.*;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.actors.npcs.types.MonologuingNPC;
import game.capabilities.Status;
import game.interfaces.PurchaseEffect;
import game.monologueconditions.ConditionalMonologue;
import game.monologueconditions.DefaultCondition;
import game.purchaseeffects.IncreaseMaxEffect;
import game.purchaseeffects.MerchantOffer;
import game.purchaseeffects.RestoreEffect;
import game.purchaseeffects.SpawnEffect;
import game.weapons.Broadsword;
import game.weapons.DragonslayerGreatsword;
import game.weapons.Katana;

/**
 * Class representing the Sellen NPC.
 */
public class Sellen extends MonologuingNPC{
    private List<MerchantOffer> offerings = new ArrayList<>();

    /**
     * Constructor.
     */
    public Sellen() {
        super("Sellen", 's', 150);
        this.addCapability(Status.MERCHANT);
        this.addBalance(100);

        // Initialise monologue pool
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever."));
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "You sense it too, don't you? The Glintstone hums, even now."));

        // Initialise merchant offerings
        // Broadsword offering
        offerings.add(new MerchantOffer(this, new Broadsword(), 100,
                new ArrayList<PurchaseEffect>(List.of(new IncreaseMaxEffect(BaseActorAttributes.HEALTH, 20)))
        ));

        // Katana offering
        offerings.add(new MerchantOffer(this, new Katana(), 500,
                new ArrayList<PurchaseEffect>(List.of(
                        new SpawnEffect(new OmenSheep()), 
                        new RestoreEffect(BaseActorAttributes.HEALTH, 10), 
                        new IncreaseMaxEffect(BaseActorAttributes.STAMINA, 20)
                ))
        ));

        // DragonslayerGreatsword offering
        offerings.add(new MerchantOffer(this, new DragonslayerGreatsword(), 1500,
                new ArrayList<PurchaseEffect>(List.of(new SpawnEffect(new GoldenBeetle())))
        ));
    }

    /**
     * Returns the list of actions that other actors can perform on Sellen.
     *
     * @param otherActor The Actor that is interacting with Sellen
     * @param direction  The direction in which the action is being performed
     * @param map        The map containing the Actor
     * @return An ActionList containing the actions that can be performed on Sellen
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            for (MerchantOffer offer : offerings) {
                actions.add(new PurchaseAction(offer, this, otherActor));
            }
        }

        return actions;
    }
}
