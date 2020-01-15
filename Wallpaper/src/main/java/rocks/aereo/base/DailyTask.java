package rocks.aereo.base;

import lombok.extern.java.Log;
import rocks.aereo.base.reader.JSONReader;
import rocks.aereo.base.wallpaper.WallpaperChanger;

import java.util.TimerTask;

@Log
public class DailyTask extends TimerTask {

    private final String path;
    private JSONReader reader;
    private WallpaperChanger wallpaperChanger;

    public DailyTask(String path) {
        this.path = path;
        reader = new JSONReader(path);
        wallpaperChanger = new WallpaperChanger(path);
    }

    @Override
    public void run() {
        log.info("searching for new image...");
        reader.saveImage(path);
        wallpaperChanger.changeWallpaper();
    }


}
