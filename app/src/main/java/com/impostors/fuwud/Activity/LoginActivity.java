package com.impostors.fuwud.Activity;

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
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.impostors.fuwud.Model.User;
import com.impostors.fuwud.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    private EditText editTextEmail,editTextPassword;
    private Button buttonLogin;
    private Switch switchRemember;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    public void init() {
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);
        buttonLogin =  findViewById(R.id.buttonLogin);
        switchRemember =  findViewById(R.id.switchRemember);
    }

    public void loginMethod(View view) throws IOException {

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Giriş yapılıyor...");
        progressDialog.setCancelable(false);
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (switchRemember.isChecked()){ // Add email To Storage
            //addToSharedPreferences(email);//Remember email
        }else{  // Clear Storage
            //ClearStorage(getSharedPreferences("emailPref", MODE_PRIVATE), "", false);
        }

        //Password Combination Controls
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Email Alanı Boş Bırakılamaz!");
            if (TextUtils.isEmpty(password)) {
                editTextPassword.setError("Şifre Alanı Boş Bırakılamaz!");
            }
            return;
        }
        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Şifre Alanı Boş Bırakılamaz!");
            return;
        }
        if (password.length() < 6 || password.length() > 20) {
            editTextPassword.setError("Şifreniz 6 ile 20 karakter arasında olmalıdır!");
            editTextPassword.setText("");
            return;
        }
        boolean lowerCase = false, upperCase = false, digit = false;
        for (int i = 0; i < password.length(); i++) {
            char charachter = password.charAt(i);
            if (Character.isUpperCase(charachter)) upperCase = true;
            if (Character.isLowerCase(charachter)) lowerCase = true;
            if (Character.isDigit(charachter)) digit = true;
        }
        if (lowerCase == false || upperCase == false || digit == false) {
            editTextPassword.setError("Şifreniz en az 1 büyük ve küçük harf ve sayı içermelidir!");
            editTextPassword.setText("");
            return;
        } else {
            //Database istek kontrolü
            FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = myDatabase.getReference("users");

            Query sorgu = myRef.orderByChild("email").equalTo(R.id.editTextEmail);
            sorgu.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot d:dataSnapshot.getChildren()){
                        User kisi=d.getValue(User.class);
                        String key=d.getKey();

                        kisi.setKey(key);
                        Log.e("kisi.key",kisi.getKey());

                        Map<String,Object> updateInfo = new HashMap<>();
                        updateInfo.put("key", kisi.getKey());

                        FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = myDatabase.getReference("users");
                        myRef.child(key).updateChildren(updateInfo);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            Intent next=new Intent(LoginActivity.this, SplashScreenActivity.class);
            startActivity(next);


        }
        progressDialog.show();
    }

}