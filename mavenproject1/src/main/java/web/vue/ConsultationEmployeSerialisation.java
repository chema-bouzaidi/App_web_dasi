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
import metier.modele.Consultation;
import metier.modele.ProfilAstral;

public class ConsultationEmployeSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("\nConsultationEmployeSerialisation -------");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        Consultation consultation = (Consultation) req.getAttribute("consultation");
        JsonObjectBuilder json = Json.createObjectBuilder();

        if (consultation == null) {
            json.add("succes", false);
        } else {
            Client client = (Client) req.getAttribute("client");
            List<Consultation> historique = (List<Consultation>) req.getAttribute("historique");

            json.add("succes", true);
            json.add("prenomClient", client.getPrenom());
            json.add("nomClient", client.getNom());
            json.add("denominationMedium", consultation.getMedium().getDenomination());

            ProfilAstral pa = client.getProfilAstral();
            if (pa != null) {
                json.add("signeZodiaque", pa.getSigneZodiaque());
                json.add("signeChinois", pa.getSigneChinois());
                json.add("couleur", pa.getCouleur());
                json.add("animalTotem", pa.getAnimalTotem());
            }

            JsonArrayBuilder jsonHistorique = Json.createArrayBuilder();
            if (historique != null) {
                for (Consultation c : historique) {
                    if (c.getDateFin()!=null){
                    JsonObjectBuilder jsonC = Json.createObjectBuilder();
                    jsonC.add("medium", c.getMedium().getDenomination());
                    String commentaire = c.getCommentaire() != null ? c.getCommentaire() : "";
                    jsonC.add("commentaire", commentaire);
                    String date = c.getDateFin() != null ? c.getDateFin().toString() : "En cours";
                    jsonC.add("date", date);
                    jsonHistorique.add(jsonC);
                    }
                }
            }
            json.add("historique", jsonHistorique);
        }

        try (PrintWriter out = res.getWriter()) {
            out.print(json.build().toString());
        }
    }
}