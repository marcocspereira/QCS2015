package server;

import javax.xml.ws.Endpoint;

/**
 * Created by Marco on 4/23/15.
 */
public class InsulinDoseCalculatorEndpoint {
    public static void main(String[] args) {
        InsulinDoseCalculator insulin = new InsulinDoseCalculator();

        if(args.length!=2){
            System.out.println("usage:<ip> <port>");
            System.exit(0);
        }

        // args[0] = ip     args[1] = port
        Endpoint endpoint = Endpoint.publish("http://" + args[0] +":" + args[1] + "/insulin" , insulin);
    }
}
