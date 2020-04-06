/*
Author : Vy Dao
Course : CSIS 365
Assignment: Major Project 2
Due Date : 4/5/2020
Date handed : 4/5/2020
Description: The DetailActivity file where it handle DetailActivity layout
 */
package vy.com.covid19.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView tvStateName,tvTotalCase,tvTotalDeath,tvTodayCase,tvTodayDeath;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.ivDetail);
        tvStateName = findViewById(R.id.tvStateNameDetail);
        tvTotalCase = findViewById(R.id.detailTCase);
        tvTotalDeath = findViewById(R.id.detailTD);
        tvTodayCase = findViewById(R.id.detailTodayCase);
        tvTodayDeath = findViewById(R.id.detailTodayDeath);
        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("NAME");

        String tCase = (String.format("%,d", bundle.getInt("TOTAL_CASE")));

        String tDeath = (String.format("%,d", bundle.getInt("TOTAL_DEATH")));

        String tdCase =  (String.format("%,d", bundle.getInt("TODAY_CASE")));

        String tdDeath = (String.format("%,d", bundle.getInt("TODAY_DEATH")));
        tvStateName.setText(name);
        tvTotalCase.setText(tvTotalCase.getText().toString() + " " + tCase);
        tvTotalDeath.setText(tvTotalDeath.getText().toString() + " " +  tDeath);
        tvTodayCase.setText(tvTodayCase.getText().toString() + " " +  tdCase);
        tvTodayDeath.setText(tvTodayDeath.getText().toString() + " " +  tdDeath);

        switch (name){
            case "New York":
                imageView.setImageResource(R.drawable.newyork);
                break;
            case "New Jersey":
                imageView.setImageResource(R.drawable.newjersey);
                break;
            case "Michigan":
                imageView.setImageResource(R.drawable.michigan);
                break;
            case "Louisiana":
                imageView.setImageResource(R.drawable.louisiana);
                break;
            case "Massachusetts":
                imageView.setImageResource(R.drawable.massachusetts);
                break;
            case "Florida":
                imageView.setImageResource(R.drawable.florida);
                break;
            case "Pennsylvania":
                imageView.setImageResource(R.drawable.pennsylvania);
                break;
            case "Illinois":
                imageView.setImageResource(R.drawable.illinois);
                break;
            case "Washington":
                imageView.setImageResource(R.drawable.washington);
                break;
            case "Georgia":
                imageView.setImageResource(R.drawable.georgia);
                break;

            case "Texas":
                imageView.setImageResource(R.drawable.texas);
                break;
            case "Connecticut":
                imageView.setImageResource(R.drawable.connecticut);
                break;
            case "Colorado":
                imageView.setImageResource(R.drawable.colorado);
                break;
            case "Indiana":
                imageView.setImageResource(R.drawable.indiana);
                break;
            case "Ohio":
                imageView.setImageResource(R.drawable.ohio);
                break;

            case "Tennessee":
                imageView.setImageResource(R.drawable.tennessee);
                break;
            case "Maryland":
                imageView.setImageResource(R.drawable.maryland);
                break;
            case "North Carolina":
                imageView.setImageResource(R.drawable.northcarolina);
                break;
            case "Virginia":
                imageView.setImageResource(R.drawable.virginia);
                break;
            case "Missouri":
                imageView.setImageResource(R.drawable.missouri);
                break;

            case "Wisconsin":
                imageView.setImageResource(R.drawable.wisconsin);
                break;
            case "Arizona":
                imageView.setImageResource(R.drawable.arizona);
                break;
            case "South Carolina":
                imageView.setImageResource(R.drawable.southcarolina);
                break;
            case "Nevada":
                imageView.setImageResource(R.drawable.nevada);
                break;
            case "Alabama":
                imageView.setImageResource(R.drawable.alabama);
                break;

            case "Mississippi":
                imageView.setImageResource(R.drawable.mississippi);
                break;
            case "Utah":
                imageView.setImageResource(R.drawable.utah);
                break;
            case "Oklahoma":
                imageView.setImageResource(R.drawable.oklahoma);
                break;
            case "Idaho":
                imageView.setImageResource(R.drawable.idaho);
                break;
            case "Oregon":
                imageView.setImageResource(R.drawable.oregon);
                break;

            case "Kentucky":
                imageView.setImageResource(R.drawable.kentucky);
                break;
            case "District Of Columbia":
                imageView.setImageResource(R.drawable.districtofcolumbia);
                break;
            case "Minnesota":
                imageView.setImageResource(R.drawable.minnesota);
                break;
            case "Rhode Island":
                imageView.setImageResource(R.drawable.rhodeisland);
                break;
            case "Iowa":
                imageView.setImageResource(R.drawable.iowa);
                break;

            case "Arkansas":
                imageView.setImageResource(R.drawable.arkansas);
                break;
            case "Kansas":
                imageView.setImageResource(R.drawable.kansas);
                break;
            case "Delaware":
                imageView.setImageResource(R.drawable.delaware);
                break;
            case "New Mexico":
                imageView.setImageResource(R.drawable.newmexico);
                break;
            case "New Hampshire":
                imageView.setImageResource(R.drawable.newhampshire);
                break;

            case "Vermont":
                imageView.setImageResource(R.drawable.vermont);
                break;
            case "Maine":
                imageView.setImageResource(R.drawable.maine);
                break;
            case "Hawaii":
                imageView.setImageResource(R.drawable.hawaii);
                break;
            case "Nebraska":
                imageView.setImageResource(R.drawable.nebraska);
                break;
            case "West Virginia":
                imageView.setImageResource(R.drawable.westvirginia);
                break;

            case "Montana":
                imageView.setImageResource(R.drawable.montana);
                break;
            case "South Dakota":
                imageView.setImageResource(R.drawable.southdakota);
                break;
            case "Wyoming":
                imageView.setImageResource(R.drawable.wyoming);
                break;
            case "North Dakota":
                imageView.setImageResource(R.drawable.northdakota);
                break;
            case "Alaska":
                imageView.setImageResource(R.drawable.alaska);
                break;
            case "California":
                imageView.setImageResource(R.drawable.california);
                break;
            case "Guam":
                imageView.setImageResource(R.drawable.guam);
                break;
            case "Northern Mariana Islands":
                imageView.setImageResource(R.drawable.northernmarianaislands);
                break;
            case "Puerto Rico":
                imageView.setImageResource(R.drawable.puertorico);
                break;
            case "United States Virgin Islands":
                imageView.setImageResource(R.drawable.virginislands);
                break;
        }
    }
}
