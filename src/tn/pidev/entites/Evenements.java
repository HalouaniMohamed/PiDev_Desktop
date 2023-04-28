/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.pidev.entites;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Evenements {
    private int id,nbr_de_places;
    private String nom_evenement,lieu_evenement,description_evenement,type,image;
    private Date date_evenement;
    private Time heure;
    private LocalDateTime created_at,updated_at;

    public Evenements() {
        
}

    public Evenements(int nbr_de_places, String nom_evenement, String lieu_evenement, String description_evenement,
			String type, Date date_evenement, Time heure) {
		super();
		this.nbr_de_places = nbr_de_places;
		this.nom_evenement = nom_evenement;
		this.lieu_evenement = lieu_evenement;
		this.description_evenement = description_evenement;
		this.type = type;
		this.date_evenement = date_evenement;
		this.heure = heure;
	}

	public Evenements(int nbr_de_places, String nom_evenement, String lieu_evenement, String description_evenement, String type, String image, Time heure) {
        this.nbr_de_places = nbr_de_places;
        this.nom_evenement = nom_evenement;
        this.lieu_evenement = lieu_evenement;
        this.description_evenement = description_evenement;
        this.type = type;
        this.image = image;
        this.heure = heure;
    }

    public Evenements(int id, int nbr_de_places, String nom_evenement) {
        this.id = id;
        this.nbr_de_places = nbr_de_places;
        this.nom_evenement = nom_evenement;
    }
    
    

    public Evenements(int id, int nbr_de_places, String nom_evenement, String lieu_evenement, String description_evenement, String type, String image, Date date_evenement, Time heure, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.nbr_de_places = nbr_de_places;
        this.nom_evenement = nom_evenement;
        this.lieu_evenement = lieu_evenement;
        this.description_evenement = description_evenement;
        this.type = type;
        this.image = image;
        this.date_evenement = date_evenement;
        this.heure = heure;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Evenements(int nbr_de_places, String nom_evenement, String lieu_evenement, String description_evenement, String type, String image, Date date_evenement, Time heure, LocalDateTime created_at, LocalDateTime updated_at) {
        this.nbr_de_places = nbr_de_places;
        this.nom_evenement = nom_evenement;
        this.lieu_evenement = lieu_evenement;
        this.description_evenement = description_evenement;
        this.type = type;
        this.image = image;
        this.date_evenement = date_evenement;
        this.heure = heure;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Evenements( int nbr_de_places, String nom_evenement, String lieu_evenement, String description_evenement, String type, String image, Date date_evenement, Time heure) {
        
        this.nbr_de_places = nbr_de_places;
        this.nom_evenement = nom_evenement;
        this.lieu_evenement = lieu_evenement;
        this.description_evenement = description_evenement;
        this.type = type;
        this.image = image;
        this.date_evenement = date_evenement;
        this.heure = heure;
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbr_de_places() {
        return nbr_de_places;
    }

    public void setNbr_de_places(int nbr_de_places) {
        this.nbr_de_places = nbr_de_places;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public String getLieu_evenement() {
        return lieu_evenement;
    }

    public void setLieu_evenement(String lieu_evenement) {
        this.lieu_evenement = lieu_evenement;
    }

    public Date getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }

    public String getDescription_evenement() {
        return description_evenement;
    }

    public void setDescription_evenement(String description_evenement) {
        this.description_evenement = description_evenement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return  "nom_evenement=" + nom_evenement+"nbr de place = "+nbr_de_places ;
    }

    
    
    
}
