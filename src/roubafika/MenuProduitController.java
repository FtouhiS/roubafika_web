/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roubafika;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class MenuProduitController implements Initializable {

    @FXML
    private Button listeProduit;
    @FXML
    private Button addProduit;
    @FXML
    private Button mesProduits;
    @FXML
    private Button echangerProduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void AcheterProduits(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitVente.fxml"));
            Parent root = loader.load();
            AfficherProduitVenteController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idUser = 14;
            ArrayList<Integer> panier = new ArrayList<Integer>();
            dcc.initData(idUser, panier);
            mesProduits.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void MesProduits(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
            Parent root = loader.load();
            AfficherProduitController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idUser = 13;
            dcc.initData(idUser, 0, 0, 0);
            mesProduits.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EchangerProduits(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
            Parent root = loader.load();
            AfficherProduitController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idUser = 14;
            dcc.initData(idUser, 1, 0, 0);
            mesProduits.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterProduit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterProduit.fxml"));
            Parent root = loader.load();
            AfficherProduitController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idUser = 14;
            dcc.initData(idUser, 1, 0, 0);
            mesProduits.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
