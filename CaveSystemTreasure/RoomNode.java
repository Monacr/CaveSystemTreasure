// An RoomNode represents a single node in a binary tree
public class RoomNode {
    // Room representing the room of the current node
    public Room data;
    // The left child node
    public RoomNode left;
    // The right child node
    public RoomNode right;
    // Boolean determining if the room has been seen to update the map
    public boolean seenByPlayer;
    // RoomNode representing the parent node
    private RoomNode parent;

    // post: Constructs a leaf node with given data
    public RoomNode(Room data) {
        this(data, null, null);
    }

    // post: Constructs a leaf or branch node with given data and links
    public RoomNode(Room data, RoomNode left, RoomNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
        seenByPlayer = false;
    }

    // post: Constructs a leaf or branch node with given data and links
    public RoomNode(Room data, RoomNode left, RoomNode right, RoomNode parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
        seenByPlayer = false;
    }

    // post: Constructs a leaf or branch node with given data and links
    public RoomNode(Room data, RoomNode parent) {
        this.data = data;
        this.parent = parent;
        seenByPlayer = false;
    }

    // Sets this nodes seenByPlayer value to true if the player has visited it
    public void seenByPlayer() {
        this.seenByPlayer = true;
    }

    // Returns the parent node
    public RoomNode getParent(){
        return parent;
    }
}
