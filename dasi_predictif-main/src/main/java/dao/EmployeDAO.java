/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static java.lang.Integer.min;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import metier.modele.Employe;
import metier.modele.Medium;

/**
 *
 * @author yguerin
 */
public class EmployeDAO {

    static public void create(Employe employe) {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }

    static public Employe getEmployeById(Integer id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Employe.class, id);
    }

    static public Employe trouverEmployeLibre(Medium medium) {
        Employe employe = null;

        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select e from Employe e where e.genre = :genre and e.estDisponible = true ORDER BY e.nbConsultations ";
        TypedQuery query = em.createQuery(jpql, Employe.class);
        query.setParameter("genre", medium.getGenre());
        List<Employe> employes = query.getResultList();
        if (!employes.isEmpty()) {
            employe = employes.getFirst();
        }
        
        return employe;
    }

    static public Employe merge(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(employe);
        return employe;
    }
    
    static public Employe getEmployeByMail(String mail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select a from Employe a where a.mail = :mail";
        TypedQuery query = em.createQuery(jpql, Employe.class);
        query.setParameter("mail", mail);
        return (Employe) query.getSingleResult();
    }
    
    static public List<Employe> getEmployesList (){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select e from Employe e";
        TypedQuery query = em.createQuery(jpql, Employe.class);
        return query.getResultList();
    }
    
    static public List<Employe> getTopEmployesList (){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select e from Employe e ORDER BY e.nbConsultations DESC";
        TypedQuery query = em.createQuery(jpql, Employe.class);
        return query.getResultList();
    }
    
}
