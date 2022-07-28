package currencyservice.api.service;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

@Service
public class CurrencyService {

    public String[] getCurrencies(String currencies) {
        return currencies.split("_");
    }

    public String createURL(String from, String to) {
        return "https://api.exchangerate.host/convert?from=" + from + "&to=" + to;
    }

    public JSONObject getAPIText(String location) throws RuntimeException, JSONException, IOException {
        JSONObject jsonObject;

        URL url = new URL(location);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        String inline = "";
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        scanner.close();

        jsonObject = new JSONObject(inline);

        return jsonObject;
    }

    public Double getExchangeRate(String input) throws JSONException, IOException {
        String[] currencies = getCurrencies(input);
        String originalCurrency = currencies[0];
        String newCurrency = currencies[1];
        String completedURL = createURL(originalCurrency, newCurrency);

        JSONObject resultAsJSON = getAPIText(completedURL);

        return resultAsJSON.getDouble("result");
    }
}