package dk.easv.MyTunes_light.GUI.Controller;

import dk.easv.MyTunes_light.BE.Song;
import dk.easv.MyTunes_light.GUI.Model.SongModel;
import dk.easv.MyTunes_light.GUI.ModelHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class PopUpCreateSong {

    @FXML
    public TextField txtFName;
    public Button btnChooseF;
    public TextField txtEditTitle;
    public TextField txtEditArtist;
    public TextField txtEditCategory;
    public TextField txtEditTime;


    private final SongModel songmodel;

    public void ChooseFile(ActionEvent actionEvent) throws SQLException, IOException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));

        File selectedFile = chooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            txtEditTitle.setText(selectedFile.getName()); //tilføjelse af title
            txtFName.setText(selectedFile.getName()); // FilePath added
        }
    }


    public void editClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }



    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }
    public PopUpCreateSong() {this.songmodel = ModelHandler.getInstance().getSongModel();
    }

}
