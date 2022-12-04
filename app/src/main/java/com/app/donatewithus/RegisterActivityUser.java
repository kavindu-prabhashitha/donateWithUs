package com.app.donatewithus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class RegisterActivityUser extends AppCompatActivity {

    Button userRegister;
    private EditText userName;
    private EditText userEmail;
    private EditText userContactNumber;
    private EditText userPassword;
    private EditText userConfirmPassword;

    private ProgressDialog loader;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        userRegister = findViewById(R.id.register_user_btn_register);
        userName = findViewById(R.id.register_user_userName);
        userEmail = findViewById(R.id.register_user_userEmail);
        userContactNumber = findViewById(R.id.register_user_userContactNumber);
        userPassword = findViewById(R.id.register_user_userPassword);
        userConfirmPassword = findViewById(R.id.register_user_userConfirmPassword);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        loader = new ProgressDialog(this);


        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_name = userName.getText().toString().trim();
                String user_email = userEmail.getText().toString().trim();
                String user_contactNo = userContactNumber.getText().toString().trim();
                String user_password = userPassword.getText().toString();
                String user_password_confirm = userConfirmPassword.getText().toString();

                if(TextUtils.isEmpty(user_name)){
                    userName.setError("UserName is Empty");
                }
                if(TextUtils.isEmpty(user_email)){
                    userEmail.setError("Email is Empty");
                }
                if(TextUtils.isEmpty(user_contactNo)){
                    userContactNumber.setError("Contact number is Empty");
                }
                if(TextUtils.isEmpty(user_password)){
                    userPassword.setError("Password is Empty");
                }
                if(TextUtils.isEmpty(user_password_confirm)){
                    userConfirmPassword.setError("Password Confirm is Empty");
                }else{
                    loader.setMessage("Registration in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    if(user_password.equals(user_password_confirm)){
                        mAuth.createUserWithEmailAndPassword(user_email,user_password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    setDocument(user_name,user_email,user_contactNo);
                                    Intent intent = new Intent(RegisterActivityUser.this,UserDashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                    loader.dismiss();
                                }
                            }
                        });
                    }else {
                        userConfirmPassword.setError("Password Mismatch !!");
                        loader.dismiss();
                    }


                }


            }
        });
    }

    public void setDocument(String name,String email,String contact){
        Map<String, Object> userDetail = new HashMap<>();
        userDetail.put("userContactNumber",contact);
        userDetail.put("userEmail",email);
        userDetail.put("userName",name);
        userDetail.put("user_id",mAuth.getUid());

        db.collection("user_details")
                .add(userDetail)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegisterActivityUser.this, "User Register Successful", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}