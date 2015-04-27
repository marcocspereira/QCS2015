
package clientDemo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ForecastResponse_QNAME = new QName("http://server/", "forecastResponse");
    private final static QName _Forecast_QNAME = new QName("http://server/", "forecast");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Forecast }
     * 
     */
    public Forecast createForecast() {
        return new Forecast();
    }

    /**
     * Create an instance of {@link ForecastResponse }
     * 
     */
    public ForecastResponse createForecastResponse() {
        return new ForecastResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ForecastResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "forecastResponse")
    public JAXBElement<ForecastResponse> createForecastResponse(ForecastResponse value) {
        return new JAXBElement<ForecastResponse>(_ForecastResponse_QNAME, ForecastResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Forecast }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server/", name = "forecast")
    public JAXBElement<Forecast> createForecast(Forecast value) {
        return new JAXBElement<Forecast>(_Forecast_QNAME, Forecast.class, null, value);
    }

}
