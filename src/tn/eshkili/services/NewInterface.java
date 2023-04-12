/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.services;

import java.util.List;

/**
 *
 * @author Mongi
 */
    public interface NewInterface<T> {
    public void ajouter(T p);
    public List<T> afficher();
    public void modifier(int id, T p);


    
    
}
