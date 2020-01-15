package rocks.aereo.base.reader;

import lombok.extern.java.Log;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log
public class JSONReader {

    String url;
    String baseUrl = "https://www.bing.com";
    String param = "/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=de-DE";
    private static final String[] properties = {"User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11"};

    public JSONReader(String url) {
        this.url = url;
    }

    public void saveImage(String path) {

        String urlToImage = "";
        try {
            JSONObject result = new JSONObject(setUpUrl());
            urlToImage = result.getJSONArray("images").getJSONObject(0).get("url").toString();
        } catch (Exception e) {
            log.info("retry in 15 min.");
            try {
                Thread.sleep(900000);
                saveImage(url);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        try (InputStream in = new URL(baseUrl + urlToImage).openStream()) {
            log.info("downloading picture...");
            if (Files.exists(Paths.get(path))) {
                Files.delete(Paths.get(path));
                Files.copy(in, Paths.get(path));
            } else {
                Files.copy(in, Paths.get(path));
            }
        } catch (IOException e) {
            log.warning(e.toString());

        }
    }

    private String setUpUrl() {
        try {
            URL urlObject = new URL(baseUrl + param);
            URLConnection urlConnection = urlObject.openConnection();
            urlConnection.setRequestProperty(properties[0], properties[1]);
            return webPageToString(urlConnection.getInputStream());
        } catch (IOException e) {
            log.warning(e.toString());
        }
        return "";
    }

    private String webPageToString(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            return stringBuilder.toString();
        }
    }
}
