package dk.easv.MyTunes_light;

import dk.easv.MyTunes_light.BE.Playlist;
import dk.easv.MyTunes_light.BE.Song;
import dk.easv.MyTunes_light.DAL.dao.PlaylistDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyTunes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        PlaylistDAO playlistDAO = new PlaylistDAO();

        for (Playlist p : playlistDAO.getAllPlaylistWithSongs()) {
            System.out.println("--------- " + p.getName() + " (" + p.getSongs().size() + ") -----------");
            for (Song song : p.getSongs()) {
                System.out.println(song.getDuration() + " " + song.getLengthString());
            }
        }
        /*
        SongDAO songDAO = new SongDAO();

        List<Song> alleSange = songDAO.getAllSongs();
        System.out.println("Antal sange: " + alleSange.size());

        for (Song song : alleSange)
            System.out.println(song);*/
    }

    public static void main(String[] args) {
        launch();
    }
}