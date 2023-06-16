package com.example.grimoire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {

    public TextInputEditText editTextEmail, editTextPassword, conTextPassword;
    public Button regButton;

    FirebaseAuth mAuth;
    ProgressBar bar;
    TextView textView;

    /* old
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            //per the assessment criteria, signup requires you to log in again
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email_reg);
        editTextPassword = findViewById(R.id.password_reg);
        conTextPassword = findViewById(R.id.conf_reg);
        regButton = findViewById(R.id.register_button);
        bar = findViewById(R.id.progress_register);

        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }});


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email;
                String password;
                String confirm;

                bar.setVisibility(View.VISIBLE);

                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                confirm = conTextPassword.getText().toString();

                //validation
                //check if fields are filled
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Please enter an email", Toast.LENGTH_SHORT).show();
                    bar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                    bar.setVisibility(View.GONE);
                    return;
                }

                //other validations, self explanatory
                if (!password.equals(confirm)){
                    Toast.makeText(Register.this, "Please enter matching passwords", Toast.LENGTH_SHORT).show();
                    bar.setVisibility(View.GONE);
                    return;
                }
                if (!email.contains("@")){
                    Toast.makeText(Register.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    bar.setVisibility(View.GONE);
                    return;
                }

                //create firebase account
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                bar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Account created!",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


                //create user entry in db


            }
                });


    }
}