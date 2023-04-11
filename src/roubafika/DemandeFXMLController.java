/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roubafika;

import Entities.Demande;
import Entities.Service;
import Entities.Session;
import Services.MyDemande;
import Services.MyService;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class DemandeFXMLController implements Initializable {

    @FXML
    private Node menuComponent;
    @FXML
    private TableView<Demande> ListD;
    @FXML
    private TableColumn<Demande, String> nomD;
    @FXML
    private TableColumn<Demande, String> prenomD;
    @FXML
    private TableColumn<Demande, String> emailD;
    @FXML
    private TableColumn<Demande, String> dateD;
    @FXML
    private TableColumn<Demande, String> idD;
    @FXML
    private ComboBox<Service> libelleService;
    @FXML
    private TextField libelleNom;
    @FXML
    private TextField libellePrenom;
    @FXML
    private DatePicker libelleDate;
    @FXML
    private Button ajouterD;
    @FXML
    private Button modifierD;
    @FXML
    private Button supprimerD;
    @FXML
    private TextField libelleEmail;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static class ServicestringConverter extends javafx.util.StringConverter<Service> {

        @Override
        public String toString(Service service) {
            return service.getTitre();
        }

        @Override
        public Service fromString(String string) {
            return null; // not needed for a read-only ComboBox
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MyService serv = new MyService();
        ObservableList<Service> listeTitre = serv.listeTitreService();

        libelleService.setItems(listeTitre);
        libelleService.setConverter(new ServicestringConverter());
        try {
            showAll();
        } catch (SQLException ex) {
            Logger.getLogger(DemandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterDemande(ActionEvent event) throws SQLException, Exception {
        MyDemande demande = new MyDemande();
        if (libelleService.getValue() == null || libelleService.getValue().toString().isEmpty()) {

            return;
        }
        if (libelleNom.getText().isEmpty()) {

            return;
        }
        if (libellePrenom.getText().isEmpty()) {

            return;
        }
        LocalDate selectedDate = libelleDate.getValue();

        if (selectedDate == null) {

            return;

        }
        String dateString = selectedDate.toString();

        if (libelleEmail.getText().isEmpty()) {

            return;
        }

        Demande parti = new Demande();
        parti.setId_serv((libelleService.getValue().getId()));
        parti.setNom_demandeur(libelleNom.getText());
        parti.setPrenom_demandeur(libellePrenom.getText());
        System.out.println(libelleDate.getValue().toString());
        String formattedDate = libelleDate.getValue().format(formatter);
        parti.setDate_demande(formattedDate);
        parti.setEmail_demandeur(libelleEmail.getText());

        demande.ajouterDemande(parti);
        Service s = demande.getServiceByID(parti.getId_serv());
        demande.sendMail("Demande Effectué", Session.getCurrentUser().getAdresse_mail());
        showAll();
    }

    @FXML
    private void modifierDemande(ActionEvent event) throws SQLException {
        Demande f = new Demande(libelleNom.getText(), libellePrenom.getText(), libelleDate.getValue().toString(), libelleEmail.getText(), libelleService.getValue().getId());
        System.out.println(f);
        MyDemande serv = new MyDemande();
        serv.modifierDemande(f, ListD.getSelectionModel().getSelectedItem().getNom_demandeur());

        showAll();

    }

    @FXML
    private void supprimerDemande(ActionEvent event) throws SQLException {
        MyDemande s = new MyDemande();
        System.out.println(ListD.getSelectionModel().getSelectedItem().getNom_demandeur());
        s.deleteDemande((ListD.getSelectionModel().getSelectedItem().getNom_demandeur()));

        showAll();
    }

    public void showAll() throws SQLException {
        MyDemande dem = new MyDemande();
        ObservableList<Demande> s = dem.afficherDemande();
        System.out.println(s);
        nomD.setCellValueFactory(new PropertyValueFactory<Demande, String>("nom_demandeur"));
        prenomD.setCellValueFactory(new PropertyValueFactory<Demande, String>("prenom_demandeur"));
        emailD.setCellValueFactory(new PropertyValueFactory<Demande, String>("email_demandeur"));
        dateD.setCellValueFactory(new PropertyValueFactory<Demande, String>("date_demande"));
        idD.setCellValueFactory(new PropertyValueFactory<Demande, String>("titreService"));

        ListD.setItems(s);

    }
}
