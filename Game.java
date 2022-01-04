package org.cis120;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game {

    //Initializes runnable 1536
    public static void main(String[] args) {
        Runnable game = new org.cis120.fifteenthirtysix.RunFifteenThirtySix(); // Set the game you
                                                                               // want to run
        // here
        SwingUtilities.invokeLater(game);
    }
}
