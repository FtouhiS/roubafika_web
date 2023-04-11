/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

import java.util.Objects;


/**
 *
 * @author moham
 */



public class Demande {
    private int id_demande;
    private String nom_demandeur, prenom_demandeur, email_demandeur,date_demande;
    private int id_serv, idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    private String titreService;

    public void setTitreService(String titreService) {
        this.titreService = titreService;
    }

    public String getTitreService() {
        return titreService;
    }

    public Demande(String nom_demandeur, String prenom_demandeur, String email_demandeur, String date_demande, String titreService) {
        this.nom_demandeur = nom_demandeur;
        this.prenom_demandeur = prenom_demandeur;
        this.email_demandeur = email_demandeur;
        this.date_demande = date_demande;
        this.titreService = titreService;
    }
    

    public Demande(int id_demande, String nom_demandeur, String prenom_demandeur, String email_demandeur, String date_demande, int id,int idUser) {
        this.id_demande = id_demande;
        this.nom_demandeur = nom_demandeur;
        this.prenom_demandeur = prenom_demandeur;
        this.email_demandeur = email_demandeur;
        this.date_demande = date_demande;
        this.id_serv = id;
        this.idUser = idUser;
    }

    public Demande(String nom_demandeur, String prenom_demandeur, String email_demandeur, String date_demande, int id_serv,int idUser) {
        this.nom_demandeur = nom_demandeur;
        this.prenom_demandeur = prenom_demandeur;
        this.email_demandeur = email_demandeur;
        this.id_serv = id_serv;
         this.idUser = idUser;
    }
    public Demande(String nom_demandeur, String prenom_demandeur, String email_demandeur, String date_demande, int id_serv) {
        this.nom_demandeur = nom_demandeur;
        this.prenom_demandeur = prenom_demandeur;
        this.email_demandeur = email_demandeur;
        this.id_serv = id_serv;
         
    }

    public Demande() {
    }

    public int getId_demande() {
        return id_demande;
    }

    public String getNom_demandeur() {
        return nom_demandeur;
    }

    public String getPrenom_demandeur() {
        return prenom_demandeur;
    }

    public String getEmail_demandeur() {
        return email_demandeur;
    }
    
    public String getDate_demande() {
        return date_demande;
    }

    public int getId_serv() {
        return id_serv;
    }

    public void setId(int id_demande) {
        this.id_demande = id_demande;
    }

    public void setNom_demandeur(String nom_demandeur) {
        this.nom_demandeur = nom_demandeur;
    }

    public void setPrenom_demandeur(String prenom_demandeur) {
        this.prenom_demandeur = prenom_demandeur;
    }

    public void setEmail_demandeur(String email_demandeur) {
        this.email_demandeur = email_demandeur;
    }
    
    public void setDate_demande(String date_demande) {
        this.date_demande = date_demande;
    }

    public void setId_serv(int id_serv) {
        this.id_serv = id_serv;
    }

    @Override
    public String toString() {
        return "Demande{" + "id_demande=" + id_demande + ", nom_demandeur=" + nom_demandeur + ", prenom_demandeur=" + prenom_demandeur + ", email_demandeur=" + email_demandeur + ", date_demande=" + date_demande + ", id_serv=" + id_serv + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
    final Demande other = (Demande) obj;
    if (this.id_demande != other.id_demande) {
        return false;
    }
    if (!Objects.equals(this.nom_demandeur, other.nom_demandeur)) {
        return false;
    }
    if (!Objects.equals(this.prenom_demandeur, other.prenom_demandeur)) {
        return false;
    }
    if (!Objects.equals(this.email_demandeur, other.email_demandeur)) {
        return false;
    }
    if (!Objects.equals(this.date_demande, other.date_demande)) {
        return false;
    }
    return this.id_serv == other.id_serv;
}

}