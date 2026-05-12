/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import metier.modele.Medium;
import metier.service.Service;


/**
 *
 * @author egoncalves
 */
public class RecupererListeMediumsAction extends Action {
    @Override
    public void execute(HttpServletRequest req) {
        System.out.println("\nLISTE MEDIUMS ------- ");
        List<Medium> mediums = Service.recupererListeMediums();
        
        System.out.println("mediums " + mediums);
        req.setAttribute("listemediums", mediums);
        

}
    
}
