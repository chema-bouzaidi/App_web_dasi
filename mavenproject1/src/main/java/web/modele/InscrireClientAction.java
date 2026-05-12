/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * @author cbouzaidi
 */
public class InscrireClientAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        System.out.println("\nInscriptionClient ------- ");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String dateNaissance = req.getParameter("dateNaissance");
        String adresse = req.getParameter("adresse");
        String telephone = req.getParameter("telephone");
        String mail = req.getParameter("mail");
        String mdp = req.getParameter("mdp");
        Client client = new Client(nom,prenom,adresse,mail,mdp,telephone, LocalDate.parse(dateNaissance));
        Boolean succes= Service.inscireClient(client);
        req.setAttribute("succes", succes);

    }
}
