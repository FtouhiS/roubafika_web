/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Blob;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class Produit {
    int id_produit;
    int id_user;
    String Categorie ;
    String nom_produit;
    String description;
    Blob image;
    Float prix ;

    public Produit(int id_user, String Categorie, String nom_produit, String description, Blob image, Float prix) {
        this.id_user = id_user;
        this.Categorie = Categorie;
        this.nom_produit = nom_produit;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public Produit(int id_produit, int id_user, String Categorie, String nom_produit, String description, Blob image, Float prix) {
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.Categorie = Categorie;
        this.nom_produit = nom_produit;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public Produit() {
    }

    public Produit(int id_produit, int id_user, String Categorie, String nom_produit, String description, Blob image) {
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.Categorie = Categorie;
        this.nom_produit = nom_produit;
        this.description = description;
        this.image = image;
    }

    public Produit(int id_produit, int id_user, String Categorie, String nom_produit, String description) {
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.Categorie = Categorie;
        this.nom_produit = nom_produit;
        this.description = description;
    }

    public Produit(int id_user, String Categorie, String nom_produit, String description, Blob image) {
        this.id_user = id_user;
        this.Categorie = Categorie;
        this.nom_produit = nom_produit;
        this.description = description;
        this.image = image;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id_produit;
        hash = 47 * hash + Objects.hashCode(this.nom_produit);
        hash = 47 * hash + Objects.hashCode(this.description);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        
        if (!Objects.equals(this.nom_produit, other.nom_produit)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    
    

    
}
