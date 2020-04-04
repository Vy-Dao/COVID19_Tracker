package vy.com.covid19.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;

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


public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetUSTotalCase();
            }
        });
    }

    private String respString;

    public void GetUSTotalCase(){
        final ArrayList<String> tmpList = new ArrayList<>();
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
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(MainActivity.this,UserActivity.class);
                    bundle.putStringArrayList("US_TOTAL",tmpList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
}
