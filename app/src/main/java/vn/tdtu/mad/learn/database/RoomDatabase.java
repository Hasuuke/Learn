package vn.tdtu.mad.learn.database;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;
import vn.tdtu.mad.learn.database.Items.*;

import static vn.tdtu.mad.learn.database.Items.TaskTypes.*;

@Database(entities = {ShopItem.class, TaskItem.class}, version = 7, exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    private static volatile RoomDatabase INSTANCE;
    public abstract Dao taskDao();



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

        private final Dao mDao;

        PopulateDbAsync(RoomDatabase db) {
            mDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAllShop();
            mDao.deleteAll();

            mDao.insert(new ShopItem("Mc Donalds", 15, "Discount 10% off! Redeem now for your discount!", ShopTypes.MC_DONALDS, false));
            mDao.insert(new ShopItem("Burger King", 35, "Discount 15% off! Redeem now for your discount!", ShopTypes.BURGER_KING, false));
            mDao.insert(new ShopItem("Fortnite", 50, "Discount 20% off! Redeem now for your discount!", ShopTypes.FORTNITE, false));
            mDao.insert(new ShopItem("Mc Donalds", 15, "Discount 10% off! Redeem now for your discount!", ShopTypes.MC_DONALDS, false));
            mDao.insert(new ShopItem("Burger King", 35, "Discount 15% off! Redeem now for your discount!", ShopTypes.BURGER_KING, false));
            mDao.insert(new ShopItem("Fortnite", 50, "Discount 20% off! Redeem now for your discount!", ShopTypes.FORTNITE, false));
            mDao.insert(new ShopItem("Mc Donalds", 15, "Discount 10% off! Redeem now for your discount!", ShopTypes.MC_DONALDS, false));
            mDao.insert(new ShopItem("Burger King", 35, "Discount 15% off! Redeem now for your discount!", ShopTypes.BURGER_KING, false));
            mDao.insert(new ShopItem("Fortnite", 50, "Discount 20% off! Redeem now for your discount!", ShopTypes.FORTNITE, false));


            mDao.insert(new ShopItem("Fortnite", 50, "Discount 10% off! Redeem now for your discount!", ShopTypes.FORTNITE, true));




            mDao.insert(new TaskItem("DNA Transcription", "Describe the DNA Transcription", BIOLOGY, 25, false));
            mDao.insert(new TaskItem("Polynoms", "Solve the Polynoms", MATHS, 50, false));
            mDao.insert(new TaskItem("Spheres", "Name all Spheres of the Earth", GEOLOGY, 25, false));
            mDao.insert(new TaskItem("Essay", "Discuss the Covid 19 Pandemic", ENGLISH, 30, false));
            mDao.insert(new TaskItem("DNA Transcription", "Describe the DNA Transcription", BIOLOGY, 25, false));

            mDao.insert(new TaskItem("Polynoms", "Solve the Polynoms", MATHS, 50, false));
            mDao.insert(new TaskItem("Spheres", "Name all Spheres of the Earth", GEOLOGY, 25, false));
            mDao.insert(new TaskItem("Essay", "Discuss the Covid 19 Pandemic", ENGLISH, 30, false));
            mDao.insert(new TaskItem("DNA Transcription", "Describe the DNA Transcription", BIOLOGY, 25, false));

            mDao.insert(new TaskItem("Polynoms", "Solve the Polynoms", MATHS, 50, false));
            mDao.insert(new TaskItem("Spheres", "Name all Spheres of the Earth", GEOLOGY, 25, false));
            mDao.insert(new TaskItem("Essay", "Discuss the Covid 19 Pandemic", ENGLISH, 30, false));
            mDao.insert(new TaskItem("DNA Transcription", "Describe the DNA Transcription", BIOLOGY, 25, false));


            mDao.insert(new TaskItem("DNA Transcription", "Describe the DNA Transcription", BIOLOGY, 5.00, true));
            mDao.insert(new TaskItem("Polynoms", "Solve the Polynoms", MATHS, 20.00, true));
            mDao.insert(new TaskItem("Spheres", "Name all Spheres of the Earth", GEOLOGY, 50.00, true));
            mDao.insert(new TaskItem("Essay", "Discuss the Covid 19 Pandemic", ENGLISH, 25.00, true));


            return null;
        }
    }
}
