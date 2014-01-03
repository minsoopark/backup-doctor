package sgen.backup.dr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import sgen.backup.dr.R;

public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        getActionBar().hide();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }
}
