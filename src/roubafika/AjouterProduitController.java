/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package roubafika;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Entities.Echange;
import Entities.Produit;
import Entities.Session;
import Entities.Utilisateur;
import Services.CRUDEchange;
import Services.CRUDProduit;
import Services.CRUDUtilisateur;
import Services.ReclamationService;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterProduitController implements Initializable {

    private ImageView ImageProduit;
    private TableColumn<?, ?> PrixColumn;
    private TextArea textDescription;
    private Label TitreMesProduits;
    private Label TitreProduits;
    private Button btnAction;
    @FXML
    private Node menuComponent;
    @FXML
    private Button btnValider;
    @FXML
    private Label TitreProduitUser;
    @FXML
    private TextField nomProduitUser;
    @FXML
    private TextArea descriptionProduitUser;
    @FXML
    private ImageView imagProduitUser;
    @FXML
    private TextField PrixProduitUser;

    int id_utilisateur;
    int id_produit;
    int id_produitOffre;
    @FXML
    private Button btnRetour;
    @FXML
    private Button AjouterImage;
    @FXML
    private TextField CategorieProduitUser;
    private Blob blob;
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

    public void initData(int idUser) throws SQLException {
        this.id_utilisateur = idUser;
        // Crée un bouton "Choisir une image"

        // Crée un objet ImageView pour afficher l'image sélectionnée
        ImageView imageView = new ImageView();

        // Ajoute un écouteur d'événements sur le bouton
        AjouterImage.setOnAction(event -> {
            // Crée un objet FileChooser et configure ses propriétés
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif", "*.jfif"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("JPEG", "*.jpg"),
                    new FileChooser.ExtensionFilter("GIF", "*.gif"),
                    new FileChooser.ExtensionFilter("JFIF", "*.jfif"));

            // Affiche la boîte de dialogue de sélection de fichier et attend que l'utilisateur sélectionne un fichier d'image
            Stage stage = (Stage) btnValider.getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);

            // Traite le fichier sélectionné
            if (selectedFile != null) {
                try {
                    Image image = new Image(new FileInputStream(selectedFile));
                    imageView.setImage(image);
                    this.blob = createBlob(selectedFile);
                    InputStream inputStream;
                    inputStream = this.blob.getBinaryStream();
                    Image im = new Image(inputStream);
                    imagProduitUser.setImage(im);

                    // You can now use the 'blob' variable to store the image in a database, for example.
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException ex) {
                    Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private Blob createBlob(File file) throws SQLException, FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = fis.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        return blob;
    }

    @FXML
    private void Valider(ActionEvent event) throws IOException {
        CRUDProduit cr = new CRUDProduit();
        String nomPr = nomProduitUser.getText();
        String descrip = descriptionProduitUser.getText();
        String txtprix = PrixProduitUser.getText();
        String categ = CategorieProduitUser.getText();
        System.out.println(validate(txtprix));
        if (validate(txtprix) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Veuillez saisir un prix valable");
            alert.show();

        } else if (nomPr.isEmpty() || descrip.isEmpty() || categ.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Veuillez saisir tous les chamaps");
            alert.show();

        } else {
            Float prix = Float.parseFloat(PrixProduitUser.getText());
            Produit p = new Produit(id_utilisateur, categ, nomPr, descrip, this.blob, prix);
            cr.ajouterproduit(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Produit ajouté");
            alert.setContentText("Merci dajouter ce produit");
            alert.show();
            retourProduit();
        }

    }

    public static boolean validate(String price) {
        String PRICE_PATTERN = "^[0-9]+(\\.[0-9]{1,2})?$";

        Pattern pattern = Pattern.compile(PRICE_PATTERN);
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }

    private void retourProduit() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
            Parent root = loader.load();
            AfficherProduitController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            dcc.initData(id_utilisateur, 0, 0, 0);
            btnRetour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Echanger Un produit pleaaase");
    }

    @FXML
    private void retour(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
            Parent root = loader.load();
            AfficherProduitController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            dcc.initData(id_utilisateur, 0, 0, 0);
            btnRetour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Echanger Un produit pleaaase");
    }
    @FXML
    private void AcheterProduits(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitVente.fxml"));
            Parent root = loader.load();
            AfficherProduitVenteController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idUser = 14;
            ArrayList<Integer> panier = new ArrayList<Integer>();
            dcc.initData(idUser, panier);
            btnRetour.getScene().setRoot(root);
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
            btnRetour.getScene().setRoot(root);
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
            btnRetour.getScene().setRoot(root);
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
            dcc.initData(Session.getCurrentUser().getIdUser(), 1, 0, 0);
            btnRetour.getScene().setRoot(root);
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
            btnRetour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
