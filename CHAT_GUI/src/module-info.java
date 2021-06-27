module CHAT.GUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    opens chat;
    opens chat.controllers;
    exports service;
}
