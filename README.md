# CaveSystemTreasure
To run the program, find the file titled "CaveGameMain.java" and enter
javac CaveGameMain.java && java CaveGameMain; rm *.class
into the terminal. This will bring up text in the terminal explaining how to play the game.
A cave is randomly generated which contains the first room (Start) along with subsequent rooms
out of Start. Each room may have 1 or 2 rooms branching out of it until you reach the end of the
cave (where the rooms will all have 0 branches). A map will pop up showing where you are in the cave.
To start, it will just show Start, then as you progress it will update to show each room you have
explored. Your position is indicated with ">>   <<". On the map, Start will show on the far left
and the rooms branching out of it will show below it and to the right of it with the left room above
and the rooms branching out of it will show below it and to the right of it with the left room above
the right room. Each room will show in this way in relation to its parent room. Note: the map may
not display correctly if your monitor cannot fit all of the text on one line.

To move through the cave, you can input "left", "right", or "up", case insensitive. Left takes you
to the left branch of a room, right to the right branch, and up to the parent room (the one that took
you to the room you are in). At any time you can quit the game by typing "exit".

As you move through the cave you will find colored keys and colored doors. To get through the doors you
need the key of the associated color. There will be an inventory that displays in the terminal showing
what keys you currently have (or empty if there is not a key in that slot of the inventory). Once you
pick up the key in a room it will turn the room into an "empty (color) room". Doors will remain as doors
on the map when you go through them. The goal is to find the treasure at the end of the cave, which will
require a yellow key to open. The yellow key and the treasure may or may not be behind any other doors.
When you bring the yellow key to the treasure to open it, the game will display a congratulatory message
require a yellow key to open. The yellow key and the treasure may or may not be behind any other doors.
When you bring the yellow key to the treasure to open it, the game will display a congratulatory message
and end the game. To play again, enter the command back into the terminal.
