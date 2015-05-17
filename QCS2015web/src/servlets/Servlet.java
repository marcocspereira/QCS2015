package servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import results.TechnicalDetail;
import voter.Voter;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Marco on 5/7/15.
 */
@WebServlet ("/servlets/Servlet")
public class Servlet extends javax.servlet.http.HttpServlet {

    private TechnicalDetail standardInsulin(Voter voter, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

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

        // Technical detail
        TechnicalDetail t = new TechnicalDetail();

        t = voter.mealtimeInsulin(std_tgcm, std_tgcp, std_abs, std_tbs, std_is);

        return t;

    }

    private TechnicalDetail personalInsulin(Voter voter, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        Type type = new TypeToken<List<String>>(){}.getType();

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
        List<String> sample_pal = new Gson().fromJson(request.getParameter("sample_pal"), type);
        // Samples of drops in blood sugar from one unit of insulin in that day
        List<String> sample_dbs = new Gson().fromJson(request.getParameter("sample_dbs"), type);

        int size_of_samples = sample_pal.size();
        List<Integer> sample_pal_int = new ArrayList<Integer>();
        List<Integer> sample_dbs_int = new ArrayList<Integer>();
        int i;
        for(i=0; i<size_of_samples; i++){
            sample_pal_int.add(Integer.parseInt(sample_pal.get(i)));
            sample_dbs_int.add(Integer.parseInt(sample_dbs.get(i)));
        }

        // Technical detail
        TechnicalDetail t = new TechnicalDetail();

        t = voter.personalSensitivityToInsulin(prs_tgcm, prs_tgcp, prs_tbs, prs_tbs, prs_pa, sample_pal_int, sample_dbs_int);

        return t;

    }

    private TechnicalDetail backgroundInsulin(Voter voter, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // Weight in kilograms
        int bg_kg = Integer.parseInt(request.getParameter("bg_kg"));

        // Technical detail
        TechnicalDetail t = new TechnicalDetail();

        t = voter.backgroundInsulin(bg_kg);

        System.out.println("majority:" + t.getMajority_result());

        return t;

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);

    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        PrintWriter out = response.getWriter();

        TechnicalDetail td = null;
        Gson gson = new Gson();
        String json;

        long start_time = System.currentTimeMillis();

        // instanciar objeto do tipo voter
        Voter voter = new Voter();

        if(!request.getParameterMap().isEmpty()){

            if(request.getParameter("FLAG") != null){

                String op = request.getParameter("FLAG");
                System.out.println("Operation: " + op);

                System.out.println("start: " + start_time);
                while( (start_time+4000) > System.currentTimeMillis() ) {
                    System.out.println("now: " + System.currentTimeMillis());
                    try {
                        if (op.equals("standard")) {
                            td = standardInsulin(voter, request, response);
                        } else if (op.equals("personal")) {
                            td = personalInsulin(voter, request, response);
                        } else if (op.equals("background")) {
                            td = backgroundInsulin(voter, request, response);
                        }

                        if (td.getMajority_result()>=0){
                            break;
                        }

                    }catch (Exception e){
                        System.out.println("Unavailable Web Services ");
                        td.setMajority_result(-2);
                        break;
                    }
                }

                json = gson.toJson(td);
                out.write(json);

                out.close();

            }
        }
    }
}
