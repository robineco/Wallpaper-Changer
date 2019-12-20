package aereo.rocks.base;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.TimerTask;

public class DailyTask extends TimerTask {

    String baseUrl = "https://www.bing.com";
    String param = "/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=de-DE";
    private static final String[] properties = {"User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11"};


    public DailyTask() {

    }

    @Override
    public void run() {
        JSONObject result = new JSONObject(setUpUrl());
        String url = result.getJSONArray("images").getJSONObject(0).get("url").toString();
        System.out.println(baseUrl + url);


    }

    private String setUpUrl() {
        try {
            URL urlObject = new URL(baseUrl + param);
            URLConnection urlConnection = urlObject.openConnection();
            urlConnection.setRequestProperty(properties[0], properties[1]);
            return webPageToString(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
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
