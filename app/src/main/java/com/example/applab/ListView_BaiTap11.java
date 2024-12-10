package com.example.applab;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListView_BaiTap11 extends AppCompatActivity {

    private TextView textViewSelected;
    private ListView listViewNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_bai_tap11);

        // Khởi tạo các thành phần UI
        textViewSelected = findViewById(R.id.textViewSelected);
        listViewNames = findViewById(R.id.listViewNames);

        // Lấy mảng tên từ strings.xml
        String[] names = getResources().getStringArray(R.array.names_array);

        // Tạo ArrayAdapter cho ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                names
        );

        // Đặt adapter cho ListView
        listViewNames.setAdapter(adapter);

        // Xử lý sự kiện click cho ListView
        listViewNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Khôi phục màu sắc cho tất cả các mục
                for (int i = 0; i < listViewNames.getChildCount(); i++) {
                    View child = listViewNames.getChildAt(i);
                    child.setBackgroundColor(Color.TRANSPARENT); // Khôi phục màu sắc mặc định
                }
                // Đổi màu mục được chọn
                view.setBackgroundColor(Color.parseColor("#2196F3")); // Màu xanh dương

                // Hiển thị vị trí và tên trong TextView
                String selectedName = adapter.getItem(position);
                textViewSelected.setText("Position = " + position + ", Value = " + selectedName);
            }
        });
    }
}
