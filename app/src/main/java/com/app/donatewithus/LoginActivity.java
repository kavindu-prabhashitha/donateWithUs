package com.app.donatewithus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    TextView registerTextView;
    EditText userEmail;
    EditText userPassword;


    private FirebaseAuth mAuth;
    private ProgressDialog loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.login_btn_loginButton);
        registerTextView = findViewById(R.id.loginPageQuestion);
        userEmail = findViewById(R.id.login_user_email_input);
        userPassword = findViewById(R.id.login_user_password_input);

        mAuth =FirebaseAuth.getInstance();
        loader = new ProgressDialog(this);

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    userEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    userPassword.setError("Password is required");
                    return;
                }

                else {
                    loader.setMessage("Login in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this, UserDashboardActivity.class);
                                startActivity(intent);
                                finish();
                                loader.dismiss();
                            }else {
                                String loginError = task.getException().toString();
                                Toast.makeText(LoginActivity.this,
                                        "Login failed " + loginError,
                                        Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }

            }
        });

    }




}