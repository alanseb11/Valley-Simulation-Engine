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
import game.interfaces.Merchant;
import game.interfaces.Monologuer;
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
public class Sellen extends Actor implements Monologuer, Merchant {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private List<ConditionalMonologue> monologuePool = new ArrayList<>();
    private List<MerchantOffer> offerings = new ArrayList<>();

    /**
     * Constructor.
     */
    public Sellen() {
        super("Sellen", 's', 150);
        behaviours.put(1, new WanderBehaviour());

        // Initialise monologue pool
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever."));
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "You sense it too, don't you? The Glintstone hums, even now."));

        // Initialise merchant offerings
        offerings.add(new MerchantOffer(this, new Broadsword(), 100, new IncreaseMaxEffect(BaseActorAttributes.HEALTH, 20)));
        offerings.add(new MerchantOffer(this, new Katana(), 500, new SpawnEffect(new OmenSheep())));

        // TO ADD GOLDEN BEETLE ONCE POSSIBLE
        offerings.add(new MerchantOffer(this, new DragonslayerGreatsword(), 1500, new SpawnEffect(null)));
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
     * @param otherActor The Actor that is interacting with Sellen
     * @param direction  The direction in which the action is being performed
     * @param map        The map containing the Actor
     * @return An ActionList containing the actions that can be performed on Sellen
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
     * Returns the monologue pool of Sellen.
     *
     * @return A list of ConditionalMonologue objects representing Sellen's monologues
     */
    @Override
    public List<ConditionalMonologue> getMonologuePool() {
        return monologuePool;
    }

    /**
     * Returns the list of merchant offerings.
     *
     * @return A list of MerchantOffer objects representing Sellen's offerings
     */
    @Override
    public List<MerchantOffer> getOfferings() {
        return offerings;
    }

}
