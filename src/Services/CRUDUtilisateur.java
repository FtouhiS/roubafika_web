/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Iservices.IUtilisateur;
import Utils.DataSource;
import Entities.Utilisateur;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Soulaima
 */
public class CRUDUtilisateur implements IUtilisateur {

    Statement ste;
    Connection cnx = DataSource.getInstance().getConnection();

    @Override
    public void ajouterUtilisateur(Utilisateur U) {

        try {
            // Vérifier que l'adresse mail est au format valide
            String mailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (!U.getAdresse_mail().matches(mailRegex)) {
                System.out.println("Adresse mail invalide !");
                return;
            }

            //les champs sont obligatoires
           
            ste = cnx.createStatement();
            String req = "Insert into utilisateur values(0,'" + U.getNom() + "','" + U.getPrenom() + "','" + U.getAdresse_mail() + "','" + U.getMdp() + "','" + U.getAdresse() + "','" + U.getNumero_telephone() + "','" + U.getRoleU() + "')";
            ste.executeUpdate(req);
            System.out.println(req);
            System.out.println("utilisateur ajoutee");
        } catch (SQLException ex) {
            System.out.println("utilisateur non ajoutee!!!!");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierUtilisateur(Utilisateur U) {
        try {
            String req = "UPDATE `utilisateur` SET `utilisateur`.`nom` = '" + U.getNom() + "', `utilisateur`.`prenom` = '" + U.getPrenom() + "', `utilisateur`.`Adresse_mail` = '" + U.getAdresse_mail() + "', `utilisateur`.`mdp` = '" + U.getMdp() + "', `utilisateur`.`adresse` = '" + U.getAdresse() + "',`utilisateur`.`Numero_telephone` = '" + U.getNumero_telephone() + "', `utilisateur`.`roleU` = '" + U.getRoleU() + "' WHERE `utilisateur`.`IdUser` = " + U.getIdUser();
            System.out.println(req);
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("utilisateur updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerUtilisateur(int IdUser) {
        try {
            String req = "DELETE FROM `utilisateur` WHERE `utilisateur`.`IdUser`=" + IdUser;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("utilisateur deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Utilisateur> afficherUtilisateur() {
        ArrayList<Utilisateur> list = new ArrayList<>();
        try {
            String req = "Select * from utilisateur";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Utilisateur U = new Utilisateur();
                U.setIdUser(RS.getInt(1));
                U.setNom(RS.getString(2));
                U.setPrenom(RS.getString(3));
                U.setAdresse_mail(RS.getString(4));
                U.setMdp(RS.getString(5));
                U.setAdresse(RS.getString(6));
                U.setNumero_telephone(RS.getInt(7));
                U.setRoleU(RS.getString(8));
                list.add(U);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public void ajouterUtilisateur2(Utilisateur U) {
        try {
            String req = "INSERT INTO `utilisateur` (`Nom`, `Prenom`,`Adresse_mail`,`mdp`,`Adresse`,`Numero_telephone`,`roleU`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, U.getNom());
            ps.setString(2, U.getPrenom());
            ps.setString(3, U.getAdresse_mail());
            ps.setString(4, U.getMdp());
            ps.setString(5, U.getAdresse());
            ps.setInt(6, U.getNumero_telephone());
            ps.setString(7, U.getRoleU());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ObservableList<Utilisateur> rechercheUser(String nom) {
        ObservableList<Utilisateur> list = FXCollections.observableArrayList();

        try {

            String req = " SELECT * FROM  `utilisateur` WHERE `utilisateur`.`Nom`=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, nom);
            ResultSet RS = pst.executeQuery();

            while (RS.next()) {
                Utilisateur U = new Utilisateur();
                U.setIdUser(RS.getInt(1));
                U.setNom(RS.getString(2));
                U.setPrenom(RS.getString(3));
                U.setAdresse_mail(RS.getString(4));
                U.setMdp(RS.getString(5));
                U.setAdresse(RS.getString(6));
                U.setNumero_telephone(RS.getInt(7));
                U.setRoleU(RS.getString(8));
                list.add(U);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(list);
        return list;

    }

    @Override
    public Utilisateur rechercheUserByMail(String mail) {
        Utilisateur user = new Utilisateur();

        try {

            String req = " SELECT * FROM  `utilisateur` WHERE `utilisateur`.`Adresse_mail`=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, mail);
            ResultSet RS = pst.executeQuery();

            while (RS.next()) {
                Utilisateur U = new Utilisateur();
                U.setIdUser(RS.getInt(1));
                U.setNom(RS.getString(2));
                U.setPrenom(RS.getString(3));
                U.setAdresse_mail(RS.getString(4));
                U.setMdp(RS.getString(5));
                U.setAdresse(RS.getString(6));
                U.setNumero_telephone(RS.getInt(7));
                U.setRoleU(RS.getString(8));
                user = U;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println(user);
        return user;

    }

    @Override
    public Utilisateur rechercheUserbyid(int IdUser) {
        Utilisateur user = new Utilisateur();

        try {

            String req = " SELECT * FROM  `utilisateur` WHERE `utilisateur`.`IdUser`=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, IdUser);
            ResultSet RS = pst.executeQuery();

            while (RS.next()) {
                Utilisateur U = new Utilisateur();
                U.setIdUser(RS.getInt(1));
                U.setNom(RS.getString(2));
                U.setPrenom(RS.getString(3));
                U.setAdresse_mail(RS.getString(4));
                U.setMdp(RS.getString(5));
                U.setAdresse(RS.getString(6));
                U.setNumero_telephone(RS.getInt(7));
                U.setRoleU(RS.getString(8));
                user = U;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(user);
        return user;

    }

    public int ChercherMail(String email) {

        try {
            String req = "SELECT * from `utilisateur` WHERE `utilisateur`.`Adresse mail` ='" + email + "'  ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString("Adresse amil").equals(email)) {
                    System.out.println("mail trouvé ! ");
                    return 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    public void ResetPaswword(String email, String password) {
        try {

            String req = "UPDATE Utilisateur SET mdpq = ? WHERE Adresse mail = ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, password);
            ps.setString(2, email);

            ps.executeUpdate();
            System.out.println("Password updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    private static final String ACCOUNT_SID = "AC54cf154f664583c95ed5ea1792a087bb";
    private static final String AUTH_TOKEN = "e6017cb99826b4480dfc9d719768ff0d";
    private static final String TWILIO_PHONE_NUMBER = "+15746525481";

    // Define the method to send the SMS message
    public void sendSms(String toPhoneNumber, String messageText) {
        // Initialize the Twilio API client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Set the phone numbers for the SMS message
        PhoneNumber to = new PhoneNumber(toPhoneNumber);
        PhoneNumber from = new PhoneNumber(TWILIO_PHONE_NUMBER);

        // Use the Message creator to send the SMS message
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(to, from, messageText).create();
    }
}
