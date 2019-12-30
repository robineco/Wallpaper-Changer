package rocks.aereo.base.wallpaper;

import lombok.extern.java.Log;

import java.io.IOException;

@Log
public class WallpaperChanger {

    private ProcessBuilder processBuilder = new ProcessBuilder();
    private String command = "gsettings set org.gnome.desktop.background picture-uri file://";

    public WallpaperChanger(String path) {
        command += path;
    }

    public void changeWallpaper() {
        log.info("changing wallpaper...");
        processBuilder.command("bash", "-c", command);
        try {
            Process process = processBuilder.start();
        } catch (IOException e) {
            log.warning(e.toString());
        }
    }
}
