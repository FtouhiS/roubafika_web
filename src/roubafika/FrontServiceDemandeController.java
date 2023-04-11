/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roubafika;

import Entities.Demande;
import Entities.Service;
import Services.MyDemande;
import Services.MyService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class FrontServiceDemandeController implements Initializable {

    @FXML
    private VBox cardsContainer;
    @FXML
    private Button listeService;
    @FXML
    private Button addService;
    @FXML
    private Button mesServices;
    @FXML
    private Button ServicesD;

    private MyDemande cs = new MyDemande();

    private MyService ds = new MyService();
    private UserService us = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Demande> allCards = cs.DemandeByUser(2);
        int numCards = allCards.size();
        int cardsPerRow = 3;

        for (int i = 0; i < numCards; i += cardsPerRow) {
            HBox hbox = new HBox();
            hbox.setSpacing(70);
            hbox.setPadding(new Insets(30));
            hbox.getStyleClass().add("card-row");

            int endIndex = Math.min(i + cardsPerRow, numCards);
            List<Demande> rowCards = allCards.subList(i, endIndex);

            for (Demande d : rowCards) {
                BorderPane cardPane = new BorderPane();
                cardPane.getStyleClass().add("card-pane");
                Service c = cs.getServiceByID(d.getId_serv());
                System.out.println(c);
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

}
