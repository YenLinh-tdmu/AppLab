package com.example.applab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Spinner_ListView extends AppCompatActivity {

    private Spinner spinnerCategories;
    private ListView listViewProducts;

    // Danh sách danh mục
    private ArrayList<String> categoryList;

    // Danh sách sản phẩm theo danh mục
    private HashMap<String, List<Product>> productMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_list_view);

        // Khởi tạo các thành phần giao diện
        spinnerCategories = findViewById(R.id.spinnerCategories);
        listViewProducts = findViewById(R.id.listViewProducts);

        // Khởi tạo danh mục
        categoryList = new ArrayList<>();
        categoryList.add("Điện thoại");
        categoryList.add("Máy tính");
        categoryList.add("Đồng hồ");

        // Khởi tạo sản phẩm cho từng danh mục
        productMap = new HashMap<>();

        // Sản phẩm cho danh mục "Điện thoại"
        List<Product> phoneList = new ArrayList<>();
        phoneList.add(new Product("iPhone 5", R.drawable.iphone5));
        phoneList.add(new Product("Nokia 1100", R.drawable.nokia_1100));
        phoneList.add(new Product("Q-Mobile", R.drawable.qmobile));

        // Sản phẩm cho danh mục "Máy tính"
        List<Product> computerList = new ArrayList<>();
        computerList.add(new Product("HTC", R.drawable.htc));
        computerList.add(new Product("Windows Phone Surface", R.drawable.surface));

        // Sản phẩm cho danh mục "Đồng hồ"
        List<Product> watchList = new ArrayList<>();
        watchList.add(new Product("Samsung S III", R.drawable.samsung_s3));

        // Đưa danh sách sản phẩm vào map
        productMap.put("Điện thoại", phoneList);
        productMap.put("Máy tính", computerList);
        productMap.put("Đồng hồ", watchList);

        // Thiết lập Adapter cho Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categoryList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(spinnerAdapter);

        // Thiết lập sự kiện chọn mục trong Spinner
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categoryList.get(position);
                loadProducts(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì
            }
        });

        // Tải sản phẩm mặc định (danh mục đầu tiên)
        loadProducts(categoryList.get(0));

        // Thiết lập sự kiện chọn mục trong ListView
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Product selectedProduct = (Product) listViewProducts.getAdapter().getItem(position);
                Toast.makeText(Spinner_ListView.this,
                        "Bạn đã chọn: " + selectedProduct.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProducts(String category) {
        List<Product> products = productMap.get(category);
        if(products == null){
            products = new ArrayList<>();
        }

        // Tạo Adapter cho ListView
        Custom_Adapter_Product adapter = new Custom_Adapter_Product(this, R.layout.list_item_product, products);
        listViewProducts.setAdapter(adapter);
    }
}