/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import javax.persistence.Embeddable;

/**
 *
 * @author yguerin
 */
@Embeddable
public class ProfilAstral {
    private String signeZodiaque;
    private String signeChinois;
    private String couleur;
    private String animalTotem;

    public ProfilAstral() {
    }

    public ProfilAstral(String signeZodiaque, String signeChinois, String couleur, String animalTotem) {
        this.signeZodiaque = signeZodiaque;
        this.signeChinois = signeChinois;
        this.couleur = couleur;
        this.animalTotem = animalTotem;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    @Override
    public String toString() {
        return "ProfilAstral{" + "signeZodiaque=" + signeZodiaque + ", signeChinois=" + signeChinois + ", couleur=" + couleur + ", animalTotem=" + animalTotem + '}';
    }
    
}
