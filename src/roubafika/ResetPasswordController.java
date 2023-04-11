/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package roubafika;

import Entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.CRUDUtilisateur;
import Utils.ControleSaisieTextFields;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author Soulaima
 */
public class ResetPasswordController implements Initializable {

    private Utilisateur user;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfconfirm;
    @FXML
    private Button btnReset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnResetAction(ActionEvent event) {
        Alert A = new Alert(Alert.AlertType.INFORMATION);
        System.out.println(tfpassword.getText().equals(tfconfirm.getText()));
        if (!tfpassword.getText().equals("") && tfpassword.getText().equals(tfconfirm.getText())) {
            System.out.println(this.user.getAdresse_mail());
            System.out.println(tfpassword.getText());

            this.user.setMdp(tfpassword.getText());
            
            CRUDUtilisateur su = new CRUDUtilisateur();
            su.modifierUtilisateur(this.user);
            
            A.setContentText("Mot de passe modifié avec succes ! ");
            A.show();
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("Login.fxml"));

                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();

            } catch (IOException ex) {

                System.out.println(ex.getMessage());

            }
        } else {
            A.setContentText("veuillez saisir un mot de passe conforme !");
            A.show();
        }

    }

    @FXML
    private void btnAnnulerResetAction(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("Motdepasseoubliee.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        }
    }

    public void initData(Utilisateur user) throws SQLException {

        this.user = user;
    }
}
