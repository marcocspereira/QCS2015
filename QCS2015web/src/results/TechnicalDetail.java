package results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 5/14/15.
 */
public class TechnicalDetail {

    private int num_webservices;
    private List<Integer> results = new ArrayList<Integer>();
    private int majority_result;

    public TechnicalDetail(){}

    public TechnicalDetail(int num_webservices, List<Integer> results, int majority_result) {
        this.num_webservices = num_webservices;
        this.results = results;
        this.majority_result = majority_result;
    }

    public int getNum_webservices() {
        return num_webservices;
    }

    public void setNum_webservices(int num_webservices) {
        this.num_webservices = num_webservices;
    }

    public List<Integer> getResults() {
        return results;
    }

    public void setResults(List<Integer> results) {
        this.results = results;
    }

    public int getMajority_result() {
        return majority_result;
    }

    public void setMajority_result(int majority_result) {
        this.majority_result = majority_result;
    }
}
