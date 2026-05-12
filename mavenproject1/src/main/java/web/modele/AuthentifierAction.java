/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.Service;

/**
 *
 * @author egoncalves
 */
public class AuthentifierAction extends Action {
    @Override
    public void execute(HttpServletRequest req) {
        System.out.println("\nConnexion ------- ");
        String mail=req.getParameter("mail");
        String mdp=req.getParameter("mdp");
       
        Client client = Service.authentifierClient(mail, mdp);
        
        req.setAttribute("client", client);
         if (client != null) {
            System.out.println("\nConnexion Client ------- ");
            HttpSession session = req.getSession();
            session.setAttribute("statut", "client");
            session.setAttribute("id", client.getId());
         }
         else {
             Employe employe = Service.authentifierEmploye(mail, mdp);
              if (employe != null) {
                System.out.println("\nConnexion Employe ------- ");
                HttpSession session = req.getSession();
                session.setAttribute("statut", "employe");
                session.setAttribute("id", employe.getId());
            }
        
         }
        
    
}
}
