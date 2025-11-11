package com.example.mytimetable;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytimetable.model.Schedule;
import com.example.mytimetable.R;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<Schedule> scheduleList;
    private OnItemDeleteListener onItemDeleteListener;

    public ScheduleAdapter(List<Schedule> list) {
        this.scheduleList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView day, time, subject;
        public ViewHolder(View view) {
            super(view);
            day = view.findViewById(R.id.text_day);
            time = view.findViewById(R.id.text_time);
            subject = view.findViewById(R.id.text_subject);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        holder.day.setText(schedule.getDay());
        holder.time.setText(schedule.getTime());
        holder.subject.setText(schedule.getSubject());

        // 롱클릭 시 삭제 다이얼로그
        holder.itemView.setOnLongClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
            builder.setTitle("삭제 확인")
                    .setMessage("이 시간표를 삭제할까요?")
                    .setPositiveButton("삭제", (dialog, which) -> {
                        if (onItemDeleteListener != null) {
                            onItemDeleteListener.onDelete(schedule.getId());
                        }
                    })
                    .setNegativeButton("취소", null)
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    // 인터페이스 정의
    public interface OnItemDeleteListener {
        void onDelete(int id);
    }

    // 리스너 등록 메서드
    public void setOnItemDeleteListener(OnItemDeleteListener listener) {
        this.onItemDeleteListener = listener;
    }
}
