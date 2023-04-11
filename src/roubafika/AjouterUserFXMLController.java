/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package roubafika;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.CRUDUtilisateur;
import Entities.Utilisateur;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Soulaima
 */
public class AjouterUserFXMLController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfAdresse_mail;
    @FXML
    private TextField tfmdp;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfnumero;
    @FXML
    private Button btnsinscrire;
    private TextField tfrole;
    @FXML
    private RadioButton btnuser;
    @FXML
    private RadioButton btnlivreur;
    @FXML
    private AnchorPane login_form;
    @FXML
    private Label ROUBAFIKA;
    @FXML
    private Hyperlink login_acc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        login_acc.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // TODO
    }

    @FXML
    private void Reset(ActionEvent event) {
        tfNom.setText("");
        tfPrenom.setText("");
        tfAdresse_mail.setText("");
        tfmdp.setText("");
        tfAdresse.setText("");
        tfnumero.setText("");

    }

    @FXML
    private void ajouterUtlisateur(ActionEvent event) {
        CRUDUtilisateur cr = new CRUDUtilisateur();

        ArrayList<Utilisateur> users = cr.afficherUtilisateur();
        if (tfNom.getText().isEmpty()
                || tfPrenom.getText().isEmpty()
                || tfAdresse_mail.getText().isEmpty()
                || tfmdp.getText().isEmpty()
                || tfAdresse.getText().isEmpty()
                || tfnumero.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();

        } else if (cr.rechercheUserByMail(tfAdresse_mail.getText()).getIdUser() != 0) {

            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Compte existant");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Un compte avec ce mail existe déjà!");
            al.show();
        } else {
            String role = "";
            if (btnuser.isSelected()) {
                role = "user";
            } else if (btnlivreur.isSelected()) {
                role = "livreur";
            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Role not found");
                al.setHeaderText("Erreur de saisie !");
                al.setContentText("vous devez choisir un role!");
                al.show();
            }
            if (role != "") {
                Utilisateur U = new Utilisateur(tfNom.getText(),
                        tfPrenom.getText(), tfAdresse_mail.getText(),
                        tfmdp.getText(), tfAdresse.getText(), Integer.parseInt(tfnumero.getText()), role);

                CRUDUtilisateur crud = new CRUDUtilisateur();
                System.out.println(U);
                try {
                    crud.ajouterUtilisateur(U);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    Parent root = loader.load();

                    btnlivreur.getScene().setRoot(root);
                } catch (Exception ex) {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setTitle("Erreur");
                    al.setHeaderText("Erreur Interne");
                    al.setContentText(ex.getMessage());
                    al.show();
                }

            }

        }

    }

}
