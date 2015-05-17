package voter;


import results.TechnicalDetail;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by cmfsnts on 15/05/2015.
 */
public class Voter {

    private final int TIMEOUT_CODE = -2;

    //Urls válidos e funçoes
    private final String[] urls = {"http://liis-lab.dei.uc.pt:8080/Server?wsdl",
            "http://qcs07.dei.uc.pt:8080/insulin?wsdl",
            "http://qcs06.dei.uc.pt:8080/insulin?wsdl",
            "http://qcs05.dei.uc.pt:8080/insulin?wsdl",
            "http://qcs12.dei.uc.pt:8080/insulin?wsdl"};
    private final int numberThreads = 5;                        // number of webservices
    // ArrayList<Future<Integer>> lista = new ArrayList<Future<Integer>>();    //  valores (inteiros) devolvidos por cada web service
    ArrayList<Integer> lista = new ArrayList<Integer>();    //  valores (inteiros) devolvidos por cada web service
    ArrayList<Future<Integer>> threads = new ArrayList<Future<Integer>>();     //  guarda as threads criadas para ligar aos N web services

    public TechnicalDetail backgroundInsulin(int weight){

        ExecutorService pool = Executors.newFixedThreadPool(numberThreads);

        // criar as threads
        for(int i=0;i<numberThreads;i++) {
            try {
                BackgroundThread task = new BackgroundThread(urls[i], weight);
                Future<Integer> future = pool.submit(task);
                System.out.println(future);
                threads.add(future);
                // lista.add(future);
            }
            catch (Exception e){
                e.getMessage();
            }
        }

        //Timer for each thread
        for(int i=0;i<numberThreads;i++) {
            Future<Integer> future = threads.get(i);
            try {
                System.out.println("Started...");
                try {
                    // 0.7 segundos por thread
                    future.get(800, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.getMessage();
                } catch (ExecutionException e) {
                    e.getMessage();
                }
                try {
                    lista.add(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.getMessage();
                }
                System.out.println("Finished!");
            } catch (TimeoutException e) {
                lista.add(TIMEOUT_CODE);
                System.out.println("Terminated!");
            }
        }

        pool.shutdownNow();

        for(int i=0;i<5;i++){
            System.out.println("Index " + i + " " + lista.get(i));
        }

        return chooser();

    }

    public TechnicalDetail personalSensitivityToInsulin(int carbohydrateAmount,
                                                        int carbohydrateToInsulinRatio,
                                                        int preMealBloodSugar,
                                                        int targetBloodSugarint,
                                                        int physicalActivityLevel,
                                                        List<Integer> physicalActivitySamples,
                                                        List<Integer> bloodSugarDropSamples){

        int individualSensitivity;

        ExecutorService pool = Executors.newFixedThreadPool(numberThreads);

        // criar as threads
        for(int i=0;i<numberThreads;i++) {
            try {
                PersonalThread personalTask = new PersonalThread(urls[i], physicalActivityLevel, physicalActivitySamples, bloodSugarDropSamples);
                Future<Integer> future = pool.submit(personalTask);
                threads.add(future);
                // lista.add(future);
            }
            catch(Exception e){
                e.getCause();
            }
        }

        //Timer for each thread
        for(int i=0;i<numberThreads;i++) {
            Future<Integer> future = threads.get(i);
            try {
                System.out.println("Started...");
                try {
                    // 0.7 segundo por thread
                    future.get(700, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.getMessage();
                } catch (ExecutionException e) {
                    e.getMessage();
                }
                try {
                    lista.add(future.get());
                } catch (InterruptedException e) {
                    e.getMessage();
                } catch (ExecutionException e) {
                    e.getMessage();
                }
                System.out.println("Finished!");
            } catch (TimeoutException e) {
                lista.add(TIMEOUT_CODE);
                System.out.println("Terminated!");
            }

        }

        pool.shutdownNow();

        individualSensitivity = chooser().getMajority_result();

        return mealtimeInsulin(carbohydrateAmount, carbohydrateToInsulinRatio, preMealBloodSugar, targetBloodSugarint, individualSensitivity);
    }


    public TechnicalDetail mealtimeInsulin(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity){

        ExecutorService pool = Executors.newFixedThreadPool(numberThreads);

        for(int i=0;i<numberThreads;i++){

            MealTimeThread task = new MealTimeThread(urls[i],carbohydrateAmount, carbohydrateToInsulinRatio, preMealBloodSugar, targetBloodSugar, personalSensitivity);
            Future<Integer> future = pool.submit(task);
            try {
                //System.out.println("Started..");
                try {
                    future.get(800, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.getMessage();
                } catch (ExecutionException e) {
                    e.getMessage();
                }
                try {
                    lista.add(future.get());
                } catch (InterruptedException e) {
                    e.getMessage();
                } catch (ExecutionException e) {
                    e.getMessage();
                }
                System.out.println("Finished!");
            } catch (TimeoutException e) {
                lista.add(TIMEOUT_CODE);
                System.out.println("Terminated!");
            }

        }

        pool.shutdownNow();

        return chooser();

    }

    // escolhe a maioria (em construcao)
    private TechnicalDetail chooser() {

        TechnicalDetail td = new TechnicalDetail();
        ArrayList<Integer> temp = new ArrayList<Integer>();

        //check for voters/2 + 1 for majority
        int chosen_val = 0;
        int count = 0;
        int location = 0;

        int array_ocorrencia[] = new int[5];

        Arrays.fill(array_ocorrencia, -2);

        td.setNum_webservices(lista.size());

        for(int i=0;i<lista.size();i++){
            temp.add(lista.get(i));
        }

        td.setResults(temp);

        Set<Integer> uniques = new HashSet<Integer>(temp);
        ArrayList<Integer> uniquesArray = new ArrayList<>(uniques);

        List<Integer> occurences = Arrays.asList(new Integer[uniquesArray.size()]);
        Collections.fill(occurences, 0);

        for (int i = 0; i < temp.size(); i++)
        {
            for (int j = 0; j < uniquesArray.size(); j++)
            {
                if (temp.get(i) == uniquesArray.get( j ) || temp.get(i)-1 == uniquesArray.get( j ) || temp.get(i)+1 == uniquesArray.get( j ))
                {
                    occurences.set(j, occurences.get( j )+1);
//	                if (occurences.get( j ) >= 3) System.out.println(uniquesArray.get( j ));
                }
            }
        }
        System.out.println(temp);
        System.out.println(uniquesArray);
        System.out.println(Arrays.toString(occurences.toArray()));

        int maxValue = 0;
        int maxIndex = 0;

        for (int counter = 1; counter < occurences.size(); counter++) {

            if (occurences.get(counter) > maxValue){
                maxValue = occurences.get(counter);
                maxIndex = counter;
            }
        }

        System.out.println(uniquesArray.get(maxIndex));

        td.setMajority_result(uniquesArray.get(maxIndex));

        lista.clear();

        return td;

    }
}


