package com.example.apptrucksharing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptrucksharing.data.UserDAO;
import com.example.apptrucksharing.data.UserDataBase;
import com.example.apptrucksharing.model.User;
import com.example.apptrucksharing.data.UserDAO;
import com.example.apptrucksharing.data.UserDataBase;
import com.example.apptrucksharing.model.User;

public class MainActivity extends AppCompatActivity {

    // variables
    UserDAO db; //declare user class
    UserDataBase dataBase; //declare database
    Button btn_sign_up, btn_sign_in;
    TextView textSlogan;
    EditText username_input, password_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set to screen
        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        username_input = findViewById(R.id.username_input);
        password_input = findViewById(R.id.password_input);

        // database build with class
        dataBase = Room.databaseBuilder(this, UserDataBase.class, "User")
                .allowMainThreadQueries()
                .build();

        // set db user data database
        db = dataBase.getUserDao();

        // click button sign in
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to string input
                String userName = username_input.getText().toString().trim();
                String password = password_input.getText().toString().trim();

                // get data from user class
                User user = db.getUser(userName, password);

                // check user exists
                if (user != null) {
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    i.putExtra("User", userName);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this, "Unknown User, try again or Sign up", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // click button sign up
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to sign up/ register page
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}