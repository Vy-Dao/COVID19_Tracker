/*
Author : Vy Dao
Course : CSIS 365
Assignment: Major Project 2
Due Date : 4/5/2020
Date handed : 4/5/2020
Description: The MainActivity file where it handle login layout
 */

package vy.com.covid19.covid19tracker;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnCreate;
    private UserSQLHelper mUserSQLHelper;
    private TextView tvLoginUser,tvLoginPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreate = findViewById(R.id.btnCreateAccount);
        mUserSQLHelper = new UserSQLHelper(this);
        tvLoginPass =findViewById(R.id.tvLoginPassword);
        tvLoginUser = findViewById(R.id.tvLoginUsername);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvLoginPass.getText().equals("")){
                    message("Please enter your password");
                }
                else if (tvLoginUser.getText().equals("")){
                    message("Please enter your username");
                }
                else
                {
                    String tmpUsername = tvLoginUser.getText().toString();
                    String tmpPassword = tvLoginPass.getText().toString();
                    SQLiteDatabase db =new UserSQLHelper(MainActivity.this).getReadableDatabase();
                    Cursor cursor = db.rawQuery("SELECT * FROM UserTable",null);
                    ArrayList<AccountInfo> accountInfoArrayList = new ArrayList<>();
                    while (cursor.moveToNext()){
                        accountInfoArrayList.add(new AccountInfo(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
                    }
                    cursor.close();
                    boolean check = false;
                    for (int i = 0; i < accountInfoArrayList.size(); i ++){
                        if(accountInfoArrayList.get(i).getUsername().equals(tmpUsername) && accountInfoArrayList.get(i).getPassword().equals(tmpPassword))
                        {
                            ArrayList<AccountInfo> tmpArray = new ArrayList<>();
                            AccountInfo tmpAccountInfo = accountInfoArrayList.get(i);
                            check = true;
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("ACCOUNT_INFO",tmpAccountInfo);
                            Intent intent = new Intent(MainActivity.this, UserActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    if(!check)
                        message("Please re-check your username or password");
                }
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CreateUserActivity.class);
                startActivity(intent);
            }
        });
    }

    public void message(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
