/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.eshkili.test;

import java.util.Date;
import java.util.List;
import tn.eshkili.entities.Cabinet;
import tn.eshkili.entities.Medecin;
import tn.eshkili.entities.rendez_vous;
import tn.eshkili.services.CabinetService;
import tn.eshkili.services.RendezVous;
import tn.eshkili.utils.MaConnexion;

/** 
 *
 * @author Mongi
 */
public class Main { 
    
    public static void main(String[] args) {
//        MaConnexion mc = MaConnexion.getInstance();
//        MaConnexion mc1 = MaConnexion.getInstance();
//        MaConnexion mc2 =  MaConnexion.getInstance();

//// pouuuuuuurrrr mooddiiiiiifiiiiiierrrrrr
//RendezVous a = new RendezVous();
//
//rendez_vous rv = new rendez_vous();
//Medecin medecin = new Medecin();
//Cabinet cabinet = new Cabinet();
//
//// initialisation des données du rendez-vous, du médecin et du cabinet
//rv.setNom("az");
//rv.setPrenom("Marie");
//rv.setCause("Consultation de routine");
//rv.setDescription("Aucune description disponible");
//rv.setDate_rv(new Date());
//
//medecin.setId(1);
//cabinet.setId(1);
//
//// appel de la méthode modifierRendezVous() avec les paramètres appropriés
//a.modifierRendezVous(27, rv, medecin, cabinet);


// c'bonnnnn c'est mooooodiiiiifiiiiiieeeerrrr
//********************************************************************************//
//// poouuuurrrr suuuuuuppppprriiiiiiimeeeerrrrr
//RendezVous r = new RendezVous();
//int id = 27; // ID du rendez-vous à supprimer
//rendez_vous rv = r.getRendezVousById(id); // Obtenir le rendez-vous par son ID
//r.supprimer(id); // Supprimer le rendez-vous de la base de données
// c bon suuuuuuppppprriiiiiiimeeeerrrrr

//***************************************************************************//

//pooouuuuurrrrr aaaaaaaafffffficccchhhhhhheeeeeerrrr
// RendezVous rendezVous = new RendezVous();
// rendezVous.afficherRendezVous();
//  
// }
// c''''eeessssstttttt booooooonnnnnnn aaaaaaaafffffficccchhhhhhheeeeeerrrr

//*************************************************************************//
//poooooouuuuuuurrrrr aaaaaajouuuuuuuteeeeeerrrrrr
// RendezVous rendezVous = new RendezVous();
//Medecin medecin = new Medecin();
//Cabinet cabinet = new Cabinet();
//medecin.setId(1); // remplacez 1 par l'ID du médecin concerné
//cabinet.setId(1); // remplacez 1 par l'ID du médecin concerné
//
//
//rendez_vous r = new rendez_vous();
// r.setNom("Nom du patient");
// r.setPrenom("Prénom du patient");
// r.setCause("Cause du rendez-vous");
// r.setDescription("Description du rendez-vous");
// r.setDate_rv(new java.util.Date()); // remplacez java.util.Date() par la date souhaitée pour le rendez-vous
//rendezVous.add(r,medecin,cabinet);

// c'essssssstt booooooonnnnn c'eeeeessstttt aaaaaajouuuuuuuuuteeeeeerrrrrrrrrrr








// pouuuuuuuuuurrr ajouuuuter cabinet //

//Cabinet cabinet = new Cabinet();
//    cabinet.setNom("Cabinet médical du parc");
//    cabinet.setAdresse("12 rue des Lilas, 75020 Paris");
//    
//    // Création d'un objet CabinetDAO pour effectuer l'ajout
//        CabinetService cabinetDAO = new CabinetService();
//    cabinetDAO.ajouter(cabinet);
//


////****************************************//


//pouuuuurrrr moooodifierrrr 
//CabinetService service = new CabinetService();
//    int id = 1; // l'identifiant du cabinet à modifier
//    Cabinet cabinet = new Cabinet();
//    cabinet.setNom(" SOFT-CABINET"); // le nouveau nom du cabinet
//    cabinet.setAdresse(" Tunis"); // la nouvelle adresse du cabinet
//    service.modifier(id, cabinet);
// pour supprimer 
//CabinetService service = new CabinetService();
//    int id = 1; // l'identifiant du cabinet à supprimer
//    service.supprimer(id);
//*************************************************************//


// pour afficher cabinet ////////////////////
//   CabinetService service = new CabinetService();
//    List<Cabinet> cabinets = service.getAllCabinets();
//    for (Cabinet cabinet : cabinets) {
//        System.out.println(cabinet.getNom() + " - " + cabinet.getAdresse());
//    }
  }
}
    

    