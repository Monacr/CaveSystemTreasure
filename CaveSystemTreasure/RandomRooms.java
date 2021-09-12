// javac RandomRooms.java && java RandomRooms; rm *.class

// import for Map functionality
import java.util.*;
// Import for Supplier functionality
import java.util.function.*;

// Randomly generates a cave system as a binary tree made up of a start room, keys, doors, and treasure
public class RandomRooms {
    // The production rules of the cave
    private Map<String, String[]> roomMap;
    // The cave system binary tree
    private RoomNode roomRoot;

    // Takes a cave rule set and constructs a RandomRooms
    public RandomRooms(Cave cave){ 
        this.roomMap = cave.productionRules.get();
        this.roomRoot = null;
    }
    // Constructs a RandomRooms
    public RandomRooms(){ 
        this.roomMap = Cave.ROOM.productionRules.get();
        this.roomRoot = null;
    }

    // Takes a cave system binary tree and returns the cave system binary tree with a treasure
    // randomly placed at one end of the tree
    public RoomNode finish(RoomNode temp, RoomNode parent) {
        if(temp == null) {
            temp = new RoomNode(new Room("Treasure"), parent);
        } else if((int)(Math.random() * 1.5) == 1) {
            temp.left = finish(temp.left, temp);
        } else {
            temp.right = finish(temp.right, temp);
        }
        return temp;
    }

    // Takes a cave system binary tree and tree length integer and returns a randomly generated 
    // cave system binary tree based on the given production rules with six levels
    public RoomNode generate(int length, RoomNode node, RoomNode nodeParent) {
        if(!roomMap.containsKey(node.data.getRoomType()) || length == 0) {
            return new RoomNode(new Room(node.data.getRoomType()), nodeParent);
        } else if(length == 5){
            roomRoot = node;
            roomRoot.left = generate(length - 1, new RoomNode(new Room("red door"), roomRoot), node);
            roomRoot.right = generate(length - 1, new RoomNode(new Room("red key"), roomRoot), node);
        }  else {
            int randomChoice = (int)(Math.random() * 1.5); 
            int randomOption = (int)(Math.random() * 1.5); 
            if(randomChoice == randomOption) {
                node.left = generate(length - 1, new RoomNode(new Room(roomMap.get(node.data.getRoomType())[randomChoice]), node), node);
            }
            node.right = generate(length - 1, new RoomNode(new Room(roomMap.get(node.data.getRoomType())[1 - randomChoice]), node), node);
        }
        return node;
    }

    // Returns a randomly generated cave system binary tree made up of a start rooms, keys, doors, and treasure
    public RoomNode getStart(){
        roomRoot = generate(5, new RoomNode(new Room("start")), null);
        finish(roomRoot, null);
        return roomRoot;
    }

    // Represents the production rules of a cave system binary tree
    public enum Cave {
        ROOM(() -> {
            Map<String, String[]> result = new TreeMap<>();
            result.put("start", "red door, red key".split(", "));
            result.put("red door", "blue key, empty room".split(", "));
            result.put("red key", "blue door, empty room".split(", "));
            result.put("blue door", "green key, empty room".split(", "));
            result.put("empty room", "empty room, yellow key".split(", "));
            result.put("green door", "empty room, empty room".split(", "));
            result.put("blue key", "empty room, green door".split(", "));
            result.put("yellow key", "green key, blue key".split(", "));
            return result;
        });

        // The production rules of the cave system 
        public final Supplier<Map<String, String[]>> productionRules;

        // Takes a map of the production rules and constructs a Cave
        private Cave(Supplier<Map<String, String[]>> productionRules) {
            this.productionRules = productionRules;
        }
    }
}
