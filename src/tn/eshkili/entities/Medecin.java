/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.entities;

/**
 *
 * @author Mongi
 */
public class Medecin {
    private int id,num_telephone ;
    private String nom,prenom,adresse;

    public Medecin(int id, int num_telephone, String nom, String prenom, String adresse) {
        this.id = id;
        this.num_telephone = num_telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public Medecin(int num_telephone, String nom, String prenom, String adresse) {
        this.num_telephone = num_telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public Medecin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_telephone() {
        return num_telephone;
    }

    public void setNum_telephone(int num_telephone) {
        this.num_telephone = num_telephone;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    
}
