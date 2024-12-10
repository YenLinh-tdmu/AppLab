package com.example.applab;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Inline extends AppCompatActivity {

    Button btnLogin, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inline);
        // lấy 2 button từ file xml
        btnLogin	=	(Button)findViewById(R.id.btnLogin);
        btnLogout	=	(Button)findViewById(R.id.btnLogout);
        // Đặt sự kiện cho từng button
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplication(), "Bạn đang Click vào Button Login", Toast.LENGTH_LONG).show();

            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplication(), "Bạn đang Click vào Button Logout", Toast.LENGTH_LONG).show();
            }
        });
    }
}