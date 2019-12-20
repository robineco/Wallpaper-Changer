package aereo.rocks.base;

import aereo.rocks.base.reader.JSONReader;
import aereo.rocks.base.wallpaper.WallpaperChanger;

import java.util.TimerTask;

public class DailyTask extends TimerTask {

    private final String path;
    private JSONReader reader;
    private WallpaperChanger wallpaperChanger;

    public DailyTask(String path) {
        this.path = path;
        reader = new JSONReader();
        wallpaperChanger = new WallpaperChanger(path);
    }

    @Override
    public void run() {
        reader.saveImage(path);
        wallpaperChanger.changeWallpaper();
    }


}
