package rocks.aereo.base;

import java.util.Timer;

public class App {

    public static void main(String[] args) {
        Timer timer = new Timer();
        DailyTask dailyTask = new DailyTask(args[0]);
        timer.scheduleAtFixedRate(dailyTask, 0 , 3600);
    }
}
