import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String url = "https://api.nobelprize.org/2.0/nobelPrize/phy/2010";
        HttpsURLConnection connection;
        URL u = new URL(url);
        connection = (HttpsURLConnection) u.openConnection();
        connection.setConnectTimeout(10000);
        connection.connect();

        StringBuilder stringBuilder = new StringBuilder();
        if(connection.getResponseCode() == 200) {
            Scanner scan = new Scanner(connection.getInputStream());
            while(scan.hasNext()) {
                stringBuilder.append(scan.nextLine());
            }
        }
        Gson gson = new Gson();
        Answer[] answers = gson.fromJson(stringBuilder.toString(),Answer[].class);
        System.out.println(answers[0].getAwardYear());
        List<Laureate> laureates = answers[0].getLaureates();
        System.out.println(laureates.get(0).getFullName().getEn());
        System.out.println(laureates.get(1).getFullName().getEn());

    }
}