/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesfbmoll.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.iesfbmoll.entities.Company;
import org.iesfbmoll.sessionbeans.CompanyFacadeLocal;

/**
 *
 * @author windeveloper
 */
public class SaveJson extends HttpServlet {
    
    @EJB
    private CompanyFacadeLocal companyBean;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Gson gson = new Gson();
        Company c = gson.fromJson( request.getParameter("company"),Company.class);
        int i = companyBean.count();
        c.setId(i+1);
        companyBean.create(c);
        
        List<Company> lc = companyBean.findAll();        
        String jsonObject = gson.toJson(lc);
        
        companyBean.remove(c);                
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        out.print(jsonObject);
        out.flush();

    }    
}
  