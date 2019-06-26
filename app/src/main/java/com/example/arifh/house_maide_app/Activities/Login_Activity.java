package com.example.arifh.house_maide_app.Activities;

import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arifh.house_maide_app.Database.DbHelper;
import com.example.arifh.house_maide_app.Model.MainApp;
import com.example.arifh.house_maide_app.Model.User;
import com.example.arifh.house_maide_app.R;

import java.util.regex.Pattern;

public class Login_Activity extends AppCompatActivity {
    private TextInputLayout textInputEmail, textInputpassword;
    private Button loginButton;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DbHelper dbHelper;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        TextRegister();

        dbHelper = new DbHelper(this);

        textInputEmail = findViewById(R.id.loginEmail);
        textInputpassword = findViewById(R.id.loginPassword);


        loginButton = findViewById(R.id.LoginButtonid);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vlidateEmail() | !validatePassword()) {
                    return;
                } else {
                    User user = dbHelper.doLogin(textInputEmail.getEditText().getText().toString().trim(), textInputpassword.getEditText().getText().toString().trim());
                    if (user != null) {
                        MainApp.getInstance().setUser(user);
                        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(Login_Activity.this, "Successfully Login", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Login_Activity.this, "User not found", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });


    }


    private boolean vlidateEmail() {
        String inputEmail = textInputEmail.getEditText().getText().toString().trim();

        if (inputEmail.isEmpty()) {
            textInputEmail.setError("Field can't be Empty");
            return false;
        } else if (!inputEmail.matches(emailPattern)) {
            textInputEmail.setError("Please enter a valid Email");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String inputEmail = textInputpassword.getEditText().getText().toString().trim();

        if (inputEmail.isEmpty()) {
            textInputpassword.setError("Field can't be Empty");
            return false;
        } else {
            textInputpassword.setError(null);
            return true;
        }
    }


    public void TextRegister() {
        TextView registerTextView = findViewById(R.id.textViewLinkRegister);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
