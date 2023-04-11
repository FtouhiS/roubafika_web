/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roubafika;

import Entities.Session;
import Entities.Utilisateur;
import Services.CRUDUtilisateur;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class MenuFXMLController implements Initializable {

    @FXML
    private VBox menuBar;
    @FXML
    private HBox ServicesModule;
    @FXML
    private Button ServicesButton;
    @FXML
    private ImageView logo;
    @FXML
    private HBox ProduitModule;
    @FXML
    private HBox ReclamationModule;

    @FXML
    private ImageView iconService;
    @FXML
    private ImageView iconProduit;
    @FXML
    private ImageView iconReclamation;

    @FXML
    private Button ProduitButton;
    @FXML
    private Button ReclamationButton;
    @FXML
    private HBox EchangeModule;
    @FXML
    private ImageView iconReclamation1;
    @FXML
    private Button EchangeButton;
    @FXML
    private HBox CommandeModule;
    @FXML
    private ImageView iconReclamation2;
    @FXML
    private Button CommandeButton;

    private int id_user;
    @FXML
    private HBox LogoutModule;
    @FXML
    private ImageView iconReclamation21;
    @FXML
    private Button LogoutButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utilisateur currentUser = Session.getCurrentUser();
        String currentUserRole = currentUser.getRoleU();
        System.out.println(currentUserRole);
        if (currentUserRole.trim().equals("user")) {
            logo.setOnMouseClicked(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("roubafikaHome.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            ServicesButton.setOnAction(event -> {
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
            ProduitButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitVente.fxml"));
                    Parent root = loader.load();
                    AfficherProduitVenteController dcc = loader.getController();
                    //user ca=listStaff.getSelectionModel().getSelectedItem();
                    int idUser = 13;
                    ArrayList<Integer> panier = new ArrayList<Integer>();
                    dcc.initData(idUser, panier);
                    ServicesButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            ReclamationButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                    Parent root = loader.load();
                    ReclamationController dcc = loader.getController();
                    //user ca=listStaff.getSelectionModel().getSelectedItem();
                    int idUser = 13;
                    ArrayList<Integer> panier = new ArrayList<Integer>();
                    dcc.initData(idUser, panier);
                    ServicesButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            LogoutButton.setOnAction(event -> {
                try {
                    Session.startSession(Session.getCurrentUser());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    Parent root = loader.load();
                    ServicesButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            EchangeModule.setVisible(false);
            EchangeModule.setManaged(false);
            CommandeModule.setVisible(false);
            CommandeModule.setManaged(false);
        }
        if (currentUserRole.trim().equals("admin")) {
            logo.setOnMouseClicked(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("roubafikaHome.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            ServicesButton.setOnAction(event -> {
                try {
                    Parent Liste = FXMLLoader.load(getClass().getResource("ServiceFXML.fxml"));

                    Scene si = new Scene(Liste);
                    si.getStylesheets().add(getClass().getResource("card.css").toExternalForm());
                    Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    st.setScene(si);
                    st.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            ProduitButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitAdmin.fxml"));
                    Parent root = loader.load();
                    AfficherProduitAdminController dcc = loader.getController();
                    //user ca=listStaff.getSelectionModel().getSelectedItem();
                    int idUser = 13;
                    ArrayList<Integer> panier = new ArrayList<Integer>();
                    dcc.initData(idUser);
                    ServicesButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            ReclamationButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
                    Parent root = loader.load();
                    AfficherReclamationController dcc = loader.getController();
                    //user ca=listStaff.getSelectionModel().getSelectedItem();
                    int idUser = 13;
                    ArrayList<Integer> panier = new ArrayList<Integer>();
                    ServicesButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            EchangeButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeEchangeAdmin.fxml"));
                    Parent root = loader.load();
                    ListeEchangeAdminController dcc = loader.getController();
                    try {
                        dcc.initData(currentUser.getIdUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int idUser = 13;
                    ArrayList<Integer> panier = new ArrayList<Integer>();

                    ServicesButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            CommandeButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeCommandeAdmin.fxml"));
                    Parent root = loader.load();

                    //user ca=listStaff.getSelectionModel().getSelectedItem();
                    ServicesButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            LogoutButton.setOnAction(event -> {
                try {
                    Session.startSession(Session.getCurrentUser());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    Parent root = loader.load();
                    ServicesButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
        if (currentUserRole.trim().equals("livreur")) {
            logo.setOnMouseClicked(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("roubafikaHome.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Image livreurIcon = new Image("/img/livreur.png");
            iconService.setImage(livreurIcon);
            ServicesButton.setText("Livraison");
            ServicesButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("OffreConfirme.fxml"));
                    Parent root = loader.load();
                    OffreConfirmeController dcc = loader.getController();
                    //user ca=listStaff.getSelectionModel().getSelectedItem();
                    int idUser = 16;

                    dcc.initData(idUser);
                    ServicesButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            ProduitButton.setText("Mes Livraisons");
            ProduitButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("EchangeEnCours.fxml"));
                    Parent root = loader.load();
                    EchangeEnCoursController dcc = loader.getController();
                    //user ca=listStaff.getSelectionModel().getSelectedItem();
                    int idUser = 16;

                    dcc.initData(idUser);
                    ServicesButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            LogoutButton.setOnAction(event -> {
                try {
                    Session.startSession(Session.getCurrentUser());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    Parent root = loader.load();
                    ServicesButton.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            ReclamationModule.setVisible(false);
            ReclamationModule.setManaged(false);
            EchangeModule.setVisible(false);
            EchangeModule.setManaged(false);
            CommandeModule.setVisible(false);
            CommandeModule.setManaged(false);
        }

    }

    public void initData(int idUser) throws SQLException {
        this.id_user = idUser;

    }

    @FXML
    private void logout(ActionEvent event) {
    }

}
