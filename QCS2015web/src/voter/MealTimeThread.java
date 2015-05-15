package voter;

import webservicegen.InsulinDoseCalculator;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by cmfsnts on 15/05/2015.
 */
public class MealTimeThread implements Callable<Integer> {

    private String address;
    private final String nameSpace= "http://server/";
    private final String serviceName= "InsulinDoseCalculatorService";

    private int carbohydrateAmount;
    private int carbohydrateToInsulinRatio;
    private int preMealBloodSugar;
    private int targetBloodSugar;
    private int personalSensitivity;

    public MealTimeThread(String address,int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity){
        this.address = address;
        this.carbohydrateAmount = carbohydrateAmount;
        this.carbohydrateToInsulinRatio = carbohydrateToInsulinRatio;
        this.preMealBloodSugar = preMealBloodSugar;
        this.targetBloodSugar = targetBloodSugar;
        this.personalSensitivity = personalSensitivity;

    }

    @Override
    public Integer call() throws Exception {
        int result;

        URL url = null;
        try {
            url = new URL (address);
        } catch (MalformedURLException e) {
            System.out.println("Erro no URL");
        }
        QName qname = new QName(nameSpace, serviceName);
        Service service = Service.create(url, qname);

        InsulinDoseCalculator proxy = service.getPort(InsulinDoseCalculator.class);

        result = proxy.mealtimeInsulinDose(carbohydrateAmount,carbohydrateToInsulinRatio,preMealBloodSugar,targetBloodSugar,personalSensitivity);

        return result;

    }
}
