package com.example.mandatorycourserating;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class RateCourseActivity extends AppCompatActivity {

    SeekBar seekBar1, seekBar2, seekBar3, seekBar4, seekBar5, seekBar6;
    TextView seekbar1Result, seekbar2Result, seekbar3Result, seekbar4Result, seekbar5Result, seekbar6Result, courseTitleName;
    Button btnSubmit;
    int progressChangedValue1 = 1, progressChangedValue2 = 1, progressChangedValue3 = 1,
            progressChangedValue4 = 1, progressChangedValue5 = 1, progressChangedValue6 = 1;
    String courseName;
    int averageValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_course);

        init();
        setCourseName();
        seekBarValues();
    }

    public void init() {
        seekBar1 = findViewById(R.id.seekBar1);
        seekBar2 = findViewById(R.id.seekBar2);
        seekBar3 = findViewById(R.id.seekBar3);
        seekBar4 = findViewById(R.id.seekBar4);
        seekBar5 = findViewById(R.id.seekBar5);
        seekBar6 = findViewById(R.id.seekBar6);
        courseTitleName = findViewById(R.id.course_name);

        seekbar1Result = findViewById(R.id.seekbar1_result);
        seekbar2Result = findViewById(R.id.seekbar2_result);
        seekbar3Result = findViewById(R.id.seekbar3_result);
        seekbar4Result = findViewById(R.id.seekbar4_result);
        seekbar5Result = findViewById(R.id.seekbar5_result);
        seekbar6Result = findViewById(R.id.seekbar6_result);

        btnSubmit = findViewById(R.id.button_submit);
    }

    public void seekBarValues() {
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue1 = progress;
                seekbar1Result.setText(getString(R.string.rating) + progressChangedValue1 + getString(R.string.slash) + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue2 = progress;
                seekbar2Result.setText(getString(R.string.rating) + progressChangedValue2 + getString(R.string.slash) + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue3 = progress;
                seekbar3Result.setText(getString(R.string.rating) + progressChangedValue3 + getString(R.string.slash) + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue4 = progress;
                seekbar4Result.setText(getString(R.string.rating) + progressChangedValue4 + getString(R.string.slash) + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue5 = progress;
                seekbar5Result.setText(getString(R.string.rating) + progressChangedValue5 + getString(R.string.slash) + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue6 = progress;
                seekbar6Result.setText(getString(R.string.rating) + progressChangedValue6 + getString(R.string.slash) + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void submitValues(View view) {
        averageValues = (progressChangedValue1 + progressChangedValue2 +
                progressChangedValue3 + progressChangedValue4 + progressChangedValue5 +
                progressChangedValue6) / 6;
        Toast.makeText(this, getString(R.string.overallRating) + averageValues, Toast.LENGTH_LONG).show();
        sendNameAndScore();
        sendEmailWithResults();
    }

    public void setCourseName(){
        Intent courseNameIntent = getIntent();
        courseName = courseNameIntent.getStringExtra(getString(R.string.MENU_ACTIVITY_KEY_COURSE_NAME));
        courseTitleName.setText(courseName);
    }

    public void sendNameAndScore() {
        Intent submittedRating = new Intent(this, MenuActivity.class);

        Bundle nameAndRating = new Bundle();

        nameAndRating.putString(getString(R.string.RATE_COURSE_COURSE_NAME), courseName);
        nameAndRating.putInt(getString(R.string.RATE_COURSE_KEY_RATING_VALUE), averageValues);
        submittedRating.putExtras(nameAndRating);
        startActivity(submittedRating);
    }

    public void sendEmailWithResults() {
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse(getString(R.string.mailToEmail)));
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mailToSubject) + courseName);
        mailIntent.putExtra(Intent.EXTRA_TEXT, courseName + getString(R.string.mailToBody) + averageValues);

        if (mailIntent.resolveActivity(getPackageManager()) != null) {
            try {
                startActivity(mailIntent);
            }
            catch (ActivityNotFoundException aNFE) {
                Toast.makeText(this, getString(R.string.noEmailSetup), Toast.LENGTH_LONG).show();
            }
        }
    }
}
