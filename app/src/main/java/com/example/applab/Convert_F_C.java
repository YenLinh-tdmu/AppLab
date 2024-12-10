package com.example.applab;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Convert_F_C extends AppCompatActivity {

    private EditText editTextFahrenheit;
    private EditText editTextCelsius;
    private Button buttonConvertToCelsius;
    private Button buttonConvertToFahrenheit;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_fc);

        // Khởi tạo các thành phần UI
        editTextFahrenheit = findViewById(R.id.editTextFahrenheit);
        editTextCelsius = findViewById(R.id.editTextCelsius);
        buttonConvertToCelsius = findViewById(R.id.buttonConvertToCelsius);
        buttonConvertToFahrenheit = findViewById(R.id.buttonConvertToFahrenheit);
        buttonClear = findViewById(R.id.buttonClear);

        // Thiết lập sự kiện cho nút Convert to Celsius
        buttonConvertToCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertToCelsius();
            }
        });

        // Thiết lập sự kiện cho nút Convert to Fahrenheit
        buttonConvertToFahrenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertToFahrenheit();
            }
        });

        // Thiết lập sự kiện cho nút Clear
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });
    }

    private void convertToCelsius() {
        String fStr = editTextFahrenheit.getText().toString().trim();

        if (!fStr.isEmpty()) {
            try {
                double fahrenheit = Double.parseDouble(fStr);
                double celsius = (fahrenheit - 32) * 5 / 9;
                String resultStr = String.format("%.2f", celsius);
                editTextCelsius.setText(resultStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng nhập giá trị số hợp lệ cho Fahrenheit.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập giá trị Fahrenheit.", Toast.LENGTH_SHORT).show();
        }
    }

    private void convertToFahrenheit() {
        String cStr = editTextCelsius.getText().toString().trim();

        if (!cStr.isEmpty()) {
            try {
                double celsius = Double.parseDouble(cStr);
                double fahrenheit = celsius * 9 / 5 + 32;
                String resultStr = String.format("%.2f", fahrenheit);
                editTextFahrenheit.setText(resultStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng nhập giá trị số hợp lệ cho Celsius.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập giá trị Celsius.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Xóa các trường nhập liệu
     */
    private void clearFields() {
        editTextFahrenheit.setText("");
        editTextCelsius.setText("");
        Toast.makeText(this, "Đã xóa các trường nhập.", Toast.LENGTH_SHORT).show();
    }
}

