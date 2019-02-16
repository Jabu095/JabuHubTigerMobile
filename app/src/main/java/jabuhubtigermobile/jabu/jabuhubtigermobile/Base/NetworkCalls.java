package jabuhubtigermobile.jabu.jabuhubtigermobile.Base;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NetworkCalls {
    private final String BASEURL = "https://jabulanihubtigerapi.azurewebsites.net/api/";
    public OkHttpClient client;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public NetworkCalls(){
        client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .build();
    }

    public Request GET(String url) {
        try {
            return new Request.Builder()
                    .url(BASEURL+url)
                    .build();
        } catch (Exception e) {

            return null;
        }

    }

    public Request POST(String json,String url) {
        try {
            RequestBody body = RequestBody.create(JSON, json);
            return new Request.Builder()
                    .url(BASEURL+url)
                    .post(body)
                    .build();

        } catch (Exception e) {

            return null;
        }
    }

    public Request PUT(String json,String url) {
        try {
            RequestBody body = RequestBody.create(JSON, json);
            return new Request.Builder()
                    .url(BASEURL+url)
                    .put(body)
                    .build();

        } catch (Exception e) {

            return null;
        }
    }

    public Request DELETE(String json, String url) {
        try {
            RequestBody body = RequestBody.create(JSON, json);
            return new Request.Builder()
                    .url(BASEURL+url)
                    .delete(body)
                    .build();
        } catch (Exception e) {

            return null;
        }

    }
}
