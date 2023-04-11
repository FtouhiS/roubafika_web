/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entities.Utilisateur;
import Utils.DataSource;
import java.sql.Date;

/**
 *
 * @author Soulaima
 */
public class Metier implements ModifierInterface{
Statement ste;
Connection  cnx = DataSource.getInstance().getConnection();
    @Override
    public List<Utilisateur> SearchByName(String name) {
            List<Utilisateur> pers = new ArrayList<>();
        try {
            PreparedStatement pre = cnx.prepareStatement("select * from `utilisateur` WHERE `Nom` LIKE ?");
            pre.setString(1, "%" + name + "%");
            ResultSet RS = pre.executeQuery();
            ste = cnx.createStatement();
        System.out.println(RS);
        while (RS.next()) {
             Utilisateur listuser =new Utilisateur(RS.getInt("IdUser"),RS.getString("Nom"),RS.getString("Prenom"),RS.getString("Adresse_mail"),RS.getString("mdp"),RS.getString("Adresse"),RS.getInt("Numero_telephone"),RS.getString("roleU"));
               pers.add(listuser);
        }
        System.out.println(pers);
     
    } catch (SQLException ex) {
         System.out.println(ex);  
    }
   return pers;

    }

    @Override
    public List<Utilisateur> sortByName() {
         List<Utilisateur> pers = new ArrayList<>();
        try {
           PreparedStatement pre = cnx.prepareStatement("SELECT * FROM `utilisateur` ORDER BY `Nom`");
           // pre.setString(1, "%" + name + "%");
            ResultSet RS= pre.executeQuery();
            ste = cnx.createStatement();
        System.out.println(RS);
            while (RS.next()) {
                 Utilisateur listuser =new Utilisateur(RS.getInt("IdUser"),RS.getString("Nom"),RS.getString("Prenom"),RS.getString("Adresse_mail"),RS.getString("mdp"),RS.getString("Adresse"),RS.getInt("Numero_telephone"),RS.getString("roleU"));
               pers.add(listuser);
               
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return pers;
    }

    }
