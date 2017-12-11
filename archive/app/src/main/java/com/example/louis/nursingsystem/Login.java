package com.example.louis.nursingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Louis on 11/25/2015.
 */
public class Login extends Activity {

    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText Nametext = (EditText)findViewById(R.id.Nametext);
        final EditText Psstext = (EditText)findViewById(R.id.Psstext);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Nametext.getText().toString().equals("Ken") &&
                        Psstext.getText().toString().equals("123")) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
