package jabuhubtigermobile.jabu.jabuhubtigermobile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import jabuhubtigermobile.jabu.jabuhubtigermobile.Interfaces.CustomAdaptorEvents;
import jabuhubtigermobile.jabu.jabuhubtigermobile.serializer.BicycleResponse;

public class customDialog extends Dialog implements android.view.View.OnClickListener {
    public Context c;
    public Dialog d;
    public Button yes;
    private Button btndialogClose;
    private BicycleResponse bicycleResponse;
    private EditText txtbikeC;
    private Spinner spnmodelsC;


    public customDialog(Context a, BicycleResponse bicycleResponse) {
        super(a);
        this.c = a;
        this.bicycleResponse = bicycleResponse;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        txtbikeC = findViewById(R.id.txtbikeC);
        spnmodelsC = findViewById(R.id.spnmodelsC);

        btndialogClose = findViewById(R.id.btnClose);
        yes = findViewById(R.id.btnSave);

        txtbikeC.setText(bicycleResponse.model);
        switch (bicycleResponse.bicyleCondition){
            case "good":
                spnmodelsC.setSelection(0);
                break;
            case "bad":
                spnmodelsC.setSelection(1);
                break;
            case "worse":
                spnmodelsC.setSelection(2);
                break;
                default:
                    spnmodelsC.setSelection(0);
                    break;
        }
        btndialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomAdaptorEvents listener = (CustomAdaptorEvents) c;
                bicycleResponse = new BicycleResponse(bicycleResponse.id,txtbikeC.getText().toString(),bicycleResponse.createdOn,spnmodelsC.getSelectedItem().toString());
                listener.customEventTrigger("update", bicycleResponse.id, bicycleResponse);
                dismiss();
            }
        });
        //yes.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.btnTCok:
                c.finish();
                dismiss();
                break;
            default:
                break;
        }*/

    }
}
