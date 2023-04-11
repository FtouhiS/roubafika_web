/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roubafika;

import Entities.Produit;
import Services.CRUDProduit;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class roubafikaHomeController implements Initializable {

  
    @FXML
    private HBox ServicesModule;
    @FXML
    private Button ServicesButton;
    @FXML
    private Node menuComponent;
    private int id_user;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void listServices(ActionEvent event) {

        try {
            Parent Liste = FXMLLoader.load(getClass().getResource("frontService.fxml"));

            Scene si = new Scene(Liste);
            si.getStylesheets().add(getClass().getResource("card.css").toExternalForm());
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(si);
            st.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
     public void initData(int idUser ) throws SQLException {
        this.id_user=idUser;
        
                        
        
            
        
    }
    
   

}
