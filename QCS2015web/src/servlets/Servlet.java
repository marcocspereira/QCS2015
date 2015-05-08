package servlets;

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
    }

    private void personalInsulin(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    private void backgroundInsulin(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        if(!request.getParameter("submit_std").isEmpty()){
            standardInsulin(request, response);
        }
        else if(!request.getParameter("submit_prs").isEmpty()){

        }
        else if(!request.getParameter("submit_bg").isEmpty()){

        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // redirect to doPost, just in case
        doPost(request, response);
    }
}
