/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.service;

import dao.ClientDAO;
import dao.ConsultationDAO;
import dao.EmployeDAO;
import dao.JpaUtil;
import dao.MediumDAO;
import java.time.LocalDate;
import java.util.List;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import util.ApiInteraction;
import util.Message;

/**
 *
 * @author yguerin
 */
public class Service {

    static public Boolean inscireClient(Client client) {
        Boolean inscriptionWorked = true;
        client.setProfilAstral(ApiInteraction.getAPIProfilAstral(client));
        if (client.getProfilAstral() == null) {
            inscriptionWorked = false;
        }
        client.setGps(ApiInteraction.getGpsCoordinates(client));
        if(client.getGps() == null){
            inscriptionWorked = false;
        }
        
        if(inscriptionWorked){
            JpaUtil.creerContextePersistance();
            try {
                JpaUtil.ouvrirTransaction();
                ClientDAO.create(client);
                JpaUtil.validerTransaction();
                envoyerMailInscription(client);
            } catch (Exception ex) {
                System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                inscriptionWorked = false;
            } finally {
                JpaUtil.fermerContextePersistance();
            }
        }
        if(!inscriptionWorked){
            envoyerMailInscriptionFail(client);
        }
        return inscriptionWorked;
    }

    static public Client authentifierClient(String mail, String mdp) {
        Client client = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            client = ClientDAO.getClientByMail(mail);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
            if (client == null || (client.getMotDePasse() == null ? mdp != null : !client.getMotDePasse().equals(mdp))) {
                client = null;
            }
        }
        return client;
    }

    static private void envoyerMailInscription(Client client) {
        Message.envoyerMail("service.inscription@predictif.fr", client.getMail(), "Inscription a Predict'IF !!!!!", "Bonjour " + client.getPrenom() + ", nous vous confirmons votre inscription au service PREDICT'IF. Rendez-vous sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums.");
    }

    static private void envoyerMailInscriptionFail(Client client) {
        Message.envoyerMail("service.inscription@predictif.fr", client.getMail(), "Echec de l'inscription chez PREDICT'IF", "Bonjour " + client.getPrenom() + ", votre inscription au service PREDICT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.");
    }

    static public Consultation demanderConsultation(Client client, Medium medium) {
        Consultation consultation = null;
        JpaUtil.creerContextePersistance();
        try {
            Employe employe = EmployeDAO.trouverEmployeLibre(medium);
            if (employe != null) {
                JpaUtil.ouvrirTransaction();
                consultation = new Consultation(client, medium, employe, LocalDate.now());
                ConsultationDAO.create(consultation);
                employe.setEstDisponible(false);
                employe.augmenterNbConsultations();
                EmployeDAO.merge(employe);
                medium.augmenterNbConsultations();
                MediumDAO.merge(medium);
                client.augmenterNbConsultations();
                ClientDAO.merge(client);
                JpaUtil.validerTransaction();
                Message.envoyerNotification(employe.getTelephone(), "Bonjour " + employe.getPrenom() + ". Consultation requise pour " + client.getPrenom() + " " + client.getNom().toUpperCase() + ". Médium à incarner : " + medium.getDenomination() + ".");

            }
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            consultation = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return consultation;
    }

    static public Medium recupererMediumParId(Integer id) {
        Medium medium = null;
        JpaUtil.creerContextePersistance();
        try {
            medium = MediumDAO.getMediumById(id);
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return medium;
    }
    
    static public Employe recupererEmployeParId(Integer id) {
        Employe employe = null;
        JpaUtil.creerContextePersistance();
        try {
            employe = EmployeDAO.getEmployeById(id);
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return employe;
    }

    static public List<Medium> recupererListeMediums() {
        List<Medium> mediums = null;
        JpaUtil.creerContextePersistance();
        try {
            mediums = MediumDAO.getMediumList();
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return mediums;
    }

    static public List<Client> recupererListeClients() {
        List<Client> clients = null;
        JpaUtil.creerContextePersistance();
        try {
            clients = ClientDAO.getClientList();
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return clients;
    }

    static public List<Consultation> recupererConsulationsClient(Client client) {
        List<Consultation> consultations = null;
        JpaUtil.creerContextePersistance();
        try {
            consultations = ConsultationDAO.getClientConsultations(client);
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return consultations;
    }

    static public Client recupererClientParId(Integer id) {
        Client client = null;
        JpaUtil.creerContextePersistance();
        try {
            client = ClientDAO.getClientById(id);
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return client;
    }
    
    static public Consultation recupererConsultationParId(Integer id) {
        Consultation consultation = null;
        JpaUtil.creerContextePersistance();
        try {
            consultation = ConsultationDAO.getConsultationById(id);
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return consultation;
    }

    static public Boolean seMettrePret(Employe employe) {
        Boolean ok = false;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            employe.setEstPret(Boolean.TRUE);
            EmployeDAO.merge(employe);
            JpaUtil.validerTransaction();
            ok = true;
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
            Consultation consultation = recupererConsultationActuelleEmploye(employe);
            Message.envoyerNotification(consultation.getClient().getTelephone(), "Bonjour " + consultation.getClient().getPrenom() + ". J'ai bien reçu votre demande de consultation du " + LocalDate.now().toString() + ". Vous pouvez dès à présent me contacter au " + consultation.getEmploye().getTelephone() + ". A tout de suite ! Médiumiquement vôtre, " + consultation.getMedium().getDenomination() + ".");

        }
        return ok;
    }

    static public Consultation recupererConsultationActuelleEmploye(Employe employe) {
        JpaUtil.creerContextePersistance();
        Consultation consultation = null;
        try {
            consultation = ConsultationDAO.getEmployeeCurrentConsultation(employe);
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return consultation;
    }

    static public Boolean terminerConsultation(Consultation consultation, String commentaire) {
        Boolean ok = false;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultation.setDateFin(LocalDate.now());
            consultation.setCommentaire(commentaire);
            Employe employe = consultation.getEmploye();
            employe.setEstPret(Boolean.FALSE);
            employe.setEstDisponible(Boolean.TRUE);
            EmployeDAO.merge(employe);
            ConsultationDAO.merge(consultation);
            JpaUtil.validerTransaction();
            ok = true;
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return ok;
    }

    static public List<Medium> recupererTop5Medium() {
        JpaUtil.creerContextePersistance();
        List<Medium> mediums = null;
        try {
            mediums = MediumDAO.getTop5Mediums();
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return mediums;
    }

    static public List<Client> recupererTop5Client() {
        JpaUtil.creerContextePersistance();
        List<Client> clients = null;
        try {
            clients = ClientDAO.getTop5Client();
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return clients;
    }

    static public Employe authentifierEmploye(String mail, String mdp) {
        Employe employe = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            employe = EmployeDAO.getEmployeByMail(mail);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
            if (employe == null || (employe.getMotDePasse() == null ? mdp != null : !employe.getMotDePasse().equals(mdp))) {
                employe = null;
            }
        }
        return employe;
    }

    static public List<Employe> recupererListeEmployes() {
        List<Employe> employes = null;
        JpaUtil.creerContextePersistance();
        try {
            employes = EmployeDAO.getEmployesList();
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return employes;
    }

    static public List<String> obtenirPrediction(Client client, Integer niveauAmour, Integer niveauSante, Integer niveauTravail) {
        return ApiInteraction.getAPIPrediction(client, niveauAmour, niveauSante, niveauTravail);
    }

    static public List<Employe> recupererTopEmployes() {
        JpaUtil.creerContextePersistance();
        List<Employe> employes = null;
        try {
            employes = EmployeDAO.getTopEmployesList();
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return employes;
    }

    static public Medium recupererMediumParNom(String nom) {
        Medium medium = null;
        JpaUtil.creerContextePersistance();
        try {
            medium = MediumDAO.getMediumByName(nom);
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return medium;
    }
}
