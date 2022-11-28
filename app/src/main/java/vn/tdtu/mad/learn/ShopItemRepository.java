package vn.tdtu.mad.learn;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ShopItemRepository {
    private ShopDao mShopDao;
    private LiveData<List<ShopItem>> mAllShopItems;

    ShopItemRepository(Application application) {
        ShopItemRoomDatabase db = ShopItemRoomDatabase.getDatabase(application);
        mShopDao = db.shopDao();
        mAllShopItems= mShopDao.getAllShopItems();
    }

    LiveData<List<ShopItem>> getAllShopItems() {
        return mAllShopItems;
    }

    public void insert (ShopItem shopItem) {
        new insertAsyncTask(mShopDao).execute(shopItem);
    }

    private static class insertAsyncTask extends AsyncTask<ShopItem, Void, Void> {

        private ShopDao mAsyncTaskDao;

        insertAsyncTask(ShopDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ShopItem... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
