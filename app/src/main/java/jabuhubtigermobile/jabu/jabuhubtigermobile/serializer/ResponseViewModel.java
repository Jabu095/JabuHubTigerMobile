package jabuhubtigermobile.jabu.jabuhubtigermobile.serializer;

import android.os.Parcel;
import android.os.Parcelable;

public class ResponseViewModel implements Parcelable {
    public int statusCode;
    public String message;
    public BicycleResponse data;


    protected ResponseViewModel(Parcel in) {
        statusCode = in.readInt();
        message = in.readString();
        data = in.readParcelable(BicycleResponse.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(statusCode);
        dest.writeString(message);
        dest.writeParcelable(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseViewModel> CREATOR = new Creator<ResponseViewModel>() {
        @Override
        public ResponseViewModel createFromParcel(Parcel in) {
            return new ResponseViewModel(in);
        }

        @Override
        public ResponseViewModel[] newArray(int size) {
            return new ResponseViewModel[size];
        }
    };
}
