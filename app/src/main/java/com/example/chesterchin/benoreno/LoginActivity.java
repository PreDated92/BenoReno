package com.example.chesterchin.benoreno;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    // Key problems and points:
    // 1. The back button brings the MainActivity back to this activity!!
    // 2. This activity doesn't really need Username and Password fields, this is because the login method is done by hardware.
    //    However~ we could have a conventional login as a backup process? But is the data is stored on the bike safe? (Unless we keep it hashed?)
    // 3. There is a unique ID code given to each Beno bike so we're gonna add that in here.

    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText nameText = findViewById(R.id.nameText);
        final EditText passText = findViewById(R.id.passText);
        final Button btnConvLogin =  findViewById(R.id.btConventionalLogin);
        final Button btnFailLogin = findViewById(R.id.btFingerprintFailLogin);
        final Button btnPassLogin = findViewById(R.id.btFingerprintPassLogin);
        final EditText uniqueIdText = findViewById(R.id.uniqueIdText);
        final Button btnUniqueID = findViewById(R.id.btnUniqueId);

        btnConvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCredentialsCorrect = nameText.getText().toString().equals("Ken") && passText.getText().toString().equals("123");
                if(isCredentialsCorrect) {
                    Toast.makeText(mContext, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(mContext, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnFailLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText(mContext, "Fingerprint Does Not Match Any Database Fingerprints!", Toast.LENGTH_SHORT).show();
            }
        });

        btnPassLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Fingerprint Match! Welcome Xxx!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            }
        });

        btnUniqueID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUniqueIdCorrect = uniqueIdText.getText().toString().equals("123");
                if(isUniqueIdCorrect) {
                    Toast.makeText(mContext, "Correct Unique ID!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, RegisterUserActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(mContext, "Incorrect Unique ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}