/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roubafika;

import Entities.Demande;
import Entities.Service;
import Entities.Session;
import Entities.Utilisateur;
import Services.MyDemande;
import Services.MyService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FrontServiceController implements Initializable {

    @FXML
    private VBox cardsContainer;

    private MyService cs = new MyService();
    private Button ServicesButton;
    @FXML
    private Button listeService;
    @FXML
    private Button addService;
    @FXML
    private Button mesServices;
    private Stage ajouterStage;
    private ImageView logo;
    @FXML
    private Node menuComponent;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @FXML
    private Button ServicesD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyDemande demande_service = new MyDemande();
        UserService user_service = new UserService();
        int id_current_user = 2;

        ObservableList<Service> allCards = cs.affichage();
        int numCards = allCards.size();
        int cardsPerRow = 3;

        for (int i = 0; i < numCards; i += cardsPerRow) {
            HBox hbox = new HBox();
            hbox.setSpacing(70);
            hbox.setPadding(new Insets(30));
            hbox.getStyleClass().add("card-row");

            int endIndex = Math.min(i + cardsPerRow, numCards);
            List<Service> rowCards = allCards.subList(i, endIndex);

            for (Service c : rowCards) {
                BorderPane cardPane = new BorderPane();
                cardPane.getStyleClass().add("card-pane");

                VBox vb = new VBox();
                VBox vbempty = new VBox();
                vbempty.setPrefHeight(20);
                vb.setSpacing(10);
                vb.getStyleClass().add("card-container");
                Label titleLabel = new Label(c.getTitre());
                titleLabel.getStyleClass().add("card-title");
                Label categorieLabel = new Label(c.getCategorie().toString());
                categorieLabel.getStyleClass().add("card-categorie");

                Label descText = new Label(c.getDescription());
                descText.getStyleClass().add("card-desc");
                descText.setWrapText(true);
                descText.setMaxWidth(280);
                vb.getChildren().addAll(titleLabel, categorieLabel, descText);
                vb.prefWidthProperty().bind(hbox.widthProperty().divide(cardsPerRow).subtract(70));
                cardPane.setTop(vb);

                cardPane.setCenter(vbempty);
                if(id_current_user == c.getIdUser()){
                    
                }
                else {
                    Button demanderBtn = new Button("Demander");
                demanderBtn.getStyleClass().add("card-button");
                cardPane.setBottom(demanderBtn);
                demanderBtn.setOnAction(event -> {
                    Demande parti = new Demande();
                    Utilisateur user = user_service.UserById(1);
                    
                    parti.setId_serv(c.getId());
                    parti.setNom_demandeur(user.getNom());
                    parti.setPrenom_demandeur(user.getPrenom());
                    parti.setEmail_demandeur(user.getAdresse_mail());
                    parti.setIdUser(id_current_user);
                    LocalDate currentDate = LocalDate.now();
                    String formattedDate = currentDate.format(formatter);
                    parti.setDate_demande(formattedDate);
                    try {
                        demande_service.ajouterDemande(parti);
                        demande_service.sendMail("Demande effectué avec succée", Session.getCurrentUser().getAdresse_mail());
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Operation completed successfully.");

                        // Display the alert box
                        alert.show();
                        // Create a delay of 3 seconds
                        PauseTransition delay = new PauseTransition(Duration.seconds(3));
                        delay.setOnFinished(e -> alert.close());
                        delay.play();

                    } catch (SQLException ex) {
                        Logger.getLogger(FrontServiceController.class.getName()).log(Level.SEVERE, null, ex);
                    }   catch (Exception ex) {
                            Logger.getLogger(FrontServiceController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                });
                
                vb.setMargin(demanderBtn, new Insets(10, 0, 0, 0));
                }
                

                hbox.getChildren().add(cardPane);
            }

            cardsContainer.getChildren().add(hbox);
        }
        addService.setOnAction(event -> {
            try {
                Parent Liste = FXMLLoader.load(getClass().getResource("frontAddService.fxml"));

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

    private void loadFrontService() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("frontService.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) addService.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
