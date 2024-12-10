package com.example.applab;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ListView_BaiTap12 extends AppCompatActivity {

    private TextView textViewSelected;
    private ListView listViewNames;
    private EditText editTextName;
    private Button buttonAdd;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> namesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_bai_tap12);

        // Khởi tạo các thành phần UI
        textViewSelected = findViewById(R.id.textViewSelected);
        listViewNames = findViewById(R.id.listViewNames);
        editTextName = findViewById(R.id.editTextName);
        buttonAdd = findViewById(R.id.buttonAdd);

        // Lấy mảng tên từ strings.xml và chuyển đổi thành ArrayList
        String[] namesArray = getResources().getStringArray(R.array.names_array);
        namesList = new ArrayList<>(Arrays.asList(namesArray));

        // Tạo ArrayAdapter cho ListView
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                namesList
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

        // Xử lý sự kiện click cho Button "Nhập"
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newName = editTextName.getText().toString().trim();
                if (!newName.isEmpty()) {
                    // Thêm tên mới vào danh sách
                    namesList.add(newName);
                    // Thông báo cho ArrayAdapter biết dữ liệu đã thay đổi
                    adapter.notifyDataSetChanged();
                    // Xóa nội dung trong EditText
                    editTextName.setText("");
                    Toast.makeText(ListView_BaiTap12.this, "Đã thêm: " + newName, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListView_BaiTap12.this, "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
