package tps.tp4;

import entite.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import service.Modele;
import entite.Image;
import javafx.scene.control.Label;

import java.util.List;

public class ApplicationController {

    @FXML
    public ListView<String> imageList;
    public TextField loginField;
    public PasswordField passField;
    public TextField noteField;
    public Button loginBtn;
    public Button noteBtn;
    public ImageView imageArea;
    public Label labelError;
    public Label textLogin;

    private final Modele modele = new Modele();

    @FXML
    public void initialize() {
        setImagesTitres();
        unlock(true);
    }

    private void setImagesTitres() {

        List<Image> images = modele.getImages();
        for (Image image : images) {
            imageList.getItems().add(image.getNom());
        }
    }

    @FXML
    public void handleListClick() {
        String selectedItem = (String) imageList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Image imageSelected = modele.getImageByName(selectedItem);

            javafx.scene.image.Image imageToDisplay = new javafx.scene.image.Image("file:" + imageSelected.getUrl());
            imageArea.setImage(imageToDisplay);

            User logged = modele.getUserLoged();

            if (logged != null) {
                Integer n = modele.getNotebyCity(selectedItem, logged.getName());
                if (n != null) {
                    noteField.setText(n.toString());
                }else {
                    noteField.setText("");
                }
            }
        }
    }

    @FXML
    public void addNote() {

        Integer note = Integer.parseInt(noteField.getText());

        try{
            String selectedItem = (String) imageList.getSelectionModel().getSelectedItem();
            Image img = modele.getImageByName(selectedItem);

            modele.addNote(note, img, modele.getUserLoged());
        }catch (Exception e){
            labelError.setText(e.getMessage());
        }

    }

    @FXML
    public void validateLogin() {
        String login = loginField.getText();
        String pass = passField.getText();

        User userLogged = modele.login(login, pass);

        if (userLogged == null) {
            labelError.setText("Login ou mot de passe incorrect");
            labelError.setStyle("-fx-text-fill: red;");
            unlock(true);
        } else {
            labelError.setText("Connecté en tant que : " + userLogged.getName());
            labelError.setStyle("-fx-text-fill: blue;");
            unlock(false);
        }
    }

    public void unlock(boolean lock){
        noteBtn.setDisable(lock);
        noteField.setDisable(lock);
        imageList.setDisable(lock);

        if (!lock) {
            List<Image> images = modele.getImages();
            Image firstImage = images.get(0);
            javafx.scene.image.Image imageToDisplay = new javafx.scene.image.Image("file:" + firstImage.getUrl());

            imageArea.setImage(imageToDisplay);
            textLogin.setText("");
        } else {
            imageArea.setImage(null);
            textLogin.setText("Veuillez vous connecter pour accéder à l'application");
        }
    }

}