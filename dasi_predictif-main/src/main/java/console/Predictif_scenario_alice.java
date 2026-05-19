/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package console;

import dao.JpaUtil;
import java.time.LocalDate;
import java.util.List;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Medium;
import metier.service.Service;
import metier.service.ServiceInitialisation;

/**
 *
 * @author yguerin
 */
public class Predictif_scenario_alice {

    public static void main(String[] args) {

        JpaUtil.creerFabriquePersistance();
        List<Medium> liste= Service.recupererListeMediums();
        System.out.println(liste);
     ServiceInitialisation.initialiserClient();
       ServiceInitialisation.initialiserEmploye();
      ServiceInitialisation.initialiserMedium();
       
        
      scenarioAlice();
    }

    public static void scenarioAlice() {
        System.out.println("=================SCENARIO ALICE=================");
        System.out.println("Consultation de la liste des mediums : ");
        System.out.println(Service.recupererListeMediums());
        Client client = new Client("Pascal", "Alice", "86 Rue des Lilas Marcq-en-Baroeul 59100", "alice.pascal@free.fr", "1234", "0688774455", LocalDate.parse("2005-03-26"));
        System.out.println("Inscription d'Alice : ");
        if(!Service.inscireClient(client)){
            System.out.println("L'inscription à échoué ! Relancez le test.");
            return;
        }
        System.out.println("Connexion d'alice : ");
        Client clientAuthentifie = Service.authentifierClient(client.getMail(), client.getMotDePasse());
        System.out.println("Alice regarde son profil astral : ");
        System.out.println(clientAuthentifie.getProfilAstral());
        System.out.println("Alice demande une consultation : ");
        Consultation consultation = Service.demanderConsultation(clientAuthentifie, Service.recupererMediumParId(10));
       
        System.out.println(consultation);

    }

}
