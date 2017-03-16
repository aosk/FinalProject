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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

public class SignupActivity extends AppCompatActivity {




    EditText firstName ;
    EditText lastName ;
    EditText emailAddress;
    EditText password;
    EditText password2;

    Spinner groupType;
    Spinner s ;
    private String fname = "mydata";

    //File ff;

/*
    File f = new File("app/res/com.example.abdulrahmanalshaghdali.letsunite/test.txt");

    File internalStorageDir = getFilesDir();

    File fl = new File(internalStorageDir, "fl.csv");

*/
    Button signup ;
    String array[] = {"Press here to Choose group","DIT", "UCD", "DCU" , "FUCK"};
    //private FileOutputStream ff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        s = (Spinner) findViewById(R.id.groupType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array);
        s.setAdapter(adapter);




/*
        if (!f.exists()) {
            try {
                f.createNewFile();

                //Toast.makeText(SignupActivity.this, "file created",
                  //      Toast.LENGTH_LONG).show();
                Log.d("file Creted","--------------------------");


                //Log.d("file Creted","--------------------------");
            } catch (IOException e) {
                e.printStackTrace();

                Log.d("file Cant be Creted","--------------------------");


            }
        }
*/

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

                String groupTypeChecker = s.getSelectedItem().toString();




                if(nameChecker.equals("") || lastChecker.equals("") || emailChecker.equals("") || passwordChecker.equals("")
                        || password2hecker.equals("") || groupTypeChecker.equals("Press here to Choose group")
                        || !passwordChecker.equals(password2hecker)) {

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

                    }else if(password2hecker.equals("")) {

                        Toast.makeText(SignupActivity.this, "Re-Enter passwords",
                                Toast.LENGTH_LONG).show();

                    }else if(groupTypeChecker.equals("Press here to Choose group")){

                        Toast.makeText(SignupActivity.this, "Please choose your group",
                                Toast.LENGTH_LONG).show();

                    }else{

                        Toast.makeText(SignupActivity.this, "Nope!! Passwords don't Match !!",
                                Toast.LENGTH_LONG).show();

                    }


                }else{

                    try {

                        try {
                            //Wrap with ouputstream writer
                            // and read with inputstream read
                            FileOutputStream fout = openFileOutput(fname,MODE_ENABLE_WRITE_AHEAD_LOGGING);
                            Log.d("-=-=-=-=-=-=-=-=-=-= Work "," OutputStream =- =-=-=- =- =-=- =-");
                            OutputStreamWriter myWriter = new OutputStreamWriter(fout);
                            BufferedWriter myBW = new BufferedWriter(myWriter);
                            myBW.write(emailChecker);
                            myBW.newLine();
                            myBW.write(groupTypeChecker);
                            myBW.newLine();
                            myBW.close();
                            myWriter.close();
                            fout.close();
                            Toast.makeText(SignupActivity.this, "file created",
                                    Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //ff.write(groupTypeChecker.getBytes());
                        // file.write(gruopTypeChecker);
                        //file.write(emailChecker);
                        //file.close();
                        JSONObject postData = new JSONObject();
                        postData.put("firstname", nameChecker);
                        postData.put("lastname", lastChecker);
                        postData.put("email", emailChecker);
                        postData.put("password", passwordChecker);
                        postData.put("groupType", groupTypeChecker);

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
