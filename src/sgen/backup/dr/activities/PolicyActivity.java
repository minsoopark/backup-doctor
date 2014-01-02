package sgen.backup.dr.activities;

import android.os.Bundle;
import sgen.backup.dr.R;

public class PolicyActivity extends BaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_policy);

        getActionBar().setTitle(getString(R.string.agree_policy));
    }
}