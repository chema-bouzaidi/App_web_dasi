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

public class PredictionSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        Boolean succes = (Boolean) req.getAttribute("succes");
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("succes", succes != null && succes);

        if (succes != null && succes) {
            List<String> prediction = (List<String>) req.getAttribute("prediction");
            JsonArrayBuilder jsonPrediction = Json.createArrayBuilder();
            for (String ligne : prediction) {
                jsonPrediction.add(ligne);
            }
            json.add("prediction", jsonPrediction);
        }

        try (PrintWriter out = res.getWriter()) {
            out.print(json.build().toString());
        }
    }
}



