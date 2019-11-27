package com.trampcr.mymultiprocesssharedpreferences;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "trampcr";

    private Button mBtnGetSp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mBtnGetSp = findViewById(R.id.btn_get_sp);
        Log.d(TAG, "SecondActivity onCreate: processName = "
                + SharedPreferencesManager.getInstance(SecondActivity.this).getProcessName());

        mBtnGetSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = SharedPreferencesManager.getInstance(SecondActivity.this).getString("name", "cr");
                Log.d(TAG, "get sp name = " + name + ", processName = "
                        + SharedPreferencesManager.getInstance(SecondActivity.this).getProcessName());
            }
        });
    }
}
