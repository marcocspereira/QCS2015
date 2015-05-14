package servlets;

import javax.servlet.RequestDispatcher;
import java.io.IOException;


/**
 * Created by Marco on 5/7/15.
 */
public class Servlet extends javax.servlet.http.HttpServlet {

    private void standardInsulin(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        int std_tgcm = Integer.parseInt(request.getParameter("std_tgcm"));
        int std_tgcp = Integer.parseInt(request.getParameter("std_tgcp"));
        int std_abs = Integer.parseInt(request.getParameter("std_abs"));
        int std_tbs = Integer.parseInt(request.getParameter("std_tbs"));
        int std_is = Integer.parseInt(request.getParameter("std_is"));

        // passar para o votador
        // receber resposta
        System.out.println(std_tgcm);

    }

    private void personalInsulin(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        int prs_tgcm = Integer.parseInt(request.getParameter("prs_tgcm"));
        int prs_tgcp = Integer.parseInt(request.getParameter("prs_tgcp"));
        int prs_abs = Integer.parseInt(request.getParameter("prs_abs"));
        int prs_tbs = Integer.parseInt(request.getParameter("prs_tbs"));
        int prs_is = Integer.parseInt(request.getParameter("prs_is"));

        // passar para o votador
        // receber resposta

    }

    private void backgroundInsulin(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        int bg_kg = Integer.parseInt(request.getParameter("bg_kg"));
        System.out.println(bg_kg);
        // passar para o votador
        // receber resposta

    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        /*
        RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
        disp.forward(request, response);
        */

        if(request.getParameter("submit_std") != null){
            standardInsulin(request, response);
        }
        else if(request.getParameter("submit_prs") != null){
            personalInsulin(request, response);
        }
        else if(request.getParameter("submit_bg") != null){
            backgroundInsulin(request, response);
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // redirect to doPost, just in case
        doPost(request, response);
    }
}
