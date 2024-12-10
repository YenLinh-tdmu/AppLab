package com.example.applab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Weekly_Task_Manager extends AppCompatActivity {

    private TextView textViewTitle, textViewTask, textViewContent, textViewDate, textViewTime, textViewTaskList;
    private EditText editTextTask, editTextContent, editTextDate, editTextTime;
    private Button buttonDate, buttonTime, buttonAddTask;
    private ListView listViewTasks;

    private ArrayList<Task> taskList;
    private Custom_Adapter_Task taskAdapter;
    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_task_manager);

        // Liên kết các thành phần giao diện
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewTask = findViewById(R.id.textViewTask);
        textViewContent = findViewById(R.id.textViewContent);
        textViewDate = findViewById(R.id.textViewDate);
        textViewTime = findViewById(R.id.textViewTime);
        textViewTaskList = findViewById(R.id.textViewTaskList);

        editTextTask = findViewById(R.id.editTextTask);
        editTextContent = findViewById(R.id.editTextContent);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);

        buttonDate = findViewById(R.id.buttonDate);
        buttonTime = findViewById(R.id.buttonTime);
        buttonAddTask = findViewById(R.id.buttonAddTask);

        listViewTasks = findViewById(R.id.listViewTasks);

        // Khởi tạo danh sách công việc
        taskList = new ArrayList<>();

        // Thiết lập Adapter cho ListView
        taskAdapter = new Custom_Adapter_Task(this, taskList);
        listViewTasks.setAdapter(taskAdapter);

        // Xử lý sự kiện nút Date
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        // Xử lý sự kiện nút Time
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        // Xử lý sự kiện nút Thêm CV
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });

        // Xử lý sự kiện khi chọn công việc trong ListView (hiển thị thông tin chi tiết)
        listViewTasks.setOnItemClickListener((adapterView, view, position, l) -> {
            Task selectedTask = taskList.get(position);
            Toast.makeText(Weekly_Task_Manager.this,
                    "Công việc: " + selectedTask.getTaskName() + "\nNội dung: " + selectedTask.getContent() +
                            "\nNgày HT: " + selectedTask.getDate() + "\nGiờ HT: " + selectedTask.getTime(),
                    Toast.LENGTH_LONG).show();
        });
    }

    // Hàm hiển thị DatePickerDialog
    private void showDatePicker() {
        // Lấy thời gian hiện tại
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Lưu ý: tháng bắt đầu từ 0
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(Weekly_Task_Manager.this,
                (datePicker, selectedYear, selectedMonth, selectedDay) -> {
                    selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    editTextDate.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

    // Hàm hiển thị TimePickerDialog với định dạng 12 giờ (AM/PM)
    private void showTimePicker() {
        // Lấy thời gian hiện tại
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Tạo TimePickerDialog với định dạng 12 giờ
        TimePickerDialog timePickerDialog = new TimePickerDialog(Weekly_Task_Manager.this,
                (timePicker, selectedHour, selectedMinute) -> {
                    Calendar selectedCal = Calendar.getInstance();
                    selectedCal.set(Calendar.HOUR_OF_DAY, selectedHour);
                    selectedCal.set(Calendar.MINUTE, selectedMinute);
                    String formattedTime = android.text.format.DateFormat.format("hh:mm a", selectedCal).toString();
                    selectedTime = formattedTime;
                    editTextTime.setText(selectedTime);
                }, hour, minute, false); // false để sử dụng định dạng 12 giờ

        timePickerDialog.show();
    }

    // Hàm thêm công việc vào ListView
    private void addTask() {
        String taskName = editTextTask.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();

        // Kiểm tra dữ liệu nhập
        if (taskName.isEmpty()) {
            editTextTask.setError("Vui lòng nhập công việc");
            editTextTask.requestFocus();
            return;
        }

        if (content.isEmpty()) {
            editTextContent.setError("Vui lòng nhập nội dung");
            editTextContent.requestFocus();
            return;
        }

        if (date.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn ngày hoàn thành", Toast.LENGTH_SHORT).show();
            return;
        }

        if (time.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn giờ hoàn thành", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo đối tượng Task và thêm vào danh sách
        Task newTask = new Task(taskName, content, date, time);
        taskList.add(newTask);

        // Cập nhật Adapter
        taskAdapter.notifyDataSetChanged();

        // Làm mới các trường nhập liệu
        editTextTask.setText("");
        editTextContent.setText("");
        editTextDate.setText("");
        editTextTime.setText("");
        selectedDate = "";
        selectedTime = "";

        Toast.makeText(this, "Thêm công việc thành công!", Toast.LENGTH_SHORT).show();
    }
}
