package vn.tdtu.mad.learn.database;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import vn.tdtu.mad.learn.database.Items.*;


import java.util.List;

public class ItemRepository {
    private Dao mDao;
    private LiveData<List<TaskItem>> mAllTaskItems;
    private LiveData<List<ShopItem>> mAllShopItems;
    private LiveData<List<CreditItem>> mAllCreditItems;
    private LiveData<List<ShopItem>> shopRedeemedItemList;
    private LiveData<List<TaskItem>> taskSolvedItemList;


    ItemRepository(Application application) {
        RoomDatabase db = RoomDatabase.getTaskItemDatabase(application);
        mDao = db.taskDao();
        mAllTaskItems= mDao.getAllTaskItems(false);

        db = RoomDatabase.getShopDatabase(application);
        mDao = db.taskDao();
        mAllShopItems= mDao.getAllShopItems(false);
    }

    public void insert (TaskItem taskItem) {
        new ItemRepository.insertAsyncTask(mDao).execute(taskItem);
    }

    public void insert (ShopItem shopItem) {
        new ItemRepository.insertAsyncTask2(mDao).execute(shopItem);
    }

    public void update (TaskItem taskItem) {
        new ItemRepository.updateAsyncTask(mDao).execute(taskItem);
    }

    public void update (ShopItem shopItem) {
        new ItemRepository.updateAsyncTask2(mDao).execute(shopItem);
    }




    public LiveData<List<TaskItem>> getAllTaskItems() {
        mAllTaskItems= mDao.getAllTaskItems(false);
        return mAllTaskItems;
    }

    public LiveData<List<TaskItem>> getAllMathItems() {
        mAllTaskItems = mDao.getAllMathItems(TaskTypes.MATHS, false);
        return mAllTaskItems;
    }

    public LiveData<List<TaskItem>> getAllEnglishItems() {
        mAllTaskItems = mDao.getAllEnglishItems(TaskTypes.ENGLISH, false);
        return mAllTaskItems;
    }

    public LiveData<List<TaskItem>> getAllBiologyItems() {
        mAllTaskItems = mDao.getAllBiologyItems(TaskTypes.BIOLOGY, false);
        return mAllTaskItems;
    }

    public LiveData<List<TaskItem>> getAllGeologyItems() {
        mAllTaskItems = mDao.getAllGeologyItems(TaskTypes.GEOLOGY, false);
        return mAllTaskItems;
    }


    public LiveData<List<TaskItem>> getSolvedTaskItems() {
        taskSolvedItemList = mDao.getSolvedTaskItems( true);
        return taskSolvedItemList;
    }

    public LiveData<List<ShopItem>> getRedeemedShopItems() {
        shopRedeemedItemList = mDao.getRedeemedShopItems( true);
        return shopRedeemedItemList;
    }

    public LiveData<List<CreditItem>> getAllCreditItems() {
        mAllCreditItems = mDao.getAllCreditItems( true, true);
        return mAllCreditItems;
    }

    LiveData<List<ShopItem>> getAllShopItems() {
        return mAllShopItems;
    }





    private static class insertAsyncTask extends AsyncTask<TaskItem, Void, Void> {

        private Dao mAsyncDao;

        insertAsyncTask(Dao dao) {
            mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(final TaskItem... params) {
            mAsyncDao.insert(params[0]);
            return null;
        }
    }


    private static class updateAsyncTask extends AsyncTask<TaskItem, Void, Void> {

        private Dao mAsyncDao;

        updateAsyncTask(Dao dao) {
            mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(final TaskItem... params) {
            mAsyncDao.update(params[0]);
            return null;
        }
    }


    private static class updateAsyncTask2 extends AsyncTask<ShopItem, Void, Void> {

        private Dao mAsyncDao;

        updateAsyncTask2(Dao dao) {
            mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(final ShopItem... params) {
            mAsyncDao.update(params[0]);
            return null;
        }
    }



    private static class insertAsyncTask2 extends AsyncTask<ShopItem, Void, Void> {

        private Dao mAsyncDao;


        insertAsyncTask2(Dao dao) {
            mAsyncDao = dao;
        }


        @Override
        protected Void doInBackground(final ShopItem... params) {
            mAsyncDao.insert(params[0]);
            return null;
        }
    }

}
