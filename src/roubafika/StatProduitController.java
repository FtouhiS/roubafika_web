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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
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
import javafx.scene.layout.StackPane;
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
public class StatProduitController implements Initializable {

    
    
    
    
    
    int id_utilisateur ;
    int id_produit ;
    int id_produitOffre;
    @FXML
    private Node menuComponent;
    @FXML
    private Button btnRetour;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label TitreProduitOffre;
    @FXML
    private Label TitreProduitUser;
    @FXML
    private StackPane stack;
    @FXML
    private BarChart<?, ?> barC;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
    public void initData(int idUser ) throws SQLException, ParseException {
        this.id_utilisateur=Session.getCurrentUser().getIdUser();
        CRUDProduit cr = new CRUDProduit();
        ArrayList<String> Cat = new ArrayList<String>() ;
        List<Produit> li = new ArrayList<Produit>();
        li =cr.afficher_user_produit(id_utilisateur);
            System.out.println("///////////////////////////////");
            System.out.println(li);
            System.out.println(li.size());
            System.out.println("///////////////////////////////");
            for (int i = 0 ; i<li.size();i++){
                String c=li.get(i).getCategorie();
                System.out.println(li.get(i).getCategorie());
                
                Cat.add(c);
            }
            System.out.println(Cat);
//        for (int i = 0 ; i<li.size();i++){
//            System.out.println("///////////////////////////////");
//            System.out.println(i);
//            System.out.println(li.get(i).getCategorie());
//            System.out.println("///////////////////////////////");
//            
//        }
////        for (Produit p : li){
////            System.out.println("///////////////////////////////");
////            System.out.println(p.getCategorie());
////            System.out.println("///////////////////////////////");
////            Categ.add(p.getCategorie());
////        }
//        System.err.println(Categ);
        stat(Cat);
        
        
            
        
    }
    
   
    
    
            
    public void stat(ArrayList<String> Stat) throws ParseException {
        Map<String, Integer> map = new HashMap<>();

        // Count the occurrences of each element
        for (String element : Stat) {
            if (map.containsKey(element)) {
                map.put(element, map.get(element) + 1);
            } else {
                map.put(element, 1);
            }
        }

        // Create data for the chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Add data to the chart
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        

        // Create the chart
        pieChart = new PieChart(pieChartData);
        
//        StackPane root = new StackPane();
          stack.getChildren().add(pieChart);

          
        
        
    }
    
    
   
    @FXML
    private void retour(ActionEvent event) {
        
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
            Parent root = loader.load();
            AfficherProduitController dcc=loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            dcc.initData(id_utilisateur,0,0,id_produitOffre);
            btnRetour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Echanger Un produit pleaaase");
    }
    }
    
     
    

