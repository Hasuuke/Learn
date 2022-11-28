package vn.tdtu.mad.learn;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShopDao {
    @Insert
    void insert(ShopItem word);

    @Query("DELETE FROM shop_items")
    void deleteAll();

    @Query("SELECT * from shop_items")
    LiveData<List<ShopItem>> getAllShopItems();
}
