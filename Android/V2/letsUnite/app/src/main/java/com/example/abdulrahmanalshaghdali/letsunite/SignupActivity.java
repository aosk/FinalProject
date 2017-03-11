package com.example.abdulrahmanalshaghdali.letsunite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

public class SignupActivity extends AppCompatActivity {


    Spinner s ;

    EditText firstName ;
    EditText lastName ;
    EditText emailAddress;
    EditText password;
    EditText password2;

    Spinner groupType;

    Button signup ;
    String array[] = {"DIT", "UCD", "DCU" , "FUCK"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        s = (Spinner) findViewById(R.id.groupType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array);
        s.setAdapter(adapter);


        firstName = (EditText)findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        emailAddress = (EditText) findViewById(R.id.email);

        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.passwordCheck);

        groupType = (Spinner) findViewById(R.id.groupType);

        signup = (Button) findViewById(R.id.signUp);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameChecker = firstName.getText().toString();
                String lastChecker = lastName.getText().toString();
                String emailChecker = emailAddress.getText().toString();
                String passwordChecker = password.getText().toString();
                String password2hecker = password2.getText().toString();




                if(nameChecker.equals("") || lastChecker.equals("") || emailChecker.equals("") || passwordChecker.equals("") || password2hecker.equals("") ||
                        !passwordChecker.equals(password2hecker)) {

                    if(nameChecker.equals("")){

                        Toast.makeText(SignupActivity.this, "Fill all your details",
                                Toast.LENGTH_LONG).show();

                    }else if(lastChecker.equals("")){

                        Toast.makeText(SignupActivity.this, "Enter Last Name, Field is Empty",
                                Toast.LENGTH_LONG).show();

                    }else if(emailChecker.equals("")){

                        Toast.makeText(SignupActivity.this, "Enter Email Address, Field is Empty",
                                Toast.LENGTH_LONG).show();

                    }else if(passwordChecker.equals("")){

                        Toast.makeText(SignupActivity.this, "Enter passwords, Field is Empty",
                                Toast.LENGTH_LONG).show();

                    }else if(password2hecker.equals("")){

                        Toast.makeText(SignupActivity.this, "Re-Enter passwords",
                                Toast.LENGTH_LONG).show();
                    }else{

                        Toast.makeText(SignupActivity.this, "Nope!! Passwords don't Match !!",
                                Toast.LENGTH_LONG).show();

                    }


                }else{

                    try {
                        JSONObject postData = new JSONObject();
                        postData.put("firstname", nameChecker);
                        postData.put("lastname", lastChecker);
                        postData.put("email", emailChecker);
                        postData.put("password", passwordChecker);

                        String str = postData.toString();
                        String re="";


                        BackgroundTasks asyncLoad = new BackgroundTasks();
                        asyncLoad.execute("signup/", str, "POST");
                        re = asyncLoad.get(5,TimeUnit.SECONDS);
                        Log.d("HOPE ON ==============>",re);
                        if(re.equals("200")) {
                            Toast.makeText(SignupActivity.this, "Thanks for being united with us",
                                    Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(SignupActivity.this, LoginActivity.class);
                            SignupActivity.this.startActivity(myIntent);
                        }else if(re.equals("400")){
                            Toast.makeText(SignupActivity.this, "Opsss..!! we can't register your details ",
                                    Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(SignupActivity.this, "Check your internet connection dude !!",
                                    Toast.LENGTH_LONG).show();
                        }

                    } catch(JSONException e){
                        e.printStackTrace();
                    } catch(Exception e){
                        e.printStackTrace();
                    }

                }

            }

        });


    }
}
