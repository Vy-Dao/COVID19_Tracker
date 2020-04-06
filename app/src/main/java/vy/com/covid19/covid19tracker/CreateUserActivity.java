/*
Author : Vy Dao
Course : CSIS 365
Assignment: Major Project 2
Due Date : 4/5/2020
Date handed : 4/5/2020
Description: The CreateUserActivity file where it handle CreateUserActivity layout
 */
package vy.com.covid19.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateUserActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText edUsername,edPassword;
    private RadioButton rbtMale, rbtFemal;
    private TextView tvDOB, tvAge;
    private Spinner spnStates;
    private Button btnCreateAccount,btnChooseDOB;
    private CheckBox cb1, cb2;
    private boolean checkbox1, checkbox2;
    private int gender;
    private UserSQLHelper mUserSQLHelper;
    private FloatingActionButton fab;
    private SeekBar seekBar;
    private int numberFM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        mUserSQLHelper = new UserSQLHelper(this);
        edUsername = findViewById(R.id.etUsername);
        edPassword = findViewById(R.id.etPassword);
        rbtFemal = findViewById(R.id.rbtFemale);
        rbtMale = findViewById(R.id.rbtMale);
        tvDOB = findViewById(R.id.DOB);
        spnStates = findViewById(R.id.spinnerStates);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnChooseDOB = findViewById(R.id.btnChooseDOB);
        cb1 = findViewById(R.id.cbAcknowledge);
        cb2 = findViewById(R.id.cbAgreeCreateAccount);
        fab = findViewById(R.id.fltBackBtn);
        tvAge = findViewById(R.id.numberFM);
        seekBar = findViewById(R.id.seekBar2);

        gender = 0;
        //Spinner Set-up
        ArrayAdapter<CharSequence> arrayAdapterStates = ArrayAdapter.createFromResource(this, R.array.States, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapterStates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStates.setAdapter(arrayAdapterStates);
        spnStates.setSelection(0);

        btnChooseDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        //Seekbar
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAge.setText(String.valueOf(progress));
                numberFM = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message("You acknowledge of the COVID-19 case");
                    checkbox1 = true;
                }
                else {
                    message("You do not acknowledge of the COVID-19 case");
                    checkbox1 = false;
                }
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message("You agree to create account on this app");
                    checkbox2 = true;
                }
                else {
                    message("You do not agree to create account on this app");
                    checkbox2 = false;
                }
            }
        });

        rbtMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message("Your gender is: Male");
                    gender = 1;
                }
            }
        });
        rbtFemal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message("Your gender is: Female");
                    gender = 2;
                }
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkbox1){
                    message("Please check on the box to acknowledge the COVID-19 case");
                }
                else if (!checkbox2){
                    message("Please check on the box to agree to create account on this app");
                }
                else if (edUsername.getText().toString().equals("")){
                    message("Please enter username");
                }
                else if (edPassword.getText().toString().equals("")){
                    message("Please enter password");
                }
                else if (gender == 0){
                    message("Please select your gender");
                }
                else if (tvDOB.getText().toString().equals("")){
                    message("Please choose your birth day");
                }
                else {
                    AddAccount(edUsername.getText().toString(),edPassword.getText().toString(),gender,tvDOB.getText().toString(),spnStates.getSelectedItem().toString(),numberFM);
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateUserActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void message(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        tvDOB.setText(currentDateString);
    }

    public void AddAccount(String tmpUsername, String tmpPassword, int tmpGender, String tmpDOB, String tmpLocation,int tmpFM){
        String tmpStringGender;
        if (tmpGender == 1)
            tmpStringGender = "Male";
        else
            tmpStringGender = "Female";

        boolean insertNewAccount = mUserSQLHelper.addUserData(tmpUsername,tmpPassword,tmpStringGender,tmpDOB,tmpLocation,String.valueOf(tmpFM));
        if (insertNewAccount)
            message("Account has been created");
        else
            message("Something is going wrong, account can't be created");
    }
}
