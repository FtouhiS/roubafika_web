/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author asus
 */
public class Echange {
    int id ;
    int produit_echange;
    int produit_offert;
    String lieu_echange;
    String lieu_offre;
    String statut ;
    int livreur ;

    public Echange(int produit_echange, int produit_offert, String lieu_echange, String lieu_offre, String statut, int livreur) {
        this.produit_echange = produit_echange;
        this.produit_offert = produit_offert;
        this.lieu_echange = lieu_echange;
        this.lieu_offre = lieu_offre;
        this.statut = statut;
        this.livreur = livreur;
    }

    public Echange(int id, int produit_echange, int produit_offert, String lieu_echange, String lieu_offre, String statut, int livreur) {
        this.id = id;
        this.produit_echange = produit_echange;
        this.produit_offert = produit_offert;
        this.lieu_echange = lieu_echange;
        this.lieu_offre = lieu_offre;
        this.statut = statut;
        this.livreur = livreur;
    }
    
    public Echange(int produit_echange, int produit_offert, String lieu_echange, String lieu_offre, String statut) {
        this.produit_echange = produit_echange;
        this.produit_offert = produit_offert;
        this.lieu_echange = lieu_echange;
        this.lieu_offre = lieu_offre;
        this.statut = statut;
    }

    public Echange() {
    }

    public int getLivreur() {
        return livreur;
    }

    public void setLivreur(int livreur) {
        this.livreur = livreur;
    }

    @Override
    public String toString() {
        return "Echange{" + "id=" + id + ", produit_echange=" + produit_echange + ", produit_offert=" + produit_offert + ", lieu_echange=" + lieu_echange + ", lieu_offre=" + lieu_offre + ", statut=" + statut + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + this.produit_echange;
        hash = 53 * hash + this.produit_offert;
        hash = 53 * hash + Objects.hashCode(this.lieu_echange);
        hash = 53 * hash + Objects.hashCode(this.lieu_offre);
        hash = 53 * hash + Objects.hashCode(this.statut);
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
        final Echange other = (Echange) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.produit_echange != other.produit_echange) {
            return false;
        }
        if (this.produit_offert != other.produit_offert) {
            return false;
        }
        if (!Objects.equals(this.lieu_echange, other.lieu_echange)) {
            return false;
        }
        if (!Objects.equals(this.lieu_offre, other.lieu_offre)) {
            return false;
        }
        return Objects.equals(this.statut, other.statut);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit_echange() {
        return produit_echange;
    }

    public void setProduit_echange(int produit_echange) {
        this.produit_echange = produit_echange;
    }

    public int getProduit_offert() {
        return produit_offert;
    }

    public void setProduit_offert(int produit_offert) {
        this.produit_offert = produit_offert;
    }

    public String getLieu_echange() {
        return lieu_echange;
    }

    public void setLieu_echange(String lieu_echange) {
        this.lieu_echange = lieu_echange;
    }

    public String getLieu_offre() {
        return lieu_offre;
    }

    public void setLieu_offre(String lieu_offre) {
        this.lieu_offre = lieu_offre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
}
