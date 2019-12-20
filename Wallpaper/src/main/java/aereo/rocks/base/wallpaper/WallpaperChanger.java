package aereo.rocks.base.wallpaper;

import java.io.IOException;

public class WallpaperChanger {

    private ProcessBuilder processBuilder = new ProcessBuilder();
    private String command = "gsettings set org.gnome.desktop.background picture-uri file://";

    public WallpaperChanger(String path) {
        command += path;
    }

    public void changeWallpaper() {
        processBuilder.command("bash", "-c", command);
        try {
            Process process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
