/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Medium;
import metier.service.Service;

/**
 *
 * @author egoncalves
 */
public class PrendreRdvAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        System.out.println("\nprendre rdv  ------- ");
        HttpSession session = req.getSession();
        if (session.getAttribute("statut").equals("client")) {
            Integer idClient = (Integer) session.getAttribute("id");
            Integer idMedium = Integer.parseInt(req.getParameter("idMedium"));
            Client client = Service.recupererClientParId(idClient);
            Medium medium = Service.recupererMediumParId(idMedium);
            Consultation consultation = Service.demanderConsultation(client, medium);
            req.setAttribute("succes", consultation != null);
        }

    }
}
