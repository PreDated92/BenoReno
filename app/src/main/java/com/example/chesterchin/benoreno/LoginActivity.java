package com.example.chesterchin.benoreno;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText nameText = findViewById(R.id.nameText);
        final EditText passText = findViewById(R.id.passText);
        final Button btnlogin =  findViewById(R.id.btnlogin);
        final EditText uniqueIdText = findViewById(R.id.uniqueIdText);
        final Button btnUniqueID = findViewById(R.id.btnUniqueId);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCredentialsCorrect = nameText.getText().toString().equals("Ken") && passText.getText().toString().equals("123");
                if(isCredentialsCorrect) {
                    Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUniqueID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUniqueIdCorrect = uniqueIdText.getText().toString().equals("123");
                if(isUniqueIdCorrect) {
                    Toast.makeText(getApplicationContext(), "Correct Unique ID!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Incorrect Unique ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}