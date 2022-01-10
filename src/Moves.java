import java.util.LinkedList;

/**
 * Keeps track of the moves of the player during the game.
 */
public class Moves {
    private LinkedList<int[][]> turns;

    // initializes the LinkedList for the turns
    public Moves() {
        turns = new LinkedList<>();
    }

    /**
     * getTurns is a getter for the states throughout the game
     *
     * @return an int 2D array LinkedList containing the states after each turn
     */
    public LinkedList<int[][]> getTurns() {
        return turns;
    }

    /**
     * sets the LinkedList of states to a new value
     *
     * @param newArr the new int 2D array LinkedList
     */
    public void setStates(LinkedList<int[][]> newArr) {
        turns = newArr;
    }

    /**
     * get is a getter for the most recent turn's state
     *
     * @return an int 2D array LinkedList containing the states after each turn
     */
    public int[][] getStates() {
        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = turns.getLast()[i][j];
            }
        }
        return arr;
    }

    /**
     * removes the most recently added turn's state
     *
     * @return an int 2D array
     */
    public int[][] remove() {
        return turns.removeLast();
    }

    /**
     * adds a new state to the end of the LinkedList
     *
     * @param arr an int 2D array
     */
    public void add(int[][] arr) {
        turns.add(arr);
    }
}
