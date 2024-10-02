
package com.example.loginapp;

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword;
    private Button btnLogin;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvError = findViewById(R.id.tv_error);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
            }
        });
    }

    private void validateInput() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            tvError.setText("Please enter your username");
            tvError.setVisibility(View.VISIBLE);
            return;
        }

        if (!isValidEmail(email)) {
            tvError.setText("Invalid email format");
            tvError.setVisibility(View.VISIBLE);
            return;
        }

        if (!isValidPassword(password)) {
            tvError.setText("Password must be at least 8 characters long, include one number, one uppercase and lowercase letter");
            tvError.setVisibility(View.VISIBLE);
            return;
        }

        tvError.setVisibility(View.GONE);
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=\\S+$)" +
                ".{8,}" +
                "$");

        return PASSWORD_PATTERN.matcher(password).matches();
            }
}