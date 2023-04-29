package com.example.apptrucksharing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.apptrucksharing.data.UserDAO;
import com.example.apptrucksharing.data.UserDataBase;
import com.example.apptrucksharing.model.User;

public class RegisterActivity extends AppCompatActivity {

    //variables
    private UserDAO userDAO; //declare user class
    EditText name_edit, username_edit, password_edit, cfm_Password_edit, phone_edit;
    Button btn_sign_up, btn_sign_in, btn_add_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // set to screen
        btn_add_photo = findViewById(R.id.btn_add_photo);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_sign_in = findViewById(R.id.btn_sign_in);

        name_edit = findViewById(R.id.name_edit);
        username_edit = findViewById(R.id.username_edit);
        password_edit = findViewById(R.id.password_edit);
        cfm_Password_edit = findViewById(R.id.cfm_Password_edit);
        phone_edit = findViewById(R.id.phone_edit);

        // click button sign in
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sign user up & start main
                startActivity(new Intent(RegisterActivity.this, com.example.apptrucksharing.MainActivity.class));
            }
        });

        // set this user
        userDAO = Room.databaseBuilder(this, UserDataBase.class, "User").build().getUserDao();

        // click button sign up
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to string input
                String userName = username_edit.getText().toString().trim();
                String name = name_edit.getText().toString().trim();
                String password = password_edit.getText().toString().trim();
                String cfmPassword = cfm_Password_edit.getText().toString().trim();
                int phone = Integer.parseInt(phone_edit.getText().toString().trim());

                // passwords match
                if (password.equals(cfmPassword)) {
                    User user = new User(userName, name, password, phone);
                    userDAO.insert(user);
                    Intent moveToLogin = new Intent(RegisterActivity.this, com.example.apptrucksharing.MainActivity.class);
                    startActivity(moveToLogin);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Passwords don't match, try again", Toast.LENGTH_SHORT).show();
                }

                // go to sign up/ register page
                //Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            }
        });

    }
}
