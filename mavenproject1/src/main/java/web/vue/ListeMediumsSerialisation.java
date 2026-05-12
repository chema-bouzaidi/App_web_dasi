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
import metier.modele.Astrologue;
import metier.modele.Cartomancier;
import metier.modele.Medium;
import metier.modele.Spirite;


/**
 *
 * @author egoncalves
 */
public class ListeMediumsSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {

        List<Medium> liste_mediums = (List<Medium>) req.getAttribute("listemediums");
        System.out.println("\nserialisation LISTE mediums ------- " + liste_mediums);

        JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
        JsonArrayBuilder jsonListeMediums = Json.createArrayBuilder();
        for (Medium medium : liste_mediums) {
            JsonObjectBuilder jsonMediums = Json.createObjectBuilder();
            String type = "" ;
            if (medium instanceof Astrologue){
                type = "Astrologue";
            }
            else if (medium instanceof Cartomancier){
                type = "Cartomancier";
            }
            else if (medium instanceof Spirite){
                type = "Spirite";
            }
            jsonMediums.add("id", medium.getId());
            jsonMediums.add("denomination", medium.getDenomination());
            jsonMediums.add("presentation", medium.getPresentation());
            jsonMediums.add("type", type);
            jsonListeMediums.add(jsonMediums);
        }
        jsonContainer.add("mediums", jsonListeMediums);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try (PrintWriter out = res.getWriter()) {
            out.print(jsonContainer.build().toString());
        }
    }

}
