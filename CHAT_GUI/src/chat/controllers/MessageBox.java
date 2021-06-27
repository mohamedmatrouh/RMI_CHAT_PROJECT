package chat.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageBox implements Initializable {

    @FXML
    private ImageView icon;

    @FXML
    private Text textView;

    @FXML
    private Text username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/chat/images/user.png");
        Image image = new Image(file.toURI().toString());
        icon.setImage(image);
    }

    public void setMessage(String username, String msg){
        this.username.setText(username);
        textView.setText(msg);
    }
}
