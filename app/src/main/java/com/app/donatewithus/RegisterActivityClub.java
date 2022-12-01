package com.app.donatewithus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivityClub extends AppCompatActivity {

    Button registerButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_club);

        registerButton = findViewById(R.id.register_club_btn_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivityClub.this, ClubDashboardActivity.class);
                startActivity(intent);
            }
        });

    }
}