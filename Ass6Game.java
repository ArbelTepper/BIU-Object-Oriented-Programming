// Arbel Tepper 209222272

import EX6.GameFlow;
import EX6.LevelInformation;
import EX6.LevelOne;
import EX6.LevelThree;
import EX6.LevelTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass6Game class represents the game as developed as far as assignment 6.
 */
public class Ass6Game {
    /**
     * The main method of the program. If no command-line arguments are given
     * it starts hte game with the 3 different levels.
     * If hte command-line arguments match the number of levels existing, the
     * game will run with the corresponding levels.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LevelOne levelOne = new LevelOne();
        LevelTwo levelTwo = new LevelTwo();
        LevelThree levelThree = new LevelThree();
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length == 0) {
            levels = List.of(levelOne, levelTwo, levelThree);
        } else {
            for (int i = 0; i < args.length; i++) {
                try {
                    int levelNum = Integer.parseInt(args[i]);
                    if (levelNum == 1) {
                        levels.add(levelOne);
                    }
                    if (levelNum == 2) {
                        levels.add(levelTwo);
                    }
                    if (levelNum == 3) {
                        levels.add(levelThree);
                    }
                } catch (Exception e) {
                }
            }
        }
        if (levels.isEmpty()) {
            levels = List.of(levelOne, levelTwo, levelThree);
        }
        GameFlow gameFlow = new GameFlow();
        gameFlow.runLevels(levels);
    }
}
