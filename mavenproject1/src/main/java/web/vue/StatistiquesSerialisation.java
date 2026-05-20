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
import metier.modele.Employe;
import metier.modele.Medium;

public class StatistiquesSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("\nStatistiquesSerialisation -------");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        JsonObjectBuilder json = Json.createObjectBuilder();

        List<Medium> top5Mediums = (List<Medium>) req.getAttribute("top5Mediums");
        JsonArrayBuilder jsonMediums = Json.createArrayBuilder();
        if (top5Mediums != null) {
            for (Medium m : top5Mediums) {
                JsonObjectBuilder o = Json.createObjectBuilder();
                o.add("denomination", m.getDenomination());
                o.add("nbConsultations", m.getNbConsultations() != null ? m.getNbConsultations() : 0);
                jsonMediums.add(o);
            }
        }
        json.add("top5Mediums", jsonMediums);

        List<Client> top5Clients = (List<Client>) req.getAttribute("top5Clients");
        JsonArrayBuilder jsonClients = Json.createArrayBuilder();
        if (top5Clients != null) {
            for (Client c : top5Clients) {
                JsonObjectBuilder o = Json.createObjectBuilder();
                o.add("prenom", c.getPrenom());
                o.add("nom", c.getNom());
                o.add("nbConsultations", c.getNbConsultations() != null ? c.getNbConsultations() : 0);
                jsonClients.add(o);
            }
        }
        json.add("top5Clients", jsonClients);

        List<Employe> topEmployes = (List<Employe>) req.getAttribute("topEmployes");
        JsonArrayBuilder jsonEmployes = Json.createArrayBuilder();
        int totalConsultations = 0;
        int nbEmployes = 0;
        if (topEmployes != null) {
            nbEmployes = topEmployes.size();
            for (Employe e : topEmployes) {
                int nb = e.getNbConsultations() != null ? e.getNbConsultations() : 0;
                totalConsultations += nb;
                JsonObjectBuilder o = Json.createObjectBuilder();
                o.add("prenom", e.getPrenom());
                o.add("nom", e.getNom());
                o.add("nbConsultations", nb);
                jsonEmployes.add(o);
            }
        }
        json.add("topEmployes", jsonEmployes);

        double moyenne = 0.0;
        if (nbEmployes > 0) {
            moyenne = (double) totalConsultations / nbEmployes;
        }
        double moyenneArrondie = Math.round(moyenne * 10.0) / 10.0;

        json.add("moyenneConsultationsParEmploye", moyenneArrondie);

        try (PrintWriter out = res.getWriter()) {
            out.print(json.build().toString());
        }
    }
}
