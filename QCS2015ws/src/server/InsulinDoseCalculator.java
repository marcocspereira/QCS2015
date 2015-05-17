package server;

import org.apache.commons.math3.stat.regression.SimpleRegression;

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

            double carbohydrateDose = (double) carbohydrateAmount / (double) carbohydrateToInsulinRatio / (double) personalSensitivity * 50.0;
            double highBloodSugarDose = ((double) preMealBloodSugar - (double) targetBloodSugar) / (double) personalSensitivity;

            return (int) Math.round(carbohydrateDose + highBloodSugarDose);
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
            return (int) Math.round(0.55 * bodyWeight * 0.5);
        }
        catch (Exception e){
            System.err.println("Background Insulin Dose error:" + e.getMessage());
            return -1;
        }
    }

    /* Determines an individual's sensitivity to one unit of insulin */
    /* base: https://commons.apache.org/math/apidocs/org/apache/commons/math3/stat/regression/SimpleRegression.html */
    @WebMethod
    public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivitySamples, int[] bloodSugarDropSamples) {

        try {
            int i, arrayLength = physicalActivitySamples.length;

            if (physicalActivitySamples.length != bloodSugarDropSamples.length)
                return -1;

            SimpleRegression regression = new SimpleRegression();

            for(i=0; i<arrayLength; i++) {
                if(physicalActivitySamples[i] < 0 || physicalActivitySamples[i] > 10)
                    return -1;
                if(bloodSugarDropSamples[i] < 15 || bloodSugarDropSamples[i] > 100)
                    return -1;
                regression.addData((double) physicalActivitySamples[i], (double) bloodSugarDropSamples[i]);
            }

            return (int) Math.round(regression.predict((double) physicalActivityLevel));

        }
        catch(Exception e){
            System.err.println("Personal Sensitivity To Insulin error:" + e.getMessage());
            return -1;
        }

    }
}
