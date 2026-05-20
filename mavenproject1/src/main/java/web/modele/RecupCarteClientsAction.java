/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import metier.modele.Client;
import metier.service.Service;

public class RecupCarteClientsAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        System.out.println("\nRecupCarteClientsAction -------");
        List<Client> clients = Service.recupererListeClients();
        req.setAttribute("clients", clients);
    }
}

