package serverDemo;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class Weather {
	@WebMethod
	public String forecast(String city) {
		if((city.hashCode() & 1) == 0)
			return "Light rain throughout the day.";
		else
			return "Sunshine in the morning and afternoon.";
	}
}
