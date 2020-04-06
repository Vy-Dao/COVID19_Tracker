/*
Author : Vy Dao
Course : CSIS 365
Assignment: Major Project 2
Due Date : 4/5/2020
Date handed : 4/5/2020
Description: The HomeFragment file where it handle Home Fragment layout
 */
package vy.com.covid19.covid19tracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    private TextView txtConfirmNum,txtDeathNum,txtRecoverNum, txtDate, txtTodayDeaths, txtTodayCases;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        txtConfirmNum = view.findViewById(R.id.txtConfirmNumber);
        txtDeathNum = view.findViewById(R.id.txtDeathNum);
        txtRecoverNum = view.findViewById(R.id.txtRecoverNum);
        txtDate = view.findViewById(R.id.txtDate);
        txtTodayCases = view.findViewById(R.id.txtTodayCases);
        txtTodayDeaths = view.findViewById(R.id.txtTodayDeath);

        GetAPIForHome();

        return view;
    }

    private String respString;
    private ArrayList<String> tmpList = new ArrayList<>();
    private void GetAPIForHome(){
        if (getActivity() != null)
        {
            //Set-up URL
            final String tmpUrl = "https://corona.lmao.ninja/countries/USA";
            //Call API
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(tmpUrl)
                    .build();
            //Handle JSON
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }
                @Override
                public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                    if (response.isSuccessful()){
                        try {
                            respString = response.body().string();
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(respString);
                            //Times
                            int tmpCases = jsonObject.getInt("cases");
                            int tmpDeath = jsonObject.getInt("deaths");
                            int tmpRecovered = jsonObject.getInt("recovered");
                            int tmpTodayCase = jsonObject.getInt("todayCases");
                            int tmpTodayDeath = jsonObject.getInt("todayDeaths");

                            //Date Convert
                            long time = jsonObject.getLong("updated");
                            Calendar cal = Calendar.getInstance(Locale.US);
                            cal.setTimeInMillis(time);
                            String date = DateFormat.format("MM-dd-yyyy hh:mm:ss", cal).toString();

                            tmpList.add(String.format("%,d", tmpCases));
                            tmpList.add(String.format("%,d", tmpDeath));
                            tmpList.add(String.format("%,d", tmpRecovered));
                            tmpList.add(date);
                            tmpList.add(String.format("%,d", tmpTodayCase));
                            tmpList.add(String.format("%,d", tmpTodayDeath));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtConfirmNum.setText(tmpList.get(0));
                                txtDeathNum.setText(tmpList.get(1));
                                txtRecoverNum.setText(tmpList.get(2));
                                txtDate.setText("(" + getString(R.string.txtUpdatedat) + ": " + tmpList.get(3) + ")");
                                txtTodayCases.setText( getString(R.string.stringCases)+ ": " +tmpList.get(4));
                                txtTodayDeaths.setText( getString(R.string.stringDeaths)+ ": " + tmpList.get(5));
                            }
                        });
                    }
                }
            });
        }
    }
}
