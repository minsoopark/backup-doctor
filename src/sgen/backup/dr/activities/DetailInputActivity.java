package sgen.backup.dr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;
import sgen.backup.dr.R;
import sgen.backup.dr.network.requests.JoinRequest;

public class DetailInputActivity extends BaseActivity {

    private EditText nameField;
    private EditText phoneField;
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

    private String id;
    private String password;
    private String gender;
    private Boolean smoking = false;
    private Boolean drinking = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_info_input);

        getActionBar().setTitle(getString(R.string.detail_info_input));

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        password = intent.getStringExtra("password");
        gender = getString(R.string.male);

        initView();
        initEvent();
    }

    private void initEvent() {
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JoinRequest request = new JoinRequest(new JoinRequest.JoinRequestCallback() {
                    @Override
                    public void onComplete(JSONObject json) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(
                                        DetailInputActivity.this,
                                        R.string.join_complete,
                                        Toast.LENGTH_SHORT
                                ).show();
                                setResult(RESULT_OK, new Intent());
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onFail() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(
                                        DetailInputActivity.this,
                                        R.string.join_error,
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        });
                    }
                });

                request.execute(
                        id,
                        password,
                        nameField.getText().toString(),
                        gender,
                        phoneField.getText().toString(),
                        ageField.getText().toString(),
                        heightField.getText().toString(),
                        weightField.getText().toString(),
                        bloodField.getText().toString(),
                        smoking.toString(),
                        drinking.toString(),
                        cDiseaseField.getText().toString(),
                        drugField.getText().toString(),
                        alergyField.getText().toString()
                );
            }
        });

        View.OnClickListener toggleListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.male_button:
                        maleButton.setSelected(true);
                        femaleButton.setSelected(false);
                        gender = getString(R.string.male);
                        break;
                    case R.id.female_button:
                        maleButton.setSelected(false);
                        femaleButton.setSelected(true);
                        gender = getString(R.string.female);
                        break;
                    case R.id.smoke_yes_button:
                        sYesButton.setSelected(true);
                        sNoButton.setSelected(false);
                        smoking = true;
                        break;
                    case R.id.smoke_no_button:
                        sYesButton.setSelected(false);
                        sNoButton.setSelected(true);
                        smoking = false;
                        break;
                    case R.id.drink_yes_button:
                        dYesButton.setSelected(true);
                        dNoButton.setSelected(false);
                        drinking = true;
                        break;
                    case R.id.drink_no_button:
                        dYesButton.setSelected(false);
                        dNoButton.setSelected(true);
                        drinking = false;
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
        phoneField = (EditText) findViewById(R.id.phone_field);
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