package org.cis120.fifteenthirtysix;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * This class holds the primary game logic for how different objects interact
 * with one another.
 */
public class Board extends JPanel {

    private FifteenThirtySix fts; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 300;
    public static final int BOARD_HEIGHT = 300;

    /**
     * Initializes the game board.
     */
    public Board(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area.
        setFocusable(true);

        fts = new FifteenThirtySix(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        // This key listener "slides" the squares of the board in the
        // direction of the pressed key by calling the
        // corresponding swipe method
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    fts.playTurn(0);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    fts.playTurn(1);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    fts.playTurn(2);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    fts.playTurn(3);
                }
            }

            public void keyReleased(KeyEvent e) {
                updateStatus(); // updates the status JLabel
                repaint(); // repaints the board to reflect the changes
            }
        });
    }

    /**
     * Resets the game to its initial state.
     */
    public void reset() {
        fts.reset();
        updateStatus();
        repaint();

        // Makes sure this component has keyboard focus
        requestFocusInWindow();
    }

    /**
     * Returns the game to its previous state.
     */
    public void undo() {
        fts.undo();
        updateStatus();
        repaint();

        // Makes sure this component has keyboard focus
        requestFocusInWindow();
    }

    /**
     * Pauses/resumes the game
     */
    public void pause() {
        fts.pause();
        updateStatus();
        repaint();

        // Makes sure this component has keyboard focus
        requestFocusInWindow();
    }

    /**
     * Provides game instructions
     */
    public void instructions(JFrame frame) {
        JOptionPane.showMessageDialog(
                frame, // parent of dialog window
                "1536\n" + "Use the arrow keys to slide the numbers and " +
                        "combine numbers to get 1536.\n" + "The arrow key pressed will " +
                        "determine the direction that all of the numbers shift/merge." +
                        "\nTwo numbers can merge if they are identical.\n"
                        + "The undo button undoes the move.\n " + "The reset button resets the " +
                        "game randomly " +
                        "to a new state.\n" +
                        "The pause/resume button stops the game and then returns it to" +
                        " its state before. \nYou lose if the board is filled and you have not " +
                        "reached 1536.",
                "Instructions", // title of dialog
                JOptionPane.INFORMATION_MESSAGE // type of dialog
        );
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        if (fts.getIsPaused()) {
            status.setText("Paused");
        } else if (fts.checkWinner() == 0) {
            status.setText("Press an arrow key to swipe!");
        } else if (fts.checkWinner() == 1) {
            status.setText("YOU WIN :) Score:" + fts.getScore());
        } else if (fts.checkWinner() == 2) {
            status.setText(("YOU LOSE :( Score:" + fts.getScore()));
        }
    }

    /**
     * Draws the game board.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawState(g);
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

    /**
     * Draws the lines of the board.
     */
    public void drawBoard(Graphics g) {
        g.drawLine(100, 0, 100, 300);
        g.drawLine(200, 0, 200, 300);
        g.drawLine(0, 100, 300, 100);
        g.drawLine(0, 200, 300, 200);
    }

    /**
     * Draws the state of the board.
     */
    public void drawState(Graphics g) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int state = fts.getCell(i, j);
                if (state != 0) {
                    g.drawString(String.valueOf(state), 48 + 100 * j, 48 + 100 * i);
                }
            }
        }
    }
}
