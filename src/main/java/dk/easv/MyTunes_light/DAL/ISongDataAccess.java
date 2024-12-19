package dk.easv.MyTunes_light.DAL;

import dk.easv.MyTunes_light.BE.Song;

import java.util.List;

public interface ISongDataAccess {

    public List<Song> getAllSongs() throws Exception;

    public Song createSong(Song song) throws Exception;

    public void updateSong(Song song) throws Exception;

    public Song deleteSong(Song song) throws Exception;
}
