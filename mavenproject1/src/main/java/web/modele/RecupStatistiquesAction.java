/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.Service;

public class RecupStatistiquesAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        System.out.println("\nRecupStatistiquesAction -------");
        List<Medium> top5Mediums = Service.recupererTop5Medium();
        List<Client> top5Clients = Service.recupererTop5Client();
        List<Employe> topEmployes = Service.recupererTopEmployes();
        req.setAttribute("top5Mediums", top5Mediums);
        req.setAttribute("top5Clients", top5Clients);
        req.setAttribute("topEmployes", topEmployes);
    }
}
