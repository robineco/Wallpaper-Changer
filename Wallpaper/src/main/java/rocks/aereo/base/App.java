package rocks.aereo.base;

import lombok.extern.java.Log;

import java.util.Timer;

@Log
public class App {

    public static void main(String[] args) {
        Timer timer = new Timer();
        DailyTask dailyTask = new DailyTask(args[0]);
        log.info("starting application...");
        timer.scheduleAtFixedRate(dailyTask, 0, 1800000);
    }
}
