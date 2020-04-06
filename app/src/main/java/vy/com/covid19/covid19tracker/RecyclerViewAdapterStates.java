/*
Author : Vy Dao
Course : CSIS 365
Assignment: Major Project 2
Due Date : 4/5/2020
Date handed : 4/5/2020
Description: The RecyclerViewAdapterStates file where it handle RecyclerView AdapterStates
 */
package vy.com.covid19.covid19tracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterStates extends RecyclerView.Adapter<RecyclerViewAdapterStates.ViewHolder>{
    private static ArrayList<StatesClass> mStatesArrayList;
    private Context mContext;

    public RecyclerViewAdapterStates(ArrayList<StatesClass> mStatesArrayList, Context tmpContext) {
        this.mStatesArrayList = mStatesArrayList;
        this.mContext = tmpContext;

    }

    //Set-up Layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout_states,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    private String tmpStateName;
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvStatenName.setText(mStatesArrayList.get(position).getStateName());
        holder.tvTotalCase.setText(String.format("%,d",mStatesArrayList.get(position).getStateTotalCases()));
        holder.tvTotalDeath.setText(String.format("%,d", mStatesArrayList.get(position).getStateTotalDeaths()));

        switch (mStatesArrayList.get(position).getStateName()){
            case "New York":
                holder.ivState.setImageResource(R.drawable.newyork);
                break;
            case "New Jersey":
                holder.ivState.setImageResource(R.drawable.newjersey);
                break;
            case "Michigan":
                holder.ivState.setImageResource(R.drawable.michigan);
                break;
            case "Louisiana":
                holder.ivState.setImageResource(R.drawable.louisiana);
                break;
            case "Massachusetts":
                holder.ivState.setImageResource(R.drawable.massachusetts);
                break;

            case "Florida":
                holder.ivState.setImageResource(R.drawable.florida);
                break;
            case "Pennsylvania":
                holder.ivState.setImageResource(R.drawable.pennsylvania);
                break;
            case "Illinois":
                holder.ivState.setImageResource(R.drawable.illinois);
                break;
            case "Washington":
                holder.ivState.setImageResource(R.drawable.washington);
                break;
            case "Georgia":
                holder.ivState.setImageResource(R.drawable.georgia);
                break;

            case "Texas":
                holder.ivState.setImageResource(R.drawable.texas);
                break;
            case "Connecticut":
                holder.ivState.setImageResource(R.drawable.connecticut);
                break;
            case "Colorado":
                holder.ivState.setImageResource(R.drawable.colorado);
                break;
            case "Indiana":
                holder.ivState.setImageResource(R.drawable.indiana);
                break;
            case "Ohio":
                holder.ivState.setImageResource(R.drawable.ohio);
                break;

            case "Tennessee":
                holder.ivState.setImageResource(R.drawable.tennessee);
                break;
            case "Maryland":
                holder.ivState.setImageResource(R.drawable.maryland);
                break;
            case "North Carolina":
                holder.ivState.setImageResource(R.drawable.northcarolina);
                break;
            case "Virginia":
                holder.ivState.setImageResource(R.drawable.virginia);
                break;
            case "Missouri":
                holder.ivState.setImageResource(R.drawable.missouri);
                break;

            case "Wisconsin":
                holder.ivState.setImageResource(R.drawable.wisconsin);
                break;
            case "Arizona":
                holder.ivState.setImageResource(R.drawable.arizona);
                break;
            case "South Carolina":
                holder.ivState.setImageResource(R.drawable.southcarolina);
                break;
            case "Nevada":
                holder.ivState.setImageResource(R.drawable.nevada);
                break;
            case "Alabama":
                holder.ivState.setImageResource(R.drawable.alabama);
                break;

            case "Mississippi":
                holder.ivState.setImageResource(R.drawable.mississippi);
                break;
            case "Utah":
                holder.ivState.setImageResource(R.drawable.utah);
                break;
            case "Oklahoma":
                holder.ivState.setImageResource(R.drawable.oklahoma);
                break;
            case "Idaho":
                holder.ivState.setImageResource(R.drawable.idaho);
                break;
            case "Oregon":
                holder.ivState.setImageResource(R.drawable.oregon);
                break;

            case "Kentucky":
                holder.ivState.setImageResource(R.drawable.kentucky);
                break;
            case "District Of Columbia":
                holder.ivState.setImageResource(R.drawable.districtofcolumbia);
                break;
            case "Minnesota":
                holder.ivState.setImageResource(R.drawable.minnesota);
                break;
            case "Rhode Island":
                holder.ivState.setImageResource(R.drawable.rhodeisland);
                break;
            case "Iowa":
                holder.ivState.setImageResource(R.drawable.iowa);
                break;

            case "Arkansas":
                holder.ivState.setImageResource(R.drawable.arkansas);
                break;
            case "Kansas":
                holder.ivState.setImageResource(R.drawable.kansas);
                break;
            case "Delaware":
                holder.ivState.setImageResource(R.drawable.delaware);
                break;
            case "New Mexico":
                holder.ivState.setImageResource(R.drawable.newmexico);
                break;
            case "New Hampshire":
                holder.ivState.setImageResource(R.drawable.newhampshire);
                break;

            case "Vermont":
                holder.ivState.setImageResource(R.drawable.vermont);
                break;
            case "Maine":
                holder.ivState.setImageResource(R.drawable.maine);
                break;
            case "Hawaii":
                holder.ivState.setImageResource(R.drawable.hawaii);
                break;
            case "Nebraska":
                holder.ivState.setImageResource(R.drawable.nebraska);
                break;
            case "West Virginia":
                holder.ivState.setImageResource(R.drawable.westvirginia);
                break;

            case "Montana":
                holder.ivState.setImageResource(R.drawable.montana);
                break;
            case "South Dakota":
                holder.ivState.setImageResource(R.drawable.southdakota);
                break;
            case "Wyoming":
                holder.ivState.setImageResource(R.drawable.wyoming);
                break;
            case "North Dakota":
                holder.ivState.setImageResource(R.drawable.northdakota);
                break;
            case "Alaska":
                holder.ivState.setImageResource(R.drawable.alaska);
                break;
            case "California":
                holder.ivState.setImageResource(R.drawable.california);
                break;
            case "Guam":
                holder.ivState.setImageResource(R.drawable.guam);
                break;
            case "Northern Mariana Islands":
                holder.ivState.setImageResource(R.drawable.northernmarianaislands);
                break;
            case "Puerto Rico":
                holder.ivState.setImageResource(R.drawable.puertorico);
                break;
            case "United States Virgin Islands":
                holder.ivState.setImageResource(R.drawable.virginislands);
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(mContext,DetailActivity.class);
                bundle.putString("NAME",mStatesArrayList.get(position).getStateName());
                bundle.putInt("TOTAL_CASE",mStatesArrayList.get(position).getStateTotalCases());
                bundle.putInt("TOTAL_DEATH",mStatesArrayList.get(position).getStateTotalDeaths());
                bundle.putInt("TODAY_CASE",mStatesArrayList.get(position).getStateTodayCases());
                bundle.putInt("TODAY_DEATH",mStatesArrayList.get(position).getStateTodayDeaths());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mStatesArrayList.size();
    }


    //Creating ViewHolde
    public class ViewHolder extends RecyclerView.ViewHolder {
        //Declaring States Recyclerview Layout
        private TextView tvStatenName;
        private TextView tvTotalCase;
        private TextView tvTotalDeath;
        private ImageView ivState;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatenName = itemView.findViewById(R.id.FAVStateName);
            tvTotalCase = itemView.findViewById(R.id.FAVStateTotalCase);
            tvTotalDeath = itemView.findViewById(R.id.FAVStateTotalDeath);
            ivState = itemView.findViewById(R.id.FAVImage);
        }
    }

    private void Message(String message){
        Toast.makeText(mContext,message,Toast.LENGTH_LONG).show();
    }

}
