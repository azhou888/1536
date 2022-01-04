import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 */
public class RunFifteenThirtySix implements Runnable {
    public void run() {

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("1536");
        frame.setLayout(new GridLayout(3, 1));
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        final JLabel status = new JLabel("Press an arrow key to swipe!");
        status_panel.add(status);

        // Game board
        final Board board = new Board(status);

        // Reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());

        // Undo button
        final JButton undo = new JButton("Undo");
        undo.addActionListener(e -> board.undo());

        // Pause button
        final JButton pause = new JButton("Pause/Resume");
        pause.addActionListener(e -> board.pause());

        // Instructions button
        final JButton instructions = new JButton("Instructions");
        instructions.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                board.instructions(frame);
            }
        });

        // Top panel to contain the reset and undo buttons
        JPanel actionButtons = new JPanel();
        actionButtons.setLayout(new GridLayout(1, 2));
        actionButtons.add(reset);
        actionButtons.add(undo);

        // Top second row panel to contain the pause/resume and instructions button
        JPanel infoButtons = new JPanel();
        infoButtons.add(pause);
        infoButtons.add(instructions);

        // Panel that contains all the buttons
        JPanel combine = new JPanel();
        combine.setLayout(new BorderLayout());
        combine.add(actionButtons, BorderLayout.NORTH);
        combine.add(infoButtons, BorderLayout.SOUTH);

        // Layout all the components
        frame.setLayout(new BorderLayout());
        frame.add(board, BorderLayout.CENTER);
        frame.add(status_panel, BorderLayout.SOUTH);
        frame.add(combine, BorderLayout.NORTH);

        // Put on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        board.reset();
    }
}