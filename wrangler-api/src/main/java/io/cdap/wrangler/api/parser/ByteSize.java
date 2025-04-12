

/**
 * Converts byte size strings like "10KB", "2MB", "1GB" into bytes.
 */
public class ByteSize {

    private final long bytes;

    /**
     * Parses a byte size string and converts it to bytes.
     * Supported units: KB, MB, GB.
     *
     * @param input the byte size string (e.g., "10KB", "2MB")
     */
    public ByteSize(String input) {
        input = input.trim().toUpperCase();

        // Extract number and unit parts
        String numberPart = input.replaceAll("[^0-9]", "");
        String unitPart = input.replaceAll("[0-9]", "");

        if (numberPart.isEmpty() || unitPart.isEmpty()) {
            throw new IllegalArgumentException("Invalid byte size: " + input);
        }

        long number = Long.parseLong(numberPart);

        // Convert based on the unit (KB, MB, GB)
        switch (unitPart) {
            case "KB":
                this.bytes = number * 1024L;
                break;
            case "MB":
                this.bytes = number * 1024L * 1024L;
                break;
            case "GB":
                this.bytes = number * 1024L * 1024L * 1024L;
                break;
            default:
                throw new IllegalArgumentException("Unsupported unit: " + unitPart);
        }
    }

    /**
     * Returns the byte value of the size.
     */
    public long getBytes() {
        return this.bytes;
    }

    @Override
    public String toString() {
        return bytes + " bytes";
    }
}
