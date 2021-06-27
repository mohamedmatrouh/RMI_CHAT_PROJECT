package chat;

import chat.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SelectNameController {

    @FXML
    private TextField username;

    @FXML
    private TextField server;

    @FXML
    void connect(MouseEvent event) {

        String name = username.getText();
        String serverIp = server.getText();

        if (!name.isEmpty() && !serverIp.isEmpty()){

            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("views/view.fxml"));
                Parent root = loader.load();
                Controller controller = loader.getController();
                controller.setClientName(name, serverIp);

                primaryStage.setScene(new Scene(root));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
