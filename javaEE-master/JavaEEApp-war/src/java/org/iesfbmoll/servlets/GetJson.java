/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesfbmoll.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.iesfbmoll.sessionbeans.CompanyFacadeLocal;
import com.google.gson.Gson;
import java.util.List;
import org.iesfbmoll.entities.Company;

/**
 *
 * @author windeveloper
 */
public class GetJson extends HttpServlet {

    @EJB
    private CompanyFacadeLocal companyBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        String jsonObject = gson.toJson(companyBean.findAll());
                
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        out.print(jsonObject);
        out.flush();
    }
    
}
