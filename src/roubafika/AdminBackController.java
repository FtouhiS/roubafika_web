/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package roubafika;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import Entities.Utilisateur;
import java.awt.Desktop;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Services.CRUDUtilisateur;

import Services.Metier;
import Utils.DataSource;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author Soulaima
 */
public class AdminBackController implements Initializable {

    @FXML
    private Button btsupprimer;
    @FXML
    private Node menuComponent;
    @FXML
    private TableView<Utilisateur> tableviewUser;
    private TableColumn<?, ?> IdUser;
    private TableColumn<?, ?> Nom;
    private TableColumn<?, ?> Prenom;
    private TableColumn<?, ?> Adresse_mail;
    private TableColumn<?, ?> mdp;
    private TableColumn<?, ?> Adresse;
    private TableColumn<?, ?> Numero_telephone;
    private TableColumn<?, ?> roleU;

    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    Utilisateur utilisateur = null;
    @FXML
    private TableColumn<?, ?> colIdUser;
    @FXML
    private TableColumn<?, ?> colNom;
    @FXML
    private TableColumn<?, ?> colPrenom;
    @FXML
    private TableColumn<?, ?> colAdresse_mail;
    @FXML
    private TableColumn<?, ?> colmdp;
    @FXML
    private TableColumn<?, ?> colAdresse;
    @FXML
    private TableColumn<?, ?> colNumero_telephone;
    @FXML
    private TableColumn<?, ?> colroleU;
    @FXML
    private Button btnRechercher;
    @FXML
    private TextField recherche;
    @FXML
    private Button tri;

    /**
     * Initializes the controller class.
     */
    public void exit() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showRec();
        //searchRec();
    }

    public ObservableList<Utilisateur> getUserList() {
        cnx = (Connection) DataSource.getInstance().getConnection();

        ObservableList<Utilisateur> UserList = FXCollections.observableArrayList();
        try {
            String query2 = "Select * from utilisateur";
            java.sql.Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(query2);
            while (RS.next()) {
                utilisateur = new Utilisateur(RS.getInt("IdUser"), RS.getString("Nom"), RS.getString("Prenom"), RS.getString("Adresse_mail"), RS.getString("mdp"), RS.getString("Adresse"), RS.getInt("Numero_telephone"), RS.getString("roleU"));
                UserList.add(utilisateur);
            }
            System.out.println(UserList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return UserList;

    }

    private void showRec() {
        CRUDUtilisateur cr = new CRUDUtilisateur();
        ArrayList<Utilisateur> list = cr.afficherUtilisateur();
        ObservableList<Utilisateur> li = FXCollections.observableArrayList(list);
        colIdUser.setCellValueFactory(new PropertyValueFactory<>("IdUser"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        colAdresse_mail.setCellValueFactory(new PropertyValueFactory<>("Adresse_mail"));
        colmdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        colNumero_telephone.setCellValueFactory(new PropertyValueFactory<>("Numero_telephone"));
        colroleU.setCellValueFactory(new PropertyValueFactory<>("roleU"));

        tableviewUser.setItems(li);
    }

    private void refresh() {
//        ObservableList<Utilisateur> list = getUserList();
//        IdUser.setCellValueFactory(new PropertyValueFactory<>("IdUser"));
//        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
//        Prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
//        Adresse_mail.setCellValueFactory(new PropertyValueFactory<>("Adresse_mail"));
//        mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
//        Adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
//        Numero_telephone.setCellValueFactory(new PropertyValueFactory<>("Numero_telephone"));
//        roleU.setCellValueFactory(new PropertyValueFactory<>("roleU"));
//
//        tableviewUser.setItems(list);

    }

    @FXML
    private void supprimerUtilisateur(ActionEvent event) {
//        CRUDUtilisateur crud = new CRUDUtilisateur();
//        Utilisateur utilisateur = (Utilisateur) tableviewUser.getSelectionModel().getSelectedItem();
//        // crud.supprimerUtilisateur();
//        refresh();
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Robafika :: Error Message");
//        alert.setHeaderText(null);
//        alert.setContentText("Utilisateur supprimé");
//        alert.showAndWait();

    }

    @FXML
    private void rechercher(ActionEvent event) {
//        Metier met = new Metier();
//        //ServiceUser sca = new ServiceUser();
//        System.out.println("/////////////recherche//////////");
//        System.out.println(recherche.getText());
//        ObservableList<Utilisateur> data = FXCollections.observableArrayList(met.SearchByName(recherche.getText()));
//        System.out.println(data);
//
//        IdUser.setCellValueFactory(new PropertyValueFactory<>("IdUser"));
//        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
//        Prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
//        Adresse_mail.setCellValueFactory(new PropertyValueFactory<>("Adresse_mail"));
//        mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
//        Adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
//        Numero_telephone.setCellValueFactory(new PropertyValueFactory<>("Numero_telephone"));
//        roleU.setCellValueFactory(new PropertyValueFactory<>("roleU"));
//
//        tableviewUser.setItems(data);
    }

    @FXML
    private void tri(ActionEvent event) {
//        Metier met = new Metier();
//        //ServiceUser sca = new ServiceUser();
//        System.out.println("/////////////Tri//////////");
//        System.out.println(tri.getText());
//        ObservableList<Utilisateur> data = FXCollections.observableArrayList(met.sortByName());
//        System.out.println(data);
//
//        IdUser.setCellValueFactory(new PropertyValueFactory<>("IdUser"));
//        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
//        Prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
//        Adresse_mail.setCellValueFactory(new PropertyValueFactory<>("Adresse_mail"));
//        mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
//        Adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
//        Numero_telephone.setCellValueFactory(new PropertyValueFactory<>("Numero_telephone"));
//        roleU.setCellValueFactory(new PropertyValueFactory<>("roleU"));
//
//        tableviewUser.setItems(data);
    }

    /*  @FXML
private void handle(ActionEvent event) {
    try {
        Document_Creation dc = new Document_Creation();
        dc.generatePDF();
        File file = new File("my_docs.pdf");
        if (file.exists()) {
            long startTime = System.currentTimeMillis();
            Desktop.getDesktop().open(file);
            long endTime = System.currentTimeMillis();
            System.out.println("Total time taken to open file -> " + file.getName() + " in " + (endTime - startTime) + "ms");
        } else {
            System.out.println("File does not exist -> " + file.getAbsolutePath());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }*/
    @FXML
    private void Reset(InputMethodEvent event) {
    }
}
