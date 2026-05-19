/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.ProfilAstral;
import metier.service.Service;

/**
 *
 * @author egoncalves
 */
public class RecupClientAction extends Action{
    
    @Override
    public void execute(HttpServletRequest req) {
        System.out.println("\nRecuperer Clientl ------- ");
        HttpSession session = req.getSession();
        if (session.getAttribute("statut").equals("client")){
            Integer idClient = (Integer) session.getAttribute("id");
            Client client= Service.recupererClientParId(idClient);
            List<Consultation> listeconsultations = Service.recupererConsulationsClient(client);
            req.setAttribute("listeconsultations",listeconsultations);
           
            req.setAttribute("client",client);
            
        
            
            
            
        }
            
        }
    
}
