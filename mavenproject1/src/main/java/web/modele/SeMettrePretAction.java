/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.modele.Employe;
import metier.service.Service;

public class SeMettrePretAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        System.out.println("\nSeMettrePretAction -------");
        HttpSession session = req.getSession();
        if ("employe".equals(session.getAttribute("statut"))) {
            Integer idEmploye = (Integer) session.getAttribute("id");
            Employe employe = Service.recupererEmployeParId(idEmploye);
            if (employe != null) {
                Boolean ok = Service.seMettrePret(employe);
                req.setAttribute("succes", ok);
            } else {
                req.setAttribute("succes", false);
            }
        } else {
            req.setAttribute("succes", false);
        }
    }
}