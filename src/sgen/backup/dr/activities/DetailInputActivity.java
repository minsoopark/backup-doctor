package sgen.backup.dr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import sgen.backup.dr.R;

public class DetailInputActivity extends BaseActivity {

    private EditText nameField;
    private EditText ageField;
    private EditText heightField;
    private EditText weightField;
    private EditText bloodField;

    private Button maleButton;
    private Button femaleButton;

    private Button sYesButton;
    private Button sNoButton;

    private Button dYesButton;
    private Button dNoButton;

    private EditText cDiseaseField;
    private EditText drugField;
    private EditText alergyField;

    private Button doneButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_info_input);

        getActionBar().setTitle(getString(R.string.detail_info_input));

        initView();
        initEvent();
    }

    private void initEvent() {
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do something

                Toast.makeText(
                        DetailInputActivity.this,
                        R.string.join_complete,
                        Toast.LENGTH_SHORT
                ).show();
                setResult(RESULT_OK, new Intent());
                finish();
            }
        });

        View.OnClickListener toggleListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.male_button:
                        maleButton.setSelected(true);
                        femaleButton.setSelected(false);
                        break;
                    case R.id.female_button:
                        maleButton.setSelected(false);
                        femaleButton.setSelected(true);
                        break;
                    case R.id.smoke_yes_button:
                        sYesButton.setSelected(true);
                        sNoButton.setSelected(false);
                        break;
                    case R.id.smoke_no_button:
                        sYesButton.setSelected(false);
                        sNoButton.setSelected(true);
                        break;
                    case R.id.drink_yes_button:
                        dYesButton.setSelected(true);
                        dNoButton.setSelected(false);
                        break;
                    case R.id.drink_no_button:
                        dYesButton.setSelected(false);
                        dNoButton.setSelected(true);
                        break;
                }
            }
        };

        maleButton.setOnClickListener(toggleListener);
        femaleButton.setOnClickListener(toggleListener);
        sYesButton.setOnClickListener(toggleListener);
        sNoButton.setOnClickListener(toggleListener);
        dYesButton.setOnClickListener(toggleListener);
        dNoButton.setOnClickListener(toggleListener);
    }

    private void initView() {
        nameField = (EditText) findViewById(R.id.name_field);
        ageField = (EditText) findViewById(R.id.age_field);
        heightField = (EditText) findViewById(R.id.height_field);
        weightField = (EditText) findViewById(R.id.weight_field);
        bloodField = (EditText) findViewById(R.id.blood_field);

        maleButton = (Button) findViewById(R.id.male_button);
        femaleButton = (Button) findViewById(R.id.female_button);

        sYesButton = (Button) findViewById(R.id.smoke_yes_button);
        sNoButton = (Button) findViewById(R.id.smoke_no_button);

        dYesButton = (Button) findViewById(R.id.drink_yes_button);
        dNoButton = (Button) findViewById(R.id.drink_no_button);

        cDiseaseField = (EditText) findViewById(R.id.c_disease_field);
        drugField = (EditText) findViewById(R.id.drug_field);
        alergyField = (EditText) findViewById(R.id.alergy_field);

        doneButton = (Button) findViewById(R.id.done_button);
    }
}