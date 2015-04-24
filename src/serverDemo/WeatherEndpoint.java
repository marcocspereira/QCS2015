package serverDemo;

import serverDemo.Weather;
import javax.xml.ws.Endpoint;

public class WeatherEndpoint {
	public static void main(String[] args) {
		Weather weather = new Weather();
		Endpoint endpoint = Endpoint.publish("http://localhost:8081/weather", weather);
	}
}
