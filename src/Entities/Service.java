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
public class Service {
    private int id ,idUser;
    private String titre,description,date_annonce,adresse;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public Service(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    
        public enum Categorie{
            transport,
            plomberie
        }
        private Categorie categorie;

    public Service(int id, String titre, String description, String date_annonce, String adresse, Categorie categorie,int idUser) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date_annonce = date_annonce;
        this.adresse = adresse;
        this.categorie = categorie;
        this.idUser = idUser;
    }

    public Service(String titre, String description, String date_annonce, String adresse, Categorie categorie,int idUser) {
        this.titre = titre;
        this.description = description;
        this.date_annonce = date_annonce;
        this.adresse = adresse;
        this.categorie = categorie;
          this.idUser = idUser;
    }

    public Service() {
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getDate_annonce() {
        return date_annonce;
    }

    public String getAdresse() {
        return adresse;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_annonce(String date_annonce) {
        this.date_annonce = date_annonce;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", date_annonce=" + date_annonce + ", adresse=" + adresse + ", categorie=" + categorie + '}';
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
        final Service other = (Service) obj;
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        return true;
    }
        
        
    }

    
    
    
    
    
    

