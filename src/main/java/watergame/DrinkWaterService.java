package watergame;

import java.util.Random;

public class DrinkWaterService {

    private static DrinkWaterService instance;
    private final Random random;
    private DrinkWaterService(){
        random = new Random();
    }

    public static DrinkWaterService getInstance(){
        if (instance == null){
            instance = new DrinkWaterService();
        }
        return instance;
    }

    public void drinkWater(GlassOfWater glassOfWater){
        int amount = random.nextInt(5);
        glassOfWater.setLevelOfWater(glassOfWater.getLevelOfWater() - amount);
        System.out.println(glassOfWater + "   " + glassOfWater.getDrinkerName());
    }

}
