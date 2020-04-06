/*
Author : Vy Dao
Course : CSIS 365
Assignment: Major Project 2
Due Date : 4/5/2020
Date handed : 4/5/2020
Description: The StatesFragment file where it handle the StatesFragment layout
 */
    package vy.com.covid19.covid19tracker;

    import android.os.Bundle;

    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.DividerItemDecoration;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.SeekBar;
    import android.widget.TextView;

    import org.jetbrains.annotations.NotNull;
    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.IOException;
    import java.lang.reflect.Array;
    import java.util.ArrayList;
    import java.util.Iterator;

    import okhttp3.Call;
    import okhttp3.Callback;
    import okhttp3.OkHttpClient;
    import okhttp3.Request;
    import okhttp3.Response;


    public class StatesFragment extends Fragment {

        private ArrayList<StatesClass> statesArrayList = new ArrayList<>();
        private ArrayList<StatesClass> tmpArrayList = new ArrayList<>();
        private RecyclerViewAdapterStates adapterStates;
        private RecyclerView recyclerView;


        public StatesFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_states, container, false);
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                statesArrayList = bundle.getParcelableArrayList("ARRAY_LIST");
            }
            recyclerView = view.findViewById(R.id.StateRecyclerView);
            adapterStates = new RecyclerViewAdapterStates(statesArrayList,getActivity());
            recyclerView.setAdapter(adapterStates);
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            return view;
        }

    }
