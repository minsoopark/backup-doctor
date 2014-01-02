package sgen.backup.dr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import sgen.backup.dr.R;

public class LoginActivity extends BaseActivity {

    private EditText emailField;
    private EditText passwordField;

    private Button loginButton;
    private Button joinButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        getActionBar().hide();

        emailField = (EditText) findViewById(R.id.email_field);
        passwordField = (EditText) findViewById(R.id.password_field);

        loginButton = (Button) findViewById(R.id.login_button);
        joinButton = (Button) findViewById(R.id.join_button);

        initEvent();
    }

    private void initEvent() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}