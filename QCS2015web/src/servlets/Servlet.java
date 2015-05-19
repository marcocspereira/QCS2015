package servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import results.TechnicalDetail;
import voter.Voter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
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

    private final int WILD_EXCEPTION_CODE = -4;
    private final int TIMEOUT_CODE = -2;
    private final int INVALID_INPUT_CODE = -3;


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

        // validação de dados de input
        if( !(std_tgcm>=60 && std_tgcm<=120) || !(std_tgcp>=10 && std_tgcp<=15)
        || !(std_abs>=120 && std_abs<=250) || !(std_tbs>=80 && std_tbs<=120) || !(std_is>=15 && std_is<=100) ){
            System.out.println("A wild Input Exception appeared");
            TechnicalDetail td = new TechnicalDetail();
            td.setMajority_result(INVALID_INPUT_CODE);
            td.setNum_webservices(0);
            return td;
        }

        // Technical detail
        return voter.mealtimeInsulin(std_tgcm, std_tgcp, std_abs, std_tbs, std_is);


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

        // validação de dados de input
        if( !(prs_tgcm>=60 && prs_tgcm<=120) || !(prs_tgcp>=10 && prs_tgcp<=15)
                || !(prs_abs>=120 && prs_abs<=250) || !(prs_tbs>=80 && prs_tbs<=120) || !(prs_pa>=0 && prs_pa<=10)
                || (sample_pal_int.size() != sample_dbs_int.size())){
            System.out.println("A wild Input Exception appeared");
            TechnicalDetail td = new TechnicalDetail();
            td.setMajority_result(INVALID_INPUT_CODE);
            td.setNum_webservices(0);
            return td;
        }

        // Technical detail
        return voter.personalSensitivityToInsulin(prs_tgcm, prs_tgcp, prs_tbs, prs_tbs, prs_pa, sample_pal_int, sample_dbs_int);


    }

    private TechnicalDetail backgroundInsulin(Voter voter, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // Weight in kilograms
        int bg_kg = Integer.parseInt(request.getParameter("bg_kg"));

        // validação de dados de input
        if( !(bg_kg>=40 && bg_kg<=130) ){
            System.out.println("A wild Input Exception appeared");
            TechnicalDetail td = new TechnicalDetail();
            td.setMajority_result(INVALID_INPUT_CODE);
            td.setNum_webservices(0);
            return td;
        }

        // Technical detail
        return voter.backgroundInsulin(bg_kg);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);

    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        PrintWriter out = response.getWriter();

        TechnicalDetail td = new TechnicalDetail(); td.setMajority_result(-100);
        Gson gson = new Gson();
        String json;

        long start_time = System.currentTimeMillis();

        // instanciar objeto do tipo voter
        Voter voter = new Voter();

        if(!request.getParameterMap().isEmpty()){

            if(request.getParameter("FLAG") != null){

                String op = request.getParameter("FLAG");
                System.out.println("Operation: " + op);

                boolean timeout = true;
                // System.out.println("start: " + start_time);
                while( (start_time+4000) > System.currentTimeMillis() ) {
                    // System.out.println("now: " + System.currentTimeMillis());
                    try {
                        if (op.equals("standard")) {
                            td = standardInsulin(voter, request, response);
                        } else if (op.equals("personal")) {
                            td = personalInsulin(voter, request, response);
                        } else if (op.equals("background")) {
                            td = backgroundInsulin(voter, request, response);
                        }

                        if (td.getMajority_result() >= 0 || td.getMajority_result() == INVALID_INPUT_CODE) {
                            timeout = false;
                            break;
                        }

                    } catch (Exception e){
                        e.printStackTrace();
                        System.out.println("A wild Exception appeared");
                        td.setMajority_result(WILD_EXCEPTION_CODE);
                        timeout = false;
                        break;
                    }
                }

                if(timeout){
                    System.out.println("A wild Time Out appeared");
                    td.setMajority_result(TIMEOUT_CODE);
                }

                json = gson.toJson(td);
                out.write(json);

                out.close();

            }
        }
    }
}
