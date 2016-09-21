package com.cab.alington.cab.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.cab.alington.cab.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sharedPreferences;
    EditText etphone,etpw,etmail;
    Button btlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        sharedPreferences= this.getSharedPreferences("login", MODE_PRIVATE);
        etphone=(EditText)findViewById(R.id.phone);
        etmail=(EditText)findViewById(R.id.user);
        etpw=(EditText)findViewById(R.id.password);
        btlogin=(Button)findViewById(R.id.login);
        btlogin.setOnClickListener(this);
        if(sharedPreferences.getBoolean("driver",false)){
            Log.e("app","app");
            etphone.setVisibility(View.GONE);
        }else if( sharedPreferences.getBoolean("user",false)){
            etphone.setVisibility(View.VISIBLE);
        }


      //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.login){
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }
    }
}
