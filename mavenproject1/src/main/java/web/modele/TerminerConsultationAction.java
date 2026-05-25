/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.service.Service;

public class TerminerConsultationAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        System.out.println("\nTerminerConsultationAction -------");
        HttpSession session = req.getSession();

        Integer idEmploye = (Integer) session.getAttribute("id");
        Employe employe = Service.recupererEmployeParId(idEmploye);
        if (employe != null) {
            Consultation consultation = Service.recupererConsultationActuelleEmploye(employe);
            if (consultation != null) {
                String commentaire = req.getParameter("commentaire");
                Boolean ok = Service.terminerConsultation(consultation, commentaire);
                req.setAttribute("succes", ok);
                return;
            }
        }

        req.setAttribute("succes", false);
    }
}
