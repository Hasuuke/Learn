package vn.tdtu.mad.learn;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ShopItemViewModel extends AndroidViewModel {
    private ShopItemRepository mShopRepository;
    private TaskItemRepository mTaskRepository;

    private LiveData<List<ShopItem>> mAllShopItems;
    private LiveData<List<TaskItem>> mAllTaskItems;

    public ShopItemViewModel (Application application) {
        super(application);
        mShopRepository = new ShopItemRepository(application);
        mTaskRepository = new TaskItemRepository(application);
        mAllShopItems = mShopRepository.getAllShopItems();
        mAllTaskItems = mTaskRepository.getAllTaskItems();
    }

    public LiveData<List<ShopItem>> getAllShopItems() { return mAllShopItems; }
    public LiveData<List<TaskItem>> getAllTaskItems() { return mAllTaskItems; }
    public LiveData<List<TaskItem>> getAllMathItems()
    {
        mAllTaskItems = mTaskRepository.getAllMathItems();
        return mAllTaskItems;
    }
    public LiveData<List<TaskItem>> getAllEnglishItems()
    {
        mAllTaskItems = mTaskRepository.getAllEnglishItems();
        return mAllTaskItems;
    }
    public LiveData<List<TaskItem>> getAllBiologyItems()
    {
        mAllTaskItems = mTaskRepository.getAllBiologyItems();
        return mAllTaskItems;
    }
    public LiveData<List<TaskItem>> getAllGeologyItems()
    {
        mAllTaskItems = mTaskRepository.getAllGeologyItems();
        return mAllTaskItems;
    }


    public void insert(ShopItem shopItem) { mShopRepository.insert(shopItem); }
    public void insert(TaskItem taskItem) { mTaskRepository.insert(taskItem); }

}
