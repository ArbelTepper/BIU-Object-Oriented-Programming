// Arbel Tepper 209222272
package EX5;

/**
 * The type Counter represents a counting object.
 */
public class Counter {
    /**
     * The Value.
     */
    private int value;

    /**
     * Instantiates a new Counter.
     *
     * @param startValue the start value
     */
    public Counter(int startValue) {
        this.value = startValue;
    }

    /**
     * Increase the current value held by the counter.
     *
     * @param number the number which is to be added to the counter.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decrease the current value held by the counter.
     *
     * @param number the number which is to be reduced from the counter.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * returns the value of the counter.
     *
     * @return the current value of the counter.
     */

    public int getValue() {
        return this.value;
    }
}
