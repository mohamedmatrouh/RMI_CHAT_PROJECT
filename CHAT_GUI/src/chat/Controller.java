package chat;

import chat.controllers.MessageBox;
import chat.controllers.OnlineUserController;
import chat.manager.AppManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private AppManager manager;

    @FXML
    private ImageView sendButton;

    @FXML
    private TextField messageTextField;

    @FXML
    private ListView<Parent> messagesList;

    @FXML
    private VBox usersList;

    @FXML
    private Text username;

    @FXML
    void disconnectClicked(MouseEvent event) {
        manager.disconnect();
        // load main page
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/selectName.fxml"));
            Parent root = loader.load();
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void sendButtonClicked(MouseEvent event) {
        String message = messageTextField.getText();

        // stop if message field is empty
        if(message.isEmpty()) return;

        messageTextField.clear();
        manager.sendMessageOut(message);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // set the icon image
        File file = new File("src/chat/images/send.png");
        Image image = new Image(file.toURI().toString());
        sendButton.setImage(image);

        // change list background
        messagesList.setStyle("-fx-control-inner-background: none;");
        usersList.setStyle("-fx-control-inner-background: none;");

    }

    public void setClientName(String name, String serverIp){

        this.username.setText(name);

        // initialize app manager
        try {
            manager = new AppManager(this, name, serverIp);
        } catch (RemoteException | MalformedURLException | UnknownHostException | ServerNotActiveException | NotBoundException e) {
            e.printStackTrace();
        }

    }

    public void loadMsgBox(String username, String msg){

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("views/messageBox.fxml"));
            Parent messageBox = loader.load();

            MessageBox controller = loader.getController();
            controller.setMessage(username, msg);
            messagesList.getItems().add(messageBox);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearUsersList(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                usersList.getChildren().clear();
            }
        });
    }

    public void addUser(String username){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("views/onlineUser.fxml"));
                try {
                    Parent userBox = loader.load();
                    OnlineUserController controller = loader.getController();
                    controller.setUsername(username);
                    usersList.getChildren().add(userBox);
                    Node item = usersList.getChildren().get(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
