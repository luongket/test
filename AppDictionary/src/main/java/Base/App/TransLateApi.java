package Base.App;
import com.google.gson.*;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TransLateApi {
    private String host;
    private String x_rapidapi_host;
    private String x_rapidapi_key;
    private String accept_encoding;
    private String baseUrl;
    private String content_type;
    private JsonObject jsonObject;
    private JsonArray jsonArray;


    public TransLateApi() {
       this.x_rapidapi_host="rapid-translate-multi-traduction.p.rapidapi.com";
       this.x_rapidapi_key="2a5f66eff0msha8789db8986b588p193012jsnd5cbf3372fac";
        accept_encoding = "application/gzip";
        baseUrl = "https://rapid-translate-multi-traduction.p.rapidapi.com/t";
        content_type = "application/json";
    }
    public String translate(String langFrom, String langTo, String text){
        String result="";
        try {
            String encodedMessage= URLEncoder.encode(text, "UTF-8");
            System.out.println(encodedMessage);
            String query = "{\r\n    \"from\": \""+langFrom+"\",\r\n " +
                    "   \"to\": \""+langTo+"\",\r\n" +
                    "   \"q\": \""+text+"\"\r\n}";
            System.out.println(query);
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(baseUrl))
                    .header("content-type", content_type)
                    .header("accept-encoding", accept_encoding)
                    .header("x-rapidapi-key", x_rapidapi_key)
                    .header("x-rapidapi-host", x_rapidapi_host)
                    .method("POST", HttpRequest.BodyPublishers.ofString(query))
                    .build();
            HttpResponse<String> stringHttpResponse;
            HttpClient client =HttpClient.newHttpClient();
            stringHttpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            result+=stringHttpResponse.toString();
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonElement jsonElement = JsonParser.parseString(stringHttpResponse.body());
               // System.out.println(jsonElement);
                String pretty = gson.toJson(jsonElement);
                result=pretty;

            }catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return TranlateResult.HelperResult(result);

    }

}
