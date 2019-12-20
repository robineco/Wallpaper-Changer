package aereo.rocks.base;

import java.util.Timer;

public class App {

    public static void main(String[] args) {
        Timer timer = new Timer();
        DailyTask dailyTask = new DailyTask();

        timer.scheduleAtFixedRate(dailyTask, 0 , 86400000);
    }
}
