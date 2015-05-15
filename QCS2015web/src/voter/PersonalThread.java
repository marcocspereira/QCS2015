package voter;

import webservicegen.InsulinDoseCalculator;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by cmfsnts on 15/05/2015.
 */
public class PersonalThread implements Callable<Integer> {

    private String address;
    private final String nameSpace= "http://server/";
    private final String serviceName= "InsulinDoseCalculatorService";

    private int physicalActivityLevel;
    private List<Integer> physicalActivitySamples;
    private List<Integer> bloodSugarDropSamples;


    public PersonalThread(String address, int physicalActivityLevel, List<Integer> physicalActivitySamples, List<Integer> bloodSugarDropSamples){

        this.address = address;
        this.physicalActivitySamples = physicalActivitySamples;
        this.physicalActivityLevel = physicalActivityLevel;
        this.bloodSugarDropSamples = bloodSugarDropSamples;

    }


    @Override
    public Integer call() throws Exception {

        int result = 0;

        URL url = null;
        try {
            url = new URL (address);
        } catch (MalformedURLException e) {
            System.out.println("Erro no URL");
        }
        QName qname = new QName(nameSpace, serviceName);
        Service service = Service.create(url, qname);

        InsulinDoseCalculator proxy = service.getPort(InsulinDoseCalculator.class);

        result = proxy.personalSensitivityToInsulin(physicalActivityLevel, physicalActivitySamples, bloodSugarDropSamples);

        return result;

    }
}
