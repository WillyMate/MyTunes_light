package dk.easv.MyTunes_light.DAL.dao;

import dk.easv.MyTunes_light.BE.Playlist;
import dk.easv.MyTunes_light.BE.Song;
import dk.easv.MyTunes_light.DAL.DBConnecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO implements IPlaylistDAO {
    private final DBConnecter dbConnecter;

    public PlaylistDAO() throws Exception {
        try {
            this.dbConnecter = new DBConnecter();
        } catch (Exception e) {
            throw new Exception("Der skete en fejl ved forbindelse til databasen");
        }
    }

    @Override
    public List<Playlist> getAllPlaylist() throws Exception {
        List<Playlist> playlists = new ArrayList<>();

        String query = "SELECT * FROM Playlist ORDER by id";

        try (Connection conn = dbConnecter.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                playlists.add(new Playlist(id, name));
            }

            return playlists;
        } catch (Exception e) {
            throw new Exception("Kunne ikke få fat i alle playlister fra databasen");
        }
    }

    @Override
    public List<Playlist> getAllPlaylistWithSongs() throws Exception {
        List<Playlist> playlists = new ArrayList<>();

        String query = "SELECT * FROM Playlist ORDER by id";

        try (Connection conn = dbConnecter.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                ArrayList<Song> songs = this.getAllPlaylistSongs(id);
                playlists.add(new Playlist(id, name, songs));
            }

            return playlists;
        } catch (Exception e) {
            throw new Exception("Kunne ikke få fat i alle playlister fra databasen");
        }
    }

    @Override
    public ArrayList<Song> getAllPlaylistSongs(int playlistId) throws Exception {
        ArrayList<Song> songs = new ArrayList<>();

        String query = """
                        SELECT Songs.id, Songs.artist, Songs.duration, Songs.genre, Songs.path, Songs.ReleaseYear, Songs.name
                        FROM Playlist_songs
                        JOIN Songs ON Playlist_songs.Song_id = Songs.id
                        WHERE Playlist_songs.Playlist_id = ?
                        ORDER BY id
                        """;

        try (Connection conn = dbConnecter.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, playlistId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String artist = rs.getString("artist");
                int duration = rs.getInt("duration");
                String genre = rs.getString("genre");
                String path = rs.getString("path");
                //String album = rs.getString("album");
                int releaseYear = rs.getInt("ReleaseYear");
                String name = rs.getString("name");

                songs.add(new Song(id, name, artist, path, genre, duration, releaseYear));
            }

            return songs;
        } catch (Exception e) {
            throw new Exception("Kunne ikke få fat i alle sange fra playlister fra databasen");
        }

    }
}
