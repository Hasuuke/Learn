package vn.tdtu.mad.learn;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_items")
public class TaskItem {
    @PrimaryKey(autoGenerate = true)
    public int mID;
    @NonNull
    @ColumnInfo(name = "Name")
    public String mName;
    @NonNull
    @ColumnInfo(name = "Description")
    public String mDescription;
    @ColumnInfo(name = "Amount")
    public double mAmount;
    @ColumnInfo(name = "Type")
    public TaskTypes mType;
    @ColumnInfo(name = "Solved")
    public boolean mSolved;

    public TaskItem(@NonNull String mName, @NonNull String mDescription, TaskTypes mType, double mAmount, boolean mSolved) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mAmount = mAmount;
        this.mType = mType;
        this.mSolved = mSolved;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    @NonNull
    public String getmName() {
        return mName;
    }

    public void setmName(@NonNull String mName) {
        this.mName = mName;
    }

    @NonNull
    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(@NonNull String mDescription) {
        this.mDescription = mDescription;
    }

    public double getmAmount() {
        return mAmount;
    }

    public void setmAmount(double mAmount) {
        this.mAmount = mAmount;
    }

    public TaskTypes getmType() {
        return mType;
    }

    public void setmType(TaskTypes mType) {
        this.mType = mType;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }
}
