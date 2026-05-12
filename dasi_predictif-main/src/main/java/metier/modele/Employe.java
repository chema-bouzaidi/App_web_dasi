/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author yguerin
 */
@Entity
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nom;
    private String prenom;
    private String genre;
    private String mail;
    private String motDePasse;
    private String telephone;
    private Boolean estDisponible;
    private Boolean estPret;
    private Integer nbConsultations;

    public Employe(String nom, String prenom, String mail, String genre, String motDePasse, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.genre = genre;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.estDisponible = true;
        this.estPret = false;
        this.nbConsultations = 0;
    }

    public Integer getNbConsultations() {
        return nbConsultations;
    }

    public void setNbConsultations(Integer nbConsultations) {
        this.nbConsultations = nbConsultations;
    }

    public void augmenterNbConsultations() {
        this.nbConsultations++;
    }

    public Boolean getEstPret() {
        return estPret;
    }

    public void setEstPret(Boolean estPret) {
        this.estPret = estPret;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(Boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    @Override
    public String toString() {
        return "Employe{" + "nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", genre=" + genre + ", motDePasse=" + motDePasse + ", telephone=" + telephone + ", id=" + id + ", estDisponible=" + estDisponible + ", estPret=" + estPret + ", nbConsultations=" + nbConsultations +  '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employe other = (Employe) obj;
        return Objects.equals(this.id, other.id);
    }

    public Employe() {
    }

}
