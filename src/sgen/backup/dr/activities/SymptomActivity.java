package sgen.backup.dr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import de.greenrobot.event.EventBus;
import org.json.JSONObject;
import sgen.backup.dr.R;
import sgen.backup.dr.etc.Events;
import sgen.backup.dr.etc.JsonUtil;
import sgen.backup.dr.fragments.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SymptomActivity extends BaseActivity {

    private static final int MAX_PAGE = 5;

    private String userString;

    private ViewPager symptomPager;
    private Button btnDone;
    private SymptomPagerAdapter pagerAdapter;

    private Map<String, String> symptoms;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setTitle(getString(R.string.input_symptom));

        setContentView(R.layout.activity_symptom);

        symptomPager = (ViewPager) findViewById(R.id.symptom_pager);
        btnDone = (Button) findViewById(R.id.btn_done);
        pagerAdapter = new SymptomPagerAdapter(getSupportFragmentManager());
        symptomPager.setAdapter(pagerAdapter);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        symptoms = new HashMap<String, String>();
        symptoms.put("year", calendar.get(Calendar.YEAR) + "");
        symptoms.put("month", calendar.get(Calendar.MONTH) + "");
        symptoms.put("day", calendar.get(Calendar.DAY_OF_MONTH) + "");

        userString = getIntent().getStringExtra("user");

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (symptoms.keySet().size() < 5) {
                    return;
                }

                Intent intent = new Intent(SymptomActivity.this, TestResultActivity.class);
                intent.putExtra("from_test", true);
                intent.putExtra("user", userString);
                intent.putExtra("json", JsonUtil.getJsonStringFromMap(symptoms));
                startActivity(intent);
            }
        });
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
            } else if (i == 1) {
                return new WhereFragment();
            } else if (i == 2) {
                return new HowFragment();
            } else if (i == 3) {
                return new LevelFragment();
            } else if (i == 4) {
                return new DetailFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }

    public void onEventMainThread(Events.SymptomInputEvent event) {
        symptoms.put(event.key, event.value);
    }
}