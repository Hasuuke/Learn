package vn.tdtu.mad.learn.database;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import vn.tdtu.mad.learn.database.Items.*;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private ItemRepository mItemRepository;

    private LiveData<List<ShopItem>> mAllShopItems;
    private LiveData<List<TaskItem>> mAllTaskItems;
    private LiveData<List<CreditItem>> mAllCreditItems;
    private LiveData<List<ShopItem>> shopRedeemedItemList;
    private LiveData<List<TaskItem>> taskSolvedItemList;

    public ItemViewModel(Application application) {
        super(application);
        mItemRepository = new ItemRepository(application);
        mAllShopItems = mItemRepository.getAllShopItems();
        mAllTaskItems = mItemRepository.getAllTaskItems();
        mAllCreditItems = mItemRepository.getAllCreditItems();
    }

    public LiveData<List<ShopItem>> getAllShopItems() {
        mAllShopItems = mItemRepository.getAllShopItems();
        return mAllShopItems;
    }
    public LiveData<List<TaskItem>> getAllTaskItems() {
        mAllTaskItems = mItemRepository.getAllTaskItems();
        return mAllTaskItems;
    }

    public LiveData<List<CreditItem>> getAllCreditItems() {
        mAllCreditItems = mItemRepository.getAllCreditItems();
        return mAllCreditItems;
    }

    public LiveData<List<TaskItem>> getAllMathItems()
    {
        mAllTaskItems = mItemRepository.getAllMathItems();
        return mAllTaskItems;
    }
    public LiveData<List<TaskItem>> getAllEnglishItems()
    {
        mAllTaskItems = mItemRepository.getAllEnglishItems();
        return mAllTaskItems;
    }
    public LiveData<List<TaskItem>> getAllBiologyItems()
    {
        mAllTaskItems = mItemRepository.getAllBiologyItems();
        return mAllTaskItems;
    }
    public LiveData<List<TaskItem>> getAllGeologyItems()
    {
        mAllTaskItems = mItemRepository.getAllGeologyItems();
        return mAllTaskItems;
    }

    public LiveData<List<ShopItem>> getRedeemedShopItems() {
        shopRedeemedItemList = mItemRepository.getRedeemedShopItems();
        return shopRedeemedItemList;
    }

    public LiveData<List<TaskItem>> getSolvedTaskItems() {
        taskSolvedItemList = mItemRepository.getSolvedTaskItems();
        return taskSolvedItemList;
    }


    public void insert(ShopItem shopItem) { mItemRepository.insert(shopItem); }
    public void insert(TaskItem taskItem) { mItemRepository.insert(taskItem); }

}
