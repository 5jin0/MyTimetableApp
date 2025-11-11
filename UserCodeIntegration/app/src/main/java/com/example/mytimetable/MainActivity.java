package com.example.mytimetable;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytimetable.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnAdd;
    private ScheduleAdapter adapter;
    private DBHelper dbHelper;
    private final List<Schedule> scheduleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_schedule);
        btnAdd = findViewById(R.id.btn_add);

        dbHelper = new DBHelper(this);
        adapter = new ScheduleAdapter(scheduleList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemDeleteListener(id -> {
            dbHelper.deleteSchedule(id); // DB에서 삭제
            loadSchedules();             // 목록 새로고침
        });

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddScheduleActivity.class);
            startActivity(intent);
        });
    }

    private void loadSchedules() {
        scheduleList.clear();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM schedule", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String day = cursor.getString(cursor.getColumnIndexOrThrow("day"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            String subject = cursor.getString(cursor.getColumnIndexOrThrow("subject"));
            Schedule schedule = new Schedule(id, day, time, subject);
            scheduleList.add(schedule);
        }
        cursor.close();
        db.close();

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSchedules(); // 다시 화면에 들어오면 최신 데이터 로드
    }
}
