/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.vue;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import metier.modele.Client;

public class CarteClientsSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("\nCarteClientsSerialisation -------");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        List<Client> clients = (List<Client>) req.getAttribute("clients");
        JsonArrayBuilder jsonClients = Json.createArrayBuilder();

        if (clients != null) {
            for (Client c : clients) {
                List<String> gps = c.getGps();
                if (gps != null && gps.size() >= 2) {
                    JsonObjectBuilder o = Json.createObjectBuilder();
                    o.add("nom", c.getNom() != null ? c.getNom() : "");
                    o.add("prenom", c.getPrenom() != null ? c.getPrenom() : "");
                    o.add("adresse", c.getAdresse() != null ? c.getAdresse() : "");
                    try {
                        o.add("latitude", Double.parseDouble(gps.get(0)));
                        o.add("longitude", Double.parseDouble(gps.get(1)));
                        jsonClients.add(o);
                    } catch (NumberFormatException e) {
                        System.out.println("GPS invalide pour client " + c.getNom());
                    }
                }
            }
        }

        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("clients", jsonClients);

        try (PrintWriter out = res.getWriter()) {
            out.print(json.build().toString());
        }
    }
}
