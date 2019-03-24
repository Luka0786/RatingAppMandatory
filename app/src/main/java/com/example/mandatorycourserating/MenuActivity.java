package com.example.mandatorycourserating;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {


    Button buttonSoftwareConstruction, buttonSoftwareDesign, buttonAngular, buttonAndroidapp, buttonLogout;
    TextView softwareConScore, softwareDesScore, angularScore,  androidAppScore;

    Course softwareConstrcution = new Course();
    Course softwareDesign = new Course();
    Course angular = new Course();
    Course androidApp = new Course();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
        displayRatings();
    }

    public void rateCourse(String courseName) {
        Intent rateACourse = new Intent(this, RateCourseActivity.class);
        rateACourse.putExtra(getString(R.string.MENU_ACTIVITY_KEY_COURSE_NAME), courseName);
        startActivity(rateACourse);
    }

    public void logout(View view) {
        Intent logout = new Intent(this, MainActivity.class);
        Toast.makeText(this, getString(R.string.logout_message), Toast.LENGTH_SHORT).show();
        startActivity(logout);
    }

    public void init() {
        softwareConstrcution.setCourseName(getString(R.string.software_construction));
        softwareDesign.setCourseName(getString(R.string.software_design));
        angular.setCourseName(getString(R.string.angular));
        androidApp.setCourseName(getString(R.string.android_app));

        buttonSoftwareConstruction = findViewById(R.id.button_softwarekon);
        buttonSoftwareConstruction.setText(softwareConstrcution.getCourseName());
        buttonSoftwareConstruction.setOnClickListener(this);

        buttonSoftwareDesign = findViewById(R.id.button_softwaredes);
        buttonSoftwareDesign.setText(softwareDesign.getCourseName());
        buttonSoftwareDesign.setOnClickListener(this);

        buttonAngular = findViewById(R.id.button_angular);
        buttonAngular.setText(angular.getCourseName());
        buttonAngular.setOnClickListener(this);

        buttonAndroidapp = findViewById(R.id.button_androidapp);
        buttonAndroidapp.setText(androidApp.getCourseName());
        buttonAndroidapp.setOnClickListener(this);

        buttonLogout = findViewById(R.id.button_logout);

        softwareConScore = findViewById(R.id.softwareConScore);
        softwareDesScore = findViewById(R.id.softwareDesScore);
        angularScore = findViewById(R.id.angularScore);
        androidAppScore = findViewById(R.id.androidAppScore);
    }

    public void displayRatings() {
        Bundle nameAndRating = getIntent().getExtras();

        if (nameAndRating != null) {
            String course = nameAndRating.getString(getString(R.string.RATE_COURSE_COURSE_NAME));
            int rating = nameAndRating.getInt(getString(R.string.RATE_COURSE_KEY_RATING_VALUE));

            if (course != null) {
                if (course.equals(softwareConstrcution.getCourseName())) {
                    softwareConstrcution.setScore(rating);
                    calculateRatings(softwareConstrcution.getScore(), softwareConScore);
                }

                if (course.equals(softwareDesign.getCourseName())) {
                    softwareDesign.setScore(rating);
                    calculateRatings(softwareDesign.getScore(), softwareDesScore);
                }

                if (course.equals(angular.getCourseName())) {
                    angular.setScore(rating);
                    calculateRatings(angular.getScore(), angularScore);
                }

                if (course.equals(androidApp.getCourseName())) {
                    androidApp.setScore(rating);
                    calculateRatings(androidApp.getScore(), androidAppScore);
                }
            }
        }
    }

    public void calculateRatings(int score, TextView textView) {
        if (score > 90) {
            textView.setText(getString(R.string.gradeA));
            textView.setTextColor(Color.GREEN);
        }

        else if (score > 80) {
            textView.setText(getString(R.string.gradeB));
            textView.setTextColor(Color.YELLOW);
        }

        else if (score > 70) {
            textView.setText(getString(R.string.gradeC));
            textView.setTextColor(Color.YELLOW);
        }

        else if (score > 60) {
            textView.setText(getString(R.string.gradeD));
            textView.setTextColor(Color.RED);
        }

        else if (score > 50) {
            textView.setText(getString(R.string.gradeE));
            textView.setTextColor(Color.RED);
        }

        else {
            textView.setText(getString(R.string.gradeX));
            textView.setTextColor(Color.RED);
        }
    }

    @Override
    public void onClick(View v) {
        String courseName = getString(R.string.space);

        switch(v.getId()) {
            case R.id.button_softwarekon:
                courseName = softwareConstrcution.getCourseName();
                break;
            case R.id.button_softwaredes:
                courseName = softwareDesign.getCourseName();
                break;
            case R.id.button_angular:
                courseName = angular.getCourseName();
                break;
            case R.id.button_androidapp:
                courseName = androidApp.getCourseName();
                break;
        }
        rateCourse(courseName);
    }
}