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


public class DepartmentFragment extends Fragment {

    public static final String KEY = "department";

    private Button[] buttons = new Button[2];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_department, container, false);

        buttons[0] = (Button) view.findViewById(R.id.surgeon_button);
        buttons[1] = (Button) view.findViewById(R.id.medicine_button);

        initEvent();

        return view;
    }

    private void initEvent() {
        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new Events.SymptomInputEvent(KEY, view.getTag().toString()));
                }
            });
        }
    }
}
