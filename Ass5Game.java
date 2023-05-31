// Arbel Tepper 209222272

import EX3.Game;

/**
 * Ass5Game class represents the game as developed as far as assignment 5.
 */
public class Ass5Game {
    /**
     * This method creates a new instance of the game, initializes it,
     * and then starts it.
     */
    public static void startGame() {
        Game game = new Game();
        game.initialize();
        game.run();
    }

    /**
     * This is the main method of the program. It simply calls the startGame()
     * method to start the game.
     *
     * @param args the command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        startGame();
    }
}
