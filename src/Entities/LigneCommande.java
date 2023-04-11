/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import java.util.Objects;

/**
 *
 * @author Mariem
 */
public class LigneCommande {
    private int id_ligne ;
    private float prix;
    private int id_commande;
    private int id_produit;
    private String lieu_produit;

    public LigneCommande(int id_ligne, float prix, int id_commande, int id_produit, String lieu_produit) {
        this.id_ligne = id_ligne;
        this.prix = prix;
        this.id_commande = id_commande;
        this.id_produit = id_produit;
        this.lieu_produit = lieu_produit;
    }
    
    
    public LigneCommande(float prix, int id_commande, int id_produit, String lieu_produit) {
        this.prix = prix;
        this.id_commande = id_commande;
        this.id_produit = id_produit;
        this.lieu_produit = lieu_produit;
    }
    
    public LigneCommande(){} ;
    public int getId_ligne() {
        return id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getLieu_produit() {
        return lieu_produit;
    }

    public void setLieu_produit(String lieu_produit) {
        this.lieu_produit = lieu_produit;
    }

    
   
    
    public float getPrix(){
    if (prix <= 0) {
        throw new IllegalStateException("Le prix doit être supérieur à 0.");
    }
    return prix;
    }
    
    public void setPrix(float prix)
    {
    if (prix <= 0) {
        throw new IllegalArgumentException("Le prix doit être supérieur à 0.");
    }
    this.prix = prix;    }
    
    
       

}