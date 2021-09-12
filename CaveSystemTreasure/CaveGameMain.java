// To run this program put 
// javac CaveGameMain.java && java CaveGameMain; rm *.class
// into the console

// import for Map and Scanner functionality
import java.util.*;
// Represents the whole of the Text Cave Game
public class CaveGameMain {

    // Prints intro text to introduce the game and its rules
    public static void intro(){
        System.out.println("Welcome to the Cave Game. This game generates a cave that contains");
        System.out.println("a set of rooms where each room can have two or less rooms branching");
        System.out.println("out of it (until the end of the cave). In these rooms there may be");
        System.out.println("nothing, or there may be a colored key or a colored door. To unlock");
        System.out.println("the colored doors you must find the key of the same color. The goal");
        System.out.println("is to find the treasure, which requires the yellow key to unlock.");
        System.out.println();
        System.out.println("You can traverse the cave by inputting: ");
        System.out.println("\"left\" to move to the left cave connected to your current room.");
        System.out.println("\"right\" to move to the right cave connected to your current room.");
        System.out.println("\"up\" to move back to the previous room.");
        System.out.println("input \"exit\" to quit the game at any time.");
        System.out.println();
        System.out.println("As you traverse the cave a map will display showing the rooms you");
        System.out.println("have been in. The further right the rooms are, the deeper they are");
        System.out.println("in the cave. Left rooms will appear on top of right rooms if both are");
        System.out.println("visible, displaying under the room that led to them, but if only one");
        System.out.println("is visible it will appear directly under the parent node. The arrows");
        System.out.println("around a room show the room you are currently in.");
        System.out.println();
        System.out.println("If you are ever in doubt about where to move, check the possible next");
        System.out.println("moves that are printed in red under your inventory.");
        System.out.println();
        System.out.println("-------------------------------------------------------------");
    }

    // Prints the direction options for the player
    public static void moveOn(){
        System.out.println("Type \"left\" to explore down a left tunnel, \"right\" to explore down a right tunnel,");
        System.out.println("or \"up\" to move back.");
    }

    // Runs the game
    public static void main(String [] args) {

        // create scanner for user input
        Scanner input = new Scanner(System.in);

        RandomRooms rand = new RandomRooms();

        RoomNode cave = rand.getStart();

        Player testPlayer = new Player(cave);

        MapPrinter map = new MapPrinter(cave, testPlayer);

        cave.seenByPlayer();

        intro();
        String direction = "";
        map.printRecursive();
        System.out.println();
        System.out.println(testPlayer.getCurrentRoom());
        testPlayer.printKeys();
        
        while(testPlayer.win() && !direction.equals("exit")) {
            testPlayer.printDirectionOptions();
            direction = input.next();
            System.out.println();
            System.out.println("-------------------------------------------------------------");
            testPlayer.gameMove(direction);
            testPlayer.getPos().seenByPlayer();
            System.out.println("Map:");
            map.printRecursive();
            System.out.println();
            System.out.println(testPlayer.getCurrentRoom());
            if(testPlayer.win()) {
                testPlayer.printKeys();
            }
        }
        if(!testPlayer.win()) {
            System.out.println("---------------------------You Won---------------------------");
        }
    }
}
