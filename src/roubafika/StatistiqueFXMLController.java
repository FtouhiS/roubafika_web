/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roubafika;

import Entities.Service;
import Services.MyDemande;
import Services.MyService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class StatistiqueFXMLController implements Initializable {

    @FXML
    private PieChart chartfor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        demandeParService();
    }    
    
    private void demandeParService() {
         MyService serv = new MyService();
         ObservableList<Service> titres= serv.listeTitreService();
          ObservableList<PieChart.Data> PieChartData =FXCollections.observableArrayList();
         for (Service s : titres){
              PieChartData.add(new PieChart.Data(s.getTitre(),serv.Stats(s.getId()+" ")));
         }
    chartfor.setData(PieChartData);
    }
}
