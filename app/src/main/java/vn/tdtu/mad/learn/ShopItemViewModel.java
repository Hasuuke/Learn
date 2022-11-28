package vn.tdtu.mad.learn;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ShopItemViewModel extends AndroidViewModel {
    private ShopItemRepository mRepository;
    private LiveData<List<ShopItem>> mAllShopItems;
    public ShopItemViewModel (Application application) {
        super(application);
        mRepository = new ShopItemRepository(application);
        mAllShopItems = mRepository.getAllShopItems();
    }

    public LiveData<List<ShopItem>> getAllShopItems() { return mAllShopItems; }

    public void insert(ShopItem shopItem) { mRepository.insert(shopItem); }

}
