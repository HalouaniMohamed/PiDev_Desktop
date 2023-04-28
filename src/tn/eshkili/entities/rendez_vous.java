/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.entities;

import java.util.Date;

/**
 *
 * @author Mongi
 */
public class rendez_vous {

    
    
    private int id ;
    private String nom,prenom,cause,description ;
    private Date date_rv ;
    private Medecin medecin;
    private Cabinet cabinet ;
    private int idMedecin,idCabinet;

    public int getIdCabinet() {
        return idCabinet;
    }

    public void setIdCabinet(int idCabinet) {
        this.idCabinet = idCabinet;
    }

    public rendez_vous( String nom, String prenom, String cause, String description, Date date_rv, int idCabinet) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.cause = cause;
        this.description = description;
        this.date_rv = date_rv;
        this.idCabinet = idCabinet;
    }
    
    
    
    



    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    public rendez_vous(int id, String nom, String prenom, String cause, String description, Date date_rv, Medecin medecin, Cabinet cabinet) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cause = cause;
        this.description = description;
        this.date_rv = date_rv;
        this.medecin = medecin;
        this.cabinet = cabinet;
    }

    public rendez_vous(int id, String nom, String prenom, String cause, String description, Date date_rv, Medecin medecin) {
        this.nom = nom;
        this.prenom = prenom;
        this.cause = cause;
        this.description = description;
        this.date_rv = date_rv;
        this.medecin = medecin;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public rendez_vous(int id, String nom, String prenom, String cause, String description, Date date_rv) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cause = cause;
        this.description = description;
        this.date_rv = date_rv;
        
    }
     

    public rendez_vous(String nom, String prenom, String cause, String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.cause = cause;
        this.description = description;
    }

    public rendez_vous(String nom, String prenom, String cause, String description, Date date_rv) {
        this.nom = nom;
        this.prenom = prenom;
        this.cause = cause;
        this.description = description;
        this.date_rv = date_rv;

    }

    public rendez_vous() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  public Date getDate_rv() {
      return date_rv;
  }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

   public void setDate_rv(Date date_rv) {
        this.date_rv = date_rv;
    }
    @Override
    public String toString() {
        return "rendez_vous{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", cause=" + cause + ", description=" + description + ", date_rv=" + date_rv + '}';
    }
}
