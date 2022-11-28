package vn.tdtu.mad.learn;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {ShopItem.class}, version = 3, exportSchema = false)
public abstract class ShopItemRoomDatabase extends RoomDatabase {
    public abstract ShopDao shopDao();
    private static ShopItemRoomDatabase INSTANCE;
    static ShopItemRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ShopItemRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ShopItemRoomDatabase.class, "shop_item_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ShopDao mDao;

        PopulateDbAsync(ShopItemRoomDatabase db) {
            mDao = db.shopDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();
            mDao.insert(new ShopItem("Mc Donalds", 22.00, "Discount 10% off! Redeem now for your discount!", ShopTypes.MC_DONALDS,true));
            mDao.insert(new ShopItem("Burger King", 28.00, "Discount 10% off! Redeem now for your discount!",ShopTypes.BURGER_KING,false));
            mDao.insert(new ShopItem("Fortnite", 290.00, "Discount 10% off! Redeem now for your discount!",ShopTypes.FORTNITE,true));
            /*
            mDao.insert(new ShopItem("Test", 22.00,true));
            mDao.insert(new ShopItem("Test2", 28.00, false));
            mDao.insert(new ShopItem("Test3", 290.00, true));*/

            return null;
        }
    }
}
