/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.eshkili.entities;

/**
 *
 * @author LENOVO
 */
public class JournalMood {
    
    private int id,id_user,moods_id; 
  

   
       public JournalMood(int id_user, int moods_id) {
        
        this.id_user = id_user;
        this.moods_id = moods_id;

    }


    public JournalMood(int id, int id_user, int moods_id) {
           
        this.id = id;
        this.id_user = id_user;
        this.moods_id = moods_id;

}

  public JournalMood() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getMoods_id() {
        return moods_id;
    }

    public void setMoods_id(int moods_id) {
        this.moods_id = moods_id;
    }

    @Override
    public String toString() {
        return "JournalMood{" + "id=" + id + ", id_user=" + id_user + ", moods_id=" + moods_id + '}';
    }


    




}
