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
import Entities.Produit;
import Entities.Session;
import Services.CRUDProduit;
import Services.CRUDUtilisateur;
import Services.ReclamationService;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author asus
 */
//Afficher les produits
public class AfficherProduitController implements Initializable {

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
    @FXML
    private Button btnOffre;
    @FXML
    private Button btnAction;
    private int id_user;
    @FXML
    private Button btnAjouter;
    @FXML
    private Node menuComponent;
    @FXML
    private Button listeProduit;
    @FXML
    private Button addProduit;
    @FXML
    private Button mesProduits;
    @FXML
    private Button echangerProduit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(int idUser, int echange, int acheter, int id_produitOffre) throws SQLException {
        //l'utilisateur veut acheter un produit
        btnAjouter.setVisible(false);
        this.id_user = idUser;
        if (acheter == 1) {
            PopulateTable();
            selectionModel = listProduit.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
            btnOffre.setText("Ajouter au panier");

            btnAction.setOnAction(event -> {
                try {
                    Acheter();
                } catch (ParseException ex) {
                    Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            btnOffre.setOnAction(event -> {
                Ajouter_au_panier();
            });
        } //l'utilisateur veut echanger un prduit
        else if (echange == 1) {
            TitreProduits.setText("Echanger Produits");
            btnAction.setText("Echanger");
            PopulateTable();
//            selectionModel = listProduit.getSelectionModel();
//            selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
            btnAction.setOnAction(event -> {
                try {
                    Echanger(Session.getCurrentUser().getIdUser(), id_produitOffre);
                } catch (ParseException ex) {
                    Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } //L'utilisateur veut visiter ses produits
        else if (acheter == 0 && echange == 0 && id_produitOffre == 0) {
            PopulateMonTable(Session.getCurrentUser().getIdUser());
            recherche.setVisible(false);
            btnRechercher.setVisible(false);
            TitreProduits.setText("Mes Produits");
            btnAjouter.setVisible(true);
            btnAction.setText("Supprimer");
            btnAction.setOnAction(event -> {
                try {
                    supprimer();
                } catch (IOException ex) {
                    Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } //L'utilisateur va choisir un de ses produits pour faire un echange
        else if (id_produitOffre != 0) {
            btnAction.setText("Echanger");
            btnOffre.setVisible(false);
            PopulateMonTable(idUser);
//            selectionModel = listProduit.getSelectionModel();
//            selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
            btnAction.setOnAction(event -> {
                ValiderEchange(Session.getCurrentUser().getIdUser(), id_produitOffre);
            });

        }

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

    public void PopulateMonTable(int id_user) {
        CRUDProduit cr = new CRUDProduit();
        textDescription.setVisible(false);
        List<Produit> li = cr.afficher_user_produit(id_user);
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
                        Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // Effectuer l'action souhaitée avec la ligne sélectionnée
                    // Par exemple :

                }
            });
            return row;
        });
    }

    public void PopulateTable() {
        CRUDProduit cr = new CRUDProduit();
        textDescription.setVisible(false);
        List<Produit> li = cr.afficherproduit();
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
                        Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // Effectuer l'action souhaitée avec la ligne sélectionnée
                    // Par exemple :

                }
            });
            return row;
        });

    }

    public void initierReclamation(ActionEvent event) throws ParseException {
//        int selectedIndex = listReclamation.getSelectionModel().getSelectedIndex();
//        if (selectedIndex < 0 ) {
//           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//           alert.setTitle("Erreur");
//           alert.setHeaderText("Aucune reclamation selectionnée!");
//           alert.setContentText("Veuiller selectionner une réclamation à modifier");
//           Optional<ButtonType> result = alert.showAndWait();
//       }
//        else{
//        int idRec=listReclamation.getSelectionModel().getSelectedItem().getId();
//        textEtat.setText(listReclamation.getSelectionModel().getSelectedItem().getEtat());
//        //textNom.setText(listReclamation.getSelectionModel().getSelectedItem().getid_client());
//        textDescription.setText(listReclamation.getSelectionModel().getSelectedItem().getDescription());
//        
//        }

    }

    public void Acheter() throws ParseException {
        System.err.println("Acheter Un produit pleaaase");
    }

    public void Echanger(int idUser, int id_produitOffre) throws ParseException {

        int idpr = listProduit.getSelectionModel().getSelectedItem().getId_produit();
        if (listProduit.getSelectionModel().getSelectedItem().getId_user() == Session.getCurrentUser().getIdUser()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("C'est votre produit!");
            alert.show();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
                Parent root = loader.load();
                AfficherProduitController dcc = loader.getController();
                //user ca=listStaff.getSelectionModel().getSelectedItem();
                dcc.initData(Session.getCurrentUser().getIdUser(), 0, 0, idpr);
                btnAction.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.err.println("Echanger Un produit pleaaase");
        }
    }

