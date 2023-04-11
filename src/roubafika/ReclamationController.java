package roubafika;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entities.MailSender;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.ReclamationService;
import Services.ReponseService;
import Utils.DataSource;
import Entities.Reclamation;
import Entities.Reponse;
import Entities.Session;
import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FGH
 */
public class ReclamationController implements Initializable {

    private int id_user;
    private ArrayList<Integer> panier;
    @FXML
    private TextField txtId;
    //@FXML
    // private TextField txtDate;
    @FXML
    private TextField txtSujet;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Node menuComponent;
    @FXML

    private Button btnSupprimer;
    @FXML
    private Button btnModification;
    @FXML
    private Button btnAfficher;
    @FXML
    private Button btnAjouterReclamation;
    @FXML
    private Button btnAfficherReclamation;
    private Scene scene;
    @FXML
    private AnchorPane pnlReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /*  @FXML
    private void Reset(ActionEvent event) {
        tfNom.setText("");
        tfPrenom.setText("");
        tfAge.setText("");
    }
     */
    @FXML
    private void ajouterReclamation(ActionEvent event) throws Exception {
        LocalDate localDate = LocalDate.now();
        //DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy/mm/dd hh:mm");
        if (txtSujet.getText().isEmpty()
                || txtDescription.getText().isEmpty()) {

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Controle de saisie");
            al.setHeaderText("Erreur de saisie !");
            al.setContentText("Les données sont vides !");
            al.show();
        } else {
            //ID est un auto incremente il ne sera pas utulise dans la requete d'ajout
            MailSender.sendMail(Session.getCurrentUser().getAdresse_mail(), "Votre réclamation est envoyé , merci pour votre attente");
            Reclamation r = new Reclamation(0, Date.valueOf(localDate), txtSujet.getText(), txtDescription.getText(),Session.getCurrentUser().getIdUser());
            ReclamationService sp = new ReclamationService();
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Reclamtion");
            al.setHeaderText("Reclamation envoyé");
            al.setContentText("");
            try {
                sp.createOne(r);
            } catch (SQLException ex) {
                al.setTitle("Erreur");
                al.setHeaderText("Erreur Interne");
                al.setContentText(ex.getMessage());
                al.show();
            }
            Parent root;
            root = FXMLLoader.load(getClass().getResource("roubafikaHome.fxml"));
            Scene c = new Scene(root);
            Stage stage = (Stage) btnAjouterReclamation.getScene().getWindow();
            stage.setScene(c);

        }

    }

    @FXML
    private void afficherReclamation(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        node.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherReclamation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage affReclamationStage = new Stage();
        affReclamationStage.setScene(scene);
        affReclamationStage.setTitle("les Reclamations");
        affReclamationStage.show();
        /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
        try{
            Parent root = loader.load();
           pnlReclamation.getChildren().setAll(root);
           
           }
        catch(IOException ex){
            System.out.println(ex);
        }
 
         */

    }

//********************************************************LES BOUTON DE TESTES**********************************************************
    @FXML
    private void supprimerReclamation(ActionEvent event) {
        // TODO Auto-generated method stub
        LocalDate localDate = LocalDate.now();
        DataSource connexion = DataSource.getInstance();

        // on va seulment utuliser l id pour la suppression
        Reclamation r = new Reclamation(17, Date.valueOf(localDate), "", "");

        ReclamationService rc = new ReclamationService();
        Reponse reponse1 = new Reponse(0, "DESCRIPTION");
        ReponseService rs = new ReponseService();

        try {
            rc.deletOne(r.getIdReclamation());
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    @FXML
    private void afficherReclamations(ActionEvent event) {
        // TODO Auto-generated method stub
        LocalDate localDate = LocalDate.now();
        DataSource connexion = DataSource.getInstance();

        Reclamation r2 = new Reclamation(0, Date.valueOf(localDate), "sujet", "Description");
        Reclamation r3 = new Reclamation(0, Date.valueOf(localDate), "Reclamation technique", "DEscription1");
        Reclamation r4 = new Reclamation(0, Date.valueOf(localDate), "sujet", "DEscription");

        ReclamationService rc = new ReclamationService();
        Reponse reponse1 = new Reponse("DESCRIPTION");
        ReponseService rs = new ReponseService();

        System.out.println(rc.afficherReclamation());

    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
        // TODO Auto-generated method stub
        LocalDate localDate = LocalDate.now();
        DataSource connexion = DataSource.getInstance();

        Reclamation r = new Reclamation(15, Date.valueOf(localDate), "sujet modification", "AHMED");

        ReclamationService rc = new ReclamationService();
        Reponse reponse1 = new Reponse("DESCRIPTION");
        ReponseService rs = new ReponseService();

        try {
            rc.updateOne(r);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void initData(int idUser, ArrayList<Integer> panier) throws SQLException {
        //l'utilisateur veut acheter un produit
        this.id_user = idUser;
        this.panier = panier;

    }

}
