package com.example.mandatorycourserating;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {
    private String courseName;
    private int rating;

    protected Course(Parcel in) {
        courseName = in.readString();
        rating = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseName);
        dest.writeInt(rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public Course() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getScore() {
        return rating;
    }

    public void setScore(int score) {
        this.rating = score;
    }
}
