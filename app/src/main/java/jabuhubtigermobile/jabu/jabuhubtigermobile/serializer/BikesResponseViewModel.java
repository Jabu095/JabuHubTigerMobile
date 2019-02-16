package jabuhubtigermobile.jabu.jabuhubtigermobile.serializer;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BikesResponseViewModel implements Parcelable {
    public int statusCode;
    public String message;
    public List<BicycleResponse> data;

    protected BikesResponseViewModel(Parcel in) {
        statusCode = in.readInt();
        message = in.readString();
        data = in.createTypedArrayList(BicycleResponse.CREATOR);
    }

    public static final Creator<BikesResponseViewModel> CREATOR = new Creator<BikesResponseViewModel>() {
        @Override
        public BikesResponseViewModel createFromParcel(Parcel in) {
            return new BikesResponseViewModel(in);
        }

        @Override
        public BikesResponseViewModel[] newArray(int size) {
            return new BikesResponseViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(statusCode);
        dest.writeString(message);
        dest.writeTypedList(data);
    }
}
