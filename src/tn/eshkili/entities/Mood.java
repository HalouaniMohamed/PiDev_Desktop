/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.eshkili.entities;

/**
 *
 * @author LENOVO
 */
public class Mood {
    private int id,user_id,mood_id; 
    private String mood,description;

       public Mood() {
    }


    public Mood(int id, int user_id, int mood_id, String mood, String description) {
           
        this.id = id;
        this.user_id = user_id;
        this.mood_id = mood_id;
        this.mood = mood;
        this.description = description;

}
    

  public Mood( int user_id, int mood_id, String mood, String description) {
       
        this.user_id = user_id;
        this.mood_id = mood_id;
        this.mood = mood;
        this.description = description;
    }

    public Mood(int user_id, String mood, String description , int mood_id) {
        this.mood_id = mood_id;
        this.mood = mood;
        this.description = description;
        this.user_id = user_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMood_id() {
        return mood_id;
    }

    public void setMood_id(int mood_id) {
        this.mood_id = mood_id;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Mood{" + "id=" + id + ", user_id=" + user_id + ", mood_id=" + mood_id + ", mood=" + mood + ", description=" + description + '}';
    }

    




}