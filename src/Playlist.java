import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Playlist implements Cloneable, Iterable<Song>, FilteredSongIterable, OrderedSongIterable{
    private List<Song> songs;
    private ScanningOrder order = ScanningOrder.ADDING;
    private String filterArtist = null;
    private Song.Genre filterGenre = null;
    private int filterDuration = Integer.MAX_VALUE;
    public Playlist() {
        this.songs = new ArrayList<>();
    }
    public Playlist(List<Song> songs) {
        this.songs = songs;
        this.order = ScanningOrder.ADDING;
    }


    @Override
    public String toString(){
        if (songs.isEmpty()) {
            return "[]";
        }
        String playlistString = "[";

        for(int i = 0; i < songs.size() - 1; i++)
            playlistString += "(" + songs.get(i).toString() + "), ";


        playlistString += "(" + songs.get(songs.size() - 1).toString() + ")]";

        return playlistString;
    }

    public void addSong(Song song){
        if(songs.contains(song))
            throw new SongAlreadyExistsException();
        songs.add(song);
    }
    public boolean removeSong(Song song){
        return songs.remove(song);
    }

    @Override
    public Playlist clone(){
        Playlist copyPlaylist;
        try{
            copyPlaylist = (Playlist) super.clone();
        }
        catch (CloneNotSupportedException e){
            return null;
        }
        copyPlaylist.songs = new ArrayList<Song>();

        for(int i = 0; i < songs.size(); i++)
            copyPlaylist.songs.add(i, songs.get(i).clone());

        return copyPlaylist;


    }
    @Override
    public boolean equals(Object other){
        if (!(other instanceof Playlist)){
            return false;
        }
        Playlist otherPlayList = (Playlist) other;

        for (int i=0; i<this.songs.size(); i++){
            if (!(otherPlayList.songs.contains(this.songs.get(i)))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int res = 0;
        for(int i = 0; i < songs.size(); i++)
            res += songs.get(i).hashCode();

        return res;
    }
    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    @Override
    public void filterArtist(String artist) {
        this.filterArtist = artist;
    }

    @Override
    public void filterGenre(Song.Genre genre) {
        this.filterGenre = genre;
    }


    @Override
    public void filterDuration(int maxDuration) {
        this.filterDuration = maxDuration;
    }

    @Override
    public void setScanningOrder(ScanningOrder order) {
        this.order = order;
    }
    public ScanningOrder getOrder(){
        return this.order;
    }



    private class PlaylistIterator implements Iterator<Song> {
        private int i;
        private List<Song> sortedSongs;
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
        @Override
        public boolean hasNext() {
            return i < sortedSongs.size();
        }



        @Override
        public Song next() {
                Song temp = sortedSongs.get(i);
                i++;
                return temp;
        }
    }

}

