import javax.swing.*;

public class Game {

    // Initializes runnable 1536
    public static void main(String[] args) {
        Runnable game = new RunFifteenThirtySix();
        SwingUtilities.invokeLater(game);
    }
}
