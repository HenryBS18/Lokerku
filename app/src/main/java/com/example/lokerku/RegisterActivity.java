package com.example.lokerku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set Theme to Light Mode
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Array
        String[] data_name = new String[10];
        String[] data_email = new String[10];
        String[] data_password = new String[10];

        // Declare
        EditText name = findViewById(R.id.nameRegister);
        EditText email = findViewById(R.id.emailRegister);
        EditText password = findViewById(R.id.passwordRegister);
        EditText passwordConfirm = findViewById(R.id.passwordConfirmationRegister);
        Button registerAccButton = findViewById(R.id.registerAccButton);

        registerAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().equals(passwordConfirm.getText().toString())) {
                    data_name[i] = name.getText().toString();
                    data_email[i] = email.getText().toString();
                    data_password[i] = password.getText().toString();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                    intent.putExtra("name_key", data_name[i]);
                    intent.putExtra("email_key", data_email[i]);
                    intent.putExtra("password_key", data_password[i]);
                    intent.putExtra("increment_key", String.valueOf(i));

                    startActivity(intent);

                    i++;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password Wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}