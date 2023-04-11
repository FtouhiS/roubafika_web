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
import Iservices.IServiceEchange;
import Iservices.IServiceProduit;
import Entities.Echange;
import Entities.Produit;
import Utils.DataSource;

/**
 *
 * @author asus
 */
public class CRUDEchange implements IServiceEchange{
    Statement ste;
    Connection conn = DataSource.getInstance().getConnection();
    public void ajouterEchange(Echange e) {
       try {
            String req = "INSERT INTO `echange` (`id`,`produit_echange`,`produit_offert`,`lieu_echange`,`lieu_offre`,`statut`,`livreur`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(7, e.getLivreur());
            ps.setString(6, e.getStatut());
            ps.setString(5, e.getLieu_offre());
            ps.setString(4, e.getLieu_echange());
            ps.setInt(3, e.getProduit_offert());
            ps.setInt(2, e.getProduit_echange());
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }
    public void modifierEchange(Echange e) {
       try {
            String req = "UPDATE `echange` SET `produit_echange` = '"+ e.getProduit_echange()+ "',`produit_offert` = '" +e.getProduit_offert()+ "',`lieu_echange`='"+e.getLieu_echange()+"',`lieu_offre`='"+e.getLieu_offre()+"',`statut`='"+e.getStatut()+"',`livreur`='"+e.getLivreur()+"' WHERE `echange`.`id` = " + e.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Echange updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimerproduit(int id_echange) {
        try {
            String req = "DELETE FROM `echange` WHERE id = " + id_echange;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Echange> afficherEchange() {
       List<Echange> echanges = new ArrayList<Echange>();
        try {
            ste =conn.createStatement();
            String req = "SELECT * FROM `echange`";
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Echange resultProduit = new Echange(result.getInt("id"),result.getInt("produit_echange"), result.getInt("produit_offert"),result.getString("lieu_echange"),result.getString("lieu_offre"),result.getString("statut"),result.getInt("livreur"));
            System.out.println(resultProduit); 
            echanges.add(resultProduit);
        }
               System.out.println("iciii") ;

        System.out.println(echanges);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return echanges;
    }
    
    public List<Echange> afficherEchangeByStatut(String stat) {
        
        
       List<Echange> echanges = new ArrayList<Echange>();
        try {
            ste =conn.createStatement();
            String req = "SELECT * FROM `echange` WHERE statut = '" + stat + "'";
            System.out.println("++++++++++++++++++");
        System.out.println(req);
        System.out.println("++++++++++++++++++");
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Echange resultProduit = new Echange(result.getInt("id"),result.getInt("produit_echange"), result.getInt("produit_offert"),result.getString("lieu_echange"),result.getString("lieu_offre"),result.getString("statut"),result.getInt("livreur"));
            System.out.println(resultProduit); 
            echanges.add(resultProduit);
        }
               System.out.println("iciii") ;

        System.out.println(echanges);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return echanges;
    }
    
    public List<Echange> afficherEchangeById_produit(int id_produit) {
       List<Echange> echanges = new ArrayList<Echange>();
        try {
            ste =conn.createStatement();
            String req = "SELECT * FROM `echange` where produit_offert =  " + id_produit ;
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Echange resultProduit = new Echange(result.getInt("id"),result.getInt("produit_echange"), result.getInt("produit_offert"),result.getString("lieu_echange"),result.getString("lieu_offre"),result.getString("statut"),result.getInt("livreur"));
            System.out.println(resultProduit); 
            echanges.add(resultProduit);
        }
               System.out.println("iciii") ;

        System.out.println(echanges);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return echanges;
    }
    
    public Echange afficherEchangeById(int id_echange) {
       Echange e = new Echange();
        try {
            ste =conn.createStatement();
            String req = "SELECT * FROM `echange` where id =  " + id_echange ;
            ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Echange resultProduit = new Echange(result.getInt("id"),result.getInt("produit_echange"), result.getInt("produit_offert"),result.getString("lieu_echange"),result.getString("lieu_offre"),result.getString("statut"),result.getInt("livreur"));
            System.out.println(resultProduit);
            e=resultProduit;
        }
               System.out.println("iciii") ;

        System.out.println(e);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return e;
    }
}
