package com.example.mytimetable;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddScheduleActivity extends AppCompatActivity {

    private Spinner spinnerDay;
    private EditText editTime, editSubject;
    private Button btnSave;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        spinnerDay = findViewById(R.id.spinner_day);
        editTime = findViewById(R.id.edit_time);
        editSubject = findViewById(R.id.edit_subject);
        btnSave = findViewById(R.id.btn_save);

        dbHelper = new DBHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String day = spinnerDay.getSelectedItem().toString();
                String time = editTime.getText().toString();
                String subject = editSubject.getText().toString();

                if (time.isEmpty() || subject.isEmpty()) {
                    Toast.makeText(AddScheduleActivity.this, "모든 항목을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("day", day);
                values.put("time", time);
                values.put("subject", subject);
                db.insert("schedule", null, values);
                db.close();

                Toast.makeText(AddScheduleActivity.this, "저장되었습니다!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
