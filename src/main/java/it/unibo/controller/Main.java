package it.unibo.controller;

import javax.swing.SwingUtilities;
import it.unibo.view.Menu;
import it.unibo.model.ScoreboardImpl;

/**
 * this class starts the game.
 */
public final class Main {

    /**
     * Main class.
     * 
     * @param args args
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ScoreboardImpl t = new ScoreboardImpl();
                t.top10();
                new Menu();
            }
        });
    }

    private Main() {
    }
}
