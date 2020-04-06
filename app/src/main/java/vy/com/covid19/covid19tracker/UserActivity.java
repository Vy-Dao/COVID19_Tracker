/*
Author : Vy Dao
Course : CSIS 365
Assignment: Major Project 2
Due Date : 4/5/2020
Date handed : 4/5/2020
Description: The UserActivity file where it handle UserActivity layout
 */
package vy.com.covid19.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserActivity extends AppCompatActivity {

    private static ArrayList<StatesClass> arrayList;
    private Fragment selectedFragment = null;
    private Bundle mainBundle = new Bundle();
    private AccountInfo accountInfoArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //Set-up bottom navigation
        mainBundle = getIntent().getExtras();

        accountInfoArrayList = mainBundle.getParcelable("ACCOUNT_INFO");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        selectedFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,selectedFragment).commit();

    }

    //Set-up bot nav listener
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    arrayList = new ArrayList<>();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,selectedFragment).commit();
                    break;
                case R.id.nav_states:
                    arrayList = new ArrayList<>();
                    GetAPIStates();
                    break;
                case R.id.nav_fav:
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ACC_INFO",accountInfoArrayList);
                    selectedFragment = new AboutFragment();
                    selectedFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,selectedFragment).commit();
            }
            return true;
        }
    };


    String respString;
    private ArrayList<StatesClass> statesArrayList = new ArrayList<>();
    public void GetAPIStates() {
        //Set-up URL
        final String tmpUrl = "https://corona.lmao.ninja/states";
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
                if (response.isSuccessful())
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String tmpStates="";
                            int tmpCases=0,tmpTodayCases=0,tmpDeaths=0,tmpTodayDeaths=0;
                            JSONObject tmpJsonObject;
                            try {
                                respString = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONArray jsonArray = new JSONArray(respString);
                                for(int i =0; i < jsonArray.length(); i ++){
                                    tmpJsonObject = jsonArray.getJSONObject(i);
                                    tmpStates = tmpJsonObject.getString("state");
                                    tmpCases = tmpJsonObject.getInt("cases");
                                    tmpTodayCases = tmpJsonObject.getInt("todayCases");
                                    tmpDeaths = tmpJsonObject.getInt("deaths");
                                    tmpTodayDeaths = tmpJsonObject.getInt("todayDeaths");
                                    if (!tmpStates.equals("Wuhan Repatriated") && !tmpStates.equals("Diamond Princess Cruise") && !tmpStates.equals("USA Total") ){
                                        statesArrayList.add(new StatesClass(tmpStates,tmpCases,tmpDeaths,tmpTodayCases,tmpTodayDeaths));
                                    }
                                }
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("ARRAY_LIST",statesArrayList);
                                bundle.putParcelable("ARRAY_ACCOUNT",accountInfoArrayList);
                                selectedFragment = new StatesFragment();
                                selectedFragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,selectedFragment).commit();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}
