package sgen.backup.dr.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import sgen.backup.dr.R;
import sgen.backup.dr.etc.PreferencesHelper;
import sgen.backup.dr.etc.ResourceWrapper;
import sgen.backup.dr.network.requests.DataSendRequest;

import java.util.Map;

public class TestResultActivity extends BaseActivity {

    private String userString;
    private String jsonString;
    private boolean fromTest;

    private JSONObject result;

    private TextView txtDepartment;
    private TextView txtWhere;
    private ImageView imgHow;
    private ImageView imgLevel;
    private ImageView imgDetail;
    private Button btnSend;

    private String department;
    private String where;
    private String how;
    private String level;
    private String detail;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_result);

        getActionBar().setTitle(getString(R.string.test_result));

        Intent intent = getIntent();
        fromTest = intent.getBooleanExtra("from_test", false);
        userString = intent.getStringExtra("user");
        jsonString = intent.getStringExtra("json");

        try {
            result = new JSONObject(jsonString);
            init();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void init() throws JSONException {
        txtDepartment = (TextView) findViewById(R.id.department);
        txtWhere = (TextView) findViewById(R.id.where);
        imgHow = (ImageView) findViewById(R.id.how);
        imgLevel = (ImageView) findViewById(R.id.level);
        imgDetail = (ImageView) findViewById(R.id.detail);
        btnSend = (Button) findViewById(R.id.btn_send);

        if (fromTest) btnSend.setVisibility(View.VISIBLE);

        department = (String) result.get("department");
        where = (String) result.get("where");
        how = (String) result.get("how");
        level = (String) result.get("level");
        detail = (String) result.get("detail");

        Map<String, Integer> strResMap = ResourceWrapper.getStringResourceMap();
        Map<String, Integer> drbResMap = ResourceWrapper.getDrawableResourceMap();

        txtDepartment.setText(strResMap.get(department));
        txtWhere.setText(strResMap.get(where));
        imgHow.setImageResource(drbResMap.get(how));
        imgLevel.setImageResource(drbResMap.get(level));
        imgDetail.setImageResource(drbResMap.get(detail));

        initEvent();
    }

    private void initEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle(R.string.test_result);

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                DataSendRequest request = new DataSendRequest(new DataSendRequest.DataSendRequestCallback() {
                    @Override
                    public void onComplete(JSONObject json) {
                        PreferencesHelper.appendHistory(TestResultActivity.this, jsonString);
                    }

                    @Override
                    public void onFail() {

                    }
                });

                request.execute(
                        PreferencesHelper.getLoginUserId(TestResultActivity.this),
                        input.getText().toString(),
                        userString,
                        jsonString,
                        PreferencesHelper.getHistory(TestResultActivity.this)
                );
            }
        });

        alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // cancel
            }
        });

        alert.show();
    }
}