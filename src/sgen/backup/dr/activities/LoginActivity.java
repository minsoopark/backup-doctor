package sgen.backup.dr.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import sgen.backup.dr.R;
import sgen.backup.dr.etc.JsonUtil;
import sgen.backup.dr.etc.PreferencesHelper;
import sgen.backup.dr.models.User;
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

        String recentId = PreferencesHelper.getLoginUserId(this);
        emailField.setText(recentId);

        initEvent();
    }

    private void initEvent() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                pd.setMessage(getString(R.string.loading));
                pd.show();

                LoginRequest request = new LoginRequest(new LoginRequest.LoginRequestCallback() {
                    @Override
                    public void onComplete(JSONObject json) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (pd.isShowing()) pd.dismiss();
                            }
                        });
                        User user = null;
                        try {
                            user = new User(
                                    json.getString("id"),
                                    json.getString("name"),
                                    json.getString("gender"),
                                    json.getInt("birth"),
                                    json.getString("phone"),
                                    json.getInt("height"),
                                    json.getInt("weight"),
                                    json.getString("blood"),
                                    json.getString("smoking"),
                                    json.getString("drinking"),
                                    json.getString("disease"),
                                    json.getString("medicine"),
                                    json.getString("allergy")
                            );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (user == null) {
                            onFail();

                            return;
                        }
                        String userString = JsonUtil.getJsonStringFromMap(user.getMapFromUser());

                        PreferencesHelper.setLoginUserId(LoginActivity.this, user.getId());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("user", userString);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFail() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (pd.isShowing()) pd.dismiss();
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