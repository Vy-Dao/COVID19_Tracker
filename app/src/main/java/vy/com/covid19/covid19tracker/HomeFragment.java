package vy.com.covid19.covid19tracker;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    private TextView txtConfirmNum,txtDeathNum,txtRecoverNum, txtDate, txtTodayDeaths, txtTodayCases;

    @SuppressLint("SetTextI18n")
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

        ArrayList<String> tmpArrayList = getArguments().getStringArrayList("US_TOTAL");
        txtConfirmNum.setText(tmpArrayList.get(0));
        txtDeathNum.setText(tmpArrayList.get(1));
        txtRecoverNum.setText(tmpArrayList.get(2));
        txtDate.setText("(" + getString(R.string.txtUpdatedat) + ": " + tmpArrayList.get(3) + ")");
        txtTodayCases.setText( getString(R.string.stringCases)+ ": " +tmpArrayList.get(4));
        txtTodayDeaths.setText( getString(R.string.stringDeaths)+ ": " + tmpArrayList.get(5));
        return  view;
    }
}
