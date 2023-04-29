package com.example.apptrucksharing;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptrucksharing.model.User;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {
    private TextView tvUser;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // find user
        user = (User) getIntent().getSerializableExtra("User");

        //set user
        tvUser = findViewById(R.id.tvUser);

        if (user != null) {
            tvUser.setText("WELCOME " + user.getUserName());
        }
    }
}
