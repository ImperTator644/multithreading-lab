package workwithwthreads;


import java.util.Random;

public class Race {

    public static int raceLength;
    private static Race instance;

    private Race(){
        Random random = new Random();
        raceLength = (random.nextInt(10) + 1) * 1000;
        System.out.println("Race length: " + raceLength + "m");
    }

    public static Race getInstance(){
        if (instance == null){
            instance = new Race();
        }
        return instance;
    }

    public static int getRaceLength() {
        return raceLength;
    }
}
