public interface FilteredSongIterable extends Iterable<Song> {
    void filterArtist(String artist);
    void filterGenre(Song.Genre genre);
    void filterDuration(int duration);


}
