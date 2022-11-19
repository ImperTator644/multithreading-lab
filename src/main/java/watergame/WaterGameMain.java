package watergame;

import calculateequation.Equation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;


public class WaterGameMain {

    private static int place = 1;
    private static final List<String> names = new ArrayList<>(Arrays.asList("Konrad", "Lukasz", "Kasia", "Patryk"));

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(names.size());

        for(int i=0; i<names.size();i++) {
            exec.submit(new FutureTask<GlassOfWater>(new DrinkWaterThread(names.get(i))) {

                @Override
                public void done() {
                    try {
                        GlassOfWater glassOfWater = get();
                        System.out.println(place++ + ". " + glassOfWater.getDrinkerName());

                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        exec.shutdown();
    }
}
