package com.agribyte.homemade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
EditText fullname, address, Mobile, Email,password;
RadioButton farmer ,customer;
TextView back;
Button signupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fullname = findViewById(R.id.edtname);
        address =findViewById(R.id.edtadd);
        Mobile = findViewById(R.id.edtPhone);
        Email = findViewById(R.id.edtemail);
        password =findViewById(R.id.edtpassword);
        farmer = findViewById(R.id.btnfarmer);
        customer = findViewById(R.id.btncustomer);
        back = findViewById(R.id.txthave);
        signupbtn = findViewById(R.id.btnsignup);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i3);
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = Email.getText().toString().trim();
                String userpass = password.getText().toString().trim();
                Signup(user1,userpass);
            }
        });
    }

    private void Signup(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = mAuth.getCurrentUser().getUid();
                    Toast.makeText(SignUpActivity.this, "User Created" + userId, Toast.LENGTH_SHORT).show();
                    Getset u = new Getset();
                    u.setAddress(address.getText().toString());
                    u.setEmail(Email.getText().toString());
                    u.setPassword(password.getText().toString());
                    u.setMobile(Mobile.getText().toString());
                    u.setFullname(fullname.getText().toString());
                    u.setCustomer(String.valueOf(customer.isChecked()));
                    u.setFarmer(String.valueOf(farmer.isChecked()));  DatabaseReference mdatabase= FirebaseDatabase.getInstance().getReference();
                    mdatabase.child( "Users" ).child( userId ).setValue( u );
                    if (farmer.isChecked()) {
                        // User selected "Farmer", go to Bottom_barActivity
                        Intent farmerIntent = new Intent(SignUpActivity.this, Bottom_barActivity.class);
                        startActivity(farmerIntent);
                    } else if (customer.isChecked()) {
                        // User selected "Customer", go to GoogleActivity
                        Intent customerIntent = new Intent(SignUpActivity.this, GoogleActivity.class);
                        startActivity(customerIntent);
                    }

                } else {
                    Log.e("TAG","Signup failed",task.getException());
                    Toast.makeText(SignUpActivity.this, "Error" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}