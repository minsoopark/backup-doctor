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
import sgen.backup.dr.etc.Symptoms;


public class DepartmentFragment extends Fragment {

    private Button surgeonButton;
    private Button medicineButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_department, container, false);

        surgeonButton = (Button) view.findViewById(R.id.surgeon_button);
        medicineButton = (Button) view.findViewById(R.id.medicine_button);

        initEvent();

        return view;
    }

    private void initEvent() {
        surgeonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new Events.SymptomInputEvent("department", Symptoms.DEPARTMENT_SURGEON));
            }
        });

        medicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new Events.SymptomInputEvent("department", Symptoms.DEPARTMENT_MEDICINE));
            }
        });
    }
}
