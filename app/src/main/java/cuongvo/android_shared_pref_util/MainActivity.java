package cuongvo.android_shared_pref_util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cuongvo.android_shared_pref_util.util.AppPref;
import cuongvo.android_shared_pref_util.util.SharedPreferenceHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textfield = (TextView) findViewById(R.id.textfield);

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppPref.saveUserName(MainActivity.this, "tony");
            }
        });

        Button load = (Button) findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textfield.setText("" + SharedPreferenceHelper.getInstance(MainActivity.this).isContainKey(AppPref.USER_NAME));

            }
        });
    }
}
