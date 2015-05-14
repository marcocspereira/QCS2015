package server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Marco on 4/23/15.
 */
@WebService
public class InsulinDoseCalculator implements InsulinDoseCalculatorInterface {

    /** TODO
        - exception handling
        - errors;
     */

    /* Calculates the number of insulin units needed after one meal */
    @WebMethod
    public int mealtimeInsulinDose(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity) {

        try {
            // special case
            if (targetBloodSugar > preMealBloodSugar) {
                return 0;
            }

            float carbohydrateDose = (float) carbohydrateAmount / carbohydrateToInsulinRatio / personalSensitivity * 50;
            float highBloodSugarDose = (preMealBloodSugar - targetBloodSugar) / personalSensitivity;

            return (int) (carbohydrateDose + highBloodSugarDose);
        }
        catch(Exception e){
            System.err.println("Mealtime Insulin Dose error:" + e.getMessage());
            return -1;
        }
    }

    /* Calculates the total number of units of insulin needed between meals */
    @WebMethod
    public int backgroundInsulinDose(int bodyWeight) {

        try {
            return (int) Math.round((0.55 * bodyWeight) / 2);
        }
        catch (Exception e){
            System.err.println("Background Insulin Dose error:" + e.getMessage());
            return -1;
        }
    }

    /* Determines an individual's sensitivity to one unit of insulin */
    /* base: http://algs4.cs.princeton.edu/14analysis/LinearRegression.java.html */
    @WebMethod
    public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivitySamples, int[] bloodSugarDropSamples) {

        int i, arrayLength = physicalActivitySamples.length;
        float alpha, beta;

        try {
            if (physicalActivitySamples.length != bloodSugarDropSamples.length) {
                return -1;
            }

            // first pass
            float sumx = 0, sumy = 0;

            for (i = 0; i < arrayLength; i++) {
                sumx += physicalActivitySamples[i];
                sumy += bloodSugarDropSamples[i];
            }

            float xbar = sumx / arrayLength;
            float ybar = sumy / arrayLength;

            // second pass: compute summary statistics
            float xxbar = 0, yybar = 0, xybar = 0;
            for (i = 0; i < arrayLength; i++) {
                xxbar += (physicalActivitySamples[i] - xbar) * (physicalActivitySamples[i] - xbar);
                xybar += (physicalActivitySamples[i] - xbar) * (bloodSugarDropSamples[i] - ybar);
            }
            beta = xybar / xxbar;
            alpha = ybar - beta * xbar;

            return Math.round(alpha + beta * physicalActivityLevel);
        }
        catch(Exception e){
            System.err.println("Personal Sensitivity To Insulin error:" + e.getMessage());
            return -1;
        }

    }
}
