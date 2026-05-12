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
public class Cartomancier extends Medium  {

    public Cartomancier() {
        
    }
    
    public Cartomancier(String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
    }

    @Override
    public String toString() {
        return "Cartomancier{" + super.toString()+'}';
    }
    
}
