package sgen.backup.dr.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import de.greenrobot.event.EventBus;
import sgen.backup.dr.R;
import sgen.backup.dr.etc.Events;
import sgen.backup.dr.fragments.DepartmentFragment;

import java.util.HashMap;
import java.util.Map;

public class SymptomActivity extends BaseActivity {

    private static final int MAX_PAGE = 1;

    private ViewPager symptomPager;
    private SymptomPagerAdapter pagerAdapter;

    private Map<String, Object> symptoms;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setTitle(getString(R.string.input_symptom));

        setContentView(R.layout.activity_symptom);

        symptomPager = (ViewPager) findViewById(R.id.symptom_pager);
        pagerAdapter = new SymptomPagerAdapter(getSupportFragmentManager());
        symptomPager.setAdapter(pagerAdapter);

        symptoms = new HashMap<String, Object>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    class SymptomPagerAdapter extends FragmentStatePagerAdapter {

        public SymptomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if (i == 0) {
                return new DepartmentFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }

    public void onEvent(Events.SymptomInputEvent event) {
        symptoms.put(event.key, event.value);
    }
}