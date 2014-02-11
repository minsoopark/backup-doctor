package sgen.backup.dr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;
import sgen.backup.dr.R;
import sgen.backup.dr.network.requests.LoginRequest;

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
                LoginRequest request = new LoginRequest(new LoginRequest.LoginRequestCallback() {
                    @Override
                    public void onComplete(JSONObject json) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFail() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(
                                        LoginActivity.this,
                                        R.string.login_error,
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        });
                    }
                });

                request.execute(
                        emailField.getText().toString(),
                        passwordField.getText().toString()
                );
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