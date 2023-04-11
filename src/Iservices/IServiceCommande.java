/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Iservices;

/**
 *
 * @author Mariem
 */
import java.sql.SQLException;
import java.util.List;
import Entities.Commande;
public interface IServiceCommande<T> {
    
    public int createOne(Commande c) throws SQLException;
    void updateOne(T t) throws SQLException;
   // void deletOne(T t) throws SQLException;
      public void supprimerCommande(int id_Commande);
      public List<Commande> afficherCommande();
      public List<Commande> afficherCommandeByStaut(String stat);
      public void sendSms(String toPhoneNumber, String messageText);
    
}
