package servlets;

import com.google.gson.Gson;
import results.TechnicalDetail;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Marco on 5/7/15.
 */
@WebServlet ("/servlets/Servlet")
public class Servlet extends javax.servlet.http.HttpServlet {

    private TechnicalDetail standardInsulin(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // Total grams of carbohydrates in the meal
        int std_tgcm = Integer.parseInt(request.getParameter("std_tgcm"));
        // Total grams of carbohydrates processed by 1 unit of rapid acting insulin
        int std_tgcp = Integer.parseInt(request.getParameter("std_tgcp"));
        // Actual blood sugar level measured before the meal
        int std_abs = Integer.parseInt(request.getParameter("std_abs"));
        // Target blood sugar before the meal
        int std_tbs = Integer.parseInt(request.getParameter("std_tbs"));
        // Individual sensitivity
        int std_is = Integer.parseInt(request.getParameter("std_is"));

        // todo enviar para o voter

        // testing technical detail * ELIMINAR *
        List<Integer> l = new ArrayList<Integer>();
        TechnicalDetail t = new TechnicalDetail();
        t.setNum_webservices(3);
        l.add(22);
        l.add(22);
        l.add(23);
        t.setResults(l);
        t.setMajority_result(22);

        // todo receber o technical detail do voter com os resultados para depois retornar para o cliente web

        return t;

    }

    private TechnicalDetail personalInsulin(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // Total grams of carbohydrates in the meal
        int prs_tgcm = Integer.parseInt(request.getParameter("prs_tgcm"));
        // Total grams of carbohydrates processed by 1 unit of rapid acting insulin
        int prs_tgcp = Integer.parseInt(request.getParameter("prs_tgcp"));
        // Actual blood sugar level measured before the meal
        int prs_abs = Integer.parseInt(request.getParameter("prs_abs"));
        // Target blood sugar before the meal
        int prs_tbs = Integer.parseInt(request.getParameter("prs_tbs"));
        // Today’s physical activity level
        int prs_pa = Integer.parseInt(request.getParameter("prs_is"));
        // Samples of physical activity level in a given day
        List<String> sample_pal = new Gson().fromJson(request.getParameter("sample_pal"), List.class);
        // Samples of drops in blood sugar from one unit of insulin in that day
        List<String> sample_dbs = new Gson().fromJson(request.getParameter("sample_dbs"), List.class);

        int size_of_samples = sample_pal.size();
        int sample_pal_int[] = new int[size_of_samples];
        int sample_dbs_int[] = new int[size_of_samples];
        int i;
        for(i=0; i<size_of_samples; i++){
            sample_pal_int[i] = Integer.parseInt( sample_pal.get(i));
            sample_dbs_int[i] = Integer.parseInt(sample_dbs.get(i));
            System.out.println("pal "+i+": "+ sample_pal_int[i]);
            System.out.println("dbs "+i+": " + sample_dbs_int[i]);
        }

        // todo passar para o votador
        // todo receber resposta


        // testing technical detail * ELIMINAR *
        List<Integer> l = new ArrayList<Integer>();
        TechnicalDetail t = new TechnicalDetail();
        t.setNum_webservices(3);
        l.add(22);
        l.add(22);
        l.add(23);
        t.setResults(l);
        t.setMajority_result(22);

        // todo receber o technical detail do voter com os resultados para depois retornar para o cliente web
        return t;

    }

    private TechnicalDetail backgroundInsulin(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // Weight in kilograms
        int bg_kg = Integer.parseInt(request.getParameter("bg_kg"));

        // todo passar para o votador
        // todo receber resposta

        // testing technical detail * ELIMINAR *
        List<Integer> l = new ArrayList<Integer>();
        TechnicalDetail t = new TechnicalDetail();
        t.setNum_webservices(3);
        l.add(22);
        l.add(22);
        l.add(23);
        t.setResults(l);
        t.setMajority_result(22);

        // todo receber o technical detail do voter com os resultados para depois retornar para o cliente web

        return t;

    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        /*
        RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
        disp.forward(request, response);
        */

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // redirect to doPost, just in case
        // doPost(request, response);

        PrintWriter out = response.getWriter();

        TechnicalDetail td = null;
        Gson gson = new Gson();
        String json;

        if(!request.getParameterMap().isEmpty()){

            if(request.getParameter("FLAG") != null){

                String op = request.getParameter("FLAG");
                System.out.println("Operation:" + op);

                if(op.equals("standard")){
                    td = standardInsulin(request, response);
                }
                else if(op.equals("personal")){
                    td = personalInsulin(request, response);
                }
                else if(op.equals("background")){
                    td = backgroundInsulin(request, response);
                }

                json = gson.toJson(td);
                out.write(json);

                out.close();

            }
        }
    }
}
