package server;

import javax.xml.ws.Endpoint;

/**
 * Created by Marco on 4/23/15.
 */
public class InsulinDoseCalculatorEndpoint {
    public static void main(String[] args) {
        InsulinDoseCalculatorClass insulin = new InsulinDoseCalculatorClass();

        Endpoint endpoint = Endpoint.publish("http://localhost:8081/qcs", insulin);
    }
}
