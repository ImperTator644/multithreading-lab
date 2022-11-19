package workwithwthreads;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Runner {

    private String runnerName;
    private final int runnerPace;
    private int distanceToFinish;
    private int runnerTime;

    @Override
    public String toString() {
        return runnerName + " finished in : " + runnerTime;
    }

    public boolean isDone(){return distanceToFinish > 0;}
}
