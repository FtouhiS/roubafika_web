/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package roubafika;

//import roubafika.ControleSaisieTextFields;
import Entities.Utilisateur;
import Services.CRUDProduit;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import Services.EmailSender;
import Utils.ControleSaisieTextFields;
import java.util.ArrayList;

/**
 * FXML Controller class
 *
 * @author Soulaima
 */
public class MotdepasseoublieeController implements Initializable {

    @FXML
    private TextField tfemail;
    @FXML
    private Button BtnCode;
    ControleSaisieTextFields cs;
    public static int code;
    public static String EmailReset;

    private int generateVerificationCode() {
        // Générer un code de vérification aléatoire à 6 chiffres
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void BtnCode(ActionEvent event) throws Exception {
        int code = generateVerificationCode();
        Alert A = new Alert(Alert.AlertType.WARNING);
        CRUDUtilisateur su = new CRUDUtilisateur();

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean verifMail = tfemail.getText().matches(emailRegex);
        if (su.rechercheUserByMail(tfemail.getText()) != null) {
            String EmailReset = tfemail.getText();
            CRUDProduit cp = new CRUDProduit();
            String body = "Votre code de verification est: " + code;
            cp.sendMail(body, tfemail.getText());

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Verifcode.fxml"));
                Parent root = loader.load();
                VerifcodeController dcc = loader.getController();
                //user ca=listStaff.getSelectionModel().getSelectedItem();

                dcc.initData(code, su.rechercheUserByMail(tfemail.getText()));
                BtnCode.getScene().setRoot(root);

            } catch (IOException ex) {

                System.out.println(ex.getMessage());

            }
        }

    }

    @FXML
    private void btnAnnulerForgot(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        }
    }

}
