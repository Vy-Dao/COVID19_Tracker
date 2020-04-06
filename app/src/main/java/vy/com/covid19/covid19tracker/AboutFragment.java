/*
Author : Vy Dao
Course : CSIS 365
Assignment: Major Project 2
Due Date : 4/5/2020
Date handed : 4/5/2020
Description: The AboutFragment file where it handle about fragment layout
 */
package vy.com.covid19.covid19tracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    private AccountInfo account_info;
    private EditText edNewPW, edConfirmPW;
    private TextView tvFamily, tvName;
    private SeekBar seekBar;
    private Button btnChangeAccount;
    private int newFM;
    public AboutFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        tvName = view.findViewById(R.id.usernamelable);
        tvFamily = view.findViewById(R.id.tvAge);
        edConfirmPW = view.findViewById(R.id.etConfirm);
        edNewPW = view.findViewById(R.id.etNewPw);
        seekBar = view.findViewById(R.id.seekBar);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvFamily.setText(String.valueOf(progress));
                newFM = Integer.parseInt(tvFamily.getText().toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        btnChangeAccount = view.findViewById(R.id.btnChagneInformation);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            account_info = bundle.getParcelable("ACC_INFO");
        }

        tvName.setText(account_info.getUsername());
        tvFamily.setText(account_info.getFamilyMember());

        btnChangeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edNewPW.getText().toString().equals("") && edConfirmPW.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"There is nothing to change",Toast.LENGTH_LONG).show();
                }
                else if (edNewPW.getText().toString().equals(edConfirmPW.getText().toString())){
                    SQLiteDatabase db = new UserSQLHelper(getActivity()).getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(UserSQLHelper.USER_USERNAME,account_info.getUsername());
                    contentValues.put(UserSQLHelper.USER_PASSWORD,edNewPW.getText().toString());
                    contentValues.put(UserSQLHelper.USER_GENDER,account_info.getGender());
                    contentValues.put(UserSQLHelper.USER_BIRTHDAY,account_info.getDOB());
                    contentValues.put(UserSQLHelper.USER_STATE,account_info.getState());
                    contentValues.put(UserSQLHelper.USER_FAMILY_MEMBER,String.valueOf(newFM));
                    db.update(UserSQLHelper.TABLE_NAME,contentValues,"ID="+account_info.getAccountID(),null);
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(),"Account has changed. Please login again",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(),"Passwords are not matching",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
