package com.example.lokerku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set Theme to Light Mode
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Declare
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        String names = getIntent().getStringExtra("name_key");
        String emails = getIntent().getStringExtra("email_key");
        String passwords = getIntent().getStringExtra("password_key");
        String increment = getIntent().getStringExtra("increment_key");

        int i;

        if (increment != null) {
            i = Integer.valueOf(increment);
        }
        else {
            i = 0;
        }

        String[] data_name = new String[10];
        String[] data_email = new String[10];
        String[] data_password = new String[10];

        data_name[i] = names;
        data_email[i] = emails;
        data_password[i] = passwords;

        System.out.println("i : " + i);
        System.out.println("name : " + names);
        System.out.println("email : " + emails);
        System.out.println("password : " + passwords);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("email : " + email);
                System.out.println("password : " + password);

                if ((email.getText().toString().equals("hbintang@student.ciputra.ac.id")) && (password.getText().toString().equals("12345"))) {
                    Intent intent = new Intent(LoginActivity.this, RequestActivity.class);
                    startActivity(intent);
                }
//                else {
//                    Toast.makeText(getApplicationContext(), "Email/Password salah", Toast.LENGTH_SHORT).show();
//                }

                for (int i = 0; i < data_email.length; i++) {
                    if (email.getText().toString().equals(data_email[i])) {
                        if (password.getText().toString().equals(data_password[i])) {
                            Intent intent = new Intent(LoginActivity.this, RequestActivity.class);

                            intent.putExtra("names_key", data_name[i]);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}