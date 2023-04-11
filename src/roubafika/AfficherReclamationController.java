package roubafika;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Utils.DataSource;
import javafx.scene.control.TableColumn;
import Entities.Reclamation;
import javax.swing.JOptionPane;
import Services.ReclamationService;
import javafx.scene.Node;




public class AfficherReclamationController implements Initializable {
@FXML
    private Node menuComponent;
	@FXML
	private TableView<Reclamation>listReclamation;

	@FXML
	private TableColumn<Reclamation, Date> columnDate;

	@FXML
	private TableColumn<Reclamation, String> columnSujet;

	@FXML
	private TableColumn<Reclamation, String> columnDescription;


	@FXML
	private Button supp;

	@FXML
	private Button ref;

	@FXML
	private TextField txt_sujet;

	@FXML

	private  TextArea txt_description;





	@FXML
	private Button btnModifier;
    @FXML
    private Button btnModifier1;
    @FXML
    private Button btnRepondre;
    @FXML
    private TextField recherche;
    @FXML
    private Button btnRechercher;



	@FXML
	public void supprimerReclamation(ActionEvent event)throws IOException {
       
        int selectedIndex = listReclamation.getSelectionModel().getSelectedIndex();

       if (selectedIndex < 0 ) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Erreur");
           alert.setHeaderText("Aucune reclamation selectionnée!");
           alert.setContentText("Veuiller selectionner une réclamation à supprimer");
           Optional<ButtonType> result = alert.showAndWait();
       } else{
        try {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ReclamationService ser = new ReclamationService();
                ser.deletOne(listReclamation.getSelectionModel().getSelectedItem().getIdReclamation());
                ReclamationService crud = new ReclamationService();
                ObservableList<Reclamation> data = FXCollections.observableArrayList(crud.afficherReclamation());
            System.out.println("///////");
            System.out.println(data);
            System.out.println("///////");
            columnSujet.setCellValueFactory(new PropertyValueFactory<>("Date_ajout"));
            columnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
            columnSujet.setCellValueFactory(new PropertyValueFactory<>("Sujet"));
            listReclamation.setItems(data);
            alert.setTitle("Reclamation");
            alert.setContentText("Reclamaton supprimée Avec succées");
            alert.show();
            } else {

            }
            throw new SQLException("Sample SQLException");
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
    }
//    @FXML
////    public void initierReclamation(ActionEvent event) throws ParseException {
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
//        
//    }

//	private void supp(int id) {
//		// TODO Auto-generated method stub
//		java.sql.Connection cnx;
//		cnx = DataSource.getInstance().getConnection();
//		try {
//			String sql = "DELETE FROM reclamation WHERE `IdReclamation` = '" +id+ "' " ;
//
//			PreparedStatement st =cnx.prepareStatement(sql);
//
//			st.executeUpdate();
//		} catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null,"Error");
//		}
//
//	}

	@FXML
	public void rafraichir(ActionEvent event){

		table();

	}


	public void table(){
            ReclamationService crud = new ReclamationService();
                ObservableList<Reclamation> data = FXCollections.observableArrayList(crud.afficherReclamation());
            System.out.println("///////");
            System.out.println(data);
            System.out.println("///////");
            columnDate.setCellValueFactory(new PropertyValueFactory<>("Date_ajout"));
            columnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
            columnSujet.setCellValueFactory(new PropertyValueFactory<>("Sujet"));
            System.out.println(data);
            listReclamation.setItems(data);
//		System.out.println("iciciii");
//		columnDate.setCellValueFactory(new PropertyValueFactory <>("Date_ajout"));
//		columnSujet.setCellValueFactory(new PropertyValueFactory <>("Sujet"));
//		columnDescription.setCellValueFactory(new PropertyValueFactory <>("Description"));
//                
//                System.out.println(RecupBase());
//		listReclamation.setItems(RecupBase()); 

	}





	public static ObservableList<Reclamation> RecupBase(){

		ObservableList<Reclamation> list = FXCollections.observableArrayList();

		java.sql.Connection cnx;
		cnx = DataSource.getInstance().getConnection();
		try {
			String sql = "select * from reclamation";


			Statement st = cnx.createStatement();

			ResultSet R = st.executeQuery(sql);
			while (R.next()){
				Reclamation r =new Reclamation();
				r.setIdReclamation(R.getInt(1));
				r.setDate_ajout(R.getDate(2));
				r.setSujet(R.getString(3));
				r.setDescription(R.getString(4));

				list.add(r);
			}
		}catch (SQLException ex){
			System.out.println(ex.getMessage()); 
		} 
                System.out.println(list);
		return list;
	}




    @FXML
    public void RemplirReclamation(ActionEvent event) throws ParseException {
        int selectedIndex = listReclamation.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0 ) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Erreur");
           alert.setHeaderText("Aucune reclamation selectionnée!");
           alert.setContentText("Veuiller selectionner une réclamation à modifier");
           Optional<ButtonType> result = alert.showAndWait();
       }
        else{
        txt_sujet.setText(listReclamation.getSelectionModel().getSelectedItem().getSujet());
        //textNom.setText(listReclamation.getSelectionModel().getSelectedItem().getid_client());
        txt_description.setText(listReclamation.getSelectionModel().getSelectedItem().getDescription());
        
        }
        
    }

	@FXML
	void modifierReclamation(ActionEvent event) {
		
                int idr = listReclamation.getSelectionModel().getSelectedItem().getIdReclamation();
		String sujet = txt_sujet.getText();
		String description = txt_description.getText();
		

		String sql = "UPDATE reclamation SET IdReclamation = '"+ idr +"', Sujet = '"+ sujet +"', Description = '"+ description +"' WHERE IdReclamation = '"+ idr +"'" ;
		java.sql.Connection cnx;
		cnx = DataSource.getInstance().getConnection();
		try {
			PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
			st.executeUpdate();
			table();
			JOptionPane.showMessageDialog(null,"Le fichier a été modifié");
		} catch (SQLException ex) {
			ex.getMessage();
		}
	}





	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		table();
	}    

    @FXML
    private void Repondre(ActionEvent event) throws SQLException {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ReponseReclamation.fxml"));
            Parent root = loader.load();
            ReponseReclamationController dcc=loader.getController();
            //user ca=listStaff.getSelectionModel().getSelectedItem();
            int idRec=listReclamation.getSelectionModel().getSelectedItem().getIdReclamation();
            dcc.initData(idRec);
            btnModifier.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rechercher(ActionEvent event) {
        ReclamationService crud = new ReclamationService();
                ObservableList<Reclamation> data = FXCollections.observableArrayList(crud.findBySujet(recherche.getText()));
            System.out.println("///////");
            System.out.println(data);
            System.out.println("///////");
            columnDate.setCellValueFactory(new PropertyValueFactory<>("Date_ajout"));
            columnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
            columnSujet.setCellValueFactory(new PropertyValueFactory<>("Sujet"));
            System.out.println(data);
            listReclamation.setItems(data);
    }




}
