/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.service;

import dao.EmployeDAO;
import dao.JpaUtil;
import dao.MediumDAO;
import java.time.LocalDate;
import metier.modele.Astrologue;
import metier.modele.Cartomancier;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Spirite;

/**
 *
 * @author yguerin
 */
public class ServiceInitialisation {

    static public void initialiserClient() {

        Service.inscireClient(new Client("Guerin", "Yoan", "20 avenue Albert Einstein, Villeurbanne 69100", "yg@hotmail.com", "123", "0600000000", LocalDate.now()));
        Service.inscireClient(new Client("Fontaine", "Jean", "20 avenue Albert Einstein, Villeurbanne 69100", "jf@hotmail.com", "123", "0600000000", LocalDate.now()));
        Service.inscireClient(new Client("Drucker", "Jules", "20 avenue Albert Einstein, Villeurbanne 69100", "jd@hotmail.com", "123", "0600000000", LocalDate.now()));
        Service.inscireClient(new Client("Dupont", "François", "20 avenue Albert Einstein, Villeurbanne 69100", "fd@hotmail.com", "123", "0600000000", LocalDate.now()));
        Service.inscireClient(new Client("Longues Oreilles", "Paco", "20 avenue Albert Einstein, Villeurbanne 69100", "plo@hotmail.com", "123", "0600000000", LocalDate.now()));
    }

    static public boolean initialiserMedium() {
        Cartomancier cartomancier1 = new Cartomancier("Mme Irma", "F", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
        Cartomancier cartomancier2 = new Cartomancier("Endora", "F", "Mes cartes réponderont à toutes vos questions personelles.");

        Astrologue astrologue1 = new Astrologue("Mr M", "M", "Avenir, avenir, que nous reserves-tu ? N'attendez plus, demandez à me consulter !", "Institut des Nouveaux Savoirs Astrologiques", "2010");
        Astrologue astrologue2 = new Astrologue("Serena", "F", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.", "Ecole Nationale Supérieure d'Astrologie (ENS-Astro)", "2006");

        Spirite spirite1 = new Spirite("Professeur Tran", "M", "Votre avenir est devant vous : regardons-le ensemble !", "Marc de café, boule de cristal, oreilles de lapin");
        Spirite spirite2 = new Spirite("Gwenaëlle", "F", "Spécialiste des grandes conversations au-delà de TOUTES les frontières.", "Boule de cristal");

        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            MediumDAO.create(cartomancier1);
            MediumDAO.create(cartomancier2);
            MediumDAO.create(astrologue1);
            MediumDAO.create(astrologue2);
            MediumDAO.create(spirite1);
            MediumDAO.create(spirite2);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return true;
    }

    static public boolean initialiserEmploye() {
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            EmployeDAO.create(new Employe("Guerin", "Yoan", "yg@gmail.fr", "M", "123", "06 62 83 96 94"));
            EmployeDAO.create(new Employe("Letourneur", "Camille", "cl@jaimail.fr", "F", "123", "06 62 83 96 95"));
            EmployeDAO.create(new Employe("Wallerick", "Nathan", "nw@yahoo.fr", "M", "123", "06 62 83 96 95"));
            EmployeDAO.create(new Employe("Manchec", "Serguei", "sm@hotmail.fr", "M", "super mot de passe", "07 67 23 96 23"));
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            System.getLogger(Service.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return true;
    }

}
