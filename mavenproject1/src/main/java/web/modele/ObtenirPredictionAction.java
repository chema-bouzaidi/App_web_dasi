/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import metier.modele.Client;
import metier.service.Service;

public class ObtenirPredictionAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        System.out.println("\nObtenirPredictionAction -------");
        HttpSession session = req.getSession();
        if ("employe".equals(session.getAttribute("statut"))) {
            Integer idEmploye = (Integer) session.getAttribute("id");
            metier.modele.Employe employe = Service.recupererEmployeParId(idEmploye);
            if (employe != null) {
                metier.modele.Consultation consultation = Service.recupererConsultationActuelleEmploye(employe);
                if (consultation != null) {
                    Client client = consultation.getClient();
                    Integer niveauAmour = Integer.parseInt(req.getParameter("amour"));
                    Integer niveauSante = Integer.parseInt(req.getParameter("sante"));
                    Integer niveauTravail = Integer.parseInt(req.getParameter("travail"));
                    List<String> prediction = Service.obtenirPrediction(client, niveauAmour, niveauSante, niveauTravail);
                    req.setAttribute("prediction", prediction);
                    req.setAttribute("succes", prediction != null);
                    return;
                }
            }
        }
        req.setAttribute("succes", false);
    }
}
