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
public class Cabinet {
    private int id ;
    private String adresse ;

    public Cabinet(int id, String adresse) {
        this.id = id;
        this.adresse = adresse;
    }

    public Cabinet() {
    }

    public Cabinet(String adresse) {
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
}
