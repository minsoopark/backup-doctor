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


public class HowFragment extends Fragment {

    public static final String KEY = "how";

    private ImageButton[] buttons = new ImageButton[4];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_how, container, false);

        buttons[0] = (ImageButton) view.findViewById(R.id.h_1);
        buttons[1] = (ImageButton) view.findViewById(R.id.h_2);
        buttons[2] = (ImageButton) view.findViewById(R.id.h_3);
        buttons[3] = (ImageButton) view.findViewById(R.id.h_4);

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
