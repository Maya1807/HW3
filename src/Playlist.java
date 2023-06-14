import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * The Playlist class represents a collection of songs that can be iterated over, filtered, and ordered.
 * It implements the Cloneable, Iterable, FilteredSongIterable, and OrderedSongIterable interfaces.
 */
public class Playlist implements Cloneable, Iterable<Song>, FilteredSongIterable, OrderedSongIterable {

    private List<Song> songs;
    private ScanningOrder order = ScanningOrder.ADDING;
    private String filterArtist = null;
    private Song.Genre filterGenre = null;
    private int filterDuration = Integer.MAX_VALUE;

    /**
     * Constructs an empty Playlist.
     */
    public Playlist() {
        this.songs = new ArrayList<>();
    }

    /**
     * Constructs a Playlist with the given list of songs.
     *
     * @param songs the list of songs
     */
    public Playlist(List<Song> songs) {
        this.songs = songs;
    }

    /**
     * Returns a string representation of the Playlist.
     *
     * @return a string representation of the Playlist
     */
    @Override
    public String toString() {
        if (songs.isEmpty()) {
            return "[]";
        }
        StringBuilder playlistString = new StringBuilder("[");

        for (int i = 0; i < songs.size() - 1; i++)
            playlistString.append("(").append(songs.get(i).toString()).append("), ");

        playlistString.append("(").append(songs.get(songs.size() - 1).toString()).append(")]");

        return playlistString.toString();
    }

    /**
     * Adds a song to the Playlist.
     *
     * @param song the song to add
     * @throws SongAlreadyExistsException if the song already exists in the Playlist
     */
    public void addSong(Song song) {
        if (songs.contains(song))
            throw new SongAlreadyExistsException();
        songs.add(song);
    }

    /**
     * Removes a song from the Playlist.
     *
     * @param song the song to remove
     * @return true if the song was removed, false otherwise
     */
    public boolean removeSong(Song song) {
        return songs.remove(song);
    }

    /**
     * Creates a copy of the Playlist object.
     *
     * @return a clone of the Playlist
     */
    @Override
    public Playlist clone() {
        Playlist copyPlaylist;
        try {
            copyPlaylist = (Playlist) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
        copyPlaylist.songs = new ArrayList<>();

        for (int i = 0; i < songs.size(); i++)
            copyPlaylist.songs.add(i, songs.get(i).clone());

        return copyPlaylist;
    }

    /**
     * Checks if the Playlist is equal to another object.
     *
     * @param other the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Playlist)) {
            return false;
        }
        Playlist otherPlayList = (Playlist) other;

        if (this.songs.size() != otherPlayList.songs.size()) {
            return false;
        }

        for (int i = 0; i < this.songs.size(); i++) {
            if (!(otherPlayList.songs.contains(this.songs.get(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Computes the hash code of the Playlist.
     *
     * @return the hash code of the Playlist
     */
    @Override
    public int hashCode() {
        int res = 0;
        for (Song song : songs)
            res += song.hashCode();

        return res;
    }

    /**
     * Returns an iterator over the songs in the Playlist.
     *
     * @return an iterator over the songs in the Playlist
     */
    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    /**
     * Filters the songs in the Playlist by artist.
     *
     * @param artist the artist to filter by
     */
    @Override
    public void filterArtist(String artist) {
        this.filterArtist = artist;
    }

    /**
     * Filters the songs in the Playlist by genre.
     *
     * @param genre the genre to filter by
     */
    @Override
    public void filterGenre(Song.Genre genre) {
        this.filterGenre = genre;
    }

    /**
     * Filters the songs in the Playlist by duration.
     *
     * @param maxDuration the maximum duration to filter by
     */
    @Override
    public void filterDuration(int maxDuration) {
        this.filterDuration = maxDuration;
    }

    /**
     * Sets the scanning order for iterating over the songs in the Playlist.
     *
     * @param order the scanning order to set
     */
    @Override
    public void setScanningOrder(ScanningOrder order) {
        this.order = order;
    }

    /**
     * Returns the current scanning order of the Playlist.
     *
     * @return the scanning order of the Playlist
     */
    public ScanningOrder getOrder() {
        return this.order;
    }

    /**
     * The PlaylistIterator class represents an iterator over the songs in the Playlist.
     */
    private class PlaylistIterator implements Iterator<Song> {

        private int i;
        private List<Song> sortedSongs;

        /**
         * Constructs a PlaylistIterator.
         */
        public PlaylistIterator() {
            this.sortedSongs = new ArrayList<>();
            for (Song song : songs) {
                if ((filterArtist == null || song.getArtist().equals(filterArtist)) &&
                        (filterGenre == null || song.getGenre().equals(filterGenre)) &&
                        (song.getDuration() <= filterDuration)) {
                    this.sortedSongs.add(song);
                }
            }
            switch (order) {
                case NAME:
                    this.sortedSongs.sort(Comparator.comparing(Song::getName));
                    break;
                case DURATION:
                    this.sortedSongs.sort(Comparator.comparingInt(Song::getDuration));
                    break;
                case ADDING:
                default:
                    break;
            }
        }

        /**
         * Checks if there are more songs to iterate over.
         *
         * @return true if there are more songs, false otherwise
         */
        @Override
        public boolean hasNext() {
            return i < sortedSongs.size();
        }

        /**
         * Returns the next song in the iteration.
         *
         * @return the next song
         */
        @Override
        public Song next() {
            Song temp = sortedSongs.get(i);
            i++;
            return temp;
        }
    }
}
