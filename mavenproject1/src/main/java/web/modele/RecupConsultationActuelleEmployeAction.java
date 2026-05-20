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
import metier.modele.Employe;
import metier.service.Service;

public class RecupConsultationActuelleEmployeAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        System.out.println("\nRecupConsultationActuelleEmployeAction -------");
        HttpSession session = req.getSession();
        if ("employe".equals(session.getAttribute("statut"))) {
            Integer idEmploye = (Integer) session.getAttribute("id");
            Employe employe = Service.recupererEmployeParId(idEmploye);
            if (employe != null) {
                Consultation consultation = Service.recupererConsultationActuelleEmploye(employe);
                if (consultation != null) {
                    Client client = consultation.getClient();
                    List<Consultation> historique = Service.recupererConsulationsClient(client);
                    req.setAttribute("consultation", consultation);
                    req.setAttribute("client", client);
                    req.setAttribute("historique", historique);
                }
            }
        }
    }
}