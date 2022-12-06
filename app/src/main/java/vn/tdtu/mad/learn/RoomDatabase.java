package vn.tdtu.mad.learn;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static vn.tdtu.mad.learn.TaskTypes.*;

@Database(entities = {ShopItem.class, TaskItem.class}, version = 5, exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    public abstract ShopDao shopDao();

    private static volatile RoomDatabase INSTANCE;

    public abstract TaskDao taskDao();


    static RoomDatabase getShopDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RoomDatabase.class, "shop_item_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


        static RoomDatabase getTaskItemDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (RoomDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                        RoomDatabase.class, "task_item_database")
                                .fallbackToDestructiveMigration()
                                .addCallback(sRoomDatabaseCallback)
                                .build();
                    }
                }
            }
            return INSTANCE;

    }

    private static androidx.room.RoomDatabase.Callback sRoomDatabaseCallback =
            new androidx.room.RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ShopDao mShopDao;
        private final TaskDao mTaskDao;

        PopulateDbAsync(RoomDatabase db) {
            mShopDao = db.shopDao();
            mTaskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mShopDao.deleteAll();
            mShopDao.insert(new ShopItem("Mc Donalds", 22.00, "Discount 10% off! Redeem now for your discount!", ShopTypes.MC_DONALDS, true));
            mShopDao.insert(new ShopItem("Burger King", 28.00, "Discount 10% off! Redeem now for your discount!", ShopTypes.BURGER_KING, false));
            mShopDao.insert(new ShopItem("Fortnite", 290.00, "Discount 10% off! Redeem now for your discount!", ShopTypes.FORTNITE, true));

            mTaskDao.deleteAll();
            mTaskDao.insert(new TaskItem("DNA Transcription", "Describe the DNA Transcription", BIOLOGY, 22.00, false));
            mTaskDao.insert(new TaskItem("Polynoms", "Solve the Polynoms", MATHS, 25.00, false));
            mTaskDao.insert(new TaskItem("Spheres", "Name all Spheres of the Earth", GEOLOGY, 40.00, false));
            mTaskDao.insert(new TaskItem("Essay", "Discuss the Covid 19 Pandemic", ENGLISH, 25.00, false));

            return null;
        }
    }


}
