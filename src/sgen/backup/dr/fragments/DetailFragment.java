package sgen.backup.dr.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import de.greenrobot.event.EventBus;
import sgen.backup.dr.R;
import sgen.backup.dr.etc.Events;
import sgen.backup.dr.etc.ScreenUtil;


public class DetailFragment extends Fragment {

    public static final String KEY = "detail";

    private int[] resIds = {
            R.drawable.d_1,
            R.drawable.d_2,
            R.drawable.d_3,
            R.drawable.d_4,
            R.drawable.d_5,
            R.drawable.d_6,
            R.drawable.d_7,
            R.drawable.d_8,
            R.drawable.d_9,
            R.drawable.d_10,
            R.drawable.d_11,
            R.drawable.d_12,
            R.drawable.d_13,
            R.drawable.d_14,
            R.drawable.d_15,
            R.drawable.d_16,
            R.drawable.d_17,
            R.drawable.d_18,
            R.drawable.d_19
    };

    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        gridView = (GridView) view.findViewById(R.id.grid_view);
        gridView.setAdapter(new DetailSymptomsAdapter(getActivity(), resIds));

        initEvent();

        return view;
    }

    class DetailSymptomsAdapter extends BaseAdapter {

        private Context context;
        private int[] resIds;

        public DetailSymptomsAdapter(Context context, int[] resIds) {
            this.context = context;
            this.resIds = resIds;
        }

        @Override
        public int getCount() {
            return resIds.length;
        }

        @Override
        public Integer getItem(int i) {
            return resIds[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ImageButton button = new ImageButton(context);
            button.setLayoutParams(new AbsListView.LayoutParams(
                    ScreenUtil.toPx(context, 79),
                    ScreenUtil.toPx(context, 103)
            ));
            button.setBackgroundResource(resIds[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new Events.SymptomInputEvent(KEY, "d_" + (i + 1)));
                }
            });

            return button;
        }
    }

    private void initEvent() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }
}
