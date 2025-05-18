package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import game.FancyMessage;
import game.actions.UnconsciousAction;
import game.capabilities.Status;
import game.weapons.BareFist;

/**
 * Class representing the Player.
 * 
 * The Player is a controllable actor in the game. It has attributes such as health and stamina,
 * and can perform actions based on user input via a menu.
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     * @param stamina     Player's starting number of stamina
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.PLAYER);
        this.addCapability(Status.FOLLOWABLE);
        this.setIntrinsicWeapon(new BareFist());
        this.addBalance(10000);
    }

    /**
     * Determines the action the Player will perform on their turn.
     * 
     * If the Player is in the middle of a multi-turn action, the next action in the sequence
     * will be returned. Otherwise, the Player is presented with a menu of possible actions
     * to choose from.
     * 
     * @param actions    A collection of possible actions for the Player
     * @param lastAction The action the Player took last turn. Can be used for multi-turn actions.
     * @param map        The map containing the Player
     * @param display    The I/O object to which messages may be written
     * @return The action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Check if the Player is conscious
        if (!this.isConscious()) {
            // Oh no! "YOU DIED" message
            for (String line : FancyMessage.YOU_DIED.split("\n")) {
                display.println(line);
                try {
                    Thread.sleep(200);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            return new UnconsciousAction();
        }

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Returns a string representation of the Player.
     * 
     * The string includes the Player's name, current health, maximum health,
     * current stamina, and maximum stamina.
     * 
     * @return A string representation of the Player
     */
    @Override
    public String toString() {
        String playerName = super.name;
        int currentHealth = getAttribute(BaseActorAttributes.HEALTH);
        int maxHealth = getAttributeMaximum(BaseActorAttributes.HEALTH);
        int currentStamina = getAttribute(BaseActorAttributes.STAMINA);
        int maxStamina = getAttributeMaximum(BaseActorAttributes.STAMINA);

        return String.format(
            "%s [HP: %d/%d | ST: %d/%d]",
            playerName, currentHealth, maxHealth, currentStamina, maxStamina
        );
    }

}
