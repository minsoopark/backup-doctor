package sgen.backup.dr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import sgen.backup.dr.R;

public class HistoryListAdapter extends BaseAdapter {

    private Context context;
    private String[] histories;

    public HistoryListAdapter(Context context, String[] histories) {
        this.context = context;
        this.histories = histories;
    }

    @Override
    public int getCount() {
        return histories.length;
    }

    @Override
    public String getItem(int i) {
        return histories[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.history_list_item, null);
        }

        TextView textView = (TextView) v.findViewById(R.id.txt_history);

        try {
            JSONObject jsonObject = new JSONObject(histories[i]);

            StringBuilder builder = new StringBuilder();
            builder.append(jsonObject.getInt("year") + "년 ");
            builder.append((jsonObject.getInt("month") + 1) + "월 ");
            builder.append(jsonObject.getInt("day") + "일 진단 기록");

            textView.setText(builder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }
}
