/**
 * The ScanningOrder enum represents the scanning order for iterating over songs in a playlist.
 */
public enum ScanningOrder {
    /**
     * Represents the scanning order of songs as they were added to the playlist.
     */
    ADDING,

    /**
     * Represents the scanning order of songs by name in alphabetical order.
     */
    NAME,

    /**
     * Represents the scanning order of songs by duration in ascending order.
     */
    DURATION
}
