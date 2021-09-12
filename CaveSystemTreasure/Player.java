// import for Map functionality
import java.util.*;

// Allows the client to create a player object that traverses the game.
public class Player {

   //Color codes to change console output color.
   public static final String ANSI_RESET = "\u001B[0m";
   public static final String RED = "\u001B[91m";
   public static final String BLUE = "\u001B[94m";
   public static final String GREEN = "\u001B[92m";
   public static final String YELLOW = "\u001B[93m";

   private RoomNode surface;

   private RoomNode currentPos;

   private boolean[] inv;

   // creates a player object that can move through the
   // given cave.
   public Player(RoomNode cave) {
      this.surface = cave;
      this.currentPos = surface;
      this.inv = new boolean[4];
   }

   // prints the keys the player currently holds. If the player doesn't hold a key
   // the inventory slot for that key will display "empty".
   public void printKeys() {
      String[] keys = new String[]{RED + "red key" + ANSI_RESET, BLUE + "blue key" + ANSI_RESET, GREEN + "green key" + ANSI_RESET, YELLOW +"yellow key" + ANSI_RESET};
      if(currentPos.data.getRoomType().contains("treasure") && !inv[3]) {
         System.out.println("Go get the yellow key to win!");
      }
      System.out.print("Inventory: ");
      for(int i = 0; i < inv.length; i++) {
         if(inv[i]) {
            System.out.print("| ");
            System.out.print(keys[i]);
            System.out.print(" | ");
         } else {
            System.out.print("| empty | ");
         }
      }
      System.out.println();
   }

   // Checks if this Player object won the game. Returns false if they have won.
   public boolean win() {
      if(currentPos.data.getRoomType().contains("treasure") && inv[3]) {
         return false;
      } else if(currentPos.data.getRoomType().contains("treasure") && !inv[3]) {
         return true;
      }
      return true;
   }


   // updates the current position of player in the cave system based
   // on the given direction. Reacts approrpiatly to door and keys
   public void gameMove(String direction) {
      if(isdoor(currentPos.data) && !direction.toLowerCase().equals("up")) { //&& (direction.toLowerCase().equals("up") || direction.toLowerCase().equals("right"))) {
         if(invHasKey(currentPos.data)) {
            move(direction);
         } else {
            System.out.println("Passing through this door requires a " + currentPos.data.getRoomColor() + " key. Go find it!");
         }
      } else {
         RoomNode save = currentPos;
         addKey(currentPos.data);
         move(direction);
         if(save.data.getRoomType().contains("key")){
            save.data.setEmpty();
         }  
      }
   }

   // checks if the given room is a key and adds it to inventory if it is.
   private void addKey(Room check) {
      int checkColor = check.getRoomType().indexOf(" ") - 3;
      if(check.getRoomType().contains("key")) {
         inv[checkColor] = true;
      }
   }

   // checks if the given room is a door
   private boolean isdoor(Room check) {
      return check.getRoomType().contains("door");
   }

   // checks if the users inventory has a key for the given room  
   private boolean invHasKey(Room check) {
      int checkColor = check.getRoomType().indexOf(" ") - 3;
      return inv[checkColor];
   }

   // This method is for debugging! Call gameMove (method above) to use
   // game mechanics like doors and 
   // updates the current position of player in the cave system based
   // on the given direction. Ignores doors and keys.
   private void move(String direction) {
      if(direction.toLowerCase().equals("up") || direction.toLowerCase().equals("left") ||
         direction.toLowerCase().equals("right")) {
               this.currentPos = this.moveToDirecion(direction);
      } else {
         System.out.println("Please input either " + this.directionOptions());
      }
   }

   // Returns the approrpiate node adjacent to the current
   // position of this Player based on the direction given.
   private RoomNode moveToDirecion(String direction){
      if(direction.toLowerCase().equals("up")) {
         if(!currentPos.equals(surface)) {
            return this.currentPos.getParent();
         }
         System.out.println("You are at the surface of the cave and cannot go any higher!");
         return currentPos;
      } else if (currentPos.left != null && direction.toLowerCase().equals("left")) {
         return currentPos.left;
      } else if (currentPos.right != null && direction.toLowerCase().equals("right")) {
         return currentPos.right;
      } else {
         System.out.println(RED + "You cannot move in this direction!" + ANSI_RESET);
         return currentPos;
      }
   }

   // prints out the direction options from this room and returns the room.
   // there are a lot of spaces so users will read this line because they are lazy
   public void printDirectionOptions() {
      System.out.println(RED + "From here you can move " + this.directionOptions() + ANSI_RESET);
   }

   // returns the possible directions player can travel based on currentPos.
   private String directionOptions() {
      String options = "";
      if(!currentPos.equals(surface)) {
         options += "\"up\" ";
         if(!isdoor(this.getCurrentRoom()) || invHasKey(currentPos.data)) {
            if(currentPos.left != null || currentPos.right != null) {
               options += "or ";
            }
         }
      }
      if(!isdoor(this.getCurrentRoom()) || invHasKey(currentPos.data)) {
         if(currentPos.left != null) {
            options += "\"left\" ";
            if(currentPos.right != null) {
               options += "or ";
            }
         }
         if(currentPos.right != null) {
            options += "\"right\" ";
         }
      }
      return options;
   }

   // returns the current room the player occupies
   public Room getCurrentRoom() {
      return this.currentPos.data;
   }

   // returns the current position the player occupies
   public RoomNode getPos() {
      return this.currentPos;
   }
}
