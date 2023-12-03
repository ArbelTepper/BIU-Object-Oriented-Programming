//Arbel Tepper 209222272
package EX6;

import EX3.GameLevel;
import EX5.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.List;

import static EX3.GameLevel.GUI_HEIGHT;
import static EX3.GameLevel.GUI_WIDTH;

/**
 * The GameFlow class handles the flow of the game, including initializing and running levels,
 * and keeping track of the score.
 */
public class GameFlow {
    private Counter score;
    private AnimationRunner runner;
    private GUI gui;
    private KeyboardSensor keyboard;
    /**
     * Constructs a new GameFlow object.
     * Initializes the GUI, score counter, animation runner, and keyboard sensor.
     */
    public GameFlow() {
        this.gui = new GUI("Game", GUI_WIDTH, GUI_HEIGHT);
        this.score = new Counter(0);
        this.runner = new AnimationRunner(this.gui, 60, new Sleeper());
        this.keyboard = this.gui.getKeyboardSensor();
    }
    /**
     * Runs the list of levels provided.
     * For each level, it creates a new GameLevel object, initializes it,
     * and runs the level until there are no remaining balls or blocks.
     * Displays the appropriate screen (win or lose) at the end of the game.
     *
     * @param levels the list of level information objects
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            Counter remainedBalls = new Counter(levelInfo.numberOfBalls());
            Counter remainedBlocks =
                    new Counter(levelInfo.numberOfBlocksToRemove());
            GameLevel level = new GameLevel(levelInfo, this.score,
                    this.runner, this.gui, this.keyboard, remainedBlocks,
                    remainedBalls);

            level.initialize();

            while (remainedBalls.getValue() > 0 && remainedBlocks.getValue() > 0) {
                level.run();
            }

            if (remainedBalls.getValue() == 0) {
                LoseScreen loseScreen = new LoseScreen(this.keyboard,
                        this.score);
                KeyPressStoppableAnimation lose =
                        new KeyPressStoppableAnimation(this.keyboard, "space", loseScreen);
                    this.runner.run(lose);
                    this.gui.close();
                    return;
            }
        }
        WinScreen winScreen = new WinScreen(this.keyboard,
                this.score);
        KeyPressStoppableAnimation win =
                new KeyPressStoppableAnimation(this.keyboard, "space", winScreen);
        this.runner.run(win);
        this.gui.close();
    }
}
