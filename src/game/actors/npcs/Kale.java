package game.actors.npcs;

import java.util.*;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actions.PurchaseAction;
import game.actions.UnconsciousAction;
import game.behaviours.WanderBehaviour;
import game.capabilities.Status;
import game.capabilities.Threshold;
import game.interfaces.Merchant;
import game.interfaces.Monologuer;
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
public class Kale extends Actor implements Monologuer, Merchant {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private List<ConditionalMonologue> monologuePool = new ArrayList<>();
    private List<MerchantOffer> offerings = new ArrayList<>();

    /**
     * Constructor.
     */
    public Kale() {
        super("Kale", 'k', 200);
        behaviours.put(1, new WanderBehaviour());

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
     * Determines the action Sellen will perform on their turn.
     *
     * @param actions    A collection of possible actions for the Sellen
     * @param lastAction The action the Sellen took last turn. Can be used for multi-turn actions.
     * @param map        The map containing the Sellen
     * @param display    The I/O object to which messages may be written
     * @return The action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            return new UnconsciousAction();
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) return action;
        }
        
        // If no action is found, return a DoNothingAction
        return new DoNothingAction();
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
        ActionList actions = new ActionList();
        
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new ListenAction(this));

            for (MerchantOffer offer : offerings) {
                actions.add(new PurchaseAction(offer, this, otherActor));
            }
        }

        return actions;
    }

    /**
     * Returns the monologue pool of Kale.
     *
     * @return A list of ConditionalMonologue objects representing Kale's monologues
     */
    @Override
    public List<ConditionalMonologue> getMonologuePool() {
        return monologuePool;
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
