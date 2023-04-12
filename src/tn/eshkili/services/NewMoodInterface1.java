/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tn.eshkili.services;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface NewMoodInterface1 {
        public void ajouterMood(Mood M);
   public List<Mood> afficherMood();
    public void supprimerMood(int id);
    public void modifierMood(Mood M);
    
}
