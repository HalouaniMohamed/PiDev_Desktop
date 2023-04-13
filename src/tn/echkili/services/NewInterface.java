/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.echkili.services;
import java.util.List;
import tn.echkili.entities.User;

/**
 *
 * @author rayen
 */
public interface NewInterface<T> {
    
    public void ajouter (T u);
    public List<T> afficher ();
    public void supprimer(T u);
    
}
