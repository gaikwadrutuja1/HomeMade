package com.agribyte.homemade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
EditText Username, Password;
TextView newAcc;
Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = findViewById(R.id.edtuser);
        Password = findViewById(R.id.edtpass);
        newAcc = findViewById(R.id.txtnew);
        btnlogin = findViewById(R.id.btnlogin);
        newAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String user =Username.getText().toString();
             String passw = Password.getText().toString();
             signInWithEmail(user,passw);
            }
        });

    }

    private void signInWithEmail(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful())
{
    String uid = mAuth.getCurrentUser().getUid();
    Toast.makeText(LoginActivity.this, "Login Succesful" +uid, Toast.LENGTH_SHORT).show();
    Intent i2 = new Intent(LoginActivity.this,Bottom_barActivity.class);
    startActivity(i2);
}
else {
    Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
}
            }
        });
    }
}