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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import metier.modele.Client;
import metier.modele.Employe;

/**
 *
 * @author cbouzaidi
 */
public class AuthentifierSerialisation extends Serialisation{
    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        String statut = (String) session.getAttribute("statut");
        Integer id = (Integer) session.getAttribute("id");
        System.out.println("\nserialisation Authentification ------- " + statut );
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        JsonObjectBuilder json = Json.createObjectBuilder();
        if (statut != null) {
            json.add("statut", statut);
            json.add("id", id);}
        
        else {
            json.addNull("statut"); } //echec de co

        
        try (PrintWriter out = res.getWriter()) {
            out.print(json.build().toString());
        }
    }

}
        
        
    
    

