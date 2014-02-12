package sgen.backup.dr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import de.greenrobot.event.EventBus;
import sgen.backup.dr.R;
import sgen.backup.dr.etc.Events;


public class WhereFragment extends Fragment {

    public static final String KEY = "where";

    private Button[] buttons = new Button[11];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_where, container, false);

        buttons[0] = (Button) view.findViewById(R.id.head);
        buttons[1] = (Button) view.findViewById(R.id.shoulder);
        buttons[2] = (Button) view.findViewById(R.id.elbow);
        buttons[3] = (Button) view.findViewById(R.id.wrist);
        buttons[4] = (Button) view.findViewById(R.id.chest);
        buttons[5] = (Button) view.findViewById(R.id.stomach);
        buttons[6] = (Button) view.findViewById(R.id.thigh);
        buttons[7] = (Button) view.findViewById(R.id.calf);
        buttons[8] = (Button) view.findViewById(R.id.knee);
        buttons[9] = (Button) view.findViewById(R.id.ankle);
        buttons[10] = (Button) view.findViewById(R.id.foot);

        initEvent();

        return view;
    }

    private void initEvent() {
        for (Button button : buttons) {
            EventBus.getDefault().post(new Events.SymptomInputEvent(KEY, button.getTag().toString()));
        }
    }
}
