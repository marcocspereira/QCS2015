package server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Marco on 4/23/15.
 */
@WebService
public class InsulinDoseCalculatorClass implements InsulinDoseCalculator {

    /* Calculates the number of insulin units needed after one meal */
    @WebMethod
    public int mealtimeInsulinDose(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity) {

        float carbohydrateDose = (carbohydrateAmount / carbohydrateToInsulinRatio) * 50/personalSensitivity ;
        float highBloodSugarDose = (preMealBloodSugar - targetBloodSugar) / personalSensitivity;

        return Math.round(carbohydrateDose + highBloodSugarDose);

        /* error */
        //return -1;
    }

    /* Calculates the total number of units of insulin needed between meals */
    @WebMethod
    public int backgroundInsulinDose(int bodyWeight) {

        return (int) Math.round((0.55 * bodyWeight)/2);

        /* error */
        //return -1;
    }

    /* Determines an individual's sensitivity to one unit of insulin */
    @WebMethod
    public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivitySamples, int[] bloodSugarDropSamples) {

        int i, arrayLength = physicalActivitySamples.length;
        float alpha, beta;


        if(physicalActivitySamples.length != bloodSugarDropSamples.length){
            return -1;
        }

        // first pass
        float sumx = 0, sumy = 0, sumx2 = 0;

        for (i = 0; i < arrayLength; i++){
            sumx += physicalActivitySamples[i];
            sumx2 += physicalActivitySamples[i]*physicalActivitySamples[i];
            sumy += bloodSugarDropSamples[i];
        }

        float xbar = sumx/arrayLength;
        float ybar = sumy/arrayLength;

        // second pass: compute summary statistics
        float xxbar = 0, yybar = 0, xybar = 0;
        for (i = 0; i < arrayLength; i++) {
            xxbar += (physicalActivitySamples[i] - xbar) * (physicalActivitySamples[i] - xbar);
            yybar += (bloodSugarDropSamples[i] - ybar) * (bloodSugarDropSamples[i] - ybar);
            xybar += (physicalActivitySamples[i] - xbar) * (bloodSugarDropSamples[i] - ybar);
        }
        beta  = xybar / xxbar;
        alpha = ybar - beta * xbar;

        return (int) (alpha + beta * physicalActivityLevel);



        /* error */
        //return -1;
    }
}
