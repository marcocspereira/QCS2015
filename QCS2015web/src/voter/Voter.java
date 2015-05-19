package voter;


import results.TechnicalDetail;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by cmfsnts on 15/05/2015.
 */
public class Voter {

    private final int NO_MAJORITY_CODE = -1;
    private final int TIMEOUT_CODE = -2;

    // 0.7 segundos por thread
    private final int TIMEOUT = 3000;

    //Urls válidos e funçoes
    private String[] urls = {
            "http://qcs05.dei.uc.pt:8080/insulin?wsdl",
            "http://qcs06.dei.uc.pt:8080/insulin?wsdl",
            "http://qcs07.dei.uc.pt:8080/insulin?wsdl",
            "http://qcs08.dei.uc.pt:8080/InsulinDoseCalculator?wsdl",
            "http://qcs12.dei.uc.pt:8080/insulin?wsdl"};

//    private final String[] urls = {"http://liis-lab.dei.uc.pt:8080/Server?wsdl",
//                                   "http://qcs01.dei.uc.pt:8080/InsulinDoseCalculator?wsdl",
//                                   "http://qcs02.dei.uc.pt:8080/insulinDosage?wsdl",
//                                   "http://qcs04.dei.uc.pt:8080/InsulinDoseCalculator?wsdl",
//                                   "http://qcs05.dei.uc.pt:8080/insulin?wsdl",
//                                   "http://qcs06.dei.uc.pt:8080/insulin?wsdl",
//                                   "http://qcs07.dei.uc.pt:8080/insulin?wsdl",
//                                   "http://qcs08.dei.uc.pt:8080/InsulinDoseCalculator?wsdl",
//                                   "http://qcs09.dei.uc.pt:8080/Insulin?wsdl",
//                                   "http://qcs10.dei.uc.pt:8080/InsulinDoseCalculator?wsdl",
//                                   "http://qcs11.dei.uc.pt:8080/insulin?wsdl",
//                                   "http://qcs12.dei.uc.pt:8080/insulin?wsdl",
//                                   "http://qcs13.dei.uc.pt:8080/insulin?wsdl",
//                                   "http://qcs16.dei.uc.pt:8080/InsulinDoseCalculator?wsdl",
//                                   "http://qcs18.dei.uc.pt:8080/insulin?wsdl",
//                                   "http://qcs19.dei.uc.pt/InsulinDoseCalculator/WebService?wsdl",
//                                   "http://qcs22.dei.uc.pt/InsulinDoseCalculator?wsdl"};
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
                    future.get(TIMEOUT, TimeUnit.MILLISECONDS);
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
                System.out.println("Started... "+urls[i]);
                try {
                    future.get(TIMEOUT, TimeUnit.MILLISECONDS);
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
                System.out.println("Finished! "+urls[i]);
            } catch (TimeoutException e) {
                lista.add(TIMEOUT_CODE);
                System.out.println("Terminated! "+urls[i]);
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
                System.out.println("Started.. "+urls[i]);
                try {
                    future.get(TIMEOUT, TimeUnit.MILLISECONDS);
                    lista.add(future.get());
                } catch (InterruptedException e) {
                    e.getMessage();
                } catch (ExecutionException e) {
                    e.getMessage();
                }
                System.out.println("Finished! "+urls[i]);
            } catch (TimeoutException e) {
                lista.add(TIMEOUT_CODE);
                System.out.println("Terminated! "+urls[i]);
            }

        }

        pool.shutdownNow();

        return chooser();

    }

    // escolhe a maioria
    // todo retornar -1 se nao tiver maioria
    private TechnicalDetail chooser() {

        TechnicalDetail td = new TechnicalDetail();
        ArrayList<Integer> temp = new ArrayList<Integer>();

        //check for voters/2 + 1 for majority
        int chosen_val = 0;
        int count = 0;
        int location = 0;

        int array_ocorrencia[] = new int[5];

        Arrays.fill(array_ocorrencia, TIMEOUT_CODE);

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
                if (uniquesArray.get(j) >= 0 && temp.get(i) == uniquesArray.get(j) || temp.get(i)-1 == uniquesArray.get(j) || temp.get(i)+1 == uniquesArray.get(j))
                {
                    occurences.set(j, occurences.get(j)+1);
//		                if (occurences.get(j) >= 3) System.out.println(uniquesArray.get(j));
                }
                if (uniquesArray.get(j) >= 0 && temp.get(i) == uniquesArray.get(j))
                {
                    occurences.set(j, occurences.get(j)+1);
                }
            }
        }
        System.out.println(temp);
        System.out.println(uniquesArray);
        System.out.println(Arrays.toString(occurences.toArray()));

        int maxValue = 0;
        int maxIndex = -1;

        for (int counter = 0; counter < occurences.size(); counter++) {

            if (occurences.get(counter) >= 5 && occurences.get(counter) > maxValue){
                maxValue = occurences.get(counter);
                maxIndex = counter;
            }
        }

        if (maxIndex != -1 )
        {
            System.out.println(uniquesArray.get(maxIndex));
            td.setMajority_result(uniquesArray.get(maxIndex));
        }
        else {
            System.out.println("s");
//            td.setMajority_result(0);
            td.setMajority_result(maxIndex);
        }

        lista.clear();

        return td;

    }
}


