package voter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * Created by cmfsnts on 15/05/2015.
 */
public class Voter {

    //Urls válidos e funçoes
    private final String[] urls = {"http://liis-lab.dei.uc.pt:8080/Server?wsdl",
                                   "http://qcs07.dei.uc.pt:8080/insulin?wsdl",
                                   "http://qcs06.dei.uc.pt:8080/insulin?wsdl",
                                   "http://qcs10.dei.uc.pt:8080/InsulinDoseCalculator?wsdl",
                                   "http://qcs12.dei.uc.pt:8080/insulin?wsdl"};
    private final int numberThreads = 5;                        // number of webservices
    ArrayList<Future<Integer>> lista = new ArrayList<Future<Integer>>();    //  valores (inteiros) devolvidos por cada web service
    ArrayList<Future<Integer>> temp = new ArrayList<Future<Integer>>();     //  guarda as threads criadas para ligar aos N web services

    public void backgroundInsulin(int weight){

        ExecutorService pool = Executors.newFixedThreadPool(numberThreads);

        // criar as threads
        for(int i=0;i<numberThreads;i++) {
            BackgroundThread task = new BackgroundThread(urls[i], weight);
            Future<Integer> future = pool.submit(task);
            temp.add(future);
            lista.add(future);
        }

        //Timer for each thread
        for(int i=0;i<numberThreads;i++) {
            Future<Integer> future = temp.get(i);
            try {
                System.out.println("Started...");
                try {
                    // 1 segundo por thread
                    future.get(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished!");
            } catch (TimeoutException e) {
                System.out.println("Terminated!");
            }
        }

        pool.shutdownNow();

        chooser();

    }


    public void meatimeInsulin(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity){

        ExecutorService pool = Executors.newFixedThreadPool(numberThreads);

        for(int i=0;i<numberThreads;i++){

            MealTimeThread task = new MealTimeThread(urls[i],carbohydrateAmount, carbohydrateToInsulinRatio, preMealBloodSugar, targetBloodSugar, personalSensitivity);
            Future<Integer> future = pool.submit(task);
            try {
                //System.out.println("Started..");
                try {
                    future.get(4, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                //System.out.println("Finished!");
            } catch (TimeoutException e) {
                //Se demora mais de 4 secs
                //System.out.println("Terminated!");
            }

            lista.add(future);

        }

        pool.shutdownNow();

        chooser();

    }


    // escolhe a maioria (em construcao)
    private int chooser() {
    //check for voters/2 + 1 for majority
        int chosen_val = 0;
        int count = 0;
        int location = 0;

        int array_ocorrencia[] = new int[5];

        Arrays.fill(array_ocorrencia,-2);






        return chosen_val;

    }


}



