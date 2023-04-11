/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Iservices;

import java.sql.SQLException;
import java.util.List;
import Entities.LigneCommande;

/**
 *
 * @author asus
 */
public interface IServiceLigneCommande<T>{
     public void createOne(LigneCommande t) throws SQLException;
     public void updateOne(LigneCommande l) throws SQLException;
     public void supprimerOne(int id);
     public List<LigneCommande> selectAll() throws SQLException;
    public List<LigneCommande> selectByIdCommande(int id_Commande) throws SQLException;
}
