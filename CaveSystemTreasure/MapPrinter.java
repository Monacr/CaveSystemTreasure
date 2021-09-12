// Imports for map functionality
import java.util.*;

// Prints out a map of the cave
public class MapPrinter {
   // Color strings to add color to console printing
   public static final String ANSI_RESET = "\u001B[0m";
   public static final String ANSI_PURPLE = "\u001B[35m";

   // RoomNode representing the overall cave
   private RoomNode cave;
   // Player representing the user
   private Player player;
   // Length of the longest room type
   public static final int LONGEST = 10;

   // Takes in a RoomNode for the cave and Player for the user and constructs a map for them
   public MapPrinter(RoomNode cave, Player player){
      this.cave = cave;
      this.player = player;
   }

   // Prints out whole cave in "horizontal" format.
   // The data of the room is printed to the distance right associated with its depth
   // (i.e. the 2nd level has 10 spaces before it, 3rd level has 20 spaces before it, etc.).
   // Rooms are printed below their parent room (so the left of the start will always be the
   // second line with 10 spaces before it, than all of its children will be next, and after
   // its children, the right of the start will print with 10 spaces before it).
   public void printRecursive(){
      printRecursive(cave, 0);
   }

   // Private helper for printing
   private void printRecursive(RoomNode node, int depth){
      if(node != null && node.seenByPlayer){
         spaces(depth * LONGEST);
         if(node.equals(player.getPos())) {
            System.out.println(ANSI_PURPLE + ">>" + ANSI_RESET + node.data.getRoomType() + ANSI_PURPLE + "<<" + ANSI_RESET);
         } else {
            System.out.println(node.data.getRoomType());
         }
         printRecursive(node.left, depth + 1);
         printRecursive(node.right, depth + 1);
      }
   }

   // Testing Purposes- Prints the entire map at once instead of just what is seen by the player
   // public void printRecursiveTest(){
   //    printRecursive(cave, 0);
   // }

   // private void printRecursiveTest(RoomNode node, int depth){
   //    if(node != null){
   //       spaces(depth * LONGEST);
   //       if(node.equals(player.getPos())) {
   //          System.out.println(">>" + node.data.getRoomType()+ "<<");
   //       } else {
   //          System.out.println(node.data.getRoomType());
   //       }
   //       printRecursive(node.left, depth + 1);
   //       printRecursive(node.right, depth + 1);
   //    }
   // }

   // Takes in an int (num) and prints num spaces
   public void spaces(int num){
      for(int i = 0; i < num; i++){
         System.out.print(" ");
      }
   }
}
