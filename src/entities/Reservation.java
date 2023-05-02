/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS
 */
public class Reservation {
    private int id,nombre_de_place_areserver;
    private String email;
    private Evenements e ;
        private String enom ;

    public Reservation(int id, int nombre_de_place_areserver, String email, String enom) {
        this.id = id;
        this.nombre_de_place_areserver = nombre_de_place_areserver;
        this.email = email;
        this.enom = enom;
    }

    public String getEnom() {
        return enom;
    }

    public void setEnom(String enom) {
        this.enom = enom;
    }

    public Reservation() {
    }
//Constructeur paramétré 
    public Reservation(int id, int nombre_de_place_areserver, String email ,Evenements e) {
        this.id = id;
        this.nombre_de_place_areserver = nombre_de_place_areserver;
        this.email = email;
        this.e=e;
        
    }
//Constructeur paramétré sans id
    public Reservation(int nombre_de_place_areserver, String email ,Evenements e) {
        this.nombre_de_place_areserver = nombre_de_place_areserver;
        this.email = email;
        this.e=e;
    }
//Constructeur paramétré sans Evenement
    public Reservation(int id, int nombre_de_place_areserver, String email) {
        this.id = id;
        this.nombre_de_place_areserver = nombre_de_place_areserver;
        this.email = email;
    }
//Constructeur paramétré sans id et événement
    public Reservation(int nombre_de_place_areserver, String email) {
        this.nombre_de_place_areserver = nombre_de_place_areserver;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public Evenements getE() {
        return e;
    }

    public void setE(Evenements e) {
        this.e = e;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombre_de_place_areserver() {
        return nombre_de_place_areserver;
    }

    public void setNombre_de_place_areserver(int nombre_de_place_areserver) {
        this.nombre_de_place_areserver = nombre_de_place_areserver;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", nombre_de_place_areserver=" + nombre_de_place_areserver + ", email=" + email + ", e=" + e + '}';
    }

    

    
}
