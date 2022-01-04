import java.io.*;
import java.nio.file.Paths;
import java.util.LinkedList;

public class PauseResume {
    private final LinkedList<int[][]> turns;
    private final LinkedList<String> stringStates;
    private final String filepath;

    // initializes
    public PauseResume(LinkedList<int[][]> turns, String filepath) {
        this.turns = turns;
        this.filepath = filepath;
        stringStates = new LinkedList<>();
    }

    // Calls methods to write the state to a txt file
    public void writeStatesToFile() {
        writeStringArrToFile(intsToString(turns));
    }

    /**
     * writes LinkedList of strings to a txt file
     *
     * @param states LinkedList containing the states
     */
    public void writeStringArrToFile(LinkedList<String> states) {
        if (states == null) {
            return;
        }
        if (states.size() == 0) {
            return;
        }
        File file = Paths.get(filepath + "paused_states.txt").toFile();
        BufferedWriter bw;
        for (int i = 0; i < states.size(); i++) {
            boolean append = i != 0;
            FileWriter fw;
            try {
                fw = new FileWriter(file, append);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            bw = new BufferedWriter(fw);
            try {
                bw.write(states.get(i) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } finally {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Helper method to convert the 2DArray LinkedList into
     * a LinkedList of strings formatted with a comma separating
     * the elements within an array
     *
     * @param turns LinkedList of the turns
     * @return LinkedList of strings
     */
    public LinkedList<String> intsToString(LinkedList<int[][]> turns) {
        int[][] arr;
        for (int[][] turn : turns) {
            arr = turn;
            String digit = "";
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 2 && j == 2) {
                        digit = digit + arr[i][j];
                    } else {
                        digit = digit + arr[i][j] + ",";
                    }
                }
            }
            stringStates.add(digit);
        }
        return stringStates;
    }

    /**
     * Method that parses the data from a txt file and returns a LinkedList
     * with the data formatted into 2DArrays
     *
     * @return LinkedList of 2d int arrays
     */
    public LinkedList<int[][]> readStringsFromFile() {
        BufferedReader br;
        LinkedList<int[][]> resumeState = new LinkedList<>();
        try {
            br = new BufferedReader(new FileReader(filepath + "paused_states.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        try {
            br.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String s;
        boolean next = true;
        while (next) {
            try {
                if (((s = br.readLine()) != null)) {
                    resumeState.addLast(formatText(s));
                } else {
                    next = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LinkedList<int[][]> newArr = new LinkedList<>();
        for (int i = resumeState.size() - 1; i >= 0; i--) {
            newArr.addFirst(resumeState.get(i));
        }
        return newArr;
    }

    /**
     * Helper method to convert a string into a 2D Array
     *
     * @param text an unformatted string
     * @return integer 2D array
     */
    public int[][] formatText(String text) {
        String[] temp = text.split(",");
        int[][] arr = new int[3][3];
        int n = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(temp[n]);
                n++;
            }
        }
        return arr;
    }
}