package com.app.donatewithus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    Button userRegBtn;
    Button clubRegBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userRegBtn = findViewById(R.id.register_btn_userRegButton);
        clubRegBtn = findViewById(R.id.register_btn_clubRegButton);

        userRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, RegisterActivityUser.class);
                startActivity(intent);
            }
        });

        clubRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, RegisterActivityClub.class);
                startActivity(intent);
            }
        });
    }
}