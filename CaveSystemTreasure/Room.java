// Represents the rooms in the cave
public class Room {
    
    // Room types would be start, empty, red key, green key, blue key, yellow key, red door, green door, blue door, or treasure.
    private String roomType;

    // Takes in a String which determines the room type
    public Room(String roomType) {
        this.roomType = roomType;
    }

    // Returns the type of room
    public String getRoomType() {
        return this.roomType.toLowerCase();
    }

    // Returns the color of the room
    public String getRoomColor() {
        if(!this.getRoomType().equals("empty")) {
            return this.roomType.toLowerCase().substring(0, this.roomType.indexOf(" "));
        }
        return "empty";
    }

    // Sets type to empty (after door is opened or key is taken for example)
    public void setEmpty(){
        this.roomType = "empty " + this.getRoomColor() + " room";
    }

    // Returns the text that would be associated with the player entering that type of room (just basic text for now, can change)
    public String toString(){
        if(getRoomType().equals("start")){
            return "You are at the mouth of the cave.";
        }
        else if(getRoomType().contains("key")){
            return "You are in a room with a " + getRoomType() + ". You take the " + getRoomType() + ". \nThe room is now an empty " + this.getRoomColor() + " room.";
        }
        else if(getRoomType().contains("door")){
            return "You are in a room with a " + getRoomType() + ".";
        }
        else if(getRoomType().equals("treasure")){
            return "You found the treasure!";
        } else {
            return "You are in an " + this.getRoomType() + ".";
        }

    }
}
