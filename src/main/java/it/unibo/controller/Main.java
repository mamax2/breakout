package it.unibo.controller;


import javax.swing.SwingUtilities;
import it.unibo.view.Menu;
/**
 * this is the main class.
 */
public class Main {

    /**Main class.
     * @param args args
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Menu menuPanel = new Menu();
            }
        });
    }
}
