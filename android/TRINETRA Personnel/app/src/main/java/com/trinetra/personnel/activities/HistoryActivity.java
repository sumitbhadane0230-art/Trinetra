package com.trinetra.personnel.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trinetra.personnel.R;
import com.trinetra.personnel.adapters.HistoryAdapter;
import com.trinetra.personnel.models.HistoryRecord;
import com.trinetra.personnel.repository.HistoryRepository;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerHistory;
    private View tvHistoryEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerHistory = findViewById(R.id.recyclerHistory);
        tvHistoryEmpty = findViewById(R.id.tvHistoryEmpty);

        recyclerHistory.setLayoutManager(
                new LinearLayoutManager(this)
        );

        loadHistory();
    }

    private void loadHistory() {

        HistoryRepository repository =
                new HistoryRepository(this);

        List<HistoryRecord> records =
                repository.getRecords();

        HistoryAdapter adapter =
                new HistoryAdapter(records);

        recyclerHistory.setAdapter(adapter);

        updateEmptyState(records.isEmpty());
    }

    private void updateEmptyState(boolean empty) {

        if (empty) {
            recyclerHistory.setVisibility(View.GONE);
            tvHistoryEmpty.setVisibility(View.VISIBLE);
        } else {
            recyclerHistory.setVisibility(View.VISIBLE);
            tvHistoryEmpty.setVisibility(View.GONE);
        }
    }
}