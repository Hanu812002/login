package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.filament.View;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    TextInputLayout fname,uname,email,phoneno,passw;

    FirebaseDatabase fire;
    DatabaseReference refer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        fname=findViewById(R.id.fullname);
        uname=findViewById(R.id.user);
        email=findViewById(R.id.email);
        phoneno=findViewById(R.id.phone);
        passw=findViewById(R.id.pass);
    }



    public void loginbutton(android.view.View view) {
        Intent intent=new Intent(getApplicationContext(),login.class);
        startActivity(intent);
        finish();
    }
    public void registerbutton(android.view.View view) {
       String full_name=fname.getEditText().getText().toString();
        String user_name=uname.getEditText().getText().toString();
        String email_var=email.getEditText().getText().toString();
        String phone=phoneno.getEditText().getText().toString();
        String passwo=passw.getEditText().getText().toString();

        if(!full_name.isEmpty()){
            fname.setError(null);
            fname.setErrorEnabled(false);

            if(!user_name.isEmpty()){
                uname.setError(null);
                uname.setErrorEnabled(false);

                if(!email_var.isEmpty()){
                    email.setError(null);
                    email.setErrorEnabled(false);

                    if(!phone.isEmpty()){
                        phoneno.setError(null);
                        phoneno.setErrorEnabled(false);

                        if(!email_var.matches("\"\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b\"")){

                            fire=FirebaseDatabase.getInstance();
                            refer=FirebaseDatabase.getInstance().getReference("datauser");

                            String full_name_s=fname.getEditText().getText().toString();
                            String user_name_s=uname.getEditText().getText().toString();
                            String email_var_s=email.getEditText().getText().toString();
                            String phone_s=phoneno.getEditText().getText().toString();
                            String passwo_s=passw.getEditText().getText().toString();

                            storingdata storingdata=new storingdata(full_name_s,user_name_s,email_var_s,phone_s,passwo_s);
                                 refer.child(user_name_s).setValue(storingdata);

                            Toast.makeText(getApplicationContext(),"registered successfully",Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(getApplicationContext(),dasboard.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            email.setError("Invalid email");
                        }
                    }
                    else {
                        phoneno.setError("please enter phone nnumber");
                    }
                }
                else{
                    email.setError("please enter email");
                }
            }
            else {
                uname.setError("please enter username");
            }
        }
        else{
            fname.setError("Please enter full name");
        }
    }
}