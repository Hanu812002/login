package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
  Button sign,login;
  TextInputLayout user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sign =findViewById(R.id.signupbutton);
        login=findViewById(R.id.loginbutton);

         user=findViewById(R.id.box1);
         pass=findViewById(R.id.box2);

         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String name=user.getEditText().getText().toString();
                 String pas=pass.getEditText().getText().toString();

                 if(!name.isEmpty()){

                     user.setError(null);
                     user.setErrorEnabled(false);
                     if(!pas.isEmpty()){
                           pass.setError(null);
                           pass.setErrorEnabled(false);

                           final String usern=user.getEditText().getText().toString();
                           final String pasw=pass.getEditText().getText().toString();

                         FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                         DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("datauser");

                         Query c_uname=databaseReference.orderByChild("uname").equalTo(usern);

                         c_uname.addListenerForSingleValueEvent(new ValueEventListener() {
                             @Override
                             public void onDataChange(@NonNull DataSnapshot snapshot) {
                                 if(snapshot.exists()){
                                     user.setError(null);
                                     user.setErrorEnabled(false);
                                     String p_check=snapshot.child(usern).child("pass").getValue(String.class);
                                     Log.e("GivenPass + FetchedPass",pasw+" "+p_check+" "+usern);
                                     Toast.makeText(getApplicationContext(),pasw+" "+p_check+" "+usern,Toast.LENGTH_SHORT).show();
                                     if(p_check.equals(pasw)){
                                       pass.setError(null);
                                       pass.setErrorEnabled(false);

                                         Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_SHORT).show();
                                         Intent intent= new Intent(getApplicationContext(),dasboard.class);
                                         startActivity(intent);
                                     }
                                     else{
                                         pass.setError("wrong password");
                                     }
                                 }
                                 else{
                                     user.setError("User does not exixt");
                                 }
                             }

                             @Override
                             public void onCancelled(@NonNull DatabaseError error) {

                             }
                         });

                     }
                     else{
                       pass.setError("Please enter password");
                     }
                 }
                 else{
                     user.setError("Please enter name");
                 }
             }
         });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),signup.class);
                startActivity(intent);
                finish();
            }
        });

    }
}