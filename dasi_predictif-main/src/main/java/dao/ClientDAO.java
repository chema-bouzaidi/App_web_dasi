/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static java.lang.Integer.min;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import metier.modele.Client;

/**
 *
 * @author yguerin
 */
public class ClientDAO {

    static public void create(Client client) {
        JpaUtil.obtenirContextePersistance().persist(client);
    }

    static public Client getClientByMail(String mail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select a from Client a where a.mail = :mail";
        TypedQuery query = em.createQuery(jpql, Client.class);
        query.setParameter("mail", mail);
        return (Client) query.getSingleResult();
    }

    static public Client getClientById(Integer id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Client.class, id);
    }

    static public List<Client> getClientList() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select c from Client c";
        TypedQuery query = em.createQuery(jpql, Client.class);
        return query.getResultList();
    }

    static public List<Client> getTop5Client() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select e from Client e ORDER BY e.nbConsultations DESC";
        TypedQuery query = em.createQuery(jpql, Client.class);
        List<Client> temp = query.getResultList();
        return temp.subList(0, min(4, temp.size()));
    }
    
    static public Client merge(Client client) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(client);
        return client;
    }
}
