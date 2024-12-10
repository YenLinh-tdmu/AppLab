package com.example.applab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Linear extends AppCompatActivity {
    Button btn1_linear, btn2_linear, btn3_linear, btn4_linear, btn5_linear, btn6_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_linear);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn1_linear = findViewById(R.id.btn1_linear);
        btn1_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Linear.this, Linear_Vertical.class);
                startActivity(intent1);
            }
        });

        btn2_linear = findViewById(R.id.btn2);
        btn2_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Linear.this, Linear_Horizontal.class);
                startActivity(intent2);
            }
        });

        btn3_linear = findViewById(R.id.btn3);
        btn3_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Linear.this, Horizontal_NoWeightSum.class);
                startActivity(intent3);
            }
        });

        btn4_linear = findViewById(R.id.btn4);
        btn4_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(Linear.this, Vertical_NoWeightSum.class);
                startActivity(intent4);
            }
        });

        btn5_linear = findViewById(R.id.btn5);
        btn5_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(Linear.this, Vertical_WeightSum.class);
                startActivity(intent5);
            }
        });
    }
}