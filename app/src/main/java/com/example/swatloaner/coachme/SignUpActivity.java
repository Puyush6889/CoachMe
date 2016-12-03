package com.example.swatloaner.coachme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText email;
    EditText password;
    EditText confirm_password;
    EditText nickName;

    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(this);


        email = (EditText) findViewById(R.id.email);
        nickName = (EditText) findViewById(R.id.nickName);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
    }

    /*
        Sign up method will check the database for duplicates and add the user to it if no duplicates
     */
    private void signUp(){

        String name = nickName.getText().toString();
        String userEmail = email.getText().toString();
        UserDataBase checkUsers = new UserDataBase();
        for(User user: checkUsers.getUsersDatabase().values())
        {
            if ( userEmail.equals(user.getEmail()))
            {
                Context context = getApplicationContext();
                CharSequence text = "Email in use, Please enter a different one";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        Integer userPassword = Integer.parseInt(password.getText().toString());
        Integer userConfirm_Password = Integer.parseInt(confirm_password.getText().toString());

        User user = new User(name, userEmail, userConfirm_Password);
        UserDataBase newUsers = new UserDataBase();
        HashMap<String, User> addingUsers = new HashMap<>();
        addingUsers.put(name, user);
        newUsers.setUsersDatabase(addingUsers);
        newUsers.fillUserDatabase();
        for ( User user1: newUsers.getUsersDatabase().values())
        {
            System.out.println(user1.getName()+ user1.getEmail());
        }
        System.out.println("The Hasmap Users");
        Intent intent = new Intent(getApplicationContext(), MainLogin.class);
        startActivity(intent);
        //pull values from edit ext views

        //pass them to search function hashtable

        //create a new user object and add it to the table
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == signup.getId()){
            signUp();
        }
    }
}
