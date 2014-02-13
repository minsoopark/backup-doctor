package sgen.backup.dr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import sgen.backup.dr.R;
import sgen.backup.dr.etc.ConsecutiveBackPressDetector;

public class MainActivity extends BaseActivity {

    private String userString;

    private TextView nameText;
    private TextView infoText;

    private Button symptomButton;
    private Button historyButton;

    private ConsecutiveBackPressDetector consecutiveBackPressDetector;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getActionBar().setHomeButtonEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setTitle(getString(R.string.app_name_long));

        nameText = (TextView) findViewById(R.id.name_txt);
        infoText = (TextView) findViewById(R.id.info_txt);

        symptomButton = (Button) findViewById(R.id.symptom_button);
        historyButton = (Button) findViewById(R.id.history_button);

        userString = getIntent().getStringExtra("user");
        try {
            JSONObject jsonObject = new JSONObject(userString);
            String gender = jsonObject.getString("gender").equals("male")?"남":"여";
            boolean smoking = jsonObject.getString("smoking").equals("Y");
            boolean drinking = jsonObject.getString("drinking").equals("Y");
            nameText.setText(jsonObject.getString("name") + " (" + gender + ")");
            infoText.setText(
                    jsonObject.getString("birth") + "년생" + " / " +
                            jsonObject.getInt("height") + "cm" + " / " +
                            jsonObject.getInt("weight") + "kg" + " / " +
                            jsonObject.getString("blood") + "형"
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initConsecutiveBackPressDetector();

        initEvent();
    }

    private void initEvent() {
        symptomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SymptomActivity.class);
                intent.putExtra("user", userString);
                startActivity(intent);
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                intent.putExtra("user", userString);
                startActivity(intent);
            }
        });
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