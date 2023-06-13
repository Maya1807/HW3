import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Playlist implements Cloneable, Iterable<Song>, FilteredSongIterable, OrderedSongIterable{
    private List<Song> songs;
    private ScanningOrder order = ScanningOrder.ADDING;
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

    public Iterator<Song> iterator() {
        return new Playlist.PlaylistIterator();
    }

    @Override
    public void filterArtist(String artist) {

        List<Song> originalSongs = new ArrayList<>(songs);
        if (artist == null) {
            sortSongsByOrder();
        } else {
            Iterator<Song> iterator = originalSongs.iterator();
            while(iterator.hasNext()){
                Song song = iterator.next();
                if (!song.getArtist().equals(artist)){
                    iterator.remove();
                }
            }
        }
        songs = originalSongs;
    }

    @Override
    public void filterGenre(Song.Genre genre) {
        List<Song> originalSongs = new ArrayList<>(songs);
        if (genre != null) {
            Iterator<Song> iterator = originalSongs.iterator();
            while(iterator.hasNext()){
                Song song = iterator.next();
                if (!song.getGenre().equals(genre)){
                    iterator.remove();
                }
            }
        }
        songs = originalSongs;
    }


    @Override
    public void filterDuration(int maxDuration) {
        List<Song> originalSongs = new ArrayList<>(songs);
        if (maxDuration <= 0) {
            originalSongs.clear();
        } else {
            Iterator<Song> iterator = originalSongs.iterator();
            while(iterator.hasNext()){
                Song song = iterator.next();
                if (song.getDuration() > maxDuration){
                    iterator.remove();
                }
            }
        }
        songs = originalSongs;
    }

    @Override
    public void setScanningOrder(ScanningOrder order) {
        this.order = order;
    }
    public ScanningOrder getOrder(){
        return this.order;
    }
    private void sortSongsByOrder() {
        switch (order) {
            case NAME:
                songs.sort(Comparator.comparing(Song::getName));
                break;
            case DURATION:
                songs.sort(Comparator.comparingInt(Song::getDuration));
                break;
            case ADDING:
            default:
                break;  // for ADDING order, we do nothing
        }
    }


    private class PlaylistIterator implements Iterator<Song> {
        private int i = 0;
        public List<Song> sortedSongs;
        public PlaylistIterator() {
            sortedSongs = new ArrayList<>(songs);
            sortSongsByOrder();
        }
        @Override
        public boolean hasNext() {
            return i < songs.size();
        }



        @Override
        public Song next() {
                Song temp = songs.get(i);
                i++;
                return temp;
        }
    }

}

