/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Entities.Utilisateur;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author moham
 */
public class UserService {

    private PreparedStatement pst;
    private Statement ste;
    private Connection connection;
    private ResultSet rs;

    public UserService() {
        connection = DataSource.getInstance().getConnection();
    }

    public Utilisateur UserById(int iduser) {
        Utilisateur user = new Utilisateur();
        String requete = "select * from utilisateur where idUser= " + iduser;
        Statement st;
        ResultSet rs;
        try {
            ste = connection.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                user = new Utilisateur(rs.getInt("idUser"), rs.getString("Nom"), rs.getString("Prenom"), rs.getString("Adresse_mail"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }
    

}
