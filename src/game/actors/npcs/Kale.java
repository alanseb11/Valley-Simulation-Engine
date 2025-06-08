package game.actors.npcs;

import java.util.*;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.actors.npcs.types.MonologuingNPC;
import game.capabilities.Status;
import game.capabilities.Threshold;
import game.interfaces.Merchant;
import game.interfaces.PurchaseEffect;
import game.monologueconditions.ConditionalMonologue;
import game.monologueconditions.DefaultCondition;
import game.monologueconditions.EmptyInventoryCondition;
import game.monologueconditions.SurroundingCapabilityCondition;
import game.monologueconditions.WalletCondition;
import game.purchaseeffects.IncreaseMaxEffect;
import game.purchaseeffects.MerchantOffer;
import game.purchaseeffects.RestoreEffect;
import game.weapons.Broadsword;
import game.weapons.DragonslayerGreatsword;

/**
 * Class representing the Kale NPC.
 */
public class Kale extends MonologuingNPC implements Merchant {
    private List<MerchantOffer> offerings = new ArrayList<>();

    /**
     * Constructor.
     */
    public Kale() {
        super("Kale", 'k', 200);

        // Initialise monologue pool
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "A merchant's life is a lonely one. But the roads... they whisper secrets to those who listen."));
        monologuePool.add(new ConditionalMonologue(new WalletCondition(500, Threshold.BELOW), "Ah, hard times, I see. Keep your head low and your blade sharp."));
        monologuePool.add(new ConditionalMonologue(new EmptyInventoryCondition(), "Not a scrap to your name? Even a farmer should carry a trinket or two."));
        monologuePool.add(new ConditionalMonologue(new SurroundingCapabilityCondition(Status.CURSED), "Rest by the flame when you can, friend. These lands will wear you thin."));

        // Initialise merchant offerings
        offerings.add(new MerchantOffer(this, new Broadsword(), 150, 
        new ArrayList<PurchaseEffect>(List.of(new IncreaseMaxEffect(BaseActorAttributes.STAMINA, 30)))
        ));

        offerings.add(new MerchantOffer(this, new DragonslayerGreatsword(), 1700, 
        new ArrayList<PurchaseEffect>(List.of(new RestoreEffect(BaseActorAttributes.STAMINA, 20)))
        ));

    }

    /**
     * Returns the list of actions that other actors can perform on Sellen.
     *
     * @param otherActor The Actor that is interacting with Kale
     * @param direction  The direction in which the action is being performed
     * @param map        The map containing the Actor
     * @return An ActionList containing the actions that can be performed on Kale
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            for (MerchantOffer offer : offerings) {
                actions.add(new PurchaseAction(offer, this));
            }
        }

        return actions;
    }

    /**
     * Returns the offerings offered by Kale.
     *
     * @return A list of MerchantOffer objects representing the items available for purchase
     */
    @Override
    public List<MerchantOffer> getOfferings() {
        return offerings;
    }

}
