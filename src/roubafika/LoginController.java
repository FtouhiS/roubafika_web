/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package roubafika;

import Entities.Session;
import Entities.Utilisateur;
import Services.CRUDUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.mail.MessagingException;
import javax.management.Notification;
import static org.bouncycastle.asn1.iana.IANAObjectIdentifiers.mail;
import org.controlsfx.control.Notifications;
import Services.CRUDUtilisateur;
import Utils.JavaMailUtil;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Soulaima
 */
public class LoginController implements Initializable {

    //@FXML
    //private ImageView imglogo;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnlogin;
    @FXML
    private AnchorPane login_form;
    @FXML
    private Label TravelMe;
    @FXML
    private Hyperlink create_acc;
    @FXML
    private Hyperlink mdp_oub;
    @FXML
    private AnchorPane signup_form;
    @FXML
    private Label TravelMe2;
    @FXML
    private TextField cin;
    @FXML
    private Button signup_btn;
    @FXML
    private Hyperlink login_acc;
    @FXML
    private PasswordField confirm_password;
    @FXML
    private TextField numero;
    @FXML
    private PasswordField password_signup;
    @FXML
    private TextField adresse;
    @FXML
    private TextField email_signup;
    @FXML
    private TextField username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         mdp_oub.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Motdepasseoubliee.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.setResizable(false);
           

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        create_acc.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterUserFXML.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.setResizable(false);
                stage.setWidth(843);
                stage.setHeight(710);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("hello");
        //Image img =new Image("/gui/images/logo.png");
        //imglogo.setImage(img);

    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        CRUDUtilisateur cr = new CRUDUtilisateur();

        Utilisateur user = cr.rechercheUserByMail(txtemail.getText());
        System.out.println(txtPassword.getText());
        System.out.println(user.getAdresse_mail());
        System.out.println(txtemail.getText().equals(user.getAdresse_mail()));

        if (txtemail.getText().equals(user.getAdresse_mail()) && user.getMdp().equals(txtPassword.getText())) {
            Session.startSession(user);
            System.out.println("------------------------------------");
            Parent root;
            root = FXMLLoader.load(getClass().getResource("roubafikaHome.fxml"));
            Scene c = new Scene(root);
            Stage stage = (Stage) btnlogin.getScene().getWindow();
            stage.setScene(c);
//        Stage stage =new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("../gui/AdminBack.fxml"));
//        Scene scene =new Scene(root);
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.show();
//        ((Node)event.getSource()).getScene().getWindow().hide();
//        System.out.println("------------------------------------");
//        check();
        } else {
            error();
        }
    }

    private void error() {
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Compte existant");
        al.setHeaderText("Erreur de saisie !");
        al.setContentText("Un compte avec ce mail existe déjà!");
        al.show();
//        Notifications notification= Notifications.create();
//        notification.title("Error");
//        notification.title("Error email or passwoed is incorrect");
//
//        notification.hideAfter(Duration.seconds(5));
//        notification.position(Pos.BASELINE_RIGHT);
//        notification.show();
    }

    private void check() {
        Notifications notification = Notifications.create();
        notification.title("Success");
        notification.title("welcome");

        notification.hideAfter(Duration.seconds(5));
        notification.position(Pos.BASELINE_RIGHT);
        notification.show();
    }

    @FXML
    private void mdpoubliee(ActionEvent event) {
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
}
