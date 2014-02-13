package sgen.backup.dr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import sgen.backup.dr.R;
import sgen.backup.dr.adapters.HistoryListAdapter;
import sgen.backup.dr.etc.PreferencesHelper;

public class HistoryActivity extends BaseActivity {

    private ListView listView;
    private String historyString;
    private String userString;

    private String[] histories;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history);

        getActionBar().setTitle(getString(R.string.show_prev_history));

        listView = (ListView) findViewById(R.id.list_view);

        userString = getIntent().getStringExtra("user");
        historyString = PreferencesHelper.getHistory(this);
        histories = historyString.split("/");

        HistoryListAdapter adapter = new HistoryListAdapter(this, histories);
        listView.setAdapter(adapter);

        initEvent();
    }

    private void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HistoryActivity.this, TestResultActivity.class);
                intent.putExtra("from_test", false);
                intent.putExtra("user", userString);
                intent.putExtra("json", histories[i]);
                startActivity(intent);
            }
        });
    }
}