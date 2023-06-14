/**
 * The FilteredSongIterable interface represents an iterable collection of songs
 * that can be filtered based on artist, genre, and duration.
 * It extends the Iterable interface for iterating over the songs.
 */
public interface FilteredSongIterable extends Iterable<Song> {

    /**
     * Filters the songs by artist.
     *
     * @param artist the artist to filter by
     */
    void filterArtist(String artist);

    /**
     * Filters the songs by genre.
     *
     * @param genre the genre to filter by
     */
    void filterGenre(Song.Genre genre);

    /**
     * Filters the songs by duration.
     *
     * @param duration the duration to filter by
     */
    void filterDuration(int duration);
}
