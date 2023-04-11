/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package roubafika;

import Entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 *
 * @author Soulaima
 */
public class VerifcodeController implements Initializable {

    int code;
    private Utilisateur user;
    @FXML
    private TextField tfCode;
    @FXML
    private Button btnConfirmerCode;
    @FXML
    private Button btnAnnulerCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(int code, Utilisateur user) throws SQLException {
        System.out.println("ààààààààààààààààààààààààààààààààà");
        System.out.println(code);
        System.out.println(user);
        System.out.println("ààààààààààààààààààààààààààààààààà");

        this.code = code;
        System.out.println("ààààààààààààààààààààààààààààààààà");
        System.out.println(this.code);

        System.out.println("ààààààààààààààààààààààààààààààààà");
        this.user = user;

    }

//    private void btnConfirmerCodeAction(ActionEvent event) {
//        
//        if (Integer.parseInt(tfCode.getText()) == MotdepasseoublieeController.code)
//        {
//              try {
//
//            Parent page1 = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
//
//            Scene scene = new Scene(page1);
//
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//            stage.setScene(scene);
//
//            stage.show();
//
//        } catch (IOException ex) {
//
//           System.out.println(ex.getMessage());
//
//        }
//        }
//        else 
//        {
//            Alert A = new Alert(Alert.AlertType.WARNING);
//            A.setContentText("Code erroné ! ");
//            A.show();
//            
//        }
//    }
//
    @FXML
    private void btnAnnulerCode(ActionEvent event) {
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

    @FXML
    private void ConfirmerCode(ActionEvent event) throws SQLException {
        System.out.println(tfCode.getText());
        System.out.println(this.code);
        System.out.println(this.user.getAdresse_mail());
        System.out.println(Integer.parseInt(tfCode.getText()) ==(this.code));
        if (Integer.parseInt(tfCode.getText()) ==(this.code)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
                Parent root = loader.load();
                ResetPasswordController dcc = loader.getController();
                //user ca=listStaff.getSelectionModel().getSelectedItem();

                dcc.initData(this.user);
                btnAnnulerCode.getScene().setRoot(root);

            } catch (IOException ex) {

                System.out.println(ex.getMessage());

            }
        }
    }

}
