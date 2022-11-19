package watergame;


import lombok.RequiredArgsConstructor;

import java.util.concurrent.Callable;
@RequiredArgsConstructor
public class DrinkWaterThread implements Callable<GlassOfWater>{
    private DrinkWaterService drinkWaterService = DrinkWaterService.getInstance();
    private final String name;

    @Override
    public GlassOfWater call() {
        GlassOfWater glassOfWater = new GlassOfWater(name,20);
        drinkAllWater(glassOfWater);
        return glassOfWater;
    }

    private void drinkAllWater(GlassOfWater glassOfWater) {
        while(!glassOfWater.isEmpty()){
            drinkWaterService.drinkWater(glassOfWater);
        }
    }

    @Override
    public String toString(){
        return name;
    }
}
