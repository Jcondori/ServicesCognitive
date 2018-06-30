package Dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class RestDao {

    public String consultar(String url) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        JsonParser convert = new JsonParser();
        String fruta;
        try {
            StringEntity body = new StringEntity("{\"Url\": \"" + url + "\"}");

            HttpPost request = new HttpPost("https://southcentralus.api.cognitive.microsoft.com/customvision/v2.0/Prediction/cc2cf8b4-a145-40e8-a87a-f9c5ee547503/url");
            request.addHeader("Prediction-Key", "9b419be6a1204f9ca0c62e84753327fd");
            request.addHeader("Content-Type", "application/json");
            request.setEntity(body);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            JsonObject json = convert.parse(EntityUtils.toString(entity)).getAsJsonObject();

            JsonArray predicciones = json.getAsJsonArray("predictions");
            if (predicciones == null) {
                fruta = "Error";
            } else {
                fruta = convert.parse(predicciones.get(0).toString()).getAsJsonObject().get("tagName").toString().substring(1);
                fruta = fruta.substring(0, fruta.length() - 1);
            }
            return fruta;
        } catch (JsonSyntaxException e) {
            throw e;
        }
    }

}
