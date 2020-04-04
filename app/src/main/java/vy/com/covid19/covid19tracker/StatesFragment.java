package vy.com.covid19.covid19tracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatesFragment extends Fragment {

    private ArrayList<StatesFragment> statesArrayList = new ArrayList<>();

    public StatesFragment() {
        // Required empty public constructor
    }

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_states, container, false);

        textView = view.findViewById(R.id.txtStates);
        GetAPI();
        return view;
    }

    private  String respString;
    public void GetAPI() {
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
                if (response.isSuccessful() || getActivity() != null )
                {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String tmpString = "";
                            String tmpStates = "";
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
                                    tmpString += tmpStates + ", ";
                                }
                                textView.setText(tmpString);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else {
                    return;
                }
            }
        });
    }
}
