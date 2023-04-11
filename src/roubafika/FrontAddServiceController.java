/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roubafika;

import Entities.Service;
import Entities.Service.Categorie;
import Services.MyService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class FrontAddServiceController implements Initializable {

    private Categorie categorieSelectionnee;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @FXML
    private VBox cardsContainer;
    @FXML
    private TextField titreInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private DatePicker dateInput;
    @FXML
    private TextField adresseInput;
    @FXML
    private RadioButton buttonTransport;
    @FXML
    private RadioButton buttonPlomberie;
    @FXML
    private Button ajoutButton;
    @FXML
    private Button listeService;
    @FXML
    private Button mesServices;
    @FXML
    private Label erreurtitre;
    @FXML
    private Label erreurDescription;
    @FXML
    private Label erreurDate;
    @FXML
    private Label erreurAdresse;
    @FXML
    private Node menuComponent;

    @FXML
    private Button ServicesD;
    @FXML
    private Button addService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        listeService.setOnAction(event -> {
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
        });
        mesServices.setOnAction(event -> {
            try {
                Parent Liste = FXMLLoader.load(getClass().getResource("frontMesService.fxml"));

                Scene si = new Scene(Liste);
                si.getStylesheets().add(getClass().getResource("card.css").toExternalForm());
                Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
                st.setScene(si);
                st.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        ServicesD.setOnAction(event -> {
            try {
                Parent Liste = FXMLLoader.load(getClass().getResource("frontServiceDemande.fxml"));

                Scene si = new Scene(Liste);
                si.getStylesheets().add(getClass().getResource("card.css").toExternalForm());
                Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
                st.setScene(si);
                st.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void save(ActionEvent event) {
        MyService serv = new MyService();

        if (titreInput.getText().isEmpty()) {
            erreurtitre.setText("Titre non valide !");
            erreurtitre.setVisible(true);

            return;
        }
        if (descriptionInput.getText().isEmpty()) {
            erreurDescription.setText("Description non valide !");
            erreurDescription.setVisible(true);

            return;
        }
        LocalDate selectedDate = dateInput.getValue();

// Check if a date has been selected
        if (selectedDate == null) {
            erreurDate.setText("Date non valide !");
            erreurDate.setVisible(true);
            return;
        }

// Convert the selected date to a string if needed
        String dateString = selectedDate.toString();

        if (adresseInput.getText().isEmpty()) {
            erreurAdresse.setText("Adresse non valide !");
            erreurAdresse.setVisible(true);

            return;
        }

        Categorie categorieSelectionnee = null;
        if (buttonTransport.isSelected()) {
            categorieSelectionnee = Categorie.transport;
        } else if (buttonPlomberie.isSelected()) {
            categorieSelectionnee = Categorie.plomberie;
        }
        Service service = new Service();
        service.setTitre(titreInput.getText());
        service.setDescription(descriptionInput.getText());
        System.out.println(dateInput.getValue().toString());
        String formattedDate = dateInput.getValue().format(formatter);
        service.setDate_annonce(formattedDate);
        service.setAdresse(adresseInput.getText());
        service.setCategorie(categorieSelectionnee);
        //Add static user
        service.setIdUser(2);
        serv.ajouterService(service);

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

}
