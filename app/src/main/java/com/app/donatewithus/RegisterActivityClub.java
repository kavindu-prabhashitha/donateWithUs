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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivityClub extends AppCompatActivity {

    private Button registerButton ;
    private EditText clubName;
    private EditText clubCode;
    private EditText clubEmail;
    private EditText clubContactNo;
    private EditText clubLocation;
    private EditText clubPassword;
    private EditText clubConfirmPassword;

    private ProgressDialog loader;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private String club_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_club);

        registerButton = findViewById(R.id.register_club_btn_register);
        clubName = findViewById(R.id.register_club_clubName);
        clubCode = findViewById(R.id.register_club_clubCode);
        clubEmail = findViewById(R.id.register_club_clubEmail);
        clubContactNo = findViewById(R.id.register_club_clubContactNo);
        clubLocation = findViewById(R.id.register_club_clubLocation);
        clubPassword = findViewById(R.id.register_club_clubPassword);
        clubConfirmPassword = findViewById(R.id.register_club_clubConfirmPassword);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        loader = new ProgressDialog(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String club_name = clubName.getText().toString().trim();
                String club_email = clubEmail.getText().toString().trim();
                String club_code = clubCode.getText().toString().trim();
                String club_contactNo = clubContactNo.getText().toString().trim();
                String club_location = clubLocation.getText().toString().trim();
                String club_password = clubPassword.getText().toString();
                String club_password_confirm = clubConfirmPassword.getText().toString();

                if(TextUtils.isEmpty(club_name)){
                    clubName.setError("UserName is Empty");
                }
                if(TextUtils.isEmpty(club_code)){
                    club_code = "#CLUB";
                }
                if(TextUtils.isEmpty(club_email)){
                    clubEmail.setError("Email is Empty");
                }
                if(TextUtils.isEmpty(club_location)){
                    clubEmail.setError("Location is Empty");
                }
                if(TextUtils.isEmpty(club_contactNo)){
                    clubContactNo.setError("Contact number is Empty");
                }
                if(TextUtils.isEmpty(club_password)){
                    clubPassword.setError("Password is Empty");
                }
                if(TextUtils.isEmpty(club_password_confirm)){
                    clubConfirmPassword.setError("Password Confirm is Empty");
                }else{
                    loader.setMessage("Registration in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    if(club_password.equals(club_password_confirm)){
                        String finalClub_code = club_code;
                        mAuth.createUserWithEmailAndPassword(club_email,club_password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){

                                            setDocument(club_name,club_email,club_contactNo, finalClub_code,club_location);
                                            Intent intent = new Intent(RegisterActivityClub.this, ClubDashboardActivity.class);
                                            startActivity(intent);
                                            finish();
                                            loader.dismiss();
                                        }
                                    }
                                });
                    }else {
                        clubConfirmPassword.setError("Password Mismatch !!");
                        loader.dismiss();
                    }


                }



            }
        });

    }

    public void setDocument(String name,String email,String contact,String code,String location){
        Map<String, Object> clubDetail = new HashMap<>();
        clubDetail.put("clubContactNumber",contact);
        clubDetail.put("clubEmail",email);
        clubDetail.put("clubName",name);
        clubDetail.put("clubCode",code);
        clubDetail.put("clubLocation",location);
        clubDetail.put("club_id",mAuth.getUid());

        db.collection("club_details")
                .add(clubDetail)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegisterActivityClub.this, "Club Register Successful", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}