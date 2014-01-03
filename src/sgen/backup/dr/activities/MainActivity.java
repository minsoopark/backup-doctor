package sgen.backup.dr.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import sgen.backup.dr.R;

public class MainActivity extends BaseActivity {

    private ImageView profilePhoto;
    private TextView nameText;
    private TextView infoText;
    private Button settingButton;

    private Button symptomButton;
    private Button historyButton;
    private Button alertButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getActionBar().setTitle(getString(R.string.app_name_long));

        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
        nameText = (TextView) findViewById(R.id.name_txt);
        infoText = (TextView) findViewById(R.id.info_txt);
        settingButton = (Button) findViewById(R.id.setting_button);

        symptomButton = (Button) findViewById(R.id.symptom_button);
        historyButton = (Button) findViewById(R.id.history_button);
        alertButton = (Button) findViewById(R.id.alert_button);
    }
}