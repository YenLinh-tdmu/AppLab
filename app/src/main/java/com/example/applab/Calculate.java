package com.example.applab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class Calculate extends AppCompatActivity {

    private EditText numa;
    private EditText numb;
    private TextView result;
    private DecimalFormat decimalFormat;
    Button exit; // Đã khai báo button exit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculate);

        initializeUI();
        setButtonClickListener(); // Giữ lại cho các button khác
        setWindowInsets();

        decimalFormat = new DecimalFormat("#.##");
    }

    private void initializeUI() {
        numa = findViewById(R.id.numa);
        numb = findViewById(R.id.numb);
        result = findViewById(R.id.result);
        exit = findViewById(R.id.exit); // Khởi tạo button exit
    }

    private void setButtonClickListener() {
        // Tính hiệu sử dụng Inline anonymous listener
        Button buttonDiff = findViewById(R.id.diff);
        buttonDiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateDiff(view);
            }
        });

        // Tính tích sử dụng Activity as listener
        Button buttonPro = findViewById(R.id.pro);
        buttonPro.setOnClickListener(this::calculatePro);

        // Tính GCD sử dụng Explicit listener class
        Button buttonGCD = findViewById(R.id.gcd);
        buttonGCD.setOnClickListener(new GCDClickListener());

        // Tính thương sử dụng Listener in variable
        Button buttonQuo = findViewById(R.id.quo);
        buttonQuo.setOnClickListener(new QuoClickListener());

        // Gán Listener cho button exit sử dụng Subclassing
        exit.setOnClickListener(new ExitClickListener());
    }

    private void setWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Tính tổng sử dụng OnClick trong XML
    public void calculateSum(View view) {
        String aStr = numa.getText().toString();
        String bStr = numb.getText().toString();

        if (!aStr.isEmpty() && !bStr.isEmpty()) {
            try {
                double a = Double.parseDouble(aStr);
                double b = Double.parseDouble(bStr);
                double sum = a + b;
                result.setText(decimalFormat.format(sum));
            } catch (NumberFormatException e) {
                result.setText("Vui lòng nhập số hợp lệ.");
            }
        } else {
            result.setText("Vui lòng nhập cả hai số a và b.");
        }
    }

    public void calculateDiff(View view) {
        String aStr = numa.getText().toString();
        String bStr = numb.getText().toString();

        if (!aStr.isEmpty() && !bStr.isEmpty()) {
            try {
                double a = Double.parseDouble(aStr);
                double b = Double.parseDouble(bStr);
                double diff = a - b;
                result.setText(decimalFormat.format(diff));
            } catch (NumberFormatException e) {
                result.setText("Vui lòng nhập số hợp lệ.");
            }
        } else {
            result.setText("Vui lòng nhập cả hai số a và b.");
        }
    }

    public void calculatePro(View view) {
        String aStr = numa.getText().toString();
        String bStr = numb.getText().toString();

        if (!aStr.isEmpty() && !bStr.isEmpty()) {
            try {
                double a = Double.parseDouble(aStr);
                double b = Double.parseDouble(bStr);
                double pro = a * b;
                result.setText(decimalFormat.format(pro));
            } catch (NumberFormatException e) {
                result.setText("Vui lòng nhập số hợp lệ.");
            }
        } else {
            result.setText("Vui lòng nhập cả hai số a và b.");
        }
    }

    // Tính thương sử dụng Listener in variable
    private class QuoClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            calculateQuo(view);
        }
    }

    public void calculateQuo(View view) {
        String aStr = numa.getText().toString();
        String bStr = numb.getText().toString();

        if (!aStr.isEmpty() && !bStr.isEmpty()) {
            try {
                double a = Double.parseDouble(aStr);
                double b = Double.parseDouble(bStr);
                if (b != 0) {
                    double quo = a / b;
                    result.setText(decimalFormat.format(quo));
                } else {
                    result.setText("Không thể chia cho 0.");
                }
            } catch (NumberFormatException e) {
                result.setText("Vui lòng nhập số hợp lệ.");
            }
        } else {
            result.setText("Vui lòng nhập cả hai số a và b.");
        }
    }

    // Thêm lớp listener cho GCD
    private class GCDClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            calculateGCD(view);
        }
    }

    public void calculateGCD(View view) {
        String aStr = numa.getText().toString();
        String bStr = numb.getText().toString();

        if (!aStr.isEmpty() && !bStr.isEmpty()) {
            try {
                // Chỉ nhận số nguyên cho GCD
                int a = Integer.parseInt(aStr);
                int b = Integer.parseInt(bStr);
                int gcd = findGCD(a, b);
                result.setText(String.valueOf(gcd));
            } catch (NumberFormatException e) {
                result.setText("Vui lòng nhập số nguyên hợp lệ.");
            }
        } else {
            result.setText("Vui lòng nhập cả hai số a và b.");
        }
    }

    private int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return findGCD(b, a % b);
    }

    // Thêm lớp listener cho Exit sử dụng Subclassing
    private class ExitClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish(); // Kết thúc Activity hiện tại
        }
    }
}
