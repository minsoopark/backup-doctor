package sgen.backup.dr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import de.greenrobot.event.EventBus;
import sgen.backup.dr.R;
import sgen.backup.dr.etc.Events;


public class LevelFragment extends Fragment {

    public static final String KEY = "level";

    private ImageButton[] buttons = new ImageButton[5];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level, container, false);

        buttons[0] = (ImageButton) view.findViewById(R.id.l_1);
        buttons[1] = (ImageButton) view.findViewById(R.id.l_2);
        buttons[2] = (ImageButton) view.findViewById(R.id.l_3);
        buttons[3] = (ImageButton) view.findViewById(R.id.l_4);
        buttons[4] = (ImageButton) view.findViewById(R.id.l_5);

        initEvent();

        return view;
    }

    private void initEvent() {
        for (ImageButton button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(
                            new Events.SymptomInputEvent(KEY, view.getTag().toString())
                    );
                }
            });
        }
    }
}
