/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import metier.modele.Client;
import metier.modele.ProfilAstral;

/**
 *
 * @author yguerin
 */
public class ApiInteraction {

    public static ProfilAstral getAPIProfilAstral(Client client) {
        JsonObject result = null;

        try {

            // TODO: adapter l'URL de l'API et la liste des paramètres
            URI requestUri = URI.create(
                    "https://servif.insa-lyon.fr/WebDataGenerator/Astro?service=profil&key=ASTRO-01-M0lGLURBU0ktQVNUUk8tQjAx"
                    + "&prenom=" + URLEncoder.encode(client.getPrenom(), StandardCharsets.UTF_8)
                    + "&date-naissance=" + URLEncoder.encode(client.getDateNaissance().toString(), StandardCharsets.UTF_8)
            );
            System.out.println(requestUri);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder(requestUri).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() == 200) {
                String body = httpResponse.body();
                System.out.println(body);

                result = Json.createReader(new StringReader(body)).readObject();
            } else {
                throw new IOException("HTTP Error Status Code " + httpResponse.statusCode());
            }

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            result = null;
        }
        
        
        ProfilAstral pa = null;
        if(result != null){
            pa = new ProfilAstral();
            pa.setSigneZodiaque(result.getJsonObject("profil").getString("signe-zodiaque"));
            pa.setSigneChinois(result.getJsonObject("profil").getString("signe-chinois"));
            pa.setCouleur(result.getJsonObject("profil").getString("couleur"));
            pa.setAnimalTotem(result.getJsonObject("profil").getString("animal"));
        }
   
        return pa;
    }
    
    public static List<String> getGpsCoordinates(Client client) {
        JsonObject result = null;

        try {

            // TODO: adapter l'URL de l'API et la liste des paramètres
            URI requestUri = URI.create(
                    "https://data.geopf.fr/geocodage/search?autocomplete=0&index=address&limit=1&returntruegeometry=false"
                    + "&q=" + URLEncoder.encode(client.getAdresse(), StandardCharsets.UTF_8)
            );
            System.out.println(requestUri);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder(requestUri).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() == 200) {
                String body = httpResponse.body();
                System.out.println(body);

                result = Json.createReader(new StringReader(body)).readObject();
            } else {
                throw new IOException("HTTP Error Status Code " + httpResponse.statusCode());
            }

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            result = null;
        }
        List<String> liste = null;
        if(result != null){
             liste = new ArrayList<>();
            liste.add(result.getJsonArray("features").getJsonObject(0).getJsonObject("geometry").getJsonArray("coordinates").getJsonNumber(0).toString());
            liste.add(result.getJsonArray("features").getJsonObject(0).getJsonObject("geometry").getJsonArray("coordinates").getJsonNumber(1).toString()); 
        }
        return liste;
    }
    
    
    
    public static List<String> getAPIPrediction(Client client, Integer niveauAmour, Integer niveauSante, Integer niveauTravail) {
        JsonObject result = null;

        try {

            // TODO: adapter l'URL de l'API et la liste des paramètres
            URI requestUri = URI.create(
                    "https://servif.insa-lyon.fr/WebDataGenerator/Astro?service=predictions&key=ASTRO-01-M0lGLURBU0ktQVNUUk8tQjAx"
                    + "&couleur=" + URLEncoder.encode(client.getProfilAstral().getCouleur(), StandardCharsets.UTF_8)
                    + "&animal=" + URLEncoder.encode(client.getProfilAstral().getAnimalTotem(), StandardCharsets.UTF_8)
                    + "&niveau-amour=" + URLEncoder.encode(niveauAmour.toString(), StandardCharsets.UTF_8)
                    + "&niveau-sante=" + URLEncoder.encode(niveauSante.toString(), StandardCharsets.UTF_8)
                    + "&niveau-travail=" + URLEncoder.encode(niveauTravail.toString(), StandardCharsets.UTF_8)
            );
            System.out.println(requestUri);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder(requestUri).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() == 200) {
                String body = httpResponse.body();
                System.out.println(body);

                result = Json.createReader(new StringReader(body)).readObject();
            } else {
                throw new IOException("HTTP Error Status Code " + httpResponse.statusCode());
            }

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            result = null;
        }
        
        List <String> resultat = null;
        if(result!=null){
            resultat = new ArrayList<>();
            resultat.add(result.getString("prediction-amour"));
            resultat.add(result.getString("prediction-sante"));
            resultat.add(result.getString("prediction-travail"));
        }
        
   
        return resultat;
    }
}
