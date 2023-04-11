/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package roubafika;

import java.awt.image.BufferedImage;
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
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import Entities.Echange;
import Entities.Produit;
import Entities.Session;
import Entities.Utilisateur;
import Services.CRUDEchange;
import Services.CRUDProduit;
import Services.CRUDUtilisateur;
import Services.ReclamationService;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ValiderEchangeLivreurController implements Initializable {

    private ImageView ImageProduit;

    private TextArea textDescription;
    private Label TitreMesProduits;
    private Label TitreProduits;
    private Button btnAction;
    @FXML
    private Button btnValider;
    @FXML
    private Label TitreProduitOffre;
    @FXML
    private Label TitreProduitUser;
    @FXML
    private TextField nomProduitUser;
    @FXML
    private TextArea descriptionProduitUser;
    @FXML
    private ImageView imagProduitUser;
    @FXML
    private TextField nomProduitOffre;
    @FXML
    private TextArea descriptionProduitOffre;
    @FXML
    private ImageView imagProduitOffre;
    private TextField PrixProduitUser;
    private TextField PrixProduitOffre;
    @FXML
    private Node menuComponent;
    int id_utilisateur;
    int id_produit;
    int id_produitOffre;
    @FXML
    private Button btnRetour;
    @FXML
    private TextField nomClient1;
    @FXML
    private TextField PrenomClient1;
    @FXML
    private TextField CINClient1;
    @FXML
    private TextField TelClient11;
    @FXML
    private TextField nomClient11;
    @FXML
    private TextField PrenomClient11;
    @FXML
    private TextField CINClient11;
    private int idEchange;
    @FXML
    private TextField TelClient1;
    @FXML
    private TextField AdresseUser;
    @FXML
    private TextField AdresseUser1;
    @FXML
    private AnchorPane topdfScene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(int idUser, int id_produit, int id_produitOffre, int id_echange) throws SQLException {
        id_utilisateur = idUser;
        this.id_produit = id_produit;
        this.id_produitOffre = id_produitOffre;
        this.idEchange = id_echange;
        //Remplir les champs des produits
        CRUDProduit cr = new CRUDProduit();
        Produit produitUser = cr.getProduitByid(id_produit);
        Produit produitOffre = cr.getProduitByid(id_produitOffre);
        nomProduitUser.setText(produitUser.getNom_produit());
        descriptionProduitUser.setText(produitUser.getDescription());
        //PrixProduitUser.setText(Float.toString(produitUser.getPrix()));
        Blob blob = produitUser.getImage();
        InputStream inputStream;
        inputStream = blob.getBinaryStream();
        Image image = new Image(inputStream);
        nomProduitUser.setEditable(false);
        descriptionProduitUser.setEditable(false);
        //PrixProduitUser.setEditable(false);
        //PrixProduitOffre.setText(Float.toString(produitOffre.getPrix()));
        imagProduitUser.setImage(image);
        nomProduitOffre.setText(produitOffre.getNom_produit());
        descriptionProduitOffre.setText(produitOffre.getDescription());
        Blob blob2 = produitOffre.getImage();
        InputStream inputStream2;
        inputStream2 = blob2.getBinaryStream();
        Image image2 = new Image(inputStream2);
        imagProduitOffre.setImage(image2);
        //PrixProduitOffre.setEditable(false);
        nomProduitOffre.setEditable(false);
        descriptionProduitOffre.setEditable(false);
        //Remplir les champsdes utilisateurs
        CRUDUtilisateur crUser = new CRUDUtilisateur();
        Utilisateur user1 = new Utilisateur();
        Utilisateur user2 = new Utilisateur();
        user1 = crUser.rechercheUserbyid(produitUser.getId_user());
        user2 = crUser.rechercheUserbyid(produitOffre.getId_user());

        nomClient1.setText(user1.getNom());
        nomClient11.setText(user2.getNom());
        nomClient1.setEditable(false);
        nomClient11.setEditable(false);

        PrenomClient1.setText(user1.getPrenom());
        PrenomClient11.setText(user2.getPrenom());
        PrenomClient1.setEditable(false);
        PrenomClient11.setEditable(false);

        CINClient1.setText(user1.getMdp());
        CINClient11.setText(user2.getMdp());
        CINClient1.setEditable(false);
        CINClient11.setEditable(false);

        TelClient1.setText(Integer.toString(user1.getNumero_telephone()));
        TelClient11.setText(Integer.toString(user2.getNumero_telephone()));
        TelClient1.setEditable(false);
        TelClient11.setEditable(false);

        AdresseUser.setText(user1.getAdresse());
        AdresseUser1.setText(user2.getAdresse());
        AdresseUser.setEditable(false);
        AdresseUser1.setEditable(false);
        CRUDEchange crEchange = new CRUDEchange();
        Echange e = crEchange.afficherEchangeById(id_echange);
        System.out.println("******************");
        System.out.println(e.getStatut().equals("En cours"));
        System.out.println("******************");
        if(e.getStatut().equals("En cours")){
            btnValider.setText("Terminer");
            btnValider.setOnAction(event -> {
                try {
                    Terminer(e);
                } catch (ParseException ex) {
                    Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        }   

    }

    public void Terminer(Echange e) throws ParseException {
        e.setStatut("terminé");
        CRUDEchange cr = new CRUDEchange();
        cr.modifierEchange(e);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echange Terminé");
        alert.setContentText("Merci pour votre travail");
        alert.show();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("roubafikaHome.fxml"));
             root = loader.load();
            roubafikaHomeController dcc = loader.getController();
            dcc.initData(this.id_utilisateur);
            Scene c=new Scene(root);
            Stage stage=(Stage)btnValider.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ValiderEchangeLivreurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void Acheter() throws ParseException {
        System.err.println("Acheter Un produit pleaaase");
    }

    public void Echanger(int idUser, int id_produitEchange) throws ParseException {

//        int idpr=listProduit.getSelectionModel().getSelectedItem().getId_produit();
//        try {
//            FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
//            Parent root = loader.load();
//            ValiderEchangeController dcc=loader.getController();
//            //user ca=listStaff.getSelectionModel().getSelectedItem();
//            List<Integer> produits_echanges = new ArrayList<>();
//            dcc.initData(idUser,0,0,idpr,produits_echanges);
//            btnAction.getScene().setRoot(root);
//        } catch (IOException ex) {
//            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.err.println("Echanger Un produit pleaaase");
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

    private void ValiderEchange(int idUser, int id_produitEchange) {

    }

    @FXML
    private void Valider(ActionEvent event) throws IOException {
        CRUDEchange crEchange = new CRUDEchange();
        Echange e = crEchange.afficherEchangeById(idEchange);
        e.setStatut("En cours");
        e.setLivreur(id_utilisateur);
        crEchange.modifierEchange(e);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        Scene scene = btnValider.getScene();
        WritableImage image = new WritableImage((int) scene.getWidth(), (int) scene.getHeight());
        scene.snapshot(image);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        PDImageXObject pdImage = LosslessFactory.createFromImage(document, bufferedImage);
        contentStream.drawImage(pdImage, 0, 0, page.getMediaBox().getWidth(), page.getMediaBox().getHeight());
        contentStream.close();
        document.save("C:\\Users\\moham\\Documents\\Roubafika\\file.pdf");
        document.close();
        CRUDUtilisateur cr = new CRUDUtilisateur();
        cr.sendSms("+21655335154", "Merci de prendre en charge cet echange");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echange pris en charge");
        alert.setContentText("Merci de prendre en charge cette echange");
        alert.show();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("roubafikaHome.fxml"));
            Scene c = new Scene(root);
            Stage stage = (Stage) btnValider.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//     private void home() throws IOException{
//            Parent root;
//            try {
//            root = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));
//            Scene c=new Scene(root);
//            Stage stage=(Stage)btnEnvoyer.getScene().getWindow();
//            stage.setScene(c);
//        } catch (IOException ex) {
//            Logger.getLogger(AjouterController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//          
//    }

    @FXML
    private void retour(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EchangeEnCours.fxml"));
            Parent root = loader.load();
            EchangeEnCoursController dcc = loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            dcc.initData(Session.getCurrentUser().getIdUser());
            btnRetour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Echanger Un produit pleaaase");
    }
}
