package jabuhubtigermobile.jabu.jabuhubtigermobile.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;

import jabuhubtigermobile.jabu.jabuhubtigermobile.Interfaces.NetworkEvnts;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class BaseEntry extends AppCompatActivity implements NetworkEvnts {
    protected NetworkCalls db;
    private Context context;

    public BaseEntry() {
        db = new NetworkCalls();
        context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void GET(String url, final Object Body) {
        try {
            db.client.newCall(db.GET(url))
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            NetworkEvnts listener = (NetworkEvnts) context;
                            listener.OnGetDataFailed(e.getMessage(), Body,500);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            NetworkEvnts listener = (NetworkEvnts) context;
                            if (response.isSuccessful()) {
                                listener.OnGetDataSuccess(response.body().string(), Body);
                                response.body().close();
                            } else {
                                listener.OnGetDataFailed(response.body().string(), Body, response.code());
                                response.body().close();
                            }
                        }
                    });
        }catch (Exception ex){

        }
    }

    protected void POST(final Object Body, String url) {
        try {
            db.client.newCall(db.POST(new Gson().toJson(Body), url))
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            NetworkEvnts listener = (NetworkEvnts) context;
                            listener.OnGetDataFailed(e.getMessage(), Body,500);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            NetworkEvnts listener = (NetworkEvnts) context;
                            if (response.isSuccessful()) {
                                listener.OnGetDataSuccess(response.body().string(), Body);
                                response.body().close();
                            } else {
                                listener.OnGetDataFailed(response.body().string(), Body, response.code());
                                response.body().close();
                            }
                        }
                    });
        }catch (Exception ex){

        }
    }

    protected void PUT(final Object Body, String url) {
        try {
            db.client.newCall(db.PUT(new Gson().toJson(Body), url))
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            NetworkEvnts listener = (NetworkEvnts) context;
                            listener.OnGetDataFailed(e.getMessage(), Body,500);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            NetworkEvnts listener = (NetworkEvnts) context;
                            if (response.isSuccessful()) {
                                listener.OnGetDataSuccess(response.body().string(), Body);
                                response.body().close();
                            } else {
                                listener.OnGetDataFailed(response.body().string(), Body, response.code());
                                response.body().close();
                            }
                        }
                    });
        }catch (Exception ex){

        }
    }

    protected void DELETE(String url, final Object Body) {
        try {
            db.client.newCall(db.DELETE(new Gson().toJson(Body),url))
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            NetworkEvnts listener = (NetworkEvnts) context;
                            listener.OnGetDataFailed(e.getMessage(), Body,500);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            NetworkEvnts listener = (NetworkEvnts) context;
                            if (response.isSuccessful()) {
                                listener.OnGetDataSuccess(response.body().string(), Body);
                                response.body().close();
                            } else {
                                if(response.code() == 401){

                                }else {
                                    listener.OnGetDataFailed(response.body().string(), Body, response.code());
                                    response.body().close();
                                }
                            }
                        }
                    });
        }catch (Exception ex){

        }
    }
}
