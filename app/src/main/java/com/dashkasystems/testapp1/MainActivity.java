package com.dashkasystems.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import ru.yandex.speechkit.SpeechKit;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpeechKit.getInstance().configure(getApplicationContext(), "a1122cec-9872-4476-92df-bc3143d3ba45");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.natureBtn:
                Intent intent = new Intent(this, ChooseExerciseActivity.class);
                this.startActivity(intent);
                break;
            case R.id.houseBtn:

                break;
            case R.id.familyBtn:
                break;
            case R.id.transportBtn:
                break;
            case R.id.toyBtn:
                break;
        }
    }
}
