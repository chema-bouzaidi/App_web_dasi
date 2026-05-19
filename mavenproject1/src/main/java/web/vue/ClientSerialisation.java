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
import metier.modele.Medium;
import metier.modele.ProfilAstral;

/**
 *
 * @author egoncalves
 */
public class ClientSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
         System.out.println("\nProfilAstralSerialisation ------- ");
        
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        Client client = (Client) req.getAttribute("client");
        ProfilAstral pa= client.getProfilAstral();
        List<Consultation> liste_consultations = (List<Consultation>) req.getAttribute("listeconsultations");
        JsonArrayBuilder jsonHistorique = Json.createArrayBuilder();
         for (Consultation consultation : liste_consultations) {
            JsonObjectBuilder jsonConsultation = Json.createObjectBuilder();
            jsonConsultation.add("medium",consultation.getMedium().getDenomination());
            jsonConsultation.add("date",consultation.getDateFin().toString());
            jsonHistorique.add(jsonConsultation);}
            
       
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("signeZodiaque", pa.getSigneZodiaque());
        json.add("signeChinois", pa.getSigneChinois());
        json.add("couleur", pa.getCouleur());
        json.add("AnimalTotem", pa.getAnimalTotem());
        json.add("prenom",client.getPrenom());
        json.add("historique",jsonHistorique);
        
     try (PrintWriter out = res.getWriter()) {
           out.print(json.build().toString());
       }
        
        
    }
    
    
}
