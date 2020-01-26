package com.example.a4_datavalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {


    private EditText editTextusername;
    private EditText editTextemail;
    private EditText editTextpassword;
    private EditText editTextnumber;
    private Button buttonsignup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextusername=findViewById(R.id.user);
        editTextemail=findViewById(R.id.email);
        editTextpassword=findViewById(R.id.password);
        editTextnumber=findViewById(R.id.phone);
        buttonsignup=findViewById(R.id.signup);

        editTextusername.addTextChangedListener(SignupTextWatcher);
        editTextemail.addTextChangedListener(SignupTextWatcher);
        editTextpassword.addTextChangedListener(SignupTextWatcher);
        editTextnumber.addTextChangedListener(SignupTextWatcher);

    }

    private TextWatcher SignupTextWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String usernameInput = editTextusername.getText().toString().trim();
            String emailInput = editTextemail.getText().toString().trim();
            String passwordInput = editTextpassword.getText().toString().trim();
            String phoneInput = editTextnumber.getText().toString().trim();
            buttonsignup.setEnabled(!usernameInput.isEmpty() && !emailInput.isEmpty() &&!passwordInput.isEmpty() && !phoneInput.isEmpty());
        }


        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    //public void SignUp(View view) {
    //Toast.makeText(this,"Sign Up Successfully",Toast.LENGTH_LONG).show();
//<!--android:onClick="SignUp" -->
    public void Login(View view){
        Intent switchLogin=new Intent(this,LoginnActivity.class);
        startActivity(switchLogin);
    }


    //////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    private static final Pattern PASSWORD_PATTERN=Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{6,12}" +               //at least 6 characters and max 12 characters
            "$");


    public void redirectLogin(View view) {

        if (validateEmail()&& passwordValidation() && nameValidation() && phoneValidation()){
            Intent LoginnActivity =new Intent(MainActivity.this,LoginnActivity.class);
            LoginnActivity.putExtra("editTextemail",validateEmail());
            LoginnActivity.putExtra("editTextpassword",passwordValidation());
            startActivity(LoginnActivity);



        }
    }
    private boolean validateEmail(){

        String emailInput=editTextemail.getText().toString().trim();
        if (emailInput.isEmpty()){


            editTextemail.setError("Email Field Is Empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            editTextemail.setError("Please Enter A Valid Email Address");
            return false;
        }
        else {

            editTextemail.setError(null);
            return true;
        }


    }


    private boolean passwordValidation(){

        String passwordInput=editTextpassword.getText().toString().trim();
        if (passwordInput.isEmpty()){

            editTextpassword.setError("Password Can't Be Empty");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){

            editTextpassword.setError("Password must contains 1 Uppercase,1 Lowercase," +
                    "1 Digit,1 Special Character,No whitespaces");
            return false;
        }
        else {
            editTextpassword.setError(null);
            return true;
        }

    }


    private boolean nameValidation(){

        String nameInput=editTextusername.getText().toString().trim();
        if (nameInput.isEmpty()){

            editTextusername.setError("Please Enter Your Name");
            return false;
        }
        else
        {
            return true;
        }

    }


    private boolean phoneValidation(){

        String phoneInput=editTextnumber.getText().toString().trim();
        if (phoneInput.isEmpty()){

            editTextnumber.setError("Please Enter Your Number");
            return false;
        }
        else if (phoneInput.length()>12){

            editTextnumber.setError("Please Enter A Valid Contact Number");
            return false;
        }
        else
        {
            return true;
        }}




}




