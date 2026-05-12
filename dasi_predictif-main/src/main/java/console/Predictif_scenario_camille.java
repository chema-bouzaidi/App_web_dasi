/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package console;

import dao.JpaUtil;
import java.time.LocalDate;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.service.Service;
import metier.service.ServiceInitialisation;
import util.ApiInteraction;

/**
 *
 * @author yguerin
 */
public class Predictif_scenario_camille {

    public static void main(String[] args) {

        JpaUtil.creerFabriquePersistance();
        ServiceInitialisation.initialiserClient();
        ServiceInitialisation.initialiserEmploye();
        ServiceInitialisation.initialiserMedium();
        
        scenarioCamille();
    }

    public static void scenarioCamille() {
        System.out.println("=================SCENARIO CAMILLE=================");
        Consultation c = Service.demanderConsultation(Service.recupererClientParId(1), Service.recupererMediumParNom("Mme Irma"));
        Employe employe = Service.authentifierEmploye("cl@jaimail.fr", "123");
        Consultation consultation = Service.recupererConsultationActuelleEmploye(employe);
        System.out.println("Consultation :");
        System.out.println(consultation);
        Client consultationClient = consultation.getClient();
        System.out.println("Profil astral du client de la consultation :");
        System.out.println(consultation.getClient().getProfilAstral());
        
        System.out.println("Historique des consultations du client :");
        System.out.println(Service.recupererConsulationsClient(consultationClient));
        System.out.println("Prédiction obtenue :");
        System.out.println(Service.obtenirPrediction(consultation.getClient(), 1, 2, 1));
        System.out.println("Camille se met prête :");
        Service.seMettrePret(employe);
        System.out.println("Consultation finie :");
        Service.terminerConsultation(consultation, "Superbe consultation ! Alice est promise à un grand avenir ! Il faut juste qu'elle ouvre ses chakras et tout ira mieux !");
        System.out.println("Client Map : (regarder les gps)");
        System.out.println(Service.recupererListeClients());
        System.out.println("Top 5 Pigeons :");
        System.out.println(Service.recupererTop5Client());
        System.out.println("Top 5 Mediums :");
        System.out.println(Service.recupererTop5Medium());
        System.out.println("Top Employes :");
        System.out.println(Service.recupererTopEmployes());
    }

}
