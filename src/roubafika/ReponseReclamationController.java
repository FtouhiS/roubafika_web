/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import API.MailerApi;
import Entities.Utilisateur;
import Entities.Reclamation;
import Entities.Reponse;
import Services.CRUDUtilisateur;
import Services.ReclamationService;
import Services.ReponseService;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author achra
 */
public class ReponseReclamationController implements Initializable {

    private Reclamation ReclamationSelected;
    private Utilisateur user;
    @FXML
    private TextArea tfReponse;
    @FXML
    private TextField textNom;
    @FXML
    private TextArea textDescription;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private TextField textTel;
    @FXML
    private TextField textMail;
    @FXML
    private Button ref;
    @FXML
    private TextField txt_sujet;
    @FXML
    private Node menuComponent;
    @FXML
    private Button btnModifier1;
    public void initData(int idRec) throws SQLException {
        ReclamationService met = new ReclamationService();
        CRUDUtilisateur user_ser = new CRUDUtilisateur();
        ReclamationSelected = met.findById(idRec);
        user = user_ser.rechercheUserbyid(ReclamationSelected.getId_user());
        System.out.println(user);
        textNom.setText(user.getNom());
        textTel.setText(Integer.toString(user.getNumero_telephone()));
        textMail.setText(user.getAdresse_mail());
        txt_sujet.setText(ReclamationSelected.getSujet());
        //textNom.setText(listReclamation.getSelectionModel().getSelectedItem().getid_client());
        textDescription.setText(ReclamationSelected.getDescription());
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
private void redirectToListeRec(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("roubafikaHome.fxml"));
            Scene c=new Scene(root);
            Stage stage=(Stage)btnEnvoyer.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          
    }

    @FXML
    private void Envoyer(ActionEvent event) throws SQLException {
        
        int id_client = ReclamationSelected.getId_user();
        String reponse = tfReponse.getText();
        if (id_client==0| reponse.isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamation");
            alert.setContentText("Voun ne pouvez pas envoyer une réponse vide!!");
            alert.show();
        }
        else {
            Reponse r = new Reponse(reponse,ReclamationSelected.getIdReclamation());
            ReponseService CR = new ReponseService();
            CRUDUtilisateur crUser= new CRUDUtilisateur();
            Utilisateur user = crUser.rechercheUserbyid(id_client);
            try {
                MailerApi met = new MailerApi();
                met.sendMail(r, user.getAdresse_mail());
            } catch (Exception ex) {
                Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            CR.createOne(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reponse");
            alert.setContentText("Reponse envoyé Avec succées");
            alert.show();
            this.redirectToListeRec();
            
        }
        }

    @FXML
    private void rafraichir(ActionEvent event) {
    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
    }

    
    }
    

