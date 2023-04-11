/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package roubafika;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Entities.Commande;
import Entities.LigneCommande;
import Entities.Produit;
import Entities.Utilisateur;
import Services.CRUDProduit;
import Services.CRUDUtilisateur;
import Services.ReclamationService;
import Services.ServiceCommande;
import Services.ServiceLigneCommande;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author asus
 */
//Afficher les produits
public class PanierAdminController implements Initializable {

    @FXML
    private Node menuComponent;
    @FXML
    private TableView<Produit> listProduit;
    @FXML
    private TableColumn<Produit, String> NomColumn;
    @FXML
    private TableColumn<Produit, String> DescriptionColumn;
    private TableColumn<Produit, String> EtatColumn;
    @FXML
    private Button btnRechercher;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<?, ?> CategorieColumn;
    @FXML
    private ImageView ImageProduit;
    @FXML
    private TableColumn<?, ?> PrixColumn;
    @FXML
    private TextArea textDescription;
    TableView.TableViewSelectionModel<Produit> selectionModel;
    private Label TitreMesProduits;
    @FXML
    private Label TitreProduits;
    private Button btnOffre;
    private Button btnAction;
    private int id_Commande;
    private ArrayList<Integer> panier;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnSupprimer;
    private Float sommeProduit = 0f;
    @FXML
    private TextField Somme;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(int idCommande) throws SQLException {
        //l'utilisateur veut acheter un produit
        this.id_Commande = idCommande;
        System.out.println("//////////////////////////");
        System.out.println(this.id_Commande);
        System.out.println("//////////////////////////");
        PopulateTable();

    }

    public void supprimer() throws IOException {

        int selectedIndex = listProduit.getSelectionModel().getSelectedIndex();
//
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun produit selectionné!");
            alert.setContentText("Veuiller selectionner un produit pour voir les offres.");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Look, a Confirmation Dialog");
                alert.setContentText("Are you ok with this?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    CRUDProduit ser = new CRUDProduit();
                    ser.supprimerproduit(listProduit.getSelectionModel().getSelectedItem().getId_produit());
                    PopulateTable();

                } else {

                }
                throw new SQLException("Sample SQLException");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public void PopulateTable() throws SQLException {
        CRUDProduit cr = new CRUDProduit();
        ServiceLigneCommande serLigne = new ServiceLigneCommande();
        List<LigneCommande> LigneList = serLigne.selectByIdCommande(this.id_Commande);
        List<Integer> prList = new ArrayList<>();
        for (LigneCommande l : LigneList) {
            prList.add(l.getId_produit()); // Ajouter le nom de chaque personne à la liste des noms
        }

        List<Produit> li = new ArrayList<>();
        for (int i = 0; i < prList.size(); i++) {
            System.out.println(prList.get(i));
            Produit p = cr.getProduitByid(prList.get(i));
            sommeProduit = sommeProduit + p.getPrix();
            li.add(p);
        }

        textDescription.setVisible(false);

        Somme.setText(Float.toString(sommeProduit));
        Somme.setEditable(false);
        //List<Produit> li =cr.afficherproduitPrix();
        ObservableList<Produit> data = FXCollections.observableArrayList(li);
        NomColumn.setCellValueFactory(
                new PropertyValueFactory<>("nom_produit"));
        CategorieColumn.setCellValueFactory(
                new PropertyValueFactory<>("Categorie"));
        DescriptionColumn.setCellValueFactory(
                new PropertyValueFactory<>("Description"));
        PrixColumn.setCellValueFactory(
                new PropertyValueFactory<>("Prix"));
        listProduit.setItems(data);

        listProduit.setRowFactory(tv -> {
            TableRow<Produit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Produit rowData = row.getItem();
                    Blob blob = rowData.getImage();
                    InputStream inputStream;
                    try {
                        inputStream = blob.getBinaryStream();
                        Image image = new Image(inputStream);
                        ImageProduit.setImage(image);
                        textDescription.setVisible(true);
                        textDescription.setText(rowData.getDescription());
                    } catch (SQLException ex) {
                        Logger.getLogger(PanierAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            return row;
        });

    }

//    @FXML
//    public void Acheter(ActionEvent event) throws ParseException, SQLException {
//        if (sommeProduit > 0) {
//            CRUDUtilisateur crUser = new CRUDUtilisateur();
//            CRUDProduit crProduit = new CRUDProduit();
//            ServiceCommande crCommande = new ServiceCommande();
//            ServiceLigneCommande crLigne = new ServiceLigneCommande();
//
//            Utilisateur user = crUser.rechercheUserbyid(id_user);
//            //Ajouter une commande
//            Commande c = new Commande(id_user, user.getAdresse(), Integer.toString(user.getNumero_telephone()), sommeProduit, 0, "Confirmé");
//            int id_commande = crCommande.createOne(c);
//            //Ajouter des ligne de commande
//            for (int i = 0; i < this.panier.size(); i++) {
//
//                Produit p = crProduit.getProduitByid(this.panier.get(i));
//                Utilisateur userProduit = crUser.rechercheUserbyid(p.getId_user());
//
//                LigneCommande ligne = new LigneCommande(p.getPrix(), id_commande, p.getId_produit(), userProduit.getAdresse());
//                crLigne.createOne(ligne);
//            }
//            Parent root;
//            try {
//                root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
//                Scene cc = new Scene(root);
//                Stage stage = (Stage) btnRetour.getScene().getWindow();
//                stage.setScene(cc);
//            } catch (IOException ex) {
//                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Commande ajouté");
//            alert.setContentText("Commade effectué avec succés");
//            alert.show();
//
//            //Envoyer un message par sms
//            crCommande.sendSms("+21695337153", "Votre commande est envoyé , un livreur prendra votre commande");
//        } else {
//
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setContentText("Panier vide!");
//            alert.show();
//        }
//
//    }
//
    @FXML
    public void rechercher(ActionEvent event) throws IOException {
//        MetierReclamation met = new MetierReclamation();
//        //ServiceUser sca = new ServiceUser();
//        System.out.println("/////////////recherche//////////");
//        System.out.println(recherche.getText());
//        ObservableList<reclamations> data = FXCollections.observableArrayList(met.SearchByName(recherche.getText()));
//        System.out.println(data);
//        NomColumn.setCellValueFactory(new PropertyValueFactory<>("id_client"));
//        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
//        EtatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
//        listReclamation.setItems(data);

    }

    @FXML
    private void Supprimer(ActionEvent event) {
        ServiceCommande cr = new ServiceCommande();
        System.out.println("-------------------------------");
        System.out.println(this.id_Commande);
        System.out.println("--------------------------");

        cr.supprimerCommande(this.id_Commande);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Commande supprimé avec succées");
        alert.setContentText("");
        alert.show();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("roubafikaHome.fxml"));
            Scene c = new Scene(root);
            Stage stage = (Stage) btnRetour.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        sommeProduit = 0f;
//        int idpr = listProduit.getSelectionModel().getSelectedItem().getId_produit();
//        this.panier.remove(Integer.valueOf(idpr));
//        PopulateTable();
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeCommandeAdmin.fxml"));
            Parent root = loader.load();

            btnRetour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
