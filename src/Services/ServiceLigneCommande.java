/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import Iservices.IServiceCommande;
import Entities.LigneCommande;
 import Utils.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import static jdk.nashorn.internal.runtime.Debug.id;
import Iservices.IServiceLigneCommande;
 /*
 * @author Mariem
 */
public class ServiceLigneCommande implements IServiceLigneCommande<LigneCommande> {
private final Connection conn;
    private Object sp;

    public ServiceLigneCommande(){
        conn = DataSource.getInstance().getConnection();
    }
    @Override
    public void createOne(LigneCommande l) throws SQLException {
   String req =   "INSERT INTO `ligne_commande`(`prix`, `id_Commande`, `id_produit`, `lieu_produit`)" + " VALUES (?,?,?,?)";
      try{
      PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(4, l.getLieu_produit());
            ps.setInt(3, l.getId_produit());
            ps.setInt(2, l.getId_commande());
            ps.setFloat(1, l.getPrix());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
        System.out.println("ligne ajouté !"); 
    }

    @Override
    public void updateOne(LigneCommande l) throws SQLException {
    String req =  " UPDATE `commande` SET prix=?,id_Commande=?,id_produit=? , lieu_produit = ? WHERE IdCommande=" + l.getId_ligne();
     
            try{
      PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(4, l.getLieu_produit());
            ps.setInt(3, l.getId_produit());
            ps.setInt(2, l.getId_commande());
            ps.setFloat(1, l.getPrix());
            ps.executeUpdate();
            System.out.println("ligne modifié !"); 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }
    

        @Override
       public void supprimerOne(int id){

        try {
            String req = "DELETE FROM `ligne_commande` WHERE Id_ligne = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Ligne Commande deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @Override
    public List<LigneCommande> selectAll() throws SQLException {
        List<LigneCommande> temp = new ArrayList<>();
        String req = "SELECT * FROM `ligne_commande`";
        PreparedStatement ps = conn.prepareStatement(req);
 
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            LigneCommande com = new LigneCommande();

           com.setPrix(rs.getFloat("prix"));
           
          
          temp.add(com);

        }
    
        return temp ;   
    }

    @Override
    public List<LigneCommande> selectByIdCommande(int id_Commande) throws SQLException {
        List<LigneCommande> temp = new ArrayList<>();
        String req = "SELECT * FROM `ligne_commande` WHERE id_Commande = " + id_Commande ;
        PreparedStatement ps = conn.prepareStatement(req);
 
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            LigneCommande com = new LigneCommande();
           com.setId_ligne(rs.getInt("Id_ligne"));
           com.setPrix(rs.getFloat("prix"));
           com.setId_commande(rs.getInt("id_Commande"));
           com.setId_produit(rs.getInt("id_produit"));
           com.setLieu_produit(rs.getString("lieu_produit"));
           
          
          temp.add(com);

        }
    
        return temp ;   
    }
    
}
