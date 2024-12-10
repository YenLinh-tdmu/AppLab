package com.example.applab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Interface extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);
        btnLogin	= (Button)findViewById(R.id.btnLogin);
        btnLogout	= (Button)findViewById(R.id.btnLogout);
        btnLogin.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnLogin) {
            Toast.makeText(getApplicationContext(), "Bạn đang Click vào Button Login", Toast.LENGTH_LONG).show();
        } else if (id == R.id.btnLogout) {
            Toast.makeText(getApplicationContext(), "Bạn đang Click vào Button Logout", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Button không xác định", Toast.LENGTH_SHORT).show();
        }
    }
}