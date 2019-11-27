package com.trampcr.mymultiprocesssharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnSaveSp;
    private Button mBtnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnSaveSp = findViewById(R.id.btn_save_sp);
        mBtnSkip = findViewById(R.id.btn_skip);

        mBtnSaveSp.setOnClickListener(this);
        mBtnSkip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_sp:
                SharedPreferencesManager.getInstance(this).setString("name", "trampcr");
                Log.d(SecondActivity.TAG, "MainActivity onCreate: processName = "
                        + SharedPreferencesManager.getInstance(this).getProcessName());
                break;
            case R.id.btn_skip:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            default:
                break;
        }
    }
}
