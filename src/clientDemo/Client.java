package clientDemo;

import java.util.*;

class Client {
	public static void main(String[] args) {
		WeatherService service = new WeatherService();
		Weather proxy = service.getWeatherPort();
		String forecast = proxy.forecast("Budapest");
		System.out.println("Weather in Budapest: " + forecast);
	}
	
	/**
	 * Instead of passing an int[] to a web service, pass a List<Integer>.
	 * <p>
	 * This method converts an array of int to a List of Integer. When a web
	 * service publishes a method that receives an int[] as parameter, the type
	 * gets converted to a "sequence" in XML, and the client should therefore
	 * submit, as parameter, a List<Integer> instead of an int[]. The web
	 * service will still receive an int[] on the server side.
	 *
	 * @param array the array of int, to be converted to List<Integer>
	 * @return a list containing the same numbers as the array
	 */
	static List<Integer> arrayToList(int[] array) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i : array)
			list.add(i);
		return list;
	}
}
