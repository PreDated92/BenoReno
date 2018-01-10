package com.example.chesterchin.benoreno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterUserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        PopulateLanguageDropDown();
        HookButtonEventHandler();
    }

    private void PopulateLanguageDropDown() {
        final Spinner sLanguage = findViewById(R.id.sLanguage);
        final String[] languages = {"English", "Blah", "Blah Blah"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sLanguage.setAdapter(adapter);
    }

    private void HookButtonEventHandler() {
        final Button btnRegisterNext = findViewById(R.id.btnRegisterNext);
        btnRegisterNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText registerNameText = findViewById(R.id.registerNameText);
                registerNameText.getText().toString();

                // Code to commit/hold name to hardware.
                // Here error checking needs to be done with the hardware to see whether the registered name is taken, is valid etc.
                // boolean isValidName = BA.SendSerial(API: Name)

                boolean isValidName = true;
                if (isValidName) {
                    Toast.makeText(getApplicationContext(), "Name is valid!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterUserActivity.this, RegisterFingerprintActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry, that name has been taken! Please choose another", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
