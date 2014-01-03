package sgen.backup.dr.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import sgen.backup.dr.R;

public class SymptomActivity extends BaseActivity {

    private ViewPager symptomPager;
    private SymptomPagerAdapter pagerAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setTitle(getString(R.string.input_symptom));

        setContentView(R.layout.activity_symptom);

        symptomPager = (ViewPager) findViewById(R.id.symptom_pager);
        pagerAdapter = new SymptomPagerAdapter(getSupportFragmentManager());
    }

    class SymptomPagerAdapter extends FragmentStatePagerAdapter {

        public SymptomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getCount() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}