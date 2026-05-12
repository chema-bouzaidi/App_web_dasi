/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.vue;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 *
 * @author cbouzaidi
 */
public class SuccesSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Boolean succes = (Boolean) req.getAttribute("succes");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("succes", succes != null && succes);

        try (PrintWriter out = res.getWriter()) {
            out.print(json.build().toString());
        }
    }

}
