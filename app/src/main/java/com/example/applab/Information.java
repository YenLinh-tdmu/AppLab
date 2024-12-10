package com.example.applab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Information extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextCMND;
    private RadioGroup radioGroupDegree;
    private RadioButton radioButtonIntermediate;
    private RadioButton radioButtonCollege;
    private RadioButton radioButtonUniversity;
    private CheckBox checkBoxNewspaper;
    private CheckBox checkBoxBooks;
    private CheckBox checkBoxCoding;
    private EditText editTextAdditionalInfo;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // Khởi tạo các thành phần UI
        editTextName = findViewById(R.id.editTextName);
        editTextCMND = findViewById(R.id.editTextCMND);
        radioGroupDegree = findViewById(R.id.radioGroupDegree);
        radioButtonIntermediate = findViewById(R.id.radioButtonIntermediate);
        radioButtonCollege = findViewById(R.id.radioButtonCollege);
        radioButtonUniversity = findViewById(R.id.radioButtonUniversity);
        checkBoxNewspaper = findViewById(R.id.checkBoxNewspaper);
        checkBoxBooks = findViewById(R.id.checkBoxBooks);
        checkBoxCoding = findViewById(R.id.checkBoxCoding);
        editTextAdditionalInfo = findViewById(R.id.editTextAdditionalInfo);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitInformation();
            }
        });
    }

    private void submitInformation() {
        String name = editTextName.getText().toString().trim();
        String cmnd = editTextCMND.getText().toString().trim();
        String additionalInfo = editTextAdditionalInfo.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Họ tên không được để trống.");
            editTextName.requestFocus();
            return;
        } else if (name.length() < 3) {
            editTextName.setError("Họ tên phải có ít nhất 3 kí tự.");
            editTextName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(cmnd)) {
            editTextCMND.setError("CMND không được để trống.");
            editTextCMND.requestFocus();
            return;
        } else if (cmnd.length() != 9) {
            editTextCMND.setError("CMND phải có đúng 9 chữ số.");
            editTextCMND.requestFocus();
            return;
        }

        int selectedDegreeId = radioGroupDegree.getCheckedRadioButtonId();
        if (selectedDegreeId == -1) {
            Toast.makeText(this, "Vui lòng chọn bằng cấp.", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton selectedDegree = findViewById(selectedDegreeId);
        String degree = selectedDegree.getText().toString();

        boolean isHobbySelected = checkBoxNewspaper.isChecked() || checkBoxBooks.isChecked() || checkBoxCoding.isChecked();
        if (!isHobbySelected) {
            Toast.makeText(this, "Vui lòng chọn ít nhất một sở thích.", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder hobbiesBuilder = new StringBuilder();
        if (checkBoxNewspaper.isChecked()) {
            hobbiesBuilder.append("Đọc báo-");
        }
        if (checkBoxBooks.isChecked()) {
            hobbiesBuilder.append("Đọc sách-");
        }
        if (checkBoxCoding.isChecked()) {
            hobbiesBuilder.append("Đọc coding-");
        }
        if (hobbiesBuilder.length() > 0) {
            hobbiesBuilder.setLength(hobbiesBuilder.length() - 1);
        }
        String hobbies = hobbiesBuilder.toString();

        // Xây dựng thông tin hiển thị
        StringBuilder infoBuilder = new StringBuilder();
        infoBuilder.append(name).append("\n");
        infoBuilder.append(cmnd).append("\n");
        infoBuilder.append(degree).append("\n");
        infoBuilder.append(hobbies);

        String information = infoBuilder.toString();

        // Tạo giao diện tùy chỉnh cho AlertDialog
        LinearLayout dialogLayout = new LinearLayout(this);
        dialogLayout.setOrientation(LinearLayout.VERTICAL);
        dialogLayout.setPadding(50, 40, 50, 10);
        dialogLayout.setBackgroundColor(Color.WHITE);

        // Tiêu đề "Thông tin cá nhân"
        TextView titleView = new TextView(this);
        titleView.setText("Thông tin cá nhân");
        titleView.setTextSize(20);
        titleView.setTypeface(null, Typeface.BOLD);
        titleView.setTextColor(Color.parseColor("#2196F3"));
        titleView.setPadding(0, 0, 0, 10);
        dialogLayout.addView(titleView);

        // Đường phân cách màu xanh biển
        View blueSeparator = new View(this);
        LinearLayout.LayoutParams blueSeparatorParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpToPx(this, 4)
        );
        blueSeparator.setLayoutParams(blueSeparatorParams);
        blueSeparator.setBackgroundColor(Color.parseColor("#2196F3")); // Màu xanh biển
        dialogLayout.addView(blueSeparator);

        // Thông tin cá nhân
        TextView infoView = new TextView(this);
        infoView.setText(information);
        infoView.setTextSize(16);
        infoView.setTextColor(Color.BLACK);
        infoView.setPadding(0, 10, 0, 10);
        dialogLayout.addView(infoView);

        // Đường phân cách đứt gãy màu đen
        View blackDashedSeparator1 = createDashedLine(this, Color.BLACK);
        dialogLayout.addView(blackDashedSeparator1);

        // Thông tin bổ sung
        TextView additionalInfoLabel = new TextView(this);
        additionalInfoLabel.setText("Thông tin bổ sung:");
        additionalInfoLabel.setTextSize(16);
        additionalInfoLabel.setTextColor(Color.BLACK);
        additionalInfoLabel.setPadding(0, 10, 0, 5);
        dialogLayout.addView(additionalInfoLabel);

        TextView additionalInfoView = new TextView(this);
        additionalInfoView.setText(TextUtils.isEmpty(additionalInfo) ? "Không có" : additionalInfo);
        additionalInfoView.setTextSize(16);
        additionalInfoView.setTextColor(Color.BLACK);
        additionalInfoView.setPadding(0, 0, 0, 10);
        dialogLayout.addView(additionalInfoView);

        // Đường phân cách đứt gãy màu đen
        View blackDashedSeparator2 = createDashedLine(this, Color.BLACK);
        dialogLayout.addView(blackDashedSeparator2);

        // Bọc trong ScrollView để hỗ trợ nội dung dài
        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(dialogLayout);

        // Tạo AlertDialog với view tùy chỉnh
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(scrollView)
                .setPositiveButton("Đóng", null)
                .create();

        dialog.show();

        // Tùy chỉnh nút "Đóng" sau khi dialog được hiển thị
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        if (positiveButton != null) {
            // Căn giữa nút
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT; // Căng rộng toàn bộ
            params.gravity = Gravity.CENTER;
            positiveButton.setLayoutParams(params);

            // Tô nền màu xanh biển và chữ màu đen
            positiveButton.setBackgroundColor(Color.parseColor("#2196F3")); // Nền xanh biển
            positiveButton.setTextColor(Color.BLACK); // Chữ màu đen

            // Optional: Tăng độ cao của nút nếu cần
            positiveButton.setPadding(dpToPx(this, 10), dpToPx(this, 10), dpToPx(this, 10), dpToPx(this, 10));
        }
    }

    private View createDashedLine(Context context, int color) {
        final int DASH_WIDTH = 10;
        final int DASH_GAP = 10;
        final int STROKE_WIDTH = 2;

        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setPathEffect(new android.graphics.DashPathEffect(new float[]{DASH_WIDTH, DASH_GAP}, 0));

        View dashedLine = new View(context) {
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                int width = getWidth();
                int height = getHeight();
                canvas.drawLine(0, height / 2, width, height / 2, paint);
            }
        };

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                2 // Chiều cao của đường phân cách
        );
        dashedLine.setLayoutParams(params);
        return dashedLine;
    }

    private int dpToPx(Context context, int dp) {
        float density = context.getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }
}