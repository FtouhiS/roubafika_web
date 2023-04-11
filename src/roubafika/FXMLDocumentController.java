/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roubafika;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Entities.Service;
import Services.MyService;
import java.time.LocalDate;
import Entities.Service;
import Entities.Service.Categorie;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Node menuComponent;
    private Categorie categorieSelectionnee;
    @FXML
    private Label label;
    @FXML
    private TableView<Service> ListA;
    @FXML
    private TableColumn<Service, String> TitreA;
    @FXML
    private TableColumn<Service, String> DescriptionA;
    @FXML
    private TableColumn<Service, String> DateA;
    @FXML
    private TableColumn<Service, String> AdresseA;
    @FXML
    private TableColumn<Service, Categorie> CategorieA1;
    @FXML
    private TextField libelleDescription;
    @FXML
    private TextField libelleTitre;
    @FXML
    private DatePicker libelleDate;
    @FXML
    private TextField libelleAdresse;
    /*@FXML
    //private ChoiceBox libelleCategorie;
    private ChoiceBox<Categorie> libelleCategorie;*/
    @FXML
    private RadioButton buttonTransport;
    @FXML
    private RadioButton buttonPlomberie;

    @FXML
    private Button ajoutA;
    @FXML
    private Button modA;
    @FXML
    private Button supA;
    private Label erreurTitre;
    @FXML
    private Label erreurDescription;
    @FXML
    private Label erreurDate;
    @FXML
    private Label erreurAdresse;

    @FXML
    private Pane pnlStatus;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @FXML
    private Label lblstatus;
    @FXML
    private Label erreurtitre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAll();
    }

    @FXML
    private void inscrire(ActionEvent event) {
        MyService serv = new MyService();

        if (libelleTitre.getText().isEmpty()) {
            erreurTitre.setText("Titre non valide !");
            erreurTitre.setVisible(true);

            return;
        }
        if (libelleDescription.getText().isEmpty()) {
            erreurDescription.setText("Description non valide !");
            erreurDescription.setVisible(true);

            return;
        }
        LocalDate selectedDate = libelleDate.getValue();

// Check if a date has been selected
        if (selectedDate == null) {
            erreurDate.setText("Date non valide !");
            erreurDate.setVisible(true);
            return;
        }

// Convert the selected date to a string if needed
        String dateString = selectedDate.toString();

        if (libelleAdresse.getText().isEmpty()) {
            erreurAdresse.setText("Adresse non valide !");
            erreurAdresse.setVisible(true);

            return;
        }
        ToggleGroup categorieToggleGroup = new ToggleGroup();
        buttonTransport.setToggleGroup(categorieToggleGroup);
        buttonPlomberie.setToggleGroup(categorieToggleGroup);

//categorieToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
//    if (newValue == buttonTransport) {
//        // Handle selection of the Transport category
//        categorieSelectionnee = Categorie.transport;
//    } else if (newValue == buttonPlomberie) {
//        // Handle selection of the Plomberie category
//        categorieSelectionnee = Categorie.plomberie;
//    } else {
//        // No category selected
//        categorieSelectionnee = null;
//    }
//});
        Categorie categorieSelectionnee = null;
        if (buttonTransport.isSelected()) {
            categorieSelectionnee = Categorie.transport;
        } else if (buttonPlomberie.isSelected()) {
            categorieSelectionnee = Categorie.plomberie;
        }
        /*if (libelleCategorie.getText().isEmpty())
         {
                        
                        
                        return;
                    }*/

// Get the selected category from the ChoiceBox
/*Object selectedCategory = libelleCategorie.getSelectionModel().getSelectedItem();

// Check if a category has been selected
//if (selectedCategory == null) {
    // No category selected, do something
} else {
    // Category selected, do something else
}*/
        Service service = new Service();

        service.setTitre(libelleTitre.getText());

        service.setDescription(libelleDescription.getText());
        System.out.println(libelleDate.getValue().toString());
        String formattedDate = libelleDate.getValue().format(formatter);
        service.setDate_annonce(formattedDate);

        service.setAdresse(libelleAdresse.getText());
        service.setCategorie(categorieSelectionnee);
        service.setIdUser(1);

        //service.setCategorie(Categorie.plomberie);
        serv.ajouterService(service);

        showAll();

    }

    @FXML
    private void onsave(MouseEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {

        Categorie categorieSelectionnee = null;
        if (buttonTransport.isSelected()) {
            categorieSelectionnee = Categorie.transport;
        } else if (buttonPlomberie.isSelected()) {
            categorieSelectionnee = Categorie.plomberie;
        }

        Service f = new Service(libelleTitre.getText(), libelleDescription.getText(), libelleDate.getValue().toString(), libelleAdresse.getText(), categorieSelectionnee, 1);
        System.out.println(f);
        MyService serv = new MyService();
        serv.updateService(f, ListA.getSelectionModel().getSelectedItem().getTitre());

        showAll();

    }

    @FXML
    private void supprimer(ActionEvent event) {
        MyService s = new MyService();
        System.out.println(ListA.getSelectionModel().getSelectedItem().getTitre());
        s.deleteService(ListA.getSelectionModel().getSelectedItem().getTitre());
        showAll();
    }

    public void showAll() {
        MyService serv = new MyService();
        ObservableList<Service> s = serv.affichage();

        TitreA.setCellValueFactory(new PropertyValueFactory<Service, String>("Titre"));
        DescriptionA.setCellValueFactory(new PropertyValueFactory<Service, String>("Description"));
        DateA.setCellValueFactory(new PropertyValueFactory<Service, String>("Date_annonce"));
        AdresseA.setCellValueFactory(new PropertyValueFactory<Service, String>("Adresse"));
        CategorieA1.setCellValueFactory(new PropertyValueFactory<Service, Categorie>("Categorie"));

        ListA.setItems(s);

        boolean updateColumnExists = false;
        for (TableColumn<?, ?> column : ListA.getColumns()) {
            if (column.getText().equals("Update")) {
                updateColumnExists = true;
                break;
            }
        }
        if (!updateColumnExists) {
            TableColumn<Service, Void> updateCol = new TableColumn<>("Update");
            updateCol.setStyle("-fx-background-color: #FFFF5D;");
            updateCol.setCellFactory(param -> new TableCell<Service, Void>() {
                private final Button updateButton = new Button("Update");

                private final Button deleteButton = new Button("Delete");

                {
                    updateButton.setOnAction(event -> {
                        Service data = getTableView().getItems().get(getIndex());
                        System.out.println("Update button clicked for row: " + data);
                        libelleTitre.setText(data.getTitre());
                        libelleDescription.setText(data.getDescription());
                        libelleDate.setValue(LocalDate.parse(data.getDate_annonce(), formatter));
                        libelleAdresse.setText(data.getAdresse());
                        Categorie c = data.getCategorie();

                        if (c == Categorie.transport) {
                            buttonTransport.setSelected(true);
                        } else if (c == Categorie.plomberie) {
                            buttonPlomberie.setSelected(true);
                        }

                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(updateButton);
                    }
                }
            });
            ListA.getColumns().add(updateCol);
        }
    }
}
