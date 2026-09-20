package com.trinetra.personnel.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trinetra.personnel.R;
import com.trinetra.personnel.models.HistoryRecord;

import java.util.List;
import java.util.Locale;

public class HistoryAdapter
        extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final List<HistoryRecord> records;

    public HistoryAdapter(List<HistoryRecord> records) {
        this.records = records;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_history,
                        parent,
                        false
                );

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull HistoryViewHolder holder,
            int position
    ) {
        HistoryRecord record = records.get(position);

        holder.tvDate.setText(record.getDate());

        holder.tvSleep.setText(
                String.format(
                        Locale.getDefault(),
                        "Sleep: %.1f hours",
                        record.getSleepHours()
                )
        );

        holder.tvWellness.setText(
                String.format(
                        Locale.getDefault(),
                        "Sleep quality %.0f/5  •  Mood %.0f/5  •  Recovery %.0f/5",
                        record.getSleepQuality(),
                        record.getMoodScore(),
                        record.getRecoveryFeeling()
                )
        );

        holder.tvStatus.setText(
                String.format(
                        Locale.getDefault(),
                        "Stress %.0f/5  •  Fatigue %.0f/5",
                        record.getStressLevel(),
                        record.getFatigueLevel()
                )
        );

        if (record.getNote() == null
                || record.getNote().trim().isEmpty()) {

            holder.tvNote.setVisibility(View.GONE);

        } else {

            holder.tvNote.setVisibility(View.VISIBLE);

            holder.tvNote.setText(
                    "Note: " + record.getNote()
            );
        }
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public static class HistoryViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvDate;
        TextView tvSleep;
        TextView tvWellness;
        TextView tvStatus;
        TextView tvNote;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvHistoryDate);
            tvSleep = itemView.findViewById(R.id.tvHistorySleep);
            tvWellness = itemView.findViewById(R.id.tvHistoryWellness);
            tvStatus = itemView.findViewById(R.id.tvHistoryStatus);
            tvNote = itemView.findViewById(R.id.tvHistoryNote);
        }
    }
}