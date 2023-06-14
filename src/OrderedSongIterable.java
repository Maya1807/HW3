/**
 * The OrderedSongIterable interface represents an iterable collection of songs
 * that can be iterated over in a specific scanning order.
 * It extends the Iterable interface for iterating over the songs.
 */
public interface OrderedSongIterable extends Iterable<Song> {

        /**
         * Sets the scanning order for iterating over the songs.
         *
         * @param order the scanning order to set
         */
        void setScanningOrder(ScanningOrder order);
}
