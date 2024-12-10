package com.example.applab;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class Custom_ListView_Layout extends AppCompatActivity {
    private TextView textViewSelected;
    private ListView listViewLocal;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> localList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view_layout);


        // Khởi tạo các thành phần UI
        textViewSelected = findViewById(R.id.textViewSelected);
        listViewLocal = findViewById(R.id.listViewLocal);

        // Lấy mảng địa phương từ strings.xml và chuyển thành ArrayList
        String[] localArray = getResources().getStringArray(R.array.local_array);
        localList = new ArrayList<>(Arrays.asList(localArray));

        // Tạo ArrayAdapter tùy chỉnh
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, localList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Lấy view từ super
                View view = super.getView(position, convertView, parent);

                // Lấy TextView trong simple_list_item_1
                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                // Lấy nội dung hiện tại
                String currentItem = getItem(position);

                // Xác định drawable dựa trên độ dài của nội dung
                int drawableId;
                if (currentItem.length() <= 3) {
                    drawableId = R.drawable.star; // Hình ngôi sao
                } else {
                    drawableId = R.drawable.globe; // Hình địa cầu
                }

                // Lấy drawable từ resources
                Drawable drawable = ContextCompat.getDrawable(Custom_ListView_Layout.this, drawableId);
                // Đặt kích thước cho drawable (tùy chọn)
                if (drawable != null) {
                    drawable.setBounds(0, 0, 60, 60); // Điều chỉnh kích thước nếu cần
                }

                // Đặt drawable vào TextView bên trái
                textView.setCompoundDrawables(drawable, null, null, null);
                textView.setCompoundDrawablePadding(16); // Khoảng cách giữa hình ảnh và văn bản

                return view;
            }
        };

        // Đặt adapter cho ListView
        listViewLocal.setAdapter(adapter);

        // Xử lý sự kiện chọn mục trong ListView
        listViewLocal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedLocal = adapter.getItem(position);
                textViewSelected.setText(selectedLocal);

                // Đổi màu nền của mục được chọn
                // Reset màu cho tất cả các mục
                for (int i = 0; i < listViewLocal.getChildCount(); i++) {
                    View child = listViewLocal.getChildAt(i);
                    child.setBackgroundColor(Color.TRANSPARENT);
                }
                // Đổi màu cho mục được chọn
                view.setBackgroundColor(Color.parseColor("#B3E5FC")); // Màu xanh nhạt
            }
        });
    }
}