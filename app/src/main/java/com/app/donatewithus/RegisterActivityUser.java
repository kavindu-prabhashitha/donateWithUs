package com.app.donatewithus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivityUser extends AppCompatActivity {

    Button userRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        userRegister = findViewById(R.id.register_user_btn_register);
        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivityUser.this,UserDashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}