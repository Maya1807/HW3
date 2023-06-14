import java.util.Objects;

/**
 * The Song class represents a song with its name, artist, genre, and duration.
 * It implements the Cloneable interface to support cloning.
 */
public class Song implements Cloneable {

    private final String name;
    private final String artist;
    private Genre genre;
    private int duration;

    /**
     * Constructs a Song object with the specified name, artist, genre, and duration.
     *
     * @param name     the name of the song
     * @param artist   the artist of the song
     * @param genre    the genre of the song
     * @param duration the duration of the song in seconds
     */
    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    /**
     * Returns the name of the song.
     *
     * @return the name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the artist of the song.
     *
     * @return the artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the genre of the song.
     *
     * @return the genre of the song
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Returns the duration of the song in seconds.
     *
     * @return the duration of the song in seconds
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the genre of the song.
     *
     * @param genre the genre to set
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Sets the duration of the song in seconds.
     *
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Formats the duration of the song in minutes and seconds (mm:ss) format.
     *
     * @return the formatted duration string
     */
    private String timeManage() {
        int min = duration / 60;
        int second = duration % 60;
        String s;
        if (second < 10)
            s = min + ":0" + second;
        else
            s = min + ":" + second;

        return s;
    }

    /**
     * Returns a string representation of the Song in the format:
     * "name, artist, genre, duration"
     *
     * @return a string representation of the Song
     */
    @Override
    public String toString() {
        return name + ", " + artist + ", " + genre + ", " + timeManage();
    }

    /**
     * Checks if the Song is equal to another object.
     *
     * @param other the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Song))
            return false;
        Song otherSong = (Song) other;
        return Objects.equals(this.name, otherSong.name) && Objects.equals(this.artist, otherSong.artist);
    }

    /**
     * Computes the hash code of the Song.
     *
     * @return the hash code of the Song
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + artist.hashCode();
        return result;
    }

    /**
     * Creates and returns a clone of the Song.
     *
     * @return a clone of the Song
     */
    @Override
    public Song clone() {
        try {
            return (Song) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * The Genre enum represents the genre of a song.
     */
    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }
}
