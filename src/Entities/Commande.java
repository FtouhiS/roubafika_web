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
public class Commande {
    private int idCommande ;
    private int id_user;
    private String adresse ;
    private String numero_tel ;
    private float Somme ;
    private int id_livreur;
    private String statut;

    public Commande() {
    }

    public Commande(int idCommande, int id_user, String adresse, String numero_tel, float Somme, int id_livreur, String statut) {
    
        this.idCommande = idCommande;
        this.id_user = id_user;
        this.adresse = adresse;
        this.numero_tel = numero_tel;
        this.Somme = Somme;
        this.id_livreur = id_livreur;
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommande=" + idCommande + ", id_user=" + id_user + ", adresse=" + adresse + ", numero_tel=" + numero_tel + ", Somme=" + Somme + ", id_livreur=" + id_livreur + ", statut=" + statut + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idCommande;
        hash = 79 * hash + this.id_user;
        hash = 79 * hash + Objects.hashCode(this.adresse);
        hash = 79 * hash + Objects.hashCode(this.numero_tel);
        hash = 79 * hash + Float.floatToIntBits(this.Somme);
        hash = 79 * hash + this.id_livreur;
        hash = 79 * hash + Objects.hashCode(this.statut);
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
        final Commande other = (Commande) obj;
        if (this.idCommande != other.idCommande) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (Float.floatToIntBits(this.Somme) != Float.floatToIntBits(other.Somme)) {
            return false;
        }
        if (this.id_livreur != other.id_livreur) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.numero_tel, other.numero_tel)) {
            return false;
        }
        return Objects.equals(this.statut, other.statut);
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumero_tel() {
        return numero_tel;
    }

    public void setNumero_tel(String numero_tel) {
        this.numero_tel = numero_tel;
    }

    public float getSomme() {
        return Somme;
    }

    public void setSomme(float Somme) {
        this.Somme = Somme;
    }

    public int getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Commande(int id_user, String adresse, String numero_tel, float Somme, int id_livreur, String statut) {
        this.id_user = id_user;
        this.adresse = adresse;
        this.numero_tel = numero_tel;
        this.Somme = Somme;
        this.id_livreur = id_livreur;
        this.statut = statut;
    }
    
    
}
