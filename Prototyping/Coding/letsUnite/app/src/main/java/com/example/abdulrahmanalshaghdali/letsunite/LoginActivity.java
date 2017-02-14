package com.example.abdulrahmanalshaghdali.letsunite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity
{



    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button singinBTN ;
    private Button signupBTN ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);

        // password
        mPasswordView = (EditText) findViewById(R.id.password);

        singinBTN = (Button) findViewById(R.id.email_sign_in_button);
        singinBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                String fixedEmail = "aa";

                // Verfication for Login.
                if(email.equals(fixedEmail))
                {

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(intent);

                }else{

                   Toast.makeText(LoginActivity.this, "login failed)",
                           Toast.LENGTH_LONG).show();
                }


            }
        });


        signupBTN = (Button) findViewById(R.id.signUp);

    }
}