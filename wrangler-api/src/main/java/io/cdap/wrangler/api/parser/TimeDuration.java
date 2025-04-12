/**
 * Converts duration strings like "100ms", "5s", "2min" into milliseconds.
 */
public class TimeDuration {

    private final long milliseconds;

    /**
     * Parses a time duration string and converts it to milliseconds.
     * Supported units: ms, s, min.
     *
     * @param input the duration string (e.g., "100ms", "5s")
     */
    public TimeDuration(String input) {
        input = input.trim().toLowerCase();

        // Extract number and unit parts
        String numberPart = input.replaceAll("[^0-9]", "");
        String unitPart = input.replaceAll("[0-9]", "");

        if (numberPart.isEmpty() || unitPart.isEmpty()) {
            throw new IllegalArgumentException("Invalid time duration: " + input);
        }

        long number = Long.parseLong(numberPart);

        // Convert based on the unit (ms, s, min)
        switch (unitPart) {
            case "ms":
                this.milliseconds = number;
                break;
            case "s":
                this.milliseconds = number * 1000L;
                break;
            case "min":
                this.milliseconds = number * 60L * 1000L;
                break;
            default:
                throw new IllegalArgumentException("Unsupported time unit: " + unitPart);
        }
    }

    /**
     * Returns the time duration in milliseconds.
     */
    public long getMilliseconds() {
        return this.milliseconds;
    }

    @Override
    public String toString() {
        return milliseconds + " ms";
    }
}
