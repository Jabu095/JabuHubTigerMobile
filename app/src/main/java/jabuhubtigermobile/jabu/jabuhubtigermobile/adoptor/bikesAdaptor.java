package jabuhubtigermobile.jabu.jabuhubtigermobile.adoptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import jabuhubtigermobile.jabu.jabuhubtigermobile.Interfaces.CustomAdaptorEvents;
import jabuhubtigermobile.jabu.jabuhubtigermobile.R;
import jabuhubtigermobile.jabu.jabuhubtigermobile.customDialog;
import jabuhubtigermobile.jabu.jabuhubtigermobile.serializer.BicycleResponse;

public class bikesAdaptor extends BaseAdapter {
    private List<BicycleResponse> bicycleResponseList;
    Context context;
    LayoutInflater layoutInflater;

    public bikesAdaptor(List<BicycleResponse> bicycleResponseList, Context context) {
        this.bicycleResponseList = bicycleResponseList;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return bicycleResponseList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View viewContent, ViewGroup parent) {
        bikesAdaptor.ViewHolder viewHolder = null;
        if (viewContent == null) {
            viewContent = layoutInflater.inflate(R.layout.item_bikes, null);
        }
        viewHolder = new bikesAdaptor.ViewHolder();
        viewContent = layoutInflater.inflate(R.layout.item_bikes, null);
        viewHolder.txtC = viewContent.findViewById(R.id.txtC);
        viewHolder.txtM = viewContent.findViewById(R.id.txtM);
        viewHolder.btnDelete = viewContent.findViewById(R.id.btnDelete);
        viewHolder.btnUpdate = viewContent.findViewById(R.id.btnUpdate);
        viewHolder.edC = viewContent.findViewById(R.id.edC);
        viewHolder.edM = viewContent.findViewById(R.id.edM);

        viewHolder.txtC.setText(bicycleResponseList.get(position).bicyleCondition);
        viewHolder.txtM.setText(bicycleResponseList.get(position).model);
        viewHolder.btnUpdate.setTag(bicycleResponseList.get(position));
        viewHolder.btnDelete.setTag(bicycleResponseList.get(position));


        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Button)v).setText("Updating..");
                BicycleResponse app = (BicycleResponse) v.getTag();
                customDialog dialog = new customDialog(context,app);
                dialog.show();
//                CustomAdaptorEvents listener = (CustomAdaptorEvents) context;
//                listener.customEventTrigger("update", app.id, app);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Button)v).setText("Deleting..");
                BicycleResponse app = (BicycleResponse) v.getTag();
                CustomAdaptorEvents listener = (CustomAdaptorEvents) context;
                listener.customEventTrigger("delete", app.id, app);
            }
        });

        return viewContent;
    }

    private class ViewHolder {
        public TextView txtM;
        public TextView txtC;
        public Button btnDelete;
        public Button btnUpdate;
        public EditText edM;
        public EditText edC;
    }
}
