/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author yguerin
 */
@Entity
public class Astrologue extends Medium {
    private String formation;
    private String promotion;

    public Astrologue() {
        
    }
    
    public Astrologue(String denomination, String genre, String presentation, String formation,String promotion ) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }
    

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "Astrologue{" + "formation=" + formation + ", promotion=" + promotion + ", " + super.toString()+'}';
    }
}
