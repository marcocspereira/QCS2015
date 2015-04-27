package insulinClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 4/23/15.
 */

public class InsulinClient {
  public static void main(String[] argv) {
      InsulinDoseCalculatorClass service = new InsulinDoseCalculatorClassService().getInsulinDoseCalculatorClassPort();

      // service.metodo_a_chamar

      System.out.println("\nTest cases");
      System.out.println("\nmethod: backgroundInsulinDose");

      System.out.println(service.backgroundInsulinDose(79));  // 79 --> 22

      System.out.println("\nmethod mealtimeInsulinDose");
      System.out.println(service.mealtimeInsulinDose(60, 12, 200, 100, 25));  // 60, 12, 200, 100, 25   -->  14
      System.out.println(service.mealtimeInsulinDose(95, 10, 100, 120, 50));  // 95, 10, 100, 120, 50   -->  0
      System.out.println(service.mealtimeInsulinDose(120, 14, 170, 100, 60)); // 120, 14, 170, 100, 60  -->  8

      System.out.println("\nmethod personalSensitivityToInsulin");
      List<Integer> a = new ArrayList<>();
      List<Integer> b = new ArrayList<>();
      a.add(0); a.add(10);
      b.add(50); b.add(50);
      System.out.println(service.personalSensitivityToInsulin(5, a, b)); // 5, {0, 10}, {50, 50}   -->  50
      a.clear();
      b.clear();
      a.add(2); a.add(8);
      b.add(32); b.add(83);
      System.out.println(service.personalSensitivityToInsulin(6, a, b));    // 6, {2, 8}, {32, 83}   -->  66
      a.clear();
      b.clear();
      a.add(1); a.add(3); a.add(10);
      b.add(33); b.add(43); b.add(70);
      System.out.println(service.personalSensitivityToInsulin(0, a, b)); // 0, {1, 3, 10}, {33, 43, 70}   -->  30
      a.clear();
      b.clear();
      a.add(1); a.add(6); a.add(8); a.add(9);
      b.add(32); b.add(61); b.add(91); b.add(88);
      System.out.println(service.personalSensitivityToInsulin(4, a, b)); // 4, {1, 6, 8, 9}, {32, 61, 91, 88}   -->  53

  }
}
