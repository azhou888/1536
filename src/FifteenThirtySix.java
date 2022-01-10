/**
 * This class is a model for 1536.
 *
 * This model is completely independent of the view and controller.
 * This is in keeping with the concept of modularity! We can play
 * the whole game from start to finish without ever drawing anything
 * on a screen or instantiating a Java Swing object.
 *
 */

public class FifteenThirtySix {

    private static String filepath = "files/";

    private int[][] board;
    private int numTurns;
    private boolean gameOver;
    private Moves moves;
    private Swipe swipe;
    private PauseResume pr;
    private boolean isPaused;

    /**
     * Constructor sets up game state.
     */
    public FifteenThirtySix() {
        reset();
    }

    /**
     * playTurn allows players to play a turn. Returns true if the move is
     * successful. Returns false if the game is over, the game is paused,
     * or if the move was unsuccessful. If the turn is successful and the game
     * has not ended, the state is updated otherwise, it stays the same.
     *
     * @param turn integer associated with a specific move
     * @return whether the turn was successful
     */
    public boolean playTurn(int turn) {
        if (isPaused) {
            return false;
        }
        if (gameOver) {
            return false;
        }

        if (turn == 0 && !swipe.swipeLeft()) {
            return false;
        } else if (turn == 1 && !swipe.swipeRight()) {
            return false;
        } else if (turn == 2 && !swipe.swipeDown()) {
            return false;
        } else if (turn == 3 && !swipe.swipeUp()) {
            return false;
        } else {
            numTurns++;
            randomThree();
            moves.add(get2DArray());
            return true;
        }
    }

    /**
     * checkWinner checks whether the game has reached a win condition.
     *
     * @return 0 if the player has not won yet and 1 if the player has won and 2
     *         if the player loses
     */
    public int checkWinner() {
        // Checks to see if 1536 is on the board
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == 1536) {
                    return 1;
                }
            }
        }
        if (boardFull() && checkLose()) {
            return 2;
        }
        return 0;
    }

    /**
     * checkLose checks whether the game has reached losing condition
     *
     * @return true if the player has lost
     */
    public boolean checkLose() {
        int[][] tempArray = board;
        for (int i = 0; i < 3; i++) {
            int[] tempRow = tempArray[i];
            if (tempRow[0] == tempRow[1] || tempRow[1] == tempRow[2]) {
                return false;
            }
            for (int j = 0; j < 3; j++) {
                if (i == 0 || i == 2) {
                    if (tempRow[j] == tempArray[1][j]) {
                        return false;
                    }
                } else {
                    if (tempRow[j] == tempArray[0][j] || tempRow[j] == tempArray[2][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * boardFull checks whether the board is filled
     *
     * @return true if the board is all filled
     */
    private boolean boardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (getCell(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * getScore finds the highest number on the board
     *
     * @return an integer for the highest number
     */
    public int getScore() {
        int maxNum = getCell(0, 0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (getCell(i, j) > maxNum) {
                    maxNum = getCell(i, j);
                }
            }
        }
        return maxNum;
    }

    /**
     * randomThree adds a random three to the board
     *
     * @return an integer array where the first element is
     *         the row and the second element is the column
     *         of the position of the three that was randomly generated
     */
    private int[] randomThree() {
        if (!boardFull()) {
            int row = randomNumToCoordinate(Math.random());
            int col = randomNumToCoordinate(Math.random());
            while (getCell(row, col) != 0) {
                row = randomNumToCoordinate(Math.random());
                col = randomNumToCoordinate(Math.random());
            }
            setCell(3, row, col);
            int[] arr = new int[2];
            arr[0] = row;
            arr[1] = col;
            return arr;
        }
        return null;
    }

    /**
     * randomNumToCoordinate is a helper method that converts the
     * generated random number to a corresponding position on the board.
     *
     * @param randomNum double denoting a generated random number
     * @return the integer denoting a position
     */
    private int randomNumToCoordinate(double randomNum) {
        if (randomNum < 0.33) {
            return 0;
        } else if (randomNum < .66) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * reset the game state to start a new game
     */
    public void reset() {
        if (isPaused) {
            return;
        }
        board = new int[3][3];
        numTurns = 0;
        gameOver = false;
        moves = new Moves();
        swipe = new Swipe(this);
        randomThree();
        randomThree();
        moves.add(get2DArray());
    }

    /**
     * undo puts the game back to the state before if it exists.
     * If the game is paused, nothing occurs.
     */
    public void undo() {
        if (isPaused) {
            return;
        }
        if (numTurns >= 1) {
            moves.remove();
            board = moves.get();
            numTurns--;
        }
    }

    /**
     * pause stops the game state if isPaused is true and returns the state
     * to what it was before pause was called if isPaused is false and the
     * states exist. If gameOver is true, nothing happens.
     */
    public void pause() {
        if (gameOver) {
            return;
        }
        isPaused = !isPaused;
        if (isPaused) {
            board = new int[3][3];
            pr = new PauseResume(moves.getTurns(), filepath);
            pr.writeStatesToFile();
        } else {
            moves.set(pr.readStringsFromFile());
            board = moves.get();
            numTurns = moves.getTurns().size() - 1;
        }
    }

    /**
     * getIsPaused is a getter for whether the game is paused
     *
     * @return a boolean that is true if the game is paused
     */
    public boolean getIsPaused() {
        return isPaused;
    }

    /**
     * setBoard is a setter for the board state
     *
     * @param board a 2D Array of integers
     */
    public void setBoard(int[][] board) {
        this.board = board;
    }

    /**
     * getCell is a getter for the contents of the cell specified by the method
     * arguments.
     *
     *
     * @param r row to retrieve
     * @param c column to retrieve
     * @return an integer denoting the contents of the corresponding cell on the
     *         game board.
     */
    public int getCell(int r, int c) {
        return board[r][c];
    }

    /**
     * setCell is a setter for the contents of the cell specified by the method
     * arguments.
     *
     * @param newValue the new value
     * @param r        row to change
     * @param c        column to change
     */
    public void setCell(int newValue, int r, int c) {
        board[r][c] = newValue;
    }

    /**
     * get2DArray is a getter for the board state
     *
     * @return 2D array of integers
     */
    public int[][] get2DArray() {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    /**
     * getRow is a getter for a row in the board
     *
     * @param row integer denoting the row
     * @return integer array
     */
    public int[] getRow(int row) {
        return board[row];
    }

    /**
     * getCol is a getter for a column in the board
     *
     * @param col integer denoting the column
     * @return integer array
     */
    public int[] getCol(int col) {
        int[] coll = new int[3];
        for (int i = 0; i < 3; i++) {
            coll[i] = board[i][col];
        }
        return coll;
    }
}
