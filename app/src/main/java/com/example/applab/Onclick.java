package com.example.applab;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Onclick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onclick);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void clickMe(View v){
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
