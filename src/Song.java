import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class Song implements Cloneable{
    private final String name;
    private final String artist;
    private Genre genre;
    private int duration;

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }
    private String timeManage(){
        int min = duration / 60;
        int second = duration % 60;
        String s;
        if(second < 10)
            s = min + ":0" + second;
        else
            s = min + ":" + second;

        return s;
    }
    @Override
    public String toString(){
        return name + ", " + artist + ", " + genre + ", " + timeManage();
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Song))
            return false;
        Song otherSong = (Song) other;
        return Objects.equals(this.name, otherSong.name) && Objects.equals(this.artist, otherSong.artist);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + artist.hashCode();
        return result;
    }

    @Override
    public Song clone(){
        try {
             return (Song) super.clone();
        }
        catch (CloneNotSupportedException e){
            return null;
        }
    }
    public enum Genre{
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }
}
