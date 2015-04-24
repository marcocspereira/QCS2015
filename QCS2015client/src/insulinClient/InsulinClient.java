package insulinClient;/**
 * Created by Marco on 4/23/15.
 */
public class InsulinClient {
  public static void main(String[] argv) {
    InsulinDoseCalculatorClass service = new InsulinDoseCalculatorClassService().getInsulinDoseCalculatorClassPort();

    //invoke business method
    // service.metodo_a_chamar
    int result = service.backgroundInsulinDose(79);  // input: 79 --> expected output: 22
    //System.out.println(result);

    System.out.println(service.mealtimeInsulinDose(60, 12, 200, 100, 25));
    System.out.println(service.mealtimeInsulinDose(95, 10, 100, 120, 50));
    System.out.println(service.mealtimeInsulinDose(120, 14, 170, 100, 60));
  }
}
