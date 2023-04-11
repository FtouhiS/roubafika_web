/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package roubafika;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button btnProduit;
    @FXML
    private Button btnStat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    



    @FXML
    private void Reclamation(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
            Parent root = loader.load();
            AfficherReclamationController dcc=loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idUser=16;
            //dcc.initData(idUser);
            btnProduit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Livreur(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("OffreConfirme.fxml"));
            Parent root = loader.load();
            OffreConfirmeController dcc=loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idUser=16;
            dcc.initData(idUser);
            btnProduit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AcheterProduits(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficherProduitVente.fxml"));
            Parent root = loader.load();
            AfficherProduitVenteController dcc=loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idUser=14;
            ArrayList<Integer> panier = new ArrayList<Integer>();
            dcc.initData(idUser,panier);
            btnProduit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void MesProduits(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
            Parent root = loader.load();
            AfficherProduitController dcc=loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idUser=13;
            dcc.initData(idUser,0,0,0);
            btnProduit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EchangerProduits(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
            Parent root = loader.load();
            AfficherProduitController dcc=loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idUser=14;
            dcc.initData(idUser,1,0,0);
            btnProduit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stat(ActionEvent event) throws ParseException {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("StatProduit.fxml"));
            Parent root = loader.load();
            StatProduitController dcc=loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idUser=13;
            dcc.initData(idUser);
            btnProduit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
