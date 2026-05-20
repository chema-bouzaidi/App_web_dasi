/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web.controleur;

import dao.JpaUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.modele.AuthentifierAction;
import web.modele.InscrireClientAction;
import web.modele.ObtenirPredictionAction;
import web.modele.PrendreRdvAction;
import web.modele.RecupClientAction;
import web.modele.RecupConsultationActuelleEmployeAction;
import web.modele.RecupererListeMediumsAction;
import web.modele.SeMettrePretAction;
import web.modele.TerminerConsultationAction;
import web.vue.AuthentifierSerialisation;
import web.vue.ListeMediumsSerialisation;
import web.vue.ClientSerialisation;
import web.vue.ConsultationEmployeSerialisation;
import web.vue.PredictionSerialisation;
import web.vue.SuccesSerialisation;

/**
 *
 * @author egoncalves
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        JpaUtil.creerFabriquePersistance();
    }

    @Override
    public void destroy() {
        JpaUtil.fermerContextePersistance();
        super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String todo = request.getParameter("todo");
        System.out.println("Trace : todo = " + todo);
        switch (todo) {
            case "consulter-liste-mediums": {
                new RecupererListeMediumsAction().execute(request);
                new ListeMediumsSerialisation().appliquer(request, response);
                break;
            }
            case "authentifier": {
                new AuthentifierAction().execute(request);
                new AuthentifierSerialisation().appliquer(request, response);
                break;
            }
            case "inscrire-client": {
                new InscrireClientAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;
            }
            case "acceder-profil-astral": {
                new RecupClientAction().execute(request);
                new ClientSerialisation().appliquer(request,response);
                
            }
            case "historique-consultations": {
                new RecupClientAction().execute(request);
                new ClientSerialisation().appliquer(request,response);
                break;
                
            }
            case "prendre-rdv": {
                new PrendreRdvAction().execute(request);
                new SuccesSerialisation().appliquer(request,response);
                break;
                
            }
            case "ma-consultation": {
                new RecupConsultationActuelleEmployeAction().execute(request);
                new ConsultationEmployeSerialisation().appliquer(request, response);
                break;
            }
            case "se-mettre-pret": {
                new SeMettrePretAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;
            }
            case "obtenir-prediction": {
                new ObtenirPredictionAction().execute(request);
                new PredictionSerialisation().appliquer(request, response);
                break;
            }
            case "terminer-consultation": {
                new TerminerConsultationAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;
            }


            
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
