/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tn.eshkili.services;

import java.util.List;
import tn.eshkili.entities.Mood;

/**
 *
 * @author LENOVO
 * @param <T>
 */
public interface NewMoodInterface1<T> {
    public void ajouterMood(Mood M);
    public List<Mood> afficherMood();
    public void supprimerMood(int id);
    public void modifierMood(Mood M);
    
}
