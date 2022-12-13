package vn.tdtu.mad.learn.database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import vn.tdtu.mad.learn.database.Items.*;

import java.util.List;
@androidx.room.Dao
public interface Dao {
    //TASK
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TaskItem taskItem);

    @Query("DELETE FROM task_items")
    void deleteAll();

    @Query("SELECT * FROM task_items WHERE Solved == :isSolved")
    LiveData<List<TaskItem>> getAllTaskItems(Boolean isSolved);

    @Query("SELECT * FROM task_items WHERE Type == :type AND Solved == :isSolved  ")
    LiveData<List<TaskItem>> getAllMathItems(TaskTypes type, Boolean isSolved );

    @Query("SELECT * FROM task_items WHERE Type == :type AND Solved == :isSolved")
    LiveData<List<TaskItem>> getAllBiologyItems(TaskTypes type, Boolean isSolved);
    @Query("SELECT * FROM task_items WHERE Type == :type AND Solved == :isSolved")
    LiveData<List<TaskItem>> getAllEnglishItems(TaskTypes type, Boolean isSolved);
    @Query("SELECT * FROM task_items WHERE Type == :type AND Solved == :isSolved")
    LiveData<List<TaskItem>> getAllGeologyItems(TaskTypes type, Boolean isSolved);


    //SHOP

    @Insert
    void insert(ShopItem word);

    @Query("DELETE FROM shop_items")
    void deleteAllShop();

    @Query("SELECT * from shop_items WHERE Redeemed == :isReedemed")
    LiveData<List<ShopItem>> getAllShopItems(Boolean isReedemed);

    @Query("SELECT Name AS name, Amount AS amount, ShopType AS type FROM shop_items WHERE Redeemed == :isRedeemed UNION SELECT Name As name, Amount as amount, Type as type FROM task_items WHERE Solved == :isSolved")
            LiveData<List<CreditItem>> getAllCreditItems(Boolean isSolved, Boolean isRedeemed);

    @Query("SELECT * from shop_items WHERE Redeemed == :isReedemed")
    LiveData<List<ShopItem>> getRedeemedShopItems(Boolean isReedemed);

    @Query("SELECT * FROM task_items WHERE Solved == :isSolved")
    LiveData<List<TaskItem>> getSolvedTaskItems(Boolean isSolved);




}
