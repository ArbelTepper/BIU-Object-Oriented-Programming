// Arbel Tepper 209222272

import EX3.GameLevel;
import EX6.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass5Game class represents the game as developed as far as assignment 5.
 */
public class Ass5Game {
    /**
     * This method creates a new instance of the game, initializes it,
     * and then starts it.
     */
    public static void startGame() {
        LevelOne levelOne = new LevelOne();
        LevelTwo levelTwo = new LevelTwo();
        LevelThree levelThree = new LevelThree();
        List<LevelInformation> levels = List.of(levelOne, levelTwo, levelThree);
        GameFlow gameFlow = new GameFlow();
        gameFlow.runLevels(levels);
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
