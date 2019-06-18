package com.example.githubapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameInput;
     Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameInput = findViewById(R.id.input_username);
        loginButton = findViewById(R.id.login);
    }

    public void getUser(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("USERNAME", usernameInput.getText().toString());
        startActivity(intent);
    }
}
