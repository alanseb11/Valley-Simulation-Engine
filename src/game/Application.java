package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.npcs.*;
import game.behaviours.RandomBehaviourType;
import game.grounds.*;
import game.items.Talisman;
import game.items.plants.BloodroseSeed;
import game.items.plants.InheritreeSeed;
import game.utilities.FancyMessage;

/**
 * The main class to setup and run the game.
 * @author Adrian Kristanto
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Blight(),
                new Wall(), new Floor(), new Soil());

        List<String> Limgrave = Arrays.asList(
                "xxxx...xxxxxxxxxxxxxxxxxxxxxxx........xx",
                "xxx.....xxxxxxx..xxxxxxxxxxxxx.........x",
                "..........xxxx....xxxxxxxxxxxxxx.......x",
                "....xxx...........xxxxxxxxxxxxxxx.....xx",
                "...xxxxx...........xxxxxxxxxxxxxx.....xx",
                "...xxxxxxxxxx.......xxxxxxxx...xx......x",
                "....xxxxxxxxxx........xxxxxx...xxx......",
                "....xxxxxxxxxxx.........xxx....xxxx.....",
                "....xxxxxxxxxxx................xxxx.....",
                "...xxxx...xxxxxx.....#####.....xxx......",
                "...xxx....xxxxxxx....#___#.....xx.......",
                "..xxxx...xxxxxxxxx...#___#....xx........",
                "xxxxx...xxxxxxxxxx...##_##...xxx.......x",
                "xxxxx..xxxxxxxxxxx.........xxxxx......xx",
                "xxxxx..xxxxxxxxxxxx.......xxxxxx......xx");

        List<String> Limveld = Arrays.asList(
                ".............xxxx",
            "..............xxx",
            "................x",
            ".................",
            "................x",
            "...............xx",
            "..............xxx",
            "..............xxx",
            "..............xxx",
            ".............xxxx",
            ".............xxxx",
            "....xxx.....xxxxx",
            "....xxxx...xxxxxx");

        GameMap limgraveMap = new GameMap("Valley of the Inheritree", groundFactory, Limgrave);
        GameMap limveldMap = new GameMap("Limveld", groundFactory, Limveld);

        world.addGameMap(limgraveMap);
        world.addGameMap(limveldMap);

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Farmer", '@', 100, 200);
        world.addPlayer(player, limgraveMap.at(23, 10));
        player.addItemToInventory(new InheritreeSeed());
        player.addItemToInventory(new BloodroseSeed());
        player.addBalance(10000);


        TeleportationCircle limgraveCircle = new TeleportationCircle('A', "Teleportation Circle");
        limgraveCircle.setDestination(limveldMap, limveldMap.at(3, 3));

        TeleportationCircle limveldCircle = new TeleportationCircle('A', "Teleportation Circle");
        limveldCircle.setDestination(limgraveMap, limgraveMap.at(23, 10));

        limgraveMap.at(23, 13).setGround(limgraveCircle);
        limveldMap.at(3, 3).setGround(limveldCircle);

        // Game setup
        limgraveMap.at(24, 11).addItem(new Talisman());
        limgraveMap.at(23, 14).addActor(new OmenSheep());
        limgraveMap.at(1,3).addActor(new SpiritGoat());
        limgraveMap.at(29, 8).addActor(new Sellen());
        limgraveMap.at(9,10).addActor(new Kale());
        limgraveMap.at(26, 3).addActor(new Guts());
        limgraveMap.at(15,11).addActor(new GoldenBeetle());

        // Testing for random behaviours
        SpiritGoat randomGoat = new SpiritGoat();
        randomGoat.setBehaviour(new RandomBehaviourType());

        SpiritGoat normalGoat = new SpiritGoat();

        limgraveMap.at(15,3).addActor(randomGoat);
        limgraveMap.at(15,4).addActor(normalGoat);

        world.run();
    }
}
