package jabuhubtigermobile.jabu.jabuhubtigermobile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import jabuhubtigermobile.jabu.jabuhubtigermobile.Base.BaseEntry;
import jabuhubtigermobile.jabu.jabuhubtigermobile.Helpers.BicycleRequestViewModel;
import jabuhubtigermobile.jabu.jabuhubtigermobile.Interfaces.CustomAdaptorEvents;
import jabuhubtigermobile.jabu.jabuhubtigermobile.adoptor.bikesAdaptor;
import jabuhubtigermobile.jabu.jabuhubtigermobile.serializer.AllBikes;
import jabuhubtigermobile.jabu.jabuhubtigermobile.serializer.BicycleResponse;
import jabuhubtigermobile.jabu.jabuhubtigermobile.serializer.BikesResponseViewModel;
import jabuhubtigermobile.jabu.jabuhubtigermobile.serializer.ResponseViewModel;

public class MainActivity extends BaseEntry implements View.OnClickListener , CustomAdaptorEvents {

    @BindView(R.id.txtbike)
    EditText txtbike;
    @BindView(R.id.spnmodels)
    Spinner spnmodels;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @BindView(R.id.lstBikes)
    ListView lstBikes;

    @BindView(R.id.prgs)
    ProgressBar prgs;

    private bikesAdaptor BikesAdaptor;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        initEvents();
    }

    private void initEvents(){

        String[] items = new String[]{"good", "bad", "worse"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spnmodels.setAdapter(adapter);
        GET("v1/Bicycle/GetAll",new AllBikes());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtbike.getText().toString().isEmpty() && !spnmodels.getSelectedItem().toString().isEmpty()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            POST(new BicycleRequestViewModel(txtbike.getText().toString(),spnmodels.getSelectedItem().toString()),"v1/Bicycle/Post");
                            btnSubmit.setText("Creating Bike ...");

                        }
                    });
                    }
            }
        });

    }
    @Override
    public void OnGetDataSuccess(final String responseBody, Object object) {
        final Gson json = new Gson();
        if(object instanceof BicycleRequestViewModel){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btnSubmit.setText("Submit");
                    final ResponseViewModel responseViewModel = json.fromJson(responseBody, ResponseViewModel.class);
                    GET("v1/Bicycle/GetAll",new AllBikes());
                }
            });
        }
        if(object instanceof BicycleResponse){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    GET("v1/Bicycle/GetAll",new AllBikes());
                }
            });
        }

        if(object instanceof AllBikes){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final BikesResponseViewModel responseViewModel = json.fromJson(responseBody, BikesResponseViewModel.class);
                    BikesAdaptor = new bikesAdaptor(responseViewModel.data, context);
                    lstBikes.setAdapter(BikesAdaptor);
                    prgs.setVisibility(View.GONE);
                    lstBikes.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    public void OnGetDataFailed(String ResponseBody, Object object, int status) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnSubmit.setText("Submit");
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void customEventTrigger(String stringValue, int intValue, final Object objectValue) {
        if(objectValue instanceof BicycleResponse && stringValue.equals("update")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    PUT(objectValue,"v1/Bicycle/Put");
                }
            });
        }
        if(objectValue instanceof BicycleResponse && stringValue.equals("delete")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DELETE("v1/Bicycle/Delete?Id="+((BicycleResponse)objectValue).id,objectValue);
                }
            });
        }
    }
}
