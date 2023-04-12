/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;
import java.util.List;
import tn.esprit.entities.Post;

/**
 *
 * @author asus
 */
public interface NewInterface<T> {
    public void ajouter(T p);
     public void modifier(T p);
    public List<T> afficher();
    public void supprimer(  T p);
    T getById(int id);
    
}
