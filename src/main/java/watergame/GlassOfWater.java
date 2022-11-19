package watergame;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GlassOfWater {

    private String drinkerName;
    private int levelOfWater;

    public boolean isEmpty(){
        return levelOfWater <= 0;
    }
}
