/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author cbouzaidi
 */
public class DeconnecterAction extends Action {
    @Override
    public void execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
    }
}