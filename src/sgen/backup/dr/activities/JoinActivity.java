package sgen.backup.dr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import sgen.backup.dr.R;
import sgen.backup.dr.etc.RequestCode;

public class JoinActivity extends BaseActivity {

    private EditText emailField;
    private EditText passwordField;

    private TextView policyButton;
    private Button nextButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_join);

        getActionBar().setTitle(getString(R.string.join));

        emailField = (EditText) findViewById(R.id.email_field);
        passwordField = (EditText) findViewById(R.id.password_field);

        policyButton = (TextView) findViewById(R.id.policy_button);
        nextButton = (Button) findViewById(R.id.next_button);

        initEvent();
    }

    private void initEvent() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = emailField.getText().toString();
                String password = passwordField.getText().toString();

                if (id.length() == 0 || password.length() == 0) {
                    return;
                }

                Intent intent = new Intent(JoinActivity.this, DetailInputActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("password", password);
                startActivityForResult(intent, RequestCode.SIGN_UP);
            }
        });

        policyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinActivity.this, PolicyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCode.SIGN_UP && resultCode == RESULT_OK) {
            finish();
        }
    }
}