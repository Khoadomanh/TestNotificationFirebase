package com.example.nagat.testnotificationfirebase.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nagat.testnotificationfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.email)
    EditText editTextEmail;
    @BindView(R.id.password)
    EditText editTextPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.login)
    public void login() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this,ChatActivity.class);
                            intent.putExtra("uID",firebaseAuth.getCurrentUser().getUid());
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Sign in Success!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Sign in Failure!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
