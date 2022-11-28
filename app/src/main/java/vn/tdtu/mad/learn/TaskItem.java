package vn.tdtu.mad.learn;

import androidx.annotation.NonNull;

public class TaskItem {
    public TaskItem(@NonNull String mName, TaskTypes mType, double mAmount, boolean mSolved) {
        this.mName = mName;
        this.mType = mType;
        this.mAmount = mAmount;
        this.mSolved = mSolved;
    }
    private String mName;
    private TaskTypes mType;
    private double mAmount;
    private boolean mSolved;

    public String getmName() {
        return mName;
    }

    public TaskTypes getmType() {
        return mType;
    }

    public double getmAmount() {
        return mAmount;
    }

    public boolean ismSolved() {
        return mSolved;
    }
}
