/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;

/**
 *
 * @author yguerin
 */
public class ConsultationDAO {

    static public void create(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }

    static public Consultation getConsultationById(Integer id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Consultation.class, id);
    }

    static public List<Consultation> getClientConsultations(Client client) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select c from Consultation c where c.client= :client";
        TypedQuery query = em.createQuery(jpql, Consultation.class);
        query.setParameter("client", client);
        return query.getResultList();

    }
    
    static public Consultation getEmployeeCurrentConsultation(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select c from Consultation c where c.employe= :employe and c.dateFin = null";
        TypedQuery query = em.createQuery(jpql, Consultation.class);
        query.setParameter("employe", employe);
        return (Consultation)query.getSingleResult();
    }
    
    static public Consultation merge(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(consultation);
        return consultation;
    }
    
}
