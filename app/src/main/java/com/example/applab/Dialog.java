package com.example.applab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void exit(View view) {
        // Inflate custom layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_dialog_custom, null); // Sử dụng tên layout chính xác

        // Khởi tạo các view trong dialog
        ImageView icon = dialogView.findViewById(R.id.dialog_icon);
        TextView title = dialogView.findViewById(R.id.dialog_title);
        TextView message = dialogView.findViewById(R.id.dialog_message);
        Button btnDisagree = dialogView.findViewById(R.id.button_disagree);
        Button btnAgree = dialogView.findViewById(R.id.button_agree);

        // Tạo AlertDialog.Builder và thiết lập view tùy chỉnh
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(dialogView);

        // Tạo AlertDialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // Hiển thị dialog trước khi thiết lập các sự kiện
        alertDialog.show();

        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);

        btnDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi nhấn "Không đồng ý"
                Toast.makeText(getApplicationContext(), "Bạn đã click vào nút không đồng ý", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi nhấn "Đồng ý"
                finish(); // Kết thúc Activity
            }
        });
    }
}