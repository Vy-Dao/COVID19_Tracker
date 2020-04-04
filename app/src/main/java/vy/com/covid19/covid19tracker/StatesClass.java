package vy.com.covid19.covid19tracker;

import android.os.Parcel;
import android.os.Parcelable;

public class StatesClass implements Parcelable {
    private String stateName;
    private int stateTotalCases;
    private int stateTodayCases;
    private int stateTotalDeaths;
    private int stateTodayDeaths;

    StatesClass(String tmpStateName, int tmpTotalCases, int tmpTotalDeaths, int tmpTodayCases, int tmpTodayDeaths){
        setStateName(tmpStateName);
        setStateTodayCases(tmpTodayCases);
        setStateTodayDeaths(tmpTodayDeaths);
        setStateTotalCases(tmpTotalCases);
        setStateTotalDeaths(tmpTotalDeaths);
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getStateTotalCases() {
        return stateTotalCases;
    }

    public void setStateTotalCases(int stateTotalCases) {
        this.stateTotalCases = stateTotalCases;
    }

    public int getStateTodayCases() {
        return stateTodayCases;
    }

    public void setStateTodayCases(int stateTodayCases) {
        this.stateTodayCases = stateTodayCases;
    }

    public int getStateTotalDeaths() {
        return stateTotalDeaths;
    }

    public void setStateTotalDeaths(int stateTotalDeaths) {
        this.stateTotalDeaths = stateTotalDeaths;
    }

    public int getStateTodayDeaths() {
        return stateTodayDeaths;
    }

    public void setStateTodayDeaths(int stateTodayDeaths) {
        this.stateTodayDeaths = stateTodayDeaths;
    }

    protected StatesClass(Parcel in) {
        stateName = in.readString();
        stateTotalCases = in.readInt();
        stateTodayCases = in.readInt();
        stateTotalDeaths = in.readInt();
        stateTodayDeaths = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stateName);
        dest.writeInt(stateTotalCases);
        dest.writeInt(stateTodayCases);
        dest.writeInt(stateTotalDeaths);
        dest.writeInt(stateTodayDeaths);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StatesClass> CREATOR = new Parcelable.Creator<StatesClass>() {
        @Override
        public StatesClass createFromParcel(Parcel in) {
            return new StatesClass(in);
        }

        @Override
        public StatesClass[] newArray(int size) {
            return new StatesClass[size];
        }
    };
}