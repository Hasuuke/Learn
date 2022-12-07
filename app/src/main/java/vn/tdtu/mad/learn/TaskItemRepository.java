package vn.tdtu.mad.learn;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskItemRepository {
    private TaskDao mTaskDao;
    private LiveData<List<TaskItem>> mAllTaskItems;

    TaskItemRepository(Application application) {
        RoomDatabase db = RoomDatabase.getTaskItemDatabase(application);
        mTaskDao = db.taskDao();
        mAllTaskItems= mTaskDao.getAllTaskItems(false);
    }



    public LiveData<List<TaskItem>> getAllTaskItems() {
        return mAllTaskItems;
    }

    public LiveData<List<TaskItem>> getAllMathItems() {
        mAllTaskItems = mTaskDao.getAllMathItems(TaskTypes.MATHS, false);
        return mAllTaskItems;
    }

    public LiveData<List<TaskItem>> getAllEnglishItems() {
        mAllTaskItems = mTaskDao.getAllEnglishItems(TaskTypes.ENGLISH, false);
        return mAllTaskItems;
    }

    public LiveData<List<TaskItem>> getAllBiologyItems() {
        mAllTaskItems = mTaskDao.getAllBiologyItems(TaskTypes.BIOLOGY, false);
        return mAllTaskItems;
    }

    public LiveData<List<TaskItem>> getAllGeologyItems() {
        mAllTaskItems = mTaskDao.getAllGeologyItems(TaskTypes.GEOLOGY, false);
        return mAllTaskItems;
    }

    public void insert (TaskItem taskItem) {
        new TaskItemRepository.insertAsyncTask(mTaskDao).execute(taskItem);
    }


    private static class insertAsyncTask extends AsyncTask<TaskItem, Void, Void> {

        private TaskDao mAsyncTaskDao;

        insertAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TaskItem... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
