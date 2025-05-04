package game.capabilities;

/**
 * Enum class to represent different types of thresholds for comparison.
 * This can be used in various conditions, such as checking if a value is above or below a certain threshold.
 */
public enum Threshold {
    ABOVE {
        @Override
        public boolean compare(int value, int threshold) {
            return value > threshold;
        }
    },
    BELOW {
        @Override
        public boolean compare(int value, int threshold) {
            return value < threshold;
        }
    };

    /**
     * Abstract method to compare a value with a threshold.
     *
     * @param value    The value to compare
     * @param threshold The threshold to compare against
     * @return true if the comparison is valid, false otherwise
     */
    public abstract boolean compare(int value, int threshold);
}
