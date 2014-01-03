package sgen.backup.dr.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import sgen.backup.dr.R;
import sgen.backup.dr.etc.ConsecutiveBackPressDetector;

public class MainActivity extends BaseActivity {

    private ImageView profilePhoto;
    private TextView nameText;
    private TextView infoText;
    private Button settingButton;

    private Button symptomButton;
    private Button historyButton;
    private Button alertButton;

    private ConsecutiveBackPressDetector consecutiveBackPressDetector;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getActionBar().setHomeButtonEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setTitle(getString(R.string.app_name_long));

        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
        nameText = (TextView) findViewById(R.id.name_txt);
        infoText = (TextView) findViewById(R.id.info_txt);
        settingButton = (Button) findViewById(R.id.setting_button);

        symptomButton = (Button) findViewById(R.id.symptom_button);
        historyButton = (Button) findViewById(R.id.history_button);
        alertButton = (Button) findViewById(R.id.alert_button);

        initConsecutiveBackPressDetector();
    }

    void initConsecutiveBackPressDetector() {
        consecutiveBackPressDetector = new ConsecutiveBackPressDetector();

        consecutiveBackPressDetector.setOnFirstBackPressListener(new ConsecutiveBackPressDetector.OnFirstBackPressListener() {
            @Override
            public void onPressed() {
                Toast.makeText(MainActivity.this,
                        "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        consecutiveBackPressDetector.setOnSecondBackPressListener(new ConsecutiveBackPressDetector.OnSecondBackPressListener() {
            @Override
            public void onPressed() {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        consecutiveBackPressDetector.onBackPressed();
    }
}