    public void modifier() throws ParseException {
        System.out.println("Modifiiiiier");
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        if (textNom.getText().isEmpty()| textDescription.getText().isEmpty() | textEtat.getText().isEmpty()){
//            alert.setTitle("Reclamation");
//            alert.setContentText("Voun ne pouvez pas modifier une reclamation avec un champ vide!!");
//            alert.show();
//        }
//        else{
//        int idRec=listReclamation.getSelectionModel().getSelectedItem().getId();
//        reclamations r = new reclamations(idRec,Integer.parseInt(textNom.getText()), textDescription.getText(),textEtat.getText());
//        CrudReclamation crud = new CrudReclamation();
//        crud.modifierreclamation(r);
//        textEtat.clear();
//        textNom.clear();
//        textDescription.clear();
//        
//            ObservableList<reclamations> data = FXCollections.observableArrayList(crud.afficherreclamation());
//            NomColumn.setCellValueFactory(new PropertyValueFactory<>("id_client"));
//            DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
//            EtatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
//            listReclamation.setItems(data);
//            alert.setTitle("Reclamation");
//            alert.setContentText("Reclamaton Modifiée Avec succées");
//            alert.show();
//    }
    }

    @FXML
    public void rechercher(ActionEvent event) throws IOException {
        PopulateTableByName();
         
         
//        //ServiceUser sca = new ServiceUser();
//        System.out.println("/////////////recherche//////////");
//        System.out.println(recherche.getText());
//        System.out.println(data);
//        NomColumn.setCellValueFactory(new PropertyValueFactory<>("id_client"));
//        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
//        EtatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
//        listReclamation.setItems(data);

    }
public void PopulateTableByName() {
        CRUDProduit cr = new CRUDProduit();
        textDescription.setVisible(false);
        List<Produit> li = cr.afficherproduit();
        ObservableList<Produit> data = FXCollections.observableArrayList(cr.afficherproduitParNom(recherche.getText()));
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
                        Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // Effectuer l'action souhaitée avec la ligne sélectionnée
                    // Par exemple :

                }
            });
            return row;
        });

    }
    private void repondre(ActionEvent event) throws SQLException {

// Activez la sélection multiple
//// Sélectionnez les deuxième et troisième lignes
//        selectionModel.select(1);
//        selectionModel.select(2);
        System.out.println("repondre");
        ObservableList<Produit> selectedItems = listProduit.getSelectionModel().getSelectedItems();
        System.out.println("//////////////////");
        System.out.println(selectedItems);
        System.out.println("//////////////////");
        for (Produit obj : selectedItems) {
            System.out.println(obj.getId_produit());
        }

//        try {
//            FXMLLoader loader=new FXMLLoader(getClass().getResource("ReponseReclamation.fxml"));
//            Parent root = loader.load();
//            ReponseReclamationController dcc=loader.getController();
//            //user ca=listStaff.getSelectionModel().getSelectedItem();
//            int idRec=listReclamation.getSelectionModel().getSelectedItem().getId();
//            dcc.initData(idRec);
//            btnModifier.getScene().setRoot(root);
//        } catch (IOException ex) {
//            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void ValiderEchange(int idUser, int id_produitOffre) {
        int idpr = listProduit.getSelectionModel().getSelectedItem().getId_produit();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ValiderEchange.fxml"));
            Parent root = loader.load();
            ValiderEchangeController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            dcc.initData(Session.getCurrentUser().getIdUser(), idpr, id_produitOffre);
            btnAction.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Echanger Un produit pleaaase");

    }

    @FXML
    private void MesOffres(ActionEvent event) {
        int idpr = listProduit.getSelectionModel().getSelectedItem().getId_produit();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MesOffres.fxml"));
            Parent root = loader.load();
            MesOffresController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            dcc.initData(Session.getCurrentUser().getIdUser(), idpr);
            btnOffre.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Echanger Un produit pleaaase");

    }

    private void Ajouter_au_panier() {

    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterProduit.fxml"));
        Parent root = loader.load();
        AjouterProduitController dcc = loader.getController();
        //user ca=listStaff.getSelectionModel().getSelectedItem();
        dcc.initData(Session.getCurrentUser().getIdUser());
        btnOffre.getScene().setRoot(root);

    }

    @FXML
    private void AcheterProduits(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitVente.fxml"));
            Parent root = loader.load();
            AfficherProduitVenteController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            ArrayList<Integer> panier = new ArrayList<Integer>();
            dcc.initData(Session.getCurrentUser().getIdUser(), panier);
            listProduit.getScene().setRoot(root);
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

            dcc.initData(Session.getCurrentUser().getIdUser(), 0, 0, 0);
            listProduit.getScene().setRoot(root);
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

            dcc.initData(Session.getCurrentUser().getIdUser(), 1, 0, 0);
            listProduit.getScene().setRoot(root);
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
            AjouterProduitController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();

            dcc.initData(Session.getCurrentUser().getIdUser());
            listProduit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void stats(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StatProduit.fxml"));
            Parent root = loader.load();
            StatProduitController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            ArrayList<Integer> panier = new ArrayList<Integer>();
            try {
                dcc.initData(Session.getCurrentUser().getIdUser());
            } catch (ParseException ex) {
                Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
            listProduit.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
