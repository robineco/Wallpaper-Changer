package rocks.aereo.base;

import java.util.Timer;

public class App {

    public static void main(String[] args) {
        System.out.println(args[0]);
        Timer timer = new Timer();
        DailyTask dailyTask = new DailyTask(args[0]);

        timer.scheduleAtFixedRate(dailyTask, 0 , 86400000);
    }
}
