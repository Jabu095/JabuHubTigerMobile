package jabuhubtigermobile.jabu.jabuhubtigermobile.serializer;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class BicycleResponse implements Parcelable {

    public int id;
    public String model;
    public String createdOn;
    public String bicyleCondition;


    public BicycleResponse(Parcel in) {
        id = in.readInt();
        model = in.readString();
        createdOn = in.readString();
        bicyleCondition = in.readString();
    }

    public BicycleResponse() {

    }

    public BicycleResponse(int id, String model, String createdOn, String bicyleCondition) {
        this.id = id;
        this.model = model;
        this.createdOn = createdOn;
        this.bicyleCondition = bicyleCondition;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(model);
        dest.writeString(createdOn);
        dest.writeString(bicyleCondition);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BicycleResponse> CREATOR = new Creator<BicycleResponse>() {
        @Override
        public BicycleResponse createFromParcel(Parcel in) {
            return new BicycleResponse(in);
        }

        @Override
        public BicycleResponse[] newArray(int size) {
            return new BicycleResponse[size];
        }
    };
}
