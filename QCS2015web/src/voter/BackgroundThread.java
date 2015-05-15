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
public class BackgroundThread implements Callable<Integer>{

    private int weight;
    private String address;
    private final String nameSpace= "http://server/";
    private final String serviceName= "InsulinDoseCalculatorService";





    public BackgroundThread(String address, int weight){
        this.address = address;
        this.weight = weight;

    }

    @Override
    public Integer call() throws Exception {

        //System.out.println(address);
        //sleep(200000);

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

        result = proxy.backgroundInsulinDose(weight);

        System.out.println("Adress = " + address + " result = " + result);
        return result;

    }
}
